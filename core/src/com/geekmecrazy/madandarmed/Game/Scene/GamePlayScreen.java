package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team;
import com.geekmecrazy.madandarmed.Screen.Screen;


public class GamePlayScreen extends Screen implements IUpdatable {

    public static final int MAX_UNITS=1000;
    
    /** IsoGrid of WarBase scene */
    public static IsoGrid isoGrid;

	/** Teams */
    public static GamePlay_Team teamPlayer;
    public static GamePlay_Team teamIA;
    
	// ===========================================================
	// Constructors
	// ===========================================================
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================
    
    public static IsoGrid getIsoGrid() {
		return isoGrid;
	}

	public static void setIsoGrid(IsoGrid isoGrid) {
		GamePlayScreen.isoGrid = isoGrid;
	}
	
    public GamePlay_Team getTeamPlayer(){
        return teamPlayer;
    }

    public GamePlay_Team getTeamIA() {
        return teamIA;
    }

    public static void setTeamPlayer(final GamePlay_Team pTeamPlayer) {
    	GamePlayScreen.teamPlayer = pTeamPlayer;
    }

    public static void setTeamIA(final GamePlay_Team pTeamIA) {
    	GamePlayScreen.teamIA = pTeamIA;
    }

    @Override
    public void show() {
        System.out.println("Show GamePlay Screen");
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

}