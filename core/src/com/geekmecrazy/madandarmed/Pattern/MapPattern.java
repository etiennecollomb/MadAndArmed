package com.geekmecrazy.madandarmed.Pattern;

import com.badlogic.gdx.utils.ObjectMap;


public class MapPattern {

	/** Map names... and AnimatedTextureType associated */
	private ObjectMap<String, TeamMapPattern> teamMapPattern;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ObjectMap<String, TeamMapPattern> getTeamMapPattern() {
		return teamMapPattern;
	}

	public void setTeamMapPattern(ObjectMap<String, TeamMapPattern> teamMapPattern) {
		this.teamMapPattern = teamMapPattern;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
}
