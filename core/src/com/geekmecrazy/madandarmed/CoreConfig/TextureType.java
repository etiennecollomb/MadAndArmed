package com.geekmecrazy.madandarmed.CoreConfig;


//===========================================================
// List of sprite used in game
//===========================================================



public enum TextureType{
	//ALIAS				sprite path / sprite width / sprite height

	//QG UI
	ICONE_BUILD			("qg/batiment-icone.png", 64, 64),

	//QG BUILDING UPGRADE
	SWORD				("qg/sword.png", 64, 64),
	SHIELD				("qg/shield.png", 64, 64),
	HELMET				("qg/helmet.png", 64, 64),
	HEART				("qg/heart.png", 64, 64),

	//QG BUILDING ICON
	QG_BUILDING_ICON_0  ("qg/QG_BUILDING_ICON_0.png", 128, 128),
	QG_BUILDING_ICON_1  ("qg/QG_BUILDING_ICON_1.png", 128, 128),
	QG_BUILDING_ICON_2  ("qg/QG_BUILDING_ICON_2.png", 128, 128),
	QG_BUILDING_ICON_3  ("qg/QG_BUILDING_ICON_3.png", 128, 128),
	QG_BUILDING_ICON_4  ("qg/QG_BUILDING_ICON_4.png", 128, 128),
	QG_BUILDING_ICON_5  ("qg/QG_BUILDING_ICON_5.png", 128, 128),
	QG_BUILDING_ICON_6  ("qg/QG_BUILDING_ICON_6.png", 128, 128),
	QG_BUILDING_ICON_7  ("qg/QG_BUILDING_ICON_7.png", 128, 128),

	//FIGHT MAP & UI
	GROUND_BATTLE				("game/groundBattle_green_001.png", 1024, 480),
	BORD_TREE_UP  				("game/bord_trees_up.png", 1024, 128),
	BORD_TREE_DOWN  			("game/bord_trees_down.png", 1024, 128),
	SOL_SOUS_BUILDING  			("game/Sol_Sous_Building.png", 128, 128),
	SOL_SOUS_BUILDING_BIG 		("game/Sol_Sous_Building_big.png", 256, 256),

	PROGRESS_BAR_BORDER			("game/score_bar_border.png", 4.8f, 1.2f),
	PROGRESS_BAR_YELLOW			("game/score_bar_yellow.png", 4.8f, 1.2f),
	//PROGRESS_BAR_PURPLE			("game/score_bar_purple.png", 256, 64),
	PROGRESS_BAR_BLUE			    ("game/score_bar_blue.png", 4.8f, 1.2f),
	//PROGRESS_BAR_ORANGE			("game/score_bar_orange.png", 256, 64),
	PROGRESS_BAR_BACKGROUND		("game/score_bar_background.png", 4.8f, 1.2f),
	PROGRESS_BAR_REFLET			("game/score_bar_reflet.png", 4.8f, 1.2f),

	BLOOD_ON_FLOOR				("game/bloodOnFloor.png", 64, 64),

	OPTIONS						("game/gear.png", 128, 128),

	//BUTTON FIGHT TEAM 1 (USER)
	BUTTON_UNIT_BACKGROUND				("game/button_unit_background.png", 2.1f, 2.1f),
	BUTTON_UNIT_REFLET		            ("game/button_unit_background_reflet.png", 2.1f, 2.1f),
	BUTTON_UNIT_SHADOW		            ("game/button_unit_background_shadow.png", 2.1f, 2.1f),
	GLADIATOR_ICON						("game/gladiator_icon.png", 2.1f,2.1f),
	MARINE_SMALL_ICON					("game/marine_small_icon.png", 2.1f,2.1f),
	MARINE_BIG_ICON						("game/marine_big_icon.png", 2.1f,2.1f),
	
	/** Tiles Ground TEST **/
	TILE_FULL_BROWN                     ("game/Tiles/128_64/Full_BROWN.png", 128, 64),
	
	TILE_1QUART_UP                      ("game/Tiles/128_64/1quartUP.png", 128, 64),
	TILE_1QUART_RIGHT                   ("game/Tiles/128_64/1quartRIGHT.png", 128, 64),
	TILE_1QUART_DOWN                    ("game/Tiles/128_64/1quartDOWN.png", 128, 64),
	TILE_1QUART_LEFT                    ("game/Tiles/128_64/1quartLEFT.png", 128, 64),
	TILE_3QUART_UP                      ("game/Tiles/128_64/3quartUP.png", 128, 64),
	TILE_3QUART_RIGHT                   ("game/Tiles/128_64/3quartRIGHT.png", 128, 64),
	TILE_3QUART_DOWN                    ("game/Tiles/128_64/3quartDOWN.png", 128, 64),
	TILE_3QUART_LEFT                    ("game/Tiles/128_64/3quartLEFT.png", 128, 64),
	TILE_DIAG_LEFT_UP                   ("game/Tiles/128_64/diagLEFT_UP.png", 128, 64),
	TILE_DIAG_RIGHT_UP                  ("game/Tiles/128_64/diagRIGHT_UP.png", 128, 64),
	TILE_DIAG_RIGHT_DOWN                ("game/Tiles/128_64/diagRIGHT_DOWN.png", 128, 64),
	TILE_DIAG_LEFT_DOWN                 ("game/Tiles/128_64/diagLEFT_DOWN.png", 128, 64),
	TILE_FULL                           ("game/Tiles/128_64/Full.png", 128, 64),

	TILE_FULL_RED                     	("game/Tiles/Full_RED_64x32.png", 64, 32),

	/** Tiles Ground 01 **/
	TILE_GROUND01_FULL_BROWN                     ("game/Tiles/128_64/Ground01/tile_40.png", 128, 64),
	
	TILE_GROUND01_1QUART_UP                      ("game/Tiles/128_64/Ground01/tile_13.png", 128, 64),
	TILE_GROUND01_1QUART_RIGHT                   ("game/Tiles/128_64/Ground01/tile_37.png", 128, 64),
	TILE_GROUND01_1QUART_DOWN                    ("game/Tiles/128_64/Ground01/tile_39.png", 128, 64),
	TILE_GROUND01_1QUART_LEFT                    ("game/Tiles/128_64/Ground01/tile_31.png", 128, 64),
	TILE_GROUND01_3QUART_UP                      ("game/Tiles/128_64/Ground01/tile_1.png", 128, 64),
	TILE_GROUND01_3QUART_RIGHT                   ("game/Tiles/128_64/Ground01/tile_9.png", 128, 64),
	TILE_GROUND01_3QUART_DOWN                    ("game/Tiles/128_64/Ground01/tile_27.png", 128, 64),
	TILE_GROUND01_3QUART_LEFT                    ("game/Tiles/128_64/Ground01/tile_3.png", 128, 64),
	TILE_GROUND01_DIAG_LEFT_UP                   ("game/Tiles/128_64/Ground01/tile_4.png", 128, 64),
	TILE_GROUND01_DIAG_RIGHT_UP                  ("game/Tiles/128_64/Ground01/tile_10.png", 128, 64),
	TILE_GROUND01_DIAG_RIGHT_DOWN                ("game/Tiles/128_64/Ground01/tile_36.png", 128, 64),
	TILE_GROUND01_DIAG_LEFT_DOWN                 ("game/Tiles/128_64/Ground01/tile_30.png", 128, 64),
	TILE_GROUND01_FULL                           ("game/Tiles/128_64/Ground01/tile_0.png", 128, 64),



	
	//DECORATION
	PLANT_00                    ("game/Decorations/Plant00_64.png", 64, 64),
	PLANT_01                    ("game/Decorations/Plant01_64.png", 64, 64),
	PLANT_02                    ("game/Decorations/Plant02_64.png", 64, 64),
	PLANT_03                    ("game/Decorations/Plant03_64.png", 64, 64),

	PLANT_00_MEDIUM                    ("game/Decorations/Plant00_128.png", 128, 128),
	PLANT_01_MEDIUM                    ("game/Decorations/Plant01_128.png", 128, 128),
	PLANT_02_MEDIUM                    ("game/Decorations/Plant02_128.png", 128, 128),
	PLANT_03_MEDIUM                    ("game/Decorations/Plant03_128.png", 128, 128),

	//HQ
	BUILDING_TEST       	("game/building_test.png", 200, 200),
	//GREEN_ARROW_LEFT    ("game/greenArrow_left.png", 256, 256),
	//GREEN_ARROW_TOP     ("game/greenArrow_top.png", 256, 256),
	//GREEN_ARROW_RIGHT   ("game/greenArrow_right.png", 256, 256),
	//GREEN_ARROW_BOTTOM  ("game/greenArrow_bottom.png", 256, 256);

	ORTHOGRID       		("game/OrthometricGrid.png", 2048, 2048),
	ISOGRID       			("game/IsometricGrid.png", 1024, 1024),

	BARRICADE_ICON 			("game/BarricadeIcon.png", 1f, 1f),
	BARRICADE_ICON2 		("game/BarricadeIcon.png", 128, 128),

	SOUND_ICON 				("game/SoundIcon.png", 1f, 1f),
	SOUND_ICON2 			("game/SoundIcon.png", 128, 128),

	;



	private String path;
	private float width;
	private float height;

	// ===========================================================
	// Constructors
	// ===========================================================

	private TextureType(final String path, final float width, final float height) {
		this.path = path;
		this.width = width;
		this.height = height;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public String getPath() {
		return path;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
}
