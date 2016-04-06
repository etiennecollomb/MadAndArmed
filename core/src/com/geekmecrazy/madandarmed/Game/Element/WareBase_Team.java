package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;

/**
 * Joueur (thune+score...) :)
 */
public class WareBase_Team extends GamePlay_Team {


	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public WareBase_Team(TeamID teamID_) {
		super(teamID_, GlobalManager.WARBASE_STARMAP_WIDTH, GlobalManager.WARBASE_STARMAP_HEIGHT);
	}


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	

	// ===========================================================
	// Methods
	// ===========================================================


	@Override
	public void notifyListeners() {
		// TODO Auto-generated method stub
		
	}
	
}
