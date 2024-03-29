package com.geekmecrazy.madandarmed.Game.Factory;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team;
import com.geekmecrazy.madandarmed.IA.AttackBehavior;
import com.geekmecrazy.madandarmed.IA.GroundMoveBehavior;
import com.geekmecrazy.madandarmed.IA.GroundPathFinding;
import com.geekmecrazy.madandarmed.Loader.PatternManager;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Renderer.CreepRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;


public class CreepFactory {

	//TODO AMA modifier gestion des team sur les military

	// disable object's instanciation (private constructor)
	private CreepFactory(){} 


	public static Creep createCreep (CreepPattern creepPattern, GamePlay_Team team, float spwanPosX, float spwanPosY) {

		// LIFE
		Life life = GlobalManager.poolManager.getLifePool().obtain();
		life.init(creepPattern.getLife());

		// ANIM
		CreepRenderer creepRenderer = GlobalManager.poolAnimManager.getCreepRendererPool().obtain();
		
		Creep creep = GlobalManager.poolManager.getCreepPool().obtain();
		creep.init(creepPattern, spwanPosX+randowSpawn(), spwanPosY+randowSpawn(), creepPattern.getDiameter(), life, team, creepRenderer);


		/***********************************************/

		//if(creepPattern.getUnitType()==UnitType.SOL){
			GroundPathFinding groundPathFinding = GlobalManager.poolManager.getGroundPathFinding().obtain();

			groundPathFinding.init(creep, 3, 3, 20, 14);//(5, 5, 20, 20); zone A , zone B
			GroundMoveBehavior groundBehavior = GlobalManager.poolManager.getGroundBehaviorPool().obtain();
			groundBehavior.setPathFinding(groundPathFinding);
			creep.setBehavior(groundBehavior);
/*
		}else if(creepPattern.getUnitType()==UnitType.AIR){
			AirPathFinding airPathFinding = PoolManager.getManager().getAirPathFindingPool().obtain();
			AirMoveBehavior airBehavior = PoolManager.getManager().getAirBehaviorPool().obtain();
			airBehavior.setPathFinding(airPathFinding);
			creep.setBehavior(airBehavior);

		}
		*/

		// ATTAQUE
		if(creepPattern.getWeaponName()!=null){

			AttackBehavior attackBehavior = GlobalManager.poolManager.getAttackBehaviorPool().obtain();
			attackBehavior.init(PatternManager.getWeaponsPattern().get(creepPattern.getWeaponName().name()));
			creep.setAttackBehavior(attackBehavior);
			Attaque attaque = GlobalManager.poolManager.getAttaquePool().obtain();
			attackBehavior.setAttaque(attaque);
	
			creep.getAttackBehavior().setMainTarget(ScreenManager.fightScreen.getOtherTeam(team).getCastle());
			creep.getAttackBehavior().setCurrentTarget(ScreenManager.fightScreen.getOtherTeam(team).getCastle());
		}


		return creep;
	}

	public static void destroy(Creep creep) {
		GlobalManager.fight_CreepManager.removeCreep(creep);
	}

	//permet de ne pas spawner pile poil l un sur l autre
	private static float randowSpawn() {

		float spawnDiameterMax = 80;
		return (float) (Math.random()*spawnDiameterMax) - spawnDiameterMax/2f;

	}


}