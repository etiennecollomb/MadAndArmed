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

	//TEAM 1 (USER) UNITS
	MARINE_SMALL_TEAM1		("game/marine1-red_64px_0000_0000#0000.png", 1024, 1024,16,16, -1, 32),
	MARINE_SMALL_TEAM1_2	("game/marine1-red-shoot_128px_0000_0000#0000.png", 1024, 128,8,1),
	MARINE_SMALL_TEAM1_3	("game/marine1-red-shoot_128px_0000_0000#0001.png", 1024, 128,8,1),

	MARINE_BIG_TEAM1        ("game/Mech1_red_128px_0000_0000#0000.png", 1024, 1024, 8, 8, -1, 64),
    MARINE_BIG_TEAM1_2		("game/Mech1_red_128px_0000_0000#0001.png", 1024, 1024, 8, 8),
    MARINE_BIG_TEAM1_3		("game/Mech1_red_128px_0000_0001#0000.png", 1024, 1024, 8, 8),
    MARINE_BIG_TEAM1_4		("game/Mech1_red_128px_0000_0001#0001.png", 1024, 1024, 8, 8),
    MARINE_BIG_TEAM1_5		("game/Mech1_red_128px_0000_0002#0000.png", 1024, 1024, 8, 8),
    MARINE_BIG_TEAM1_6		("game/Mech1_red_128px_0000_0002#0001.png", 1024, 1024, 8, 8),
	//MARINE_BIG_TEAM1_7		("game/marine1-red-shoot_256px_0000_0000#0002.png", 1024, 256, 4, 1),
	//MARINE_BIG_TEAM1_8		("game/marine1-red-shoot_256px_0000_0000#0003.png", 1024, 256, 4, 1),

	GLADIATOR_TEAM1			("game/gladiator01-red_64px_0000_0000#0000.png", 1024, 1024, 16, 16, -1, 32),
	GLADIATOR_TEAM1_2		("game/gladiator01-red-shoot_128px_0000_0000#0000.png", 1024, 1024, 8, 8),
	GLADIATOR_TEAM1_3		("game/gladiator01-red-shoot_128px_0000_0000#0001.png", 1024, 1024, 8, 8),

	//TEAM 2 (IA) UNITS
	MARINE_SMALL_TEAM2		("game/marine1-blue_64px_0000_0000#0000.png", 1024, 1024,16,16, -1, 32),
	MARINE_SMALL_TEAM2_2	("game/marine1-blue-shoot_128px_0000_0000#0000.png", 1024, 128,8,1),
	MARINE_SMALL_TEAM2_3	("game/marine1-blue-shoot_128px_0000_0000#0001.png", 1024, 128,8,1),

	MARINE_BIG_TEAM2		("game/Mech1_128px_0000_0000#0000.png", 1024, 1024, 8, 8, -1, 64),
	MARINE_BIG_TEAM2_2		("game/Mech1_128px_0000_0000#0001.png", 1024, 1024, 8, 8),
	MARINE_BIG_TEAM2_3		("game/Mech1_128px_0000_0001#0000.png", 1024, 1024, 8, 8),
	MARINE_BIG_TEAM2_4		("game/Mech1_128px_0000_0001#0001.png", 1024, 1024, 8, 8),
	MARINE_BIG_TEAM2_5		("game/Mech1_128px_0000_0002#0000.png", 1024, 1024, 8, 8),
	MARINE_BIG_TEAM2_6		("game/Mech1_128px_0000_0002#0001.png", 1024, 1024, 8, 8),
	//MARINE_BIG_TEAM2_7		("game/marine1-blue-shoot_256px_0000_0000#0002.png", 1024, 256, 4, 1),
	//MARINE_BIG_TEAM2_8		("game/marine1-blue-shoot_256px_0000_0000#0003.png", 1024, 256, 4, 1),

	GLADIATOR_TEAM2			("game/gladiator01-blue_64px_0000_0000#0000.png", 1024, 1024, 16, 16, -1, 32),
	GLADIATOR_TEAM2_2		("game/gladiator01-blue-shoot_128px_0000_0000#0000.png", 1024, 1024, 8, 8),
	GLADIATOR_TEAM2_3		("game/gladiator01-blue-shoot_128px_0000_0000#0001.png", 1024, 1024, 8, 8),

	//FLAMETHOWER				("game/Flame-Thrower-01_64px_0000_0000#0000.png", 1024, 64, 16, 1, -1, 64),
	TURRET_01				("game/Fr-Launcher-03_128px_0000_0000#0000.png", 1024, 128, 8, 1, -1, 96),
	TURRET_01_2				("game/Fr-Launcher-03_128px_0000_0000#0001.png", 1024, 128, 8, 1, -1, 96),

	TURRET_02				("game/Turret-08_256px_0000_0000#0000.png", 1024, 256, 4, 1, -1, 96),
	TURRET_02_2				("game/Turret-08_256px_0000_0000#0001.png", 1024, 256, 4, 1, -1, 96),
	TURRET_02_3				("game/Turret-08_256px_0000_0000#0002.png", 1024, 256, 4, 1, -1, 96),
	TURRET_02_4				("game/Turret-08_256px_0000_0000#0003.png", 1024, 256, 4, 1, -1, 96),

	// QG UI
	BUY					("qg/buy2.png", 128, 64, 2, 1),
	OKKO				("qg/ok_ko.png", 64, 32, 2, 1),


    /** UNITS HIGH DEF */
    //Gladiator Red
    GLADIATOR_HD_TEAM1      ("game/HD/Gladiator_128px_Red/gladiator01-red_128px_0000_0000#0000.png", 1024, 1024, 8, 8, -1, 64),
    GLADIATOR_HD_TEAM1_01   ("game/HD/Gladiator_128px_Red/gladiator01-red_128px_0000_0000#0001.png", 1024, 1024, 8, 8),
    GLADIATOR_HD_TEAM1_02   ("game/HD/Gladiator_128px_Red/gladiator01-red_128px_0000_0001#0000.png", 1024, 1024, 8, 8),
    GLADIATOR_HD_TEAM1_03   ("game/HD/Gladiator_128px_Red/gladiator01-red_128px_0000_0001#0001.png", 1024, 1024, 8, 8),
    GLADIATOR_HD_TEAM1_04   ("game/HD/Gladiator_128px_Red/gladiator01-red-shoot_256px_0000_0000#0000.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM1_05   ("game/HD/Gladiator_128px_Red/gladiator01-red-shoot_256px_0000_0000#0001.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM1_06   ("game/HD/Gladiator_128px_Red/gladiator01-red-shoot_256px_0000_0000#0002.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM1_07   ("game/HD/Gladiator_128px_Red/gladiator01-red-shoot_256px_0000_0000#0003.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM1_08   ("game/HD/Gladiator_128px_Red/gladiator01-red-shoot_256px_0000_0001#0000.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM1_09   ("game/HD/Gladiator_128px_Red/gladiator01-red-shoot_256px_0000_0001#0001.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM1_10   ("game/HD/Gladiator_128px_Red/gladiator01-red-shoot_256px_0000_0001#0002.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM1_11   ("game/HD/Gladiator_128px_Red/gladiator01-red-shoot_256px_0000_0001#0003.png", 1024, 1024, 4, 4),

    //Gladiator Blue
    GLADIATOR_HD_TEAM2      ("game/HD/Gladiator_128px_Blue/gladiator01-blue_128px_0000_0000#0000.png", 1024, 1024, 8, 8, -1, 64),
    GLADIATOR_HD_TEAM2_01   ("game/HD/Gladiator_128px_Blue/gladiator01-blue_128px_0000_0000#0001.png", 1024, 1024, 8, 8),
    GLADIATOR_HD_TEAM2_02   ("game/HD/Gladiator_128px_Blue/gladiator01-blue_128px_0000_0001#0000.png", 1024, 1024, 8, 8),
    GLADIATOR_HD_TEAM2_03   ("game/HD/Gladiator_128px_Blue/gladiator01-blue_128px_0000_0001#0001.png", 1024, 1024, 8, 8),
    GLADIATOR_HD_TEAM2_04   ("game/HD/Gladiator_128px_Blue/gladiator01-blue-shoot_256px_0000_0000#0000.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM2_05   ("game/HD/Gladiator_128px_Blue/gladiator01-blue-shoot_256px_0000_0000#0001.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM2_06   ("game/HD/Gladiator_128px_Blue/gladiator01-blue-shoot_256px_0000_0000#0002.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM2_07   ("game/HD/Gladiator_128px_Blue/gladiator01-blue-shoot_256px_0000_0000#0003.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM2_08   ("game/HD/Gladiator_128px_Blue/gladiator01-blue-shoot_256px_0000_0001#0000.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM2_09   ("game/HD/Gladiator_128px_Blue/gladiator01-blue-shoot_256px_0000_0001#0001.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM2_10   ("game/HD/Gladiator_128px_Blue/gladiator01-blue-shoot_256px_0000_0001#0002.png", 1024, 1024, 4, 4),
    GLADIATOR_HD_TEAM2_11   ("game/HD/Gladiator_128px_Blue/gladiator01-blue-shoot_256px_0000_0001#0003.png", 1024, 1024, 4, 4),

    //Marine Red
    MARINE_HD_TEAM1         ("game/HD/Marine_128px_Red/marine1-red_128px_0000_0000#0000.png", 1024, 1024, 8, 8, -1, 64),
    MARINE_HD_TEAM1_01      ("game/HD/Marine_128px_Red/marine1-red_128px_0000_0000#0001.png", 1024, 1024, 8, 8),
    MARINE_HD_TEAM1_02      ("game/HD/Marine_128px_Red/marine1-red_128px_0000_0001#0000.png", 1024, 1024, 8, 8),
    MARINE_HD_TEAM1_03      ("game/HD/Marine_128px_Red/marine1-red_128px_0000_0001#0001.png", 1024, 1024, 8, 8),
    MARINE_HD_TEAM1_04      ("game/HD/Marine_128px_Red/marine1-red-shoot_256px_0000_0000#0000.png", 1024, 1024, 4, 1),
    MARINE_HD_TEAM1_05      ("game/HD/Marine_128px_Red/marine1-red-shoot_256px_0000_0000#0001.png", 1024, 1024, 4, 1),
    MARINE_HD_TEAM1_06      ("game/HD/Marine_128px_Red/marine1-red-shoot_256px_0000_0000#0002.png", 1024, 1024, 4, 1),
    MARINE_HD_TEAM1_07      ("game/HD/Marine_128px_Red/marine1-red-shoot_256px_0000_0000#0003.png", 1024, 1024, 4, 1),

    //Marine Blue
    MARINE_HD_TEAM2         ("game/HD/Marine_128px_Blue/marine1-blue_128px_0000_0000#0000.png", 1024, 1024, 8, 8, -1, 64),
    MARINE_HD_TEAM2_01      ("game/HD/Marine_128px_Blue/marine1-blue_128px_0000_0000#0001.png", 1024, 1024, 8, 8),
    MARINE_HD_TEAM2_02      ("game/HD/Marine_128px_Blue/marine1-blue_128px_0000_0001#0000.png", 1024, 1024, 8, 8),
    MARINE_HD_TEAM2_03      ("game/HD/Marine_128px_Blue/marine1-blue_128px_0000_0001#0001.png", 1024, 1024, 8, 8),
    MARINE_HD_TEAM2_04      ("game/HD/Marine_128px_Blue/marine1-blue-shoot_256px_0000_0000#0000.png", 1024, 1024, 4, 1),
    MARINE_HD_TEAM2_05      ("game/HD/Marine_128px_Blue/marine1-blue-shoot_256px_0000_0000#0001.png", 1024, 1024, 4, 1),
    MARINE_HD_TEAM2_06      ("game/HD/Marine_128px_Blue/marine1-blue-shoot_256px_0000_0000#0002.png", 1024, 1024, 4, 1),
    MARINE_HD_TEAM2_07      ("game/HD/Marine_128px_Blue/marine1-blue-shoot_256px_0000_0000#0003.png", 1024, 1024, 4, 1),

    //Mesh Red
    MESH_HD_TEAM1           ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0000#0000.png", 1024, 1024, 4, 4, -1, 128),
    MESH_HD_TEAM1_01        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0000#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_02        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0000#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_03        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0000#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_04        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0001#0000.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_05        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0001#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_06        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0001#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_07        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0001#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_08        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0002#0000.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_09        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0002#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_11        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0002#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_12        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0002#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_13        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0003#0000.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_14        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0003#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_15        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0003#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_16        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0003#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_17        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0004#0000.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_18        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0004#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_19        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0004#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_20        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0004#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM1_21        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0005#0000.png", 1024, 1024, 4, 2),
    MESH_HD_TEAM1_22        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0005#0001.png", 1024, 1024, 4, 2),
    MESH_HD_TEAM1_23        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0005#0002.png", 1024, 1024, 4, 2),
    MESH_HD_TEAM1_24        ("game/HD/Mesh_256px_Red/Mech1-red_256px_0000_0005#0003.png", 1024, 1024, 4, 2),

    //Mesh Blue
    MESH_HD_TEAM2           ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0000#0000.png", 1024, 1024, 4, 4, -1, 128),
    MESH_HD_TEAM2_01        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0000#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_02        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0000#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_03        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0000#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_04        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0001#0000.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_05        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0001#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_06        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0001#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_07        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0001#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_08        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0002#0000.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_09        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0002#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_11        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0002#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_12        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0002#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_13        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0003#0000.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_14        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0003#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_15        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0003#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_16        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0003#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_17        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0004#0000.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_18        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0004#0001.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_19        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0004#0002.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_20        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0004#0003.png", 1024, 1024, 4, 4),
    MESH_HD_TEAM2_21        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0005#0000.png", 1024, 1024, 4, 2),
    MESH_HD_TEAM2_22        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0005#0001.png", 1024, 1024, 4, 2),
    MESH_HD_TEAM2_23        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0005#0002.png", 1024, 1024, 4, 2),
    MESH_HD_TEAM2_24        ("game/HD/Mesh_256px_Blue/Mech1-blue_256px_0000_0005#0003.png", 1024, 1024, 4, 2),


    /** TURRETS HIGH DEF */
    //Turret01 Red
    TURRET_01_HD_TEAM1      ("game/HD/Turret01_512px_Red/Turret-08-red_512px_0000_0000#0000.png", 1024, 512, 2, 1, -1, 128),
    TURRET_01_HD_TEAM1_01   ("game/HD/Turret01_512px_Red/Turret-08-red_512px_0000_0000#0001.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM1_02   ("game/HD/Turret01_512px_Red/Turret-08-red_512px_0000_0000#0002.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM1_03   ("game/HD/Turret01_512px_Red/Turret-08-red_512px_0000_0000#0003.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM1_04   ("game/HD/Turret01_512px_Red/Turret-08-red_512px_0000_0000#0004.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM1_05   ("game/HD/Turret01_512px_Red/Turret-08-red_512px_0000_0000#0005.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM1_06   ("game/HD/Turret01_512px_Red/Turret-08-red_512px_0000_0000#0006.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM1_07   ("game/HD/Turret01_512px_Red/Turret-08-red_512px_0000_0000#0007.png", 1024, 512, 2, 1),

    //Turret01 Blue
    TURRET_01_HD_TEAM2      ("game/HD/Turret01_512px_Blue/Turret-08-blue_512px_0000_0000#0000.png", 1024, 512, 2, 1, -1, 128),
    TURRET_01_HD_TEAM2_01   ("game/HD/Turret01_512px_Blue/Turret-08-blue_512px_0000_0000#0001.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM2_02   ("game/HD/Turret01_512px_Blue/Turret-08-blue_512px_0000_0000#0002.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM2_03   ("game/HD/Turret01_512px_Blue/Turret-08-blue_512px_0000_0000#0003.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM2_04   ("game/HD/Turret01_512px_Blue/Turret-08-blue_512px_0000_0000#0004.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM2_05   ("game/HD/Turret01_512px_Blue/Turret-08-blue_512px_0000_0000#0005.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM2_06   ("game/HD/Turret01_512px_Blue/Turret-08-blue_512px_0000_0000#0006.png", 1024, 512, 2, 1),
    TURRET_01_HD_TEAM2_07   ("game/HD/Turret01_512px_Blue/Turret-08-blue_512px_0000_0000#0007.png", 1024, 512, 2, 1),

    //Turret02 Red
    TURRET_02_HD_TEAM1      ("game/HD/Turret02_256px_Red/Fr-Launcher-03-red_256px_0000_0000#0000.png", 1024, 256, 4, 1, -1, 128),
    TURRET_02_HD_TEAM1_01   ("game/HD/Turret02_256px_Red/Fr-Launcher-03-red_256px_0000_0000#0001.png", 1024, 256, 4, 1),
    TURRET_02_HD_TEAM1_02   ("game/HD/Turret02_256px_Red/Fr-Launcher-03-red_256px_0000_0000#0002.png", 1024, 256, 4, 1),
    TURRET_02_HD_TEAM1_03   ("game/HD/Turret02_256px_Red/Fr-Launcher-03-red_256px_0000_0000#0003.png", 1024, 256, 4, 1),

    //Turret02 Blue
    TURRET_02_HD_TEAM2      ("game/HD/Turret02_256px_Blue/Fr-Launcher-03-blue_256px_0000_0000#0000.png", 1024, 256, 4, 1, -1, 128),
    TURRET_02_HD_TEAM2_01   ("game/HD/Turret02_256px_Blue/Fr-Launcher-03-blue_256px_0000_0000#0001.png", 1024, 256, 4, 1),
    TURRET_02_HD_TEAM2_02   ("game/HD/Turret02_256px_Blue/Fr-Launcher-03-blue_256px_0000_0000#0002.png", 1024, 256, 4, 1),
    TURRET_02_HD_TEAM2_03   ("game/HD/Turret02_256px_Blue/Fr-Launcher-03-blue_256px_0000_0000#0003.png", 1024, 256, 4, 1),

    
    //BARRICADE
    BARRICADES				("game/barricade_tiles.png", 512, 512, 4, 4, 128);

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

