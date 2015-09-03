package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Game.Element.FlameThrower;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Missile;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponType;
import com.geekmecrazy.madandarmed.Renderer.MissileRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;
import com.geekmecrazy.madandarmed.pool.PoolManager;

public class WeaponManager implements IUpdatable {

	private List<Weapon> weaponsList;

	// ===========================================================
	// Constructors
	// ===========================================================

	/** Singleton manager **/
	private static WeaponManager weaponManager;

	/** Creation et initialisation du manager */
	public static void initManager() {
		if (weaponManager != null) throw new RuntimeException("weaponManager already created ! weaponManager is not null");
		weaponManager = new WeaponManager();
	}

	/** Disable object's instantiation (private constructor) */
	private WeaponManager(){
		this.weaponsList = new ArrayList<Weapon>();
	}

	/** Acces au manager */
	public static WeaponManager getManager(){
		if (weaponManager == null) throw new RuntimeException("weaponManager not created ! weaponManager is null");
		return weaponManager;
	}

	/** Destruction du manager */
	public static void destroyManager(){
		if (weaponManager == null) throw new RuntimeException("weaponManager not created ! weaponManager is null");
		weaponManager=null;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/** Mise a jour de l'état des missiles à chaque cycle */
	public void onUpdate(){	
		for(int i=0; i<weaponsList.size(); i++)
			this.weaponsList.get(i).onUpdate();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/** launch new weapon **/
	public void fireWeapon(Military shooter, Military target){

		switch(shooter.getAttackBehavior().getWeaponPattern().getWeaponType()){
		case CAC:
			this.fireCAC(shooter, target);
			break;
		case GUN:
			this.fireGUN(shooter, target);
			break;
		case MISSILE:
			this.fireFlameThrower(shooter, target); //TEST
			//this.fireMISSILE(shooter, target);
			break;
		default:
			break;
		}

	}
	
	public void destroyWeapon(Weapon weapon){
		
		if(weapon instanceof Missile)
			PoolManager.getManager().getMissilePool().free((Missile)weapon);

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
	private void fireCAC(Military shooter, Military target){}

	private void fireGUN(Military shooter, Military target){}

	private void fireFlameThrower(Military shooter, Military target){

		//SoundManager.playSound(SoundType.ROCKET_LAUNCH);
		FlameThrower flameThrower = PoolAnimManager.getManager().getMissilePool().obtain();//new FlameThrower(); 
		float startingFirePosX = shooter.getPos().getX();
		float startingFirePosY = shooter.getPos().getY();
		flameThrower.init(startingFirePosX, startingFirePosY, shooter, target);
		this.addWeapon(flameThrower);

	}
	
	private void fireMISSILE(Military shooter, Military target){

		//SoundManager.playSound(SoundType.ROCKET_LAUNCH);
		Missile missile = PoolManager.getManager().getMissilePool().obtain();
		float startingFirePosX = shooter.getPos().getX();
		float startingFirePosY = shooter.getPos().getY();
		missile.init(startingFirePosX, startingFirePosY, shooter, target);
		this.addWeapon(missile);

	}


}
