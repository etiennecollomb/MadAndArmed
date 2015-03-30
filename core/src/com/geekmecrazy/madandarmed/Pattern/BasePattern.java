package com.geekmecrazy.madandarmed.Pattern;

public class BasePattern {
	
	// ===========================================================
	// Attributes
	// ===========================================================
	private TiledSkin skin;
	private TiledPos pos;
	private int ia;
	
	
	// ===========================================================
	// Enum
	// ===========================================================
	
	public enum BaseStatut {
		OK,
		KO
	} 
	
	public enum TiledSkin {
		SKIN1		(1394, 13);
		
		private int baseOk;
		private int baseKo;
		
		private TiledSkin(int baseOk, int baseKo) {
			this.baseOk = baseOk;
			this.baseKo = baseKo;
		}
		
		public int getBaseOk() {
			return baseOk;
		}
		public void setBaseOk(int baseOk) {
			this.baseOk = baseOk;
		}
		public int getBaseKo() {
			return baseKo;
		}
		public void setBaseKo(int baseKo) {
			this.baseKo = baseKo;
		}
	}
	
	
	
	// ===========================================================
	// Attributes accessor
	// ===========================================================

	
	public TiledSkin getSkin() {
		return skin;
	}

	public void setSkin(TiledSkin skin) {
		this.skin = skin;
	}
	
	public TiledPos getPos() {
		return pos;
	}

	public void setPos(TiledPos pos) {
		this.pos = pos;
	}
	
	public int getIa() {
		return ia;
	}
	
	public void setIa(int ia) {
		this.ia = ia;
	}
	
}
