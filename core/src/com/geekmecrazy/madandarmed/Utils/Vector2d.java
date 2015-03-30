package com.geekmecrazy.madandarmed.Utils;

import com.badlogic.gdx.utils.Pool.Poolable;

/** Implements a simple 2d vector class
 */
public class Vector2d implements Poolable
{

	private float x;
	
	private float y;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void set(float pX, float pY ){
		this.setX(pX); 
		this.setY(pY); 
	}
	
	public void set(Vector2d pVector){
		this.x = pVector.getX(); 
		this.y = pVector.getY(); 
	}
	
	public void setX(float pX){
		this.x = pX; 
	}
	
	public void setY(float pY){
		this.y = pY;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void reset(){
		this.x = 0; 
		this.y = 0; 		
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void add(float pX, float pY ){
		this.x += pX;
		this.y += pY;  
	}

	public void add(Vector2d pVector){
		this.x += pVector.getX();
		this.y += pVector.getY();
	}

	public void sub(float pX, float pY ){
		this.x -= pX;
		this.y -= pY;  
	}

	public void sub(Vector2d pVector){ 
		this.x -= pVector.getX();
		this.y -= pVector.getY();  
	}

	public void scale(float pS){ 
		this.x *= pS; 
		this.y *= pS; 
	}

	public float length(){ 
		return  (float) Math.sqrt(this.x * this.x + this.y * this.y);
	}

	//(vecteur unitaire)
	public void normalize(){ 
		float lght = length();
		if(lght != 0){
			this.x /= lght;
			this.y /= lght;
		}
	}

	//(produit scalaire)
	public float dot(Vector2d pVector){ 
		return this.x * pVector.getX() + this.y * pVector.getY(); 
	}

}

