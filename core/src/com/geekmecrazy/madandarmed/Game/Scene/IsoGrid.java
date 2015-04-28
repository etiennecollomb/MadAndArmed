package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Renderer.IsoGridRenderer;

public class IsoGrid {

	private float cellWidth;

	private float cellHeight;
	
	private Scene scene;

	private IsoGridRenderer isoGridRenderer;
	
	// ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
	
	public IsoGridRenderer getIsoGridRenderer() {
		return isoGridRenderer;
	}

	public void setIsoGridRenderer(IsoGridRenderer isoGridRenderer) {
		this.isoGridRenderer = isoGridRenderer;
	}

	public float getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(float cellWidth) {
		this.cellWidth = cellWidth;
	}

	public float getCellHeight() {
		return cellHeight;
	}

	public void setCellHeight(float cellHeight) {
		this.cellHeight = cellHeight;
	}
	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
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
		
        this.cellWidth = pCellWidth;
        this.cellHeight = pCellHeight;
        
        this.scene = pScene;
        
        this.isoGridRenderer = null;

    }

	/** from array (i,j) to screen position */
	public void place(Entity e, int i, int j){
		
		float posX = (j/2*2 == j)? this.getCellWidth()/2f : 0;
		float posY = 0;
		
		posX = posX + i*this.getCellWidth();
		posY = posY + j*this.getCellHeight()/2f;
		
		e.setPosition(posX, posY);
	}
	
	/** from screen position (x,y) to screen position */
	public void placeFromPosition(Entity e, float pX, float pY){
		
		int j = (int) (pY / (this.getCellHeight()/2f));
		
		pX = (j/2*2 != j)? pX+this.getCellWidth()/2f : pX;
		int i = (int) (pX / this.getCellWidth());
		
		this.place(e, i, j);
		
	}
	
}
