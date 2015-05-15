package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Tools.GraphicalTools;


public class CreepRenderer extends VehicleRenderer {

	private CreepPattern mCreepPattern;

	private int mWalkCurrentFrame;
	
	private int mFireCurrentFrame;

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
                if(this.getFireCurrentFrame() >= this.getCreepPattern().getFireAnimation().length)
                    this.setFireCurrentFrame(0);
                this.setCurrentFrame(GraphicalTools.getGraphicDirection(thisCreep.getNormalizedDir()), this.getCreepPattern().getFireAnimation()[getFireCurrentFrame()]);

            }else{
                this.setWalkCurrentFrame(this.getWalkCurrentFrame()+1);
                if(this.getWalkCurrentFrame() >= this.getCreepPattern().getWalkAnimation().length)
                    this.setWalkCurrentFrame(0);
                this.setCurrentFrame(GraphicalTools.getGraphicDirection(thisCreep.getNormalizedDir()), this.getCreepPattern().getWalkAnimation()[getWalkCurrentFrame()]);

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






}
