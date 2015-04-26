package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Scene.IsoGrid;

public class IsoGridRenderer extends Shape {
	
	/** Model */
    private IsoGrid isoGrid;
	
	// ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

	public IsoGrid getIsoGrid() {
		return isoGrid;
	}

	public void setIsoGrid(IsoGrid isoGrid) {
		this.isoGrid = isoGrid;
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
	
	public void init(final IsoGrid pGrid){
		super.init(0, 0, pGrid.getScene().getWidth(), pGrid.getScene().getHeight());
		
		pGrid.setIsoGridRenderer(this);
		this.setIsoGrid(isoGrid);
		this.setGridGraphics();
		
		this.setAlignment(Alignment.NONE);
		
	}
	
	private void setGridGraphics(){
		
		int numberOfColumns =  (int) (this.getWidth() / TextureType.ISOGRID.getWidth());
		int numberOfRows =  (int) (this.getHeight() / TextureType.ISOGRID.getHeight());
		
		for(int i=0; i<numberOfColumns; i++)
			for(int j=0; j<numberOfRows; j++){
				
				Sprite isoGrid = new Sprite();
				isoGrid.init(TextureType.ISOGRID);
				this.attachChild(isoGrid, Alignment.NONE);
				isoGrid.setPosition(i*TextureType.ISOGRID.getWidth(), j*TextureType.ISOGRID.getHeight());
			}
				
		
	}
	
}
