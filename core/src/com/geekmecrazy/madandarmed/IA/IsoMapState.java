package com.geekmecrazy.madandarmed.IA;

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
	public Type[][] positionMap;

	
	// ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
	
	public Type[][] getPositionMap() {
		return positionMap;
	}

	public void setPositionMap(Type[][] positionMap) {
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
		
		positionMap = new Type[pWidth][pHeight];
		
		for(int i=0; i<pWidth ; i++)
			for(int j=0; j<pHeight ; j++)
				positionMap[i][j] = Type.NONE;
		
	}
	
	public void add(final Type pType, final int pX, final int pY){
		positionMap[pX][pY] = pType;
	}
	
	public void remove(final int pX, final int pY){
		positionMap[pX][pY] = Type.NONE;
	}
	
	public Type getType(final int pX, final int pY){
		return positionMap[pX][pY];
	}
	
	public boolean isOccupied(final int pX, final int pY){
		return positionMap[pX][pY]!=Type.NONE;
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
