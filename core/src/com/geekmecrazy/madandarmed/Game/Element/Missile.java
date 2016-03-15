package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.Fight_WeaponManager;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.MissileRenderer;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class Missile extends Weapon {
	// ===========================================================
	// Attributes
	// ===========================================================
	private Vector2d m_vel = new Vector2d();
	private float vitesse;
	
	public Missile() {
	}
	
	// ===========================================================
	// Init
	// ===========================================================
	public void init(float posX, float posY, Military shooter, Military target){
		
		super.init(posX, posY, shooter, target);
		this.vitesse=shooter.getAttackBehavior().getWeaponPattern().getMissileSpeed();
		
		this.setWeaponRenderer( PoolAnimManager.getManager().getMissileRendererPool().obtain() );
		((MissileRenderer) this.getWeaponRenderer()).init(this, shooter);

		GlobalManager.fightScreen.getScene().attachChild(this.getWeaponRenderer());
		
		}

	
	// ===========================================================
	// Run item
	// ===========================================================
	@Override
	public void onUpdate(){
		
		//le cas ou la target est morte
		//TODO : suivre les derniere coordonn�e connues avant mort?
		if(!this.getTarget().isAlive()){
			GlobalManager.fight_WeaponManager.destroyWeapon(this);
			return;
		}

	
		//calcul la trajectoire direct vers la target
		float targetCenterX_ = this.getTarget().getPos().getX()+this.getTarget().getDiameter()/2f;
		float targetCenterY_ = this.getTarget().getPos().getY()+this.getTarget().getDiameter()/2f;
		m_vel.set(targetCenterX_, targetCenterY_);
		m_vel.sub(this.getPos());
		float distance = m_vel.length();
		m_vel.normalize();
		m_vel.scale(vitesse);

		//on a atteint la cible au prochain coup?
		if((distance - this.getTarget().getDiameter()/2f)<=vitesse){
			this.getTarget().hit(this);
			GlobalManager.fight_WeaponManager.destroyWeapon(this);
			return;
		}
		//sinon on "avance" vers elle
		else this.getPos().add(m_vel);
		
		//on set la direction local du missile
		setNormalizedDir(m_vel);

	}

	
	// ===========================================================
	// Recycle
	// ===========================================================
	@Override
	public void reset() {
		super.reset();
		vitesse=0f;
	}

}
