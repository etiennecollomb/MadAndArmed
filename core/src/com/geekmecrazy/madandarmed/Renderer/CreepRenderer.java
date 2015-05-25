package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Tools.GraphicalTools;


public class CreepRenderer extends VehicleRenderer {

	private CreepPattern mCreepPattern;

	private int mWalkCurrentFrame;
	
	private int mFireCurrentFrame;
	
	private int[] fireAnimation;

	private int[] walkAnimation;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CreepRenderer(){
		super();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public CreepPattern getCreepPattern() {
		return mCreepPattern;
	}

	public void setCreepPattern(final CreepPattern pCreepPattern) {
		this.mCreepPattern = pCreepPattern;
	}

	public int getWalkCurrentFrame() {
		return mWalkCurrentFrame;
	}

	public void setWalkCurrentFrame(final int pWalkCurrentFrame) {
		this.mWalkCurrentFrame = pWalkCurrentFrame;
	}

	public int getFireCurrentFrame() {
		return mFireCurrentFrame;
	}

	public void setFireCurrentFrame(final int pFireCurrentFrame) {
		this.mFireCurrentFrame = pFireCurrentFrame;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final SpriteSheet pSpriteSheet, final CreepPattern pCreepPattern, final Creep pCreep){
		super.init(pSpriteSheet, pCreep);
		
		this.setCreepPattern(pCreepPattern);
		this.setWalkCurrentFrame(0);
		this.setFireCurrentFrame(0);
		
	}
	
	@Override
	public void setDeadPattern(){
		this.addDeadActionRenderer(AnimatedTextureType.DEAD, 0f, 0f, 0);
	}

	// ===========================================================
	// Draw item
	// ===========================================================

    @Override
    /** Units Selection */
    public void onTouch(final MyGestureDetector.GestureType gestureType, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
//        if(gestureType == MyGestureDetector.GestureType.PAN) {
//            this.setColor(0f, 1f, 0f, 1f);
//            Creep thisCreep = ((Creep) this.getMilitary());
//            CreepManager.getManager().addSelectedCreep(thisCreep);
//        }
    }

    @Override
    public void onUpdate() {
        Creep thisCreep = ((Creep)this.getMilitary());

        this.setPosition(thisCreep.getPos().getX(), thisCreep.getPos().getY());
        this.setZIndex(GlobalManager.ZINDEXMAXVALUE - (int)thisCreep.getPos().getY());

        if(thisCreep.isAlive()){

            if (thisCreep.getAttackBehavior().isAttacking()){
                this.setFireCurrentFrame(this.getFireCurrentFrame()+1);
                if(this.getFireCurrentFrame() >= this.getCreepPattern().getFireAnimationRow().size())
                    this.setFireCurrentFrame(0);
                this.setCurrentFrame(GraphicalTools.getGraphicDirection(thisCreep.getNormalizedDir()), this.getCreepPattern().getFireAnimationRow().get(getFireCurrentFrame()));

            }else{
                this.setWalkCurrentFrame(this.getWalkCurrentFrame()+1);
                if(this.getWalkCurrentFrame() >= this.getCreepPattern().getWalkAnimationRow().size())
                    this.setWalkCurrentFrame(0);
                this.setCurrentFrame(GraphicalTools.getGraphicDirection(thisCreep.getNormalizedDir()), this.getCreepPattern().getWalkAnimationRow().get(getWalkCurrentFrame()));

            }
        }

        //after because OffSet has been modified
        super.onUpdate();
    }

	// ===========================================================
	// Recycle
	// ===========================================================

	@Override
	public void reset() {
		super.reset();
		
		this.setCreepPattern(null);
		this.setWalkCurrentFrame(0);
		this.setFireCurrentFrame(0);

	}



	//renvoie une array a lire de gauche a droite (ex: 012340123401234...etc)
	public void calculateAnimationListFire(){
		int state1Counter=0;
		int state2Counter=0;

		WeaponPattern weaponPattern = this.getMilitary().getAttackBehavior().getWeaponPattern();
				
		switch (weaponPattern.getWeaponType()){
		case CAC:
			//Corps a corps : 1234567 1234567...etc
			state1Counter=this.getCreepPattern().getFireAnimationRow().size();
			state2Counter = 2; //4 = ralenti x4 des coups 

			this.fireAnimation = new int[state1Counter*state2Counter];

			for(int i=0; i<state1Counter; i++)
				for(int j=0; j<state2Counter; j++)
					fireAnimation[(i*state2Counter)+j]=this.getCreepPattern().getFireAnimationRow().get(i);
			break; 

		case GUN:
			//repetition d un tir pdt tout la duree d un hitSpeed
			//GUN :  232323232 11111111...etc

			int actionLength = this.getCreepPattern().getFireAnimationRow().size(); // un coup de tir ...
			int nbOfRepetition = 2;
			//(int)weaponPattern.getHitSpeed() / (int)actionLength;

			this.fireAnimation = new int[weaponPattern.getHitSpeed()];

			for(int i=0; i<nbOfRepetition; i++)
				for(int j=0; j<actionLength; j++)
					this.fireAnimation[i*actionLength + j] = this.getCreepPattern().getFireAnimationRow().get(j);

			for(int i=actionLength*nbOfRepetition; i<this.fireAnimation.length; i++)
				this.fireAnimation[i] = this.getCreepPattern().getAimAnimationRow().get(0);

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

		state1Counter=this.getCreepPattern().getWalkAnimationRow().size();

		//type marche : 123454321
		size_=state1Counter;
		int[] tempArray = new int[size_];
		for(int i=0; i<state1Counter; i++)
			tempArray[i]=this.getCreepPattern().getWalkAnimationRow().get(i);

		//on "etale" les frame selon le ratio modulo sur la array	
		float nbFramePerStep = ((float)this.getCreepPattern().getAnimationWalkPixelLength())/((float)size_*(float)this.getCreepPattern().getWalkSpeed()); //nb de frame entre 2 dessins
		this.walkAnimation = new int[(int)(((float)this.getCreepPattern().getAnimationWalkPixelLength())/((float)this.getCreepPattern().getWalkSpeed()))];

		float frameCounter=0f;
		int stepCounter=0;
		for(int i=0; i<this.walkAnimation.length; i++){

			if(frameCounter>nbFramePerStep){
				stepCounter=stepCounter+1;
				frameCounter=frameCounter-nbFramePerStep;
			}
			this.walkAnimation[i]=tempArray[stepCounter];
			frameCounter = frameCounter+1;
		}
	}





}
