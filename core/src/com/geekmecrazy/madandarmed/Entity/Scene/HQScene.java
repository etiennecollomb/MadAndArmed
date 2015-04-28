package com.geekmecrazy.madandarmed.Entity.Scene;

import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Renderer.HQBuildingRenderer;

/**
 * Created by ECollomb on 10/03/2015.
 */
public class HQScene extends Scene {

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
            Shape shape = this.mRegisteredTouchableShape.get(i);
            if(shape instanceof HQBuildingRenderer && pGestureType.equals(MyGestureDetector.GestureType.PAN)){
                if(((HQBuildingRenderer)shape).isFocused()){
                    shape.onTouch(pGestureType, touchX, touchY);
                }
            }
            else if(shape instanceof HQBuildingRenderer && pGestureType.equals(MyGestureDetector.GestureType.TOUCHDOWN)){
                if(!shape.contains(touchX, touchY) && ((HQBuildingRenderer)shape).isFocused()){
                    ((HQBuildingRenderer)shape).focus();
                }
            }
        }

    }

    // ===========================================================
    // Methods
    // ===========================================================

}
