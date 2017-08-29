package com.geekmecrazy.madandarmed.CoreConfig;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

/**
 * Created by ECollomb on 03/03/2015.
 */
public class TilesType {

	public static int MAX_TILES_PER_GROUND = 1000;

	public static int idStartGround00 = MAX_TILES_PER_GROUND *0;
	public static int idStartGround01 = MAX_TILES_PER_GROUND *1;

	public static int ID_TILE_FULL = 0;
	public static int ID_TILE_1QUART_UP = 1;
	public static int ID_TILE_1QUART_RIGHT = 2;
	public static int ID_TILE_1QUART_DOWN = 3;
	public static int ID_TILE_1QUART_LEFT = 4;
	public static int ID_TILE_3QUART_UP = 5;
	public static int ID_TILE_3QUART_RIGHT = 6;
	public static int ID_TILE_3QUART_DOWN = 7;
	public static int ID_TILE_3QUART_LEFT = 8;
	public static int ID_TILE_DIAG_LEFT_UP = 9;
	public static int ID_TILE_DIAG_RIGHT_UP = 10;
	public static int ID_TILE_DIAG_RIGHT_DOWN = 11;
	public static int ID_TILE_DIAG_LEFT_DOWN = 12;

	//contain tiles of same type but with different drawing in it
	//(we choose for the map a random tile among the list for a specific tile type)
	private ArrayList<TextureType> ground00_tile_full = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_full = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_1quart_up = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_1quart_right = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_1quart_down = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_1quart_left = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_3quart_up = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_3quart_right = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_3quart_down = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_3quart_left = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_diag_left_up = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_diag_right_up = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_diag_right_down = new ArrayList<TextureType>();
	private ArrayList<TextureType> ground01_tile_diag_left_down = new ArrayList<TextureType>();
	

	public static HashMap<Integer, ArrayList<TextureType>> tilesType = new HashMap<Integer, ArrayList<TextureType>>();;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TilesType(){


		/** Testing purpose **/
		//		/** Ground 00 */
		//		tilesType.put(idStartGround00 + ID_TILE_FULL, TextureType.TILE_FULL_BROWN);
		//		
		//		/** Ground 01 superpose sur Ground 00 */
		//		tilesType.put(idStartGround01 + ID_TILE_FULL, TextureType.TILE_FULL);
		//		tilesType.put(idStartGround01 + ID_TILE_1QUART_UP, TextureType.TILE_1QUART_UP);
		//		tilesType.put(idStartGround01 + ID_TILE_1QUART_RIGHT, TextureType.TILE_1QUART_RIGHT);
		//		tilesType.put(idStartGround01 + ID_TILE_1QUART_DOWN, TextureType.TILE_1QUART_DOWN);
		//		tilesType.put(idStartGround01 + ID_TILE_1QUART_LEFT, TextureType.TILE_1QUART_LEFT);
		//		tilesType.put(idStartGround01 + ID_TILE_3QUART_UP, TextureType.TILE_3QUART_UP);
		//		tilesType.put(idStartGround01 + ID_TILE_3QUART_RIGHT, TextureType.TILE_3QUART_RIGHT);
		//		tilesType.put(idStartGround01 + ID_TILE_3QUART_DOWN, TextureType.TILE_3QUART_DOWN);
		//		tilesType.put(idStartGround01 + ID_TILE_3QUART_LEFT, TextureType.TILE_3QUART_LEFT);
		//		tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_UP, TextureType.TILE_DIAG_LEFT_UP);
		//		tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_UP, TextureType.TILE_DIAG_RIGHT_UP);
		//		tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_DOWN, TextureType.TILE_DIAG_RIGHT_DOWN);
		//		tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_DOWN, TextureType.TILE_DIAG_LEFT_DOWN);


		//				/** Ground 00 */
		//				tilesType.put(idStartGround00 + ID_TILE_FULL, TextureType.TILE_GROUND01_FULL_BROWN);
		//		
		//				/** Ground 01 superpose sur Ground 00 */
		//				tilesType.put(idStartGround01 + ID_TILE_FULL, TextureType.TILE_GROUND01_FULL);
		//				tilesType.put(idStartGround01 + ID_TILE_1QUART_UP, TextureType.TILE_GROUND01_1QUART_UP);
		//				tilesType.put(idStartGround01 + ID_TILE_1QUART_RIGHT, TextureType.TILE_GROUND01_1QUART_RIGHT);
		//				tilesType.put(idStartGround01 + ID_TILE_1QUART_DOWN, TextureType.TILE_GROUND01_1QUART_DOWN);
		//				tilesType.put(idStartGround01 + ID_TILE_1QUART_LEFT, TextureType.TILE_GROUND01_1QUART_LEFT);
		//				tilesType.put(idStartGround01 + ID_TILE_3QUART_UP, TextureType.TILE_GROUND01_3QUART_UP);
		//				tilesType.put(idStartGround01 + ID_TILE_3QUART_RIGHT, TextureType.TILE_GROUND01_3QUART_RIGHT);
		//				tilesType.put(idStartGround01 + ID_TILE_3QUART_DOWN, TextureType.TILE_GROUND01_3QUART_DOWN);
		//				tilesType.put(idStartGround01 + ID_TILE_3QUART_LEFT, TextureType.TILE_GROUND01_3QUART_LEFT);
		//				tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_UP, TextureType.TILE_GROUND01_DIAG_LEFT_UP);
		//				tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_UP, TextureType.TILE_GROUND01_DIAG_RIGHT_UP);
		//				tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_DOWN, TextureType.TILE_GROUND01_DIAG_RIGHT_DOWN);
		//				tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_DOWN, TextureType.TILE_GROUND01_DIAG_LEFT_DOWN);


		
		//each tilestype contains a list of tiles of types of the same type
		//that will be choose randomly to avoid pattern repetition.
		/** Ground 00 */
		//////////
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_1);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_2);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_3);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_4);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_5);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_6);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_7);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_8);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_9);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_10);
		ground00_tile_full.add(TextureType.TILE_GROUND00_FULL_11);
		tilesType.put(idStartGround00 + ID_TILE_FULL, ground00_tile_full);

		/** Ground 01 superpose sur Ground 00 */
		//////////
		ground01_tile_full.add(TextureType.TILE_FULL);
		tilesType.put(idStartGround01 + ID_TILE_FULL, ground01_tile_full);
		//////////
		ground01_tile_1quart_up.add(TextureType.TILE_1QUART_UP);
		tilesType.put(idStartGround01 + ID_TILE_1QUART_UP, ground01_tile_1quart_up);
		//////////
		ground01_tile_1quart_right.add(TextureType.TILE_1QUART_RIGHT);
		tilesType.put(idStartGround01 + ID_TILE_1QUART_RIGHT, ground01_tile_1quart_right);
		//////////
		ground01_tile_1quart_down.add(TextureType.TILE_1QUART_DOWN);
		tilesType.put(idStartGround01 + ID_TILE_1QUART_DOWN, ground01_tile_1quart_down);
		//////////
		ground01_tile_1quart_left.add(TextureType.TILE_1QUART_LEFT);
		tilesType.put(idStartGround01 + ID_TILE_1QUART_LEFT, ground01_tile_1quart_left);
		//////////
		ground01_tile_3quart_up.add(TextureType.TILE_3QUART_UP);
		tilesType.put(idStartGround01 + ID_TILE_3QUART_UP, ground01_tile_3quart_up);
		//////////
		ground01_tile_3quart_right.add(TextureType.TILE_3QUART_RIGHT);
		tilesType.put(idStartGround01 + ID_TILE_3QUART_RIGHT, ground01_tile_3quart_right);
		//////////
		ground01_tile_3quart_down.add(TextureType.TILE_3QUART_DOWN);
		tilesType.put(idStartGround01 + ID_TILE_3QUART_DOWN, ground01_tile_3quart_down);
		//////////
		ground01_tile_3quart_left.add(TextureType.TILE_3QUART_LEFT);
		tilesType.put(idStartGround01 + ID_TILE_3QUART_LEFT, ground01_tile_3quart_left);
		//////////
		ground01_tile_diag_left_up.add(TextureType.TILE_DIAG_LEFT_UP);
		tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_UP, ground01_tile_diag_left_up);
		//////////
		ground01_tile_diag_right_up.add(TextureType.TILE_DIAG_RIGHT_UP);
		tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_UP, ground01_tile_diag_right_up);
		//////////
		ground01_tile_diag_right_down.add(TextureType.TILE_DIAG_RIGHT_DOWN);
		tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_DOWN, ground01_tile_diag_right_down);
		//////////
		ground01_tile_diag_left_down.add(TextureType.TILE_DIAG_LEFT_DOWN);
		tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_DOWN, ground01_tile_diag_left_down);



	}

}
