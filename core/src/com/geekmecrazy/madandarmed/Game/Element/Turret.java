package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Json.DataLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.LifeBarRenderer;
import com.geekmecrazy.madandarmed.Renderer.TurretRenderer;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class Turret extends Building {

	private Sprite floor;

	private LifeBarRenderer lifeBarreRenderer;

	/** pointing to target */
	private Vector2d directionVector = new Vector2d();
	
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public void init(float posX, float posY, float diameter, BuildingPattern buildingPattern, Life life, Team myTeam, Team ennemyTeam) {
		super.init(posX, posY, diameter, buildingPattern, life, myTeam, ennemyTeam);
		
		/** Renderer */
		TurretRenderer turretRenderer = PoolAnimManager.getManager().getTurretRendererPool().obtain();
		AnimatedTextureType animatedTextureType = DataLoader.getTexturesPattern().get(myTeam.getTeamID().name()).getTextures().get(buildingPattern.getBuildingType().name());
		turretRenderer.init(PoolAnimManager.getManager().getSpriteSheets().get(animatedTextureType), buildingPattern, this);
		FightScreen.isoGrid.place(turretRenderer, (int)posX, (int)posY);
		FightScreen.isoGrid.getIsoMapState().add(turretRenderer);
		this.setMilitaryRenderer(turretRenderer);
		
		this.setPos(turretRenderer.getX(), turretRenderer.getY()); //TODO : mettre coord que sur military et non rendrer!
		
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

		if(this.getAttackBehavior().isAttacking()){
			//En mode attaque, on pointe sur la target
			directionVector.set(-this.getPos().getX(), -this.getPos().getY());
			directionVector.add(this.getAttackBehavior().getCurrentTarget().getPos());
			setNormalizedDir(directionVector);
		}

	}
	
	@Override
	public void noMoreLife(){
		super.noMoreLife();

		FightScreen.isoGrid.getIsoMapState().remove((TurretRenderer)this.getMilitaryRenderer());
		
		//on efface la lifebar
		if(lifeBarreRenderer!=null) PoolAnimManager.getManager().getLifeBarRendererPool().free(lifeBarreRenderer);
		lifeBarreRenderer = null;
		
	}
	
	@Override
	public void reset() {
		super.reset();

		if(lifeBarreRenderer!=null) PoolAnimManager.getManager().getLifeBarRendererPool().free(lifeBarreRenderer);
		lifeBarreRenderer = null;
	}
	// ===========================================================
	// Methods
	// ===========================================================

}
