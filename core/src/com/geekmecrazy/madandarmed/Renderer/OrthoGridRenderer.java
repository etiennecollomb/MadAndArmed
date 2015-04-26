package com.geekmecrazy.madandarmed.Renderer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.geekmecrazy.madandarmed.Core.TextureBuilder;
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
		super.init(0, 0, pGrid.getCellSizeX()*pGrid.getCols(), pGrid.getCellSizeY()*pGrid.getRows());
		
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
