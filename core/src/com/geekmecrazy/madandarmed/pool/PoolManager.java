package com.geekmecrazy.madandarmed.pool;

import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Game.Element.Missile;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.IA.AirMoveBehavior;
import com.geekmecrazy.madandarmed.IA.AirPathFinding;
import com.geekmecrazy.madandarmed.IA.AttackBehavior;
import com.geekmecrazy.madandarmed.IA.GroundMoveBehavior;
import com.geekmecrazy.madandarmed.IA.GroundPathFinding;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.badlogic.gdx.utils.Pool;

public class PoolManager {

	private static final int STARTING_ALLOCATE_ATTAQUE 				= 20;
	private static final int STARTING_ALLOCATE_CREEP 				= 20;
	private static final int STARTING_ALLOCATE_WEAPON 				= 20;
	private static final int STARTING_ALLOCATE_MISSILE 				= 20;
	private static final int STARTING_ALLOCATE_LIFE 				= 20;
	private static final int STARTING_ALLOCATE_BUILDING 			= 10;
	private static final int STARTING_ALLOCATE_GROUNDPATHFINDING 	= 20;
	private static final int STARTING_ALLOCATE_AIRPATHFINDING 		= 20;
	private static final int STARTING_ALLOCATE_GROUNDBEHAVIOR 		= 20;
	private static final int STARTING_ALLOCATE_ATTACKBEHAVIOR 		= 20;
	private static final int STARTING_ALLOCATE_AIRBEHAVIOR 			= 20;
	
	/** Pools */
	private Pool<Attaque> attaquePool;
	private Pool<Creep> creepPool;
	private Pool<WeaponPattern> weaponPool;
	private Pool<Missile> missilePool;
	private Pool<Life> lifePool;
	private Pool<Turret> turretPool;
	private Pool<GroundPathFinding> groundPathFinding;
	private Pool<AirPathFinding> airPathFindingPool;
	private Pool<AttackBehavior> attackBehaviorPool;
	private Pool<GroundMoveBehavior> groundBehaviorPool;
	private Pool<AirMoveBehavior> airBehaviorPool;
	
	
	// ===========================================================
	// Singleton manager
	// ===========================================================
	private static PoolManager poolManager;

	/** Disable object's instantiation (private constructor) */
	private PoolManager(){
		init();
	}

	/** Acces au manager */
	public static PoolManager getManager(){
		if (poolManager == null)
			poolManager = new PoolManager();
		return poolManager;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public Pool<Attaque> getAttaquePool() {
		return attaquePool;
	}

	public Pool<Creep> getCreepPool() {
		return creepPool;
	}

	public Pool<WeaponPattern> getWeaponPool() {
		return weaponPool;
	}

	public Pool<Missile> getMissilePool() {
		return missilePool;
	}

	public Pool<Life> getLifePool() {
		return lifePool;
	}

	public Pool<Turret> getTurretPool() {
		return turretPool;
	}

	public Pool<GroundPathFinding> getGroundPathFinding() {
		return groundPathFinding;
	}

	public Pool<AirPathFinding> getAirPathFindingPool() {
		return airPathFindingPool;
	}

	public Pool<GroundMoveBehavior> getGroundBehaviorPool() {
		return groundBehaviorPool;
	}

	public Pool<AttackBehavior> getAttackBehaviorPool() {
		return attackBehaviorPool;
	}

	public Pool<AirMoveBehavior> getAirBehaviorPool() {
		return airBehaviorPool;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**  Starting PoolManager */
	public void init () {
		
		attaquePool = new Pool<Attaque>(STARTING_ALLOCATE_ATTAQUE){
			@Override
			protected Attaque newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + Attaque.class.getName());
				return new Attaque();
			}
		};
		
		creepPool = new Pool<Creep>(STARTING_ALLOCATE_CREEP){
			@Override
			protected Creep newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + Creep.class.getName());
                return new Creep();
			 }
		};
		
		weaponPool = new Pool<WeaponPattern>(STARTING_ALLOCATE_WEAPON){
			@Override
			protected WeaponPattern newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + WeaponPattern.class.getName());
                return new WeaponPattern();
			 }
		};
		
		missilePool = new Pool<Missile>(STARTING_ALLOCATE_MISSILE){
			@Override
			protected Missile newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + Missile.class.getName());
				return new Missile();
			 }
		};
		
		lifePool = new Pool<Life>(STARTING_ALLOCATE_LIFE){
			@Override
			protected Life newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + Life.class.getName());
				return new Life();
			 }
		};
		
		groundPathFinding = new Pool<GroundPathFinding>(STARTING_ALLOCATE_GROUNDPATHFINDING){
			@Override
			protected GroundPathFinding newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + GroundPathFinding.class.getName());
				return new GroundPathFinding();
			 }
		};
		
		airPathFindingPool = new Pool<AirPathFinding>(STARTING_ALLOCATE_AIRPATHFINDING){
			@Override
			protected AirPathFinding newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + AirPathFinding.class.getName());
				return new AirPathFinding();
			 }
		};
		
		attackBehaviorPool = new Pool<AttackBehavior>(STARTING_ALLOCATE_ATTACKBEHAVIOR){
			@Override
			protected AttackBehavior newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + AttackBehavior.class.getName());
				return new AttackBehavior();
			 }
		};
		
		groundBehaviorPool = new Pool<GroundMoveBehavior>(STARTING_ALLOCATE_GROUNDBEHAVIOR){
			@Override
			protected GroundMoveBehavior newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + GroundMoveBehavior.class.getName());
				return new GroundMoveBehavior();
			 }
		};
		
		airBehaviorPool = new Pool<AirMoveBehavior>(STARTING_ALLOCATE_AIRBEHAVIOR){
			@Override
			protected AirMoveBehavior newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + AirMoveBehavior.class.getName());
				return new AirMoveBehavior();
			 }
		};

		turretPool = new Pool<Turret>(STARTING_ALLOCATE_BUILDING){
			@Override
			protected Turret newObject() {
                System.out.println("#__ POOL __# allocate new Object : " + Turret.class.getName());
				return new Turret();
			 }
		};

		
		
	}




}
