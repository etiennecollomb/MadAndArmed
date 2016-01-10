package com.geekmecrazy.madandarmed.Pattern;

import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingName;

public class BuildingMapPattern {

	private BuildingName buildingName;
	
	private int gridPositionX;
	
	private int gridPositionY;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public BuildingName getBuildingName() {
		return buildingName;
	}
	
	public void setBuildingName(BuildingName buildingName) {
		this.buildingName = buildingName;
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
