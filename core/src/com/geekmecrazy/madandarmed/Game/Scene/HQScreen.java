package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Element.HQBuilding;
import com.geekmecrazy.madandarmed.Game.Tween.OrthographicCameraTween;
import com.geekmecrazy.madandarmed.Renderer.GridRenderer;
import com.geekmecrazy.madandarmed.Screen.Screen;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;

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

        Grid grid = new Grid();
        grid.init(GlobalManager.BIG_NODESIZE, GlobalManager.BIG_NODESIZE, pScene);
        
        GridRenderer gridRenderer = new GridRenderer();
        gridRenderer.init(grid);
        gridRenderer.setVisible(false);
        

        hq = new HQBuilding();
        hq.init(TextureType.BUILDING_TEST);
        hq.setAlignment(Entity.Alignment.NONE);
        hq.setGrid(grid);
        hq.setSize(grid.widthFor(3),grid.heightFor(3));

        grid.snap(hq,grid.getCols()/2, grid.getRows()/2);

        pScene.attachChild(gridRenderer);

        pScene.attachChild(hq);
        pScene.registerTouchableShape(hq);

    }

    @Override
    public void show() {
        System.out.println("Show HQ Screen");

        Timeline.createSequence()
                .beginParallel()
                .push(Tween.to(GlobalManager.camera, OrthographicCameraTween.TRANSLATE, 1.2f).target(this.getScene().getWidth()/2f, this.getScene().getHeight()/2f))
                .push(Tween.to(GlobalManager.camera, OrthographicCameraTween.SCALE, 1.2f).target(1.5f))
                .end()
                .start(GlobalManager.getTweenManager());
    }
}
