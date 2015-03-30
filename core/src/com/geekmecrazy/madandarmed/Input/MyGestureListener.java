package com.geekmecrazy.madandarmed.Input;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Tween.OrthographicCameraTween;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Quad;
import aurelienribon.tweenengine.equations.Quint;

public class MyGestureListener implements GestureDetector.GestureListener {

    /** Zoom */
    private float initialScale=1f;

    /** Scroll */
//    private boolean first2fingersScroll;
//    private Vector2 previousPos1 = new Vector2(0,0);
//    private Vector2 previousPos2 = new Vector2(0,0);
//    private Vector2 delta1 = new Vector2(0,0);
//    private Vector2 delta2 = new Vector2(0,0);

    private Tween cameraVelocityTween;
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
            ScreenManager.getCurrentScreen().onTouch(MyGestureDetector.GestureType.TOUCHDOWN, pX, pY);
//        }

        return false;
    }

    @Override
    public boolean tap(final float pX, final float pY, final int pCount, final int pButton) {

        ScreenManager.getCurrentScreen().onTouch(MyGestureDetector.GestureType.TAP, pX, pY);
        return false;
    }

    @Override
    public boolean longPress(final float pX, final float pY) {
        ScreenManager.getCurrentScreen().onTouch(MyGestureDetector.GestureType.LONGPRESS, pX, pY);

        return false;
    }

    @Override
    public boolean fling(final float pVelocityX, final float pVelocityY, final int pButton) {
        // TODO Auto-generated method stub

//        System.out.println("##### FLING " + numberOfActiveTouch);

        float velocityRatio = 0.3f;

        float targetX = GlobalManager.camera.position.x - velocityRatio* pVelocityX *GlobalManager.camera.zoom;
        float targetY = GlobalManager.camera.position.y + velocityRatio* pVelocityY *GlobalManager.camera.zoom;

        if(cameraVelocityTween != null) cameraVelocityTween.kill();
        cameraVelocityTween = Tween.to(GlobalManager.camera, OrthographicCameraTween.TRANSLATE, 1f)
                    .target(targetX, targetY)
                    .ease(Quint.OUT)
                    .start(GlobalManager.getTweenManager());

        return false;
    }


    @Override
    public boolean pan(final float pX, final float pY, final float pDeltaX, final float pDeltaY) {
        //MOUSE CONTROL
        if(cameraVelocityTween != null) cameraVelocityTween.kill();

        if(GlobalManager.moveable) {
            GlobalManager.camera.translate(-pDeltaX * GlobalManager.camera.zoom, pDeltaY * GlobalManager.camera.zoom);
            //FINGER CONTROL
//            ScreenManager.getCurrentScreen().getScene().onTouch(MyGestureDetector.GestureType.PAN, pX, pY); //Selection d unite
        }

        return false;
    }

    @Override
    public boolean panStop(final float pX, final float pY, final int pPointer, final int pButton) {
        ScreenManager.getCurrentScreen().getScene().onTouch(MyGestureDetector.GestureType.PANSTOP, pX, pY);
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
