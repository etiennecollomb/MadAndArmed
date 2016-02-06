package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.CampBuildingRenderer;
import com.geekmecrazy.madandarmed.Renderer.LifeBarRenderer;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class CampBuilding extends Building {

	private Sprite floor;

	private LifeBarRenderer lifeBarreRenderer;

	// ===========================================================
	// Constructors
	// ===========================================================

	public void init(float posX, float posY, float diameter, BuildingPattern buildingPattern, Life life, Team myTeam, Team ennemyTeam) {
		super.init(posX, posY, diameter, buildingPattern, life, myTeam, ennemyTeam);
		
		/** Renderer */
		CampBuildingRenderer campBuildingRenderer = PoolAnimManager.getManager().getCampBuildingRendererPool().obtain();
		AnimatedTextureType animatedTextureType = PatternLoader.getTexturesPattern().get(myTeam.getTeamID().name()).getTextures().get(buildingPattern.getBuildingName().name());
		campBuildingRenderer.init(PoolAnimManager.getManager().getSpriteSheets().get(animatedTextureType), this);
		FightScreen.isoGrid.place(campBuildingRenderer, (int)posX, (int)posY);
		FightScreen.isoGrid.getIsoMapState().add(campBuildingRenderer);
		this.setMilitaryRenderer(campBuildingRenderer);
		
		this.setPos(campBuildingRenderer.getX(), campBuildingRenderer.getY()); //TODO : mettre coord que sur military et non rendrer!
		
		/** Set LifeBar */
		if(life!=null){
			lifeBarreRenderer = PoolAnimManager.getManager().getLifeBarRendererPool().obtain();
			int lifeBarreWidth = 64;
			int lifeBarreHeight = 10;
			this.lifeBarreRenderer.init(life, 0, 100, lifeBarreWidth, lifeBarreHeight);
			this.militaryRenderer.attachChild(this.lifeBarreRenderer, Entity.Alignment.CENTER);
		}

		FightScreen.getManager().getScene().attachChild(this.militaryRenderer);

		
		/** Floor */
		float floorPosX = this.getPos().getX();
		float floorPosY = this.getPos().getY();
		floor = new Sprite();
		floor.init(TextureType.SOL_SOUS_BUILDING);
		floor.setPosition(floorPosX, floorPosY+20);
		FightScreen.getManager().getScene().attachChild(floor);
		
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

		FightScreen.isoGrid.getIsoMapState().remove((CampBuildingRenderer)this.getMilitaryRenderer());
		
		//on efface la lifebar
		if(lifeBarreRenderer!=null) PoolAnimManager.getManager().getLifeBarRendererPool().free(lifeBarreRenderer);
		lifeBarreRenderer = null;
		
	}
	
	@Override
	public void reset() {
		super.reset();

		if(lifeBarreRenderer!=null) PoolAnimManager.getManager().getLifeBarRendererPool().free(lifeBarreRenderer);
		lifeBarreRenderer = null;
		
		PoolAnimManager.getManager().getCampBuildingRendererPool().free((CampBuildingRenderer) this.militaryRenderer);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

}