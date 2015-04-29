package com.geekmecrazy.madandarmed.Entity.Scene;

import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Renderer.FightBuildingRenderer;

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
	public void onTouch(final MyGestureDetector.GestureType pGestureType, final float pX, final float pY){
		super.onTouch(pGestureType, pX, pY);


		int size = this.mRegisteredTouchableShape.size;
		for(int i=0; i<size; i++){
			
			/** Deplacer des building sur la grid ISO */
			Shape shape = this.mRegisteredTouchableShape.get(i);
			if(shape instanceof FightBuildingRenderer && pGestureType.equals(MyGestureDetector.GestureType.PAN)){
				if(((FightBuildingRenderer)shape).isFocused()){
					shape.onTouch(pGestureType, touchX, touchY);
				}
			}
			else if(shape instanceof FightBuildingRenderer && pGestureType.equals(MyGestureDetector.GestureType.TOUCHDOWN)){
				if(!shape.contains(touchX, touchY) && ((FightBuildingRenderer)shape).isFocused()){
					((FightBuildingRenderer)shape).focus();
				}
			}
			
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
