package com.geekmecrazy.madandarmed.Game.UI;

import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Input.TouchData;


public class Layout extends Shape {

	public static enum Dimension {
		MATCH_PARENT,
		WRAP_CONTENT,
		SPECIFIED
	}

	public static enum Orientation {
		VERTICAL,
		HORIZONTAL
	}
	private Orientation mOrientation;

	private Dimension dimension_X;
	private Dimension dimension_Y;
	

	// ===========================================================
	// Constructors
	// ===========================================================

	public Layout(){
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Orientation getOrientation() {
		return mOrientation;
	}

	public void setOrientation(Orientation pOrientation) {
		this.mOrientation = pOrientation;
	}

	public Dimension getDimension_X() {
		return dimension_X;
	}

	public void setDimension_X(Dimension dimension_X) {
		this.dimension_X = dimension_X;
	}

	public Dimension getDimension_Y() {
		return dimension_Y;
	}

	public void setDimension_Y(Dimension dimension_Y) {
		this.dimension_Y = dimension_Y;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void setScaleX(final float pScaleX) {
		super.setScaleX(1f); //scale not allowed on Layout Object
	}

	@Override
	public void setScaleY(final float pScaleY) {
		super.setScaleY(1f); //scale not allowed on Layout Object
	}

	@Override
	public void setScale(final float pScale) {
		super.setScaleX(1f); //scale not allowed on Layout Object
		super.setScaleY(1f); //scale not allowed on Layout Object
	}

	@Override
	public void reset(){
		super.reset();

		this.setOrientation(Orientation.VERTICAL);
	}
	
	/** Touch Events */
	public void onTouchDownEvent(final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		
	}
	
	@Override
	public void onPanEvent() {
		
		/** Swipe children */
		switch(this.getOrientation()){
		case VERTICAL:
			for(int i=this.getChildren().size-1; i>=0; i--) {
				this.getChildren().get(i).setY( this.getChildren().get(i).getY() + TouchData.screenDeltaY );
			}
			break;
		case HORIZONTAL:
			for(int i=0; i<this.getChildren().size; i++) {
				this.getChildren().get(i).setX( this.getChildren().get(i).getX() + TouchData.screenDeltaX );
			}
			break;
		default :
			break;
		}
		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final float pX, final float pY) {
		super.init(pX, pY, 0, 0);

		this.setOrientation(Orientation.VERTICAL);
		this.setLayoutSize(Dimension.WRAP_CONTENT, Dimension.WRAP_CONTENT);
	}

	public void setLayoutSize(Dimension pDimension_X, Dimension pDimension_Y){
		this.setDimension_X(pDimension_X);
		this.setDimension_Y(pDimension_Y);
	}

	public void setLayoutSize(Dimension pDimension_X,float hVal){
		this.setDimension_X(pDimension_X);
		this.setDimension_Y(Dimension.SPECIFIED);
		this.setHeight(hVal);
	}

	public void setLayoutSize(float wVal,Dimension pDimension_Y){
		this.setDimension_X(Dimension.SPECIFIED);
		this.setWidth(wVal);
		this.setDimension_Y(pDimension_Y);
	}

	public void setLayoutSize(float wVal,float hVal){
		this.setDimension_X(Dimension.SPECIFIED);
		this.setDimension_Y(Dimension.SPECIFIED);
		this.setSize(wVal,hVal);
	}

	public void add(final Shape pShape){
		switch(this.getOrientation()){
		case VERTICAL:
			this.add(pShape, Alignment.CENTER_BOTTOM);
			break;
		case HORIZONTAL:
			this.add(pShape, Alignment.CENTER_LEFT);
			break;
		}
	}

	public void add(final Shape pShape,final Alignment alignment){

		switch(this.getOrientation()) {
		case VERTICAL:
			pShape.setAlignment(Alignment.CENTER_BOTTOM);
			this.attachChild(pShape,alignment);
			break;
		case HORIZONTAL:
			pShape.setAlignment(Alignment.CENTER_LEFT);
			this.attachChild(pShape,alignment);
			break;
		default:
			break;
		}
		
		this.updateChildrenPositions();
		this.updateSize();

	}

	public void updateChildrenPositions(){

		float pos = 0;
		switch(this.getOrientation()){
		case VERTICAL:
			for(int i=this.getChildren().size-1; i>=0; i--) {
				this.getChildren().get(i).setY(pos);
				pos = pos+this.getChildren().get(i).getHeight();
			}
			break;
		case HORIZONTAL:
			for(int i=0; i<this.getChildren().size; i++) {
				this.getChildren().get(i).setX(pos);
				pos = pos+this.getChildren().get(i).getWidth();
			}
			break;
		default :
			break;
		}

	}

	public void updateSize(){

		float newWidth = 0;
		float newHeight = 0;


		int size = this.getChildren().size;
		for(int i=0; i<size; i++) {
			switch(this.getOrientation()) {
			case VERTICAL:
				newWidth = Math.max(newWidth, this.getChildren().get(i).getWidth());
				newHeight = newHeight + this.getChildren().get(i).getHeight();
				break;
			case HORIZONTAL:
				newWidth = newWidth + this.getChildren().get(i).getWidth();
				newHeight = Math.max(newHeight, this.getChildren().get(i).getHeight());
				break;
			default:
				break;
			}
		}

		/** If matchParent, we force the size */
		if(this.getParent() != null && this.getParent() instanceof Shape) {
			if(this.getDimension_X() != Dimension.SPECIFIED){
				if(this.getDimension_X() == Dimension.MATCH_PARENT){
					newWidth = ((Shape)this.getParent()).getWidth();
				}
			}
			if(this.getDimension_Y() != Dimension.SPECIFIED){
				if(this.getDimension_Y() == Dimension.MATCH_PARENT){
					newHeight = ((Shape)this.getParent()).getHeight();
				}
			}
		}

		/** Specified Size */
		if(this.getDimension_X() == Dimension.SPECIFIED){
			newWidth = this.getWidth();
		}

		if(this.getDimension_Y() == Dimension.SPECIFIED){
			newHeight = this.getHeight();
		}

		setSize(newWidth, newHeight);

		if(this.getParent() != null && this.getParent() instanceof Layout){
			((Layout)this.getParent()).updateSize();
		}
	}


}

