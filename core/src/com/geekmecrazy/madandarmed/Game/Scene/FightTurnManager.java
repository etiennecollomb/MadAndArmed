package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.List;

import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;
import com.geekmecrazy.madandarmed.Utils.TimeIntervalControl;

/** cree les units en fonction des spawnbuilding present sur le terrain **/
public class FightTurnManager {

	TimeIntervalControl spawnTurnTimer = new TimeIntervalControl(2000);

	// ===========================================================
	// Singleton manager
	// ===========================================================
	private static FightTurnManager turnManager;

	/** Creation et initialisation du manager */
	public static void initManager() {
		if (turnManager != null) throw new RuntimeException("TurnManager already created ! TurnManager is not null");
		turnManager = new FightTurnManager();
	}

	/** Disable object's instantiation (private constructor) */
	private FightTurnManager(){
	}

	/** Acces au manager */
	public static FightTurnManager getManager(){
		if (turnManager == null) throw new RuntimeException("TurnManager not created ! TurnManager is null");
		return turnManager;
	}

	/** Destruction du manager */
	public static void destroyManager(){
		if (turnManager == null) throw new RuntimeException("TurnManager not created ! TurnManager is null");
		turnManager.destroy();
	}

	/** Destruction */
	public void destroy(){
		turnManager=null;
	}

	// ===========================================================
	// Manager
	// ===========================================================


	/** Mise a jour de l'état des missiles à chaque cycle */
	public void runUpdateNextState(){

		if(!spawnTurnTimer.isInsideInterval()){
			System.out.println("______START");
			spawnTurnTimer.resetTimer();

			/** get spawn list from Building Manager SpawnBuilding **/
			List<SpawnBuilding> spawnBuildingList = FightBuildingManager.getManager().getListSpawnBuildings();
			int size = spawnBuildingList.size();
			for(int i=0; i<size; i++){
				SpawnBuilding spbld = spawnBuildingList.get(i);
				CreepType creepType = spbld.getPattern().getCreepType();

				FightCreepManager.getManager().askForCreateCreep(creepType, spbld.getMyTeam(), spbld.getMilitaryRenderer().getX(), spbld.getMilitaryRenderer().getY());
			}
		}else{
			System.out.println("PAUSE");
		}


	}
}
