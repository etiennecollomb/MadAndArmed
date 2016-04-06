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
import com.geekmecrazy.madandarmed.Game.Element.WareBase_Team;
import com.geekmecrazy.madandarmed.Game.Element.Property.GameMap;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Game.UI.ScoreBarUI;
import com.geekmecrazy.madandarmed.Game.UI.UIFinishGame;
import com.geekmecrazy.madandarmed.Game.UI.WarBase_BuildingButtonUI;
import com.geekmecrazy.madandarmed.Game.UI.Fight_SpawnBuildingButtonUI;
import com.geekmecrazy.madandarmed.IA.AstarMap;
import com.geekmecrazy.madandarmed.IA.GlobalAstar;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Renderer.IsoGridRenderer;
import com.geekmecrazy.madandarmed.Screen.Screen;


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
        teamPlayer.notifyListeners();

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
    public void reset(){

        this.setTeamPlayer(null);
    }

    @Override
    public WareBase_Team getTeamPlayer() {
        return (WareBase_Team)teamPlayer;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    /** Creation et initialisation du manager */
    public void init(final Scene pScene) {
        super.init(pScene);
    }

    @Override
    public void show() {
        System.out.println("Show WarBase Screen");
    }

    // Lance le screen la premiere fois qu'on y accede
	public void newGame(){
        
        /** Init IsoGrid */
		isoGrid = new IsoGrid();
		isoGrid.init(GlobalManager.GROUNDTILEDWIDTH, GlobalManager.GROUNDTILEDHEIGHT, this.getScene());
        IsoGridRenderer gridRenderer = new IsoGridRenderer();
        gridRenderer.init(isoGrid);
        //this.getScene().attachChild(gridRenderer);
        
        /** Init des 2 teams */
		this.setTeamPlayer( new WareBase_Team(TeamID.TEAM1));
		
        GlobalManager.createManagersForWarBase((WareBase_Team)this.teamPlayer);
        
        GameMap.initMap( GlobalManager.warBaseScreen.getScene() );
        
        /** init UIs */

        mBuildingButtonUI = new WarBase_BuildingButtonUI();
        mBuildingButtonUI.init(0, 0);
        this.getHUD().attachChild(mBuildingButtonUI, Entity.Alignment.LEFT_BOTTOM);
        
//        /** Memory Usage **/
//        System.out.println("################## MEMORY USAGE ##################");
//        System.out.println("Java Heap : " + Gdx.app.getJavaHeap()/1000000f + " Mo" );
//        System.out.println("Native Heap : " + Gdx.app.getNativeHeap()/1000000f + " Mo" );
//        System.out.println("##################################################");
        
        
	}

    public void closeGame(){
        //TODO: a revoir pour faire bien clean
    	GlobalManager.destroyManagers();
    }

	public void runUpdateNextState(){
		GlobalManager.gamePlay_BuildingManager.runUpdateNextState();
	}
	


}