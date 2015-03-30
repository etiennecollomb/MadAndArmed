package com.geekmecrazy.madandarmed.Entity;

import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.MyGestureListener;

public interface ITouchable {
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public boolean contains(final float pX, final float pY);

	public void onTouch(final MyGestureDetector.GestureType gestureType, final float pTouchAreaLocalX, final float pTouchAreaLocalY);
	
	// ===========================================================
	// Methods
	// ===========================================================
}
