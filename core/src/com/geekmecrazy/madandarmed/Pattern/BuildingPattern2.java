package com.geekmecrazy.madandarmed.Pattern;


public class BuildingPattern2 {
	
	public static enum BuildingType {
		CASTLE,
		TURRET,
		BARRICADE;
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
	
	public String name;
	public String number;

	public BuildingPattern2(){
	}
	
	public BuildingPattern2(String s1, String s2){
		name=s1;
		number=s2;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	

	
	public BuildingType getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	public int getWeaponPatternType() {
		return weaponPatternType;
	}

	public void setWeaponPatternType(int weaponPatternType) {
		this.weaponPatternType = weaponPatternType;
	}
	
}
