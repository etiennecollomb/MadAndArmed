package com.geekmecrazy.madandarmed.Game.Element;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.IMoneyListener;
import com.geekmecrazy.madandarmed.Entity.IScoreListener;
import com.geekmecrazy.madandarmed.Game.Scene.GamePlayScreen;
import com.geekmecrazy.madandarmed.IA.StateMap;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingName;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;

/**
 * Joueur (thune+score...) :)
 */
public class WareBase_Team extends GamePlay_Team {


	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public WareBase_Team(int startingMoney, int moneyByTurn, int moneyMax, TeamID teamID_, int thoriumMax) {
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
