package com.geekmecrazy.madandarmed.Entity;

import com.geekmecrazy.madandarmed.Utils.ZIndexComparator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;


public class Entity implements IDrawable, IUpdatable, Poolable {

    public static enum Alignment{

        //Alignment inside the parent
        LEFT,
        CENTER_X,
        RIGHT,
        BOTTOM,
        CENTER_Y,
        TOP,
        CENTER,

        //Center on mX and mY
        CENTER_ON_ITSELF,

        LEFT_TOP,
        CENTER_TOP,
        CENTER_BOTTOM,
        CENTER_LEFT,
        CENTER_RIGHT,
        LEFT_BOTTOM,
        RIGHT_BOTTOM,
        RIGHT_TOP,

        NONE
    }

	private Array<Entity> mChildren;
	private boolean mIsChildrenSorted;

    private Alignment mAlignmentX = Alignment.CENTER_X;
    private Alignment mAlignmentY = Alignment.CENTER_Y;

    private int mZIndex;

    private boolean mIsVisible;

    private Entity mParent;

	//local referential (parent)
	private float mX;
	private float mY;

    private float mCenterX;
    private float mCenterY;

    private float mOffsetX;
    private float mOffsetY;

    private float mWidth;
    private float mHeight;

    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;

	//global referential global
	private float mSceneX;
	private float mSceneY;

    private float mSceneScaleX;
    private float mSceneScaleY;

    private boolean mIsScalable;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Entity(){
		this.setChildren( new Array<Entity>() );
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

    public Array<Entity> getChildren() {
        return mChildren;
    }

    public void setChildren(final Array<Entity> pChildren) {
        this.mChildren = pChildren;
    }

    public boolean isChildrenSorted() {
        return mIsChildrenSorted;
    }

    public void setChildrenSorted(final boolean pChildrenSorted) {
        this.mIsChildrenSorted = pChildrenSorted;
    }

    public Alignment getAlignmentX() {
        return mAlignmentX;
    }

    public void setAlignmentX(final Alignment pAlignmentX) {
        this.mAlignmentX = pAlignmentX;
    }

    public Alignment getAlignmentY() {
        return mAlignmentY;
    }

    public void setAlignmentY(final Alignment pAlignmentY) {
        this.mAlignmentY = pAlignmentY;
    }

    public int getZIndex() {
        return this.mZIndex;
    }

    public void setZIndex(final int pZIndex) {

        this.mZIndex = pZIndex;

        if(this.getParent() != null)
            this.mParent.setChildrenSorted(false);
    }

    public boolean isVisible() {
        return this.mIsVisible;
    }

    public void setVisible(final boolean pVisible) {
        this.mIsVisible = pVisible;
    }

    public Entity getParent() {
        return this.mParent;
    }

    public void setParent(final Entity pEntity) {
        this.mParent = pEntity;
    }

    public float getX() {
        return this.mX;
    }

    public void setX(final float pX) {
        this.mX = pX;
    }

    public float getY() {
        return this.mY;
    }

    public void setY(final float pY) {
        this.mY = pY;
    }

    public float getCenterX() {
        return mCenterX;
    }

    public void setCenterX(final float pCenterX) {
        this.mCenterX = pCenterX;
    }

    public float getCenterY() {
        return mCenterY;
    }

    public void setCenterY(final float pCenterY) {
        this.mCenterY = pCenterY;
    }

    public float getOffsetX() {
        return mOffsetX;
    }

    public void setOffsetX(final float pOffsetX) {
        this.mOffsetX = pOffsetX;
    }

    public float getOffsetY() {
        return mOffsetY;
    }

    public void setOffsetY(final float pOffsetY) {
        this.mOffsetY = pOffsetY;
    }

    public float getWidth() {
        return this.mWidth;
    }

    public void setWidth(final float pWidth){
        this.mWidth = pWidth;
    }

    public float getHeight() {
        return this.mHeight;
    }

    public void setHeight(final float pHeight){
        this.mHeight = pHeight;
    }

    public float getScaleX() {
        return mScaleX;
    }

    public void setScaleX(final float pScaleX) {
        this.mScaleX = pScaleX;
    }

    public float getScaleY() {
        return mScaleY;
    }

    public void setScaleY(final float pScaleY) {
        this.mScaleY = pScaleY;
    }

    public float getSceneX() {
        return this.mSceneX;
    }

    public void setSceneX(final float pSceneX) {
        this.mSceneX = pSceneX;
    }

    public float getSceneY() {
        return this.mSceneY;
    }

    public void setSceneY(final float pSceneY) {
        this.mSceneY = pSceneY;
    }

    public float getSceneScaleX() {
        return mSceneScaleX;
    }

    public void setSceneScaleX(final float pSceneScaleX) {
        this.mSceneScaleX = pSceneScaleX;
    }

    public float getSceneScaleY() {
        return mSceneScaleY;
    }

    public void setSceneScaleY(final float pSceneScaleY) {
        this.mSceneScaleY = pSceneScaleY;
    }

    public boolean isScalable() {
        return mIsScalable;
    }

    public void setScalable(boolean pIsScalable) {
        this.mIsScalable = pIsScalable;

        int size = this.getChildren().size;
        for(int i=0; i<size; i++)
            this.getChildren().get(i).setScalable(pIsScalable);
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onDraw() {
        if(this.isVisible()){
            this.onDrawChildren();
        }
    }

	/** Pool */
	@Override
	public void reset() {
		
		this.detachChildren();
		this.setChildrenSorted(false);
		this.getChildren().clear();

		this.setX(0);
		this.setY(0);

        this.setCenterX(0);
        this.setCenterY(0);

		this.setSceneX(0);
		this.setSceneY(0);
		
		this.setOffsetX(0);
		this.setOffsetY(0);

        this.setWidth(0);
        this.setHeight(0);

        this.setScaleX(1.0f);
        this.setScaleY(1.0f);

        this.setSceneScaleX(1.0f);
        this.setSceneScaleY(1.0f);

        this.setAlignment(Alignment.CENTER_ON_ITSELF);

		this.setZIndex(0);

		this.setVisible(false);

		this.detachSelf();
		this.setParent(null);

        this.setScalable(false);

	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	protected void init(final float pX, final float pY){

		this.setChildrenSorted(false);
		this.getChildren().clear();
		
		this.setX(pX);
		this.setY(pY);

        this.setCenterX(0);
        this.setCenterY(0);
		
		this.setSceneX(pX);
		this.setSceneY(pY);

		this.setOffsetX(0);
        this.setOffsetY(0);

        this.setWidth(0);
        this.setHeight(0);

        this.setScaleX(1.0f);
        this.setScaleY(1.0f);

        this.setSceneScaleX(1.0f);
        this.setSceneScaleY(1.0f);

        this.setAlignment(Alignment.CENTER_ON_ITSELF);

		this.setZIndex(0);
		
		this.setVisible(true);

		this.setParent(null);

        this.setScalable(false);

	}

    public void detachSelf() {
        if(this.getParent() != null)
            this.getParent().detachChild(this);
    }

    public void attachChild(final Entity pEntity) {
        this.getChildren().add(pEntity);
        pEntity.setParent(this);
        this.setChildrenSorted(false);

        if(this.isScalable())
            pEntity.setScalable(this.isScalable());
    }

    public void attachChild(final Entity pEntity, final Alignment pAlignment) {
        this.attachChild(pEntity);
        pEntity.setAlignment(pAlignment);
    }

    public void detachChild(final Entity pEntity) {
        this.getChildren().removeValue(pEntity, true);
        pEntity.setParent(null);
        pEntity.setScalable(false);
    }

    public void detachChildren() {

        int size = this.getChildren().size;
        for(int i=0; i<size; i++)
            this.detachChild(this.getChildren().get(0));

        this.getChildren().clear();
    }

    public int getChildCount() {
        return this.mChildren.size;
    }

    public void sortChildren() {
        this.getChildren().sort(ZIndexComparator.getInstance());
        this.setChildrenSorted(true);
    }

    public Entity getChild(final int pIndex) {
        return this.mChildren.get(pIndex);
    }

    public void setPosition(final float pX, final float pY) {
        this.setX(pX);
        this.setY(pY);
    }

    public void setSize(final float width, final float height){
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setScale(final float pScale) {
        this.setScaleX(pScale);
        this.setScaleY(pScale);
    }

    public void setAlignment(final Alignment pAlignment){
        switch(pAlignment){
            case LEFT :
                this.setAlignmentX(Alignment.LEFT);
                break;
            case CENTER_X :
                this.setAlignmentX(Alignment.CENTER_X);
                break;
            case RIGHT :
                this.setAlignmentX(Alignment.RIGHT);
                break;
            case BOTTOM :
                this.setAlignmentY(Alignment.BOTTOM);
                break;
            case CENTER_Y :
                this.setAlignmentY(Alignment.CENTER_Y);
                break;
            case TOP :
                this.setAlignmentY(Alignment.TOP);
                break;
            case CENTER:
                this.setAlignmentX(Alignment.CENTER_X);
                this.setAlignmentY(Alignment.CENTER_Y);
                break;
            case CENTER_ON_ITSELF:
                this.setAlignmentX(Alignment.CENTER_ON_ITSELF);
                this.setAlignmentY(Alignment.CENTER_ON_ITSELF);
                break;
            case LEFT_TOP:
                this.setAlignmentX(Alignment.LEFT);
                this.setAlignmentY(Alignment.TOP);
                break;
            case CENTER_TOP:
                this.setAlignmentX(Alignment.CENTER_X);
                this.setAlignmentY(Alignment.TOP);
                break;
            case CENTER_BOTTOM:
                this.setAlignmentX(Alignment.CENTER_X);
                this.setAlignmentY(Alignment.BOTTOM);
                break;
            case CENTER_LEFT:
                this.setAlignmentX(Alignment.LEFT);
                this.setAlignmentY(Alignment.CENTER_Y);
                break;
            case CENTER_RIGHT:
                this.setAlignmentX(Alignment.RIGHT);
                this.setAlignmentY(Alignment.CENTER_Y);
                break;
            case LEFT_BOTTOM:
                this.setAlignmentX(Alignment.LEFT);
                this.setAlignmentY(Alignment.BOTTOM);
                break;
            case RIGHT_BOTTOM:
                this.setAlignmentX(Alignment.RIGHT);
                this.setAlignmentY(Alignment.BOTTOM);
                break;
            case RIGHT_TOP:
                this.setAlignmentX(Alignment.RIGHT);
                this.setAlignmentY(Alignment.TOP);
                break;
            case NONE:
                this.setAlignmentX(Alignment.NONE);
                this.setAlignmentY(Alignment.NONE);
                break;
            default: //CENTER X & Y
                this.setAlignmentX(Alignment.CENTER_ON_ITSELF);
                this.setAlignmentY(Alignment.CENTER_ON_ITSELF);
                break;
        }
    }

    /** For onUpdate */
    public void setSceneCoordinates() {
        this.setSceneX( this.getX() + this.getOffsetX() );
        this.setSceneY( this.getY() + this.getOffsetY() );

        if(this.getParent() != null){

            this.setSceneX( this.getSceneX() + this.getParent().getSceneX() );
            this.setSceneY( this.getSceneY() + this.getParent().getSceneY() );

            //on reajuste en fonction du zoom du pere (distance des centres)
            if(this.isScalable()){
                float distanceX = this.getParent().getCenterX() - (this.getCenterX() + this.getOffsetX() );
                this.setSceneX(this.getSceneX() + (distanceX * (1 - this.getParent().getSceneScaleX())));
                float distanceY = this.getParent().getCenterY() - (this.getCenterY() + this.getOffsetY() );
                this.setSceneY(this.getSceneY() + (distanceY * (1 - this.getParent().getSceneScaleY())));
            }
        }
    }

    public void setSceneScales() {
        this.setSceneScaleX(this.getScaleX());
        this.setSceneScaleY(this.getScaleY());
        if(this.getParent() != null) {
            this.setSceneScaleX(this.getSceneScaleX() * this.getParent().getSceneScaleX());
            this.setSceneScaleY(this.getSceneScaleY() * this.getParent().getSceneScaleY());
        }
    }

	public void onDrawChildren(){

		if(!this.isChildrenSorted())
			this.sortChildren(); //Sort zIndex

		int size = this.getChildren().size;
		for(int i=0; i<size ; i++)
			this.getChildren().get(i).onDraw();
	}

	public void onUpdateChildren(){

		int size = this.getChildren().size;
		for(int i=0; i<size ; i++)
			this.getChildren().get(i).onUpdate();
	}

    public void setOffsetX(){

        if(this.getParent() != null) {
            switch (this.getAlignmentX()) {
                case LEFT:
                    this.setOffsetX(0);
                    break;
                case CENTER_X:
                    this.setOffsetX( this.getParent().getWidth() / 2f - this.getWidth() / 2f);
                    break;
                case RIGHT:
                    this.setOffsetX( this.getParent().getWidth() - this.getWidth());
                    break;
                case CENTER_ON_ITSELF:
                    this.setOffsetX(-this.getWidth()/2f);
                    break;
                default:
                    this.setOffsetX(0);
                    break;
            }
        }else{
            this.setOffsetX(-this.getWidth()/2f); //Center on itself
        }

    }

    public void setOffsetY(){

        if(this.getParent() != null) {
            switch (this.getAlignmentY()) {
                case BOTTOM:
                    this.setOffsetY(0);
                    break;
                case CENTER_Y:
                    this.setOffsetY(this.getParent().getHeight() / 2f - this.getHeight() / 2f);
                    break;
                case TOP:
                    this.setOffsetY(this.getParent().getHeight() - this.getHeight());
                    break;
                case CENTER_ON_ITSELF:
                    this.setOffsetY(-this.getHeight() / 2f);
                    break;
                default:
                    this.setOffsetY(0);
                    break;
            }
        }else{
            this.setOffsetY(-this.getHeight() / 2f); //Center on itself
        }

    }

    public void setOffsets(){
        this.setOffsetX();
        this.setOffsetY();
    }

    public void setCenters(){
        this.setCenterX(this.getWidth()/2f);
        this.setCenterY(this.getHeight()/2f);
    }

    @Override
    public void onUpdate() {

        this.setCenters();
        this.setOffsets();

        if(this.isScalable())
            this.setSceneScales();

        this.setSceneCoordinates();
        this.onUpdateChildren();

    }

}

