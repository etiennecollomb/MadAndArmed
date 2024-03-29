package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Scene.GamePlayScreen;
import com.geekmecrazy.madandarmed.Loader.PatternManager;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.SpawnBuildingRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;

public class SpawnBuilding extends Building {
	
	private GamePlayScreen currentGamePlayScreen;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public void init(float posX, float posY, float diameter, BuildingPattern buildingPattern, Life life, GamePlay_Team myTeam) {
		super.init(posX, posY, diameter, buildingPattern, life, myTeam);

		currentGamePlayScreen = (GamePlayScreen)ScreenManager.getCurrentScreen();
		
		/** Renderer */
		SpawnBuildingRenderer spawnBuildingRenderer = GlobalManager.poolAnimManager.getSpawnBuildingRendererPool().obtain();
		
		AnimatedTextureType animatedTextureType = PatternManager.getTexturesPattern().get(myTeam.getTeamID().name()).getTextures().get(buildingPattern.getBuildingName().name());
		spawnBuildingRenderer.init(GlobalManager.poolAnimManager.getSpriteSheets().get(animatedTextureType), this, this.currentGamePlayScreen.getIsoGrid());
		this.currentGamePlayScreen.getIsoGrid().place(spawnBuildingRenderer, (int)posX, (int)posY);
		this.currentGamePlayScreen.getIsoGrid().getIsoMapState().add(spawnBuildingRenderer);
		this.setMilitaryRenderer(spawnBuildingRenderer);
		
		this.setPos(spawnBuildingRenderer.getX(), spawnBuildingRenderer.getY()); //TODO : mettre coord que sur military et non rendrer!
		
		this.currentGamePlayScreen.getScene().attachChild(this.militaryRenderer);
		
		/** If player team : make it movable */
		//TEMP : this should be only on edit mode WARBASE
		if(myTeam.getTeamID() == TeamID.TEAM1)
			this.currentGamePlayScreen.getScene().registerTouchableShape(spawnBuildingRenderer);

		
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

		((SpawnBuildingRenderer)this.getMilitaryRenderer()).releaseControls();
		this.currentGamePlayScreen.getIsoGrid().getIsoMapState().remove((SpawnBuildingRenderer)this.getMilitaryRenderer());
		
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
