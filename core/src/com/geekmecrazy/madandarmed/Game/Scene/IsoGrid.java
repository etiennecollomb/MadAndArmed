package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IsoShape;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.IA.IsoMapState;
import com.geekmecrazy.madandarmed.Renderer.IsoGridRenderer;

public class IsoGrid {

	private float cellWidth;

	private float cellHeight;
	
	private int width;
	private int height;
	
	private Scene scene;

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
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
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
		
        this.cellWidth = pCellWidth;
        this.cellHeight = pCellHeight;
        
        this.setWidth((int)(pScene.getWidth() / (pCellWidth) ));
        this.setHeight((int)(pScene.getHeight() / (pCellHeight/2f) ));
        
        this.scene = pScene;
        
        this.isoGridRenderer = null;
        
        this.isoMapState.init(this.getWidth(), this.getHeight());

    }

	/** from array (i,j) to screen position */
	public void place(Entity e, int i, int j){
		
		float posX = (j/2*2 == j)? this.getCellWidth()/2f : 0;
		float posY = 0;
		
		posX = posX + i*this.getCellWidth();
		posY = posY + j*this.getCellHeight()/2f;
		
		e.setPosition(posX, posY);
	}
	
	/** from screen position (x,y) to array position */
	public void placeFromPosition(IsoShape e, float pX, float pY){
		
		int j = (int) (pY / (this.getCellHeight()/2f));
		
		pX = (j/2*2 != j)? pX+this.getCellWidth()/2f : pX;
		int i = (int) (pX / this.getCellWidth());
		
		e.setGridPosX(i);
		e.setGridPosY(j);
		this.place(e, i, j);
		
	}

	
}
