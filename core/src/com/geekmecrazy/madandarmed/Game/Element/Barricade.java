package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Game.Element.Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Json.DataLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.BarricadeRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class Barricade extends Building {
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public void init(float posX, float posY, float diameter, BuildingPattern buildingPattern, Life life, Team myTeam, Team ennemyTeam) {
		super.init(posX, posY, diameter, buildingPattern, life, myTeam, ennemyTeam);
		
		/** Renderer */
		BarricadeRenderer barricadeRenderer = PoolAnimManager.getManager().getBarricadeRendererPool().obtain();
		
		AnimatedTextureType animatedTextureType = DataLoader.getTexturesPattern().get(myTeam.getTeamID().name()).getTextures().get(buildingPattern.getBuildingType().name());
		barricadeRenderer.init(PoolAnimManager.getManager().getSpriteSheets().get(animatedTextureType), buildingPattern, this, FightScreen.isoGrid);
		this.setMilitaryRenderer(barricadeRenderer);
		
		FightScreen.getManager().getScene().attachChild(this.militaryRenderer);
		
		/** If player team : make it movable */
		//TEMP : this should be only on edit mode
		if(myTeam.getTeamID() == TeamID.TEAM1)
			FightScreen.getManager().getScene().registerTouchableShape(barricadeRenderer);

		
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
