package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Entity.Sprite.AnimatedSprite;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;


public abstract class SimpleRenderer extends AnimatedSprite {
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleRenderer(){
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

	public void init(SpriteSheet pSpriteSheet, int pWidth, int pHeight){
		super.init(pSpriteSheet, pWidth, pHeight);
	}
	
}
