package com.geekmecrazy.madandarmed.Entity;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.IsoGrid;

public class IsoShape extends Shape {
	

    public static enum IsoShapeState {
        FOCUSED,
        UNFOCUSED
    }

    private IsoShapeState state;
    
    private float diffX;
    private float diffY;
    
    /** associated to a IsoGrid */
    private IsoGrid grid;
    
    // ===========================================================
    // Constructors
    // ===========================================================

    public IsoShape(){
    	super();
    	
    	this.state = IsoShapeState.UNFOCUSED;
    }
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================
    
    public IsoShapeState getState() {
		return state;
	}

	public void setState(IsoShapeState state) {
		this.state = state;
	}

	public IsoGrid getGrid() {
		return grid;
	}

	public void setGrid(IsoGrid grid) {
		this.grid = grid;
	}
	
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

	public void onTouchDownEvent(final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		//System.out.println("viewportWidth vaut "+GlobalManager.camera.viewportWidth+" et viewportHeight vaut "+GlobalManager.camera.viewportHeight);
        diffX = pTouchAreaLocalX - this.getX();
        diffY = pTouchAreaLocalY - this.getY();
	};
	
	public void onPanEvent(final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		
		if(isFocused()){
			
            float effectiveViewportWidth = GlobalManager.camera.viewportWidth * GlobalManager.camera.zoom;
            float effectiveViewportHeight = GlobalManager.camera.viewportHeight * GlobalManager.camera.zoom;

            this.grid.placeFromPosition(this, pTouchAreaLocalX-diffX, pTouchAreaLocalY-diffY);
            
            if(this.getX() < 0){
                this.setPosition(0,this.getY());
            }
            else if(this.getX() + this.getWidth() > GlobalManager.MAP_FIGHT_WIDTH){
                this.setPosition(GlobalManager.MAP_FIGHT_WIDTH - this.getWidth(),this.getY());
            }
            else if(this.getX() < GlobalManager.camera.position.x - effectiveViewportWidth/2f){
                GlobalManager.camera.position.x = this.getX() + effectiveViewportWidth/2f;
            }
            else if(this.getX() + this.getWidth() > GlobalManager.camera.position.x + effectiveViewportWidth/2f){
                GlobalManager.camera.position.x = this.getX() + this.getWidth() - effectiveViewportWidth/2f;
            }

            if(this.getY() < 0){
                this.setPosition(this.getX(),0);
            }
            else if(this.getY() + this.getHeight() > GlobalManager.MAP_FIGHT_HEIGHT){
                this.setPosition(this.getX(),GlobalManager.MAP_FIGHT_HEIGHT - this.getHeight());
            }
            else if(this.getY() < (GlobalManager.camera.position.y - effectiveViewportHeight/2f)){
                GlobalManager.camera.position.y = this.getY() + effectiveViewportHeight/2f;
            }
            else if(this.getY() + this.getHeight() > GlobalManager.camera.position.y + effectiveViewportHeight/2f){
                GlobalManager.camera.position.y = this.getY() + this.getHeight() - effectiveViewportHeight/2f;
            }
        }
		
	};

    
    // ===========================================================
    // Methods
    // ===========================================================

    public boolean isFocused(){
        return this.getState() == IsoShapeState.FOCUSED;
    }
    
	public void setFocusState(){}

}
