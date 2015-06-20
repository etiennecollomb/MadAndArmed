package com.geekmecrazy.madandarmed.Entity.Scene;

import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.TouchData;
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
    public void onTouch(){
        super.onTouch();

        int size = this.mRegisteredTouchableShape.size;
        for(int i=0; i<size; i++){
            Shape shape = this.mRegisteredTouchableShape.get(i);
            if(shape instanceof HQBuildingRenderer && TouchData.gestureType.equals(MyGestureDetector.GestureType.PAN)){
                if(((HQBuildingRenderer)shape).isFocused()){
                    shape.onTouch();
                }
            }
            else if(shape instanceof HQBuildingRenderer && TouchData.gestureType.equals(MyGestureDetector.GestureType.TOUCHDOWN)){
                if(!shape.contains(TouchData.screenTouchX, TouchData.screenTouchY) && ((HQBuildingRenderer)shape).isFocused()){
                    ((HQBuildingRenderer)shape).focus();
                }
            }
        }

    }

    // ===========================================================
    // Methods
    // ===========================================================

}
