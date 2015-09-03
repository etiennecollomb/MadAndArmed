package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.WeaponManager;
import com.geekmecrazy.madandarmed.Renderer.MissileRenderer;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class Missile extends Weapon {
	// ===========================================================
	// Attributes
	// ===========================================================
	private Vector2d m_vel = new Vector2d();
	private AnimatedTextureType hitAnimatedType;
	private float vitesse;
	private float dmgEffect;
	MissileRenderer missileRenderer;
	
	public Missile() {
	}
	
	// ===========================================================
	// Init
	// ===========================================================
	public void init(float posX, float posY, Military shooter, Military target){
		
		super.init(posX, posY, shooter, target);
		this.hitAnimatedType=shooter.getAttackBehavior().getWeaponPattern().getAnimatedTextureType();
		this.vitesse=shooter.getAttackBehavior().getWeaponPattern().getMissileSpeed();
		this.dmgEffect=shooter.getAttackBehavior().getWeaponPattern().getDmgEffect();
		
		this.missileRenderer = PoolAnimManager.getManager().getMissileRendererPool().obtain();;
		this.missileRenderer.init(this);

		FightScreen.getManager().getScene().attachChild(this.missileRenderer);
		
		}

	
	// ===========================================================
	// Run item
	// ===========================================================
	@Override
	public void onUpdate(){
		
		//le cas ou la target est morte
		//TODO : suivre les derniere coordonnée connues avant mort?
		if(!this.getTarget().isAlive()){
			WeaponManager.getManager().destroyWeapon(this);
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
			this.getTarget().hit(dmgEffect, hitAnimatedType);
			WeaponManager.getManager().destroyWeapon(this);
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
		//animatedMissile.setPosition(XSceneManager.OUT_OF_SCENE, XSceneManager.OUT_OF_SCENE);
		PoolAnimManager.getManager().getMissileRendererPool().free(missileRenderer);
		hitAnimatedType=null;
	}

}
