package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Game.Element.Life;


public class LifeBarRenderer extends Shape {

    private static final int BORDER = 2;

    private Life mLife;

    private Rectangle mLifeBarBackground;

    private Rectangle mLifeBar;

    private float mLifeBarMaxWidth;

    // ===========================================================
    // Constructors
    // ===========================================================

    public LifeBarRenderer() {
        super();

        this.setLifeBarBackground( new Rectangle() );
        this.setLifeBar( new Rectangle() );
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public Life getLife() {
        return mLife;
    }

    public void setLife(Life pLife) {
        this.mLife = pLife;
    }

    public Rectangle getLifeBarBackground() {
        return mLifeBarBackground;
    }

    public void setLifeBarBackground(final Rectangle pLifeBarBackground) {
        this.mLifeBarBackground = pLifeBarBackground;
    }

    public Rectangle getLifeBar() {
        return mLifeBar;
    }

    public void setLifeBar(final Rectangle pLifeBar) {
        this.mLifeBar = pLifeBar;
    }

    public float getLifeBarMaxWidth() {
        return mLifeBarMaxWidth;
    }

    public void setLifeBarMaxWidth(float pLifeBarMaxWidth) {
        this.mLifeBarMaxWidth = pLifeBarMaxWidth;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onDraw(){

        if(this.isVisible()) {
            float ratio = this.getLife().getLifeRatio();
            this.getLifeBar().setWidth(this.getLifeBarMaxWidth() * ratio);

            // Tjrs aligné a gauche, malgré sa taille
            this.getLifeBar().setX(-(this.getLifeBarMaxWidth() - this.getLifeBar().getWidth()) / 2f);

            if (ratio >= 0.5f)
                this.getLifeBar().setColor(2f - 2f * ratio, 1f, 0f, 1f);
            else
                this.getLifeBar().setColor(1f, 2f * ratio, 0f, 1f);

            super.onDraw();
        }
    }

    @Override
    public void reset() {
        super.reset();

        this.setLife(null);
        this.setLifeBarMaxWidth(0);

        //TODO : foutre dans un pool et appeler Free()
        this.getLifeBarBackground().reset();
        this.getLifeBar().reset();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void init(final Life pLife, final float pX, final float pY, final float pWidth, final float pHeight){
        super.init(pX, pY, pWidth, pHeight);

        this.setLife(pLife);

        this.setLifeBarMaxWidth(pWidth);

        // Background
        this.getLifeBarBackground().init(0, 0, this.getLifeBarMaxWidth()+2*BORDER, pHeight+2*BORDER);
        this.getLifeBarBackground().setColor(0f, 0f, 0f, 0.25f);
        this.attachChild(this.getLifeBarBackground(), Entity.Alignment.CENTER);

        // Life Bar
        this.getLifeBar().init(0, 0, this.getLifeBarMaxWidth(), pHeight);
        this.getLifeBar().setColor(0f, 1f, 0f, 1f);
        this.attachChild(this.getLifeBar(), Entity.Alignment.CENTER);

    }


}
