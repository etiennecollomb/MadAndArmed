package com.geekmecrazy.madandarmed.CoreConfig;

import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet.SpriteSheetType;

//===========================================================
// List of tiled sprite used in game
//===========================================================

public enum AnimatedTextureType {
	//ALIAS				sprite path/ sprite width/ sprite height/ number tile's column / number tile's row/ number of frame/ real size of render

	//WEAPON 
	MISSILE_TYPE_1			("game/missile.png", 64, 64),

	//WEAPON HIT EFFECT
	IMPACT_BULLET			("game/metal_impact_strip_64px.png", 64, 64, 3),
	FIRE_BLAST_001_64PX		("game/SB-2_1_64px.png", 64, 64),
	FIRE_BLAST_001_128PX	("game/SB-2_1_128px.png", 128, 128),
	FIRE_BLAST_001_64PX_BLUE("game/SB-2_1_64px.png", 64, 64),
	FIRE_BLAST_001_128PX_BLUE	("game/SB-2_1_128px.png", 128, 128),
	SWORD_001_256PX			("game/swordeffect_256px.png", 256, 256),
	SWORD_001_64PX			("game/swordeffect_64px.png", 64, 64),
	
	HALO_BLUE_192PX			("game/halo_blue_192px.png", 192, 192),
	MISSILE_EXPLOSION		("game/wind_002_s2_blue.png", 102, 102), //A REVOIR (remmettre le png original) !!!

	//DEAD ANIMATION
	DEAD					("game/deadCreep.png", 64, 64),  //CREEP
	BUILDING_DEATH_128PX	("game/cloud_debris_blast_sprite_sheet_128px.png", 128, 128), //BUILDING
	BUILDING_DEATH_64PX		("game/cloud_debris_blast_sprite_sheet_64px.png", 64, 64), //BUILDING
	BUILDING_DEATH_32PX		("game/cloud_debris_blast_sprite_sheet_32px.png", 32, 32), //BUILDING

	// QG UI
	BUY						("qg/buy2.png", 64, 64),
	OKKO					("qg/ok_ko.png", 32, 32),
	
    //BARRICADE
    BARRICADES				("game/barricade_tiles.png", 128, 128),

    
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


	private SpriteSheetType spriteSheetType;
	private String path;
	private int tiledWidth;
	private int tiledHeight;
	private int numberOfTiled;
	private int realSizeRenderer; //taille reelle du rendu en pixel

	// ===========================================================
	// Constructors
	// ===========================================================

	/** unique sprite **/
	private AnimatedTextureType(final String path, final int tiledWidth, final int tiledHeight) {
		this.spriteSheetType = SpriteSheetType.UNIQUE;
		this.path = path;
		this.tiledWidth = tiledWidth;
		this.tiledHeight = tiledHeight;
		this.numberOfTiled = -1;
		this.realSizeRenderer = 0;
	}

	private AnimatedTextureType(final String path, final int tiledWidth, final int tiledHeight, final int numberOfTiled) {
		this.spriteSheetType = SpriteSheetType.UNIQUE;
		this.path = path;;
		this.tiledWidth = tiledWidth;
		this.tiledHeight = tiledHeight;
		this.numberOfTiled = numberOfTiled;
		this.realSizeRenderer = 0;
	}
	
	/** multi animated sprite from ATLAS **/
	private AnimatedTextureType(final String path, final int realSizeRenderer) {
		this.spriteSheetType = SpriteSheetType.FROM_ATLAS;
		this.path = path;
		this.tiledWidth = -1;
		this.tiledHeight = -1;
		this.numberOfTiled = -1;
		this.realSizeRenderer = realSizeRenderer;
	}
		

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public SpriteSheetType getSpriteSheetType() {
		return spriteSheetType;
	}
	
	public String getPath() {
		return path;
	}

	public int getTiledWidth() {
		return tiledWidth;
	}

	public int getTiledHeight() {
		return tiledHeight;
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

