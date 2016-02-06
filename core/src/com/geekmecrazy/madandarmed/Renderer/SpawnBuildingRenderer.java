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
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	public void init(final SpriteSheet pSpriteSheet, final Building pBuilding, final IsoGrid isoGrid){
		 super.init(pSpriteSheet, pBuilding, isoGrid);
	}


	
}
