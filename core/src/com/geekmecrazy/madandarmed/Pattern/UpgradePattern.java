package com.geekmecrazy.madandarmed.Pattern;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Pattern.BuildingQgPattern.BuildingQgUpgradeType;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepID;


public class UpgradePattern {
	// ===========================================================
	// Attributes
	// ===========================================================
	private BuildingQgUpgradeType type;		// Type of upgrade (DMG,  RANGE...)
	private int level;						// Ugrade Level
	
	private String name;					// Upgrade Title
	private String description;				// Upgrade information
	private String tooltip;					//
	
	private int price;						// Upgrade price
	private int value;						// Value of upgrade (DMG+1 ...)
	
	private Set<CreepID> targets = new HashSet<CreepPattern.CreepID>();			// Unite visees par l upgrade

	private TextureType spriteIcone;		// Icone upgrade
	
	private AnimatedTextureType spriteBuilding;
	private AnimatedTextureType spriteBuilding1;
	private AnimatedTextureType spriteBuilding2;
	private AnimatedTextureType spriteBuilding3;
	private AnimatedTextureType spriteBuilding4;
	
	private List<ConditionPattern>	listCondiction = new ArrayList<ConditionPattern>();	
	
	private int graphicID;
	
	// ===========================================================
	// Enum
	// ===========================================================
	public enum UpgradeType {
		DMG,
		RANGE,
		HITSPEED,
		LIFE
	} 
	
	// ===========================================================
	// Attributes accessor
	// ===========================================================
	
	public String getName() {
		return name;
	}
	
	public BuildingQgUpgradeType getType() {
		return type;
	}

	public void setType(BuildingQgUpgradeType type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public Set<CreepID> getTargets() {
		return targets;
	}

	public void setTargets(Set<CreepID> targets) {
		this.targets = targets;
	}



	public TextureType getSpriteIcone() {
		return spriteIcone;
	}

	public void setSpriteIcone(TextureType spriteIcone) {
		this.spriteIcone = spriteIcone;
	}

	public List<ConditionPattern> getListCondiction() {
		return listCondiction;
	}

	public void setListCondiction(List<ConditionPattern> listCondiction) {
		this.listCondiction = listCondiction;
	}

	public AnimatedTextureType getSpriteBuilding() {
		return spriteBuilding;
	}

	public void setSpriteBuilding(AnimatedTextureType spriteBuilding) {
		this.spriteBuilding = spriteBuilding;
	}

	public AnimatedTextureType getSpriteBuilding1() {
		return spriteBuilding1;
	}

	public void setSpriteBuilding1(AnimatedTextureType spriteBuilding1) {
		this.spriteBuilding1 = spriteBuilding1;
	}

	public AnimatedTextureType getSpriteBuilding2() {
		return spriteBuilding2;
	}

	public void setSpriteBuilding2(AnimatedTextureType spriteBuilding2) {
		this.spriteBuilding2 = spriteBuilding2;
	}

	public AnimatedTextureType getSpriteBuilding3() {
		return spriteBuilding3;
	}

	public void setSpriteBuilding3(AnimatedTextureType spriteBuilding3) {
		this.spriteBuilding3 = spriteBuilding3;
	}

	public AnimatedTextureType getSpriteBuilding4() {
		return spriteBuilding4;
	}

	public void setSpriteBuilding4(AnimatedTextureType spriteBuilding4) {
		this.spriteBuilding4 = spriteBuilding4;
	}

	public int getGraphicID() {
		return graphicID;
	}

	public void setGraphicID(int graphicID) {
		this.graphicID = graphicID;
	}

}
