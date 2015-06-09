package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Scene.OrthoGrid;


public class OrthoGridRenderer extends Shape {
    
    /** Model */
    private OrthoGrid grid;
    
    // ===========================================================
    // Constructors
    // ===========================================================
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================

	public OrthoGrid getGrid() {
		return grid;
	}

	public void setGrid(OrthoGrid grid) {
		this.grid = grid;
	}

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

	@Override
	public void reset(){
		super.reset();
	}
    
    // ===========================================================
    // Methods
    // ===========================================================
	
	public void init(final OrthoGrid pGrid){
		super.init(0, 0, pGrid.getCellWidth()*pGrid.getWidth(), pGrid.getCellHeight()*pGrid.getHeight());
		
		pGrid.setGridRenderer(this);
		this.setGrid(pGrid);
		this.setGridGraphics();
		
		this.setAlignment(Alignment.NONE);
		
	}
	
	private void setGridGraphics(){
		
		Sprite orthoGrid = new Sprite();
		orthoGrid.init(TextureType.ORTHOGRID);
		this.attachChild(orthoGrid, Alignment.NONE);
		
	}

	
}
