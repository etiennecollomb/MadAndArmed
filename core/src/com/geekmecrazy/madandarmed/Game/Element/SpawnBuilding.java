package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Scene.GamePlayScreen;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.SpawnBuildingRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;

public class SpawnBuilding extends Building {
	
	int spawnOrder; /** ordre d apparition du spawn building , -1 not initiated, 0 first one ...**/
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public void init(float posX, float posY, float diameter, BuildingPattern buildingPattern, Life life, GamePlay_Team myTeam) {
		super.init(posX, posY, diameter, buildingPattern, life, myTeam);
		
		/** Renderer */
		SpawnBuildingRenderer spawnBuildingRenderer = GlobalManager.poolAnimManager.getSpawnBuildingRendererPool().obtain();
		
		AnimatedTextureType animatedTextureType = PatternLoader.getTexturesPattern().get(myTeam.getTeamID().name()).getTextures().get(buildingPattern.getBuildingName().name());
		spawnBuildingRenderer.init(GlobalManager.poolAnimManager.getSpriteSheets().get(animatedTextureType), this, GamePlayScreen.isoGrid);
		GamePlayScreen.isoGrid.place(spawnBuildingRenderer, (int)posX, (int)posY);
		GamePlayScreen.isoGrid.getIsoMapState().add(spawnBuildingRenderer);
		this.setMilitaryRenderer(spawnBuildingRenderer);
		
		this.setPos(spawnBuildingRenderer.getX(), spawnBuildingRenderer.getY()); //TODO : mettre coord que sur military et non rendrer!
		
		ScreenManager.getCurrentScreen().getScene().attachChild(this.militaryRenderer);
		
		/** If player team : make it movable */
		//TEMP : this should be only on edit mode WARBASE
		if(myTeam.getTeamID() == TeamID.TEAM1)
			ScreenManager.getCurrentScreen().getScene().registerTouchableShape(spawnBuildingRenderer);

		spawnOrder = -1;
		
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public int getSpawnOrder() {
		return spawnOrder;
	}

	public void setSpawnOrder(int spawnOrder) {
		this.spawnOrder = spawnOrder;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void noMoreLife(){
		super.noMoreLife();

		((SpawnBuildingRenderer)this.getMilitaryRenderer()).releaseControls();
		GamePlayScreen.isoGrid.getIsoMapState().remove((SpawnBuildingRenderer)this.getMilitaryRenderer());
		
	}
	
	@Override
	public void reset() {
		super.reset();
		
		GlobalManager.poolAnimManager.getSpawnBuildingRendererPool().free((SpawnBuildingRenderer) this.militaryRenderer);
	}

	// ===========================================================
	// Methods
	// ===========================================================

}
