package com.geekmecrazy.madandarmed.Pattern;

import java.util.List;

import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class TeamMapPattern {

	private Vector2d spawnPoint;

	/** Team names... and AnimatedTextureType associated */
	private List<BuildingMapPattern> buildingsList;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public Vector2d getSpawnPoint() {
		return spawnPoint;
	}

	public void setSpawnPoint(Vector2d spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	public List<BuildingMapPattern> getBuildingsList() {
		return buildingsList;
	}

	public void setBuildingsList(List<BuildingMapPattern> buildingsList) {
		this.buildingsList = buildingsList;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
}
