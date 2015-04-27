package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Renderer.OrthoGridRenderer;

public class OrthoGrid {
    private float cellSizeX;
	private float cellSizeY;
    private int cols;
	private int rows;
	
	private OrthoGridRenderer gridRenderer;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public float getCellSizeX() {
		return cellSizeX;
	}

	public void setCellSizeX(float cellSizeX) {
		this.cellSizeX = cellSizeX;
	}

	public float getCellSizeY() {
		return cellSizeY;
	}

	public void setCellSizeY(float cellSizeY) {
		this.cellSizeY = cellSizeY;
	}

    public int getCols() {
		return cols;
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
        this.cellSizeX = pCellWidth;
        this.cellSizeY = pCellHeight;
        this.cols = (int) (scene.getWidth() / GlobalManager.BIG_NODESIZE) +1;
        this.rows = (int) (scene.getHeight() / GlobalManager.BIG_NODESIZE) +1;
        
        this.gridRenderer = null;

    }

    public void snap(Entity e,int row,int col){
        e.setPosition(row*cellSizeX + cellSizeX/2f - e.getWidth()/2f,col*cellSizeY + cellSizeY/2f - e.getHeight()/2f);
    }

    public float widthFor(int val){
        return val*cellSizeX;
    }

    public float heightFor(int val){
        return val*cellSizeY;
    }
    




}
