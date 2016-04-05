package com.geekmecrazy.madandarmed.Game.Element;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.GamePlayScreen;
import com.geekmecrazy.madandarmed.IA.StateMap;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingName;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;

/**
 * Joueur (thune+score...) :)
 */
public abstract class GamePlay_Team extends GameElement {

	public static enum TeamID{
		TEAM1,
		TEAM2
	}
	
	private TeamID teamID;
	
	private List<CreepType> listAskForCreateCreep;
	private List<Float> listAskForCreateCreepPosX; //Stock provisoirement la position de spawn (car les spawnbuilding peuvent bouger, pas tres propre
	private List<Float> listAskForCreateCreepPosY; //Stock provisoirement la position de spawn (car les spawnbuilding peuvent bouger, pas tres propre
	
	private List<BuildingName> listAskForCreateSpawnBuilding;
	
	private List<Military> listMilitary; // liste des Military de la team (building + creep)
	private int currentNbMilitary; // Nombre de Military courante (plutot que .size(), gain de temps? )
	
	private StateMap stateMap = new StateMap();

	

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public GamePlay_Team(TeamID teamID_, int starMapWidth, int starMapHeight) {
		this.listMilitary = new ArrayList<Military>(GamePlayScreen.MAX_UNITS);
		this.listAskForCreateCreep=new ArrayList<CreepType>();
		this.listAskForCreateCreepPosX=new ArrayList<Float>();
		this.listAskForCreateCreepPosY=new ArrayList<Float>();
		this.listAskForCreateSpawnBuilding=new ArrayList<BuildingName>();
		this.teamID = teamID_;
		
		this.stateMap.init(starMapWidth, starMapHeight);
	}

	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public TeamID getTeamID() {
		return teamID;
	}
	
	public List<CreepType> getListAskForCreateCreep() {
		return listAskForCreateCreep;
	}	
	
	public List<Float> getListAskForCreateCreepPosX() {
		return listAskForCreateCreepPosX;
	}

	public List<Float> getListAskForCreateCreepPosY() {
		return listAskForCreateCreepPosY;
	}
	
	public List<BuildingName> getListAskForCreateSpawnBuilding() {
		return listAskForCreateSpawnBuilding;
	}
	
	public StateMap getStateMap() {
		return stateMap;
	}
	
	
	// ===========================================================
	// Methods
	// ===========================================================

	// Add Military
	public void addMilitary(Military military){
		listMilitary.add(military);
		currentNbMilitary++;
	}
	
	// Remove Military
	public void removeMilitary(Military military){
		listMilitary.remove(military);
		currentNbMilitary--;
	}

	// Check si (x_,y_) a un voisin dans cette team - Coord (x_,y_) en smallNode
	public Military getNearestMilitary(int x_, int y_){
		int min_distance = 99999999;
		Military nearestMilitary = null;
		for(int i=0; i<currentNbMilitary; i++){
				Military m = listMilitary.get(i);
				if(x_ >= m.getSmallNodeX()-m.getVisibilityRadiusRange())
					if(y_ >= m.getSmallNodeY()-m.getVisibilityRadiusRange())
						if(x_ <= m.getSmallNodeX()+m.getVisibilityRadiusRange())
							if(y_ <= m.getSmallNodeY()+m.getVisibilityRadiusRange()){
								//target encore plus proche?
								int currentDistance = Math.abs(x_-listMilitary.get(i).getSmallNodeX())+Math.abs(y_-listMilitary.get(i).getSmallNodeY());
								if(currentDistance<min_distance){
									min_distance=currentDistance;
									nearestMilitary=listMilitary.get(i);
								}
							}
		}
		return nearestMilitary;
	}

	
	public abstract void  notifyListeners();

	public void clearListsAskForCreateCreep() {
		listAskForCreateCreep.clear();
		listAskForCreateCreepPosX.clear();
		listAskForCreateCreepPosY.clear();
	}

	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	

	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
	}
	
}
