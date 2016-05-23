package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Element.Fight_Team;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Element.Property.GameMap;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Game.UI.ScoreBarUI;
import com.geekmecrazy.madandarmed.Game.UI.Fight_SpawnBuildingButtonUI;
import com.geekmecrazy.madandarmed.IA.AstarMap;
import com.geekmecrazy.madandarmed.IA.GlobalAstar;
import com.geekmecrazy.madandarmed.Renderer.IsoGridRenderer;
import com.geekmecrazy.madandarmed.Renderer.MyTiledMapRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.geekmecrazy.madandarmed.Screen.ScreenManager.ScreenType;


public class FightScreen extends GamePlayScreen implements IUpdatable {

	public static final long TURN_MONEY_LENGTH =1000;
	public static final int START_MONEY=500;
	public static final int MAX_MONEY=2000;
	public static final int TURN_MONEY=500; //30
	public static final int MAX_THORIUM=1000;

	/** UI */
	private Fight_SpawnBuildingButtonUI mUnitButtonUI;
	private ScoreBarUI mScoreBarUI;

	/** Time stuff */
	private long currentTime;
	private long previousMoneyTurnTime;



	// ===========================================================
	// Constructors
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public long getCurrentTime() {
		return currentTime;
	}

	public long getPreviousMoneyTurnTime() {
		return previousMoneyTurnTime;
	}

	public void setCurrentTime(final long pCurrentTime) {
		this.currentTime = pCurrentTime;
	}

	public void setPreviousMoneyTurnTime(final long pPreviousMoneyTurnTime) {
		this.previousMoneyTurnTime = pPreviousMoneyTurnTime;
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(){

		/**** Create Creep ***/
		GlobalManager.fight_CreepManager.excuteAskForCreateCreep();
		GlobalManager.gamePlay_BuildingManager.excuteAskForCreateSpawnBuilding();

		/******** PREPARATION DU TOUR ********/
		this.currentTime = System.currentTimeMillis();
		this.updateNextMoneyTurn();

		/******** UPDATE EN COURS ********/
		this.getTeamPlayer().notifyListeners();
		this.getTeamIA().notifyListeners();

		/******** TRAITEMENT DU TOUR ********/
		this.runUpdateNextState();

		/******** RECYCLE ********/
		GlobalManager.fight_CreepManager.recycleCreep();
		GlobalManager.gamePlay_BuildingManager.recycleBuilding();

		/******** FINALISATION DU TOUR ********/
		this.getTeamPlayer().getStateMap().swap();
		this.getTeamIA().getStateMap().swap();

		//principalement update des positions des renderer (a faire apres calcul metier...donc a la fin)
		super.onUpdate();

		/** Memory Usage **/
		//        System.out.println("################## MEMORY USAGE ##################");
		//        System.out.println("Java Heap : " + Gdx.app.getJavaHeap()/1000000f + " Mo" );
		//        System.out.println("Native Heap : " + Gdx.app.getNativeHeap()/1000000f + " Mo" );
		//        System.out.println("##################################################");

	}

	@Override
	public void show() {
		System.out.println("Show Fight Screen");
	}

	@Override
	public Fight_Team getTeamPlayer() {
		return (Fight_Team)super.getTeamPlayer();
	}

	@Override
	public Fight_Team getTeamIA() {
		return (Fight_Team)super.getTeamIA();
	}


	// ===========================================================
	// Methods
	// ===========================================================

	/** Creation et initialisation du manager */
	public void init(final Scene pScene) {
		super.init(pScene);

		this.previousMoneyTurnTime=System.currentTimeMillis();
		GlobalAstar.init();
		AstarMap.init(GlobalManager.FIGHT_STARMAP_WIDTH, GlobalManager.FIGHT_STARMAP_HEIGHT);

		/** Init IsoGrid */
		this.setIsoGrid(new IsoGrid());
		this.getIsoGrid().init(GlobalManager.GROUNDTILEDWIDTH, GlobalManager.GROUNDTILEDHEIGHT, this.getScene());

		/** draw grid? **/
		IsoGridRenderer gridRenderer = new IsoGridRenderer();
		gridRenderer.init(this.getIsoGrid());
		//this.getScene().attachChild(gridRenderer);

		/** create teams */
		this.setTeamPlayer( new Fight_Team(START_MONEY, TURN_MONEY, MAX_MONEY, TeamID.TEAM1, MAX_THORIUM));
		this.setTeamIA( new Fight_Team(START_MONEY, TURN_MONEY, MAX_MONEY, TeamID.TEAM2, MAX_THORIUM));

		
		
	}


	public void closeGame(){
		//TODO: a revoir pour faire bien clean
		GlobalManager.destroyManagers();
	}

	public Fight_Team getOtherTeam(final GamePlay_Team team){
		if(team == this.getTeamPlayer())
			return this.getTeamIA();
		else
			return this.getTeamPlayer();
	}

	public void runUpdateNextState(){
		GlobalManager.fight_TurnManager.runUpdateNextState();	
		//GlobalManager.fight_IaManager.getManager().runUpdateNextState();
		GlobalManager.gamePlay_BuildingManager.runUpdateNextState();
		GlobalManager.fight_CreepManager.runUpdateNextState();
		GlobalManager.fight_WeaponManager.runUpdateNextState();
	}

	public void updateNextMoneyTurn(){
		if(this.getCurrentTime() > this.getPreviousMoneyTurnTime() + TURN_MONEY_LENGTH){
			this.getTeamPlayer().addMoneyNewTurn();
			this.getTeamIA().addMoneyNewTurn();
			this.setPreviousMoneyTurnTime(this.getCurrentTime());
		}
	}



	/** Load First Time **/
	//boolean ?
	/** Load Normal **/
	/** Unload **/
	/** Unload last Time **/

	/*
	@Override
	public void reset() {
		this.getRegisteredUpdatable().clear();
		this.mTiledGround = null;
		this.mScene = null;
		this.getHUD().reset();
	}

    @Override
    public void reset(){

        this.setTeamPlayer(null);
        this.setTeamIA(null);

        this.setCurrentTime(0);
        this.setPreviousMoneyTurnTime(0);
    }
	 */



	/** Load Screen First Time **/
	@Override
	public void loadScreenFirstTime(){
			super.loadScreenFirstTime();
					
			/** Ground **/
			MyTiledMapRenderer tiledGround = new MyTiledMapRenderer();
			tiledGround.init(GlobalManager.FIGHT_SCENE_WIDTH, GlobalManager.FIGHT_SCENE_HEIGHT, GlobalManager.GROUNDTILEDWIDTH, GlobalManager.GROUNDTILEDHEIGHT);
			ScreenManager.fightScreen.setTiledGround(tiledGround);
			
			/** Decoration **/
			GameMap.initMap( this.getScene() );
	}

	/** Load Screen **/
	@Override
	public void loadScreen(){

		if(this.isLoadFirstTime()) this.loadScreenFirstTime();
    	super.loadScreen();


		/** Init manager **/
		GlobalManager.gamePlay_BuildingManager.init((Fight_Team)this.getTeamPlayer(), (Fight_Team)this.getTeamIA());
		GlobalManager.gamePlay_BuildingManager.initTarget();
		GlobalManager.fight_CreepManager.init((Fight_Team)this.getTeamPlayer(), (Fight_Team)this.getTeamIA());
		
		/** State Map **/
		this.getTeamPlayer().getStateMap().initGame();
		this.getTeamPlayer().getStateMap().setZoneBPositionMap(this.getTeamPlayer().getTeamID());
		this.getTeamIA().getStateMap().initGame();
		this.getTeamIA().getStateMap().setZoneBPositionMap(this.getTeamIA().getTeamID());
		
		/** UI **/
		mUnitButtonUI = new Fight_SpawnBuildingButtonUI();
		mUnitButtonUI.init(0, 0);
		this.getHUD().attachChild(mUnitButtonUI, Entity.Alignment.LEFT_BOTTOM);
		
		mScoreBarUI = new ScoreBarUI();
		mScoreBarUI.init(0, 0);
		this.getHUD().attachChild(mScoreBarUI, Entity.Alignment.LEFT_TOP);

		/** Sound Button **/
		/*
        Button soundButton = new Button();
        soundButton.init(0, 0, TextureType.SOUND_ICON);
        soundButton.setAction(new IAction(){
            @Override
            public void execute(){
            	SoundManager.swicthSoundONOFF();
            }
        });
        soundButton.setSize(1.0f, 1.0f);
        this.getHUD().attachChild(soundButton, Entity.Alignment.RIGHT_BOTTOM);
        this.getHUD().registerTouchableShape(soundButton);
		 */

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
		
		
		/** Start Sound Background */
		SoundManager.playMusicBackground();
		
		
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




}