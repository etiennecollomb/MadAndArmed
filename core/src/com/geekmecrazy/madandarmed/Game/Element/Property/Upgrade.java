package com.geekmecrazy.madandarmed.Game.Element.Property;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Definition d'une upgrade du Factory
 */
public class Upgrade {
	private int lvl;
	private String name;
	private String tooltip;
	private String description;
	private int priceLevel;
	private int value;
	private TextureRegion btnTextureRegion;
	
	
	public int getPriceLevel() {
		return priceLevel;
	}
	
	public void setPriceLevel(int priceLevel) {
		this.priceLevel = priceLevel;
	}
	
	public int getLvl() {
		return lvl;
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public TextureRegion getBtnTextureRegion() {
		return btnTextureRegion;
	}
	
	public void setBtnTextureRegion(TextureRegion btnTextureRegion) {
		this.btnTextureRegion = btnTextureRegion;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
}
