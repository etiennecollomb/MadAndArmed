package com.geekmecrazy.madandarmed.Pattern;

import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingType;


public class LevelBuildingPattern {
	// ===========================================================
	// Attributes
	// ===========================================================
	private BuildingType modelID;
	private float life;
	private int posNodeX;
	private int posNodeY;
	

	
	// ===========================================================
	// Attributes accessor
	// ===========================================================
	
	public BuildingType getModelID() {
		return modelID;
	}

	public void setModelID(BuildingType modelID) {
		this.modelID = modelID;
	}

	public float getLife() {
		return life;
	}
	
	public void setLife(float life) {
		this.life = life;
	}
	
	public int getPosNodeX() {
		return posNodeX;
	}
	
	public void setPosNodeX(int posNodeX) {
		this.posNodeX = posNodeX;
	}
	
	public int getPosNodeY() {
		return posNodeY;
	}
	
	public void setPosNodeY(int posNodeY) {
		this.posNodeY = posNodeY;
	}

}
