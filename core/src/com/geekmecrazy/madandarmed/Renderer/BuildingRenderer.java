package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;


public class BuildingRenderer extends MilitaryRenderer {

	private BuildingPattern mBuildingPattern;
		
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public BuildingRenderer(){
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public BuildingPattern getBuildingPattern() {
		return mBuildingPattern;
	}

	public void setBuildingPattern(final BuildingPattern pBuildingPattern) {
		this.mBuildingPattern = pBuildingPattern;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void reset() {
		super.reset();

		setBuildingPattern(null);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final SpriteSheet pSpriteSheet, final BuildingPattern pBuildingPattern, final Building pBuilding){
		super.init(pSpriteSheet, pBuilding);
		
		this.setBuildingPattern(pBuildingPattern);
	}


}

