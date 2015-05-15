package com.geekmecrazy.madandarmed.Game.Factory;

import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Scene.CreepManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.IA.AirMoveBehavior;
import com.geekmecrazy.madandarmed.IA.AirPathFinding;
import com.geekmecrazy.madandarmed.IA.AttackBehavior;
import com.geekmecrazy.madandarmed.IA.GroundMoveBehavior;
import com.geekmecrazy.madandarmed.IA.GroundPathFinding;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.UnitType;
import com.geekmecrazy.madandarmed.Renderer.CreepRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class CreepFactory {

	//TODO AMA modifier gestion des team sur les military

	// disable object's instanciation (private constructor)
	private CreepFactory(){} 


	public static Creep createCreep (CreepPattern creepPattern, Team team) {

		// LIFE
		Life life = PoolManager.getManager().getLifePool().obtain();
		life.init(creepPattern.getLife());

		// ANIM
		CreepRenderer creepRenderer = PoolAnimManager.getManager().getCreepRendererPool().obtain();
		
		int diameter = 20; //A mettre dans XML ! NOTE : 10 fonctionne bien mais plus ca pete  oO
		Creep creep = PoolManager.getManager().getCreepPool().obtain();
		creep.init(creepPattern, team.getSpawnPoint().getX()+randowSpawn(), team.getSpawnPoint().getY()+randowSpawn(), diameter, life, team, FightScreen.getManager().getOtherTeam(team), creepRenderer);


		/***********************************************/

		if(creepPattern.getUnitType()==UnitType.SOL){
			GroundPathFinding groundPathFinding = PoolManager.getManager().getGroundPathFinding().obtain();

			groundPathFinding.init(creep, 3, 3, 20, 14);//(5, 5, 20, 20); zone A , zone B
			GroundMoveBehavior groundBehavior = PoolManager.getManager().getGroundBehaviorPool().obtain();
			groundBehavior.setPathFinding(groundPathFinding);
			creep.setBehavior(groundBehavior);

		}else if(creepPattern.getUnitType()==UnitType.AIR){
			AirPathFinding airPathFinding = PoolManager.getManager().getAirPathFindingPool().obtain();
			AirMoveBehavior airBehavior = PoolManager.getManager().getAirBehaviorPool().obtain();
			airBehavior.setPathFinding(airPathFinding);
			creep.setBehavior(airBehavior);

		}

		// ATTAQUE
		AttackBehavior attackBehavior = PoolManager.getManager().getAttackBehaviorPool().obtain();
		attackBehavior.init(creepPattern.getWeaponPattern());
		creep.setAttackBehavior(attackBehavior);
		Attaque attaque = PoolManager.getManager().getAttaquePool().obtain();
		attackBehavior.setAttaque(attaque);

		creep.getAttackBehavior().setMainTarget(FightScreen.getManager().getOtherTeam(team).getCastle());
		creep.getAttackBehavior().setCurrentTarget(FightScreen.getManager().getOtherTeam(team).getCastle());


		return creep;
	}

	public static void destroy(Creep creep) {
		CreepManager.getManager().removeCreep(creep);
	}

	//permet de pas spawner pile poil l un sur l autre
	private static float randowSpawn() {

		float spawnDiameterMax = 20;
		return (float) (Math.random()*spawnDiameterMax);

	}

	//	@Override
	//	public void updateDestroy(Destructible destructible) {
	//		if(destructible instanceof Creep)destroy((Creep)destructible);
	//		
	//	}

}