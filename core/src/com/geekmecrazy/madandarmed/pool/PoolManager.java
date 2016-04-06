package com.geekmecrazy.madandarmed.pool;

import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.Barricade;
import com.geekmecrazy.madandarmed.Game.Element.CampBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Game.Element.FlameThrower;
import com.geekmecrazy.madandarmed.Game.Element.Gun;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Game.Element.MeshMultiExplosion;
import com.geekmecrazy.madandarmed.Game.Element.Missile;
import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Sword;
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
	private static final int STARTING_ALLOCATE_SWORD				= 20;
	private static final int STARTING_ALLOCATE_GUN					= 20;
	private static final int STARTING_ALLOCATE_FLAMETHROWER			= 20;
	private static final int STARTING_ALLOCATE_MESHMULTIEXPLOSION	= 20;
	private static final int STARTING_ALLOCATE_MISSILE 				= 20;
	private static final int STARTING_ALLOCATE_LIFE 				= 20;
	private static final int STARTING_ALLOCATE_TURRET 				= 10;
	private static final int STARTING_ALLOCATE_CAMPBUILDING			= 10;
	private static final int STARTING_ALLOCATE_SPAWNBUILDING		= 10;
	private static final int STARTING_ALLOCATE_BARRICADE			= 10;
	private static final int STARTING_ALLOCATE_GROUNDPATHFINDING 	= 20;
	private static final int STARTING_ALLOCATE_AIRPATHFINDING 		= 20;
	private static final int STARTING_ALLOCATE_GROUNDBEHAVIOR 		= 20;
	private static final int STARTING_ALLOCATE_ATTACKBEHAVIOR 		= 20;
	private static final int STARTING_ALLOCATE_AIRBEHAVIOR 			= 20;
	
	/** Pools */
	private static Pool<Attaque> attaquePool;
	private static Pool<Creep> creepPool;
	private static Pool<Sword> swordPool;
	private static Pool<Gun> gunPool;
	private static Pool<FlameThrower> flameThrowerPool;
	private static Pool<MeshMultiExplosion> meshMultiExplosionPool;
	private static Pool<Missile> missilePool;
	private static Pool<Life> lifePool;
	private static Pool<Turret> turretPool;
	private static Pool<CampBuilding> campBuildingPool;
	private static Pool<SpawnBuilding> spawnBuildingPool;
	private static Pool<Barricade> barricadePool;
	private static Pool<GroundPathFinding> groundPathFinding;
	private static Pool<AirPathFinding> airPathFindingPool;
	private static Pool<AttackBehavior> attackBehaviorPool;
	private static Pool<GroundMoveBehavior> groundBehaviorPool;
	private static Pool<AirMoveBehavior> airBehaviorPool;
	

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public PoolManager(){
		init();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public static Pool<Attaque> getAttaquePool() {
		return attaquePool;
	}

	public static Pool<Creep> getCreepPool() {
		return creepPool;
	}

	public static Pool<Sword> getSwordPool() {
		return swordPool;
	}
	
	public static Pool<Gun> getGunPool() {
		return gunPool;
	}
	
	public static Pool<FlameThrower> getFlameThrowerPool() {
		return flameThrowerPool;
	}

	public static Pool<MeshMultiExplosion> getMeshMultiExplosionPool() {
		return meshMultiExplosionPool;
	}

	public static Pool<Missile> getMissilePool() {
		return missilePool;
	}

	public static Pool<Life> getLifePool() {
		return lifePool;
	}

	public static Pool<Turret> getTurretPool() {
		return turretPool;
	}

	public static Pool<CampBuilding> getCampBuildingPool() {
		return campBuildingPool;
	}
	
	public static Pool<Barricade> getBarricadePool() {
		return barricadePool;
	}

	public static Pool<GroundPathFinding> getGroundPathFinding() {
		return groundPathFinding;
	}

	public static Pool<AirPathFinding> getAirPathFindingPool() {
		return airPathFindingPool;
	}

	public static Pool<GroundMoveBehavior> getGroundBehaviorPool() {
		return groundBehaviorPool;
	}

	public static Pool<AttackBehavior> getAttackBehaviorPool() {
		return attackBehaviorPool;
	}

	public static Pool<AirMoveBehavior> getAirBehaviorPool() {
		return airBehaviorPool;
	}
	
	public static Pool<SpawnBuilding> getSpawnBuildingPool() {
		return spawnBuildingPool;
	}

	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**  Starting PoolManager */
	public static void init () {
		
		attaquePool = new Pool<Attaque>(STARTING_ALLOCATE_ATTAQUE){
			@Override
			protected Attaque newObject() {
                //System.out.println("#__ POOL __# allocate new Object : " + Attaque.class.getName());
				return new Attaque();
			}
		};
		
		creepPool = new Pool<Creep>(STARTING_ALLOCATE_CREEP){
			@Override
			protected Creep newObject() {
                //System.out.println("#__ POOL __# allocate new Object : " + Creep.class.getName());
                return new Creep();
			 }
		};
		
		swordPool = new Pool<Sword>(STARTING_ALLOCATE_SWORD){
			@Override
			protected Sword newObject() {
                //System.out.println("#__ POOL __# allocate new Object : " + Sword.class.getName());
                return new Sword();
			 }
		};
		
		gunPool = new Pool<Gun>(STARTING_ALLOCATE_GUN){
			@Override
			protected Gun newObject() {
                //System.out.println("#__ POOL __# allocate new Object : " + Gun.class.getName());
                return new Gun();
			 }
		};
		
		flameThrowerPool = new Pool<FlameThrower>(STARTING_ALLOCATE_FLAMETHROWER){
			@Override
			protected FlameThrower newObject() {
                //System.out.println("#__ POOL __# allocate new Object : " + FlameThrower.class.getName());
				return new FlameThrower();
			 }
		};
		
		meshMultiExplosionPool = new Pool<MeshMultiExplosion>(STARTING_ALLOCATE_MESHMULTIEXPLOSION){
			@Override
			protected MeshMultiExplosion newObject() {
                //System.out.println("#__ POOL __# allocate new Object : " + MeshMultiExplosion.class.getName());
				return new MeshMultiExplosion();
			 }
		};
				
		missilePool = new Pool<Missile>(STARTING_ALLOCATE_MISSILE){
			@Override
			protected Missile newObject() {
                //System.out.println("#__ POOL __# allocate new Object : " + Missile.class.getName());
				return new Missile();
			 }
		};
		
		lifePool = new Pool<Life>(STARTING_ALLOCATE_LIFE){
			@Override
			protected Life newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + Life.class.getName());
				return new Life();
			 }
		};
		
		groundPathFinding = new Pool<GroundPathFinding>(STARTING_ALLOCATE_GROUNDPATHFINDING){
			@Override
			protected GroundPathFinding newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + GroundPathFinding.class.getName());
				return new GroundPathFinding();
			 }
		};
		
		airPathFindingPool = new Pool<AirPathFinding>(STARTING_ALLOCATE_AIRPATHFINDING){
			@Override
			protected AirPathFinding newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + AirPathFinding.class.getName());
				return new AirPathFinding();
			 }
		};
		
		attackBehaviorPool = new Pool<AttackBehavior>(STARTING_ALLOCATE_ATTACKBEHAVIOR){
			@Override
			protected AttackBehavior newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + AttackBehavior.class.getName());
				return new AttackBehavior();
			 }
		};
		
		groundBehaviorPool = new Pool<GroundMoveBehavior>(STARTING_ALLOCATE_GROUNDBEHAVIOR){
			@Override
			protected GroundMoveBehavior newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + GroundMoveBehavior.class.getName());
				return new GroundMoveBehavior();
			 }
		};
		
		airBehaviorPool = new Pool<AirMoveBehavior>(STARTING_ALLOCATE_AIRBEHAVIOR){
			@Override
			protected AirMoveBehavior newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + AirMoveBehavior.class.getName());
				return new AirMoveBehavior();
			 }
		};

		turretPool = new Pool<Turret>(STARTING_ALLOCATE_TURRET){
			@Override
			protected Turret newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + Turret.class.getName());
				return new Turret();
			 }
		};
		
		campBuildingPool = new Pool<CampBuilding>(STARTING_ALLOCATE_CAMPBUILDING){
			@Override
			protected CampBuilding newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + CampBuilding.class.getName());
				return new CampBuilding();
			 }
		};
		
		spawnBuildingPool = new Pool<SpawnBuilding>(STARTING_ALLOCATE_SPAWNBUILDING){
			@Override
			protected SpawnBuilding newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + SpawnBuilding.class.getName());
				return new SpawnBuilding();
			 }
		};

		barricadePool = new Pool<Barricade>(STARTING_ALLOCATE_BARRICADE){
			@Override
			protected Barricade newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + Barricade.class.getName());
				return new Barricade();
			 }
		};
		
	}




}
