package com.geekmecrazy.madandarmed.pool;

import java.util.HashMap;
import java.util.Map;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet.SpriteSheetType;
import com.geekmecrazy.madandarmed.Renderer.BarricadeRenderer;
import com.geekmecrazy.madandarmed.Renderer.CampBuildingRenderer;
import com.geekmecrazy.madandarmed.Renderer.CreepRenderer;
import com.geekmecrazy.madandarmed.Renderer.LifeBarRenderer;
import com.geekmecrazy.madandarmed.Renderer.SpawnBuildingRenderer;
import com.geekmecrazy.madandarmed.Renderer.TurretRenderer;
import com.geekmecrazy.madandarmed.Renderer.UniqueActionRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.FlameThrowerRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.GunRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.MeshMultiExplosionRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.MissileRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.SwordRenderer;
import com.badlogic.gdx.utils.Pool;


public class PoolAnimManager {

	/** Number of Preloaded item */
	private static final int ALLOCATE_UNIQUE_ACTION_RENDERER	= 10;
	private static final int ALLOCATE_CREEP_RENDERER			= 10;
	private static final int ALLOCATE_TURRET_RENDERER			= 10;
	private static final int ALLOCATE_CAMPBUILDING_RENDERER		= 10;
	private static final int ALLOCATE_SPAWNBUILDING_RENDERER	= 10;
	private static final int ALLOCATE_BARRICADE_RENDERER		= 10;
	private static final int ALLOCATE_SWORD_RENDERER			= 10;
	private static final int ALLOCATE_GUN_RENDERER				= 10;
	private static final int ALLOCATE_FLAMETHROWER_RENDERER		= 10;
	private static final int ALLOCATE_MESHMULTIEXPLOSION_RENDERER	= 10;
	private static final int ALLOCATE_MISSILE_RENDERER			= 10;
	private static final int ALLOCATE_LIFEBAR_RENDERER			= 10;


	/** SpriteSheet */
	private static Map<AnimatedTextureType, SpriteSheet> spriteSheets;


	/** Pools */
	private static Pool<UniqueActionRenderer> uniqueActionRendererPool;
	private static Pool<CreepRenderer> creepRendererPool;
	private static Pool<TurretRenderer> turretRendererPool;
	private static Pool<CampBuildingRenderer> campBuildingRendererPool;
	private static Pool<SpawnBuildingRenderer> spawnBuildingRendererPool;
	private static Pool<BarricadeRenderer> barricadeRendererPool;
	private static Pool<SwordRenderer> swordRendererPool;
	private static Pool<GunRenderer> gunRendererPool;
	private static Pool<FlameThrowerRenderer> flameThrowerRendererPool;
	private static Pool<MeshMultiExplosionRenderer> meshMultiExplosionRendererPool;
	private static Pool<MissileRenderer> missileRendererPool;
	private static Pool<LifeBarRenderer> lifeBarRendererPool;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public PoolAnimManager(){
		init();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public static Map<AnimatedTextureType, SpriteSheet> getSpriteSheets() {
		return spriteSheets;
	}

	public static Pool<UniqueActionRenderer> getUniqueActionRendererPool() {
		return uniqueActionRendererPool;
	}

	public static Pool<CreepRenderer> getCreepRendererPool() {
		return creepRendererPool;
	}

	public static Pool<TurretRenderer> getTurretRendererPool() {
		return turretRendererPool;
	}

	public static Pool<CampBuildingRenderer> getCampBuildingRendererPool() {
		return campBuildingRendererPool;
	}

	public static Pool<SpawnBuildingRenderer> getSpawnBuildingRendererPool() {
		return spawnBuildingRendererPool;
	}

	public static Pool<BarricadeRenderer> getBarricadeRendererPool() {
		return barricadeRendererPool;
	}

	public static Pool<SwordRenderer> getSwordRendererPool() {
		return swordRendererPool;
	}

	public static Pool<GunRenderer> getGunRendererPool() {
		return gunRendererPool;
	}

	public static Pool<FlameThrowerRenderer> getFlameThrowerRendererPool() {
		return flameThrowerRendererPool;
	}

	public static Pool<MeshMultiExplosionRenderer> getMeshMultiExplosionRendererPool() {
		return meshMultiExplosionRendererPool;
	}

	public static Pool<MissileRenderer> getMissileRendererPool() {
		return missileRendererPool;
	}

	public static Pool<LifeBarRenderer> getLifeBarRendererPool() {
		return lifeBarRendererPool;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static void init () {

		/** Sprites */
		spriteSheets = new HashMap<AnimatedTextureType, SpriteSheet>();

		for (AnimatedTextureType animatedTextureType : AnimatedTextureType.values()) {
			SpriteSheet spriteSheet = new SpriteSheet(animatedTextureType);
			spriteSheets.put(animatedTextureType, spriteSheet);
		}


		/** Pools */
		uniqueActionRendererPool = createUniqueActionRendererPool(ALLOCATE_UNIQUE_ACTION_RENDERER);
		creepRendererPool = createCreepRendererPool(ALLOCATE_CREEP_RENDERER);
		turretRendererPool = createTurretRendererPool(ALLOCATE_TURRET_RENDERER);
		campBuildingRendererPool = createCampBuildingRendererPool(ALLOCATE_CAMPBUILDING_RENDERER);
		spawnBuildingRendererPool = createSpawnBuildingRendererPool(ALLOCATE_SPAWNBUILDING_RENDERER);
		barricadeRendererPool = createBarricadeRendererPool(ALLOCATE_BARRICADE_RENDERER);
		swordRendererPool = createSwordRendererPool(ALLOCATE_SWORD_RENDERER);
		gunRendererPool = createGunRendererPool(ALLOCATE_GUN_RENDERER);
		flameThrowerRendererPool = createFlameThrowerRendererPool(ALLOCATE_FLAMETHROWER_RENDERER);
		meshMultiExplosionRendererPool = createMeshMultiExplosionRendererPool(ALLOCATE_MESHMULTIEXPLOSION_RENDERER);
		missileRendererPool = createMissileRendererPool(ALLOCATE_MISSILE_RENDERER);
		lifeBarRendererPool = createLifeBarRendererPool(ALLOCATE_LIFEBAR_RENDERER);

	}

	// CREATE
	//===================

	//UNIQUE ACTION
	private static Pool<UniqueActionRenderer> createUniqueActionRendererPool(int initPoolNumber){

		Pool<UniqueActionRenderer> pool = new Pool<UniqueActionRenderer>(initPoolNumber) {

			@Override
			protected UniqueActionRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + UniqueActionRenderer.class.getName());
				return new UniqueActionRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//CREEP RENDERER
	private static Pool<CreepRenderer> createCreepRendererPool(int initPoolNumber){

		Pool<CreepRenderer> pool = new Pool<CreepRenderer>(initPoolNumber) {

			@Override
			protected CreepRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + CreepRenderer.class.getName());
				return new CreepRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//TURRET RENDERER
	private static Pool<TurretRenderer> createTurretRendererPool(int initPoolNumber){

		Pool<TurretRenderer> pool = new Pool<TurretRenderer>(initPoolNumber) {

			@Override
			protected TurretRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + TurretRenderer.class.getName());
				return new TurretRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}

	//CAMP BUILDING RENDERER
	private static Pool<CampBuildingRenderer> createCampBuildingRendererPool(int initPoolNumber){

		Pool<CampBuildingRenderer> pool = new Pool<CampBuildingRenderer>(initPoolNumber) {

			@Override
			protected CampBuildingRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + CampBuildingRenderer.class.getName());
				return new CampBuildingRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}

	//SPAWN BUILDING RENDERER
	private static Pool<SpawnBuildingRenderer> createSpawnBuildingRendererPool(int initPoolNumber){

		Pool<SpawnBuildingRenderer> pool = new Pool<SpawnBuildingRenderer>(initPoolNumber) {

			@Override
			protected SpawnBuildingRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + SpawnBuildingRenderer.class.getName());
				return new SpawnBuildingRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}

	//BARRICADE RENDERER
	private static Pool<BarricadeRenderer> createBarricadeRendererPool(int initPoolNumber){

		Pool<BarricadeRenderer> pool = new Pool<BarricadeRenderer>(initPoolNumber) {

			@Override
			protected BarricadeRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + BarricadeRenderer.class.getName());
				return new BarricadeRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//SWORD RENDERER
	private static Pool<SwordRenderer> createSwordRendererPool(int initPoolNumber){

		Pool<SwordRenderer> pool = new Pool<SwordRenderer>(initPoolNumber) {

			@Override
			protected SwordRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + SwordRenderer.class.getName());
				return new SwordRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//GUN RENDERER
	private static Pool<GunRenderer> createGunRendererPool(int initPoolNumber){

		Pool<GunRenderer> pool = new Pool<GunRenderer>(initPoolNumber) {

			@Override
			protected GunRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + GunRenderer.class.getName());
				return new GunRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//FLAMETHROWER RENDERER
	private static Pool<FlameThrowerRenderer> createFlameThrowerRendererPool(int initPoolNumber){

		Pool<FlameThrowerRenderer> pool = new Pool<FlameThrowerRenderer>(initPoolNumber) {

			@Override
			protected FlameThrowerRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + FlameThrowerRenderer.class.getName());
				return new FlameThrowerRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//MESHMULTIEXPLOSION RENDERER
	private static Pool<MeshMultiExplosionRenderer> createMeshMultiExplosionRendererPool(int initPoolNumber){

		Pool<MeshMultiExplosionRenderer> pool = new Pool<MeshMultiExplosionRenderer>(initPoolNumber) {

			@Override
			protected MeshMultiExplosionRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + MeshMultiExplosionRenderer.class.getName());
				return new MeshMultiExplosionRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//MISSILE RENDERER
	private static Pool<MissileRenderer> createMissileRendererPool(int initPoolNumber){

		Pool<MissileRenderer> pool = new Pool<MissileRenderer>(initPoolNumber) {

			@Override
			protected MissileRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + MissileRenderer.class.getName());
				return new MissileRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}


	//LIFEBAR RENDERER
	private static Pool<LifeBarRenderer> createLifeBarRendererPool(int initPoolNumber){

		Pool<LifeBarRenderer> pool = new Pool<LifeBarRenderer>(initPoolNumber) {

			@Override
			protected LifeBarRenderer newObject() {
				//System.out.println("#__ POOL __# allocate new Object : " + LifeBarRenderer.class.getName());
				return new LifeBarRenderer();
			}

		};

		for(int i=0; i<initPoolNumber ; i++)
			pool.obtain();
		return pool;
	}




}
