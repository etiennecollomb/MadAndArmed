
package com.geekmecrazy.madandarmed.Pattern;

import java.util.List;

import com.geekmecrazy.madandarmed.Loader.PatternManager;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponName;

public class CreepPattern {
	
	public static enum CreepType {
		GLADIATOR,
		MARINE,
		FLAMETHROWER,
		MESH,
		BULLHOUND,
		HACKER,
		MEDIC,
		GARGAN,
		SNIPER
	} 
	
	public static enum CreepSize {
		SMALL(0), 		//1x1, rayon 0
		MEDIUM(1), 		//3x3, rayon 1
		BIG(2);			//5x5, rayon 2

		private int mRayon;

		private CreepSize(int pRayon) {
			this.mRayon = pRayon;
		}

		public int getRayon() {
			return mRayon;
		}
	}

	private int[] fireAnimation;
	private int[] walkAnimation;
	
	/** Creep Type */
	private CreepType creepType;

	/** Weapon type */
	private WeaponName weaponName;
	
	/** size on the map en small Node */
	private CreepSize creepSize;

	/** Unit diameter in pixel (used for boids algo) */
	private float diameter;
	
	private float life;
	
	/** walking speed */
	private float walkSpeed;
	
	/** Price of the unit at creation */
	private int price;

	/** how many pixels for one walk step */
	private int animationWalkPixelLength;

	/** list of row in spriteSheet for Walk Animation */
	private List<Integer> walkAnimationRow;
	
	/** list of row in spriteSheet for Walk Animation */
	private List<Integer> aimAnimationRow;

	/** list of row in spriteSheet for Fire Animation */
	private List<Integer> fireAnimationRow;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int[] getFireAnimation() {
		return fireAnimation;
	}

	public void setFireAnimation(int[] fireAnimation) {
		this.fireAnimation = fireAnimation;
	}

	public int[] getWalkAnimation() {
		return walkAnimation;
	}

	public void setWalkAnimation(int[] walkAnimation) {
		this.walkAnimation = walkAnimation;
	}

	public CreepType getCreepType() {
		return creepType;
	}

	public void setCreepType(CreepType creepType) {
		this.creepType = creepType;
	}

	public WeaponName getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(WeaponName weaponName) {
		this.weaponName = weaponName;
	}

	public CreepSize getCreepSize() {
		return creepSize;
	}

	public void setCreepSize(CreepSize creepSize) {
		this.creepSize = creepSize;
	}

	public float getDiameter() {
		return diameter;
	}

	public void setDiameter(float diameter) {
		this.diameter = diameter;
	}

	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}

	public void setWalkSpeed(float walkSpeed) {
		this.walkSpeed = walkSpeed;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAnimationWalkPixelLength() {
		return animationWalkPixelLength;
	}

	public void setAnimationWalkPixelLength(int animationWalkPixelLength) {
		this.animationWalkPixelLength = animationWalkPixelLength;
	}

	public List<Integer> getAimAnimationRow() {
		return aimAnimationRow;
	}

	public void setAimAnimationRow(List<Integer> aimAnimationRow) {
		this.aimAnimationRow = aimAnimationRow;
	}

	public List<Integer> getWalkAnimationRow() {
		return walkAnimationRow;
	}

	public void setWalkAnimationRow(List<Integer> walkAnimationRow) {
		this.walkAnimationRow = walkAnimationRow;
	}

	public List<Integer> getFireAnimationRow() {
		return fireAnimationRow;
	}

	public void setFireAnimationRow(List<Integer> fireAnimationRow) {
		this.fireAnimationRow = fireAnimationRow;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	//renvoie une array a lire de gauche a droite (ex: 012340123401234...etc)
	public void calculateAnimationListFire(){
		int state1Counter=0;
		int state2Counter=0;

		WeaponPattern weaponPattern = PatternManager.getWeaponsPattern().get(this.getWeaponName().name());
		switch (weaponPattern.getWeaponType()){
		case FLAMETHROWER:
		case MESH_MULTIEXPLOSION:
		case BULLHOUND:
		case MEDIC:
		case HACKER:
		case GARGAN:
		case SNIPER:
		case SWORD:
			//Corps a corps : 1234567 1234567...etc
			state1Counter = this.getFireAnimationRow().size();
			state2Counter = 2; //4 = ralenti x4 des coups 

			this.fireAnimation = new int[state1Counter*state2Counter];

			for(int i=0; i<state1Counter; i++)
				for(int j=0; j<state2Counter; j++)
					fireAnimation[(i*state2Counter)+j]=this.getFireAnimationRow().get(i);
			break; 

		case GUN:
			//repetition d un tir pdt tout la duree d un hitSpeed
			//GUN :  232323232 11111111...etc

			int actionLength = this.getFireAnimationRow().size(); // un coup de tir ...
			int nbOfRepetition = 2;
			//(int)weaponPattern.getHitSpeed() / (int)actionLength;

			this.fireAnimation = new int[weaponPattern.getHitSpeed()];

			for(int i=0; i<nbOfRepetition; i++)
				for(int j=0; j<actionLength; j++)
					this.fireAnimation[i*actionLength + j] = this.getFireAnimationRow().get(j);

			for(int i=actionLength*nbOfRepetition; i<this.fireAnimation.length; i++)
				this.fireAnimation[i] = this.getAimAnimationRow().get(0);

			break;
			
		default:
			break; 

		}
	}

	//renvoie une array a lire de gauche a droite en boucle
	public void calculateAnimationListWalk(){
		
		/** set walk speed per frame **/
//		this.setWalkSpeed( this.getAnimationWalkPixelLength() / (float)this.getWalkAnimationRow().size() );
		
		int frameRepetition = 2; /** double, triple...etc les frames si neccessaire **/
		int size = this.getWalkAnimationRow().size();
		this.walkAnimation = new int[ size * frameRepetition ];
		for(int i=0; i<size; i++){
			for(int j=0; j<frameRepetition; j++)
				this.walkAnimation[i*frameRepetition+j]=this.getWalkAnimationRow().get(i);
		}
		
//		/** TEST :saute des frame **/
//		int delaiFrame = 2;
//		this.walkAnimation = new int[ this.getWalkAnimationRow().size()/delaiFrame*delaiFrame ];
//		for(int i=0; i<this.walkAnimation.length/delaiFrame; i++){
//			for(int j=0; j<delaiFrame; j++)
//				this.walkAnimation[i*delaiFrame+j]=this.getWalkAnimationRow().get(i*delaiFrame);
//		}
		

	}



}


