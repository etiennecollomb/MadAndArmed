package com.geekmecrazy.madandarmed.Game.Tween;

import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Game.UI.Button;

import aurelienribon.tweenengine.TweenAccessor;

public class RectangleTween implements TweenAccessor<Rectangle> {

    public static final int POSITION_X = 1;
    public static final int POSITION_Y = 2;
    public static final int SCALE = 3;
    public static final int ALPHA = 4;

    @Override
    public int getValues(Rectangle target, int tweenType, float[] returnValues) {
        switch(tweenType) {
            case POSITION_X:
                returnValues[0] = target.getX();
                return 1;
            case SCALE:
                returnValues[0] = target.getScaleX();
                returnValues[1] = target.getScaleY();
                return 2;
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Rectangle target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POSITION_X:
                target.setX(newValues[0]);
                break;
            case SCALE:
                target.setScaleX(newValues[0]);
                target.setScaleY(newValues[1]);
                break;
            case ALPHA:
                target.setColor(target.getColor().r,target.getColor().g,target.getColor().b,newValues[0]);
            default:
                assert false;
                break;
        }
    }
}
