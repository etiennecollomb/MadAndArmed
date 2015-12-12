package com.geekmecrazy.madandarmed.Entity.Sprite;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Tools.GraphicalTools;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;


public class SpriteSheet {


	public static enum SpriteSheetType{
		UNIQUE,
		FROM_DIR, /** Old version TODO: a virer a terme, il ne faut que FROM_ATLAS **/
		FROM_ATLAS
	}

	public static int MIN_TILED_SIZE = 64; //64 (taille minimal d'un tiled qui tient sur un seul sprite) = 1024 (sprite size) /16 (directions)

	/**
	 * la premiere texture a inserer,
	 * sur la base de laquelle toute les autres vont etre mises
	 */
	private AnimatedTextureType mAnimatedTextureTypeRoot;

	/**
	 * if Unique Sprite : une sprite and no rules, une seule sprite stockee dans sprites
	 * if Multi : SpriteSheet based on Multi Sprites
	 * TiledSprite[][] sprites = {{t,t2},{t3,t4},{t5,t6,t7,t8}};
	 * on supose que toutes les sprites definissent une meta sprite carree de [N x N] tiled
	 */
	private TextureRegion[][] mSprites;

	private int mNumberOfColumn;

	private int mNumberOfRow;

	private int mNumberOfTiled;

	/** list des textureAtlas si on cree le spriteSheet a partir de ca **/
	private boolean isFromTexturePack;


	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * baseName : racine commune du groupe de Sprite a associer ensemble
	 * usually baseName = tiledTextureType.XXXX.name()
	 */
	public SpriteSheet(final AnimatedTextureType pAnimatedTextureType) {

		long timeTEMP = System.currentTimeMillis();

		this.mAnimatedTextureTypeRoot = pAnimatedTextureType;
		this.isFromTexturePack = false;

		switch(pAnimatedTextureType.getSpriteSheetType()){
		case UNIQUE:
			this.generateUniqueSpriteSheet(this.mAnimatedTextureTypeRoot);
			break;
		case FROM_DIR:
			this.generateSpriteSheet(this.mAnimatedTextureTypeRoot);
			break;
		case FROM_ATLAS:
			this.generateSpriteSheetFromAtlas(this.mAnimatedTextureTypeRoot);
			this.isFromTexturePack = true;
			break;
		}

		//on supose que toutes les sprites definissent une meta sprite de NxN
		this.mNumberOfTiled = this.mNumberOfColumn * this.mNumberOfRow;
		if(pAnimatedTextureType.getNumberOfTiled() >= 0)
			this.mNumberOfTiled = pAnimatedTextureType.getNumberOfTiled(); //on impose un nombre de tiled

		System.out.println("SpriteSheet Loading :  " + pAnimatedTextureType.name() + " > "  + (System.currentTimeMillis() - timeTEMP)/1000f + " sec.\n---------------" );

	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getNumberOfTiled() {
		return mNumberOfTiled;
	}

	public AnimatedTextureType getAnimatedTextureTypeRoot() {
		return mAnimatedTextureTypeRoot;
	}

	public int getNumberOfColumn() {
		return mNumberOfColumn;
	}

	public int getNumberOfRow() {
		return mNumberOfRow;
	}

	public boolean isFromTexturePack() {
		return isFromTexturePack;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void generateSpriteSheetFromAtlas(AnimatedTextureType animatedTextureType){


		/** Le code ci dessous permet de calculer le temps de chargement pas groupe d asset (Unit type...), util pour regarder les stats **/
//		/** Get all files in dir **/
//		FileHandle dirHandle;
//		if (Gdx.app.getType() == ApplicationType.Android) {
//			dirHandle = Gdx.files.internal(animatedTextureType.getPath());
//		} else { // ApplicationType.Desktop ..
//			dirHandle = Gdx.files.internal("./bin/"+animatedTextureType.getPath());
//		}
//		
//		/** create all texture atlas **/
//		List<TextureAtlas> textureAtlasList = new ArrayList<TextureAtlas>();
//		for (FileHandle fileHandle: dirHandle.list()) {
//			String fileName = fileHandle.file().getName();
//			String extension = fileName.substring(fileName.lastIndexOf(".")+1);
//
//			if(extension.equals("txt")){
//				TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal(fileHandle.file().getPath()),Gdx.files.internal(fileHandle.file().getParent()));
//				textureAtlasList.add(textureAtlas);
//			}
//		}
		

		List<TextureAtlas> textureAtlasList = new ArrayList<TextureAtlas>();
		textureAtlasList = GlobalManager.assestsLoader.getTextureAtlasListFromDirName(animatedTextureType.getPath());
		
		/** get atlas datas **/
		mNumberOfColumn = 0;

		int numberOfWalkFrame = 0;
		int numberOfAimFrame = 0;
		int numberOfShootFrame = 0;
		
		boolean isWalkFrames = false;
		boolean isAimFrames = false;
		boolean isShootFrames = false;
		
		Array<Integer> walkColumns = new Array<Integer>();
		Array<Integer> walkRows = new Array<Integer>();
		Array<AtlasRegion> walkAtlasRegion = new Array<AtlasRegion>();
		
		Array<Integer> aimColumns = new Array<Integer>();
		Array<Integer> aimRows = new Array<Integer>();
		Array<AtlasRegion> aimAtlasRegion = new Array<AtlasRegion>();
		
		Array<Integer> shootColumns = new Array<Integer>();
		Array<Integer> shootRows = new Array<Integer>();
		Array<AtlasRegion> shootAtlasRegion = new Array<AtlasRegion>();

		int frameSkiped = 2; /** Durty but flexible, without regenerated all images : allow to get 1 of n image **/
		
		int atlasListSize = textureAtlasList.size();
		for(int i=0; i<atlasListSize; i++){
			Array<AtlasRegion> atlasRegions = textureAtlasList.get(i).getRegions();
			for(int j=0; j<atlasRegions.size; j++){

				/** file name example : XXXXX#00000#00000.png **/
				String fileName = atlasRegions.get(j).name;
				int pos = fileName.length();
				int column = Integer.parseInt(fileName.substring(pos-5, pos));
				int row = Integer.parseInt(fileName.substring(pos-10, pos-6)) / frameSkiped; /** "/ frameSkiped": durty but flexible fro text **/

				pos = fileName.indexOf("#");
				String actionName = fileName.substring(0, pos);

				if(column>mNumberOfColumn) mNumberOfColumn = column;
				
				if(actionName.equals("1Walk")){
					isWalkFrames = true;
					if(row>numberOfWalkFrame) numberOfWalkFrame = row;
					walkColumns.add(new Integer(column));
					walkRows.add(new Integer(row));
					walkAtlasRegion.add(atlasRegions.get(j));
				}else if(actionName.equals("2Aim")){
					isAimFrames = true;
					if(row>numberOfAimFrame) numberOfAimFrame = row;
					aimColumns.add(new Integer(column));
					aimRows.add(new Integer(row));
					aimAtlasRegion.add(atlasRegions.get(j));
				}else if(actionName.equals("3Shoot")){
					isShootFrames = true;
					if(row>numberOfShootFrame) numberOfShootFrame = row;
					shootColumns.add(new Integer(column));
					shootRows.add(new Integer(row));
					shootAtlasRegion.add(atlasRegions.get(j));
				}
			}
		}
		if(isWalkFrames) numberOfWalkFrame++;
		if(isAimFrames) numberOfAimFrame++;
		if(isShootFrames) numberOfShootFrame++;

		mNumberOfColumn++;
		mNumberOfRow = numberOfWalkFrame + numberOfAimFrame + numberOfShootFrame;

		System.out.println("Spritesheet Size : " +mNumberOfColumn+" "+mNumberOfRow+"");

		/** set SpriteSheet**/
		mSprites = new TextureRegion[mNumberOfColumn][mNumberOfRow];

		/** Walk **/
		int currentIndex=0;
		for(int i=0; i<walkAtlasRegion.size; i++){
			mSprites[walkColumns.get(i)][walkRows.get(i)+currentIndex] = walkAtlasRegion.get(i);
		}
		currentIndex = currentIndex + numberOfWalkFrame;

		/** Aim **/
		for(int i=0; i<aimAtlasRegion.size; i++){
			mSprites[aimColumns.get(i)][aimRows.get(i)+currentIndex] = aimAtlasRegion.get(i);
		}
		currentIndex = currentIndex + numberOfAimFrame;
		
		/** Shoot **/
		for(int i=0; i<shootAtlasRegion.size; i++){
			mSprites[shootColumns.get(i)][shootRows.get(i)+currentIndex] = shootAtlasRegion.get(i);
		}
		currentIndex = currentIndex + numberOfShootFrame;

	}

	

	/**
	 * on recupere tous les texture des spritesheet d un dir
	 * ancien format : example:  TiledSprite[][] sprites = {{t,t2},{t3,t4},{t5,t6,t7,t8}};
	 * il faut que les noms de sprites dans le dir soient mis dans l'ordre
	 * on suppose que les TiledSprite d'un meme sous groupe ont tous la meme taille
	 */
	@Deprecated
	private void generateSpriteSheet(AnimatedTextureType animatedTextureType){

		//animatedTextureType = animatedTextureType.GLADIATOR_HD_TEAM1;

		List<Texture> textures = new ArrayList<Texture>();
		List<Integer> numberOfSpriteSheetByLine = new ArrayList<Integer>();
		List<Integer> tiledSize = new ArrayList<Integer>();

		//Get all texture
		FileHandle dirHandle;
		
		dirHandle = Gdx.files.internal(GlobalManager.convertToDevicePath(animatedTextureType.getPath()));

		/** get all texture and number of spritesheet by line **/
		int currentRowFile = 0;
		numberOfSpriteSheetByLine.add(new Integer(0));
		for (FileHandle fileHandle: dirHandle.list()) {

			/** file name example : gladiator01-red-shoot_256px_0000_0000#0001.png **/
			String fileName = fileHandle.file().getName();
			int pos = fileName.lastIndexOf(".");
			int columnFile = Integer.parseInt(fileName.substring(pos-4, pos));
			int rowFile = Integer.parseInt(fileName.substring(pos-9, pos-5));

			/** Add Textures **/
			textures.add(new Texture(Gdx.files.internal(fileHandle.file().getPath())));

			/** if new spriteSheet line, save size and number of row **/
			if(currentRowFile != rowFile){
				currentRowFile = rowFile;
				numberOfSpriteSheetByLine.add(new Integer(0));
			}

			/** set number of spriteSheet by Line **/
			int index = numberOfSpriteSheetByLine.size()-1;
			numberOfSpriteSheetByLine.set(index , numberOfSpriteSheetByLine.get(index) +1 ); 

		}

		/** get number of row and tiled size for this line **/
		mNumberOfRow = 0;
		int size = numberOfSpriteSheetByLine.size();
		int indexTextures=0;
		for(int i=0; i<size; i++){

			int sizeOfTiled = numberOfSpriteSheetByLine.get(i)*textures.get(indexTextures).getWidth()/GraphicalTools.NB_ORIENTATION;
			tiledSize.add(new Integer(sizeOfTiled));

			mNumberOfRow = mNumberOfRow + textures.get(0).getHeight()/sizeOfTiled;

			indexTextures = indexTextures + numberOfSpriteSheetByLine.get(i);
		}


		this.mNumberOfColumn = GraphicalTools.NB_ORIENTATION;


		/** init arrays **/
		mSprites = new TextureRegion[mNumberOfColumn][mNumberOfRow];

		/** fill array **/
		int tmpX=0, tmpY=0;
		int currentLine = 0 ;
		int numberOfSpriteSheet = numberOfSpriteSheetByLine.get(currentLine);
		for (final Texture currentTexture: textures){

			TextureRegion[][] mFrames = TextureRegion.split(currentTexture, tiledSize.get(currentLine), tiledSize.get(currentLine));

			for(int i=0; i<mFrames.length; i++)
				for(int j=0; j<mFrames[i].length; j++){
					mSprites[tmpX+j][tmpY+i] = mFrames[i][j];
				}

			tmpX = tmpX + mFrames[0].length;


			/** On a finit avec toutes les sprites sheet d une meme ligne? **/
			numberOfSpriteSheet--;
			if(numberOfSpriteSheet <= 0){

				tmpX=0;
				tmpY = tmpY + mFrames.length;

				currentLine++;
				if(currentLine >= numberOfSpriteSheetByLine.size()) break;
				numberOfSpriteSheet = numberOfSpriteSheetByLine.get(currentLine);
			}
		}

	}


//	/** SpriteSheet unique 
//	 * tiled size equals (width/number of tiled) ....
//	 */
//	private void generateUniqueSpriteSheet(final AnimatedTextureType pAnimatedTextureTypeRoot){
//		Texture currentTexture = new Texture(Gdx.files.internal(pAnimatedTextureTypeRoot.getPath()));
//		this.generateUniqueSpriteSheet(pAnimatedTextureTypeRoot, currentTexture.getWidth()/pAnimatedTextureTypeRoot.getNumberOfColumn(), currentTexture.getHeight()/pAnimatedTextureTypeRoot.getNumberOfRow());
//	}

	/** SpriteSheet unique 
	 * Tiled size must be specified
	 */
	private void generateUniqueSpriteSheet(final AnimatedTextureType pAnimatedTextureTypeRoot){


		Texture currentTexture = new Texture(Gdx.files.internal(pAnimatedTextureTypeRoot.getPath()));
		TextureRegion[][] mFrames = TextureRegion.split(currentTexture, pAnimatedTextureTypeRoot.getTiledWidth(), pAnimatedTextureTypeRoot.getTiledHeight());
		
		this.mNumberOfColumn = currentTexture.getWidth()/pAnimatedTextureTypeRoot.getTiledWidth();
		this.mNumberOfRow = currentTexture.getHeight()/pAnimatedTextureTypeRoot.getTiledHeight();
		mSprites = new TextureRegion[mNumberOfColumn][mNumberOfRow];
		
		for(int i=0; i<mFrames.length; i++)
			for(int j=0; j<mFrames[i].length; j++){
				mSprites[j][i] = mFrames[i][j];
			}

	}
	

	public TextureRegion getFrame(final int pCol_x, final int pRow_y) {
		return this.mSprites[pCol_x][pRow_y];
	}

	//gauche-droite, haut-bas
	public TextureRegion getFrame(final int pNumber) {
		int col_x = pNumber%this.mNumberOfColumn;
		int row_y = pNumber/this.mNumberOfColumn;
		return this.getFrame(col_x, row_y);
	}

	public int getFrameWidth(final int pCol_x, final int pRow_y) {
		return this.mSprites[pCol_x][pRow_y].getRegionWidth();
	}

	public int getFrameHeight(final int pCol_x, final int pRow_y) {
		return this.mSprites[pCol_x][pRow_y].getRegionHeight();
	}

	public float getFrameOffsetX(final int pCol_x, final int pRow_y) {
		if(this.isFromTexturePack)
			return ((AtlasRegion)this.mSprites[pCol_x][pRow_y]).offsetX;
		else
			return 0;
	}

	public float getFrameOffsetY(final int pCol_x, final int pRow_y) {
		if(this.isFromTexturePack)
			return ((AtlasRegion)this.mSprites[pCol_x][pRow_y]).offsetY;
		else
			return 0;
	}

	public float getFrameOriginalWidth(final int pCol_x, final int pRow_y) {
		if(this.isFromTexturePack)
			return ((AtlasRegion)this.mSprites[pCol_x][pRow_y]).originalWidth;
		else
			return this.mSprites[pCol_x][pRow_y].getRegionWidth();
	}

	public float getFrameOriginalHeight(final int pCol_x, final int pRow_y) {
		if(this.isFromTexturePack)
			return ((AtlasRegion)this.mSprites[pCol_x][pRow_y]).originalHeight;
		else
			return this.mSprites[pCol_x][pRow_y].getRegionHeight();
	}

}


















