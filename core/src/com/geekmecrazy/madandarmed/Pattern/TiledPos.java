package com.geekmecrazy.madandarmed.Pattern;

public class TiledPos {
	
	// ===========================================================
	// Attributes
	// ===========================================================
	private int posX;
	private int posY;
	
	private int type;
	
	
	public TiledPos(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}
	
	// ===========================================================
	// Attributes accessor
	// ===========================================================
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
	// ===========================================================
	// Override hashCode, equals
	// ===========================================================
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		TiledPos other = (TiledPos) obj;
		if (posX != other.posX)return false;
		if (posY != other.posY)return false;
		return true;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
