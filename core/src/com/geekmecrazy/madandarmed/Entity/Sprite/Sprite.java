package com.geekmecrazy.madandarmed.Entity.Sprite;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Sprite extends Shape {

	private TextureRegion mTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Sprite() {
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public TextureRegion getTextureRegion() {
		return mTextureRegion;
	}

	public void setTextureRegion(final TextureRegion pTextureRegion) {
		this.mTextureRegion = pTextureRegion;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onTouch(final MyGestureDetector.GestureType pGestureType, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		super.onTouch(pGestureType, pTouchAreaLocalX, pTouchAreaLocalY);
	}

	@Override
	public void onDraw(){
		if(this.isVisible()){
			GlobalManager.spriteBatchDrawWithColor(this.getTextureRegion(),
                    this.getSceneX(), this.getSceneY(),
                    this.getCenterX(), this.getCenterY(),
                    this.getWidth(), this.getHeight(),
                    this.getSceneScaleX(), this.getSceneScaleY(),
                    0f, this.getColor());
			this.onDrawChildren();
		}
	}

	@Override
	public void reset() {
		super.reset();
		
		this.setTextureRegion(null);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final TextureType pTextureType){
		super.init(0, 0, pTextureType.getWidth(), pTextureType.getHeight());
		
		Texture spriteTexture = new Texture(Gdx.files.internal(pTextureType.getPath()));
		this.setTextureRegion(new TextureRegion(spriteTexture));

	}

    public void init(final TextureRegion region){
        super.init(0, 0, region.getRegionWidth(), region.getRegionHeight());

        this.setTextureRegion(region);
    }

}




