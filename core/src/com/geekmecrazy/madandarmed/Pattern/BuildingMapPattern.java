package com.geekmecrazy.madandarmed.Pattern;

import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingType;

public class BuildingMapPattern {

	private BuildingType buildingType;
	
	private int gridPositionX;
	
	private int gridPositionY;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public BuildingType getBuildingType() {
		return buildingType;
	}
	
	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	public int getGridPositionX() {
		return gridPositionX;
	}

	public void setGridPositionX(int gridPositionX) {
		this.gridPositionX = gridPositionX;
	}

	public int getGridPositionY() {
		return gridPositionY;
	}

	public void setGridPositionY(int gridPositionY) {
		this.gridPositionY = gridPositionY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	
}
