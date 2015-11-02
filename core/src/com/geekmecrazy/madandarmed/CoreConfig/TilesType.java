package com.geekmecrazy.madandarmed.CoreConfig;

import java.util.HashMap;

import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

/**
 * Created by ECollomb on 03/03/2015.
 */
public class TilesType {

    public static int MAX_TILES_PER_GROUND = 100;

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

    public static HashMap<Integer, TextureType> tilesType;

    // ===========================================================
    // Constructors
    // ===========================================================

    public TilesType(){

        //Tiles
        tilesType = new HashMap<Integer, TextureType>();
        /** Testing purpose **/
        //GROUND 00
//        tilesType.put(idStartGround00 + ID_TILE_FULL, TextureType.TILE_FULL);
//        tilesType.put(idStartGround00 + ID_TILE_1QUART_UP, TextureType.TILE_1QUART_UP);
//        tilesType.put(idStartGround00 + ID_TILE_1QUART_RIGHT, TextureType.TILE_1QUART_RIGHT);
//        tilesType.put(idStartGround00 + ID_TILE_1QUART_DOWN, TextureType.TILE_1QUART_DOWN);
//        tilesType.put(idStartGround00 + ID_TILE_1QUART_LEFT, TextureType.TILE_1QUART_LEFT);
//        tilesType.put(idStartGround00 + ID_TILE_3QUART_UP, TextureType.TILE_3QUART_UP);
//        tilesType.put(idStartGround00 + ID_TILE_3QUART_RIGHT, TextureType.TILE_3QUART_RIGHT);
//        tilesType.put(idStartGround00 + ID_TILE_3QUART_DOWN, TextureType.TILE_3QUART_DOWN);
//        tilesType.put(idStartGround00 + ID_TILE_3QUART_LEFT, TextureType.TILE_3QUART_LEFT);
//        tilesType.put(idStartGround00 + ID_TILE_DIAG_LEFT_UP, TextureType.TILE_DIAG_LEFT_UP);
//        tilesType.put(idStartGround00 + ID_TILE_DIAG_RIGHT_UP, TextureType.TILE_DIAG_RIGHT_UP);
//        tilesType.put(idStartGround00 + ID_TILE_DIAG_RIGHT_DOWN, TextureType.TILE_DIAG_RIGHT_DOWN);
//        tilesType.put(idStartGround00 + ID_TILE_DIAG_LEFT_DOWN, TextureType.TILE_DIAG_LEFT_DOWN);
//        //GROUND 01
//        tilesType.put(idStartGround01 + ID_TILE_FULL, TextureType.TILE_FULL_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_1QUART_UP, TextureType.TILE_1QUART_UP_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_1QUART_RIGHT, TextureType.TILE_1QUART_RIGHT_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_1QUART_DOWN, TextureType.TILE_1QUART_DOWN_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_1QUART_LEFT, TextureType.TILE_1QUART_LEFT_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_3QUART_UP, TextureType.TILE_3QUART_UP_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_3QUART_RIGHT, TextureType.TILE_3QUART_RIGHT_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_3QUART_DOWN, TextureType.TILE_3QUART_DOWN_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_3QUART_LEFT, TextureType.TILE_3QUART_LEFT_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_UP, TextureType.TILE_DIAG_LEFT_UP_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_UP, TextureType.TILE_DIAG_RIGHT_UP_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_DOWN, TextureType.TILE_DIAG_RIGHT_DOWN_BROWN);
//        tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_DOWN, TextureType.TILE_DIAG_LEFT_DOWN_BROWN);
        
        SpriteSheet sp = PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_001_128PX);
        
        //GROUND 00
        tilesType.put(idStartGround00 + ID_TILE_FULL, sp.getFrame(0, 0).getTexture());
        tilesType.put(idStartGround00 + ID_TILE_1QUART_UP, TextureType.TILE_1QUART_UP);
        tilesType.put(idStartGround00 + ID_TILE_1QUART_RIGHT, TextureType.TILE_1QUART_RIGHT);
        tilesType.put(idStartGround00 + ID_TILE_1QUART_DOWN, TextureType.TILE_1QUART_DOWN);
        tilesType.put(idStartGround00 + ID_TILE_1QUART_LEFT, TextureType.TILE_1QUART_LEFT);
        tilesType.put(idStartGround00 + ID_TILE_3QUART_UP, TextureType.TILE_3QUART_UP);
        tilesType.put(idStartGround00 + ID_TILE_3QUART_RIGHT, TextureType.TILE_3QUART_RIGHT);
        tilesType.put(idStartGround00 + ID_TILE_3QUART_DOWN, TextureType.TILE_3QUART_DOWN);
        tilesType.put(idStartGround00 + ID_TILE_3QUART_LEFT, TextureType.TILE_3QUART_LEFT);
        tilesType.put(idStartGround00 + ID_TILE_DIAG_LEFT_UP, TextureType.TILE_DIAG_LEFT_UP);
        tilesType.put(idStartGround00 + ID_TILE_DIAG_RIGHT_UP, TextureType.TILE_DIAG_RIGHT_UP);
        tilesType.put(idStartGround00 + ID_TILE_DIAG_RIGHT_DOWN, TextureType.TILE_DIAG_RIGHT_DOWN);
        tilesType.put(idStartGround00 + ID_TILE_DIAG_LEFT_DOWN, TextureType.TILE_DIAG_LEFT_DOWN);
        //GROUND 01
        tilesType.put(idStartGround01 + ID_TILE_FULL, TextureType.TILE_FULL_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_1QUART_UP, TextureType.TILE_1QUART_UP_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_1QUART_RIGHT, TextureType.TILE_1QUART_RIGHT_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_1QUART_DOWN, TextureType.TILE_1QUART_DOWN_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_1QUART_LEFT, TextureType.TILE_1QUART_LEFT_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_3QUART_UP, TextureType.TILE_3QUART_UP_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_3QUART_RIGHT, TextureType.TILE_3QUART_RIGHT_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_3QUART_DOWN, TextureType.TILE_3QUART_DOWN_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_3QUART_LEFT, TextureType.TILE_3QUART_LEFT_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_UP, TextureType.TILE_DIAG_LEFT_UP_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_UP, TextureType.TILE_DIAG_RIGHT_UP_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_DIAG_RIGHT_DOWN, TextureType.TILE_DIAG_RIGHT_DOWN_BROWN);
        tilesType.put(idStartGround01 + ID_TILE_DIAG_LEFT_DOWN, TextureType.TILE_DIAG_LEFT_DOWN_BROWN);
    }

}
