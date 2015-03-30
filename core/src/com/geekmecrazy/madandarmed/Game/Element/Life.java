package com.geekmecrazy.madandarmed.Game.Element;


public class Life extends GameElement {

	private float mLifeMax;	// max life point

	private float mLife; // current life point
	
	private float mLifeRatio; // ratio = (current life point)/(max life point)
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public Life(){}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public float getLifeMax() {
		return mLifeMax;
	}

	public void setLifeMax(final float pLifeMax) {
		this.mLifeMax = pLifeMax;
	}

	public float getLife() {
		return mLife;
	}

	public void setLife(final float pLife) {
		this.mLife = pLife;
	}

	public float getLifeRatio() {
		return mLifeRatio;
	}

	public void setLifeRatio(final float pLifeRatio) {
		this.mLifeRatio = pLifeRatio;
	}
		
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void reset() {
		this.setLifeMax(0f);
		this.setLife(0f);
		this.setLifeRatio(0f);
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	public void init(final float pLife) {
		this.setLife(pLife);
		this.setLifeMax(pLife);
		this.setLifeRatio(1f);
	}
	
	public void hit(final float pDmgEffect){
		this.setLife( this.getLife() - pDmgEffect ); 
		this.setLifeRatio( this.getLife() / this.getLifeMax() );
	}
	
	public boolean isDead (){
		return this.getLife() <= 0;
	}


}


