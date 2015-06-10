package com.geekmecrazy.madandarmed.CoreConfig;

public enum SoundType {

	
	//===========================================================
	// List of tiled sprite used in game
	//===========================================================

	SHOTGUN_BLAST_01			("sound/Shotgun_Blast_01.mp3", 2000),
	SUBMACHIN_SHOT_01			("sound/Submachin_shot_01.mp3", 800),
	PUNCH_01					("sound/Punch_01.mp3", 500),
	PUNCH_02					("sound/Punch_02.mp3", 500),
	

	;
		
	
	private String path;
	private int msLength; /** MilliSeconds */

	// ===========================================================
	// Constructors
	// ===========================================================

	private SoundType(final String path, final int msLength) {
		this.path = path;
		this.msLength = msLength;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getPath() {
		return path;
	}

	public int getMsLength() {
		return msLength;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
}
