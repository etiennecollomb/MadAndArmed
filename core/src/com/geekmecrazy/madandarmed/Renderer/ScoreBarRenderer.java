package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Utils.VirtualViewport;


public class ScoreBarRenderer extends Shape {

	//Score Bar
	private float score_bar_minX;

	private float score_bar_maxX;

	private float score_bar_minSize;  //toujours paire

	private float leftBar_Width;

	private float rightBar_PosX;

	private Sprite score_bar_background;

	private Sprite score_bar_border;

	private Sprite score_bar_leftPart;

	private Sprite score_bar_rightPart;

	private Sprite score_bar_reflet;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ScoreBarRenderer() {
		super();

		this.setScore_bar_background(new Sprite());
		this.setScore_bar_border(new Sprite());
		this.setScore_bar_leftPart(new Sprite(){
			@Override
			public void onDraw(){
				if(this.isVisible()){
					GlobalManager.spriteBatchDrawPortion(
							this.getTextureRegion().getTexture(),
							this.getSceneX(), this.getSceneY(),
							0, 0, //int srcX, int srcY,
							(int)getLeftBar_Width(), //int srcWidtht
							this.getTextureRegion().getTexture().getHeight() //int srcHeight
							);
					this.onDrawChildren();
				}
			}
		});
		this.setScore_bar_rightPart(new Sprite(){
			@Override
			public void onDraw(){
				if(this.isVisible()){
					GlobalManager.spriteBatchDrawPortion(
							this.getTextureRegion().getTexture(),
							this.getSceneX()+VirtualViewport.convertUIWidthToUnit(getLeftBar_Width()), this.getSceneY(),
							(int) getRightBar_PosX(), 0, //int srcX, int srcY,
							(int) this.getTextureRegion().getTexture().getWidth(), //int srcWidtht
							(int) this.getTextureRegion().getTexture().getHeight() //int srcHeight
							);
					this.onDrawChildren();
				}
			}
		});
		this.setScore_bar_reflet(new Sprite());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public float getScore_bar_minX() {
		return score_bar_minX;
	}

	public void setScore_bar_minX(float score_bar_minX) {
		this.score_bar_minX = score_bar_minX;
	}

	public float getScore_bar_maxX() {
		return score_bar_maxX;
	}

	public void setScore_bar_maxX(float score_bar_maxX) {
		this.score_bar_maxX = score_bar_maxX;
	}

	public float getScore_bar_minSize() {
		return score_bar_minSize;
	}

	public void setScore_bar_minSize(float score_bar_minSize) {
		this.score_bar_minSize = score_bar_minSize;
	}

	public float getLeftBar_Width() {
		return leftBar_Width;
	}

	public void setLeftBar_Width(float leftBar_Width) {
		this.leftBar_Width = leftBar_Width;
	}

	public float getRightBar_PosX() {
		return rightBar_PosX;
	}

	public void setRightBar_PosX(float rightBar_PosX) {
		this.rightBar_PosX = rightBar_PosX;
	}

	public Sprite getScore_bar_background() {
		return score_bar_background;
	}

	public void setScore_bar_background(Sprite score_bar_background) {
		this.score_bar_background = score_bar_background;
	}

	public Sprite getScore_bar_border() {
		return score_bar_border;
	}

	public void setScore_bar_border(Sprite score_bar_border) {
		this.score_bar_border = score_bar_border;
	}

	public Sprite getScore_bar_reflet() {
		return score_bar_reflet;
	}

	public void setScore_bar_reflet(Sprite score_bar_reflet) {
		this.score_bar_reflet = score_bar_reflet;
	}

	public Sprite getScore_bar_leftPart() {
		return score_bar_leftPart;
	}

	public void setScore_bar_leftPart(Sprite score_bar_leftPart) {
		this.score_bar_leftPart = score_bar_leftPart;
	}

	public Sprite getScore_bar_rightPart() {
		return score_bar_rightPart;
	}

	public void setScore_bar_rightPart(Sprite score_bar_rightPart) {
		this.score_bar_rightPart = score_bar_rightPart;
	}

	// ===========================================================
			// Methods for/from SuperClass/Interfaces
			// ===========================================================

			@Override
			public void onDraw(){

				super.onDraw();
			}

			@Override
			public void reset() {
				super.reset();

			}

			// ===========================================================
			// Methods
			// ===========================================================

			public void init(final TextureType textureType, final float pX, final float pY, final float pScore_bar_min, final float pScore_bar_max, final float pScore_bar_min_size){
				super.init(pX, pY, textureType.getWidth(), textureType.getHeight());

				this.setScore_bar_minX(pScore_bar_min);
				this.setScore_bar_maxX(pScore_bar_max);
				this.setScore_bar_minSize(pScore_bar_min_size/2f * 2f);

				this.setLeftBar_Width( getScore_bar_minX()+getScore_bar_minSize() );

				this.getScore_bar_background().init(TextureType.SCORE_BAR_BACKGROUND);
				this.getScore_bar_border().init(TextureType.SCORE_BAR_BORDER);
				this.getScore_bar_leftPart().init(textureType);
				this.getScore_bar_rightPart().init(textureType);
				this.getScore_bar_reflet().init(TextureType.SCORE_BAR_REFLET);

				this.attachChild(this.getScore_bar_background(), Entity.Alignment.CENTER);
				this.attachChild(this.getScore_bar_border(), Entity.Alignment.CENTER);
				this.attachChild(this.getScore_bar_leftPart(), Entity.Alignment.CENTER);
				this.attachChild(this.getScore_bar_rightPart(), Entity.Alignment.CENTER);
				this.attachChild(this.getScore_bar_reflet(), Entity.Alignment.CENTER);

				this.setBarSize(0);

			}

			public void setBarSize(float score_bar_size_){

				//Hide Bar, Score too small
				if(score_bar_size_ < score_bar_minSize){
					score_bar_leftPart.setVisible(false);
					score_bar_rightPart.setVisible(false);
				}
				//Show Bar, Score OK
				else{
					score_bar_leftPart.setVisible(true);
					score_bar_rightPart.setVisible(true);

					if(score_bar_size_ > (score_bar_maxX-score_bar_minX))
						score_bar_size_ = (score_bar_maxX-score_bar_minX);

					float rightBarWidth = (score_bar_size_-score_bar_minSize/2);
					this.setRightBar_PosX(score_bar_maxX - rightBarWidth);

				}

			}


}
