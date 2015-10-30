package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.FlameThrowerRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.GunRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.MeshMultiExplosionRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.MissileRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.SwordRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.WeaponRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class Weapon extends Geometrie {

	private Military shooter;
	private Military target;
	
	private WeaponRenderer weaponRenderer;
	
	private float dmgEffect;
	
	/** Weapon
	 * OnUpdate : trajectory of the weapon before reaching the target and until that time, manage by WeaponManager
	 * ***.hit() : spread weapon effect on the target (list of UniqueActionRenedere with delay), weapon is destroy at this time
	 */

	// ===========================================================
	// Constructors
	// ===========================================================

	public Weapon() {}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public Military getShooter() {
		return shooter;
	}

	public void setShooter(Military shooter) {
		this.shooter = shooter;
	}
	
	public Military getTarget() {
		return target;
	}

	public void setTarget(Military target) {
		this.target = target;
	}

	public WeaponRenderer getWeaponRenderer() {
		return weaponRenderer;
	}

	public void setWeaponRenderer(WeaponRenderer weaponRenderer) {
		this.weaponRenderer = weaponRenderer;
	}

	public float getDmgEffect() {
		return dmgEffect;
	}

	public void setDmgEffect(float dmgEffect) {
		this.dmgEffect = dmgEffect;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public void reset() {
		this.shooter = null;
		this.target = null;

		//TODO : mettre dans le reset de chaque weapon type au lieu de faire les appels ici avec des instanceof.....
		if(this.getWeaponRenderer() instanceof MissileRenderer)
			PoolAnimManager.getManager().getMissileRendererPool().free(((MissileRenderer) this.getWeaponRenderer()));
		if(this.getWeaponRenderer() instanceof FlameThrowerRenderer)
			PoolAnimManager.getManager().getFlameThrowerRendererPool().free(((FlameThrowerRenderer) this.getWeaponRenderer()));
		if(this.getWeaponRenderer() instanceof GunRenderer)
			PoolAnimManager.getManager().getGunRendererPool().free(((GunRenderer) this.getWeaponRenderer()));
		if(this.getWeaponRenderer() instanceof MeshMultiExplosionRenderer)
			PoolAnimManager.getManager().getMeshMultiExplosionRendererPool().free(((MeshMultiExplosionRenderer) this.getWeaponRenderer()));
		if(this.getWeaponRenderer() instanceof SwordRenderer)
			PoolAnimManager.getManager().getSwordRendererPool().free(((SwordRenderer) this.getWeaponRenderer()));
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	/** Pos : position of the weapon effect (can be little bit shift from the shooter itself, ie: flame thrower) **/
	public void init(final float posX, final float poxY, Military shooter, Military target){
		super.init(posX, poxY, 0);

		this.setShooter(shooter);
		this.setTarget(target);
		
		this.setDmgEffect(shooter.getAttackBehavior().getWeaponPattern().getDmgEffect());
	}


}
