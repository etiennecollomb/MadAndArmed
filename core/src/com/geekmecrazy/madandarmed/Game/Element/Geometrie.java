package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class Geometrie extends GameElement {

	/** direction du vehicule */
	private Vector2d mNormalizedDir = new Vector2d();
	
	/** Coordonnees reelles */
	private Vector2d mPos = new Vector2d();
	
	/** Coordonnees Small Node */
    private int mSmallNodeX;
    private int mSmallNodeY;
    
    /** Coordonnees Big Node */
    private int mBigNodeX;
    private int mBigNodeY;
    
    /** Taille reelles
     * de la geometrie "visible"
     * et non du sprite qui la contient*/
    private float mWidth;
	private float mHeight;
    
    /** Taille Small Node */
    private int mSmallNodeWidth;
    private int mSmallNodeHeight;
    
    /** Non penetration constrainte */
    private boolean isNPC; //est ce que la geometrie est sujette a la Non Penetration Constraint (forcer un creep a aller a un point par exemple...)
    
	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setNormalizedDir(Vector2d v) {
		v.normalize();
		mNormalizedDir.set(v);
	}

	public Vector2d getNormalizedDir() {
		return mNormalizedDir;
	}
	
	public void setPos(float x, float y) {
		mPos.set(x, y);
		this.setNodeCoord();
	}

	public Vector2d getPos() {
		return mPos;
	}
	
    public float getWidth() {
		return mWidth;
	}

	public void setWidth(float pWidth) {
		this.mWidth = pWidth;
	}

	public float getHeight() {
		return mHeight;
	}

	public void setHeight(float pHeight) {
		this.mHeight = pHeight;
	}

	public int getSmallNodeWidth() {
		return mSmallNodeWidth;
	}

	public int getSmallNodeHeight() {
		return mSmallNodeHeight;
	}

    public int getSmallNodeX() {
		return mSmallNodeX;
	}

	public int getSmallNodeY() {
		return mSmallNodeY;
	}

	public int getBigNodeX() {
		return mBigNodeX;
	}

	public int getBigNodeY() {
		return mBigNodeY;
	}
	
	public boolean isNPC() {
		return isNPC;
	}

	public void setNPC(boolean isNPC) {
		this.isNPC = isNPC;
	}

	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void reset() {	
		
		mPos.set(0, 0);
		mSmallNodeX=0;
		mSmallNodeY=0;
		mBigNodeX=0;
		mBigNodeY=0;
		mWidth=0;
		mHeight=0;
		
	}

	@Override
	public void onUpdate() {
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(float pPosX, float pPosY, float pWidth, float pHeight) {
		
		this.setPos(pPosX, pPosY);
		this.mWidth = pWidth;
		this.mHeight = pHeight;
		this.mSmallNodeWidth=(int)(this.mWidth/GlobalManager.SMALL_NODESIZE);
		this.mSmallNodeHeight=(int)(this.mHeight/GlobalManager.SMALL_NODESIZE);
		this.mNormalizedDir.set(1, 0);
		this.setNormalizedDir(this.mNormalizedDir);
		setNPC(true);
	}

	public void setNodeCoord(){
		this.mSmallNodeX=(int)(this.getPos().getX()/GlobalManager.SMALL_NODESIZE);
		this.mSmallNodeY=(int)(this.getPos().getY()/GlobalManager.SMALL_NODESIZE);
		this.mBigNodeX=(int)(this.getPos().getX()/GlobalManager.BIG_NODESIZE);
		this.mBigNodeY=(int)(this.getPos().getY()/GlobalManager.BIG_NODESIZE);
	}
	


}


