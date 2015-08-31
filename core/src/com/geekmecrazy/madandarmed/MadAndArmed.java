package com.geekmecrazy.madandarmed;

import com.geekmecrazy.madandarmed.Assets.Assets;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.MenuScreen;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Game.UI.Layout;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.geekmecrazy.madandarmed.Utils.FPSControl;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;


public class MadAndArmed extends ApplicationAdapter {

    public static final String LOG = "LOG_MADANDARMED";
    private GlobalManager globalManager;
	
	private FPSControl fpsControl;
	
	private FPSLogger fpsLogger;

	@Override
	public void create () {
		
		/** 24 fps */
		fpsControl = new FPSControl(25); //25 fps
		
		System.out.println("### Gdx.graphics: " + Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
		fpsLogger = new FPSLogger();

        Assets.load();

		globalManager = GlobalManager.getManager();
		globalManager.init();

		/** MAD AND ARMED GAME */
	    /*Scene fightScene = new Scene();
		fightScene.init(0, 0);

        ScreenManager.setCurrentScreen(FightScreen.getManager());

		FightScreen.getManager().init(fightScene);
		FightScreen.getManager().loadData(); //from xml, et precalcul
		FightScreen.getManager().newGame();
*/

        //MENU SCREEN
        Scene menuScene = new Scene();
        menuScene.init(GlobalManager.MENU_SCENE_WIDTH, GlobalManager.MENU_SCENE_HEIGHT);
        MenuScreen.getManager().init(menuScene);
        ScreenManager.setCurrentScreen(MenuScreen.getManager());

        ////// TEST //////

//        Sprite sprite1 = new Sprite();
//        sprite1.init(TextureType.BLOOD_ON_FLOOR);
//        sprite1.setPosition(10f, 10f);
//        menuScene.attachChild(sprite1);

//        Entity entity1 = new Entity();
//        entity1.init(0, 0);
//        menuScene.attachChild(entity1);
//
//		Rectangle rect1 = new Rectangle(){
//            @Override
//            public void onTouch(){
//                System.out.println("#### TOUCH !!");
//            }
//        };
//        rect1.init(0, 0, 200, 200);
//        rect1.setColor(0f, 0f, 1f, 1f);
//        menuScene.attachChild(rect1);
//        rect1.setAlignment(Entity.Alignment.CENTER);
//        menuScene.registerTouchableShape(rect1);

		Rectangle rect2 = new Rectangle();
        rect2.init(0, 0, 100, 100);
		rect2.setColor(1f, 0f, 0f, 1f);
//		rect1.attachChild(rect2);
//        rect2.setScale(0.66f);

        Rectangle rect3 = new Rectangle();
        rect3.init(0, 0, 50, 50);
        rect3.setColor(0f, 1f, 0f, 1f);
//        rect2.attachChild(rect3);
//        rect3.setAlignment(Entity.Alignment.RIGHT);
//        rect3.setAlignment(Entity.Alignment.TOP);


        ///LAYOUT

        Layout layout1 = new Layout();
        layout1.init(0, 0);
        layout1.add(rect2);
//        layout1.add(rect1);
        layout1.add(rect3);

        menuScene.attachChild(layout1);
        layout1.setAlignment(Entity.Alignment.CENTER);

        ////// FIN TEST //////

	}


	@Override
	public void render () {
		//Update
		this.onUpdate();

		//Camera
		GlobalManager.updateCamera();

		//Draw
		GlobalManager.renderCurrentScreen();

		//control du FPS
		fpsControl.check();

		//FSP log
		fpsLogger.log();

	}

	//Update
	public void onUpdate(){
		ScreenManager.getCurrentScreen().onUpdate();
	}


}




