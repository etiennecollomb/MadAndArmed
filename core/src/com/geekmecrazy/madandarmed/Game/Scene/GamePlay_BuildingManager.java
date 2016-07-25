package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.utils.Array;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Barricade;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.Fight_Team;
import com.geekmecrazy.madandarmed.Game.Element.CampBuilding;
import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Factory.BuildingFactory;
import com.geekmecrazy.madandarmed.Loader.PatternManager;
import com.geekmecrazy.madandarmed.Pattern.BuildingMapPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingName;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.geekmecrazy.madandarmed.Utils.SpawnOrderComparator;


public class GamePlay_BuildingManager {

	/** Spawn Building (spawn unit) : unreal building non destroyable **/
	private Array<SpawnBuilding> listSpawnBuildings;

	/** real building destroyable **/
	private Array<Building> listBuildings;

	/** list of buildings to delete */
	private Set<Building> listBuildingsRecycle;

	/** Team **/
	private GamePlay_Team teamPlayer;
	private GamePlay_Team teamIA;


	// ===========================================================
	// Constructors
	// ===========================================================

	/** Disable object's instantiation (private constructor) */
	public GamePlay_BuildingManager(){
		this.teamPlayer = null;
		this.teamIA = null;
		this.listSpawnBuildings = new Array<SpawnBuilding>();
		this.listBuildings = new Array<Building>();
		this.listBuildingsRecycle = new HashSet<Building>();
	}

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
	public void init(GamePlay_Team teamPlayer, GamePlay_Team teamIA){

		this.teamPlayer = teamPlayer;
		this.teamIA = teamIA;

		if(this.teamPlayer!=null)
			for(BuildingMapPattern buildingLevelModel: PatternManager.getMapsPattern().get("MAP_1").getTeamMapPattern().get(TeamID.TEAM1.name()).getBuildingsList()){
				BuildingPattern buildingPattern = PatternManager.getBuildingsPattern().get(buildingLevelModel.getBuildingName().name());
				Building building = BuildingFactory.create(buildingLevelModel.getGridPositionX(), buildingLevelModel.getGridPositionY(), buildingPattern, this.teamPlayer);

				if(building instanceof SpawnBuilding)
					this.addSpawnBuilding((SpawnBuilding)building);
				else
					this.addBuilding(building);
			}

		if(this.teamIA!=null)
			for(BuildingMapPattern buildingLevelModel: PatternManager.getMapsPattern().get("MAP_1").getTeamMapPattern().get(TeamID.TEAM2.name()).getBuildingsList()){
				BuildingPattern buildingPattern = PatternManager.getBuildingsPattern().get(buildingLevelModel.getBuildingName().name());
				Building building = BuildingFactory.create(buildingLevelModel.getGridPositionX(), buildingLevelModel.getGridPositionY(), buildingPattern, this.teamIA);

				if(building instanceof SpawnBuilding)
					this.addSpawnBuilding((SpawnBuilding)building);
				else
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
				b.getAttackBehavior().setMainTarget(ScreenManager.fightScreen.getOtherTeam(b.getMyTeam()).getCastle());
				b.getAttackBehavior().setCurrentTarget(ScreenManager.fightScreen.getOtherTeam(b.getMyTeam()).getCastle());
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
			else if(building instanceof CampBuilding)
				GlobalManager.poolManager.getCampBuildingPool().free((CampBuilding)building);

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
	public void askForCreateSpawnBuilding(BuildingName buildingName, GamePlay_Team team){

		BuildingPattern buildingPattern = PatternManager.getBuildingsPattern().get(buildingName.name());

		/** TODO : isoler ... na  rien a faire dans class generic GamePlay **/
		if(ScreenManager.getCurrentScreen() instanceof FightScreen){
			if(((Fight_Team)team).hasEnoughtMoney(buildingPattern.getPrice())){
				((Fight_Team)team).subMoney(buildingPattern.getPrice());
				team.getListAskForCreateSpawnBuilding().add(buildingName);
			}
		}

		if(ScreenManager.getCurrentScreen() instanceof WarBaseScreen){
			team.getListAskForCreateSpawnBuilding().add(buildingName);
		}

	}

	/** Excecute les demandes de création de creep */
	public void excuteAskForCreateSpawnBuilding(){
		if(teamPlayer!=null){
			for (BuildingName buildingName : teamPlayer.getListAskForCreateSpawnBuilding()){
				this.createSpawnBuilding(teamPlayer, 10, 10, buildingName);
			}
			teamPlayer.getListAskForCreateSpawnBuilding().clear();
		}

		if(teamIA!=null){
			for (BuildingName buildingName : teamIA.getListAskForCreateSpawnBuilding()){
				this.createSpawnBuilding(teamIA, 10, 10, buildingName);
			}
			teamIA.getListAskForCreateSpawnBuilding().clear();
		}
	}


	/** Creation d'un Spawn Building **/
	public void createSpawnBuilding(GamePlay_Team team, float posX, float posY, BuildingName buildingName) {

		BuildingPattern buildingPattern = PatternManager.getBuildingsPattern().get(buildingName.name());
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

			if( this.listSpawnBuildings.get(i).getPattern().getSpawnOrder() == previousSpawnOrder ){

				/** if doublon, we shift all values **/
				for(int j=i; j<this.listSpawnBuildings.size; j++ ){
					this.listSpawnBuildings.get(j).getPattern().setSpawnOrder( this.listSpawnBuildings.get(j).getPattern().getSpawnOrder() +1 );
				}

				previousSpawnOrder = this.listSpawnBuildings.get(i).getPattern().getSpawnOrder();
			}
		}
	}

	/** Remove empty space **/
	private void removeEmptySpace(){

		this.sortSpawBuilding(); /** Re-order **/

		int previousSpawnOrder = -1;
		for(int i=0; i<this.listSpawnBuildings.size ; i++){

			if( this.listSpawnBuildings.get(i).getPattern().getSpawnOrder() > previousSpawnOrder+1 ){
				this.listSpawnBuildings.get(i).getPattern().setSpawnOrder(previousSpawnOrder+1);
			}

			previousSpawnOrder = this.listSpawnBuildings.get(i).getPattern().getSpawnOrder();

		}
	}


}
