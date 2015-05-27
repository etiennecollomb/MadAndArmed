package com.geekmecrazy.madandarmed.Pattern;

import java.util.ArrayList;

import com.geekmecrazy.madandarmed.Json.DataLoader;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponName;


public class BuildingPattern {
	
	public static enum BuildingType {
		CASTLE,
		TURRET,
		BARRICADE
	} 
	
	public static enum BuildingSize {
		SMALL		(1), 		//1x1
		MEDIUM		(3), 		//3x3
		BIG			(5);		//5x5
		
		private int mBigNodeSize;

		private BuildingSize(int pBigNodeSize) {
			this.mBigNodeSize = pBigNodeSize;
		}

		public int getBigNodeSize() {
			return mBigNodeSize;
		}
	}
	
	private static int[] fireAnimation;

	/** Building Type */
	private BuildingType buildingType;
	
	/** Weapon type */
	private WeaponName weaponName;
	
	/** size on the map */
	private BuildingSize buildingSize;
	
	private float life;
	
	/** cost to buy this building */
	private int price;
	
	/** list of row in spriteSheet for Aim Animation */
	private ArrayList<Integer> aimAnimationRow;
	
	/** list of row in spriteSheet for Fire Animation */
	private ArrayList<Integer> fireAnimationRow;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public static int[] getFireAnimation() {
		return fireAnimation;
	}

	public static void setFireAnimation(int[] fireAnimation) {
		BuildingPattern.fireAnimation = fireAnimation;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	public BuildingType getBuildingType() {
		return buildingType;
	}

	public WeaponName getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(WeaponName weaponName) {
		this.weaponName = weaponName;
	}

	public BuildingSize getBuildingSize() {
		return buildingSize;
	}

	public void setBuildingSize(BuildingSize buildingSize) {
		this.buildingSize = buildingSize;
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

	public ArrayList<Integer> getFireAnimationRow() {
		return fireAnimationRow;
	}

	public void setFireAnimationRow(ArrayList<Integer> fireAnimationRow) {
		this.fireAnimationRow = fireAnimationRow;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/** renvoie une array de row correspondant a une animation */
	public void calculateAnimationListFire(){
		
		WeaponPattern weaponPattern = DataLoader.getWeaponsPattern().get(this.getWeaponName().name());
		
		if(weaponPattern !=null){
			switch (weaponPattern.getWeaponType()){
			case MISSILE:
				//TODO: on set une anim de Aim
				//revoir avec anim de Fire
				this.fireAnimation = new int[1];
				this.fireAnimation[0]=this.getAimAnimationRow().get(0);
				break;
			default:
				break;
			}
		}

	}

}
