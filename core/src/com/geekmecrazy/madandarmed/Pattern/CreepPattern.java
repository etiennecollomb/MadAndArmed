package com.geekmecrazy.madandarmed.Pattern;

import java.util.List;

import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponName;

public class CreepPattern {
	
	public static enum CreepType {
		GLADIATOR,
		MARINE,
		MESH
	} 
	
	public static enum CreepSize {
		SMALL(0), 		//1x1, rayon 0
		MEDIUM(1), 		//3x3, rayon 1
		BIG(2);			//5x5, rayon 2

		private int mRayon;

		private CreepSize(int pRayon) {
			this.mRayon = pRayon;
		}

		public int getRayon() {
			return mRayon;
		}
	}
	
	/** Creep Type */
	private CreepType creepType;

	/** Weapon type */
	private WeaponName weaponName;
	
	/** size on the map en small Node */
	private CreepSize creepSize;

	/** Unit diameter in pixel (used for boids algo) */
	private float diameter;
	
	private float life;
	
	/** walking speed */
	private float walkSpeed;
	
	/** Price of the unit at creation */
	private int price;

	/** how many pixels for one walk step */
	private int animationWalkPixelLength;

	/** list of row in spriteSheet for Walk Animation */
	private List<Integer> walkAnimationRow;
	
	/** list of row in spriteSheet for Walk Animation */
	private List<Integer> aimAnimationRow;

	/** list of row in spriteSheet for Fire Animation */
	private List<Integer> fireAnimationRow;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public CreepType getCreepType() {
		return creepType;
	}

	public void setCreepType(CreepType creepType) {
		this.creepType = creepType;
	}

	public WeaponName getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(WeaponName weaponName) {
		this.weaponName = weaponName;
	}

	public CreepSize getCreepSize() {
		return creepSize;
	}

	public void setCreepSize(CreepSize creepSize) {
		this.creepSize = creepSize;
	}

	public float getDiameter() {
		return diameter;
	}

	public void setDiameter(float diameter) {
		this.diameter = diameter;
	}

	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}

	public void setWalkSpeed(float walkSpeed) {
		this.walkSpeed = walkSpeed;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAnimationWalkPixelLength() {
		return animationWalkPixelLength;
	}

	public void setAnimationWalkPixelLength(int animationWalkPixelLength) {
		this.animationWalkPixelLength = animationWalkPixelLength;
	}

	public List<Integer> getAimAnimationRow() {
		return aimAnimationRow;
	}

	public void setAimAnimationRow(List<Integer> aimAnimationRow) {
		this.aimAnimationRow = aimAnimationRow;
	}

	public List<Integer> getWalkAnimationRow() {
		return walkAnimationRow;
	}

	public void setWalkAnimationRow(List<Integer> walkAnimationRow) {
		this.walkAnimationRow = walkAnimationRow;
	}

	public List<Integer> getFireAnimationRow() {
		return fireAnimationRow;
	}

	public void setFireAnimationRow(List<Integer> fireAnimationRow) {
		this.fireAnimationRow = fireAnimationRow;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	
	
/*
	private UnitTeam mTeam;								// Team de l'unite (a cause des texture associe a l unit ID ..; TODO : a refaire car ca devrait pas etre ici ...)
	
	private UnitType mUnitType;							// Unit type (air, sol)
	
	private UnitSize mUnitSize;							// Unit size in Small Node (small, medium, big)

	private WeaponPattern mWeaponPattern;

	private int mPrice;									// Unit price
	
	private float mLife;								// Unit Life point
	
	private float mSpeed;								// Unit move speed

	private AnimatedTextureType mAnimatedTextureType;	// SpriteSheetRoot du creepRenderer
	
	private TextureType mSpriteButton;					// Sprite used for the button
	
	private int mMenuPosition;							//positon des boutons unit dans le menu en bas de Fight

	private int mWaitAnimationRow;						// Tile Row WAIT
	
	private int mAimAnimationRow;						// Tile Row AIM
	
	private int[] mFireAnimationRow;					// Tiles Row FIRE
	
	private int[] mWalkAnimationRow;					// Tiles Row WALK

	private int mAnimationWalkPixelLength;				// Number of pixel for one step for walk animation

	private int[] mFireAnimation;						//List des animations Fire
	
	private int[] mWalkAnimation;						//List des animations Walk

	public enum CreepID {
		//TEAM 1 (PLAYER)
        GLADIATOR_HD_TEAM1,
		MARINE_HD_TEAM1,
        MESH_HD_TEAM1,

		//TEAM 2 (IA)
        GLADIATOR_HD_TEAM2,
        MARINE_HD_TEAM2,
        MESH_HD_TEAM2,

		//OTHER TEMP
		NAO,
		SPARTAN,
		HORUS
	}

	public enum UnitType {
		AIR, 
		SOL
	}

	public enum UnitTeam {
		TEAM1, 
		TEAM2
	}
*/

}


