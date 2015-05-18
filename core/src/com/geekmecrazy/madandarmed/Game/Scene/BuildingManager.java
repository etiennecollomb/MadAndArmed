package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.Game.Factory.BuildingFactory;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.LevelBuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingType;
import com.geekmecrazy.madandarmed.XML.DataManager;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class BuildingManager {
	
	// ===========================================================
	// Singleton manager
	// ===========================================================
	private static BuildingManager buildingManager;
	
	/** Creation et initialisation du manager */
	public static void initManager(Scene scene, Team teamPlayer, Team teamIA) {
		if (buildingManager != null) throw new RuntimeException("buildingManager already created ! buildingManager is not null");
		buildingManager = new BuildingManager(scene, teamPlayer, teamIA);
	}

	/** Disable object's instantiation (private constructor) */
	private BuildingManager(Scene scene, Team teamPlayer, Team teamIA){
		this.teamPlayer = teamPlayer;
		this.teamIA = teamIA;
		this.listBuildings = new ArrayList<Building>();
		this.listBuildingsRecycle = new HashSet<Building>();
	}
	
	/** Acces au manager */
	public static BuildingManager getManager(){
		if (buildingManager == null) throw new RuntimeException("buildingManager not created ! buildingManager is null");
		return buildingManager;
	}

	/** Destruction du manager */
	public static void destroyManager(){
		if (buildingManager == null) throw new RuntimeException("buildingManager not created ! buildingManager is null");
		buildingManager.destroy();
	}
	
	/** Destruction */
	public void destroy(){
		buildingManager=null;
	}
	
	// ===========================================================
	// Manager
	// ===========================================================
	public static final int MAX_DEAD_BUILDING=10;

	// Team
	private Team teamPlayer;
	private Team teamIA;
	
	private List<Building> listBuildings;
	
	// Dead & Recycle
	private Set<Building> listBuildingsRecycle;

	
	/** Création des building en début de partie */
	public void initBuildingAtStart(){
		for(LevelBuildingPattern buildingLevelModel: DataManager.getMapPattern().getListBuildingPlayer()){
			BuildingPattern buildingPattern = DataManager.getBuildingsPatern().get(BuildingType.valueOf(buildingLevelModel.getModelID().toString()));
			BuildingFactory.create(buildingLevelModel.getPosNodeX()* GlobalManager.BIG_NODESIZE, buildingLevelModel.getPosNodeY()*GlobalManager.BIG_NODESIZE, buildingPattern, teamPlayer);
		}
		for(LevelBuildingPattern buildingLevelModel: DataManager.getMapPattern().getListBuildingIA()){
			BuildingPattern buildingPattern = DataManager.getBuildingsPatern().get(BuildingType.valueOf(buildingLevelModel.getModelID().toString()));
			BuildingFactory.create(buildingLevelModel.getPosNodeX()*GlobalManager.BIG_NODESIZE, buildingLevelModel.getPosNodeY()*GlobalManager.BIG_NODESIZE, buildingPattern, teamIA);
		}
	}
	
	
	// Cr�ation d'une Building (+gestion argent)
	public void createBuilding(Team team, float posX, float posY, float width, float height, BuildingPattern buildingPattern) {
		if (team.hasEnoughtMoney(buildingPattern.getPrice())) {
			Life life = PoolManager.getManager().getLifePool().obtain();
			life.init(100);
			BuildingFactory.create(posX, posY, buildingPattern, team);
			team.subMoney(buildingPattern.getPrice());
		}
	}
	
	
	/** Enregistre le building dans le manager */
	public void addBuilding(Building newBuilding){
		newBuilding.getMyTeam().addMilitary(newBuilding);
		listBuildings.add(newBuilding);
	}

	/** Initialisation des cible des building */
	public void initTarget(){
		for(Building b : listBuildings){
			b.getAttackBehavior().setMainTarget(FightScreen.getManager().getOtherTeam(b.getMyTeam()).getCastle());
			b.getAttackBehavior().setCurrentTarget(FightScreen.getManager().getOtherTeam(b.getMyTeam()).getCastle());
		}
	}

	///////////
	
	/** Desenregistre le building dans le manager */
	public void removeBuilding(Building building){
		listBuildingsRecycle.add(building);
	}

	/** Recyclage du creep mort */
	public void recycleBuilding(){

		for (Building building : listBuildingsRecycle){
			/*
			building.getMyTeam().removeMilitary(building);
			building.getMyTeam().getStateMap().removeBuilding(building);
			GameManager.getManager().getotherTeam(building.getMyTeam()).addScore(building.getPattern().getPrice());
			*/
			listBuildings.remove(building);
			//building.recycle();
			PoolManager.getManager().getTurretPool().free((Turret)building); //appel ensuite creep.reset()
		}
		listBuildingsRecycle.clear();

	}
	
	/** Mise a jour de l'état des buildings à chaque cycle */
	public void runUpdateNextState(){	
		for(Building building : listBuildings) building.onUpdateNextState();
	}

	
	public List<Building> getListBuildings() {
		return listBuildings;
	}
}
