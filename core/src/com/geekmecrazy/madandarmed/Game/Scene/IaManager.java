package com.geekmecrazy.madandarmed.Game.Scene;


/** Doit gerer le placement des buildings en fonction des pattern d'ordre de placement defini par le user **/
public class IaManager {
	
	// ===========================================================
	// Singleton manager
	// ===========================================================
	private static IaManager iaManager;
	
	/** Creation et initialisation du manager */
	public static void initManager() {
		if (iaManager != null) throw new RuntimeException("IaManager already created ! IaManager is not null");
		iaManager = new IaManager();
	}

	/** Disable object's instantiation (private constructor) */
	private IaManager(){
	}
	
	/** Acces au manager */
	public static IaManager getManager(){
		if (iaManager == null) throw new RuntimeException("IaManager not created ! IaManager is null");
		return iaManager;
	}

	/** Destruction du manager */
	public static void destroyManager(){
		if (iaManager == null) throw new RuntimeException("IaManager not created ! IaManager is null");
		iaManager.destroy();
	}
	
	/** Destruction */
	public void destroy(){
		iaManager=null;
	}
	
	// ===========================================================
	// Manager
	// ===========================================================
	private final long TURN=100; //100
	private long nbTurnAfterPreviousSpawn;
	
	private final long MESH_TURN=12;
	private final long BULLHOUND_TURN=6;
	private long big_spawn;


    /** Mise a jour de l'état des missiles à chaque cycle */
    public void runUpdateNextState(){

        //DEBUG
        if(CreepManager.getManager().getCreepsNumber()<200) {

            if (nbTurnAfterPreviousSpawn >= TURN) {
                if (big_spawn == MESH_TURN) {
                    //CreepManager.getManager().askForCreateCreep(CreepType.MESH, FightScreen.getManager().getTeamIA());
                    big_spawn = 0;
                }
                else if (big_spawn == BULLHOUND_TURN) {
                	//CreepManager.getManager().askForCreateCreep(CreepType.BULLHOUND, FightScreen.getManager().getTeamIA());
                }

                big_spawn++;

                //CreepManager.getManager().askForCreateCreep(CreepType.GLADIATOR, FightScreen.getManager().getTeamIA());
                //CreepManager.getManager().askForCreateCreep(CreepType.GLADIATOR, FightScreen.getManager().getTeamIA());


                //CreepManager.getManager().askForCreateCreep(CreepType.MARINE, FightScreen.getManager().getTeamIA());


                nbTurnAfterPreviousSpawn = 0;
            }
            nbTurnAfterPreviousSpawn++;

        }

    }
}
