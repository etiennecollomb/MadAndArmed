package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Screen.Screen;


public class GamePlayScreen extends Screen implements IUpdatable {

    /** IsoGrid of WarBase scene */
    public static IsoGrid isoGrid;
    
    /** Teams */
    public static Team teamPlayer;
    public static Team teamIA;
    
	// ===========================================================
	// Constructors
	// ===========================================================
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================

	public static void setIsoGrid(IsoGrid isoGrid) {
		FightScreen.isoGrid = isoGrid;
	}
	
    public Team getTeamPlayer(){
        return teamPlayer;
    }

    public Team getTeamIA() {
        return teamIA;
    }

    public static void setTeamPlayer(final Team pTeamPlayer) {
        FightScreen.teamPlayer = pTeamPlayer;
    }

    public static void setTeamIA(final Team pTeamIA) {
        FightScreen.teamIA = pTeamIA;
    }

    @Override
    public void show() {
        System.out.println("Show WarBase Screen");
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

}