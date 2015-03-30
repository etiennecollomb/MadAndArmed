package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Factory.BuildingFactory;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Renderer.BuildingRenderer;
import com.geekmecrazy.madandarmed.Renderer.LifeBarRenderer;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class Building extends Military{

	private BuildingPattern pattern;

	private Sprite floor;
	private LifeBarRenderer lifeBarreRenderer;

	private Vector2d moveVector = new Vector2d();

	// initialise le batiment
	public void init(float posX, float posY, float width, float height, BuildingPattern buildingPattern, Life life, Team myTeam, Team ennemyTeam, WeaponPattern weapon, BuildingRenderer animatedMilitary ) {

		super.init(posX, posY, width, height, life, myTeam, ennemyTeam, weapon);

		this.pattern=buildingPattern;
		setAttacking(true);
		
		float floorPosX = this.getPos().getX();
		float floorPosY = this.getPos().getY();
		floor = new Sprite();
		floor.init(TextureType.SOL_SOUS_BUILDING);
		floor.setPosition(floorPosX, floorPosY+20);
		FightScreen.getManager().getScene().attachChild(floor);

		this.militaryRenderer=animatedMilitary;
		((BuildingRenderer)this.militaryRenderer).init(PoolAnimManager.getManager().getSpriteSheets().get(buildingPattern.getAnimatedTextureType()), buildingPattern, this);

		/** Set LifeBar */
		if(life!=null){
			lifeBarreRenderer = PoolAnimManager.getManager().getLifeBarRendererPool().obtain();
			int lifeBarreWidth = 64;
			int lifeBarreHeight = 10;
			this.lifeBarreRenderer.init(life, 0, -50, lifeBarreWidth, lifeBarreHeight);
			this.militaryRenderer.attachChild(this.lifeBarreRenderer, Entity.Alignment.CENTER);
		}

		FightScreen.getManager().getScene().attachChild(animatedMilitary);

	}

	public String getTitleText(){
		StringBuffer sb = new StringBuffer();
		//sb.append(pattern.getName()+" ("+buildingModel.getNameUnit()+")\n");
		return sb.toString();
	}

	public boolean isCollideWithMe (Shape r){
		return  !( (r.getX() < this.getPos().getX()) || (r.getX() > this.getPos().getX()+this.getWidth()) )
				&&  ( (r.getY() < this.getPos().getY()) || (r.getY() > this.getPos().getY()+this.getHeight()) );
	}



	public BuildingPattern getPattern() {
		return pattern;
	}
	
	//si plus de vie , alors on enleve le building du jeu
	//(on fait le recycle Full une fois l anim de mort faite)
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

	// ===========================================================
	// Run item
	// ===========================================================
	@Override
	public void onUpdateNextState(){

		super.onUpdateNextState();

		if(this.isAttacking()){
			//En mode attaque, on focalise sur la target et non le sens de la direction
			moveVector.set(-this.getPos().getX(), -this.getPos().getY());
			moveVector.add(this.getCurrentTarget().getPos());
			moveVector.normalize();
			setNormalizedDir(moveVector);
		}

	}

	@Override
	public void reset() {
		super.reset();
		this.militaryRenderer.detachSelf();
		
		pattern=null;
		//touchArea.setPosition(XSceneManager.OUT_OF_SCENE, XSceneManager.OUT_OF_SCENE);

		PoolAnimManager.getManager().getBuildingRendererPool().free((BuildingRenderer) this.militaryRenderer);

		if(lifeBarreRenderer!=null) PoolAnimManager.getManager().getLifeBarRendererPool().free(lifeBarreRenderer);
		lifeBarreRenderer = null;
	}

	@Override
	public void notifyDestrution() {
		BuildingFactory.destroy(this);
	}

}
