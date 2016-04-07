package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Game.Element.Fight_Team;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team;
import com.geekmecrazy.madandarmed.Game.Factory.CreepFactory;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;

public class Fight_CreepManager {


	/** Disable object's instantiation (private constructor) */
	public Fight_CreepManager(Fight_Team teamPlayer, Fight_Team teamIA){
		this.teamPlayer = teamPlayer;
		this.teamIA = teamIA;
		this.listCreeps = new ArrayList<Creep>();
		this.listCreepsRecycle = new HashSet<Creep>();
        this.listSelectedCreeps = new ArrayList<Creep>();
	}


	public static final int MAX_UNITS=1000;

	// Team
	private Fight_Team teamPlayer;
	private Fight_Team teamIA;

	// Creeps
	private List<Creep> listCreeps;

    // Selected Creeps
    private List<Creep> listSelectedCreeps;

	// Dead & Recycle
	private Set<Creep> listCreepsRecycle;
	
	/** Enregistre les demandes création de creep */
	public void askForCreateCreep(CreepType creepType, Fight_Team team, float posX, float posY){
		CreepPattern creepPattern = PatternLoader.getCreepsPattern().get(creepType.name());
		
		if(team.hasEnoughtMoney(creepPattern.getPrice())){
			team.subMoney(creepPattern.getPrice());
			
			team.getListAskForCreateCreep().add(creepType);
			team.getListAskForCreateCreepPosX().add(posX);
			team.getListAskForCreateCreepPosY().add(posY);
		}
	}

	/** Excecute les demandes de création de creep */
	public void excuteAskForCreateCreep(){
		int size;
		
		size = teamPlayer.getListAskForCreateCreep().size();
		for (int i=0; i<size; i++){
			createCreep(teamPlayer.getListAskForCreateCreep().get(i), teamPlayer, teamPlayer.getListAskForCreateCreepPosX().get(i), teamPlayer.getListAskForCreateCreepPosY().get(i));
		}
		
		size = teamIA.getListAskForCreateCreep().size();
		for (int i=0; i<size; i++){
			createCreep(teamIA.getListAskForCreateCreep().get(i), teamIA, teamIA.getListAskForCreateCreepPosX().get(i), teamIA.getListAskForCreateCreepPosY().get(i));
		}

		teamPlayer.clearListsAskForCreateCreep();
		teamIA.clearListsAskForCreateCreep();
	}

	/** Creation du nouveau creep */
	public void createCreep(CreepType creepID, GamePlay_Team team, float spawnPosX, float spawnPosY){
		CreepPattern creepPattern = PatternLoader.getCreepsPattern().get(creepID.name());
		Creep creep = CreepFactory.createCreep(creepPattern, team, spawnPosX, spawnPosY);
		addCreep(creep);
	}

	/** Enregistre le creep dans le manager */
	public void addCreep(Creep newCreep){
		if(listCreeps.size()<MAX_UNITS) {
			newCreep.getMyTeam().addMilitary(newCreep);
			listCreeps.add(newCreep);

            System.out.println("NUMBER OF UNITS : " + this.getCreepsNumber());		}
	}

    public int getCreepsNumber(){
        return listCreeps.size();
    }

	/** Desenregistre le creep dans le manager */
	public void removeCreep(Creep creep){
		listCreepsRecycle.add(creep);
	}

	/** Recyclage du creep mort */
	public void recycleCreep(){
		
		for (Creep creep : listCreepsRecycle){
			creep.getMyTeam().removeMilitary(creep);
			ScreenManager.fightScreen.getOtherTeam(creep.getMyTeam()).addScore(creep.getPattern().getPrice());
			listCreeps.remove(creep);
            listSelectedCreeps.remove(creep);
            GlobalManager.poolManager.getCreepPool().free(creep); //appel ensuite creep.reset()
		}
		listCreepsRecycle.clear();

	}


	/** Mise a jour de l'état des creeps a chaque cycle */
	public void runUpdateNextState(){
		for(Creep ce : listCreeps) ce.onUpdateNextState();
	}

	public List<Creep> getListCreeps() {
		return listCreeps;
	}


    public void addSelectedCreep(Creep selectedCreep){
        listSelectedCreeps.add(selectedCreep);
    }

    public void clearSelectedCreep(){
        for (int i=0; i<listSelectedCreeps.size(); i++){
            Creep creep = listSelectedCreeps.get(i);
            creep.getMilitaryRenderer().setColor(1f, 1f, 1f, 1f);
        }
        listSelectedCreeps.clear();
    }

    public void applyTargetPointToSelectedCreep(final float pX, final float pY){

        for (int i=0; i<listSelectedCreeps.size(); i++){
            Creep creep = listSelectedCreeps.get(i);
            creep.setIsGoPoint(true);
            creep.getGoPoint().set(pX, pY);
        }
    }
}