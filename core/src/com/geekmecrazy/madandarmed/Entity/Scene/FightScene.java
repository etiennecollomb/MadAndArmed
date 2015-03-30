package com.geekmecrazy.madandarmed.Entity.Scene;

import com.geekmecrazy.madandarmed.Input.MyGestureDetector;

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

        //Action on selected Creep
//        if(pGestureType == MyGestureListener.GestureType.TOUCHDOWN) {
//            CreepManager.getManager().applyTargetPointToSelectedCreep(touchX, touchY); //A foutre dans un fightscene ??!!
//            CreepManager.getManager().clearSelectedCreep();
//        }

    }

    // ===========================================================
    // Methods
    // ===========================================================

}
