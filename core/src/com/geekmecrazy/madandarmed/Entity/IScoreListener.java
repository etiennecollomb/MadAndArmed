package com.geekmecrazy.madandarmed.Entity;

import com.geekmecrazy.madandarmed.Game.Element.Fight_Team;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team;


public interface IScoreListener {
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

    public void scoreChange(Fight_Team team);
	
	// ===========================================================
	// Methods
	// ===========================================================
}
