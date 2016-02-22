package com.geekmecrazy.madandarmed.Game.Element.Property;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Scene.DecorationManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;

//TODO AMA a revoir
//Node 32px
//Ecran 15/25
//Map 15/40
public class GameMap {
	
	//Map
	private static Sprite ground;
	private static Sprite bord_up;
	private static Sprite bord_down;
	public static int bord_up_height = 100;
	public static int bord_down_height = 100;
	
	// disable object's instanciation (private constructor)
	private GameMap(){} 

	public static void initMap(Scene scene) {

        DecorationManager decorationManager = new DecorationManager();

        decorationManager.setDecoration();
        decorationManager.attachDecoration( scene );

        
	}
	
}
