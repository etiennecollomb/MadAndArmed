package com.geekmecrazy.madandarmed.Entity.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AnimatedSprite extends Sprite {

	private int mCurrentFrameX;
	private int mCurrentFrameY;

	private SpriteSheet mSpriteSheet;

	// ===========================================================
	// Constructors
	// ===========================================================

	public AnimatedSprite() {
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getCurrentFrameX() {
		return mCurrentFrameX;
	}

	public int getCurrentFrameY() {
		return mCurrentFrameY;
	}

	public void setCurrentFrameX(final int pCurrentFrameX) {
		this.mCurrentFrameX = (pCurrentFrameX<this.getSpriteSheet().getNumberOfColumn())? pCurrentFrameX: this.getSpriteSheet().getNumberOfColumn()-1;
		this.setWidth(this.getCurrentFrameSize());
	}

	public void setCurrentFrameY(final int pCurrentFrameY) {
		this.mCurrentFrameY = (pCurrentFrameY<this.getSpriteSheet().getNumberOfRow())? pCurrentFrameY: this.getSpriteSheet().getNumberOfRow()-1;
		this.setHeight(this.getCurrentFrameSize());
	}
	
	public void setCurrentFrame(final int pCurrentFrameX, final int pCurrentFrameY) {
		this.mCurrentFrameX = (pCurrentFrameX<this.getSpriteSheet().getNumberOfColumn())? pCurrentFrameX: this.getSpriteSheet().getNumberOfColumn()-1;
		this.mCurrentFrameY = (pCurrentFrameY<this.getSpriteSheet().getNumberOfRow())? pCurrentFrameY: this.getSpriteSheet().getNumberOfRow()-1;
		this.setWidth(this.getCurrentFrameSize());
		this.setHeight(this.getCurrentFrameSize());
		
		//testtexturepacker
//		this.setOffset
	}

	public void setCurrentFrame(final int pNumber) {
		this.setCurrentFrameX(pNumber%this.getSpriteSheet().getNumberOfColumn());
		this.setCurrentFrameY(pNumber/this.getSpriteSheet().getNumberOfColumn());
	}

	public SpriteSheet getSpriteSheet() {
		return mSpriteSheet;
	}
	
	public void setSpriteSheet(final SpriteSheet pSpriteSheet) {
		this.mSpriteSheet = pSpriteSheet;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

    @Override
    public TextureRegion getTextureRegion(){
        return this.getSpriteSheet().getFrame(this.getCurrentFrameX(), this.getCurrentFrameY());
    }

	@Override
	public void reset() {
		super.reset();
		
		this.setCurrentFrameX(0);
		this.setCurrentFrameY(0);

		this.setSpriteSheet(null);
		
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final SpriteSheet pSpriteSheet, final float pWidth, final float pHeight){
		super.init(0, 0, pWidth, pHeight);

		this.setSpriteSheet(pSpriteSheet);
		this.setCurrentFrame(0, 0);
	}

	public int getCurrentFrameSize(){
		return this.getSpriteSheet().getFrameSize(this.getCurrentFrameX(), this.getCurrentFrameY());
	}

	public int getNumberOfFrame(){
		return this.getSpriteSheet().getNumberOfTiled();
	}

}




