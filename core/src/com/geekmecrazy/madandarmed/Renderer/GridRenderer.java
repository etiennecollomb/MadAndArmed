package com.geekmecrazy.madandarmed.Renderer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.geekmecrazy.madandarmed.Core.TextureBuilder;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Scene.Grid;


public class GridRenderer extends Shape {
	
	/** gray grid */
    private ArrayList<Sprite> gridGraphics;
    
    /** Model */
    private Grid grid;
    
    // ===========================================================
    // Constructors
    // ===========================================================
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================
    
	public ArrayList<Sprite> getGridGraphics() {
		return gridGraphics;
	}

	public void setGridGraphics(ArrayList<Sprite> gridGraphics) {
		this.gridGraphics = gridGraphics;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public GridRenderer(){
		super();
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
	
	public void init(final Grid pGrid){
		super.init(0, 0, pGrid.getCellSizeX()*pGrid.getCols(), pGrid.getCellSizeY()*pGrid.getRows());
		
		pGrid.setGridRenderer(this);
		this.setGrid(pGrid);
		this.setGridGraphics();
		
		this.setAlignment(Alignment.NONE);
		
	}
	
	private void setGridGraphics(){
		
		boolean colorSwap = true;
		boolean lineSwap = true;
		float gray1 = 0.2f;
		float gray2 = 0.6f;
		float grayDelta = 0.0f;
		
		TextureBuilder textureBuilder = new TextureBuilder();
        textureBuilder.init((int)this.getWidth(), (int)this.getHeight()); //size
		
        Pixmap pixmap = new Pixmap((int)this.grid.getCellSizeX(), (int)this.grid.getCellSizeY(), Format.RGBA8888);
        
		for(int i = 0 ; i < this.grid.getRows() ; i++){
			
			colorSwap = lineSwap;
			
            for(int j = 0 ; j < this.grid.getCols() ; j++){
            	
            	//Color
            	float r = colorSwap?gray1:gray2;
                r = r + (float)Math.random() * grayDelta;
        		pixmap.setColor(r, r, r, 0.5f);
        		pixmap.fill();
                
        		//Position
        		int posX = (int)( j*this.grid.getCellSizeX() );
        		int posY = (int)( i*this.grid.getCellSizeY() );
                
        		textureBuilder.getFinalPixmap().drawPixmap(pixmap, posX, posY) ;
                
                colorSwap = colorSwap? false:true;
            }
            
            lineSwap = lineSwap? false:true;
        }
        
        /*
        Pixmap pixmap = new Pixmap((int)2000, (int)2000, Format.RGBA8888);
        pixmap.setColor(0,0,1,0.5f);
        pixmap.fillRectangle(0, 0, pixmap.getWidth(),  pixmap.getHeight());
        textureBuilder.getFinalPixmap().drawPixmap(pixmap, 0, 0) ;
		*/
        
		pixmap.dispose();
		
		this.setGridGraphics( textureBuilder.splitInSprites() );
		TextureBuilder.attachSprites(this, this.getGridGraphics());
		
		System.out.println("#####SIZE1 "+textureBuilder.getFinalPixmap().getWidth()+" "+textureBuilder.getFinalPixmap().getHeight());
		System.out.println("#####SIZE2 "+this.getGridGraphics().size());
	}

	
}
