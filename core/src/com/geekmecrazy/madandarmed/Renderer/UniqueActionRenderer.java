package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;


public class UniqueActionRenderer extends SimpleRenderer  {

	/** One action, can be set on NxN */
	private int mCurrentFrame;	

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public UniqueActionRenderer(){
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate() {		
		//on demarre apres le delay
		if(this.mCurrentFrame>=0 && !this.isFinished()){
			this.setVisible(true);
			this.setCurrentFrame(this.mCurrentFrame);
		}else{
			this.setVisible(false);
		}
		this.mCurrentFrame++;
		
		//after because OffSet has been modified
		super.onUpdate();
	}
	
	@Override
	public void reset() {
		super.reset();
		
		this.setStartDelay(0);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	/** startDelay : temps de "pause" avant de commencer l anim (usefull pour multi explosion) */
	public void init(final SpriteSheet pSpriteSheet){
		super.init(pSpriteSheet, 0, 0);
		
		this.setStartDelay(0);
		this.setVisible(false);
	}

	public boolean isFinished(){
		return this.mCurrentFrame>=this.getNumberOfFrame();
	}

	public void setStartDelay(final int pStartDelay){
		this.mCurrentFrame = -pStartDelay;
	}


}

