package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Missile;


public class MissileRenderer extends VehicleRenderer {
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public MissileRenderer(){
		super();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(){
		
		Missile thisMissile = ((Missile)this.getMilitary());

		this.setPosition(thisMissile.getPos().getX(), thisMissile.getPos().getY());
		this.setZIndex(GlobalManager.ZINDEXMAXVALUE - (int)thisMissile.getPos().getY());
		
		if(thisMissile.isAlive()){
			this.setCurrentFrame(0, 0);
		}
		
		//after because OffSet has been modified
		super.onUpdate();
	}
	
	@Override
	public void reset() {
		super.reset();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final  SpriteSheet pSpriteSheet, final Missile pMissile){
		super.init(pSpriteSheet, pMissile);
	}


}
