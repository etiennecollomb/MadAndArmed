package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Renderer.BuildingRenderer;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class Turret extends Building {

	private Sprite floor;

	/** pointing to target */
	private Vector2d directionVector = new Vector2d();
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public void init(float posX, float posY, float width, float height, BuildingPattern buildingPattern, Life life, Team myTeam, Team ennemyTeam, BuildingRenderer animatedMilitary ) {
		super.init(posX, posY, width, height, buildingPattern, life, myTeam, ennemyTeam, animatedMilitary);
		
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
	
	// ===========================================================
	// Methods
	// ===========================================================

}
