package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Scene.IsoGrid;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;

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

		this.getMilitary().setPos(this.getX(), this.getY()); //Update the position of the model (can be moved)
		
		this.setOrientation();
        this.setZIndex(GlobalManager.ZINDEXMAXVALUE - (int)this.getY());
        
		super.onUpdate();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	public void init(final SpriteSheet pSpriteSheet, final BuildingPattern pBuildingPattern, final Building pBuilding, final IsoGrid isoGrid){
		 super.init(pSpriteSheet, pBuildingPattern, pBuilding, isoGrid);
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
