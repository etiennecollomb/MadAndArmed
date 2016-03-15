package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.FlameThrower;
import com.geekmecrazy.madandarmed.Game.Element.Gun;
import com.geekmecrazy.madandarmed.Game.Element.MeshMultiExplosion;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Missile;
import com.geekmecrazy.madandarmed.Game.Element.Sword;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.pool.PoolManager;

public class Fight_WeaponManager {

	private List<Weapon> weaponsList;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Fight_WeaponManager(){
		this.weaponsList = new ArrayList<Weapon>();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/** Mise a jour de l'état des missiles à chaque cycle */
	public void runUpdateNextState(){	
		for(int i=0; i<weaponsList.size(); i++)
			this.weaponsList.get(i).onUpdate();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/** launch new weapon **/
	public void fireWeapon(Military shooter, Military target){

		switch(shooter.getAttackBehavior().getWeaponPattern().getWeaponType()){
		case SWORD:
			this.fireSWORD(shooter, target);
			break;
		case GUN:
			this.fireGUN(shooter, target);
			break;
		case FLAMETHROWER:
			this.fireFlameThrower(shooter, target);
			break;
		case MESH_MULTIEXPLOSION:
			this.fireMeshMultiExplosion(shooter, target);
			break;
		case MISSILE:
			this.fireMISSILE(shooter, target);
			break;
		default:
			break;
		}

	}
	
	public void destroyWeapon(Weapon weapon){
		
		if(weapon instanceof Missile)
			GlobalManager.poolManager.getMissilePool().free((Missile)weapon);

		this.removeWeapon(weapon);
	}
			

	/** Enregistre le missile dans le manager */
	private void addWeapon(Weapon weapon){
		this.weaponsList.add(weapon);
	}

	/** Desenregistre le missile dans le manager */
	private void removeWeapon(Weapon weapon){
		this.weaponsList.remove(weapon);
	}
	

	/** Type of weapons **/
	private void fireSWORD(Military shooter, Military target){
		
		Sword sword = GlobalManager.poolManager.getSwordPool().obtain();
		float startingFirePosX = shooter.getPos().getX();
		float startingFirePosY = shooter.getPos().getY();
		sword.init(startingFirePosX, startingFirePosY, shooter, target);
		this.addWeapon(sword);
	}

	private void fireGUN(Military shooter, Military target){
		
		Gun gun = GlobalManager.poolManager.getGunPool().obtain();
		float startingFirePosX = shooter.getPos().getX();
		float startingFirePosY = shooter.getPos().getY();
		gun.init(startingFirePosX, startingFirePosY, shooter, target);
		this.addWeapon(gun);
	}

	private void fireFlameThrower(Military shooter, Military target){

		//SoundManager.playSound(SoundType.ROCKET_LAUNCH);
		FlameThrower flameThrower = GlobalManager.poolManager.getFlameThrowerPool().obtain();
		float startingFirePosX = shooter.getPos().getX();
		float startingFirePosY = shooter.getPos().getY();
		flameThrower.init(startingFirePosX, startingFirePosY, shooter, target);
		this.addWeapon(flameThrower);

	}
	
	private void fireMeshMultiExplosion(Military shooter, Military target){

		//SoundManager.playSound(SoundType.ROCKET_LAUNCH);
		MeshMultiExplosion meshMultiExplosion = GlobalManager.poolManager.getMeshMultiExplosionPool().obtain();
		float startingFirePosX = shooter.getPos().getX();
		float startingFirePosY = shooter.getPos().getY();
		meshMultiExplosion.init(startingFirePosX, startingFirePosY, shooter, target);
		this.addWeapon(meshMultiExplosion);

	}
	
	private void fireMISSILE(Military shooter, Military target){

		//SoundManager.playSound(SoundType.ROCKET_LAUNCH);
		Missile missile = GlobalManager.poolManager.getMissilePool().obtain();
		float startingFirePosX = shooter.getPos().getX();
		float startingFirePosY = shooter.getPos().getY();
		missile.init(startingFirePosX, startingFirePosY, shooter, target);
		this.addWeapon(missile);

	}


}
