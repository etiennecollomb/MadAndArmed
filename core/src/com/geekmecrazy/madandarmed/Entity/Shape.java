package com.geekmecrazy.madandarmed.Entity;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.IsoGrid;
import com.geekmecrazy.madandarmed.Game.Scene.OrthoGrid;
import com.geekmecrazy.madandarmed.IA.IsoMapState.Type;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector.GestureType;
import com.geekmecrazy.madandarmed.Input.TouchData;
import com.badlogic.gdx.graphics.Color;

public class Shape extends Entity implements IColor, ITouchable {

	private Color mColor;

	/** Used for Touch Event */
	public static enum ShapeState {
		FOCUSED,
		UNFOCUSED
	}
	private ShapeState state;

	/*
	 * Can be Isometric , ie. IsoShape
	 * or Orthometric, ie. OrthoShape
	 * Setting up in init(...)
	 */

	/** IsoShape */
	private boolean isIsoShape;
	private IsoGrid isoGrid;

	/** OrthoShape */
	private boolean isOrthoShape;
	private OrthoGrid orthoGrid;

	/** Common to IsoShape and OrthoShape */
    private Type type;
    
	private float diffX;
	private float diffY;

	private int gridPosX;
	private int gridPosY;


	// ===========================================================
	// Constructors
	// ===========================================================

	public Shape() {
		super();

		this.mColor = new Color();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Color getmColor() {
		return mColor;
	}

	public void setmColor(Color mColor) {
		this.mColor = mColor;
	}

	public ShapeState getState() {
		return state;
	}

	public void setState(ShapeState state) {
		this.state = state;
	}

	public boolean isIsoShape() {
		return isIsoShape;
	}

	public void setIsoShape(boolean isIsoShape) {
		this.isIsoShape = isIsoShape;
	}

	public IsoGrid getIsoGrid() {
		return isoGrid;
	}

	public void setIsoGrid(IsoGrid isoGrid) {
		this.isoGrid = isoGrid;
	}

	public boolean isOrthoShape() {
		return isOrthoShape;
	}

	public void setOrthoShape(boolean isOrthoShape) {
		this.isOrthoShape = isOrthoShape;
	}

	public OrthoGrid getOrthoGrid() {
		return orthoGrid;
	}

	public void setOrthoGrid(OrthoGrid orthoGrid) {
		this.orthoGrid = orthoGrid;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public float getDiffX() {
		return diffX;
	}

	public void setDiffX(float diffX) {
		this.diffX = diffX;
	}

	public float getDiffY() {
		return diffY;
	}

	public void setDiffY(float diffY) {
		this.diffY = diffY;
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

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    
	/* for IsoShape Type :
	 * you should use setPosition(final float pX, final float pY) instead of setX(final float pX)
	 * as gridPosX depend of pY to....
	 */
	public void setX(final float pX) {
		super.setX(pX);
        
        /** IsoShape */
		if(this.isIsoShape())
			this.setGridPosX(this.isoGrid.convertToGridPositionX(pX, this.getY()));
		/** OrthoShape */
		else if(this.isOrthoShape())
			this.setGridPosX(this.orthoGrid.convertToGridPositionX(pX));
    }

	public void setY(final float pY) {
		super.setY(pY);
        
        /** IsoShape */
		if(this.isIsoShape())
			this.setGridPosY(this.isoGrid.convertToGridPositionY(pY));
		/** OrthoShape */
		else if(this.isOrthoShape())
			this.setGridPosY(this.orthoGrid.convertToGridPositionY(pY));
    }
	
	
	@Override
    public void setPosition(final float pX, final float pY) {
		super.setX(pX);
        super.setY(pY);
        
        /** IsoShape */
		if(this.isIsoShape()){
			this.setGridPosX(this.isoGrid.convertToGridPositionX(pX, pY));
			this.setGridPosY(this.isoGrid.convertToGridPositionY(pY));
		}
		/** OrthoShape */
		else if(this.isOrthoShape()){
			this.setGridPosX(this.orthoGrid.convertToGridPositionX(pX));
			this.setGridPosY(this.orthoGrid.convertToGridPositionY(pY));
		}
    }
	
	@Override
	public void onTouch() {
		switch(TouchData.gestureType){
		case TOUCHDOWN:
			this.onTouchDownEvent();
			break;
		case TOUCHUP:
			this.onTouchUpEvent();
			break;
		case LONGPRESS:
			this.onLongPressEvent();
			break;
		case TAP:
			this.onTapEvent();
			break;
		case PAN:
			this.onPanEvent();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean contains(final float pX, final float pY){

		/** IsoShape */
		if(this.isIsoShape()){
			/** Touch in Grid Cases ? */
			return ( (this.isoGrid.convertToGridPositionX(pX, pY) == gridPosX) && (this.isoGrid.convertToGridPositionY(pY) == gridPosY) ) ;
		}
		/** OrthoShape */
		else if(this.isOrthoShape()){
			/** Touch in Grid Cases ? */
			return ( (this.orthoGrid.convertToGridPositionX(pX) == gridPosX) && (this.orthoGrid.convertToGridPositionY(pY) == gridPosY) ) ;
		}
		/** Normal case */
		else{
			/** touch bound */
			float X1 = this.getSceneX() + this.getCenterX() - this.getWidth()*this.getSceneScaleX()/2f ;
			float Y1 = this.getSceneY() + this.getCenterY() - this.getHeight()*this.getSceneScaleY()/2f ;
			float X2 = X1 + this.getWidth() * this.getSceneScaleX();
			float Y2 = Y1 + this.getHeight() * this.getSceneScaleY();

			return (pX >= X1 && pX <= X2 && pY >= Y1 && pY <= Y2);
		}
	}

	@Override
	public Color getColor() {
		return mColor;
	}

	@Override
	public void setColor(final float r, final float g, final float b, final float a) {
		this.mColor.set(r, g, b, a);
	}

	/** Pool */
	@Override
	public void reset() {
		super.reset();

		this.setWidth(0);
		this.setHeight(0);

		this.setColor(1f, 1f, 1f, 1f);

		this.setIsoShape(false);
		this.setIsoGrid(null);

		this.setOrthoShape(false);
		this.setOrthoGrid(null);

		this.setDiffX(0f);
		this.setDiffY(0f);

		this.setGridPosX(0);
		this.setGridPosY(0);

	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final float pX, final float pY, final float pWidth, final float pHeight){
		super.init(pX, pY);

		this.setWidth(pWidth);
		this.setHeight(pHeight);

		this.setColor(1f, 1f, 1f, 1f);

		this.setIsoShape(false);
		this.setIsoGrid(null);

		this.setOrthoShape(false);
		this.setOrthoGrid(null);

		this.setDiffX(0f);
		this.setDiffY(0f);

		this.setGridPosX(0);
		this.setGridPosY(0);

	}

	/** IsoGrid */
	public void init(final float pX, final float pY, final float pWidth, final float pHeight, IsoGrid isoGrid){
		this.init(pX, pY, pWidth, pHeight);
		this.setIsoShape(true);
		this.setIsoGrid(isoGrid);
	}

	/** OrthoGrid */
	public void init(final float pX, final float pY, final float pWidth, final float pHeight, OrthoGrid orthoGrid){
		this.init(pX, pY, pWidth, pHeight);
		this.setOrthoShape(true);
		this.setOrthoGrid(orthoGrid);
	}


	/** Touch Events */
	public void onTouchUpEvent(){};

	public void onLongPressEvent(){};

	public void onTapEvent(){};

	public void onTouchDownEvent(){
		/** IsoShape || OrthoShape */
		if(this.isIsoShape() || this.isOrthoShape()){
			diffX = TouchData.touchX - this.getX();
			diffY = TouchData.touchY - this.getY();
		}
		/** Normal case */
		else{

		}
	};

	public void onPanEvent(){
		/** IsoShape */
		if(this.isIsoShape()){
			onPanEventIsoGrig(TouchData.touchX, TouchData.touchY);
		}
		/** OrthoShape */
		else if(this.isOrthoShape()){
			onPanEventOrthoGrid(TouchData.touchX, TouchData.touchY);
		}
		/** Normal case */
		else{

		}
	};


	public void onPanEventIsoGrig(final float pTouchAreaLocalX, final float pTouchAreaLocalY){

		if(isFocused()){

			/** pour etre visible et non sous le doigt */
			float decallageX = 0; //-100;
			float decallageY = 0; //100;

			float effectiveViewportWidth = GlobalManager.camera.viewportWidth * GlobalManager.camera.zoom;
			float effectiveViewportHeight = GlobalManager.camera.viewportHeight * GlobalManager.camera.zoom;

			/**OFF of gridMapState */
			this.getIsoGrid().getIsoMapState().remove(this);
			/** New Position */
			this.getIsoGrid().placeFromPosition(this, pTouchAreaLocalX-diffX + decallageX, pTouchAreaLocalY-diffY + decallageY);

			/** Check boundaries */
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
			
			/**Put on gridMapState */
			this.getIsoGrid().getIsoMapState().add(this);

		}

	};

	public void onPanEventOrthoGrid(final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		if(isFocused()){

			float effectiveViewportWidth = GlobalManager.camera.viewportWidth * GlobalManager.camera.zoom;
			float effectiveViewportHeight = GlobalManager.camera.viewportHeight * GlobalManager.camera.zoom;

			this.setPosition(Math.round((pTouchAreaLocalX-diffX)/this.getOrthoGrid().getCellWidth())*this.getOrthoGrid().getCellWidth(), Math.round((pTouchAreaLocalY-diffY)/this.getOrthoGrid().getCellHeight())*this.getOrthoGrid().getCellHeight());

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
	
	public boolean isFocused(){
        return this.getState() == ShapeState.FOCUSED;
    }

    public void setFocusState(){}

}




