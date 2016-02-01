package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Factory.CreepFactory;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;
import com.geekmecrazy.madandarmed.pool.PoolManager;

public class CreepManager {

	// ===========================================================
	// Singleton manager
	// ===========================================================
	private static CreepManager creepManager;

	/** Creation et initialisation du manager */
	public static void initManager(Team teamPlayer, Team teamIA) {
		if (creepManager != null) throw new RuntimeException("creepManager already created ! creepManager is not null");
		creepManager = new CreepManager(teamPlayer, teamIA);
	}

	/** Disable object's instantiation (private constructor) */
	private CreepManager(Team teamPlayer, Team teamIA){
		this.teamPlayer = teamPlayer;
		this.teamIA = teamIA;
		this.listCreeps = new ArrayList<Creep>();
		this.listCreepsRecycle = new HashSet<Creep>();
        this.listSelectedCreeps = new ArrayList<Creep>();
	}

	/** Acces au manager */
	public static CreepManager getManager(){
		if (creepManager == null) throw new RuntimeException("creepManager not created ! creepManager is null");
		return creepManager;
	}

	/** Destruction du manager */
	public static void destroyManager(){
		if (creepManager == null) throw new RuntimeException("creepManager not created ! creepManager is null");
		creepManager.destroy();
	}

	/** Destruction */
	public void destroy(){
		creepManager=null;
	}

	// ===========================================================
	// Manager
	// ===========================================================
	public static final int MAX_UNITS=1000;
	public static final int MAX_DEAD_UNITS=100;

	// Team
	private Team teamPlayer;
	private Team teamIA;

	// Creeps
	private List<Creep> listCreeps;

    // Selected Creeps
    private List<Creep> listSelectedCreeps;

	// Dead & Recycle
	private Set<Creep> listCreepsRecycle;
	
	/** Enregistre les demandes création de creep */
	public void askForCreateCreep(CreepType creepType, Team team){
		CreepPattern creepPattern = PatternLoader.getCreepsPattern().get(creepType.name());
		if(team.hasEnoughtMoney(creepPattern.getPrice())){
			team.subMoney(creepPattern.getPrice());
			team.getListAskForCreateCreep().add(creepType);
		}
	}

	/** Excecute les demandes de création de creep */
	public void excuteAskForCreateCreep(){
		for (CreepType creepType : teamPlayer.getListAskForCreateCreep()){
			createCreep(creepType, teamPlayer);
		}
		for (CreepType creepType : teamIA.getListAskForCreateCreep()){
			createCreep(creepType, teamIA);
		}
		teamPlayer.getListAskForCreateCreep().clear();
		teamIA.getListAskForCreateCreep().clear();
	}

	/** Creation du nouveau creep */
	public void createCreep(CreepType creepID, Team team){
		CreepPattern creepPattern = PatternLoader.getCreepsPattern().get(creepID.name());
		Creep creep = CreepFactory.createCreep(creepPattern, team);
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
			FightScreen.getManager().getOtherTeam(creep.getMyTeam()).addScore(creep.getPattern().getPrice());
			listCreeps.remove(creep);
            listSelectedCreeps.remove(creep);
			PoolManager.getManager().getCreepPool().free(creep); //appel ensuite creep.reset()
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