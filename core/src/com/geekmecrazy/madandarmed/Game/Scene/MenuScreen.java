package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Entity.Sprite.AnimatedSprite;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Tween.RectangleTween;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Game.UI.Layout;
import com.geekmecrazy.madandarmed.Game.UI.Layout.Orientation;
import com.geekmecrazy.madandarmed.Renderer.MyTiledMapRenderer;
import com.geekmecrazy.madandarmed.Renderer.UniqueActionRenderer;
import com.geekmecrazy.madandarmed.Screen.Screen;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.geekmecrazy.madandarmed.Screen.ScreenManager.ScreenType;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quad;



public class MenuScreen extends Screen implements IUpdatable {

	//TEST
	AnimatedSprite as1, as2, as3, as4, as5;
	float start_angle = (float) (Math.PI/2f);
	//FIN TEST//


	private Rectangle blackScreen;
	private Button warBaseScreenButton;
	private Button fightScreenButton;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(){

		//TEST
		//		int x = as1.getCurrentFrameX();
		//		int y = as1.getCurrentFrameY();
		//		int maxX = as1.getSpriteSheet().getNumberOfColumn();
		//		int maxY = as1.getSpriteSheet().getNumberOfRow();
		//		y++;
		//		if(y>=maxY){y=33; x++; if(x>=maxX){x=0;} }
		//		as1.setCurrentFrame(x, y);
		//		
		//		x = as2.getCurrentFrameX();
		//		y = as2.getCurrentFrameY();
		//		maxX = as2.getSpriteSheet().getNumberOfColumn();
		//		maxY = as2.getSpriteSheet().getNumberOfRow();
		//		y++;
		//		if(y>=32){y=0; x++; if(x>=maxX){x=0;} }
		//		as2.setCurrentFrame(x, y);
		//		
		//		x = 16;//as3.getCurrentFrameX();
		//		y = as3.getCurrentFrameY();
		//		maxX = as3.getSpriteSheet().getNumberOfColumn();
		//		maxY = as3.getSpriteSheet().getNumberOfRow();
		//		y++;
		//		if(y>=maxY){y=0; /*x++; if(x>=maxX){x=0;}*/ }
		//		as3.setCurrentFrame(x, y);
		//		
		//		x = as4.getCurrentFrameX();
		//		y = as4.getCurrentFrameY();
		//		maxX = as4.getSpriteSheet().getNumberOfColumn();
		//		maxY = as4.getSpriteSheet().getNumberOfRow();
		//		y++;
		//		if(y>=maxY){y=0; /*x++; if(x>=maxX){x=0;}*/ }
		//		as4.setCurrentFrame(x, maxY-1);


		//		x = as5.getCurrentFrameX();
		//		y = as5.getCurrentFrameY();
		//		maxX = as5.getSpriteSheet().getNumberOfColumn();
		//		maxY = as5.getSpriteSheet().getNumberOfRow();
		//		y++;
		//		if(y>=maxY){y=0; /*x++; if(x>=maxX){x=0;}*/ }
		//		as5.setCurrentFrame(x, maxY-1);
		//FINTEST


		super.onUpdate();
	}



	@Override
	public void show() {
		System.out.println("Show Menu Screen");
	}

	/** Load Screen First Time **/
	@Override
	public void loadScreenFirstTime(){
		super.loadScreenFirstTime();
	}

	/** Load Screen **/
	@Override
	public void loadScreen(){
		if(this.isLoadFirstTime()) this.loadScreenFirstTime();
    	super.loadScreen();

		blackScreen.setColor(0,0,0,0f);
	}

	/** UnLoad Screen **/
	@Override
	public void unLoadScreen(){
		super.unLoadScreen();
	}

	/** UnLoad Screen Last Time **/
	@Override
	public void unLoadScreenLastTime(){
		super.unLoadScreenLastTime();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/** Creation et initialisation du manager */
	public void init(final Scene pScene) {
		super.init(pScene);

		blackScreen = new Rectangle();
		blackScreen.init(0,0,this.getHUD().getWidth(),this.getHUD().getHeight());
		blackScreen.setColor(0,0,0,0f);

		fightScreenButton = new Button();
		fightScreenButton.init(0, 0, TextureType.BUTTON_UNIT_BACKGROUND);
		fightScreenButton.setSize(3f, 1.2f);
		fightScreenButton.setAction(new IAction(){
			// Pour le moment ça va lancer le fight direct ce bouton au lieu du world
			@Override
			public void execute(){
				showFightScreen();
			}
		});
		this.getHUD().registerTouchableShape(fightScreenButton);

		warBaseScreenButton = new Button();
		warBaseScreenButton.init(0, 0, TextureType.BUTTON_UNIT_BACKGROUND);
		warBaseScreenButton.setSize(3f, 1.2f);
		warBaseScreenButton.setAction(new IAction(){
			@Override
			public void execute(){
				showWarBaseScreen(); //A REMETTRE POUR LE QG

				/** TEST unit weapon on unit **/
				/*
				//TEST
				int x_ = as5.getCurrentFrameX()+1;
				if(x_>= as5.getSpriteSheet().getNumberOfColumn()) x_=0;
				as5.setCurrentFrame(x_, as5.getCurrentFrameY());

				start_angle = start_angle + (float)(2*Math.PI/16f);
				start_angle = (start_angle >= 2*Math.PI)? 0:start_angle;

				float dirX = (float)Math.cos(start_angle);
				float dirY = (float)Math.sin(start_angle);

				// start_angle is (2*Math.PI/16f)*graphicOrientation
				//				float deltaAngleWeapon = (float) (-Math.PI/24);
				//				float posX_ = (float)Math.cos(start_angle + deltaAngleWeapon) * 80 ;
				//				float posY_ = (float)Math.sin(start_angle + deltaAngleWeapon) * 80;
				//				posY_ = posY_/1.5f + 2f;
				//				fireThrower(as4.getX()+posX_, as4.getY()+posY_, dirX, dirY);

				float deltaAngleWeapon = (float) (+Math.PI/5);
				float posX_ = (float)Math.cos(start_angle + deltaAngleWeapon) * 80 ;
				float posY_ = (float)Math.sin(start_angle + deltaAngleWeapon) * 80;
				posY_ = posY_/1.5f - 20f;
				fireThrowerMesh(as5.getX()+posX_, as5.getY()+posY_, dirX, dirY, start_angle, 0);

				deltaAngleWeapon = (float) (-Math.PI/5);
				posX_ = (float)Math.cos(start_angle + deltaAngleWeapon) * 80 ;
				posY_ = (float)Math.sin(start_angle + deltaAngleWeapon) * 80;
				posY_ = posY_/1.5f - 20f;
				fireThrowerMesh(as5.getX()+posX_, as5.getY()+posY_, dirX, dirY, start_angle, 1);

				//fireThrowerMesh(as5.getX()+posX_-50, as5.getY()+posY_, dirX, dirY);
				//				System.out.println("3333 "+start_angle + " " + dirX + " " + dirY );
				//FINTEST
				 */

			}
		});
		this.getHUD().registerTouchableShape(warBaseScreenButton);


		//TEST/////////////////////////////////////////
		Rectangle title = new Rectangle();
		title.init(0,0,7,1.6f);
		title.setColor(1,0,0,1);

		Layout l1 = new Layout();
		l1.init(0,0);
		l1.add(fightScreenButton);
		l1.add(warBaseScreenButton);

		Layout titleLayout = new Layout();
		titleLayout.init(0,0);
		titleLayout.setLayoutSize(Layout.Dimension.MATCH_PARENT, Layout.Dimension.WRAP_CONTENT);
		titleLayout.add(title,Entity.Alignment.CENTER);

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
		//        this.getHUD().attachChild(layout_test, Entity.Alignment.CENTER_LEFT);
		this.getHUD().registerTouchableShape(layout_test);


		this.getHUD().attachChild(titleLayout, Entity.Alignment.CENTER_TOP);
		this.getHUD().attachChild(l1,Entity.Alignment.CENTER);
		this.getHUD().attachChild(layout_test, Entity.Alignment.CENTER_BOTTOM);
		this.getHUD().attachChild(blackScreen, Entity.Alignment.CENTER);

		//TEST
		//		SpriteSheet spshit = new SpriteSheet(AnimatedTextureType.FLAMETHROWER1_HD_TEAM2);
		//		as1 = new AnimatedSprite();
		//		as1.init(spshit, 256, 256);
		//		as1.setPosition(300f,  300f);
		//		as1.setCurrentFrame(0);
		//		this.getScene().attachChild(as1);
		//		
		//		Rectangle r2d2 = new Rectangle();
		//		r2d2.init(300f,300f,9, 9);
		//		r2d2.setColor(1,0,0,1);
		//		this.getScene().attachChild(r2d2);
		//		
		//		SpriteSheet spshit2 = new SpriteSheet(AnimatedTextureType.BULLHOUND_HD_TEAM2, SpriteSheetType.FROM_ATLAS);
		//		as2 = new AnimatedSprite();
		//		as2.init(spshit2, 256, 256);
		//		as2.setPosition(450f,  300f);
		//		as2.setCurrentFrame(0);
		//		this.getScene().attachChild(as2);
		//		
		//		Rectangle r2d3 = new Rectangle();
		//		r2d3.init(300f,300f,9, 9);
		//		r2d3.setColor(1,0,0,1);
		//		this.getScene().attachChild(r2d3);
		//		
		//		SpriteSheet spshit3 = new SpriteSheet(AnimatedTextureType.BULLHOUND_HD_TEAM2, SpriteSheetType.FROM_ATLAS);
		//		as3 = new AnimatedSprite();
		//		as3.init(spshit3, 256, 256);
		//		as3.setPosition(300f,  500f);
		//		as3.setCurrentFrame(0);
		//		this.getScene().attachChild(as3);
		//		
		//		Rectangle r2d4 = new Rectangle();
		//		r2d4.init(300f,300f,9, 9);
		//		r2d4.setColor(1,0,0,1);
		//		this.getScene().attachChild(r2d4);
		//		
		//		SpriteSheet spshit4 = new SpriteSheet(AnimatedTextureType.FLAMETHROWER1_HD_TEAM2, SpriteSheetType.FROM_ATLAS);
		//		as4 = new AnimatedSprite();
		//		as4.init(spshit4, 256, 256);
		//		as4.setPosition(450f,  500f);
		//		as4.setCurrentFrame(8,0); //y sur la position de tir !
		//		this.getScene().attachChild(as4);

		//		SpriteSheet spshit5 = new SpriteSheet(AnimatedTextureType.MESH_HD_TEAM2);
		//		as5 = new AnimatedSprite();
		//		as5.init(spshit5, 256, 256);
		//		as5.setPosition(450f,  500f);
		//		as5.setCurrentFrame(8,0); //y sur la position de tir !
		//		this.getScene().attachChild(as5);

		//		Rectangle r2d5 = new Rectangle();
		//		r2d5.init(300f,300f,9, 9);
		//		r2d5.setColor(1,0,0,1);
		//		this.getScene().attachChild(r2d5);

		//FIN TEST

	}


	public void showFightScreen(){
		Tween.to(blackScreen, RectangleTween.ALPHA, 0.45f)
		.target(1f)
		.setCallbackTriggers(TweenCallback.END)
		.setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {

				/** Fight Screen **/
				ScreenManager.launchScreen(ScreenType.FIGHT);

			}
		})
		.ease(Quad.OUT)
		.start(GlobalManager.getTweenManager());
	}


	public void showWarBaseScreen(){
		Tween.to(blackScreen, RectangleTween.ALPHA, 0.45f)
		.target(1f)
		.setCallbackTriggers(TweenCallback.END)
		.setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {

				/** WarBase Screen **/
				ScreenManager.launchScreen(ScreenType.WARBASE);
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

		SpriteSheet sp = GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_001_128PX); //MISSILE_EXPLOSION FIRE_BLAST_001_64PX ok

		float positionX = posX;
		float positionY = posY;

		float distanceBetweenSprite = (float) (Math.sqrt(2.0f*5.0f*5.0f) * (float)sp.getFrameWidth(0, 0)/64.0f); // 5.0f for 64px looks good

		int delai = 0;
		int delaiIncrement = 1;
		float numberOfBalls = 12;
		float animationSpeedStart = 4.0f;

		float increment, currentValue, scale, animationSpeed;
		for(int i=0; i<=numberOfBalls; i++){

			//Exponential value : Exp(-2) = env. 0 to Exp(0) = 1
			increment = -2.0f + (2.0f/numberOfBalls)*(i);
			currentValue = (float) Math.exp(increment);

			scale = currentValue;
			animationSpeed = (1.0f-currentValue)*animationSpeedStart;

			if(animationSpeed>0f){

				UniqueActionRenderer uar = GlobalManager.poolAnimManager.getUniqueActionRendererPool().obtain();
				uar.init(sp);
				uar.setScalable(true);
				uar.setScale(scale);
				uar.setStartDelay(delai);
				uar.setAnimationSpeed(animationSpeed);
				uar.setPosition(positionX, positionY);
				this.getScene().attachChild(uar);
			}

			delai = delai + delaiIncrement;

			positionX = positionX + distanceBetweenSprite*dirX;
			positionY = (float) (positionY + distanceBetweenSprite*dirY/GlobalManager.ISO_CIRCLE_RATIO);

		}

	}


	// TEST FIRETHROWER
	/** from Pos through the direction vector
		     dirX, dirY must be unitary vector
	 */
	public void fireThrowerMesh(final float posX, final float posY, final float dirX, final float dirY, final float angle, final float weapon){

		Random random = new Random();

		SpriteSheet sp = GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.HALO_BLUE_192PX);
		SpriteSheet sp2 = GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_004_128PX);

		List<SpriteSheet> explosionsList = new ArrayList<SpriteSheet>();
		explosionsList.add(GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_001_128PX));
		explosionsList.add(GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_002_128PX));
		explosionsList.add(GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_003_128PX));
		explosionsList.add(GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_004_128PX));
		explosionsList.add(GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_005_128PX));


		float positionX = posX;
		float positionY = posY;

		/** Jet Explosion **/
		float distanceBetweenSprite = (float) (Math.sqrt(2.0f*5.0f*5.0f) * (float)sp.getFrameWidth(0, 0)/64.0f); // 5.0f for 64px looks good

		int delai = 0;
		int delaiIncrement = 1;
		float numberOfBalls = 12;
		float animationSpeedStart = 4.0f;

		float increment, currentValue, scale, animationSpeed;
		for(int i=0; i<=numberOfBalls; i++){

			/** We print only if not hide by the mesh - specific case for mesh **/
			if(!(
					((weapon==0)&&(angle>=1* Math.PI*2/16-Math.PI/16)&&(angle<=1* Math.PI*2/16+Math.PI/16)&&(i<1))
					||((weapon==0)&&(angle>=2* Math.PI*2/16-Math.PI/16)&&(angle<=2* Math.PI*2/16+Math.PI/16)&&(i<2))
					||((weapon==0)&&(angle>=3* Math.PI*2/16-Math.PI/16)&&(angle<=3* Math.PI*2/16+Math.PI/16)&&(i<3))
					||((weapon==1)&&(angle>=5* Math.PI*2/16-Math.PI/16)&&(angle<=5* Math.PI*2/16+Math.PI/16)&&(i<3))
					||((weapon==1)&&(angle>=6* Math.PI*2/16-Math.PI/16)&&(angle<=6* Math.PI*2/16+Math.PI/16)&&(i<2))
					||((weapon==1)&&(angle>=7* Math.PI*2/16-Math.PI/16)&&(angle<=7* Math.PI*2/16+Math.PI/16)&&(i<1))
					)) {

				//Exponential value : Exp(-2) = env. 0 to Exp(0) = 1
				increment = -2.0f + (2.0f/numberOfBalls)*(i);
				currentValue = (float) Math.exp(increment);

				scale = currentValue;
				animationSpeed = (1.0f-currentValue)*animationSpeedStart;

				if(animationSpeed>0f){

					/** Under explosion effect **/
					UniqueActionRenderer uar = GlobalManager.poolAnimManager.getUniqueActionRendererPool().obtain();
					uar.init(sp);
					uar.setScalable(true);
					uar.setScale(scale);
					uar.setStartDelay(delai);
					uar.setAnimationSpeed(animationSpeed/3f);
					uar.setPosition(positionX, positionY);
					this.getScene().attachChild(uar);

					//					/** Explosion effect **/
					//					UniqueActionRenderer uar2 = GlobalManager.poolAnimManager.getUniqueActionRendererPool().obtain();
					//					uar2.init(sp2);
					//					uar2.setScalable(true);
					//					uar2.setScale(scale);
					//					uar2.setStartDelay(delai);
					//					uar2.setAnimationSpeed(animationSpeed);
					//					uar2.setPosition(positionX, positionY);
					//					this.getScene().attachChild(uar2);
				}
			}

			delai = delai + delaiIncrement;

			positionX = positionX + distanceBetweenSprite*dirX;
			positionY = (float) (positionY + distanceBetweenSprite*dirY/GlobalManager.ISO_CIRCLE_RATIO);

		}

		/** MASS explosion **/
		float explosionsWidth = (5f);
		float numberOfEndingExplosions = 5;
		float explosionsWidthIncrement = 150f/4f/numberOfEndingExplosions;

		for(int j=0; j<=3; j++){
			for(int i=0; i<=numberOfEndingExplosions; i++){

				float x_ = (float) (positionX + Math.random()*2f*explosionsWidth-explosionsWidth);
				float y_ = (float) (positionY + Math.random()*2f*explosionsWidth-explosionsWidth);

				SpriteSheet sp_explosion = explosionsList.get(random.nextInt(explosionsList.size()));

				/** Explosion effect **/
				UniqueActionRenderer uar2 = GlobalManager.poolAnimManager.getUniqueActionRendererPool().obtain();
				uar2.init(sp_explosion);
				uar2.setScalable(true);
				//uar2.setScale(scale);
				uar2.setStartDelay(delai);
				uar2.setAnimationSpeed(0.8f);
				uar2.setPosition(x_, y_);
				this.getScene().attachChild(uar2);

				delai = delai + 2;
				explosionsWidth = explosionsWidth + explosionsWidthIncrement;
			}
		}


	}




}

