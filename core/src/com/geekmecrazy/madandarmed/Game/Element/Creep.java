package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Game.Factory.CreepFactory;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Renderer.CreepRenderer;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class Creep extends Vehicle {
    
	// ===========================================================
	// Init
	// ===========================================================
	public void init(CreepPattern creepPattern, float posX, float posY, float diameter, Life life, Team myTeam, Team ennemyTeam, CreepRenderer animatedMilitary){

		super.init(creepPattern, posX, posY, diameter, life, myTeam, ennemyTeam);
		this.militaryRenderer=animatedMilitary;
		((CreepRenderer)this.militaryRenderer).init(PoolAnimManager.getManager().getSpriteSheets().get(creepPattern.getAnimatedTextureType()), creepPattern, this);

        animatedMilitary.setAlignment(Entity.Alignment.CENTER_ON_ITSELF);
		FightScreen.getManager().getScene().attachChild(animatedMilitary);

        //on ne peut selectionner que les units de sa team
        if(creepPattern.getTeam() == CreepPattern.UnitTeam.TEAM1)
            FightScreen.getManager().getScene().registerTouchableShape(animatedMilitary);
		
		/*
		if(UnitType.AIR.equals(pattern.getUnitType()))GameManager.getManager().getScene().attachChild(animatedMilitary.getSupport(), LayerIndex.AIR);
		else GameManager.getManager().getScene().attachChild(animatedMilitary.getSupport(), LayerIndex.GAME);
		 */

		//this.lifeBarre = PoolManager.getLifeBarrePool().obtainPoolItem();
		//this.lifeBarre.init(0,-20, diameter, diameter);
		//animatedMilitary.getSupport().attachChild(lifeBarre.getLifeBarre());
	}

	

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    
	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public void notifyDestrution() {
		CreepFactory.destroy(this);
	}

	//si plus de vie
	@Override
	public void noMoreLife(){
		super.noMoreLife();

		//TODO : a refaire propre : PB DE PERF PLUS IL Y EN A
		//On add un tache de sang la ou le creep est mort
//		float posX = this.getPos().getX();
//		float posY = this.getPos().getY();
//		Sprite bloodOnFloor = new Sprite();
//		bloodOnFloor.init(TextureType.BLOOD_ON_FLOOR);
//		bloodOnFloor.setPosition(posX, posY);
//		bloodOnFloor.setZIndex(0);
//		FightScreen.getManager().getScene().attachChild(bloodOnFloor);

	}

	// ===========================================================
	// Run item
	// ===========================================================
	@Override
	public void onUpdateNextState(){
		super.onUpdateNextState();
		
		this.getAttackBehavior().setAttacking( !isMoving() && this.getAttackBehavior().isAttacking() );
	}


	// ===========================================================
	// Recycle
	// ===========================================================
	@Override
	public void reset() {
		super.reset();
		
		PoolAnimManager.getManager().getCreepRendererPool().free((CreepRenderer) this.militaryRenderer);
	}

}