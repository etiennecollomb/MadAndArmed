package com.geekmecrazy.madandarmed.Entity.Scene;

/**
 * Created by ECollomb on 10/03/2015.
 */
public class FightScene extends Scene {

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onTouch(){
		super.onTouch();


		int size = this.mRegisteredTouchableShape.size;
		for(int i=0; i<size; i++){

			
			/** Action on selected Creep */
			//        if(pGestureType == MyGestureListener.GestureType.TOUCHDOWN) {
			//            CreepManager.getManager().applyTargetPointToSelectedCreep(touchX, touchY); //A foutre dans un fightscene ??!!
			//            CreepManager.getManager().clearSelectedCreep();
			//        }
		}


	}

	// ===========================================================
	// Methods
	// ===========================================================

}
