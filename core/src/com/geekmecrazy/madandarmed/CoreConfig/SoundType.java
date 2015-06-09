package com.geekmecrazy.madandarmed.CoreConfig;

public enum SoundType {

	
	//===========================================================
	// List of tiled sprite used in game
	//===========================================================

	SHOTGUN_BLAST_01			("sound/Shotgun_Blast_01.mp3", 2),
	
	;
		
	
	private String path;
	private int secondsLength;

	// ===========================================================
	// Constructors
	// ===========================================================

	private SoundType(final String path, final int secondsLength) {
		this.path = path;
		this.secondsLength = secondsLength;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getPath() {
		return path;
	}

	public int getSecondsLength() {
		return secondsLength;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
}
