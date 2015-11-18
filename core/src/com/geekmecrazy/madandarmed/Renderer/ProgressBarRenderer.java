package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Utils.VirtualViewport;


public class ProgressBarRenderer extends Shape {

	//Score Bar
	private float progressBarMinX;

	private float progressBarMaX;

	private float progressBarMinSize;  //toujours paire

	private float leftBarWidth;

	private float rightBarPosX;

	private Sprite progressBarBackground;

	private Sprite progressBarBorder;

	private Sprite progressBarLeftPart;

	private Sprite progressBarRightPart;

	private Sprite progressBarReflet;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ProgressBarRenderer() {
		super();

		this.setProgressBarBackground(new Sprite());
		this.setProgressBarBorder(new Sprite());
		this.setProgressBarLeftPart(new Sprite(){
			@Override
			public void onUpdate() {
				super.onUpdate();
				
				/** HUD Conversion */
				this.setDraw_width(VirtualViewport.convertUIWidthToUnit(getLeftBarWidth()));
				this.setDraw_srcWidth((int)getLeftBarWidth());
			}
		});
		this.setProgressBarRightPart(new Sprite(){
			@Override
			public void onUpdate() {
				super.onUpdate();
				this.setDraw_x(this.getSceneX()+VirtualViewport.convertUIWidthToUnit(getLeftBarWidth()));
				this.setDraw_srcX((int)getRightBarPosX());
			}
		});
		this.setProgressBarReflet(new Sprite());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	public float getProgressBarMinX() {
		return progressBarMinX;
	}

	public void setProgressBarMinX(float progressBarMinX) {
		this.progressBarMinX = progressBarMinX;
	}

	public float getProgressBarMaX() {
		return progressBarMaX;
	}

	public void setProgressBarMaX(float progressBarMaX) {
		this.progressBarMaX = progressBarMaX;
	}

	public float getProgressBarMinSize() {
		return progressBarMinSize;
	}

	public void setProgressBarMinSize(float progressBarMinSize) {
		this.progressBarMinSize = progressBarMinSize;
	}

	public float getLeftBarWidth() {
		return leftBarWidth;
	}

	public void setLeftBarWidth(float leftBarWidth) {
		this.leftBarWidth = leftBarWidth;
	}

	public float getRightBarPosX() {
		return rightBarPosX;
	}

	public void setRightBarPosX(float rightBarPosX) {
		this.rightBarPosX = rightBarPosX;
	}

	public Sprite getProgressBarBackground() {
		return progressBarBackground;
	}

	public void setProgressBarBackground(Sprite progressBarBackground) {
		this.progressBarBackground = progressBarBackground;
	}

	public Sprite getProgressBarBorder() {
		return progressBarBorder;
	}

	public void setProgressBarBorder(Sprite progressBarBorder) {
		this.progressBarBorder = progressBarBorder;
	}

	public Sprite getProgressBarLeftPart() {
		return progressBarLeftPart;
	}

	public void setProgressBarLeftPart(Sprite progressBarLeftPart) {
		this.progressBarLeftPart = progressBarLeftPart;
	}

	public Sprite getProgressBarRightPart() {
		return progressBarRightPart;
	}

	public void setProgressBarRightPart(Sprite progressBarRightPart) {
		this.progressBarRightPart = progressBarRightPart;
	}

	public Sprite getProgressBarReflet() {
		return progressBarReflet;
	}

	public void setProgressBarReflet(Sprite progressBarReflet) {
		this.progressBarReflet = progressBarReflet;
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

	public void init(final TextureType textureType, final float pX, final float pY, final float pprogress_bar_min, final float pprogress_bar_max, final float pprogress_bar_min_size){
		super.init(pX, pY, textureType.getWidth(), textureType.getHeight());

		this.setProgressBarMinX(pprogress_bar_min);
		this.setProgressBarMaX(pprogress_bar_max);
		this.setProgressBarMinSize(pprogress_bar_min_size/2f * 2f);

		this.setLeftBarWidth( getProgressBarMinX()+getProgressBarMinSize() );

		this.getProgressBarBackground().init(TextureType.PROGRESS_BAR_BACKGROUND);
		this.getProgressBarBorder().init(TextureType.PROGRESS_BAR_BORDER);
		this.getProgressBarLeftPart().init(textureType);
		this.getProgressBarRightPart().init(textureType);
		this.getProgressBarReflet().init(TextureType.PROGRESS_BAR_REFLET);

		this.attachChild(this.getProgressBarBackground(), Entity.Alignment.CENTER);
		this.attachChild(this.getProgressBarBorder(), Entity.Alignment.CENTER);
		this.attachChild(this.getProgressBarLeftPart(), Entity.Alignment.CENTER);
		this.attachChild(this.getProgressBarRightPart(), Entity.Alignment.CENTER);
		this.attachChild(this.getProgressBarReflet(), Entity.Alignment.CENTER);

		this.setBarSize(0);

	}

	
	/** Size of load in pixel **/
	public void setBarSize(float progress_bar_size_){

		//Hide Bar, Score too small
		if(progress_bar_size_ < progressBarMinSize){
			progressBarLeftPart.setVisible(false);
			progressBarRightPart.setVisible(false);
		}
		//Show Bar, Score OK
		else{
			progressBarLeftPart.setVisible(true);
			progressBarRightPart.setVisible(true);

			if(progress_bar_size_ > (progressBarMaX-progressBarMinX))
				progress_bar_size_ = (progressBarMaX-progressBarMinX);

			float rightBarWidth = (progress_bar_size_-progressBarMinSize/2);
			this.setRightBarPosX(progressBarMaX - rightBarWidth);

		}

	}


}
