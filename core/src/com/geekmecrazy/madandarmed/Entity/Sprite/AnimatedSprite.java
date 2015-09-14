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
	
	public void setCurrentFrame(final int pCurrentFrameX, final int pCurrentFrameY) {
		this.mCurrentFrameX = (pCurrentFrameX<this.getSpriteSheet().getNumberOfColumn())? pCurrentFrameX: this.getSpriteSheet().getNumberOfColumn()-1;
		this.mCurrentFrameY = (pCurrentFrameY<this.getSpriteSheet().getNumberOfRow())? pCurrentFrameY: this.getSpriteSheet().getNumberOfRow()-1;
		this.setWidth(this.getCurrentFrameWidth());
		this.setHeight(this.getCurrentFrameHeight());
		/** usefull in case of TexturePack animatedSprite **/
		//TODO a faire correcttement 256 = taille original de l image
//		this.setOffsetX(this.getCurrentFrameOffsetX()+(this.getTextureRegion().getRegionWidth()/2f)-(256/2f));
//		this.setOffsetY(this.getCurrentFrameOffsetY()+(this.getTextureRegion().getRegionHeight()/2f)-(256/2f));
	}

	public void setCurrentFrame(final int pNumber) {
		this.setCurrentFrame(pNumber%this.getSpriteSheet().getNumberOfColumn(), pNumber/this.getSpriteSheet().getNumberOfColumn());
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
		
		this.setCurrentFrame(0, 0);

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

	public int getCurrentFrameWidth(){
		return this.getSpriteSheet().getFrameWidth(this.getCurrentFrameX(), this.getCurrentFrameY());
	}
	
	public int getCurrentFrameHeight(){
		return this.getSpriteSheet().getFrameHeight(this.getCurrentFrameX(), this.getCurrentFrameY());
	}

	public float getCurrentFrameOffsetX(){
		return this.getSpriteSheet().getFrameOffsetX(this.getCurrentFrameX(), this.getCurrentFrameY());
	}
	
	public float getCurrentFrameOffsetY(){
		return this.getSpriteSheet().getFrameOffsetY(this.getCurrentFrameX(), this.getCurrentFrameY());
	}	
	
	public int getNumberOfFrame(){
		return this.getSpriteSheet().getNumberOfTiled();
	}

}




