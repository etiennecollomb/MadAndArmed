package com.geekmecrazy.madandarmed.Entity;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.OrthoGrid;

public class OrthoShape extends Shape {
	

    public static enum OrthoShapeState {
        FOCUSED,
        UNFOCUSED
    }

    private OrthoShapeState state;
    
    private float diffX;
    private float diffY;
    
    /** associated to a OrthoGrid */
    private OrthoGrid grid;
    
    // ===========================================================
    // Constructors
    // ===========================================================

    public OrthoShape(){
    	super();
    	
    	this.state = OrthoShapeState.UNFOCUSED;
    }
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================
    
    public OrthoShapeState getState() {
		return state;
	}

	public void setState(OrthoShapeState state) {
		this.state = state;
	}

	public OrthoGrid getGrid() {
		return grid;
	}

	public void setGrid(OrthoGrid grid) {
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

            this.setPosition(Math.round((pTouchAreaLocalX-diffX)/this.getGrid().getCellWidth())*this.getGrid().getCellWidth(), Math.round((pTouchAreaLocalY-diffY)/this.getGrid().getCellHeight())*this.getGrid().getCellHeight());
            
            if(this.getX() < 0){
                this.setPosition(0,this.getY());
            }
            else if(this.getX() + this.getWidth() > GlobalManager.HQ_SCENE_WIDTH){
                this.setPosition(GlobalManager.HQ_SCENE_WIDTH - this.getWidth(),this.getY());
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
            else if(this.getY() + this.getHeight() > GlobalManager.HQ_SCENE_HEIGHT){
                this.setPosition(this.getX(),GlobalManager.HQ_SCENE_HEIGHT - this.getHeight());
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
        return this.getState() == OrthoShapeState.FOCUSED;
    }
    
	public void setFocusState(){}

}
