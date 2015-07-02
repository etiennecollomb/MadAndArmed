package com.geekmecrazy.madandarmed.Input;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.badlogic.gdx.input.GestureDetector;

public class MyGestureDetector extends GestureDetector {

    /** Gesture Type */
    public static enum GestureType{
        TOUCHDOWN,
        TOUCHUP,
        TAP,
        LONGPRESS,
        FLING,
        PAN,
        PANSTOP,
        ZOOM,
        PINCH;
    }

    public MyGestureDetector(MyGestureListener listener) {
        super(20, 0.4f, 0.65f, 0.15f, listener);
    }

    @Override
    public boolean touchUp(float x, float y, int pointer, int button) {
        super.touchUp(x, y, pointer, button);
        TouchData.gestureType = MyGestureDetector.GestureType.TOUCHUP;
    	TouchData.touchX = x;
    	TouchData.touchY = y;
        ScreenManager.getCurrentScreen().onTouch();
        return false;
    }

    /** mouse middle button Zoom */
    @Override
    public boolean scrolled(int amount) {
        GlobalManager.camera.zoom += (amount*0.5f);
        return true;
    }

}
