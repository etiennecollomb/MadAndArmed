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


public class WarBaseScreen extends Screen implements IUpdatable {

	
    /** IsoGrid of WarBase scene */
    public static IsoGrid isoGrid;

    // ===========================================================
    // Singleton manager
    // ===========================================================

	private static WarBaseScreen warBaseScreen;

    /** Disable object's instantiation (private constructor) */
    private WarBaseScreen(){ }

    /** Access au manager */
    public static WarBaseScreen getManager(){
        if (warBaseScreen == null)
        	warBaseScreen = new WarBaseScreen();
        return warBaseScreen;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

	public static void setIsoGrid(IsoGrid isoGrid) {
		WarBaseScreen.isoGrid = isoGrid;
	}


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onUpdate(){
        
        //principalement update des positions des renderer (a faire apres calcul metier...donc a la fin)
        super.onUpdate();
    }

    @Override
    public void reset(){

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
        
        GameMap.initMap( WarBaseScreen.getManager().getScene() );
        
        
//        /** Memory Usage **/
//        System.out.println("################## MEMORY USAGE ##################");
//        System.out.println("Java Heap : " + Gdx.app.getJavaHeap()/1000000f + " Mo" );
//        System.out.println("Native Heap : " + Gdx.app.getNativeHeap()/1000000f + " Mo" );
//        System.out.println("##################################################");
        
        
	}


	public void runUpdateNextState(){
	}



}