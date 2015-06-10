package com.geekmecrazy.madandarmed.Pattern;

import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.SoundType;


public class WeaponPattern{

	public static enum WeaponName {
		CAC_1,
		GUN_1,
		GUN_2,
		MISSILE_1,
		MISSILE_2
	}
	
	public static enum WeaponType {
		MISSILE,
		GUN,
		CAC
	}	
	
	/** Type de tir */
	private WeaponType weaponType;

	/** distance maxi de tir */
	private float hitRange;

	/** nombre de cycle entre chaque tir */
	private int hitSpeed;

	/** Dommages causes */
	private int dmgEffect;

	/** Animation sur l'unite touche */
	private AnimatedTextureType animatedTextureType;

	/** Missile(weapon) move speed */
	private float missileSpeed;
	
	/** Sounds  of the weapon (can be multiple, choosed by random) */
	private List<SoundType> soundsType;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public float getHitRange() {
		return hitRange;
	}

	public void setHitRange(float hitRange) {
		this.hitRange = hitRange;
	}

	public int getHitSpeed() {
		return hitSpeed;
	}

	public void setHitSpeed(int hitSpeed) {
		this.hitSpeed = hitSpeed;
	}

	public int getDmgEffect() {
		return dmgEffect;
	}

	public void setDmgEffect(int dmgEffect) {
		this.dmgEffect = dmgEffect;
	}

	public AnimatedTextureType getAnimatedTextureType() {
		return animatedTextureType;
	}

	public void setAnimatedTextureType(AnimatedTextureType animatedTextureType) {
		this.animatedTextureType = animatedTextureType;
	}

	public float getMissileSpeed() {
		return missileSpeed;
	}

	public void setMissileSpeed(float missileSpeed) {
		this.missileSpeed = missileSpeed;
	}

	public List<SoundType> getSoundsType() {
		return soundsType;
	}

	public void setSoundsType(List<SoundType> soundsType) {
		this.soundsType = soundsType;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public SoundType getRandometSoundsType() {
		int size = this.soundsType.size();
		return this.soundsType.get( GlobalManager.random.nextInt(size) );
	}
	
}


