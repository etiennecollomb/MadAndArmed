package com.geekmecrazy.madandarmed.Renderer;

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
		
		Building thisBuilding = ((Building)this.getMilitary());

		super.onUpdate();
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
