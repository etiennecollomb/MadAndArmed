package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;

/** Doit gerer le placement des buildings en fonction des pattern d'ordre de placement defini par le user **/
public class Fight_IaManager {

	private final long TURN=100; //100
	private long nbTurnAfterPreviousSpawn;
	
	private final long MESH_TURN=12;
	private final long BULLHOUND_TURN=6;
	private long big_spawn;


    /** Mise a jour de l'état des missiles à chaque cycle */
    public void runUpdateNextState(){

        //DEBUG
        if(GlobalManager.fight_CreepManager.getCreepsNumber()<200) {

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
