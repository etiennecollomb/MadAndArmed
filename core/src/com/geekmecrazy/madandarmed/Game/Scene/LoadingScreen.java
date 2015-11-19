package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Renderer.ProgressBarRenderer;
import com.geekmecrazy.madandarmed.Screen.Screen;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;


public class LoadingScreen extends Screen implements IUpdatable {

	/** loading status TODO: a generiser **/
	ProgressBarRenderer loadingStatus; 
	

	// ===========================================================
	// Singleton manager
	// ===========================================================

	private static LoadingScreen loadingScreen;


	/** Access au manager */
	public static LoadingScreen getManager(){
		if (loadingScreen == null)
			loadingScreen = new LoadingScreen();
		return loadingScreen;
	}

	/** Creation et initialisation du manager */
	public void init(final Scene pScene) {
		super.init(pScene);

		///////TEST//
		float score_bar_min = 49;
        float score_bar_max = 207;
        float score_bar_min_size = 2;
        
		this.loadingStatus = new ProgressBarRenderer();
		float posX_0 = 0f;
        float posY_0 = 0f; //VirtualViewport.convertWorldHeightToUnit(-60f);
        this.loadingStatus.init(TextureType.PROGRESS_BAR_BLUE, posX_0, posY_0, score_bar_min, score_bar_max, score_bar_min_size);
        ///FIN TEST//
        
        
        this.getHUD().attachChild(this.loadingStatus, Entity.Alignment.CENTER);
	}

	@Override
	public void onUpdate(){
		super.onUpdate();

		/** Dataloader **/
		if(!GlobalManager.isAssestsLoaded){
			//Continue to load
			GlobalManager.assestsLoader.getAssetManager().update();
			if(GlobalManager.assestsLoader.getAssetManager().getProgress() == 1f){
				GlobalManager.assestsLoader.getAssetManager().finishLoading(); /** to be sure... **/
				GlobalManager.isAssestsLoaded = true;
			}
			
			/** set loading bar renderer **/
			//TEST a refaire propre en [0; 1.0]
			float size_ = GlobalManager.assestsLoader.getAssetManager().getProgress() * (float)(this.loadingStatus.getProgressBarMaX() - this.loadingStatus.getProgressBarMinX());
	        this.loadingStatus.setBarSize((int)size_);
			
			System.out.println("Loading ... " + GlobalManager.assestsLoader.getAssetManager().getProgress());
			
			
		}else{
			//Launch next screen
			this.showMenuScreen();
		}

	}



	@Override
	public void show() {
		System.out.println("Show Loading Screen...");
	}

	public void showMenuScreen(){

		Scene menuScene = new Scene();
		menuScene.init(GlobalManager.MENU_SCENE_WIDTH, GlobalManager.MENU_SCENE_HEIGHT);
		MenuScreen.getManager().init(menuScene);
		ScreenManager.setCurrentScreen(MenuScreen.getManager());

	}



}

