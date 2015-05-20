package com.geekmecrazy.madandarmed.Pattern;

import java.util.ArrayList;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;


public class BuildingPattern {
	
	public static enum BuildingType {
		CASTLE,
		TURRET,
		BARRICADE
	} 
	
	public static enum BuildingSize {
		SMALL		(1), 		//1x1
		MEDIUM		(2), 		//3x3
		BIG			(3);		//5x5
		
		private int mBigNodeSize;

		private BuildingSize(int pBigNodeSize) {
			this.mBigNodeSize = pBigNodeSize;
		}

		public int getBigNodeSize() {
			return mBigNodeSize;
		}
	}
	
	/** Building Type */
	private BuildingType buildingType;
	
	/** Weapon type */
	private int weaponPatternType;
	
	/** size on the map */
	private BuildingSize buildingSize;
	
	/** Animated Sprite of the building */
	private AnimatedTextureType animatedTextureType;
	
	private float life;
	
	/** cost to buy this building */
	private int price;
	
	/** list of row in spriteSheet for Aim Animation */
	private ArrayList<Integer> aimAnimationRow;
	
	/** list of row in spriteSheet for Fire Animation */
	private int[] fireAnimationRow;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public BuildingType getBuildingType() {
		return buildingType;
	}

	public void setBuildingID(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	public int getWeaponPatternType() {
		return weaponPatternType;
	}

	public void setWeaponPatternType(int weaponPatternType) {
		this.weaponPatternType = weaponPatternType;
	}

	public BuildingSize getBuildingSize() {
		return buildingSize;
	}

	public void setBuildingSize(BuildingSize buildingSize) {
		this.buildingSize = buildingSize;
	}

	public AnimatedTextureType getAnimatedTextureType() {
		return animatedTextureType;
	}

	public void setAnimatedTextureType(AnimatedTextureType animatedTextureType) {
		this.animatedTextureType = animatedTextureType;
	}

	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ArrayList<Integer> getAimAnimationRow() {
		return aimAnimationRow;
	}

	public void setAimAnimationRow(ArrayList<Integer> aimAnimationRow) {
		this.aimAnimationRow = aimAnimationRow;
	}

	public int[] getFireAnimationRow() {
		return fireAnimationRow;
	}

	public void setFireAnimationRow(int[] fireAnimationRow) {
		this.fireAnimationRow = fireAnimationRow;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================


	//A METTRE DANS RENDERER???
	/** renvoie une array de row correspondant a une animation */
	public void calculateAnimationListFire(){
		/** A REMETTRE !!!
		if(weaponPattern!=null){
			switch (this.getWeaponPattern().getWeaponType()){
			case MISSILE:
				//TODO: on set une anim de Aim
				//revoir avec anim de Fire
				fireAnimation = new int[1];
				fireAnimation[0]=this.getAimAnimationRow()[0];
				break;
			default:
				break;
			}
		}
		*/
	}



}
