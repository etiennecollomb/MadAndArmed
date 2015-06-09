package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Scene.IsoGrid;

public class BarricadeRenderer extends IsoBuildingRenderer {
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public BarricadeRenderer(){
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
		
		Building thisBuilding = ((Building)this.getMilitary());
		
		if(thisBuilding.isAlive()){
			thisBuilding.setPos(this.getX(), this.getY()); //Update the position of the model (can be moved)
			
			this.setOrientation();
	        this.setZIndex(GlobalManager.ZINDEXMAXVALUE - (int)this.getY());
		}
		else{
			System.out.println("VISIBILITY = " + this.isVisible());
		}
        
		super.onUpdate();
	}
	
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
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	public void init(final SpriteSheet pSpriteSheet, final Building pBuilding, final IsoGrid isoGrid){
		 super.init(pSpriteSheet, pBuilding, isoGrid);
	}


	/** Set Barricade Frame regarding state of neighbor cell */
	public void setOrientation(){
		
		if( this.getIsoGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
			&& this.getIsoGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) 
			&& this.getIsoGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
			&& this.getIsoGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(10);
		}
		
		else if( this.getIsoGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(12);
			
		}else if( this.getIsoGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) 
				&& this.getIsoGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(13);
			
		}else if( this.getIsoGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(14);
			
		}else if( this.getIsoGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) 
				&& this.getIsoGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(15);
		}
		
		else if( this.getIsoGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(4);
			
		}else if( this.getIsoGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(5);
			
		}else if( this.getIsoGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(6);
			
		}else if( this.getIsoGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(7);
			
		}else if( this.getIsoGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(8);
			
		}else if( this.getIsoGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getIsoGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.setCurrentFrame(9);
		}
		
		else if(this.getIsoGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())){
			this.setCurrentFrame(0);
		}else if(this.getIsoGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())){
			this.setCurrentFrame(1);
		}else if(this.getIsoGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY())){
			this.setCurrentFrame(2);
		}else if(this.getIsoGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY())){
			this.setCurrentFrame(3);
		}
		
		
		else{
			this.setCurrentFrame(0);
		}

	}

	
}
