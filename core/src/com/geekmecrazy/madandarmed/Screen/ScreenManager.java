package com.geekmecrazy.madandarmed.Screen;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.LoadingScreen;
import com.geekmecrazy.madandarmed.Game.Scene.MenuScreen;
import com.geekmecrazy.madandarmed.Game.Scene.WarBaseScreen;

public class ScreenManager {

	public static enum ScreenType {
		LOADING,
		MENU,
		FIGHT,
		WARBASE
	}

	private static Screen currentScreen;

	/** Screens **/
	public static LoadingScreen loadingScreen = new LoadingScreen();
	public static MenuScreen menuScreen = new MenuScreen();
	public static FightScreen fightScreen = new FightScreen();
	public static WarBaseScreen warBaseScreen = new WarBaseScreen();

	// ===========================================================
	// Constructors
	// ===========================================================

	/**  Disable object's instantiation (private constructor) */
	private ScreenManager(){}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public static Screen getCurrentScreen() {
		return currentScreen;
	}

	private static void setCurrentScreen(final Screen pCurrentScreen) {
		currentScreen = pCurrentScreen;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	public static void launchScreen(ScreenType screenType){

		/** 1) unLoad previous screen **/
		if(ScreenManager.getCurrentScreen()!=null)
			ScreenManager.getCurrentScreen().unLoadScreen();
		
		/** 2) set current screen **/
		switch(screenType){
		case LOADING:
			ScreenManager.setCurrentScreen(ScreenManager.loadingScreen);
			break;
		case MENU:
			ScreenManager.setCurrentScreen(ScreenManager.menuScreen);
			break;
		case FIGHT:
			ScreenManager.setCurrentScreen(ScreenManager.fightScreen);
			ScreenManager.fightScreen.newGame();
			break;
		case WARBASE:
			ScreenManager.setCurrentScreen(ScreenManager.warBaseScreen);
			ScreenManager.warBaseScreen.newGame();
			break;
		default:
			break;
		}
		
		/** 3) load current screen **/
		GlobalManager.initCameras();
		ScreenManager.getCurrentScreen().loadScreen();
		currentScreen.show();

	}

	public static void init(){
		currentScreen = null;

		/** Loading Screen **/
		Scene loadingScene = new Scene();
		loadingScene.init(GlobalManager.LOADING_SCENE_WIDTH, GlobalManager.LOADING_SCENE_HEIGHT);
		ScreenManager.loadingScreen.init(loadingScene);

		/** Menu Screen **/
		Scene menuScene = new Scene();
		menuScene.init(GlobalManager.MENU_SCENE_WIDTH, GlobalManager.MENU_SCENE_HEIGHT);
		ScreenManager.menuScreen.init(menuScene);

		/** Fight Screen **/
		Scene fightScene = new Scene();
		fightScene.init(GlobalManager.FIGHT_SCENE_WIDTH, GlobalManager.FIGHT_SCENE_HEIGHT);
		ScreenManager.fightScreen.init(fightScene);

		/** WarBase Screen **/
		Scene warBaseScene = new Scene();
		warBaseScene.init(GlobalManager.WARBASE_SCENE_WIDTH, GlobalManager.WARBASE_SCENE_HEIGHT);
		ScreenManager.warBaseScreen.init(warBaseScene);
	}




}
