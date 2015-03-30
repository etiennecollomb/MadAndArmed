package com.geekmecrazy.madandarmed.Entity;

import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.MyGestureListener;
import com.badlogic.gdx.graphics.Color;

public class Shape extends Entity implements IColor, ITouchable {

    private Color mColor;

    // ===========================================================
    // Constructors
    // ===========================================================

    public Shape() {
        super();

        this.mColor = new Color();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onTouch(final MyGestureDetector.GestureType pGestureType, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
    }

    @Override
    public boolean contains(final float pX, final float pY){

        /** touch bound */
        float X1 = this.getSceneX() + this.getCenterX() - this.getWidth()*this.getSceneScaleX()/2f ;
        float Y1 = this.getSceneY() + this.getCenterY() - this.getHeight()*this.getSceneScaleY()/2f ;
        float X2 = X1 + this.getWidth() * this.getSceneScaleX();
        float Y2 = Y1 + this.getHeight() * this.getSceneScaleY();

        return (pX >= X1 && pX <= X2 && pY >= Y1 && pY <= Y2);
    }

    @Override
    public Color getColor() {
        return mColor;
    }

    @Override
    public void setColor(final float r, final float g, final float b, final float a) {
        this.mColor.set(r, g, b, a);
    }

    /** Pool */
    @Override
    public void reset() {
        super.reset();

        this.setWidth(0);
        this.setHeight(0);

        this.setColor(1f, 1f, 1f, 1f);

    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void init(final float pX, final float pY, final float pWidth, final float pHeight){
        super.init(pX, pY);

        this.setWidth(pWidth);
        this.setHeight(pHeight);

        this.setColor(1f, 1f, 1f, 1f);
    }

}




