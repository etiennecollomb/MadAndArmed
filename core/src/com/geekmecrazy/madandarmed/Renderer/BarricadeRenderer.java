package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
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
		this.setOrientation();
        this.setZIndex(GlobalManager.ZINDEXMAXVALUE - (int)this.getY());
		super.onUpdate();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	public void init(final SpriteSheet pSpriteSheet, final IsoGrid grid){
		 super.init(pSpriteSheet, grid);
	}
	

	/** Set Barricade Frame regarding state of neighbor cell */
	public void setOrientation(){
		
		if(    this.getGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
			&& this.getGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) 
			&& this.getGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
			&& this.getGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(10);
		}
		
		else if( this.getGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(12);
			
		}else if( this.getGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) 
				&& this.getGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(13);
			
		}else if( this.getGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(14);
			
		}else if( this.getGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) 
				&& this.getGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(15);
		}
		
		else if( this.getGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(4);
			
		}else if( this.getGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(5);
			
		}else if( this.getGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(6);
			
		}else if( this.getGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(7);
			
		}else if( this.getGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(8);
			
		}else if( this.getGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())
				&& this.getGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY()) ){
			this.getBuildingSprite().setCurrentFrame(9);
		}
		
		else if(this.getGrid().getIsoMapState().isLeftOccupied(this.getGridPosX(), this.getGridPosY())){
			this.getBuildingSprite().setCurrentFrame(0);
		}else if(this.getGrid().getIsoMapState().isUpOccupied(this.getGridPosX(), this.getGridPosY())){
			this.getBuildingSprite().setCurrentFrame(1);
		}else if(this.getGrid().getIsoMapState().isRightOccupied(this.getGridPosX(), this.getGridPosY())){
			this.getBuildingSprite().setCurrentFrame(2);
		}else if(this.getGrid().getIsoMapState().isDownOccupied(this.getGridPosX(), this.getGridPosY())){
			this.getBuildingSprite().setCurrentFrame(3);
		}
		
		
		else{
			this.getBuildingSprite().setCurrentFrame(0);
		}

	}

	
}
