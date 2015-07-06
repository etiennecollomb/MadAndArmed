package com.geekmecrazy.madandarmed.Game.Tween;

import com.geekmecrazy.madandarmed.Game.UI.Layout;

import aurelienribon.tweenengine.TweenAccessor;

public class LayoutTween implements TweenAccessor<Layout> {

	public static final int TRANSLATE = 0;

	@Override
	public int getValues(Layout target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case TRANSLATE:
			returnValues[0] = target.getTranslateX();
			returnValues[1] = target.getTranslateY();
			return 2;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Layout target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case TRANSLATE:
			target.setTranslateX(newValues[0]);
			target.setTranslateY(newValues[1]);
			break;
		default:
			assert false;
			break;
		}
	}

}
