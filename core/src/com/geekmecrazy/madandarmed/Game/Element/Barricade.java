package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Shape.ShapeState;
import com.geekmecrazy.madandarmed.Game.Element.Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
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
		BarricadeRenderer barricadeRenderer = GlobalManager.poolAnimManager.getBarricadeRendererPool().obtain();
		
		AnimatedTextureType animatedTextureType = PatternLoader.getTexturesPattern().get(myTeam.getTeamID().name()).getTextures().get(buildingPattern.getBuildingName().name());
		barricadeRenderer.init(GlobalManager.poolAnimManager.getSpriteSheets().get(animatedTextureType), this, FightScreen.isoGrid);
		FightScreen.isoGrid.place(barricadeRenderer, (int)posX, (int)posY);
		FightScreen.isoGrid.getIsoMapState().add(barricadeRenderer);
		this.setMilitaryRenderer(barricadeRenderer);
		
		this.setPos(barricadeRenderer.getX(), barricadeRenderer.getY()); //TODO : mettre coord que sur military et non rendrer!
		
		GlobalManager.fightScreen.getScene().attachChild(this.militaryRenderer);
		
		/** If player team : make it movable */
		//TEMP : this should be only on edit mode
		if(myTeam.getTeamID() == TeamID.TEAM1)
			GlobalManager.fightScreen.getScene().registerTouchableShape(barricadeRenderer);

		
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public void noMoreLife(){
		super.noMoreLife();

		((BarricadeRenderer)this.getMilitaryRenderer()).releaseControls();
		FightScreen.isoGrid.getIsoMapState().remove((BarricadeRenderer)this.getMilitaryRenderer());
		
	}
	
	@Override
	public void reset() {
		super.reset();
		
		GlobalManager.poolAnimManager.getBarricadeRendererPool().free((BarricadeRenderer) this.militaryRenderer);
	}

	// ===========================================================
	// Methods
	// ===========================================================

}
