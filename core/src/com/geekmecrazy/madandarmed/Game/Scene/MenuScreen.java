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
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quad;


public class MenuScreen extends Screen implements IUpdatable {
	
	//TEST
	AnimatedSprite as1;
	
	
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
				//showHQScreen(); //A REMETTRE POUR LE QG
				
				
				//TEST
				int x = as1.getCurrentFrameX();
				int y = as1.getCurrentFrameY();
				int maxX = as1.getSpriteSheet().getNumberOfColumn();
				int maxY = as1.getSpriteSheet().getNumberOfRow();
				y++;
				if(y>=maxY){y=0; x++; if(x>=maxX){x=0;} }
				as1.setCurrentFrameX(x);
				as1.setCurrentFrameY(y);
//				fireThrower(20f, 30f, 0.71f, 0.71f); //TEST
				//FINTEST

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
		
		//TEST
		SpriteSheet spshit = new SpriteSheet(AnimatedTextureType.TEST, false);
		as1 = new AnimatedSprite();
		as1.init(spshit, 256, 256);
		as1.setPosition(300f,  300f);
		as1.setCurrentFrame(0);
		this.getScene().attachChild(as1);

	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();
		
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







	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////         TEST        ///////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////


	// TEST FIRETHROWER
	/** from Pos through the direction vector
     dirX, dirY must be unitary vector
	 */
	public void fireThrower(final float posX, final float posY, final float dirX, final float dirY){

		SpriteSheet sp = PoolAnimManager.FIRE_BLAST_001_128PX_SPRITESHEET; //MISSILE_EXPLOSION FIRE_BLAST_001_64PX ok

		float positionX = posX;
		float positionY = posY;

		float distanceBetweenSprite = (float) (Math.sqrt(2.0f*5.0f*5.0f) * (float)sp.getFrameSize(0, 0)/64.0f); // 5.0f for 64px looks good

		int delai = 0;
		int delaiIncrement = 1;
		float numberOfBalls = 7;
		float animationSpeedStart = 4.0f;

		float increment, currentValue, scale, animationSpeed;
		for(int i=0; i<=numberOfBalls; i++){

			//Exponential value : Exp(-2) = env. 0 to Exp(0) = 1
			increment = -2.0f + (2.0f/numberOfBalls)*(i);
			currentValue = (float) Math.exp(increment);

			scale = currentValue;
			animationSpeed = (1.0f-currentValue)*animationSpeedStart;

			UniqueActionRenderer uar = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
			uar.init(sp);
			uar.setScalable(true);
			uar.setScale(scale);
			uar.setStartDelay(delai);
			uar.setAnimationSpeed(animationSpeed);
			uar.setPosition(positionX, positionY);
			this.getScene().attachChild(uar);

			delai = delai + delaiIncrement;

			positionX = positionX + distanceBetweenSprite*dirX;
			positionY = positionY + distanceBetweenSprite*dirY;

		}


		//    	
		//    	UniqueActionRenderer uniqueActionRenderer = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
		//		uniqueActionRenderer.init(PoolAnimManager.SWORD_001_64PX_SPRITESHEET);
		//		uniqueActionRenderer.setAnimationSpeed(0.5f);
		//		uniqueActionRenderer.setPosition(50, 50);
		//		this.getScene().attachChild(uniqueActionRenderer);

	}





}

