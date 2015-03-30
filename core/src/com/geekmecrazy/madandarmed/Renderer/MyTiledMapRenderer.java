package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Core.TextureBuilder;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TilesType;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MyTiledMapRenderer extends Shape {

    int[][] pattern_01 = new int[][]{
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
            { -1, -1,  0,  0,  0, -1, -1, -1, -1, -1 },
            { -1, -1,  0,  0,  0,  0, -1, -1, -1, -1 },
            { -1, -1,  0,  0,  0,  0,  0, -1, -1, -1 },
            { -1, -1, -1,  0,  0,  0,  0,  0, -1, -1 },
            { -1, -1, -1, -1,  0,  0,  0,  0, -1, -1 },
            { -1, -1, -1, -1, -1,  0,  0,  0, -1, -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };

    int[][] pattern_02 = new int[][]{
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
            { -1, -1, -1,  0,  0,  0,  0, -1, -1, -1 },
            { -1, -1, -1,  0,  0,  0,  0, -1, -1, -1 },
            { -1, -1, -1,  0,  0,  0,  0, -1, -1, -1 },
            { -1, -1, -1,  0,  0,  0,  0, -1, -1, -1 },
            { -1, -1, -1,  0,  0,  0,  0, -1, -1, -1 },
            { -1, -1, -1,  0,  0,  0,  0, -1, -1, -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };

    TextureBuilder textureBuilder;

    private int mTileWidth;
    private int mTileHeight;

    private int mTiledMapWidth;
    private int mTiledMapHeight;

    private TilesType mTilesType;

    private int mOrthoMapSize; //It is a square
    private int[][] orthoMap; //Map : Contains Type Tiles
    private int[][] orthoMapCopy; //Map : Contains Type Tiles
    private ArrayList<Vector2> usedCoordinates;
    private ArrayList<Sprite> backgroundSprite;
    private ArrayList<int[][]> patterns;


    // ===========================================================
	// Constructors
	// ===========================================================

	public MyTiledMapRenderer(){
        this.usedCoordinates = new ArrayList<Vector2>();
        this.backgroundSprite = new ArrayList<Sprite>();

        this.mTilesType = new TilesType();

        //Patterns
        this.patterns = new ArrayList<int[][]>();
        this.patterns.add(this.pattern_01);
        this.patterns.add(this.pattern_02);
    }

	// ===========================================================
	// Getter & Setter
	// ===========================================================

    public int getTileWidth() {
        return mTileWidth;
    }

    public void setTileWidth(final int pTileWidth) {
        this.mTileWidth = pTileWidth;
    }

    public int getTileHeight() {
        return mTileHeight;
    }

    public void setTileHeight(final int pTileHeight) {
        this.mTileHeight = pTileHeight;
    }

    public int getOrthoMapSize() {
        return mOrthoMapSize;
    }

    public void setOrthoMapSize(final int pOrthoMapSize) {
        this.mOrthoMapSize = pOrthoMapSize;
    }

    public int getTiledMapWidth() {
        return mTiledMapWidth;
    }

    public void setTiledMapWidth(int pTiledMapWidth) {
        this.mTiledMapWidth = pTiledMapWidth;
    }

    public int getTiledMapHeight() {
        return mTiledMapHeight;
    }

    public void setTiledMapHeight(int pTiledMapHeight) {
        this.mTiledMapHeight = pTiledMapHeight;
    }

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void reset() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final float pWidth, final float pHeight, final int pTileWidth, final int pTileHeight){

        super.init(pWidth/2f, pHeight/2f, pWidth, pHeight);
        this.setAlignment(Alignment.NONE);

        this.setTileWidth(pTileWidth);
        this.setTileHeight(pTileHeight);

        //Init
        this.calculateOrthoMapSize();
        this.computeTiledMapSize();
        this.generateUsedCoordinates(); //Square in wich we fit the screen

        this.generateOrthoMap(this.mTilesType.idStartGround00, this.mTilesType.idStartGround01);
        this.generateBackgroundSprites();
        this.attachBackgroundSprites();

    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void calculateOrthoMapSize(){

        int shapeInTiledMapWidth = (int)(this.getWidth()/this.getTileWidth());
        int shapeInTiledMapHeight = (int)(this.getHeight()/this.getTileHeight());

        if(shapeInTiledMapWidth > shapeInTiledMapHeight)
            this.setOrthoMapSize(2*shapeInTiledMapWidth);
        else
            this.setOrthoMapSize(2*shapeInTiledMapHeight);

        //TEST
//        this.setOrthoMapSize(this.getOrthoMapSize()-1); //a effacer & dans globalmanager (scroll camera)
        //FIN TEST

    }

    public void computeTiledMapSize(){
        //todo: ne marche que si square !
        this.setTiledMapWidth( (int)( this.getOrthoMapSize() * this.getTileWidth() ));
        this.setTiledMapHeight( (int)( this.getOrthoMapSize() * this.getTileHeight() ));
    }

    //The "square" of tiled visible on screen
    public void generateUsedCoordinates(){

        int size = this.getOrthoMapSize()/2;
        for(int i = 0; i<size; i++) {
            for (int j = -i-1; j <= i; j++) {

                Vector2 coord1 = new Vector2(i, size+j);
                this.usedCoordinates.add(coord1);
                Vector2 coord2 = new Vector2(2*size-i-1, size+j);
                this.usedCoordinates.add(coord2);

            }
        }
    }

    /** cut in smaller pieces
     * based on hardware capacities
     */
    public void generateBackgroundSprites(){

        textureBuilder = new TextureBuilder();

        //Create the full sprite
        textureBuilder.init((int)this.getWidth(), (int)this.getHeight()); //size

        //debug
//        textureBuilder.drawNewTexture(TileTypes.ARID_01.getTextureType(), 0, 0);

        this.addTileOnTexture(this.mTilesType.idStartGround00, this.mTilesType.idStartGround01);
//        this.addDecoration();
        Pixmap megaPixmap = textureBuilder.getFinalPixmap();

        //max sub texture size allowed (depend about device capacity and <= of scene size
        int maxTextureWidth = (GlobalManager.MAX_TEXTURE_WIDTH > this.getWidth())? (int)this.getWidth() : GlobalManager.MAX_TEXTURE_WIDTH;
        int maxTextureHeight = (GlobalManager.MAX_TEXTURE_HEIGHT > this.getHeight())? (int)this.getHeight() : GlobalManager.MAX_TEXTURE_HEIGHT;

        //Split texture in smaller one
        int numberColumns = megaPixmap.getWidth() / maxTextureWidth;
        int numberRows = megaPixmap.getHeight() / maxTextureHeight;

        int posX = 0;
        int posY = megaPixmap.getHeight() - maxTextureHeight;

        for(int i=0; i < numberColumns; i++) {
            for (int j=0; j < numberRows; j++) {

                //Create Texture
                //TODO : calculer la taille restante en puissance de 2 (limite espace memoire)
                textureBuilder.init(maxTextureWidth, maxTextureHeight);
                textureBuilder.getFinalPixmap().drawPixmap(megaPixmap, -posX, -posY);
                Texture spriteTexture = textureBuilder.createTexture();

                //Create Sprite
                Sprite newSprite = new Sprite();
//                newSprite.init(i*MyTiledMapRenderer.MAX_TEXTURE_WIDTH +i*4, j*MyTiledMapRenderer.MAX_TEXTURE_HEIGHT +j*4, spriteTexture.getWidth(), spriteTexture.getHeight()); //Debug
                newSprite.init(i*maxTextureWidth, j*maxTextureHeight, spriteTexture.getWidth(), spriteTexture.getHeight());
                newSprite.setTextureRegion(new TextureRegion(spriteTexture));
                newSprite.setAlignment(Alignment.NONE);

                backgroundSprite.add(newSprite);

                posY = posY - maxTextureHeight;
            }
            posX = posX + maxTextureWidth;
            posY = megaPixmap.getHeight() - maxTextureHeight;
        }

        megaPixmap.dispose();

        //debug
//        Texture spriteTexture = textureBuilder.createTexture();
//        Sprite newSprite = new Sprite();
//        newSprite.init(0, 0, spriteTexture.getWidth(), spriteTexture.getHeight());
//        newSprite.setTextureRegion(new TextureRegion(spriteTexture));
//        newSprite.setAlignment(Alignment.NONE);
//        backgroundSprite.add(newSprite);
    }

    public void attachBackgroundSprites(){

        int size = backgroundSprite.size();
        for(int i = 0; i< size; i++) {
            Sprite sprite = backgroundSprite.get(i);
            this.attachChild(sprite);
        }
    }

    /** from UsedTiled to CreatedTexture */
    private void addTileOnTexture(int pIdStartgroundBase, int pIdStartgroundSmoothed){

        //1- Stock all pixmap from tileType in hastable
        HashMap<Integer, Pixmap> pixmaps = new HashMap<Integer, Pixmap>();

        for (Map.Entry<Integer,TextureType> entry : this.mTilesType.tilesType.entrySet()) {
            final Integer key = entry.getKey();
            final TextureType textureType = entry.getValue();

            Texture texture = new Texture(Gdx.files.internal( textureType.getPath()) );
            Pixmap pixmap = textureBuilder.getPixmapFromTexture(texture);

            pixmaps.put(key, pixmap);
        }

        //2- create texture
        int size = this.usedCoordinates.size();
        for(int i=0; i<size; i++) {
            Vector2 coord = this.usedCoordinates.get(i);

            /** Ortho to Iso !
             x = (j * tile_width / 2) + (i * tile_width / 2)
             y = (i * tile_height / 2) - (j * tile_height / 2)
             */
            int posX = (int)( (coord.y * this.getTileWidth() /2f) + (coord.x * this.getTileWidth() / 2f) );
            int posY = (int)( (coord.x * this.getTileHeight() /2f) - (coord.y * this.getTileHeight() / 2f) );

            //Center the tiled Map on itself
            posX = posX - (int)(this.getTiledMapWidth()/2f);
            posY = posY - (int)(this.getTileHeight()/2f);

            //Center on the texture
            posX = posX + (int)(textureBuilder.getTextureWidth()/2f);
            posY = posY + (int)(textureBuilder.getTextureHeight()/2f);

            int tileId = this.orthoMap[(int)coord.x][(int)coord.y];
            if(tileId != pIdStartgroundBase && tileId != pIdStartgroundSmoothed) {
                //if limits, we draw underground before
                textureBuilder.getFinalPixmap().drawPixmap(pixmaps.get(pIdStartgroundBase), posX, posY) ;
            }
            textureBuilder.getFinalPixmap().drawPixmap(pixmaps.get(tileId), posX, posY) ;
        }

        //3- dispose all pixmaps
        for (Map.Entry<Integer,Pixmap> entry : pixmaps.entrySet()) {
            final Pixmap pixmap = entry.getValue();
            pixmap.dispose();
        }
    }


//    public void addDecoration(){
//
//        //1- Stock all pixmap from tileType in hastable
//        HashMap<Integer, Pixmap> pixmaps = new HashMap<Integer, Pixmap>();
//
//        for (Map.Entry<Integer,TextureType> entry : tilesType.entrySet()) {
//            final Integer key = entry.getKey();
//            final TextureType textureType = entry.getValue();
//
//            Texture texture = new Texture(Gdx.files.internal( textureType.getPath()) );
//            Pixmap pixmap = textureBuilder.getPixmapFromTexture(texture);
//
//            pixmaps.put(key, pixmap);
//        }
//    }


    // -1 = Empty Cell
    // full cell for each of type of ground
    public void generateOrthoMap(int pIdStartgroundBase, int pIdStartgroundSmoothed){

        int size = this.getOrthoMapSize();
        this.orthoMap = new int[size][size];

        //Init
        for(int i = 0; i<size; i++)
            for (int j=0; j<size; j++)
                this.orthoMap[i][j]=-1;

        //Create World base (full of pIdStartGround1)
        for(int i = 0; i<size; i++)
            for (int j=0; j<size; j++)
                this.orthoMap[i][j]=pIdStartgroundBase;


        //put random pattern on orthoMap
        int numberOfPatterns = 1000;
        for(int i=0; i<numberOfPatterns; i++){
            Random ran = new Random();
            int patternNumber = ran.nextInt( patterns.size() );
            int[][] pattern = this.patterns.get(patternNumber);
            this.putPatternOnOrthoMap(pattern, ran.nextInt(this.getOrthoMapSize()), ran.nextInt(this.getOrthoMapSize()), pIdStartgroundSmoothed);
        }

        this.postProcessOrthoMap(pIdStartgroundBase, pIdStartgroundSmoothed);

        //Debug
//        this.orthoMap[60 +1][60]=1;
//        this.orthoMap[60][60]=1;
//        this.orthoMap[60][60 +1]=1;
//        this.orthoMap[60][60 +2]=1;
    }


    /** (posX, posY) in the OrthoMap World
     * pTileType : TileType to put on orthomap
     **/
    private void putPatternOnOrthoMap(int[][] pattern, int posX, int posY, int idStartGround){

        for(int i=0; i<pattern.length ; i++)
            for(int j=0; j<pattern[0].length ; j++){

                int x = posX+i;
                int y = posY+j;
                //-1 = Empty
                if(pattern[i][j] >= 0)
                    if(x>=0 && x<this.getOrthoMapSize() && y>=0 && y<this.getOrthoMapSize())
                        this.orthoMap[x][y] = idStartGround + pattern[i][j];
            }

    }

    /**
     * pIdStartgroundBase : l'id du ground par rapport auquel on lisse
     * pIdStartgroundSmoothed : l'id du ground qu on doit lisser
     */
    private void postProcessOrthoMap(int pIdStartgroundBase, int pIdStartgroundSmoothed){

        int size;

        boolean isTile1BaseType;
        boolean isTile2BaseType;
        boolean isTile3BaseType;
        boolean isTile4BaseType;
        boolean isTile5BaseType;
        boolean isTile6BaseType;
        boolean isTile7BaseType;
        boolean isTile8BaseType;

        boolean isDiag1TileBaseType;
        boolean isDiag2TileBaseType;
        boolean isDiag3TileBaseType;
        boolean isDiag4TileBaseType;

        /** Copy OrthoMap */
        size = this.getOrthoMapSize();
        this.orthoMapCopy = new int[size][size];
        for(int i=0; i<size; i++)
            for (int j=0; j<size; j++)
                this.orthoMapCopy[i][j] = this.orthoMap[i][j];


        // 1 - PRE-TREATMENT
        /** Check if invalid tiled state
        * (impossible to resolve for smoothing)
        * it is done case by case ....
        */
        size = this.getOrthoMapSize();
        for(int i=1; i<size-1; i++)
            for (int j=1; j<size-1; j++){

                if(this.orthoMapCopy[i][j] == pIdStartgroundSmoothed) {

                    isTile1BaseType = (this.orthoMapCopy[i - 1][j + 1] == pIdStartgroundBase) ? true : false;
                    isTile2BaseType = (this.orthoMapCopy[i][j + 1] == pIdStartgroundBase) ? true : false;
                    isTile3BaseType = (this.orthoMapCopy[i + 1][j + 1] == pIdStartgroundBase) ? true : false;
                    isTile4BaseType = (this.orthoMapCopy[i + 1][j] == pIdStartgroundBase) ? true : false;
                    isTile5BaseType = (this.orthoMapCopy[i + 1][j - 1] == pIdStartgroundBase) ? true : false;
                    isTile6BaseType = (this.orthoMapCopy[i][j - 1] == pIdStartgroundBase) ? true : false;
                    isTile7BaseType = (this.orthoMapCopy[i - 1][j - 1] == pIdStartgroundBase) ? true : false;
                    isTile8BaseType = (this.orthoMapCopy[i - 1][j] == pIdStartgroundBase) ? true : false;

                    //Cases
                    if(isTile1BaseType && !isTile2BaseType && !isTile3BaseType && !isTile4BaseType && isTile5BaseType && !isTile6BaseType && !isTile7BaseType && !isTile8BaseType)
                        this.orthoMap[i][j] = pIdStartgroundBase;
                    else if(!isTile1BaseType && !isTile2BaseType && isTile3BaseType && !isTile4BaseType && !isTile5BaseType && !isTile6BaseType && isTile7BaseType && !isTile8BaseType)
                        this.orthoMap[i][j] = pIdStartgroundBase;

                }
            }


        /** 2 - SMOOTHING */
        // TODO: revoir le traitement des bords (aggrandir orthmap de +1 ? ou c est ok comme ca?)
        size = this.getOrthoMapSize();
        for(int i=1; i<size-1; i++)
            for (int j=1; j<size-1; j++){

                if(this.orthoMap[i][j] == pIdStartgroundSmoothed){

                    isTile1BaseType = ( this.orthoMap[i][j+1] == pIdStartgroundBase)? true : false;
                    isTile2BaseType = ( this.orthoMap[i+1][j] == pIdStartgroundBase)? true : false;
                    isTile3BaseType = ( this.orthoMap[i][j-1] == pIdStartgroundBase)? true : false;
                    isTile4BaseType = ( this.orthoMap[i-1][j] == pIdStartgroundBase)? true : false;
                    isDiag1TileBaseType = ( this.orthoMap[i-1][j+1] == pIdStartgroundBase)? true : false;
                    isDiag2TileBaseType = ( this.orthoMap[i+1][j+1] == pIdStartgroundBase)? true : false;
                    isDiag3TileBaseType = ( this.orthoMap[i+1][j-1] == pIdStartgroundBase)? true : false;
                    isDiag4TileBaseType = ( this.orthoMap[i-1][j-1] == pIdStartgroundBase)? true : false;

                    //1 QUART
                    if(isTile1BaseType && !isTile2BaseType && !isTile3BaseType && isTile4BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_UP;
                    if(isTile1BaseType && isTile2BaseType && !isTile3BaseType && !isTile4BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_RIGHT;
                    if(!isTile1BaseType && isTile2BaseType && isTile3BaseType && !isTile4BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_DOWN;
                    if(!isTile1BaseType && !isTile2BaseType && isTile3BaseType && isTile4BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_LEFT;

                    //3 QUART
                    if(isDiag1TileBaseType && !isTile4BaseType && !isTile1BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_3QUART_UP;
                    if(isDiag2TileBaseType && !isTile1BaseType && !isTile2BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_3QUART_RIGHT;
                    if(isDiag3TileBaseType && !isTile2BaseType && !isTile3BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_3QUART_DOWN;
                    if(isDiag4TileBaseType && !isTile3BaseType && !isTile4BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_3QUART_LEFT;

                    //DIAG
                    if(!isTile1BaseType && isTile2BaseType && !isTile3BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_DIAG_RIGHT_DOWN;
                    if(!isTile2BaseType && isTile3BaseType && !isTile4BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_DIAG_LEFT_DOWN;
                    if(!isTile3BaseType && isTile4BaseType && !isTile1BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_DIAG_LEFT_UP;
                    if(!isTile4BaseType && isTile1BaseType && !isTile2BaseType) this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_DIAG_RIGHT_UP;

                }

            }


        // 3 - POST-TREATMENT
        /** Check if invalid tiled state
         * (impossible to resolve for smoothing)
         * it is done case by case ....
         */
        size = this.getOrthoMapSize();
        for(int i=1; i<size-1; i++)
            for (int j=1; j<size-1; j++){

                if(this.orthoMap[i][j] != pIdStartgroundBase) {

                    isTile1BaseType = (this.orthoMap[i - 1][j + 1] == pIdStartgroundBase) ? true : false;
                    isTile2BaseType = (this.orthoMap[i][j + 1] == pIdStartgroundBase) ? true : false;
                    isTile3BaseType = (this.orthoMap[i + 1][j + 1] == pIdStartgroundBase) ? true : false;
                    isTile4BaseType = (this.orthoMap[i + 1][j] == pIdStartgroundBase) ? true : false;
                    isTile5BaseType = (this.orthoMap[i + 1][j - 1] == pIdStartgroundBase) ? true : false;
                    isTile6BaseType = (this.orthoMap[i][j - 1] == pIdStartgroundBase) ? true : false;
                    isTile7BaseType = (this.orthoMap[i - 1][j - 1] == pIdStartgroundBase) ? true : false;
                    isTile8BaseType = (this.orthoMap[i - 1][j] == pIdStartgroundBase) ? true : false;

                    //Cases
                    if(!isTile1BaseType && isTile2BaseType && !isTile6BaseType && isTile7BaseType && !isTile8BaseType) {
                        this.orthoMap[i-1][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_DOWN;
                        this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_UP;
                    }
                    else if(isTile4BaseType && !isTile5BaseType && !isTile6BaseType && isTile7BaseType && !isTile8BaseType) {
                        this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_DOWN;
                        this.orthoMap[i][j-1] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_UP;
                    }
                    else if(isTile1BaseType && !isTile2BaseType && isTile6BaseType && !isTile7BaseType && !isTile8BaseType){
                        this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_LEFT;
                        this.orthoMap[i-1][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_RIGHT;
                    }
                    else if(!isTile4BaseType && isTile5BaseType && !isTile6BaseType && !isTile7BaseType && isTile8BaseType){
                        this.orthoMap[i][j-1] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_RIGHT;
                        this.orthoMap[i][j] = pIdStartgroundSmoothed + TilesType.ID_TILE_1QUART_LEFT;
                    }

                }
            }

    }
}
