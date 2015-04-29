package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Renderer.OrthoGridRenderer;

public class OrthoGrid {
    private float cellWidth;
	private float cellHeight;
    private int cols;
	private int rows;
	
	private OrthoGridRenderer gridRenderer;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public int getCols() {
		return cols;
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

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
    public OrthoGridRenderer getGridRenderer() {
		return gridRenderer;
	}


	public void setGridRenderer(OrthoGridRenderer gridRenderer) {
		this.gridRenderer = gridRenderer;
	}

	
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    
    
    // ===========================================================
    // Methods
    // ===========================================================

    public void init(final float pCellWidth, final float pCellHeight, Scene scene){
        this.cellWidth = pCellWidth;
        this.cellHeight = pCellHeight;
        this.cols = (int) (scene.getWidth() / GlobalManager.BIG_NODESIZE) +1;
        this.rows = (int) (scene.getHeight() / GlobalManager.BIG_NODESIZE) +1;
        
        this.gridRenderer = null;

    }

    public float widthFor(int val){
        return val*cellWidth;
    }

    public float heightFor(int val){
        return val*cellHeight;
    }

    public void place(Entity e, int row, int col){
        e.setPosition(row*cellWidth + cellWidth/2f - e.getWidth()/2f,col*cellHeight + cellHeight/2f - e.getHeight()/2f);
    }

    public void placeFromPosition(Entity e, float pX, float pY){
    	e.setPosition(Math.round((pX)/this.getCellWidth())*this.getCellWidth(), Math.round((pY)/this.getCellHeight())*this.getCellHeight());
    }
    



}
