package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Missile;
import com.geekmecrazy.madandarmed.Renderer.MissileRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class MissileManager {
	// ===========================================================
	// Singleton manager
	// ===========================================================
	private static MissileManager missileManager;
	
	/** Creation et initialisation du manager */
	public static void initManager() {
		if (missileManager != null) throw new RuntimeException("missileManager already created ! missileManager is not null");
		missileManager = new MissileManager();
	}

	/** Disable object's instantiation (private constructor) */
	private MissileManager(){
		this.listMissiles = new ArrayList<Missile>();
	}
	
	/** Acces au manager */
	public static MissileManager getManager(){
		if (missileManager == null) throw new RuntimeException("missileManager not created ! missileManager is null");
		return missileManager;
	}

	/** Destruction du manager */
	public static void destroyManager(){
		if (missileManager == null) throw new RuntimeException("missileManager not created ! missileManager is null");
		missileManager.destroy();
	}
	
	/** Destruction */
	public void destroy(){
		missileManager=null;
	}
	
	// ===========================================================
	// Manager
	// ===========================================================
	private List<Missile> listMissiles;
	
	/** Tirer un nouveau missile */
	public void fireMissile(Military shooter, Military target){
		//SoundManager.playSound(SoundType.ROCKET_LAUNCH);
		Missile missile = PoolManager.getManager().getMissilePool().obtain();
		float startingFirePosX = shooter.getPos().getX();
		float startingFirePosY = shooter.getPos().getY();
		MissileRenderer missileRenderer = PoolAnimManager.getManager().getMissileRendererPool().obtain();
		missile.init(target, shooter.getWeaponPattern().getAnimatedTextureType(), shooter.getWeaponPattern().getMissileSpeed(), shooter.getWeaponPattern().getDmgEffect(), startingFirePosX, startingFirePosY, missileRenderer);
		addMissile(missile);
	}

	/** Le missile à exploser */
	public void finishMissile(Missile missile){
		removeMissile(missile);
		PoolManager.getManager().getMissilePool().free(missile); //appel ensuite missile.reset()
		//missile.recycle();
	}
	
	/** Enregistre le missile dans le manager */
	private void addMissile(Missile missile){
		listMissiles.add(missile);
	}
	
	/** Desenregistre le missile dans le manager */
	private void removeMissile(Missile missile){
		listMissiles.remove(missile);
	}
	
	/** Mise a jour de l'état des missiles à chaque cycle */
	public void runUpdateNextState(){	
		for(int i=0; i<listMissiles.size();i++)listMissiles.get(i).onUpdateNextState();
	}

	public List<Missile> getListMissiles() {
		return listMissiles;
	}
}
