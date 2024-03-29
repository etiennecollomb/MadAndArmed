package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Scene.GamePlayScreen;
import com.geekmecrazy.madandarmed.Loader.PatternManager;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.CampBuildingRenderer;
import com.geekmecrazy.madandarmed.Renderer.LifeBarRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;

public class CampBuilding extends Building {

	private Sprite floor;

	private LifeBarRenderer lifeBarreRenderer;
	
	private GamePlayScreen currentGamePlayScreen;

	// ===========================================================
	// Constructors
	// ===========================================================

	public void init(float posX, float posY, float diameter, BuildingPattern buildingPattern, Life life, GamePlay_Team myTeam) {
		super.init(posX, posY, diameter, buildingPattern, life, myTeam);
		
		currentGamePlayScreen = (GamePlayScreen)ScreenManager.getCurrentScreen();
		
		/** Renderer */
		CampBuildingRenderer campBuildingRenderer = GlobalManager.poolAnimManager.getCampBuildingRendererPool().obtain();
		
		AnimatedTextureType animatedTextureType = PatternManager.getTexturesPattern().get(myTeam.getTeamID().name()).getTextures().get(buildingPattern.getBuildingName().name());
		campBuildingRenderer.init(GlobalManager.poolAnimManager.getSpriteSheets().get(animatedTextureType), this);
		this.currentGamePlayScreen.getIsoGrid().place(campBuildingRenderer, (int)posX, (int)posY);
		this.currentGamePlayScreen.getIsoGrid().getIsoMapState().add(campBuildingRenderer);
		this.setMilitaryRenderer(campBuildingRenderer);
		
		this.setPos(campBuildingRenderer.getX(), campBuildingRenderer.getY()); //TODO : mettre coord que sur military et non rendrer!
		
		/** Set LifeBar */
		if(life!=null){
			lifeBarreRenderer = GlobalManager.poolAnimManager.getLifeBarRendererPool().obtain();
			int lifeBarreWidth = 64;
			int lifeBarreHeight = 10;
			this.lifeBarreRenderer.init(life, 0, 100, lifeBarreWidth, lifeBarreHeight);
			this.militaryRenderer.attachChild(this.lifeBarreRenderer, Entity.Alignment.CENTER);
		}

		this.currentGamePlayScreen.getScene().attachChild(this.militaryRenderer);

		
		/** Floor */
		float floorPosX = this.getPos().getX();
		float floorPosY = this.getPos().getY();
		floor = new Sprite();
		floor.init(TextureType.SOL_SOUS_BUILDING);
		floor.setPosition(floorPosX, floorPosY+20);
		this.currentGamePlayScreen.getScene().attachChild(floor);
		
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdateNextState(){
		super.onUpdateNextState();
	}
	
	@Override
	public void noMoreLife(){
		super.noMoreLife();

		currentGamePlayScreen.getIsoGrid().getIsoMapState().remove((CampBuildingRenderer)this.getMilitaryRenderer());
		
		//on efface la lifebar
		if(lifeBarreRenderer!=null) GlobalManager.poolAnimManager.getLifeBarRendererPool().free(lifeBarreRenderer);
		lifeBarreRenderer = null;
		
	}
	
	@Override
	public void reset() {
		super.reset();

		if(lifeBarreRenderer!=null) GlobalManager.poolAnimManager.getLifeBarRendererPool().free(lifeBarreRenderer);
		lifeBarreRenderer = null;
		
		GlobalManager.poolAnimManager.getCampBuildingRendererPool().free((CampBuildingRenderer) this.militaryRenderer);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

}
