package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Scene.IsoGrid;

public class SpawnBuildingRenderer extends IsoBuildingRenderer {
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SpawnBuildingRenderer(){
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
		super.onUpdate();
		
		Building thisBuilding = ((Building)this.getMilitary());
		
		thisBuilding.setPos(this.getSceneX(), this.getSceneY()); //Update the position of the model (can be moved)
        this.setZIndex(GlobalManager.ZINDEXMAXVALUE - (int)this.getY());
	}

	
	// ===========================================================
	// Methods
	// ===========================================================
	
	public void init(final SpriteSheet pSpriteSheet, final Building pBuilding, final IsoGrid isoGrid){
		 super.init(pSpriteSheet, pBuilding, isoGrid);
	}


	
}
