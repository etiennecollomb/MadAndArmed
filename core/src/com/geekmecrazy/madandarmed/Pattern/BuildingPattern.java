package com.geekmecrazy.madandarmed.Pattern;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponType;


public class BuildingPattern {

	private BuildingID mBuildingID;
	
	private BuildingSize mBuildingSize;
	
	private AnimatedTextureType mAnimatedTextureType;
	
	private float mLife;
	
	private int mPrice;
	
	private WeaponPattern mWeaponPattern;
	
	private int[] mAimAnimationRow;
	
	private int[] mFireAnimation;
	
	public enum BuildingID {
		CASTLE_TEAM1,
        CASTLE_TEAM2,
		TOWER_TEAM1,
        TOWER_TEAM2,
	} 
	
	public enum BuildingSize {
		SMALL		(1), 		//1x1
		MEDIUM		(2), 		//3x3
		BIG			(3);		//5x5
		
		private int mBigNodeSize;

		private BuildingSize(int pBigNodeSize) {
			this.mBigNodeSize = pBigNodeSize;
		}

		public int getBigNodeSize() {
			return mBigNodeSize;
		}
	}

	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public BuildingID getBuildingID() {
		return mBuildingID;
	}

	public void setBuildingID(BuildingID pBuildingID) {
		this.mBuildingID = pBuildingID;
	}
	
	public BuildingSize getBuildingSize() {
		return mBuildingSize;
	}

	public void setBuildingSize(BuildingSize pBuildingSize) {
		this.mBuildingSize = pBuildingSize;
	}
	
	public AnimatedTextureType getAnimatedTextureType() {
		return mAnimatedTextureType;
	}

	public void setAnimatedTextureType(AnimatedTextureType pAnimatedTextureType) {
		this.mAnimatedTextureType = pAnimatedTextureType;
	}

	public float getLife() {
		return mLife;
	}

	public void setLife(float pLife) {
		this.mLife = pLife;
	}

	public int getPrice() {
		return mPrice;
	}

	public void setPrice(int pPrice) {
		this.mPrice = pPrice;
	}

	public WeaponPattern getWeaponPattern() {
		return mWeaponPattern;
	}

	public void setWeaponPattern(WeaponPattern pWeaponPattern) {
		this.mWeaponPattern = pWeaponPattern;
	}

	public int[] getAimAnimationRow() {
		return mAimAnimationRow;
	}

	public void setAimAnimationRow(int[] pAimAnimationRow) {
		this.mAimAnimationRow = pAimAnimationRow;
	}

	public int[] getmFireAnimation() {
		return mFireAnimation;
	}

	public void setmFireAnimation(int[] mFireAnimation) {
		this.mFireAnimation = mFireAnimation;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("################  BuildingPattern  #####################");
		builder.append("\n---- BuildingID=");
		builder.append(mBuildingID);
		builder.append("\n---- BuildingSize=");
		builder.append(mBuildingSize);
		builder.append("\n---- Life=");
		builder.append(mLife);
		builder.append("\n---- Price=");
		builder.append(mPrice);
		builder.append("\n---- AnimatedTextureType=");
		builder.append(mAnimatedTextureType);
		builder.append("\n---- weapon=");
		builder.append(mWeaponPattern);
		builder.append("\n");
		return builder.toString();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	/** renvoie une array de row correspondant a une animation */
	public void calculateAnimationListFire(){
		if(mWeaponPattern!=null){
			switch (this.getWeaponPattern().getWeaponType()){
			case MISSILE:
				//TODO: on set une anim de Aim
				//revoir avec anim de Fire
				mFireAnimation = new int[1];
				mFireAnimation[0]=this.getAimAnimationRow()[0];
				break;
			default:
				break;
			}
		}
	}


}
