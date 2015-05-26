package com.geekmecrazy.madandarmed.Pattern;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingType;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;


public class ButtonPattern {

	private CreepType creepType;
	private BuildingType buildingType;
	
	private TextureType icon;

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

	public BuildingType getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	public TextureType getIcon() {
		return icon;
	}

	public void setIcon(TextureType icon) {
		this.icon = icon;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
}
