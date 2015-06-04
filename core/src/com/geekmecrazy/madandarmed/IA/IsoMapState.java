package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Entity.Shape;

public class IsoMapState {

	public static enum Type{
		NONE,
		BARRICADE,
		;
		
	}; 
	
	public int width;
	public int height;
	
	/** Iso map state save in ortho array
	 * Une ligne sur deux est decallee d'une case
	 */
	public Shape[][] positionMap;

	
	// ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
	
	public Shape[][] getPositionMap() {
		return positionMap;
	}

	public void setPositionMap(Shape[][] positionMap) {
		this.positionMap = positionMap;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	// ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
	
    // ===========================================================
    // Methods
    // ===========================================================
	
	public void init(final int pWidth, final int pHeight) {
		
		this.setWidth(pWidth);
		this.setHeight(pHeight);
		
		positionMap = new Shape[pWidth][pHeight];
		
		for(int i=0; i<pWidth ; i++)
			for(int j=0; j<pHeight ; j++)
				positionMap[i][j] = null;
	}
	
	public void add(final Shape pIsoShape, final int pX, final int pY){
		if( !isOccupied(pX, pY))
			positionMap[pX][pY] = pIsoShape;
	}
	
	public void remove(final Shape pIsoShape, final int pX, final int pY){
		if(pIsoShape == positionMap[pX][pY])
			positionMap[pX][pY] = null;
	}
	
	public Type getType(final int pX, final int pY){
		return positionMap[pX][pY].getType();
	}
	
	public Shape getIsoShape(final int pX, final int pY){
		return positionMap[pX][pY];
	}
	
	public boolean isOccupied(final int pX, final int pY){
		/** Check borders */
		if(pX<0 || pY<0 || pX>=this.width || pY>=this.height)
			return false;
		return positionMap[pX][pY]!=null;
	}
	
	public boolean isUpOccupied(final int pX, final int pY){
		if(pY/2*2==pY) return isOccupied(pX+1, pY+1); //Pair
		return isOccupied(pX, pY+1); //Impair 
	}
	public boolean isDownOccupied(final int pX, final int pY){
		if(pY/2*2==pY) return isOccupied(pX, pY-1); //Pair
		return isOccupied(pX-1, pY-1); //Impair
	}
	public boolean isLeftOccupied(final int pX, final int pY){
		if(pY/2*2==pY) return isOccupied(pX, pY+1); //Pair
		return isOccupied(pX-1, pY+1); //Impair
	}
	public boolean isRightOccupied(final int pX, final int pY){
		if(pY/2*2==pY) return isOccupied(pX+1, pY-1); //Pair
		return isOccupied(pX, pY-1); //Impair
	}

	
	
}
