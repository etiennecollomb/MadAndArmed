package com.geekmecrazy.madandarmed.Entity.Sprite;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Tools.GraphicalTools;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class SpriteSheet {


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

	/** size of sprites */
	private int mSpritesSize[][]; //square size NxN

	private boolean mIsUniqueSprite; //si il n y a qu un spriteSheet et non une composition de plusieurs , on est plus souple sur son emploi

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * baseName : racine commune du groupe de Sprite a associer ensemble
	 * usually baseName = tiledTextureType.XXXX.name()
	 */
	public SpriteSheet(final AnimatedTextureType pAnimatedTextureType, final boolean pIsUniqueSprite) {

		long timeTEMP = System.currentTimeMillis();
		this.mIsUniqueSprite = pIsUniqueSprite;

		this.mAnimatedTextureTypeRoot = pAnimatedTextureType;

		if(pIsUniqueSprite)
			this.generateUniqueSpriteSheet(this.mAnimatedTextureTypeRoot);
		else
			this.generateSpriteSheet(this.mAnimatedTextureTypeRoot);

		//on supose que toutes les sprites definissent une meta sprite de NxN
		this.mNumberOfTiled = this.mNumberOfColumn * this.mNumberOfRow;
		if(this.mIsUniqueSprite)
			this.mNumberOfTiled = pAnimatedTextureType.getNumberOfTiled(); //on impose un nombre de tiled

		System.out.println("##### SpriteSheet Loading :  " + pAnimatedTextureType.name() + " > "  + (System.currentTimeMillis() - timeTEMP)/1000f + " sec." );
		

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

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * on recupere tous les texture des spritesheet d un dir
	 * ancien format : example:  TiledSprite[][] sprites = {{t,t2},{t3,t4},{t5,t6,t7,t8}};
	 * il faut que les noms de sprites dans le dir soient mis dans l'ordre
	 * on suppose que les TiledSprite d'un meme sous groupe ont tous la meme taille
	 */
	private void generateSpriteSheet(AnimatedTextureType animatedTextureType){

		//animatedTextureType = animatedTextureType.GLADIATOR_HD_TEAM1;

		List<Texture> textures = new ArrayList<Texture>();
		List<Integer> numberOfSpriteSheetByLine = new ArrayList<Integer>();
		List<Integer> tiledSize = new ArrayList<Integer>();

		//Get all texture
		FileHandle dirHandle;
		if (Gdx.app.getType() == ApplicationType.Android) {
			dirHandle = Gdx.files.internal(animatedTextureType.getPath());
		} else { // ApplicationType.Desktop ..
			dirHandle = Gdx.files.internal("./bin/"+animatedTextureType.getPath());
		}

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
		mSpritesSize = new int[mNumberOfColumn][mNumberOfRow];

		/** fill array **/
		int tmpX=0, tmpY=0;
		int currentLine = 0 ;
		int numberOfSpriteSheet = numberOfSpriteSheetByLine.get(currentLine);
		for (final Texture currentTexture: textures){

			TextureRegion[][] mFrames = TextureRegion.split(currentTexture, tiledSize.get(currentLine), tiledSize.get(currentLine));

			for(int i=0; i<mFrames.length; i++)
				for(int j=0; j<mFrames[i].length; j++){
					mSprites[tmpX+j][tmpY+i] = mFrames[i][j];
					mSpritesSize[tmpX+j][tmpY+i] = tiledSize.get(currentLine);
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





	//////////////////////////////////////////////////////////
	private void generateUniqueSpriteSheet(final AnimatedTextureType pAnimatedTextureTypeRoot){


		String animatedTextureRootName = pAnimatedTextureTypeRoot.name();

		//get final array size
		int tiledSize = pAnimatedTextureTypeRoot.getWidth()/pAnimatedTextureTypeRoot.getNumberOfColumn();
		int numberOfSpriteOnSameLine = tiledSize / SpriteSheet.MIN_TILED_SIZE; 
		this.mNumberOfColumn = numberOfSpriteOnSameLine * pAnimatedTextureTypeRoot.getNumberOfColumn();
		if(this.mIsUniqueSprite) this.mNumberOfColumn = pAnimatedTextureTypeRoot.getNumberOfColumn();

		this.mNumberOfRow = 0;
		numberOfSpriteOnSameLine = 0;
		for (final AnimatedTextureType t: AnimatedTextureType.values()){
			if(t.name().startsWith(animatedTextureRootName)){
				if(numberOfSpriteOnSameLine == 0){
					if(this.mIsUniqueSprite)
						numberOfSpriteOnSameLine = 1;
					else{
						tiledSize = t.getWidth()/t.getNumberOfColumn();
						numberOfSpriteOnSameLine = tiledSize / SpriteSheet.MIN_TILED_SIZE; 
					}
				}

				numberOfSpriteOnSameLine--;

				if(numberOfSpriteOnSameLine == 0){
					this.mNumberOfRow = this.mNumberOfRow + t.getNumberOfRow();
				}
			}
		}

		//init arrays
		mSprites = new TextureRegion[mNumberOfColumn][mNumberOfRow];
		mSpritesSize = new int[mNumberOfColumn][mNumberOfRow];

		//fill array
		int tmpX=0, tmpY=0;
		numberOfSpriteOnSameLine = 0;
		for (final AnimatedTextureType t: AnimatedTextureType.values()){

			if(t.name().startsWith(animatedTextureRootName)){

				//on a mis toutes les tiled de la taille courant sur la meme ligne?
				if(numberOfSpriteOnSameLine == 0){

					if(this.mIsUniqueSprite)
						numberOfSpriteOnSameLine = 1;
					else{
						tiledSize = t.getWidth()/t.getNumberOfColumn();
						numberOfSpriteOnSameLine = tiledSize / SpriteSheet.MIN_TILED_SIZE; 
					}
				}

				Texture currentTexture = new Texture(Gdx.files.internal(t.getPath()));
				TextureRegion[][] mFrames = TextureRegion.split(currentTexture, currentTexture.getWidth()/t.getNumberOfColumn(), currentTexture.getHeight()/t.getNumberOfRow());

				for(int i=0; i<mFrames.length; i++)
					for(int j=0; j<mFrames[i].length; j++){
						mSprites[tmpX+j][tmpY+i] = mFrames[i][j];
						mSpritesSize[tmpX+j][tmpY+i] = tiledSize;
					}

				tmpX = tmpX + mFrames[0].length;

				numberOfSpriteOnSameLine--;

				if(numberOfSpriteOnSameLine == 0){
					tmpX=0;
					tmpY = tmpY + mFrames.length;
				}
			}
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

	public int getFrameSize(final int pCol_x, final int pRow_y) {
		return this.mSpritesSize[pCol_x][pRow_y];
	}
}



