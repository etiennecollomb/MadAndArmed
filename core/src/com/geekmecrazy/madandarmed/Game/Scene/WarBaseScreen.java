package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team.TeamID;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Element.WarBase_Team;
import com.geekmecrazy.madandarmed.Game.Element.Property.GameMap;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Game.UI.WarBase_BuildingButtonUI;
import com.geekmecrazy.madandarmed.Renderer.IsoGridRenderer;
import com.geekmecrazy.madandarmed.Renderer.MyTiledMapRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.geekmecrazy.madandarmed.Screen.ScreenManager.ScreenType;


public class WarBaseScreen extends GamePlayScreen implements IUpdatable {

	/** UI */
    private WarBase_BuildingButtonUI mBuildingButtonUI;
    
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

        /**** Create Creep ***/
    	GlobalManager.gamePlay_BuildingManager.excuteAskForCreateSpawnBuilding();

        /******** PREPARATION DU TOUR ********/
    	//WarBaseScreen.currentTime = System.currentTimeMillis();
        //this.updateNextMoneyTurn();

        /******** UPDATE EN COURS ********/
    	this.getTeamPlayer().notifyListeners();

        /******** TRAITEMENT DU TOUR ********/
        this.runUpdateNextState();

        /******** RECYCLE ********/
        GlobalManager.gamePlay_BuildingManager.recycleBuilding();

        /******** FINALISATION DU TOUR ********/
        this.getTeamPlayer().getStateMap().swap();

        //principalement update des positions des renderer (a faire apres calcul metier...donc a la fin)
        super.onUpdate();
        
        /** Memory Usage **/
//        System.out.println("################## MEMORY USAGE ##################");
//        System.out.println("Java Heap : " + Gdx.app.getJavaHeap()/1000000f + " Mo" );
//        System.out.println("Native Heap : " + Gdx.app.getNativeHeap()/1000000f + " Mo" );
//        System.out.println("##################################################");

    }

    @Override
    public WarBase_Team getTeamPlayer() {
        return (WarBase_Team)super.getTeamPlayer();
    }
    
    /** Load Screen First Time **/
    @Override
    public void loadScreenFirstTime(){
    		super.loadScreenFirstTime();
    		
            GameMap.initMap( this.getScene() );

			//init GROUND
			MyTiledMapRenderer tiledGround = new MyTiledMapRenderer();
			tiledGround.init(GlobalManager.WARBASE_SCENE_WIDTH, GlobalManager.WARBASE_SCENE_HEIGHT, GlobalManager.GROUNDTILEDWIDTH, GlobalManager.GROUNDTILEDHEIGHT);
			ScreenManager.warBaseScreen.setTiledGround(tiledGround);
    }

    /** Load Screen **/
    @Override
    public void loadScreen(){
    	if(this.isLoadFirstTime()) this.loadScreenFirstTime();
    	super.loadScreen();
    	
    	/** Init managers **/
		GlobalManager.gamePlay_BuildingManager.init((WarBase_Team)this.getTeamPlayer(), null);
    	
        /** Building Creation Button */
        mBuildingButtonUI = new WarBase_BuildingButtonUI();
        mBuildingButtonUI.init(0, 0);
        this.getHUD().attachChild(mBuildingButtonUI, Entity.Alignment.LEFT_BOTTOM);
        
		/** Back Button **/
		Button backButton = new Button();
		backButton.init(0, 0, TextureType.BACK_ICON);
		backButton.setAction(new IAction(){
			@Override
			public void execute(){
				ScreenManager.launchScreen(ScreenType.MENU);
			}
		});
		backButton.setSize(1.0f, 1.0f);
		this.getHUD().attachChild(backButton, Entity.Alignment.RIGHT_BOTTOM);
		this.getHUD().registerTouchableShape(backButton);
    	
    }

    /** UnLoad Screen **/
    @Override
    public void unLoadScreen(){
    	super.unLoadScreen();
    	
		/** called at last **/
    	if(this.isUnloadLastTime()) this.unLoadScreenLastTime();
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
        
        /** Init IsoGrid */
		this.setIsoGrid(new IsoGrid());
		this.getIsoGrid().init(GlobalManager.GROUNDTILEDWIDTH, GlobalManager.GROUNDTILEDHEIGHT, this.getScene());
		
		/** draw grid? **/
        IsoGridRenderer gridRenderer = new IsoGridRenderer();
        gridRenderer.init(this.getIsoGrid());
        //this.getScene().attachChild(gridRenderer);
        
        /** Create teams */
		this.setTeamPlayer( new WarBase_Team(TeamID.TEAM1));
        
    }

    @Override
    public void show() {
        System.out.println("Show WarBase Screen");
    }

    public void closeGame(){
        //TODO: a revoir pour faire bien clean
    	GlobalManager.destroyManagers();
    }

	public void runUpdateNextState(){
		GlobalManager.gamePlay_BuildingManager.runUpdateNextState();
	}
	


}