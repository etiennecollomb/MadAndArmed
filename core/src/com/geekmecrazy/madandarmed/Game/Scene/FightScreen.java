package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Entity.Alignment;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Element.Property.GameMap;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Game.UI.ScoreBarUI;
import com.geekmecrazy.madandarmed.Game.UI.UIFinishGame;
import com.geekmecrazy.madandarmed.Game.UI.UnitButtonUI;
import com.geekmecrazy.madandarmed.IA.AstarMap;
import com.geekmecrazy.madandarmed.IA.GlobalAstar;
import com.geekmecrazy.madandarmed.IA.IsoMapState;
import com.geekmecrazy.madandarmed.Renderer.BarricadeRenderer;
import com.geekmecrazy.madandarmed.Renderer.FightBuildingRenderer;
import com.geekmecrazy.madandarmed.Renderer.IsoGridRenderer;
import com.geekmecrazy.madandarmed.Screen.Screen;
import com.geekmecrazy.madandarmed.XML.DataManager;


public class FightScreen extends Screen implements IUpdatable {

    private static final long TURN_MONEY_LENGTH =1000;
    public static final int START_MONEY=500;
    public static final int MAX_MONEY=2000;
    public static final int TURN_MONEY=500; //30

    public static final int MAX_THORIUM=1000;

    public static final int MAX_UNITS=1000;

    public static final int TEAM_PLAYER_ID = 0;
    public static final int TEAM_IA_ID = 1;

    /** Teams */
    private static Team teamPlayer;
    private static Team teamIA;

    /** UI */
    private UIFinishGame uiFinishGame;
    private UnitButtonUI mUnitButtonUI;
    private ScoreBarUI mScoreBarUI;

    /** Time stuff */
    private static long currentTime;
    private static long previousMoneyTurnTime;


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

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onUpdate(){

        /**** Create Creep ***/
        CreepManager.getManager().excuteAskForCreateCreep();

        /******** PREPARATION DU TOUR ********/
        FightScreen.currentTime = System.currentTimeMillis();
        this.updateNextMoneyTurn();

        /******** UPDATE EN COURS ********/
        teamPlayer.notifyListeners();
        teamIA.notifyListeners();

        /******** TRAITEMENT DU TOUR ********/
        this.runUpdateNextState();

        /******** RECYCLE ********/
        CreepManager.getManager().recycleCreep();
        BuildingManager.getManager().recycleBuilding();

        /******** FINALISATION DU TOUR ********/
        this.getTeamPlayer().calculateMilitarySpace();
        this.getTeamIA().calculateMilitarySpace();

        this.getTeamPlayer().getStateMap().swap();
        this.getTeamIA().getStateMap().swap();

        //principalement update des positions des renderer (a faire apres calcul metier...donc a la fin)
        super.onUpdate();

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

    // Lance une nouvelle partie
	public void newGame(){

		// Init des 2 teams
		this.setTeamPlayer( new Team(START_MONEY, TURN_MONEY, MAX_MONEY, DataManager.getMapPattern().getSpawnPointPlayer(), TEAM_PLAYER_ID, MAX_THORIUM));
		this.setTeamIA( new Team(START_MONEY, TURN_MONEY, MAX_MONEY, DataManager.getMapPattern().getSpawnPointIa(), TEAM_IA_ID, MAX_THORIUM));

		BuildingManager.initManager(this.getScene(), this.getTeamPlayer(), this.getTeamIA());
		CreepManager.initManager(this.getScene(), this.getTeamPlayer(), this.getTeamIA());
		IaManager.initManager();
		MissileManager.initManager();

		BuildingManager.getManager().initBuildingAtStart();
		BuildingManager.getManager().initTarget();

        this.getTeamPlayer().getStateMap().initGame();
        this.getTeamPlayer().getStateMap().setZoneBPositionMap(this.getTeamPlayer().getTeamID());
        this.getTeamIA().getStateMap().initGame();
        this.getTeamIA().getStateMap().setZoneBPositionMap(this.getTeamIA().getTeamID());

		GameMap.initMap();

        /** Init IsoGrid */
        final IsoGrid grid = new IsoGrid();
        grid.init(GlobalManager.GROUNDTILEDWIDTH, GlobalManager.GROUNDTILEDHEIGHT, this.getScene());
        IsoGridRenderer gridRenderer = new IsoGridRenderer();
        gridRenderer.init(grid);
        this.getScene().attachChild(gridRenderer);
                
        /** init UIs */
		uiFinishGame = new UIFinishGame();
		uiFinishGame.initUI();

        mUnitButtonUI = new UnitButtonUI();
        mUnitButtonUI.init(0, 0);
        this.getHUD().attachChild(mUnitButtonUI, Entity.Alignment.LEFT_BOTTOM);

        mScoreBarUI = new ScoreBarUI();
        mScoreBarUI.init(0, 0);
        this.getHUD().attachChild(mScoreBarUI, Entity.Alignment.LEFT_TOP);

        /** Barricade Button */
        Button newBarricadButton = new Button();
        newBarricadButton.init(0, 0, TextureType.BARRICADE_ICON);
        newBarricadButton.setAction(new IAction(){
            @Override
            public void execute(){
                //System.out.println("#### TOUCH BARRICADE BUTTON !!");
                BarricadeRenderer fBR = new BarricadeRenderer();
                SpriteSheet sp = new SpriteSheet(AnimatedTextureType.BARRICADES, true);
                fBR.init(sp, grid);
                grid.place(fBR, 20, 50);
                getScene().attachChild(fBR);
                getScene().registerTouchableShape(fBR);
            }
        });
        newBarricadButton.setSize(1.5f, 1.5f);
        this.getHUD().attachChild(newBarricadButton, Entity.Alignment.RIGHT_BOTTOM);
        FightScreen.getManager().getHUD().registerTouchableShape(newBarricadButton);
        
        

        
        //TEST ISO
        /*
        Sprite testIsoSprite = new Sprite();
        testIsoSprite.init(TextureType.BARRICADE_ICON2);
        grid.place(testIsoSprite, 20, 50);
        this.getScene().attachChild(testIsoSprite);
        */
        //FIN TEST
        
        
	}

	public void loadData() {
		DataManager.loadMapPattern();
		DataManager.loadCreepsPattern();
		DataManager.loadBuildingsPattern();
		DataManager.updateCreepsPatternAnimation();
		DataManager.updateBuildingsPatternAnimation();
		/** Debug */
		DataManager.logCreepsPattern();
		DataManager.logBuildingsPattern();
	}

    public void closeGame(){
        //TODO: a revoir pour faire bien clean
        BuildingManager.destroyManager();
        CreepManager.destroyManager();
        IaManager.destroyManager();
        MissileManager.destroyManager();
    }

	public Team getOtherTeam(final Team team){
		if(team == this.getTeamPlayer())
            return this.getTeamIA();
		else
            return this.getTeamPlayer();
	}

	public void runUpdateNextState(){
		IaManager.getManager().runUpdateNextState();	
		BuildingManager.getManager().runUpdateNextState();
		CreepManager.getManager().runUpdateNextState();
		MissileManager.getManager().runUpdateNextState();
	}

	public void updateNextMoneyTurn(){
		if(FightScreen.getCurrentTime() > this.getPreviousMoneyTurnTime() + TURN_MONEY_LENGTH){
            this.getTeamPlayer().addMoneyNewTurn();
            this.getTeamIA().addMoneyNewTurn();
			this.setPreviousMoneyTurnTime(FightScreen.getCurrentTime());
		}
	}


}