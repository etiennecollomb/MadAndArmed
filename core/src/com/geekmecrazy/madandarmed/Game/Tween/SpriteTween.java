package com.geekmecrazy.madandarmed.Game.Tween;

import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

public class SpriteTween implements TweenAccessor<Sprite> {

    public static final int POSITION_X = 1;
    public static final int POSITION_Y = 2;
    public static final int SCALE = 3;
    public static final int COLOR = 4;

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        switch(tweenType) {
            case POSITION_X:
                returnValues[0] = target.getX();
                return 1;
            case SCALE:
                returnValues[0] = target.getScaleX();
                returnValues[1] = target.getScaleY();
                return 2;
            case COLOR:
                returnValues[0] = target.getColor().r;
                returnValues[1] = target.getColor().g;
                returnValues[2] = target.getColor().b;
                return 3;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POSITION_X:
                target.setX(newValues[0]);
                break;
            case SCALE:
                target.setScaleX(newValues[0]);
                target.setScaleY(newValues[1]);
                break;
            case COLOR:
                target.setColor(newValues[0],newValues[1],newValues[2],target.getColor().a);
                break;
            default:
                assert false;
                break;
        }
    }
}
