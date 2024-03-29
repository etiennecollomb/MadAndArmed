package com.geekmecrazy.madandarmed.Input;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class MyGestureListener implements GestureDetector.GestureListener {

    /** Zoom */
    private float initialScale=1f;

    /** Scroll */
//    private boolean first2fingersScroll;
//    private Vector2 previousPos1 = new Vector2(0,0);
//    private Vector2 previousPos2 = new Vector2(0,0);
//    private Vector2 delta1 = new Vector2(0,0);
//    private Vector2 delta2 = new Vector2(0,0);

    private int numberOfActiveTouch = 0;


    @Override
    public boolean touchDown(final float pX, final float pY, final int pPointer, final int pButton) {

        //How many fingers?
        numberOfActiveTouch = 0;
        for (int i = 0; i < 20; i++) {
            if (Gdx.app.getInput().isTouched(i)) numberOfActiveTouch++;
        }

        //TWO fingers
        if(numberOfActiveTouch==2){
            this.initialScale = GlobalManager.camera.zoom;
        }

//        //ONE finger
//        else{
        	TouchData.gestureType = MyGestureDetector.GestureType.TOUCHDOWN;
        	TouchData.touchX = pX;
        	TouchData.touchY = pY;
            ScreenManager.getCurrentScreen().onTouch();
//        }

        return false;
    }

    @Override
    public boolean tap(final float pX, final float pY, final int pCount, final int pButton) {

    	TouchData.gestureType = MyGestureDetector.GestureType.TAP;
    	TouchData.touchX = pX;
    	TouchData.touchY = pY;
        ScreenManager.getCurrentScreen().onTouch();
        return false;
    }

    @Override
    public boolean longPress(final float pX, final float pY) {
    	TouchData.gestureType = MyGestureDetector.GestureType.LONGPRESS;
    	TouchData.touchX = pX;
    	TouchData.touchY = pY;
        ScreenManager.getCurrentScreen().onTouch();

        return false;
    }

    @Override
    public boolean fling(final float pVelocityX, final float pVelocityY, final int pButton) {

    	TouchData.gestureType = MyGestureDetector.GestureType.FLING;
    	TouchData.velocityX = pVelocityX;
    	TouchData.velocityY = pVelocityY;
    	ScreenManager.getCurrentScreen().onTouch();

        return false;
    }


    @Override
    public boolean pan(final float pX, final float pY, final float pDeltaX, final float pDeltaY) {

        TouchData.gestureType = MyGestureDetector.GestureType.PAN;
    	TouchData.touchX = pX;
    	TouchData.touchY = pY;
    	TouchData.deltaX = pDeltaX;
    	TouchData.deltaY = pDeltaY;
        ScreenManager.getCurrentScreen().onTouch();
		
        return false;
    }

    @Override
    public boolean panStop(final float pX, final float pY, final int pPointer, final int pButton) {
    	
    	TouchData.gestureType = MyGestureDetector.GestureType.PANSTOP;
    	TouchData.touchX = pX;
    	TouchData.touchY = pY;
        ScreenManager.getCurrentScreen().getScene().onTouch();
        return false;
    }

    @Override
    public boolean zoom(final float pInitialDistance, final float pDistance) {
        GlobalManager.camera.zoom = this.initialScale * (pInitialDistance / pDistance);
        return false;
    }

    /** Gestion du Scroll avec 2 doigts */
    @Override
    public boolean pinch(final Vector2 pInitialPointer1, final Vector2 pInitialPointer2,
                         final Vector2 pPointer1, final Vector2 pPointer2) {

//        if(first2fingersScroll){ //initial setting
//            previousPos1.set(pPointer1);
//            previousPos2.set(pPointer2);
//            first2fingersScroll = false;
//        }
//
//        delta1.set(pPointer1).sub(previousPos1);
//        delta2.set(pPointer2).sub(previousPos2);
//
//        //Mouvement moyen des 2 fingers (pour le scroll)
//        float pDeltaX = (delta1.x + delta2.x)/2f;
//        float pDeltaY = (delta1.y + delta2.y)/2f;
//        GlobalManager.camera.translate(-pDeltaX * GlobalManager.camera.zoom, pDeltaY * GlobalManager.camera.zoom);
//
//        previousPos1.set(pPointer1);
//        previousPos2.set(pPointer2);

        return false;
    }
}
