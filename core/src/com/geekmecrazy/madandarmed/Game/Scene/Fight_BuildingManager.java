package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.geekmecrazy.madandarmed.Game.Element.Barricade;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.BaseBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.Game.Element.Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Factory.BuildingFactory;
import com.geekmecrazy.madandarmed.Game.Factory.CreepFactory;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingMapPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingName;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class Fight_BuildingManager {

	/** Spawn Building (spawn unit) : unreal building non destroyable **/
	private List<SpawnBuilding> listSpawnBuildings;

	/** real building destroyable **/
	private List<Building> listBuildings;

	/** list of buildings to delete */
	private Set<Building> listBuildingsRecycle;

	// ===========================================================
	// Constructors
	// ===========================================================

	private static Fight_BuildingManager buildingManager;

	/** Creation et initialisation du manager */
	public static void initManager(Team teamPlayer, Team teamIA) {
		if (buildingManager != null) throw new RuntimeException("buildingManager already created ! buildingManager is not null");
		buildingManager = new Fight_BuildingManager(teamPlayer, teamIA);
	}

	/** Disable object's instantiation (private constructor) */
	private Fight_BuildingManager(Team teamPlayer, Team teamIA){
		this.teamPlayer = teamPlayer;
		this.teamIA = teamIA;
		this.listSpawnBuildings = new ArrayList<SpawnBuilding>();
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



	/** Team **/
	private Team teamPlayer;
	private Team teamIA;


	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public static Fight_BuildingManager getManager(){
		return buildingManager;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/** Creation des buildings en debut de partie */
	public void initBuildingAtStart(){

		for(BuildingMapPattern buildingLevelModel: PatternLoader.getMapsPattern().get("MAP_1").getTeamMapPattern().get(TeamID.TEAM1.name()).getBuildingsList()){
			BuildingPattern buildingPattern = PatternLoader.getBuildingsPattern().get(buildingLevelModel.getBuildingName().name());
			Building building = BuildingFactory.create(buildingLevelModel.getGridPositionX(), buildingLevelModel.getGridPositionY(), buildingPattern, FightScreen.teamPlayer);
			this.addBuilding(building);
			}
		for(BuildingMapPattern buildingLevelModel: PatternLoader.getMapsPattern().get("MAP_1").getTeamMapPattern().get(TeamID.TEAM2.name()).getBuildingsList()){
			BuildingPattern buildingPattern = PatternLoader.getBuildingsPattern().get(buildingLevelModel.getBuildingName().name());
			Building building = BuildingFactory.create(buildingLevelModel.getGridPositionX(), buildingLevelModel.getGridPositionY(), buildingPattern, FightScreen.teamIA);
			this.addBuilding(building);
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
			else if(building instanceof Barricade)
				PoolManager.getManager().getBarricadePool().free((Barricade)building);
			else if(building instanceof BaseBuilding)
				PoolManager.getManager().getCampBuildingPool().free((BaseBuilding)building);

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

	/************************************/
	/** Spawn Building undestructibles **/

	/** Enregistre le building dans le manager
	 * mais pas au niveau des military list de la team car peut pas etre une target... */
	public void addSpawnBuilding(SpawnBuilding newBuilding){
		listSpawnBuildings.add(newBuilding);
	}

	public List<SpawnBuilding> getListSpawnBuildings() {
		return listSpawnBuildings;
	}

	/** Enregistre les demandes création de creep */
	public void askForCreateSpawnBuilding(BuildingName buildingName, Team team){

		BuildingPattern buildingPattern = PatternLoader.getBuildingsPattern().get(buildingName.name());
		if(team.hasEnoughtMoney(buildingPattern.getPrice())){
			team.subMoney(buildingPattern.getPrice());
			team.getListAskForCreateSpawnBuilding().add(buildingName);
		}
	}

	/** Excecute les demandes de création de creep */
	public void excuteAskForCreateSpawnBuilding(){
		for (BuildingName buildingName : teamPlayer.getListAskForCreateSpawnBuilding()){
			this.createSpawnBuilding(teamPlayer, 10, 10, buildingName);
		}
		for (BuildingName buildingName : teamIA.getListAskForCreateSpawnBuilding()){
			this.createSpawnBuilding(teamIA, 10, 10, buildingName);
		}
		teamPlayer.getListAskForCreateSpawnBuilding().clear();
		teamIA.getListAskForCreateSpawnBuilding().clear();
	}

	
	/** Creation d'un Spawn Building **/
	public void createSpawnBuilding(Team team, float posX, float posY, BuildingName buildingName) {

			BuildingPattern buildingPattern = PatternLoader.getBuildingsPattern().get(buildingName.name());
			SpawnBuilding swpanBuilding = BuildingFactory.createSpawnBuilding(posX, posY, buildingPattern, team);
			addSpawnBuilding(swpanBuilding);
	}

}
