package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Game.Factory.BuildingFactory;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Json.DataLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.BuildingRenderer;
import com.geekmecrazy.madandarmed.Renderer.LifeBarRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class Building extends Military{

	private BuildingPattern pattern;
	
	private LifeBarRenderer lifeBarreRenderer;
	
    // ===========================================================
    // Constructors
    // ===========================================================
    
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================
	
	public BuildingPattern getPattern() {
		return pattern;
	}
	
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

	@Override
	public void reset() {
		super.reset();
		
		this.militaryRenderer.detachSelf();
		
		pattern=null;
		
		PoolAnimManager.getManager().getBuildingRendererPool().free((BuildingRenderer) this.militaryRenderer);

		if(lifeBarreRenderer!=null) PoolAnimManager.getManager().getLifeBarRendererPool().free(lifeBarreRenderer);
		lifeBarreRenderer = null;
	}

	@Override
	public void notifyDestrution() {
		BuildingFactory.destroy(this);
	}
	
	
    // ===========================================================
    // Methods
    // ===========================================================
    
	public void init(float posX, float posY, float width, float height, BuildingPattern buildingPattern, Life life, Team myTeam, Team ennemyTeam, BuildingRenderer animatedMilitary ) {

		super.init(posX, posY, width, height, life, myTeam, ennemyTeam);

		this.pattern=buildingPattern;

		this.militaryRenderer=animatedMilitary;
		AnimatedTextureType animatedTextureType = DataLoader.getTexturesPattern().get(myTeam.getTeamID().name()).getTextures().get(buildingPattern.getBuildingType().name());
		((BuildingRenderer)this.militaryRenderer).init(PoolAnimManager.getManager().getSpriteSheets().get(animatedTextureType), buildingPattern, this);

		/** Set LifeBar */
		if(life!=null){
			lifeBarreRenderer = PoolAnimManager.getManager().getLifeBarRendererPool().obtain();
			int lifeBarreWidth = 64;
			int lifeBarreHeight = 10;
			this.lifeBarreRenderer.init(life, 0, 100, lifeBarreWidth, lifeBarreHeight);
			this.militaryRenderer.attachChild(this.lifeBarreRenderer, Entity.Alignment.CENTER);
		}

		FightScreen.getManager().getScene().attachChild(animatedMilitary);

	}
	
	/**si plus de vie , alors on enleve le building du jeu
	 * (on fait le recycle Full une fois l anim de mort faite)
	 */
	@Override
	public void noMoreLife(){
		super.noMoreLife();

		this.getMyTeam().removeMilitary(this);
		this.getMyTeam().getStateMap().removeBuilding(this);
		FightScreen.getManager().getOtherTeam(this.getMyTeam()).addScore(this.getPattern().getPrice());
		
		//on efface la lifebar
		if(lifeBarreRenderer!=null) PoolAnimManager.getManager().getLifeBarRendererPool().free(lifeBarreRenderer);
		lifeBarreRenderer = null;
		
	}
	

}
