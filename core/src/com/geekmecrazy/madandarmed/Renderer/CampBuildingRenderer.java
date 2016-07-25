package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.Loader.PatternManager;
import com.geekmecrazy.madandarmed.Tools.GraphicalTools;


public class CampBuildingRenderer extends BuildingRenderer {
	
	private int animCurrentFrame; //Animation du building (fan..etc..)
		
	// ===========================================================
	// Constructors
	// ===========================================================

	public CampBuildingRenderer(){
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getAnimCurrentFrame() {
		return animCurrentFrame;
	}

	public void setAnimCurrentFrame(int animCurrentFrame) {
		this.animCurrentFrame = animCurrentFrame;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public void setDeadPattern(){

		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_128PX, 0f, 0f, 0);

		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_64PX, 10f, 10f, 5);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_64PX, -10f, 10f, 5);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_64PX, 10f, -10f, 5);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_64PX, -10f, -10f, 5);
		
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_64PX, 15f, 0f, 8);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_64PX, 0f, 15f, 8);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_64PX, -15f, 0f, 8);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_64PX, 0f, -15f, 8);
		
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, 20f, -10f, 10);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, -10f, -20f, 12);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, 0f, 10f, 14);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, 15f, 15f, 16);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, -5f, 5f, 18);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, 5f, -15f, 20);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, -20f, 10f, 22);
		
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, -20f, 10f, 15);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, -10f, 20f, 17);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, 0f, -20f, 19);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, -15f, -15f, 21);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, 5f, -5f, 23);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, -5f, 15f, 25);
		addDeadActionRenderer(AnimatedTextureType.BUILDING_DEATH_32PX, 20f, -10f, 27);
	}


	@Override
	public void onUpdate(){

		Building thisBuilding = ((Building)this.getMilitary());
		
		this.setPosition(this.getMilitary().getPos().getX(), this.getMilitary().getPos().getY());
		this.setZIndex(GlobalManager.ZINDEXMAXVALUE - (int)this.getMilitary().getPos().getY());

		if(thisBuilding.isAlive()){
			this.setAnimCurrentFrame(this.getAnimCurrentFrame()+1);
			if(this.getAnimCurrentFrame() >= GraphicalTools.NB_ORIENTATION)
				this.setAnimCurrentFrame(0);
			this.setCurrentFrame(0, this.getAnimCurrentFrame());
		}
		
		//after because OffSet has been modified
		super.onUpdate();
	}
	
	@Override
	public void reset() {
		super.reset();

		this.setAnimCurrentFrame(0);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final SpriteSheet pSpriteSheet, final Building pBuilding){
		super.init(pSpriteSheet, pBuilding);
		
		this.setAnimCurrentFrame(0);
	}


}

