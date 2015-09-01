package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.MissileManager;
import com.geekmecrazy.madandarmed.Renderer.MissileRenderer;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class Missile extends Vehicle {
	// ===========================================================
	// Attributes
	// ===========================================================
	private Vector2d m_vel = new Vector2d();
	private AnimatedTextureType hitAnimatedType;
	private Military target;
	private float vitesse;
	private float dmgEffect;
	
	public Missile() {
	}
	
	// ===========================================================
	// Init
	// ===========================================================
	public void init(Military target, AnimatedTextureType hitAnimatedType, float vitesse, float dmgEffect, float posX, float posY, MissileRenderer missileRenderer){
		
		super.init(posX, posY, 0, null, null, null);
		this.hitAnimatedType=hitAnimatedType;
		this.target=target;
		this.vitesse=vitesse;
		this.dmgEffect=dmgEffect;
		
		this.militaryRenderer = missileRenderer;
		this.militaryRenderer.init(PoolAnimManager.getManager().getSpriteSheets().get(hitAnimatedType), this);

		FightScreen.getManager().getScene().attachChild(this.militaryRenderer);
		
		}

	
	// ===========================================================
	// Run item
	// ===========================================================
	@Override
	public void onUpdateNextState(){
		
		//le cas ou la target est morte
		//TODO : suivre les derniere coordonnée connues avant mort?
		if(!target.isAlive()){
			MissileManager.getManager().finishMissile(this);
			return;
		}

	
		//calcul la trajectoire direct vers la target
		float targetCenterX_ = target.getPos().getX()+target.getDiameter()/2f;
		float targetCenterY_ = target.getPos().getY()+target.getDiameter()/2f;
		m_vel.set(targetCenterX_, targetCenterY_);
		m_vel.sub(this.getPos());
		float distance = m_vel.length();
		m_vel.normalize();
		m_vel.scale(vitesse);

		//on a atteint la cible au prochain coup?
		if((distance - target.getDiameter()/2f)<=vitesse){
			target.hit(dmgEffect, hitAnimatedType);
			MissileManager.getManager().finishMissile(this);
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
		target=null;
		vitesse=0f;
		//animatedMissile.setPosition(XSceneManager.OUT_OF_SCENE, XSceneManager.OUT_OF_SCENE);
		PoolAnimManager.getManager().getMissileRendererPool().free((MissileRenderer)this.militaryRenderer);
		hitAnimatedType=null;
	}

}
