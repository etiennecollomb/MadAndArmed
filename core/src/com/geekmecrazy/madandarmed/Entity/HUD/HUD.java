package com.geekmecrazy.madandarmed.Entity.HUD;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.ITouchable;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Input.SelectedShapeManager;
import com.geekmecrazy.madandarmed.Input.TouchData;
import com.badlogic.gdx.utils.Array;

public class HUD extends Shape implements ITouchable {

	private Array<Shape> mRegisteredTouchableShape;

	// ===========================================================
	// Constructors
	// ===========================================================

	public HUD(){
		super();
		this.mRegisteredTouchableShape = new Array<Shape>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onTouch(){

		TouchData.convertToHud();

		int size = this.mRegisteredTouchableShape.size;
		for(int i=0; i<size; i++){
			Shape shape = this.mRegisteredTouchableShape.get(i);
			if(shape.contains(TouchData.screenTouchX, TouchData.screenTouchY)){
				shape.onTouch();
				if(SelectedShapeManager.isTouchLocked) /** si locked on ne teste plus le reste */
					break;
			}
		}
		
		if(!SelectedShapeManager.isTouchLocked) {
			/** if touch not locked, we play event on shape itself */
			super.onTouch();
		}


	}

	@Override
	public boolean contains(final float pX, final float pY){
		return true;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(){
		super.init(GlobalManager.getVvp().world_x/2f, GlobalManager.getVvp().world_y/2f, GlobalManager.getVvp().world_x, GlobalManager.getVvp().world_y);
		this.setAlignment(Alignment.NONE);
	}

	public void registerTouchableShape(final Shape pShape){
		this.mRegisteredTouchableShape.add(pShape);
		pShape.setHUD(true);
	}

	public void unregisterTouchableShape(final Shape pShape){
		this.mRegisteredTouchableShape.removeValue(pShape, true);
		pShape.setHUD(false);
	}

}
