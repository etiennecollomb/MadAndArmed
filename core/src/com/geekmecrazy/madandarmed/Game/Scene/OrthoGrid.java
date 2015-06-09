package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Renderer.OrthoGridRenderer;

public class OrthoGrid extends Grid {
	
	private OrthoGridRenderer gridRenderer;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

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
        this.setCellWidth(pCellWidth);
        this.setCellHeight(pCellHeight);
        this.setWidth( (int) (scene.getWidth() / GlobalManager.BIG_NODESIZE) +1);
        this.setHeight( (int) (scene.getHeight() / GlobalManager.BIG_NODESIZE) +1);
        
        this.gridRenderer = null;
    }

    public float widthFor(int val){
        return val*this.getCellWidth();
    }

    public float heightFor(int val){
        return val*this.getCellHeight();
    }

    public void place(Entity e, int row, int col){
        e.setPosition(row*this.getCellWidth() + this.getCellWidth()/2f - e.getWidth()/2f,col*this.getCellHeight() + this.getCellHeight()/2f - e.getHeight()/2f);
    }

    public void placeFromPosition(Entity e, float pX, float pY){
    	e.setPosition(Math.round((pX)/this.getCellWidth())*this.getCellWidth(), Math.round((pY)/this.getCellHeight())*this.getCellHeight());
    }
    
    public int convertToGridPositionX(float pX){
		return (int)(pX/this.getCellWidth());
	}
	
	public int convertToGridPositionY(float pY){
		return (int)(pY/this.getCellHeight());
	}


}
