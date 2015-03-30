package com.geekmecrazy.madandarmed.Pattern;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Game.Element.GameElement;
import com.geekmecrazy.madandarmed.Utils.Vector2d;


public class WeaponPattern extends GameElement {

	private WeaponType mWeaponType;						// Type de tir

	private float mHitRange;							// distance maxi de tir

	private int mHitSpeed;								// nombre de cycle entre chaque tir

	private int mDmgEffect;								// dommage cause

	private AnimatedTextureType mAnimatedTextureType;	// Animation sur l'unite touche

	private float mMissileSpeed;						// Missile(weapon) move speed

	public enum WeaponType {
		MISSILE,
		GUN,
		CAC
	}	

	public void init(float pHitRange, int pHitSpeed, int pDmgEffect, float pMissileSpeed, AnimatedTextureType pAnimatedTextureType, WeaponType pWeaponType){
		this.mWeaponType=pWeaponType;
		this.mHitRange=pHitRange;
		this.mHitSpeed=pHitSpeed;
		this.mDmgEffect=pDmgEffect;
		this.mAnimatedTextureType=pAnimatedTextureType;
		this.mMissileSpeed=pMissileSpeed;
	}

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public WeaponType getWeaponType() {
		return mWeaponType;
	}

	public void setWeaponType(WeaponType pWeaponType) {
		this.mWeaponType = pWeaponType;
	}

	public float getHitRange() {
		return mHitRange;
	}

	public void setHitRange(float pHitRange) {
		this.mHitRange = pHitRange;
	}

	public int getHitSpeed() {
		return mHitSpeed;
	}

	public void setHitSpeed(int pHitSpeed) {
		this.mHitSpeed = pHitSpeed;
	}

	public int getDmgEffect() {
		return mDmgEffect;
	}

	public void setDmgEffect(int pDmgEffect) {
		this.mDmgEffect = pDmgEffect;
	}

	public AnimatedTextureType getAnimatedTextureType() {
		return mAnimatedTextureType;
	}

	public void setAnimatedTextureType(AnimatedTextureType pAnimatedTextureType) {
		this.mAnimatedTextureType = pAnimatedTextureType;
	}

	public float getMissileSpeed() {
		return mMissileSpeed;
	}

	public void setMissileSpeed(float pMissileSpeed) {
		this.mMissileSpeed = pMissileSpeed;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
	}

	// ===========================================================
	// Methods
	// ===========================================================

}


