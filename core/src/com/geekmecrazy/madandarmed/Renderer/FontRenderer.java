package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class FontRenderer extends Shape {

    private String mText;

    private BitmapFont mBitmapFont;

    private BitmapFont.TextBounds mTextBounds;

    // ===========================================================
    // Constructors
    // ===========================================================

    public FontRenderer(BitmapFont pBitmapFont) {
        super();

        this.mBitmapFont = pBitmapFont;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public BitmapFont getBitmapFont() {
        return mBitmapFont;
    }

    public void setBitmapFont(final BitmapFont pBitmapFont) {
        this.mBitmapFont = pBitmapFont;
    }

    public BitmapFont.TextBounds getTextBounds() {
        return mTextBounds;
    }

    public void setTextBounds(BitmapFont.TextBounds pTextBounds) {
        this.mTextBounds = pTextBounds;
    }

    public String getText() {
        return mText;
    }

    public void setText(final String pText) {

        this.mText = pText;
        this.setTextBounds(this.mBitmapFont.getBounds(pText));

        if(pText.equals("")) {
            this.setWidth(0);
            this.setHeight(0);
        }else{
            this.setWidth(this.getTextBounds().width);
            this.setHeight(this.getTextBounds().height);
        }

    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onDraw(){
        if(this.isVisible()) {
            //font are not perfectly centered in Pixel
            //TODO : should be a parameter in enum depending of font type to get in the init
            //should be global variable
            int correctedOffsetX = -1;
            int correctedOffsetY = -4;
            GlobalManager.spriteBatchDrawFont(this.mBitmapFont, this.getText(), this.getSceneX(), this.getSceneY(), this.getScaleX(), this.getScaleY(), this.getTextBounds().height, correctedOffsetX, correctedOffsetY);
            this.onDrawChildren();
        }
    }

    @Override
    public void reset() {
        super.reset();

        this.setText("");

    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void init(final String pText, final float pX, final float pY){
        super.init(pX, pY, 0, 0);

        this.setText(pText);
    }


}
