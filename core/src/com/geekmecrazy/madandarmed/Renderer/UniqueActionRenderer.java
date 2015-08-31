package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;


public class UniqueActionRenderer extends SimpleRenderer  {

	/** Dealy before running the animation **/
	private int delay = 0;
	
	/** One action, can be set on NxN */
	private int mCurrentFrame;

	/** Speed of the animation
	n times the cycle speed (ie. 0.5f every 2 frames, 2f skip one frame **/
	private float animationSpeed;
	private float animationSpeedModulo;

	// ===========================================================
	// Constructors
	// ===========================================================

	public UniqueActionRenderer(){
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public float getAnimationSpeed() {
		return animationSpeed;
	}

	public void setAnimationSpeed(float animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate() {		
		//on demarre apres le delay
		if(this.delay <= 0 && !this.isFinished()){
			this.setVisible(true);
			this.setCurrentFrame(this.mCurrentFrame);
		}else{
			this.setVisible(false);
			this.delay = this.delay - 1;
		}
		
		//Set current frame regarding the animation speed
		if(this.getAnimationSpeed()<=1.0f){
			animationSpeedModulo = animationSpeedModulo + this.getAnimationSpeed();
			if(animationSpeedModulo>1.0f){
				animationSpeedModulo = animationSpeedModulo - 1.0f;
				this.mCurrentFrame++;
			}
		}else{ // ie. > 1.0f
			animationSpeedModulo = animationSpeedModulo + this.getAnimationSpeed() % 1.0f;
			this.mCurrentFrame = this.mCurrentFrame + (int)this.getAnimationSpeed();
		}
			
		
		
		//after because OffSet has been modified
		super.onUpdate();
	}
	
	@Override
	public void reset() {
		super.reset();
		
		this.setStartDelay(0);
		this.setAnimationSpeed(1f);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	/** startDelay : temps de "pause" avant de commencer l anim (usefull pour multi explosion) */
	public void init(final SpriteSheet pSpriteSheet){
		super.init(pSpriteSheet, 0, 0);
		
		this.setAnimationSpeed(1f);
		
		this.setStartDelay(0);
		this.setVisible(false);
		
		mCurrentFrame = 0;
		animationSpeedModulo = 0f;
	}

	public boolean isFinished(){
		return this.mCurrentFrame>=this.getNumberOfFrame();
	}

	public void setStartDelay(final int pStartDelay){
		this.delay = pStartDelay;
	}
	


}

