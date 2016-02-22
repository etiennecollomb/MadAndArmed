package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Element.Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Element.Property.GameMap;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Game.UI.ScoreBarUI;
import com.geekmecrazy.madandarmed.Game.UI.UIFinishGame;
import com.geekmecrazy.madandarmed.Game.UI.SpawnBuildingButtonUI;
import com.geekmecrazy.madandarmed.IA.AstarMap;
import com.geekmecrazy.madandarmed.IA.GlobalAstar;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Renderer.IsoGridRenderer;
import com.geekmecrazy.madandarmed.Screen.Screen;


public class FightScreen extends Screen implements IUpdatable {

    private static final long TURN_MONEY_LENGTH =1000;
    public static final int START_MONEY=500;
    public static final int MAX_MONEY=2000;
    public static final int TURN_MONEY=500; //30

    public static final int MAX_THORIUM=1000;

    public static final int MAX_UNITS=1000;

    /** Teams */
    public static Team teamPlayer;
    public static Team teamIA;

    /** UI */
    private UIFinishGame uiFinishGame;
    private SpawnBuildingButtonUI mUnitButtonUI;
    private ScoreBarUI mScoreBarUI;

    /** Time stuff */
    private static long currentTime;
    private static long previousMoneyTurnTime;
    
    /** IsoGrid of fight scene */
    public static IsoGrid isoGrid;

    // ===========================================================
    // Singleton manager
    // ===========================================================

	private static FightScreen fightScreen;

    /** Disable object's instantiation (private constructor) */
    private FightScreen(){ }

    /** Access au manager */
    public static FightScreen getManager(){
        if (fightScreen == null)
            fightScreen = new FightScreen();
        return fightScreen;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public Team getTeamPlayer(){
        return teamPlayer;
    }

    public Team getTeamIA() {
        return teamIA;
    }

    public UIFinishGame getUiFinishGame() {
        return uiFinishGame;
    }

    public static long getCurrentTime() {
        return currentTime;
    }

    public static long getPreviousMoneyTurnTime() {
        return previousMoneyTurnTime;
    }

    public static void setTeamPlayer(final Team pTeamPlayer) {
        FightScreen.teamPlayer = pTeamPlayer;
    }

    public static void setTeamIA(final Team pTeamIA) {
        FightScreen.teamIA = pTeamIA;
    }

    public void setUiFinishGame(final UIFinishGame pUiFinishGame) {
        this.uiFinishGame = pUiFinishGame;
    }

    public static void setCurrentTime(final long pCurrentTime) {
        FightScreen.currentTime = pCurrentTime;
    }

    public static void setPreviousMoneyTurnTime(final long pPreviousMoneyTurnTime) {
        FightScreen.previousMoneyTurnTime = pPreviousMoneyTurnTime;
    }

	public static void setIsoGrid(IsoGrid isoGrid) {
		FightScreen.isoGrid = isoGrid;
	}


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onUpdate(){

        /**** Create Creep ***/
        FightCreepManager.getManager().excuteAskForCreateCreep();
        FightBuildingManager.getManager().excuteAskForCreateSpawnBuilding();

        /******** PREPARATION DU TOUR ********/
        FightScreen.currentTime = System.currentTimeMillis();
        this.updateNextMoneyTurn();

        /******** UPDATE EN COURS ********/
        teamPlayer.notifyListeners();
        teamIA.notifyListeners();

        /******** TRAITEMENT DU TOUR ********/
        this.runUpdateNextState();

        /******** RECYCLE ********/
        FightCreepManager.getManager().recycleCreep();
        FightBuildingManager.getManager().recycleBuilding();

        /******** FINALISATION DU TOUR ********/
//        this.getTeamPlayer().calculateMilitarySpace();
//        this.getTeamIA().calculateMilitarySpace();

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
    public void reset(){

        this.setTeamPlayer(null);
        this.setTeamIA(null);

        this.setCurrentTime(0);
        this.setPreviousMoneyTurnTime(0);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    /** Creation et initialisation du manager */
    public void init(final Scene pScene) {
        super.init(pScene);

        this.previousMoneyTurnTime=System.currentTimeMillis();

        GlobalAstar.init();

        AstarMap.init(GlobalManager.STARMAP_WIDTH, GlobalManager.STARMAP_HEIGHT);
    }

    @Override
    public void show() {
        System.out.println("Show Fight Screen");
    }

    // Lance le screen la premiere fois qu'on y accede
	public void newGame(){
		
		/** Init SoundManager */
        new SoundManager();
        
        /** Init IsoGrid */
		isoGrid = new IsoGrid();
		isoGrid.init(GlobalManager.GROUNDTILEDWIDTH, GlobalManager.GROUNDTILEDHEIGHT, this.getScene());
        IsoGridRenderer gridRenderer = new IsoGridRenderer();
        gridRenderer.init(isoGrid);
        //this.getScene().attachChild(gridRenderer);
        
		/** Init des 2 teams */
		this.setTeamPlayer( new Team(START_MONEY, TURN_MONEY, MAX_MONEY, TeamID.TEAM1, MAX_THORIUM));
		this.setTeamIA( new Team(START_MONEY, TURN_MONEY, MAX_MONEY, TeamID.TEAM2, MAX_THORIUM));

		FightBuildingManager.initManager(this.getTeamPlayer(), this.getTeamIA());
		FightCreepManager.initManager(this.getTeamPlayer(), this.getTeamIA());
		FightTurnManager.initManager();
		FightIaManager.initManager();
		FightWeaponManager.initManager();

		FightBuildingManager.getManager().initBuildingAtStart();
		FightBuildingManager.getManager().initTarget();

        this.getTeamPlayer().getStateMap().initGame();
        this.getTeamPlayer().getStateMap().setZoneBPositionMap(this.getTeamPlayer().getTeamID());
        this.getTeamIA().getStateMap().initGame();
        this.getTeamIA().getStateMap().setZoneBPositionMap(this.getTeamIA().getTeamID());

		GameMap.initMap( FightScreen.getManager().getScene() );
                
        /** init UIs */
		uiFinishGame = new UIFinishGame();
		uiFinishGame.initUI();

        mUnitButtonUI = new SpawnBuildingButtonUI();
        mUnitButtonUI.init(0, 0);
        this.getHUD().attachChild(mUnitButtonUI, Entity.Alignment.LEFT_BOTTOM);

        mScoreBarUI = new ScoreBarUI();
        mScoreBarUI.init(0, 0);
        this.getHUD().attachChild(mScoreBarUI, Entity.Alignment.LEFT_TOP);

        /** Sound Button */
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
        FightScreen.getManager().getHUD().registerTouchableShape(soundButton);
        
        
		/** Start Sound Background */
        SoundManager.playMusicBackground();
        
        
        
//        /** Memory Usage **/
//        System.out.println("################## MEMORY USAGE ##################");
//        System.out.println("Java Heap : " + Gdx.app.getJavaHeap()/1000000f + " Mo" );
//        System.out.println("Native Heap : " + Gdx.app.getNativeHeap()/1000000f + " Mo" );
//        System.out.println("##################################################");
        
        
	}

	public void loadData() {
		
		/** Json */
		PatternLoader.loadPatternsData();
		
	}

    public void closeGame(){
        //TODO: a revoir pour faire bien clean
        FightBuildingManager.destroyManager();
        FightCreepManager.destroyManager();
        FightIaManager.destroyManager();
        FightWeaponManager.destroyManager();
    }

	public Team getOtherTeam(final Team team){
		if(team == this.getTeamPlayer())
            return this.getTeamIA();
		else
            return this.getTeamPlayer();
	}

	public void runUpdateNextState(){
		FightTurnManager.getManager().runUpdateNextState();	
		//IaManager.getManager().runUpdateNextState();
		FightBuildingManager.getManager().runUpdateNextState();
		FightCreepManager.getManager().runUpdateNextState();
		FightWeaponManager.getManager().onUpdate();
	}

	public void updateNextMoneyTurn(){
		if(FightScreen.getCurrentTime() > this.getPreviousMoneyTurnTime() + TURN_MONEY_LENGTH){
            this.getTeamPlayer().addMoneyNewTurn();
            this.getTeamIA().addMoneyNewTurn();
			this.setPreviousMoneyTurnTime(FightScreen.getCurrentTime());
		}
	}


}