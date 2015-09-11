package com.geekmecrazy.madandarmed.CoreConfig;

//===========================================================
// List of tiled sprite used in game
//===========================================================

public enum AnimatedTextureType {
	//ALIAS				sprite path/ sprite width/ sprite height/ number tile's column / number tile's row/ number of frame/ real size of render

	//WEAPON 
	MISSILE_TYPE_1			("game/missile.png", 64, 64, 1, 1, 1),

	//WEAPON HIT EFFECT8,4
	MISSILE_EXPLOSION		("game/wind_002_s2_blue.png", 512, 512, 5, 5, 25), //A REVOIR !!!
	IMPACT_BULLET			("game/metal_impact_strip_64px.png", 256, 64, 4, 1, 3),
	FIRE_BLAST_001_64PX		("game/SB-2_1_64px.png", 512, 512, 8, 8),
	FIRE_BLAST_001_128PX	("game/SB-2_1_128px.png", 1024, 1024, 8, 8),
	SWORD_001_256PX			("game/swordeffect_256px.png", 1024, 256, 4, 1),
	SWORD_001_64PX			("game/swordeffect_64px.png", 256, 64, 4, 1),

	//DEAD ANIMATION
	DEAD					("game/deadCreep.png", 512, 64, 8, 1, 8),  //CREEP
	BUILDING_DEATH_128PX	("game/cloud_debris_blast_sprite_sheet_128px.png", 1024, 1024, 8, 8), //BUILDING
	BUILDING_DEATH_64PX		("game/cloud_debris_blast_sprite_sheet_64px.png", 512, 512, 8, 8), //BUILDING
	BUILDING_DEATH_32PX		("game/cloud_debris_blast_sprite_sheet_32px.png", 256, 256, 8, 8), //BUILDING

	// QG UI
	BUY					("qg/buy2.png", 128, 64, 2, 1),
	OKKO				("qg/ok_ko.png", 64, 32, 2, 1),
	
    //BARRICADE
    BARRICADES				("game/barricade_tiles.png", 512, 512, 4, 4, 128),

    
    /** UNITS HIGH DEF */
	GLADIATOR_HD_TEAM1      ("game/Units/Gladiator/red", 64),
	GLADIATOR_HD_TEAM2      ("game/Units/Gladiator/blue", 64),
	MARINE_HD_TEAM1			("game/Units/Marine/red", 64),
	MARINE_HD_TEAM2			("game/Units/Marine/blue", 64),
	FLAMETHROWER1_HD_TEAM1  ("game/Units/FlameThrower/red", 64),
	FLAMETHROWER1_HD_TEAM2  ("game/Units/FlameThrower/blue", 64),
	MESH_HD_TEAM1           ("game/Units/Mesh/red", 128),
	MESH_HD_TEAM2           ("game/Units/Mesh/blue", 128),
	BULLHOUND_HD_TEAM1      ("game/Units/Bullhound/red", 128),
	BULLHOUND_HD_TEAM2      ("game/Units/Bullhound/blue", 128),
		
    /** TURRETS HIGH DEF */
	TURRET_01_HD_TEAM1      ("game/Turrets/Turret01_512px_Red", 128),
	TURRET_01_HD_TEAM2      ("game/Turrets/Turret01_512px_Blue", 128),
	TURRET_02_HD_TEAM1      ("game/Turrets/Turret02_256px_Red", 128),
	TURRET_02_HD_TEAM2      ("game/Turrets/Turret02_256px_Blue", 128),

	;


	private String path;
	private int width;
	private int height;
	private int numberOfColumn;
	private int numberOfRow;
	private int numberOfTiled;
	private int realSizeRenderer; //taille reelle du rendu en pixel

	// ===========================================================
	// Constructors
	// ===========================================================

	/** unique sprite **/
	private AnimatedTextureType(final String path, final int width, final int height, final int numberOfColumn, final int numberOfRow) {
		this.path = path;
		this.width = width;
		this.height = height;
		this.numberOfColumn = numberOfColumn;
		this.numberOfRow = numberOfRow;
		this.numberOfTiled = this.numberOfColumn*this.numberOfRow;
		this.realSizeRenderer = 0;
	}

	private AnimatedTextureType(final String path, final int width, final int height, final int numberOfColumn, final int numberOfRow, final int numberOfTiled) {
		this.path = path;
		this.width = width;
		this.height = height;
		this.numberOfColumn = numberOfColumn;
		this.numberOfRow = numberOfRow;
		this.numberOfTiled = numberOfTiled;
		this.realSizeRenderer = 0;
	}

	private AnimatedTextureType(final String path, final int width, final int height, final int numberOfColumn, final int numberOfRow, final int numberOfTiled, final int realSizeRenderer) {
		this.path = path;
		this.width = width;
		this.height = height;
		this.numberOfColumn = numberOfColumn;
		this.numberOfRow = numberOfRow;
		if(numberOfTiled == -1)
			this.numberOfTiled = this.numberOfColumn*this.numberOfRow;
		else
			this.numberOfTiled = numberOfTiled;
		this.realSizeRenderer = realSizeRenderer;
	}
	
	/** multi animated sprite **/
	private AnimatedTextureType(final String path, final int realSizeRenderer) {
		this.path = path;
		this.width = -1;
		this.height = -1;
		this.numberOfColumn = -1;
		this.numberOfRow = -1;
		this.numberOfTiled = -1;
		this.realSizeRenderer = realSizeRenderer;
	}
		

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getPath() {
		return path;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getNumberOfColumn() {
		return numberOfColumn;
	}
	public int getNumberOfRow() {
		return numberOfRow;
	}
	public int getNumberOfTiled() {
		return numberOfTiled;
	}
	public int getRealSizeRenderer() {
		return realSizeRenderer;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

}

