package com.geekmecrazy.madandarmed.Game.Tween;

import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.badlogic.gdx.graphics.OrthographicCamera;

import aurelienribon.tweenengine.TweenAccessor;

public class OrthographicCameraTween implements TweenAccessor<OrthographicCamera> {

    public static final int TRANSLATE = 0;
    public static final int POSITION_X = 1;
    public static final int POSITION_Y = 2;
    public static final int SCALE = 3;

    @Override
    public int getValues(OrthographicCamera target, int tweenType, float[] returnValues) {
        switch(tweenType) {
            case TRANSLATE:
                returnValues[0] = target.position.x;
                returnValues[1] = target.position.y;
                return 2;
            case SCALE:
                returnValues[0] = target.zoom;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(OrthographicCamera target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case TRANSLATE:
                target.position.x = newValues[0];
                target.position.y = newValues[1];
                break;
            case SCALE:
                target.zoom = newValues[0];
                break;
            default:
                assert false;
                break;
        }
    }
}
