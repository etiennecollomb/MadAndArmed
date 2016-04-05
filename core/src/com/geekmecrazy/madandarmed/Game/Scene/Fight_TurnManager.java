package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.List;

import com.badlogic.gdx.utils.Array;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Fight_Team;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;
import com.geekmecrazy.madandarmed.Utils.TimeIntervalControl;

/** cree les units en fonction des spawnbuilding present sur le terrain **/
public class Fight_TurnManager {

	TimeIntervalControl spawnTurnTimer = new TimeIntervalControl(2000);

	
	/** Mise a jour de l'état des missiles à chaque cycle */
	public void runUpdateNextState(){

		if(!spawnTurnTimer.isInsideInterval()){
			System.out.println("______START");
			spawnTurnTimer.resetTimer();

			/** get spawn list from Building Manager SpawnBuilding **/
			Array<SpawnBuilding> spawnBuildingList = GlobalManager.gamePlay_BuildingManager.getListSpawnBuildings();
			for(int i=0; i<spawnBuildingList.size; i++){
				SpawnBuilding spbld = spawnBuildingList.get(i);
				CreepType creepType = spbld.getPattern().getCreepType();

				GlobalManager.fight_CreepManager.askForCreateCreep(creepType, (Fight_Team) spbld.getMyTeam(), spbld.getMilitaryRenderer().getX(), spbld.getMilitaryRenderer().getY());
			}
		}else{
			System.out.println("PAUSE");
		}


	}
}
