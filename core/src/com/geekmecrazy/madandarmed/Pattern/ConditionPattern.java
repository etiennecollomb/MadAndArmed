package com.geekmecrazy.madandarmed.Pattern;

import com.geekmecrazy.madandarmed.Pattern.BuildingQgPattern.BuildingQgID;
import com.geekmecrazy.madandarmed.Pattern.RessourcePattern.RessourceID;


public class ConditionPattern {
	private ConditionType type;						// Type de condiction
	
	private BuildingQgID buildingID;				// Type building
	private RessourceID ressourceID;				// Type ressource
	private int number;								// Number required
	private int level;								// Level required
	
	
	public enum ConditionType {
		BUILDING,				// Condition sur un batiment (type, nombre et level)
		RESSOURCE,				// Condition sur une ressource (type et nombre)
	} 
	
	
	public ConditionType getType() {
		return type;
	}

	public void setType(ConditionType type) {
		this.type = type;
	}

	public BuildingQgID getBuildingID() {
		return buildingID;
	}
	
	public void setBuildingID(BuildingQgID buildingID) {
		this.buildingID = buildingID;
	}
	
	public RessourceID getRessourceID() {
		return ressourceID;
	}

	public void setRessourceID(RessourceID ressourceID) {
		this.ressourceID = ressourceID;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
