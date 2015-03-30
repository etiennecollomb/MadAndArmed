package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Core.TextureBuilder;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Entity.Sprite.AnimatedSprite;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Element.HQBuilding;
import com.geekmecrazy.madandarmed.Game.Tween.OrthographicCameraTween;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Game.UI.Layout;
import com.geekmecrazy.madandarmed.Screen.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

public class HQScreen extends Screen implements IUpdatable {

    private static HQScreen hqScreen;
    private Sprite[][] bg;
    private HQBuilding hq;

    /** Access au manager */
    public static HQScreen getManager(){
        if (hqScreen == null)
            hqScreen = new HQScreen();
        return hqScreen;
    }

    /** Creation et initialisation du manager */
    public void init(final Scene pScene) {
        super.init(pScene);

        bg = new Sprite[5][2];

        for(int i = 0 ; i < bg.length ; i++){
            for(int j = 0 ; j < bg[i].length ; j++){
                bg[i][j] = new Sprite();
                bg[i][j].init(TextureType.GROUND_BATTLE);
                bg[i][j].setPosition(j*bg[i][j].getWidth() + bg[i][j].getWidth()/2f,(i*bg[i][j].getHeight() + bg[i][j].getHeight()/2f));
            }
        }

        for(int i = 0 ; i < bg.length ; i++){
            for(int j = 0 ; j < bg[i].length ; j++){
                pScene.attachChild(bg[i][j]);
            }
        }

        //hq.setAlignment(Entity.Alignment.CENTER);
        //hq.setPosition(1200 - hq.getWidth()/2f,1200 - hq.getHeight()/2f);

        Grid grid = new Grid(30,30);
        grid.init(0,0);
        grid.sync(pScene);

        hq = new HQBuilding();
        hq.init(TextureType.BUILDING_TEST);
        hq.setAlignment(Entity.Alignment.NONE);
        hq.setGrid(grid);
        hq.setSize(grid.widthFor(3),grid.heightFor(3));

        grid.snap(hq,9,9);

        pScene.attachChild(grid);

        pScene.attachChild(hq);
        pScene.registerTouchableShape(hq);

        //TEST
//        TextureBuilder textureBuilder = new TextureBuilder();
//        Texture spriteTexture = textureBuilder.getTexture();
//
//        Sprite sprite = new Sprite();
//        sprite.init(0, 0, spriteTexture.getWidth(), spriteTexture.getHeight());
//        sprite.setTextureRegion(new TextureRegion(spriteTexture));
//
//        //sprite.setPosition( 1024, 1024 );
//        sprite.setAlignment(Entity.Alignment.CENTER);
//        pScene.attachChild(sprite);
        //TEST

        /*Layout lBottom = new Layout();
        lBottom.init(0,0);
        lBottom.setLayoutSize(Layout.Dimension.MATCH_PARENT,1.6f);

        Layout l1 = new Layout();
        l1.init(0,0);
        l1.setOrientation(Layout.Orientation.HORIZONTAL);
        l1.setLayoutSize(Layout.Dimension.WRAP_CONTENT, Layout.Dimension.WRAP_CONTENT);

        int numberOfButtons = 7;

        for(int i = 0 ; i < numberOfButtons ; i++){
            Button b = new Button();
            b.init(0,0, TextureType.BUTTON_UNIT_BACKGROUND);
            b.setSize(1.2f,1.2f);
            this.getManager().getHUD().registerTouchableShape(b);
            l1.add(b);
        }

        lBottom.add(l1, Entity.Alignment.CENTER);

        this.getHUD().attachChild(lBottom, Entity.Alignment.CENTER_BOTTOM);*/
    }

    @Override
    public void show() {
        System.out.println("Show HQ Screen");

        Timeline.createSequence()
                .beginParallel()
                .push(Tween.to(GlobalManager.camera, OrthographicCameraTween.TRANSLATE, 1.2f).target(1200f,1200f))
                .push(Tween.to(GlobalManager.camera, OrthographicCameraTween.SCALE, 1.2f).target(1f))
                .end()
                .start(GlobalManager.getTweenManager());
    }
}
