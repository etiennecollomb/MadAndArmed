package com.geekmecrazy.madandarmed.Entity;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.IsoGrid;
import com.geekmecrazy.madandarmed.IA.IsoMapState.Type;

public class IsoShape extends Shape {
	

    public static enum IsoShapeState {
        FOCUSED,
        UNFOCUSED
    }
    
    private IsoShapeState state;
    
    private Type type;
    
    private float diffX;
    private float diffY;
    
    private int gridPosX;
	private int gridPosY;
    
    /** associated to a IsoGrid */
    private IsoGrid grid;
    
    // ===========================================================
    // Constructors
    // ===========================================================

    public IsoShape(){
    	super();
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
	
    public int getGridPosX() {
		return gridPosX;
	}

	public void setGridPosX(int gridPosX) {
		this.gridPosX = gridPosX;
	}

	public int getGridPosY() {
		return gridPosY;
	}

	public void setGridPosY(int gridPosY) {
		this.gridPosY = gridPosY;
	}

    public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
	
	@Override
	public boolean contains(final float pX, final float pY){
		/** touch bound */
		return ( (this.grid.convertToGridPositionX(pX, pY) == gridPosX) && (this.grid.convertToGridPositionY(pY) == gridPosY) ) ;
	}
	
	@Override
	public void onTouchDownEvent(final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		//System.out.println("viewportWidth vaut "+GlobalManager.camera.viewportWidth+" et viewportHeight vaut "+GlobalManager.camera.viewportHeight);
        diffX = pTouchAreaLocalX - this.getX();
        diffY = pTouchAreaLocalY - this.getY();
	};
	
	@Override
	public void onPanEvent(final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		
		if(isFocused()){
			
			/** pour etre visible et non sous le doigt */
			float decallageX = -100;
			float decallageY = 100;
			
            float effectiveViewportWidth = GlobalManager.camera.viewportWidth * GlobalManager.camera.zoom;
            float effectiveViewportHeight = GlobalManager.camera.viewportHeight * GlobalManager.camera.zoom;

            /**OFF of gridMapState */
            this.getGrid().getIsoMapState().remove(this, this.getGridPosX(), this.getGridPosY());
            /** New Position */
            this.grid.placeFromPosition(this, pTouchAreaLocalX-diffX + decallageX, pTouchAreaLocalY-diffY + decallageY);
            /**Put on gridMapState */
            this.getGrid().getIsoMapState().add(this, this.getGridPosX(), this.getGridPosY());
            
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
            
            this.afterOnPanEvent();
        }
		
	};

    
    // ===========================================================
    // Methods
    // ===========================================================

	public void init(final float pX, final float pY, final float pWidth, final float pHeight, final IsoGrid grid){
		super.init(pX, pY, pWidth, pHeight);
		
		gridPosX = 0;
		gridPosY = 0;

    	this.state = IsoShapeState.UNFOCUSED;
    	
    	this.setGrid(grid);
	}
	
    public boolean isFocused(){
        return this.getState() == IsoShapeState.FOCUSED;
    }

    public void setFocusState(){}
    
    public void afterOnPanEvent(){}
    
}
