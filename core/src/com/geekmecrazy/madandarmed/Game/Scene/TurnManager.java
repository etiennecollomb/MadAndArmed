package com.geekmecrazy.madandarmed.Game.Scene;


/** cree les units en fonction des spawnbuilding present sur le terrain **/
public class TurnManager {
	
	// ===========================================================
	// Singleton manager
	// ===========================================================
	private static TurnManager turnManager;
	
	/** Creation et initialisation du manager */
	public static void initManager() {
		if (turnManager != null) throw new RuntimeException("TurnManager already created ! TurnManager is not null");
		turnManager = new TurnManager();
	}

	/** Disable object's instantiation (private constructor) */
	private TurnManager(){
	}
	
	/** Acces au manager */
	public static TurnManager getManager(){
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

    	/** get spawn list from Building Manager SpawnBuilding **/
    	//CreepManager.getManager().askForCreateCreep(CreepType.MESH, FightScreen.getManager().getTeamIA());

    }
}
