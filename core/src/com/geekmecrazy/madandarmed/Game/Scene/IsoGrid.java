package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.IA.IsoMapState;
import com.geekmecrazy.madandarmed.Renderer.IsoGridRenderer;

public class IsoGrid extends Grid {

	private IsoGridRenderer isoGridRenderer;
	
	/** state of the grid */
	private IsoMapState isoMapState;
	
	// ===========================================================
    // Constructors
    // ===========================================================

	public IsoGrid(){
		isoMapState = new IsoMapState();
	}
	
    // ===========================================================
    // Getter & Setter
    // ===========================================================
	
	public IsoGridRenderer getIsoGridRenderer() {
		return isoGridRenderer;
	}

	public void setIsoGridRenderer(IsoGridRenderer isoGridRenderer) {
		this.isoGridRenderer = isoGridRenderer;
	}
	
	public IsoMapState getIsoMapState() {
		return isoMapState;
	}

	public void setIsoMapState(IsoMapState isoMapState) {
		this.isoMapState = isoMapState;
	}

	// ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    
    // ===========================================================
    // Methods
    // ===========================================================
	
	/** Ortho to Iso !
    x = (j * tile_width/2) + (i * tile_width/2)
    y = (i * tile_height/2) - (j * tile_height/2)
    */
	
	/** Iso to Ortho !
    i = ( (x / tile_width/2) + (y / tile_height/2) )/2
    j = ( (x / tile_width/2) - (y / tile_height/2) )/2
    */
	
	public void init(final float pCellWidth, final float pCellHeight, final Scene pScene){
		
        this.setCellWidth(pCellWidth);
        this.setCellHeight(pCellHeight);
        
        this.setWidth((int)(pScene.getWidth() / (pCellWidth) ));
        this.setHeight((int)(pScene.getHeight() / (pCellHeight/2f) ));
        
        this.setScene(pScene);
        
        this.isoGridRenderer = null;
        
        this.isoMapState.init(this.getWidth(), this.getHeight());

    }

	/** from array (i,j) to screen position */
	public void place(Shape e, int i, int j){
		
		float posX = (j/2*2 == j)? this.getCellWidth()/2f : 0;
		float posY = 0;
		
		posX = posX + i*this.getCellWidth();
		posY = posY + j*this.getCellHeight()/2f;
		
		e.setGridPosX(i);
		e.setGridPosY(j);
		
		e.setPosition(posX, posY);
	}
	
	/** from screen position (x,y) to array position */
	public void placeFromPosition(Shape e, float pX, float pY){
		
		int j = (int) (pY / (this.getCellHeight()/2f));
		
		pX = (j/2*2 != j)? pX+this.getCellWidth()/2f : pX;
		int i = (int) (pX / this.getCellWidth());

		this.place(e, i, j);
		
	}
	
	public int convertToGridPositionX(float pX, float pY){
		int j = (int) (pY / (this.getCellHeight()/2f));
		pX = (j/2*2 != j)? pX+this.getCellWidth()/2f : pX;
		return (int) (pX / this.getCellWidth());
	}
	
	public int convertToGridPositionY(float pY){
		return (int) (pY / (this.getCellHeight()/2f));
	}

	
}
