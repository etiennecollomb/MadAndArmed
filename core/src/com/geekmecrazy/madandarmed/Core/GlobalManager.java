package com.geekmecrazy.madandarmed.Core;

import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.GamePlay_BuildingManager;
import com.geekmecrazy.madandarmed.Game.Scene.Fight_CreepManager;
import com.geekmecrazy.madandarmed.Game.Scene.Fight_IaManager;
import com.geekmecrazy.madandarmed.Game.Scene.Fight_TurnManager;
import com.geekmecrazy.madandarmed.Game.Scene.Fight_WeaponManager;
import com.geekmecrazy.madandarmed.Game.Scene.LoadingScreen;
import com.geekmecrazy.madandarmed.Game.Scene.MenuScreen;
import com.geekmecrazy.madandarmed.Game.Scene.WarBaseScreen;
import com.geekmecrazy.madandarmed.Game.Tween.ButtonTween;
import com.geekmecrazy.madandarmed.Game.Tween.LayoutTween;
import com.geekmecrazy.madandarmed.Game.Tween.OrthographicCameraTween;
import com.geekmecrazy.madandarmed.Game.Tween.RectangleTween;
import com.geekmecrazy.madandarmed.Game.Tween.ShapeTween;
import com.geekmecrazy.madandarmed.Game.Tween.SpriteTween;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Game.UI.Layout;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.MyGestureListener;
import com.geekmecrazy.madandarmed.Loader.AssetsLoader;
import com.geekmecrazy.madandarmed.Screen.Screen;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.geekmecrazy.madandarmed.Tools.GraphicalTools;
import com.geekmecrazy.madandarmed.Utils.VirtualViewport;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;
import com.geekmecrazy.madandarmed.pool.PoolManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.IntBuffer;
import java.util.Random;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;


public class GlobalManager {

	/**
	 *
	 * Global Settings
	 *
	 */

	public static OrthographicCamera groundCamera; //Tiled Map
	public static OrthographicCamera camera;
	public static OrthographicCamera hudCamera;

	/** taille DEVICE SCREEN */
	public static final int DEVICE_SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final int DEVICE_SCREEN_HEIGHT = Gdx.graphics.getHeight();

	/** taille CAMERA */
	public static float maxZoom;
	public static float minZoom;
	public static final int CAMERA_WIDTH = DEVICE_SCREEN_WIDTH;
	public static final int CAMERA_HEIGHT = DEVICE_SCREEN_HEIGHT;

	/** SpriteBatch */
	private static float[] projectionMatrixHUD = {0, 0, CAMERA_WIDTH, CAMERA_HEIGHT};
	private static SpriteBatch currentSpriteBatch;
	private static SpriteBatch spriteBatchScene;
	private static SpriteBatch spriteBatchHUD;

	/** Ground */
	public static int GROUNDTILEDWIDTH = 128;
	public static int GROUNDTILEDHEIGHT = 64;
	public static float ISO_CIRCLE_RATIO = 1.5f; //permet d'approximer le cerle applatit en iso sur les abscisse , ie: y = y / ISO_TILED_RATIO

	/** Z-Index */
	public static int ZINDEXMAXVALUE = 1000000000;

	/** Depend of the devices capacity
	 * Best performance when biggest texture possible
	 */
	public static int MAX_TEXTURE_WIDTH;
	public static int MAX_TEXTURE_HEIGHT;

	private static VirtualViewport vvp;

	private static TweenManager tweenManager;

	public static Random random;

	/** **/
	public static AssetsLoader assestsLoader;
	public static boolean isAssestsLoaded;




	// ===========================================================
	// JSON
	// ===========================================================

	public static final String JSON_BUILDINGSPATTERN = "json/BuildingsPattern.json";
	public static final String JSON_WEAPONSPATTERN = "json/WeaponsPattern.json";
	public static final String JSON_TEXTURESPATTERN = "json/TexturesPattern.json";
	public static final String JSON_MAPSPATTERN = "json/MapsPattern.json";
	public static final String JSON_CREEPSPATTERN = "json/CreepsPattern.json";
	public static final String JSON_MENUSPATTERN = "json/MenusPattern.json";

	// ===========================================================
	// Screen and Scenes
	// ===========================================================

	/** Screens **/
	public static LoadingScreen loadingScreen = new LoadingScreen();
	public static MenuScreen menuScreen = new MenuScreen();
	public static FightScreen fightScreen = new FightScreen();
	public static WarBaseScreen warBaseScreen = new WarBaseScreen();

	/** Menu Scene */
	public static final int MENU_SCENE_WIDTH = DEVICE_SCREEN_WIDTH*5;
	public static final int MENU_SCENE_HEIGHT = DEVICE_SCREEN_HEIGHT*5;

	/** FIGHT Map */
	public static final int MAP_FIGHT_WIDTH = 4096; //2048;
	public static final int MAP_FIGHT_HEIGHT = 4096; //2048;

	/** HQ Scene */
	public static final int WARBASE_SCENE_WIDTH = 4096;
	public static final int WARBASE_SCENE_HEIGHT = 4096;

	public static final float BIG_NODESIZE = 64f;//32f LD, 64f HD;
	public static final float SMALL_NODESIZE = BIG_NODESIZE/3f;

	public static final int STARMAP_WIDTH = (int)(MAP_FIGHT_WIDTH / BIG_NODESIZE);
	public static final int STARMAP_HEIGHT = (int)(MAP_FIGHT_HEIGHT / BIG_NODESIZE);


	// ===========================================================
	// Managers
	// ===========================================================

	/** Pools **/
	public static PoolManager poolManager;
	public static PoolAnimManager poolAnimManager;

	/** Fight **/
	public static GamePlay_BuildingManager gamePlay_BuildingManager;
	public static Fight_CreepManager fight_CreepManager;
	public static Fight_TurnManager fight_TurnManager;
	public static Fight_IaManager fight_IaManager;
	public static Fight_WeaponManager fight_WeaponManager;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static void init(){

		GlobalManager.random = new Random();

		//Graphical Tools
		GraphicalTools.init();

		//MAX Texture Size
		IntBuffer buf = BufferUtils.newIntBuffer(16);
		Gdx.gl.glGetIntegerv(GL20.GL_MAX_TEXTURE_SIZE, buf);
		int maxSize = buf.get(0);
		GlobalManager.MAX_TEXTURE_WIDTH = maxSize;
		GlobalManager.MAX_TEXTURE_HEIGHT = maxSize;

		//Virtual viewport
		GlobalManager.vvp = new VirtualViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		GlobalManager.tweenManager = new TweenManager();
		GlobalManager.createCameras();
		GlobalManager.createInput();

		//SpriteBatches
		GlobalManager.spriteBatchScene = new SpriteBatch();
		GlobalManager.spriteBatchHUD = new SpriteBatch();
		GlobalManager.spriteBatchHUD.setProjectionMatrix(new Matrix4().setToOrtho2D(
				GlobalManager.projectionMatrixHUD[0],
				GlobalManager.projectionMatrixHUD[1],
				GlobalManager.projectionMatrixHUD[2],
				GlobalManager.projectionMatrixHUD[3]));

		//Tween
		Tween.registerAccessor(Button.class, new ButtonTween());
		Tween.registerAccessor(Rectangle.class, new RectangleTween());
		Tween.registerAccessor(Shape.class, new ShapeTween());
		Tween.registerAccessor(OrthographicCamera.class, new OrthographicCameraTween());
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		Tween.registerAccessor(Layout.class, new LayoutTween());

		/** Data Loader **/
		assestsLoader = new AssetsLoader();
		isAssestsLoaded = false;
	}

	/** Set Camera */
	private static void createCameras(){

		GlobalManager.camera = new OrthographicCamera(GlobalManager.CAMERA_WIDTH, GlobalManager.CAMERA_HEIGHT);
		GlobalManager.hudCamera = new OrthographicCamera(vvp.world_x, vvp.world_y);
		GlobalManager.groundCamera = new OrthographicCamera(GlobalManager.CAMERA_WIDTH, GlobalManager.CAMERA_HEIGHT);

	}

	public static void initCameras(){
		setMinMaxCameraZoom();

		GlobalManager.camera.zoom = minZoom;
		GlobalManager.camera.position.set(ScreenManager.getCurrentScreen().getScene().getWidth()/2f, ScreenManager.getCurrentScreen().getScene().getHeight()/2f, 0);
		GlobalManager.camera.update();

		GlobalManager.hudCamera.zoom = 1f;
		GlobalManager.hudCamera.position.set(vvp.world_x/2f, vvp.world_y/2f, 0);
		GlobalManager.hudCamera.update();

		GlobalManager.groundCamera.zoom = GlobalManager.camera.zoom;
		GlobalManager.groundCamera.position.set(GlobalManager.camera.position.x, GlobalManager.camera.position.y, GlobalManager.camera.position.z);
		GlobalManager.groundCamera.update();

	}

	private static void setMinMaxCameraZoom(){
		maxZoom = 0.4f;
		Screen currentScreen = ScreenManager.getCurrentScreen();
		float verticalMaxZoom = currentScreen.getScene().getWidth() / GlobalManager.camera.viewportWidth; //MAP_FIGHT_WIDTH / GlobalManager.camera.viewportWidth;
		float horizontalMaxZoom = currentScreen.getScene().getHeight() / GlobalManager.camera.viewportHeight; //MAP_FIGHT_HEIGHT / GlobalManager.camera.viewportHeight;
		minZoom = (verticalMaxZoom < horizontalMaxZoom) ? verticalMaxZoom : horizontalMaxZoom;
	}

	/** Set GestureDetector */
	private static void createInput(){
		InputMultiplexer im = new InputMultiplexer();

		MyGestureDetector gd = new MyGestureDetector(new MyGestureListener());
		im.addProcessor(gd);

		Gdx.input.setInputProcessor(im);
	}

	/** empeche la camera de sortir de la surface [pMaxWidth X pMaxHeight]
	 * et du zoom
	 **/
	private static void checkCameraBound(float pMaxWidth, float pMaxHeight){
		//Clamp range and set zoom
		OrthographicCamera cam = GlobalManager.camera;

		cam.zoom = MathUtils.clamp(cam.zoom, GlobalManager.maxZoom, GlobalManager.minZoom);

		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

		cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, pMaxWidth - effectiveViewportWidth / 2f);
		cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, pMaxHeight - effectiveViewportHeight / 2f);

	}

	/** update Camera */
	public static void updateCamera(){

		Screen currentScreen = ScreenManager.getCurrentScreen();

		GlobalManager.checkCameraBound(currentScreen.getScene().getWidth(), currentScreen.getScene().getHeight());

		GlobalManager.camera.update();
		GlobalManager.spriteBatchScene.setProjectionMatrix(GlobalManager.camera.combined);

		GlobalManager.groundCamera.zoom = GlobalManager.camera.zoom;
		GlobalManager.groundCamera.position.set(GlobalManager.camera.position.x, GlobalManager.camera.position.y, GlobalManager.camera.position.z);
		GlobalManager.groundCamera.update();

		GlobalManager.hudCamera.update();
		GlobalManager.spriteBatchHUD.setProjectionMatrix(GlobalManager.hudCamera.combined);
	}

	/** render a screen */
	public static void renderCurrentScreen(){
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1); //background
		//Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		GlobalManager.tweenManager.update(Gdx.graphics.getDeltaTime());

		/** Tiled Ground */
		if(ScreenManager.getCurrentScreen().getTiledGround() != null) {
			GlobalManager.currentSpriteBatch = GlobalManager.spriteBatchScene;
			GlobalManager.currentSpriteBatch.begin();
			ScreenManager.getCurrentScreen().getTiledGround().onDraw();
			GlobalManager.currentSpriteBatch.end();
		}

		/** Scene */
		GlobalManager.currentSpriteBatch = GlobalManager.spriteBatchScene;
		GlobalManager.currentSpriteBatch.begin();
		ScreenManager.getCurrentScreen().getScene().onDraw();
		GlobalManager.currentSpriteBatch.end();

		/** HUD */
		GlobalManager.currentSpriteBatch = GlobalManager.spriteBatchHUD;
		GlobalManager.currentSpriteBatch.begin();
		ScreenManager.getCurrentScreen().getHUD().onDraw();
		GlobalManager.currentSpriteBatch.end();
	}

	/** SpriteBatch draw
	    x - the x-coordinate in screen space
	    y - the y-coordinate in screen space
	    originX - the x-coordinate of the scaling and rotation origin relative to the screen space coordinates
	    originY - the y-coordinate of the scaling and rotation origin relative to the screen space coordinates
	    width - the width in pixels
	    height - the height in pixels
	    scaleX - the scale of the rectangle around originX/originY in x
	    scaleY - the scale of the rectangle around originX/originY in y
	    rotation - the angle of counter clockwise rotation of the rectangle around originX/originY
		srcX - the x-coordinate in texel space
		srcY - the y-coordinate in texel space
		srcWidth - the source with in texels
		srcHeight - the source height in texels
		flipX - whether to flip the sprite horizontally
		flipY - whether to flip the sprite vertically
	 */
	public static void spriteBatchDraw2(final Sprite sprite){

		GlobalManager.currentSpriteBatch.setColor(sprite.getColor());

		GlobalManager.currentSpriteBatch.draw(
				sprite.getTextureRegion().getTexture(),
				sprite.getDraw_x(),
				sprite.getDraw_y(),
				sprite.getDraw_originX(),
				sprite.getDraw_originY(),
				sprite.getDraw_width(),
				sprite.getDraw_height(),
				sprite.getDraw_scaleX(),
				sprite.getDraw_scaleY(),
				sprite.getDraw_rotation(),
				sprite.getDraw_srcX(),
				sprite.getDraw_srcY(),
				sprite.getDraw_srcWidth(),
				sprite.getDraw_srcHeight(),
				sprite.isDraw_flipX(),
				sprite.isDraw_flipY()
				);

		GlobalManager.currentSpriteBatch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	}

	/** SpriteBatch draw
	 * Font
	 * */
	public static void spriteBatchDrawFont(BitmapFont pBitmapFont, String pString, float pX, float pY, float pScaleX, float pScaleY, float pHeight, int pCorrectedOffsetX, int pCorrectedOffsetY){
		float scaleX = pScaleX;
		float scaleY = pScaleY;
		float correctedOffsetX = pCorrectedOffsetX;
		float correctedOffsetY = pCorrectedOffsetY;
		if(GlobalManager.currentSpriteBatch == GlobalManager.spriteBatchHUD) {
			scaleX = scaleX * VirtualViewport.convertUIWidthToUnit(1); //0.015f
			scaleY = scaleY * VirtualViewport.convertUIHeightToUnit(1); //0.015f
			correctedOffsetX = VirtualViewport.convertUIWidthToUnit(correctedOffsetX);
			correctedOffsetY = VirtualViewport.convertUIHeightToUnit(correctedOffsetY);
		}
		pBitmapFont.setScale(scaleX, scaleY);

		pBitmapFont.setUseIntegerPositions(false);

		pBitmapFont.draw(GlobalManager.currentSpriteBatch, pString, pX + correctedOffsetX, pY + pHeight - correctedOffsetY);

	}

	/**
	 * Draw TiledMap
	 * */
	public static void spriteBatchDrawTiledMap(TiledMapRenderer pTiledMapRenderer){
		pTiledMapRenderer.setView(groundCamera);
		pTiledMapRenderer.render();
	}

	public static VirtualViewport getVvp() {
		return vvp;
	}

	public static void setVvp(VirtualViewport vvp) {
		GlobalManager.vvp = vvp;
	}

	public static TweenManager getTweenManager() {
		return tweenManager;
	}

	public static void setTweenManager(TweenManager tweenManager) {
		GlobalManager.tweenManager = tweenManager;
	}

	public static String convertToDevicePath(String dirName){

		/** Set Units Dir **/
		FileHandle dirHandle;
		if (Gdx.app.getType() == ApplicationType.Android)
			return dirName; /** Android Application **/
		else
			return "./bin/"+dirName; /** ApplicationType.Desktop **/


	}

	public static void createManagersForFight(Team teamPlayer, Team teamIA){

		GlobalManager.gamePlay_BuildingManager = new GamePlay_BuildingManager(teamPlayer, teamIA);
		GlobalManager.fight_CreepManager = new Fight_CreepManager(teamPlayer, teamIA);
		GlobalManager.fight_TurnManager = new Fight_TurnManager();
		GlobalManager.fight_IaManager = new Fight_IaManager();
		GlobalManager.fight_WeaponManager = new Fight_WeaponManager();

	}
	
	public static void destroyManagersForFight(){

		GlobalManager.gamePlay_BuildingManager = null;
		GlobalManager.fight_CreepManager = null;
		GlobalManager.fight_TurnManager = null;
		GlobalManager.fight_IaManager = null;
		GlobalManager.fight_WeaponManager = null;

	}

	public static void createPoolManagers(){
	
		GlobalManager.poolManager = new PoolManager();
		GlobalManager.poolAnimManager = new PoolAnimManager();
	}
	
}




