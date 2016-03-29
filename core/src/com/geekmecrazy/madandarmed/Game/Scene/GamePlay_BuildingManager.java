package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.utils.Array;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Barricade;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.BaseBuilding;
import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.Game.Element.Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Factory.BuildingFactory;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingMapPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingName;
import com.geekmecrazy.madandarmed.Utils.SpawnOrderComparator;


public class GamePlay_BuildingManager {

	/** Spawn Building (spawn unit) : unreal building non destroyable **/
	private Array<SpawnBuilding> listSpawnBuildings;

	/** real building destroyable **/
	private Array<Building> listBuildings;

	/** list of buildings to delete */
	private Set<Building> listBuildingsRecycle;

	// ===========================================================
	// Constructors
	// ===========================================================

	/** Disable object's instantiation (private constructor) */
	public GamePlay_BuildingManager(Team teamPlayer, Team teamIA){
		this.teamPlayer = teamPlayer;
		this.teamIA = teamIA;
		this.listSpawnBuildings = new Array<SpawnBuilding>();
		this.listBuildings = new Array<Building>();
		this.listBuildingsRecycle = new HashSet<Building>();
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

	// ===========================================================
	// Methods
	// ===========================================================

	/** Creation des buildings en debut de partie */
	public void initBuildingAtStart(GamePlayScreen gamePlayScreen){

		for(BuildingMapPattern buildingLevelModel: PatternLoader.getMapsPattern().get("MAP_1").getTeamMapPattern().get(TeamID.TEAM1.name()).getBuildingsList()){
			BuildingPattern buildingPattern = PatternLoader.getBuildingsPattern().get(buildingLevelModel.getBuildingName().name());
			Building building = BuildingFactory.create(buildingLevelModel.getGridPositionX(), buildingLevelModel.getGridPositionY(), buildingPattern, gamePlayScreen.teamPlayer);
			this.addBuilding(building);
			}
		for(BuildingMapPattern buildingLevelModel: PatternLoader.getMapsPattern().get("MAP_1").getTeamMapPattern().get(TeamID.TEAM2.name()).getBuildingsList()){
			BuildingPattern buildingPattern = PatternLoader.getBuildingsPattern().get(buildingLevelModel.getBuildingName().name());
			Building building = BuildingFactory.create(buildingLevelModel.getGridPositionX(), buildingLevelModel.getGridPositionY(), buildingPattern, gamePlayScreen.teamIA);
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
				b.getAttackBehavior().setMainTarget(GlobalManager.fightScreen.getOtherTeam(b.getMyTeam()).getCastle());
				b.getAttackBehavior().setCurrentTarget(GlobalManager.fightScreen.getOtherTeam(b.getMyTeam()).getCastle());
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
			listBuildings.removeValue(building, true);

			if(building instanceof Turret)
				GlobalManager.poolManager.getTurretPool().free((Turret)building);
			else if(building instanceof Barricade)
				GlobalManager.poolManager.getBarricadePool().free((Barricade)building);
			else if(building instanceof BaseBuilding)
				GlobalManager.poolManager.getCampBuildingPool().free((BaseBuilding)building);

		}
		listBuildingsRecycle.clear();

	}

	/** Mise a jour de l'état des buildings à chaque cycle */
	public void runUpdateNextState(){	
		for(Building building : listBuildings) building.onUpdateNextState();
	}


	public Array<Building> getListBuildings() {
		return listBuildings;
	}

	/************************************/
	/** Spawn Building undestructibles **/

	/** Enregistre le building dans le manager
	 * mais pas au niveau des military list de la team car peut pas etre une target... */
	public void addSpawnBuilding(SpawnBuilding newBuilding){
		listSpawnBuildings.add(newBuilding);
	}

	public Array<SpawnBuilding> getListSpawnBuildings() {
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
	
	
	
	
	
	

	/** Spawn Building Order **/
	private void sortSpawBuilding() {
		this.listSpawnBuildings.sort(SpawnOrderComparator.getInstance());
	}

	/** Remove doublon **/
	private void removeDoublon(){

		this.sortSpawBuilding(); /** Re-order **/

		int previousSpawnOrder = -1;
		for(int i=0; i<this.listSpawnBuildings.size ; i++){

			if( this.listSpawnBuildings.get(i).getSpawnOrder() == previousSpawnOrder ){

				/** if doublon, we shift all values **/
				for(int j=i; j<this.listSpawnBuildings.size; j++ ){
					this.listSpawnBuildings.get(j).setSpawnOrder( this.listSpawnBuildings.get(j).getSpawnOrder() +1 );
				}

				previousSpawnOrder = this.listSpawnBuildings.get(i).getSpawnOrder();
			}
		}
	}

	/** Remove empty space **/
	private void removeEmptySpace(){

		this.sortSpawBuilding(); /** Re-order **/

		int previousSpawnOrder = -1;
		for(int i=0; i<this.listSpawnBuildings.size ; i++){

			if( this.listSpawnBuildings.get(i).getSpawnOrder() > previousSpawnOrder+1 ){
				this.listSpawnBuildings.get(i).setSpawnOrder(previousSpawnOrder+1);
			}

			previousSpawnOrder = this.listSpawnBuildings.get(i).getSpawnOrder();

		}
	}
	

}
