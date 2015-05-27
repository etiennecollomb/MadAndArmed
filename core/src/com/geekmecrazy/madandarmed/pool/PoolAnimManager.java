package com.geekmecrazy.madandarmed.pool;

import java.util.HashMap;
import java.util.Map;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Renderer.BarricadeRenderer;
import com.geekmecrazy.madandarmed.Renderer.CreepRenderer;
import com.geekmecrazy.madandarmed.Renderer.LifeBarRenderer;
import com.geekmecrazy.madandarmed.Renderer.MissileRenderer;
import com.geekmecrazy.madandarmed.Renderer.TurretRenderer;
import com.geekmecrazy.madandarmed.Renderer.UniqueActionRenderer;
import com.badlogic.gdx.utils.Pool;


public class PoolAnimManager {

	/** Number of Preloaded item */
	private static final int ALLOCATE_UNIQUE_ACTION_RENDERER	= 100;
	private static final int ALLOCATE_CREEP_RENDERER			= 100;
	private static final int ALLOCATE_TURRET_RENDERER			= 100;
	private static final int ALLOCATE_BARRICADE_RENDERER		= 100;
	private static final int ALLOCATE_MISSILE_RENDERER			= 100;
	private static final int ALLOCATE_LIFEBAR_RENDERER			= 100;


	/** SpriteSheet */
	private static Map<AnimatedTextureType, SpriteSheet> spriteSheets;

	//HIT
	public static final SpriteSheet MISSILE_EXPLOSION_SPRITESHEET = new SpriteSheet(AnimatedTextureType.MISSILE_EXPLOSION, true);
	public static final SpriteSheet IMPACT_BULLET_SPRITESHEET = new SpriteSheet(AnimatedTextureType.IMPACT_BULLET, true);

	//DEAD
	public static final SpriteSheet DEAD_SPRITESHEET = new SpriteSheet(AnimatedTextureType.DEAD, true);
	public static final SpriteSheet BUILDING_DEATH_128PX_SPRITESHEET = new SpriteSheet(AnimatedTextureType.BUILDING_DEATH_128PX, true);
	public static final SpriteSheet BUILDING_DEATH_64PX_SPRITESHEET = new SpriteSheet(AnimatedTextureType.BUILDING_DEATH_64PX, true);
	public static final SpriteSheet BUILDING_DEATH_32PX_SPRITESHEET = new SpriteSheet(AnimatedTextureType.BUILDING_DEATH_32PX, true);

	//UNIT
	//---TEAM1
	public static final SpriteSheet GLADIATOR_HD_TEAM1_SPRITESHEET = new SpriteSheet(AnimatedTextureType.GLADIATOR_HD_TEAM1, false);
	public static final SpriteSheet MARINE_HD_TEAM1_SPRITESHEET = new SpriteSheet(AnimatedTextureType.MARINE_HD_TEAM1, false);
	public static final SpriteSheet MESH_HD_TEAM1_SPRITESHEET = new SpriteSheet(AnimatedTextureType.MESH_HD_TEAM1, false);
	//---TEAM2
	public static final SpriteSheet GLADIATOR_HD_TEAM2_SPRITESHEET = new SpriteSheet(AnimatedTextureType.GLADIATOR_HD_TEAM2, false);
	public static final SpriteSheet MARINE_HD_TEAM2_SPRITESHEET = new SpriteSheet(AnimatedTextureType.MARINE_HD_TEAM2, false);
	public static final SpriteSheet MESH_HD_TEAM2_SPRITESHEET = new SpriteSheet(AnimatedTextureType.MESH_HD_TEAM2, false);

	//BUILDING
	//---TEAM1
	public static final SpriteSheet TURRET_01_HD_TEAM1_SPRITESHEET = new SpriteSheet(AnimatedTextureType.TURRET_01_HD_TEAM1, false);
	public static final SpriteSheet TURRET_02_HD_TEAM1_SPRITESHEET = new SpriteSheet(AnimatedTextureType.TURRET_02_HD_TEAM1, false);
	//---TEAM2
	public static final SpriteSheet TURRET_01_HD_TEAM2_SPRITESHEET = new SpriteSheet(AnimatedTextureType.TURRET_01_HD_TEAM2, false);
	public static final SpriteSheet TURRET_02_HD_TEAM2_SPRITESHEET = new SpriteSheet(AnimatedTextureType.TURRET_02_HD_TEAM2, false);
	//---BARRICADE
	public static final SpriteSheet BARRICADE_SPRITESHEET = new SpriteSheet(AnimatedTextureType.BARRICADES, false);

	//MISSILE
	public static final SpriteSheet MISSILE_TYPE_1_SPRITESHEET = new SpriteSheet(AnimatedTextureType.MISSILE_TYPE_1, false);


	/** Pools */
	private static Pool<UniqueActionRenderer> uniqueActionRendererPool;
	private static Pool<CreepRenderer> creepRendererPool;
	private static Pool<TurretRenderer> turretRendererPool;
	private static Pool<BarricadeRenderer> barricadeRendererPool;
	private static Pool<MissileRenderer> missileRendererPool;
	private static Pool<LifeBarRenderer> lifeBarRendererPool;

	// ===========================================================
	// Singleton manager
	// ===========================================================
	private static PoolAnimManager poolAnimManager;

	/** Disable object's instantiation (private constructor) */
	private PoolAnimManager(){
		init();
	}

	/** Acces au manager */
	public static PoolAnimManager getManager(){
		if (poolAnimManager == null)
			poolAnimManager = new PoolAnimManager();
		return poolAnimManager;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Map<AnimatedTextureType, SpriteSheet> getSpriteSheets() {
		return spriteSheets;
	}

	public Pool<UniqueActionRenderer> getUniqueActionRendererPool() {
		return uniqueActionRendererPool;
	}

	public Pool<CreepRenderer> getCreepRendererPool() {
		return creepRendererPool;
	}

	public Pool<TurretRenderer> getTurretRendererPool() {
		return turretRendererPool;
	}

	public Pool<BarricadeRenderer> getBarricadeRendererPool() {
		return barricadeRendererPool;
	}

	public Pool<MissileRenderer> getMissileRendererPool() {
		return missileRendererPool;
	}

	public Pool<LifeBarRenderer> getLifeBarRendererPool() {
		return lifeBarRendererPool;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void init () {

		/** Sprites */
		spriteSheets = new HashMap<AnimatedTextureType, SpriteSheet>();
		//HIT
		spriteSheets.put(AnimatedTextureType.MISSILE_EXPLOSION, MISSILE_EXPLOSION_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.IMPACT_BULLET, IMPACT_BULLET_SPRITESHEET);
		//DEAD
		spriteSheets.put(AnimatedTextureType.DEAD, DEAD_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.BUILDING_DEATH_128PX, BUILDING_DEATH_128PX_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.BUILDING_DEATH_64PX, BUILDING_DEATH_64PX_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.BUILDING_DEATH_32PX, BUILDING_DEATH_32PX_SPRITESHEET);
		//UNIT
		//---TEAM1
		spriteSheets.put(AnimatedTextureType.GLADIATOR_HD_TEAM1, GLADIATOR_HD_TEAM1_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.MARINE_HD_TEAM1, MARINE_HD_TEAM1_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.MESH_HD_TEAM1, MESH_HD_TEAM1_SPRITESHEET);
		//---TEAM2
		spriteSheets.put(AnimatedTextureType.GLADIATOR_HD_TEAM2, GLADIATOR_HD_TEAM2_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.MARINE_HD_TEAM2, MARINE_HD_TEAM2_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.MESH_HD_TEAM2, MESH_HD_TEAM2_SPRITESHEET);
		//BUILDING
		//---TEAM1
		spriteSheets.put(AnimatedTextureType.TURRET_01_HD_TEAM1, TURRET_01_HD_TEAM1_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.TURRET_02_HD_TEAM1, TURRET_02_HD_TEAM1_SPRITESHEET);
		//---TEAM2
		spriteSheets.put(AnimatedTextureType.TURRET_01_HD_TEAM2, TURRET_01_HD_TEAM2_SPRITESHEET);
		spriteSheets.put(AnimatedTextureType.TURRET_02_HD_TEAM2, TURRET_02_HD_TEAM2_SPRITESHEET);
		//---BARRICADE
		spriteSheets.put(AnimatedTextureType.BARRICADES, BARRICADE_SPRITESHEET);
		//MISSILE
		spriteSheets.put(AnimatedTextureType.MISSILE_TYPE_1, MISSILE_TYPE_1_SPRITESHEET);


		/** Pools */
		uniqueActionRendererPool = createUniqueActionRendererPool(ALLOCATE_UNIQUE_ACTION_RENDERER);
		creepRendererPool = createCreepRendererPool(ALLOCATE_CREEP_RENDERER);
		turretRendererPool = createTurretRendererPool(ALLOCATE_TURRET_RENDERER);
		missileRendererPool = createMissileRendererPool(ALLOCATE_MISSILE_RENDERER);
		lifeBarRendererPool = createLifeBarRendererPool(ALLOCATE_LIFEBAR_RENDERER);

	}

	// CREATE
	//===================

	//UNIQUE ACTION
	private Pool<UniqueActionRenderer> createUniqueActionRendererPool(int initPoolNumber){

		Pool<UniqueActionRenderer> pool = new Pool<UniqueActionRenderer>(initPoolNumber) {

			@Override
			protected UniqueActionRenderer newObject() {
				System.out.println("#__ POOL __# allocate new Object : " + UniqueActionRenderer.class.getName());
				return new UniqueActionRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//CREEP RENDERER
	private Pool<CreepRenderer> createCreepRendererPool(int initPoolNumber){

		Pool<CreepRenderer> pool = new Pool<CreepRenderer>(initPoolNumber) {

			@Override
			protected CreepRenderer newObject() {
				System.out.println("#__ POOL __# allocate new Object : " + CreepRenderer.class.getName());
				return new CreepRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//TURRET RENDERER
	private Pool<TurretRenderer> createTurretRendererPool(int initPoolNumber){

		Pool<TurretRenderer> pool = new Pool<TurretRenderer>(initPoolNumber) {

			@Override
			protected TurretRenderer newObject() {
				System.out.println("#__ POOL __# allocate new Object : " + TurretRenderer.class.getName());
				return new TurretRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//TURRET RENDERER
	private Pool<BarricadeRenderer> createBarricadeRendererPool(int initPoolNumber){

		Pool<BarricadeRenderer> pool = new Pool<BarricadeRenderer>(initPoolNumber) {

			@Override
			protected BarricadeRenderer newObject() {
				System.out.println("#__ POOL __# allocate new Object : " + BarricadeRenderer.class.getName());
				return new BarricadeRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//MISSILE RENDERER
	private Pool<MissileRenderer> createMissileRendererPool(int initPoolNumber){

		Pool<MissileRenderer> pool = new Pool<MissileRenderer>(initPoolNumber) {

			@Override
			protected MissileRenderer newObject() {
				System.out.println("#__ POOL __# allocate new Object : " + MissileRenderer.class.getName());
				return new MissileRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//LIFEBAR RENDERER
	private Pool<LifeBarRenderer> createLifeBarRendererPool(int initPoolNumber){

		Pool<LifeBarRenderer> pool = new Pool<LifeBarRenderer>(initPoolNumber) {

			@Override
			protected LifeBarRenderer newObject() {
				System.out.println("#__ POOL __# allocate new Object : " + LifeBarRenderer.class.getName());
				return new LifeBarRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


}
