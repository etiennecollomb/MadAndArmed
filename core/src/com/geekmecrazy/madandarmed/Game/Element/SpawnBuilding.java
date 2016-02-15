package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Shape.ShapeState;
import com.geekmecrazy.madandarmed.Game.Element.Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.BarricadeRenderer;
import com.geekmecrazy.madandarmed.Renderer.SpawnBuildingRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class SpawnBuilding extends Building {
	
	int spawnOrder; /** ordre d apparition du spawn building , -1 not initiated, 0 first one ...**/
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public void init(float posX, float posY, float diameter, BuildingPattern buildingPattern, Life life, Team myTeam, Team ennemyTeam) {
		super.init(posX, posY, diameter, buildingPattern, life, myTeam, ennemyTeam);
		
		/** Renderer */
		SpawnBuildingRenderer spawnBuildingRenderer = PoolAnimManager.getManager().getSpawnBuildingRendererPool().obtain();
		
		AnimatedTextureType animatedTextureType = PatternLoader.getTexturesPattern().get(myTeam.getTeamID().name()).getTextures().get(buildingPattern.getBuildingName().name());
		spawnBuildingRenderer.init(PoolAnimManager.getManager().getSpriteSheets().get(animatedTextureType), this, FightScreen.isoGrid);
		FightScreen.isoGrid.place(spawnBuildingRenderer, (int)posX, (int)posY);
		FightScreen.isoGrid.getIsoMapState().add(spawnBuildingRenderer);
		this.setMilitaryRenderer(spawnBuildingRenderer);
		
		this.setPos(spawnBuildingRenderer.getX(), spawnBuildingRenderer.getY()); //TODO : mettre coord que sur military et non rendrer!
		
		FightScreen.getManager().getScene().attachChild(this.militaryRenderer);
		
		/** If player team : make it movable */
		//TEMP : this should be only on edit mode
		if(myTeam.getTeamID() == TeamID.TEAM1)
			FightScreen.getManager().getScene().registerTouchableShape(spawnBuildingRenderer);

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
		FightScreen.isoGrid.getIsoMapState().remove((SpawnBuildingRenderer)this.getMilitaryRenderer());
		
	}
	
	@Override
	public void reset() {
		super.reset();
		
		PoolAnimManager.getManager().getSpawnBuildingRendererPool().free((SpawnBuildingRenderer) this.militaryRenderer);
	}

	// ===========================================================
	// Methods
	// ===========================================================

}
