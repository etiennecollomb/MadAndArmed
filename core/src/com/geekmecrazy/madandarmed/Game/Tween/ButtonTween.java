package com.geekmecrazy.madandarmed.Game.Tween;

import com.geekmecrazy.madandarmed.Game.UI.Button;

import aurelienribon.tweenengine.TweenAccessor;

public class ButtonTween implements TweenAccessor<Button> {

    public static final int POSITION_X = 1;
    public static final int POSITION_Y = 2;
    public static final int SCALE = 3;

    @Override
    public int getValues(Button target, int tweenType, float[] returnValues) {
        switch(tweenType) {
            case POSITION_X:
                returnValues[0] = target.getX();
                return 1;
            case SCALE:
                returnValues[0] = target.getScaleX();
                returnValues[1] = target.getScaleY();
                return 2;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Button target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POSITION_X:
                target.setX(newValues[0]);
                break;
            case SCALE:
                target.setScaleX(newValues[0]);
                target.setScaleY(newValues[1]);
                break;
            default:
                assert false;
                break;
        }
    }
}
