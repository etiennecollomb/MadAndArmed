package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Scene.FightScene;
import com.geekmecrazy.madandarmed.Entity.Scene.HQScene;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Entity.Sprite.AnimatedSprite;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Tween.RectangleTween;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Game.UI.Layout;
import com.geekmecrazy.madandarmed.Game.UI.Layout.Orientation;
import com.geekmecrazy.madandarmed.Renderer.UniqueActionRenderer;
import com.geekmecrazy.madandarmed.Screen.Screen;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quad;


public class MenuScreen extends Screen implements IUpdatable {

    // ===========================================================
    // Singleton manager
    // ===========================================================

    private static MenuScreen menuScreen;
    private Rectangle blackScreen;
    private Button hqButton;
    private Button worldButton;
    

    /** Access au manager */
    public static MenuScreen getManager(){
        if (menuScreen == null)
            menuScreen = new MenuScreen();
        return menuScreen;
    }

    /** Creation et initialisation du manager */
    public void init(final Scene pScene) {
        super.init(pScene);

        blackScreen = new Rectangle();
        blackScreen.init(0,0,this.getHUD().getWidth(),this.getHUD().getHeight());
        blackScreen.setColor(0,0,0,0f);

        worldButton = new Button();
        worldButton.init(0, 0, TextureType.BUTTON_UNIT_BACKGROUND);
        worldButton.setSize(3f, 1.2f);
        worldButton.setAction(new IAction(){
            // Pour le moment ça va lancer le fight direct ce bouton au lieu du world
            @Override
            public void execute(){
                showWorldScreen();
            }
        });
        MenuScreen.getManager().getHUD().registerTouchableShape(worldButton);

        hqButton = new Button();
        hqButton.init(0, 0, TextureType.BUTTON_UNIT_BACKGROUND);
        hqButton.setSize(3f, 1.2f);
        hqButton.setAction(new IAction(){
            @Override
            public void execute(){
            	fireThrower(); //TEST
                //showHQScreen(); //A REMETTRE POUR LE QG
            }
        });
        MenuScreen.getManager().getHUD().registerTouchableShape(hqButton);

        
        //TEST/////////////////////////////////////////
        Rectangle title = new Rectangle();
        title.init(0,0,7,1.6f);
        title.setColor(1,0,0,1);

        Layout l1 = new Layout();
        l1.init(0,0);
        l1.add(worldButton);
        l1.add(hqButton);

        Layout titleLayout = new Layout();
        titleLayout.init(0,0);
        titleLayout.setLayoutSize(Layout.Dimension.MATCH_PARENT, Layout.Dimension.WRAP_CONTENT);
        titleLayout.add(title,Entity.Alignment.CENTER);

        this.getHUD().attachChild(titleLayout, Entity.Alignment.CENTER_TOP);
        this.getHUD().attachChild(l1,Entity.Alignment.CENTER);
        this.getHUD().attachChild(blackScreen, Entity.Alignment.CENTER);
        
        //TEST scrollable
        Rectangle r1 = new Rectangle();
        r1.init(0,0,3f, 1.2f);
        r1.setColor(0,1,0f,1);
        
        Rectangle r2 = new Rectangle();
        r2.init(0,0,3f, 1.2f);
        r2.setColor(0.5f,1,0,1);
        
        Rectangle r3 = new Rectangle();
        r3.init(0,0,3f, 1.2f);
        r3.setColor(0,1,1f,1);
        
        Sprite sp1 = new Sprite();
        sp1.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp2 = new Sprite();
        sp2.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp3 = new Sprite();
        sp3.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp4 = new Sprite();
        sp4.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp5 = new Sprite();
        sp5.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp6 = new Sprite();
        sp6.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp7 = new Sprite();
        sp7.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp8 = new Sprite();
        sp8.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp9 = new Sprite();
        sp9.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp10 = new Sprite();
        sp10.init(TextureType.BUTTON_UNIT_BACKGROUND);
        Sprite sp11 = new Sprite();
        sp11.init(TextureType.BUTTON_UNIT_REFLET);
        
        Layout layout_test;
        layout_test = new Layout();
        layout_test.init(0,0);
        layout_test.setOrientation(Orientation.HORIZONTAL);
        layout_test.setLayoutSize(7f, Layout.Dimension.WRAP_CONTENT);
//        layout_test.setOrientation(Orientation.VERTICAL);
//        layout_test.setLayoutSize(Layout.Dimension.WRAP_CONTENT, 6f);
        layout_test.add(sp1);
        layout_test.add(sp2);
        layout_test.add(sp3);
        layout_test.add(sp4);
        layout_test.add(sp5);
        layout_test.add(sp6);
        layout_test.add(sp7);
        layout_test.add(sp8);
        layout_test.add(sp9);
        layout_test.add(sp10);
        layout_test.add(sp11);
//        layout_test.add(r1);
//        layout_test.add(r2);
//        layout_test.add(r3);
        this.getHUD().attachChild(layout_test, Entity.Alignment.CENTER_BOTTOM);
//        this.getHUD().attachChild(layout_test, Entity.Alignment.CENTER_LEFT);
        MenuScreen.getManager().getHUD().registerTouchableShape(layout_test);
        
        
    }

    @Override
    public void show() {
        System.out.println("Show Menu Screen");
    }

    public void showWorldScreen(){
        Tween.to(blackScreen, RectangleTween.ALPHA, 0.45f)
                .target(1f)
                .setCallbackTriggers(TweenCallback.END)
                .setCallback(new TweenCallback() {

                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        Scene fightScene = new FightScene();

                        fightScene.init(GlobalManager.MAP_FIGHT_WIDTH, GlobalManager.MAP_FIGHT_HEIGHT);
                        FightScreen.getManager().init(fightScene);
                        ScreenManager.setCurrentScreen(FightScreen.getManager());

                        FightScreen.getManager().loadData(); //from xml, et precalcul
                        FightScreen.getManager().newGame();

                        //init GROUND
                        /*WORKING : A REMETTRE ON une fois debug fini*/
                        /*
                        MyTiledMapRenderer tiledGround = new MyTiledMapRenderer();
                        tiledGround.init(GlobalManager.MAP_FIGHT_WIDTH, GlobalManager.MAP_FIGHT_HEIGHT, GlobalManager.GROUNDTILEDWIDTH, GlobalManager.GROUNDTILEDHEIGHT);
                        FightScreen.getManager().setTiledGround(tiledGround);
                        */
                    }
                })
                .ease(Quad.OUT)
                .start(GlobalManager.getTweenManager());
    }

    public void showHQScreen(){
        Tween.to(blackScreen, RectangleTween.ALPHA, 0.45f)
                .target(1f)
                .setCallbackTriggers(TweenCallback.END)
                .setCallback(new TweenCallback() {

                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        Scene hqScene = new HQScene();

                        hqScene.init(GlobalManager.HQ_SCENE_WIDTH, GlobalManager.HQ_SCENE_HEIGHT);
                        HQScreen.getManager().init(hqScene);
                        ScreenManager.setCurrentScreen(HQScreen.getManager());
                    }
                })
                .ease(Quad.OUT)
                .start(GlobalManager.getTweenManager());
    }
    
    
    
    // TEST FIRETHROWER
    public void fireThrower(){
    	
    	int delai = 0;
    	int delaiIncrement = 5;
    	float numberOfBalls = 5;
    	float positionX = 20;
    	float positionY = 30;
    	float positionSpaceX = 5;
    	float positionSpaceY = 10;
    	float scale = 0.25f;
    	float scaleIncrement = (1.0f - scale)/numberOfBalls;
    	
    	for(int i=0; i<numberOfBalls; i++){
    		
    		SpriteSheet sp = new SpriteSheet(AnimatedTextureType.FIRE_BLAST_SB2_64PX, true); 
	        UniqueActionRenderer asp1 = new UniqueActionRenderer();
	        asp1.init(sp);
	        asp1.setScalable(true);
	        asp1.setScale(scale);
	        asp1.setStartDelay(delai);
	        asp1.setPosition(positionX, positionY);
	        this.getScene().attachChild(asp1);
	        
	        delai = delai + delaiIncrement;
	        positionX = positionX + positionSpaceX;
	        positionY = positionY + positionSpaceY;
	        scale = scale + scaleIncrement;
	        
    	}
        
        
        
    }


}

