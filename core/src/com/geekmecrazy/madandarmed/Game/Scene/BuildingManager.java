package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Barricade;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.Game.Element.Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Factory.BuildingFactory;
import com.geekmecrazy.madandarmed.Json.DataLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingMapPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class BuildingManager {
	
	private List<Building> listBuildings;

	/** list of buildings to delete */
	private Set<Building> listBuildingsRecycle;

	// ===========================================================
	// Constructors
	// ===========================================================

	private static BuildingManager buildingManager;
	
	/** Creation et initialisation du manager */
	public static void initManager() {
		if (buildingManager != null) throw new RuntimeException("buildingManager already created ! buildingManager is not null");
		buildingManager = new BuildingManager();
	}

	/** Disable object's instantiation (private constructor) */
	private BuildingManager(){
		this.listBuildings = new ArrayList<Building>();
		this.listBuildingsRecycle = new HashSet<Building>();
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
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	public static BuildingManager getManager(){
		return buildingManager;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	/** Creation des buildings en debut de partie */
	public void initBuildingAtStart(){
		
		for(BuildingMapPattern buildingLevelModel: DataLoader.getMapsPattern().get("MAP_1").getTeamMapPattern().get(TeamID.TEAM1.name()).getBuildingsList()){
			BuildingPattern buildingPattern = DataLoader.getBuildingsPattern().get(buildingLevelModel.getBuildingType().name());
			BuildingFactory.create(buildingLevelModel.getGridPositionX(), buildingLevelModel.getGridPositionY(), buildingPattern, FightScreen.teamPlayer);
		}
		for(BuildingMapPattern buildingLevelModel: DataLoader.getMapsPattern().get("MAP_1").getTeamMapPattern().get(TeamID.TEAM2.name()).getBuildingsList()){
			BuildingPattern buildingPattern = DataLoader.getBuildingsPattern().get(buildingLevelModel.getBuildingType().name());
			BuildingFactory.create(buildingLevelModel.getGridPositionX(), buildingLevelModel.getGridPositionY(), buildingPattern, FightScreen.teamIA);
		}
	}
		
	/** Creation d'une Building (+gestion argent) */
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
			if(b.getAttackBehavior()!=null){
				b.getAttackBehavior().setMainTarget(FightScreen.getManager().getOtherTeam(b.getMyTeam()).getCastle());
				b.getAttackBehavior().setCurrentTarget(FightScreen.getManager().getOtherTeam(b.getMyTeam()).getCastle());
			}
		}
	}

	/** Desenregistre le building dans le manager */
	public void removeBuilding(Building building){
		listBuildingsRecycle.add(building);
	}

	/** Recyclage du creep mort */
	public void recycleBuilding(){

		for (Building building : listBuildingsRecycle){
			listBuildings.remove(building);
			
			if(building instanceof Turret)
				PoolManager.getManager().getTurretPool().free((Turret)building);
			if(building instanceof Barricade)
				PoolManager.getManager().getBarricadePool().free((Barricade)building);
			
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
