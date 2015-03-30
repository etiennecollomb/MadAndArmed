package com.geekmecrazy.madandarmed.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by zlithgow on 06/03/15.
 */
public class Assets {

    private static TextureAtlas uiAtlas;

    public static TextureRegion buttonUnitBG;
    public static TextureRegion buttonUnitReflect;
    public static TextureRegion buttonUnitShadow;
    public static TextureRegion greenArrowBottom;
    public static TextureRegion greenArrowLeft;
    public static TextureRegion greenArrowRight;
    public static TextureRegion greenArrowTop;
    public static TextureRegion scoreBarBG;
    public static TextureRegion scoreBarBlue;
    public static TextureRegion scoreBarBorder;
    public static TextureRegion scoreBarReflect;
    public static TextureRegion scoreBarYellow;

    public static void load(){
        //uiAtlas = new TextureAtlas(Gdx.files.internal("images/"+dpi+"-atlas/"+dpi+"-atlas.txt"),Gdx.files.internal("images/"+dpi+"-atlas/"));
        uiAtlas = new TextureAtlas(Gdx.files.internal("game/ui-atlas/ui-atlas.txt"),Gdx.files.internal("game/ui-atlas/"));

        buttonUnitBG = uiAtlas.findRegion("button_unit_background");
        buttonUnitReflect = uiAtlas.findRegion("button_unit_background_reflet");
        buttonUnitShadow = uiAtlas.findRegion("button_unit_background_shadow");
        greenArrowBottom = uiAtlas.findRegion("greenArrow_bottom");
        greenArrowLeft = uiAtlas.findRegion("greenArrow_left");
        greenArrowRight = uiAtlas.findRegion("greenArrow_right");
        greenArrowTop = uiAtlas.findRegion("greenArrow_top");
        scoreBarBG = uiAtlas.findRegion("score_bar_background");
        scoreBarBlue = uiAtlas.findRegion("score_bar_blue");
        scoreBarBorder = uiAtlas.findRegion("score_bar_border");
        scoreBarReflect = uiAtlas.findRegion("score_bar_reflet");
        scoreBarYellow = uiAtlas.findRegion("score_bar_yellow");
    }

    public static void dispose(){
        uiAtlas.dispose();
    }
}
