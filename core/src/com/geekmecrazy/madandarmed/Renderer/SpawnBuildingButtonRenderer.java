package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;


public class SpawnBuildingButtonRenderer extends Shape {

    Sprite shadow = new Sprite();
    Sprite background = new Sprite();
    Sprite millitary = new Sprite();
    Sprite reflet = new Sprite();

    // ===========================================================
    // Constructors
    // ===========================================================

    public SpawnBuildingButtonRenderer() {
        super();

        shadow.init(TextureType.BUTTON_UNIT_SHADOW);
        background.init(TextureType.BUTTON_UNIT_BACKGROUND);
        reflet.init(TextureType.BUTTON_UNIT_REFLET);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void setColor(float r, float g, float b, float a) {
        super.setColor(r, g, b, a);

        shadow.setColor(r, g, b, a);
        background.setColor(r, g, b, a);
        millitary.setColor(r, g, b, a);
        reflet.setColor(r, g, b, a);
    }

    @Override
    public void setSize(float w,float h){
        super.setSize(w,h);

        shadow.setSize(w,h);
        background.setSize(w,h);
        millitary.setSize(w,h);
        reflet.setSize(w,h);
    }

    @Override
    public void setScaleX(final float scaleX) {
        super.setScaleX(scaleX);

        shadow.setScaleX(scaleX);
        background.setScaleX(scaleX);
        millitary.setScaleX(scaleX);
        reflet.setScaleX(scaleX);
    }

    @Override
    public void setScaleY(final float scaleY) {
        super.setScaleY(scaleY);

        shadow.setScaleY(scaleY);
        background.setScaleY(scaleY);
        millitary.setScaleY(scaleY);
        reflet.setScaleY(scaleY);
    }

    @Override
    public void setScale(final float scale) {
        this.setScaleX(scale);
        this.setScaleY(scale);
    }

    @Override
    public void onDraw(){
    	if(this.isVisible()) {
    		super.onDraw();
    	}
    }

    @Override
    public void reset() {
        super.reset();

        this.millitary.reset();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void init(final TextureType pTextureType, final float pX, final float pY, final float pWidth, final float pHeight){
        super.init(pX, pY, pWidth, pHeight);

        millitary.init(pTextureType);

        this.attachChild(shadow, Entity.Alignment.CENTER);
        this.attachChild(background, Entity.Alignment.CENTER);
        this.attachChild(millitary, Entity.Alignment.CENTER);
        this.attachChild(reflet, Entity.Alignment.CENTER);
    }


}
