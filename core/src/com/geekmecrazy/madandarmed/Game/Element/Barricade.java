package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.BuildingRenderer;

public class Barricade extends Building {
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public void init(float posX, float posY, float diameter, BuildingPattern buildingPattern, Life life, Team myTeam, Team ennemyTeam, BuildingRenderer animatedMilitary ) {
		super.init(posX, posY, diameter, buildingPattern, life, myTeam, ennemyTeam, animatedMilitary);
		
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdateNextState(){

	}

	// ===========================================================
	// Methods
	// ===========================================================

}
