package com.geekmecrazy.madandarmed.Entity.Scene;

import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.TouchData;
import com.geekmecrazy.madandarmed.Renderer.IsoBuildingRenderer;

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
			
			/** Deplacer des building sur la grid ISO */
			Shape shape = this.mRegisteredTouchableShape.get(i);
			if(shape instanceof IsoBuildingRenderer && TouchData.gestureType.equals(MyGestureDetector.GestureType.PAN)){
				if(((IsoBuildingRenderer)shape).isFocused()){
					shape.onTouch();
				}
			}
			else if(shape instanceof IsoBuildingRenderer && TouchData.gestureType.equals(MyGestureDetector.GestureType.TOUCHDOWN)){
				if(!shape.contains(TouchData.touchX, TouchData.touchY) && ((IsoBuildingRenderer)shape).isFocused()){
					((IsoBuildingRenderer)shape).focus();
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
