package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepID;


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
	
	private final long BIG_TURN=3;
	private long big_spawn;


    /** Mise a jour de l'état des missiles à chaque cycle */
    public void runUpdateNextState(){

        //DEBUG
        if(CreepManager.getManager().getCreepsNumber()<200) {

            if (nbTurnAfterPreviousSpawn >= TURN) {
                if (big_spawn >= BIG_TURN) {
                    CreepManager.getManager().askForCreateCreep(CreepID.MESH_HD_TEAM2, FightScreen.getManager().getTeamIA());
                    big_spawn = 0;
                } else {
                    big_spawn++;
                }

                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());
//                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());
//                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());
//                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());
//                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());
//                CreepManager.getManager().askForCreateCreep(CreepID.GLADIATOR_HD_TEAM2, FightScreen.getManager().getTeamIA());


                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());
                CreepManager.getManager().askForCreateCreep(CreepID.MARINE_HD_TEAM2, FightScreen.getManager().getTeamIA());


                nbTurnAfterPreviousSpawn = 0;
            }
            nbTurnAfterPreviousSpawn++;

        }

    }
}
