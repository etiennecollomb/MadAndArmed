package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team;
import com.geekmecrazy.madandarmed.Screen.Screen;


public abstract class GamePlayScreen extends Screen implements IUpdatable {

    public static final int MAX_UNITS=1000;
    
    /** IsoGrid of WarBase scene */
    private IsoGrid isoGrid;

	/** Teams */
    private GamePlay_Team teamPlayer;
    private GamePlay_Team teamIA;
    
	// ===========================================================
	// Constructors
	// ===========================================================
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================
    
    public IsoGrid getIsoGrid() {
		return isoGrid;
	}

	public void setIsoGrid(IsoGrid isoGrid) {
		this.isoGrid = isoGrid;
	}
	
    public GamePlay_Team getTeamPlayer(){
        return teamPlayer;
    }

    public GamePlay_Team getTeamIA() {
        return teamIA;
    }

    public void setTeamPlayer(final GamePlay_Team pTeamPlayer) {
    	this.teamPlayer = pTeamPlayer;
    }

    public void setTeamIA(final GamePlay_Team pTeamIA) {
    	this.teamIA = pTeamIA;
    }

    @Override
    public void show() {
        System.out.println("Show GamePlay Screen");
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    
    /** Load Screen First Time **/
    @Override
    public void loadScreenFirstTime(){
    		super.loadScreenFirstTime();
    }

    /** Load Screen **/
    @Override
    public void loadScreen(){
    	if(this.isLoadFirstTime()) this.loadScreenFirstTime();
    	super.loadScreen();
    	
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

}