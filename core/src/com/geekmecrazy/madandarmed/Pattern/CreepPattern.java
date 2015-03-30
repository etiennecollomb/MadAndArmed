package com.geekmecrazy.madandarmed.Pattern;

import java.util.Arrays;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;

public class CreepPattern {

	private CreepID mUnitID;							// UnitID (horus, nao ...)
	
	private UnitTeam mTeam;								// Team de l'unite (a cause des texture associe a l unit ID ..; TODO : a refaire car ca devrait pas etre ici ...)
	
	private UnitType mUnitType;							// Unit type (air, sol)
	
	private UnitSize mUnitSize;							// Unit size in Small Node (small, medium, big)

	private WeaponPattern mWeaponPattern;

	private int mPrice;									// Unit price
	
	private float mLife;								// Unit Life point
	
	private float mSpeed;								// Unit move speed

	private AnimatedTextureType mAnimatedTextureType;	// SpriteSheetRoot du creepRenderer
	
	private TextureType mSpriteButton;					// Sprite used for the button
	
	private int mMenuPosition;							//positon des boutons unit dans le menu en bas de Fight

	private int mWaitAnimationRow;						// Tile Row WAIT
	
	private int mAimAnimationRow;						// Tile Row AIM
	
	private int[] mFireAnimationRow;					// Tiles Row FIRE
	
	private int[] mWalkAnimationRow;					// Tiles Row WALK

	private int mAnimationWalkPixelLength;				// Number of pixel for one step for walk animation

	private int[] mFireAnimation;						//List des animations Fire
	
	private int[] mWalkAnimation;						//List des animations Walk

	public enum CreepID {
		//TEAM 1 (PLAYER)
        GLADIATOR_HD_TEAM1,
		MARINE_HD_TEAM1,
        MESH_HD_TEAM1,

		//TEAM 2 (IA)
        GLADIATOR_HD_TEAM2,
        MARINE_HD_TEAM2,
        MESH_HD_TEAM2,

		//OTHER TEMP
		NAO,
		SPARTAN,
		HORUS
	}

	public enum UnitType {
		AIR, 
		SOL
	}

	public enum UnitTeam {
		TEAM1, 
		TEAM2
	}

	public enum UnitSize {
		SMALL(0), 		//1x1, rayon 0
		MEDIUM(1), 		//3x3, rayon 1
		BIG(2);			//5x5, rayon 2

		private int mRayon;

		private UnitSize(int pRayon) {
			this.mRayon = pRayon;
		}

		public int getRayon() {
			return mRayon;
		}
	}

	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public CreepID getUnitID() {
		return mUnitID;
	}

	public void setUnitID(CreepID pUnitID) {
		this.mUnitID = pUnitID;
	}

	public UnitTeam getTeam() {
		return mTeam;
	}

	public void setTeam(UnitTeam pTeam) {
		this.mTeam = pTeam;
	}

	public UnitSize getUnitSize() {
		return mUnitSize;
	}

	public void setUnitSize(UnitSize pUnitSize) {
		this.mUnitSize = pUnitSize;
	}

	public UnitType getUnitType() {
		return mUnitType;
	}

	public void setUnitType(UnitType pUnitType) {
		this.mUnitType = pUnitType;
	}

	public WeaponPattern getWeaponPattern() {
		return mWeaponPattern;
	}

	public void setWeaponPattern(WeaponPattern pWeaponPattern) {
		this.mWeaponPattern = pWeaponPattern;
	}

	public int getPrice() {
		return mPrice;
	}

	public void setPrice(int pPrice) {
		this.mPrice = pPrice;
	}

	public float getLife() {
		return mLife;
	}

	public void setLifePts(float pLife) {
		this.mLife = pLife;
	}

	public float getSpeed() {
		return mSpeed;
	}

	public void setSpeed(float pSpeed) {
		this.mSpeed = pSpeed;
	}

	public AnimatedTextureType getAnimatedTextureType() {
		return mAnimatedTextureType;
	}

	public void setAnimatedTextureType(AnimatedTextureType pAnimatedTextureType) {
		this.mAnimatedTextureType = pAnimatedTextureType;
	}

	/** button */	

	public TextureType getSpriteButton() {
		return mSpriteButton;
	}

	public void setSpriteButton(TextureType pSpriteButton) {
		this.mSpriteButton = pSpriteButton;
	}
	public int getMenuPosition() {
		return mMenuPosition;
	}

	public void setMenuPosition(int pMenuPosition) {
		this.mMenuPosition = pMenuPosition;
	}

	/** Animations */
	public int getAimAnimationRow() {
		return mAimAnimationRow;
	}

	public void setAimAnimationRow(int pAimAnimationRow) {
		this.mAimAnimationRow = pAimAnimationRow;
	}

	public int[] getFireAnimationRow() {
		return mFireAnimationRow;
	}

	public void setFireAnimationRow(int[] pFireAnimationRow) {
		this.mFireAnimationRow = pFireAnimationRow;
	}

	public int[] getWalkAnimationRow() {
		return mWalkAnimationRow;
	}

	public void setWalkAnimationRow(int[] pWalkAnimationRow) {
		this.mWalkAnimationRow = pWalkAnimationRow;
	}

	public int getAnimationWalkPixelLength() {
		return mAnimationWalkPixelLength;
	}

	public void setAnimationWalkPixelLength(int pAnimationWalkPixelLength) {
		this.mAnimationWalkPixelLength = pAnimationWalkPixelLength;
	}

	public int[] getFireAnimation() {
		return mFireAnimation;
	}

	public int[] getWalkAnimation() {
		return mWalkAnimation;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("################  CreepPattern  #####################\n");
		builder.append("---- unitID=");
		builder.append(mUnitID);
		builder.append(" \n---- unitType=");
		builder.append(mUnitType);
		builder.append(" \n---- unitSize=");
		builder.append(mUnitSize);
		builder.append(" \n---- price=");
		builder.append(mPrice);
		builder.append(" \n---- lifePts=");
		builder.append(mLife);
		builder.append(" \n---- walkSpeed=");
		builder.append(mSpeed);
		builder.append(" \n---- spriteButton=");
		builder.append(mSpriteButton);
		builder.append(" \n---- animationRowWait=");
		builder.append(mWaitAnimationRow);
		builder.append(" \n---- animationRowAim=");
		builder.append(mAimAnimationRow);
		builder.append(" \n---- animationRowFire=");
		builder.append(Arrays.toString(mFireAnimationRow));
		builder.append(" \n---- animationRowWalk=");
		builder.append(Arrays.toString(mWalkAnimationRow));
		builder.append(" \n---- readyForAnimationListFire=");
		builder.append(Arrays.toString(mFireAnimation));
		builder.append(" \n---- readyForAnimationListWalk=");
		builder.append(Arrays.toString(mWalkAnimation));
		builder.append("\n");
		return builder.toString();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	//renvoie une array a lire de gauche a droite (ex: 012340123401234...etc)
	public void calculateAnimationListFire(){
		int state1Counter=0;
		int state2Counter=0;

		switch (this.getWeaponPattern().getWeaponType()){
		case CAC:
			//Corps a corps : 1234567 1234567...etc
			state1Counter=this.getFireAnimationRow().length;
			state2Counter = 2; //4 = ralenti x4 des coups 

			mFireAnimation = new int[state1Counter*state2Counter];

			for(int i=0; i<state1Counter; i++)
				for(int j=0; j<state2Counter; j++)
					mFireAnimation[(i*state2Counter)+j]=this.getFireAnimationRow()[i];
			break; 

		case GUN:
			//repetition d un tir pdt tout la duree d un hitSpeed
			//GUN :  232323232 11111111...etc

			int actionLength = this.getFireAnimationRow().length; // un coup de tir ...
			int nbOfRepetition = 2;
			//(int)weaponPattern.getHitSpeed() / (int)actionLength;

			mFireAnimation = new int[this.mWeaponPattern.getHitSpeed()];

			for(int i=0; i<nbOfRepetition; i++)
				for(int j=0; j<actionLength; j++)
					mFireAnimation[i*actionLength + j] = this.getFireAnimationRow()[j];

			for(int i=actionLength*nbOfRepetition; i<mFireAnimation.length; i++)
				mFireAnimation[i] = this.getAimAnimationRow();

			break;
			
		default:
			break; 

		}
	}

	//renvoie une array a lire de gauche a droite en boucle
	//type marche : 123454321...etc
	public void calculateAnimationListWalk(){
		int size_ = 0;
		int state1Counter=0;

		state1Counter=this.getWalkAnimationRow().length;

		//type marche : 123454321
		size_=state1Counter;
		int[] tempArray = new int[size_];
		for(int i=0; i<state1Counter; i++)
			tempArray[i]=this.getWalkAnimationRow()[i];

		//on "etale" les frame selon le ratio modulo sur la array	
		float nbFramePerStep = ((float)this.mAnimationWalkPixelLength)/((float)size_*(float)this.mSpeed); //nb de frame entre 2 dessins
		mWalkAnimation = new int[(int)(((float)this.mAnimationWalkPixelLength)/((float)this.mSpeed))];

		float frameCounter=0f;
		int stepCounter=0;
		for(int i=0; i<mWalkAnimation.length; i++){

			if(frameCounter>nbFramePerStep){
				stepCounter=stepCounter+1;
				frameCounter=frameCounter-nbFramePerStep;
			}
			mWalkAnimation[i]=tempArray[stepCounter];
			frameCounter = frameCounter+1;
		}
	}

}


