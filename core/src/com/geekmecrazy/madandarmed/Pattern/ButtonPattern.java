package com.geekmecrazy.madandarmed.Pattern;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingName;


public class ButtonPattern {

	private BuildingName buildingName;
	
	private TextureType icon;

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
