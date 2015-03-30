package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Input.MyGestureListener;


public class MultiActionRenderer extends SimpleRenderer {
	
	/**
	 * N animated actions rows on M frame columns
	 */

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public MultiActionRenderer(){
		super();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void reset() {
		super.reset();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final SpriteSheet pSpriteSheet, final int pWidth, final int pHeight){
		super.init(pSpriteSheet, pWidth, pHeight);
	}
	

}

