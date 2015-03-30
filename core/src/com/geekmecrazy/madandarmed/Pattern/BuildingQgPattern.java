package com.geekmecrazy.madandarmed.Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepID;


public class BuildingQgPattern {
	
	// ===========================================================
	// Attributes
	// ===========================================================
	private BuildingQgID buildingID;					// BuildingID (ex : POWERPLANT, AIRPORT ...)
	private BuildingQgSize buildingSize;				// Building size in Big Node (ex : small, medium, big)
	private BuildingQgType buildingQgType;				// Type de batiment (batiment d'ugrade, batiment de d�mo..)
	
	private String name;								// Building Name (ex : Nuclear powerplant ...)
	private String description;							// Building Description
	private boolean buidable;							// User can build it ?
	private boolean sellable;							// User can sell it ?
	private boolean movable;							// User can move it ?
	
	private int price;									// Building price
	private int cristaux;								// Building crystals price
	
	private TextureType sprite;							// Building image
	private TextureType spriteIcone;					// Button
	private AnimatedTextureType spriteBuilding;
	private AnimatedTextureType spriteBuilding1;			// Sprite orientation 1
	private AnimatedTextureType spriteBuilding2;			// Sprite orientation 2
	private AnimatedTextureType spriteBuilding3;			// Sprite orientation 3
	private AnimatedTextureType spriteBuilding4;			// Sprite orientation 4

	private int animationNbFrame;						// nombre de frame d'animation
	private int animationSpeed;							// nb cycle par frame
	private int animationNbFramePerLine=8;				// nb cycle par frame
	
	private int animationPositionX;						// placement de l'animation sur X
	private int animationPositionY;						// placement de l'animation sur Y

	private Map<BuildingQgUpgradeType, Map<Integer, UpgradePattern>> upgradesPattern=new HashMap<BuildingQgUpgradeType, Map<Integer, UpgradePattern>>();	// Liste des upgrade possible
	private Map<Integer, UpgradePattern> upgradesBuildingPattern=new HashMap<Integer, UpgradePattern>();						// Liste des evolutions du batiment possible
	
	private List<ConditionPattern> listCondiction = new ArrayList<ConditionPattern>();	// Liste des condictions à remplir pour le costruire
	private List<CreepID> listUnity = new ArrayList<CreepID>();	 	// Liste des unitées gérées par le batiments (type=demo)	
	
	//List Animation
	private int[] animationList;
	private int[] animationListY;
	
	//QG UI
	private int graphicID; //correspond a l ID des graphic pour les building
	private int qgUIPosition; //position dans le menu du QG
	
	
	// ===========================================================
	// Enum
	// ===========================================================

	public enum BuildingQgID {
		//QG
		TURRET,
		POWERPLANT,
		COMMANDPOST,
		BUILDING4,
		AIRPORT,
		TURRET2,
		NAOFACTORY,
		BUILDING7;

	} 
	
	public enum BuildingQgType {
		UPGRADE,
		DEMO
	} 
	
	public enum BuildingQgSize {
		SMALL		(1), 	//1x1
		MEDIUM		(2), 	//3x3
		BIG			(3),	//5x5
		BIG2        (4);	//5x5
		
		private int bigNode;
		private int pixel;
		
		private BuildingQgSize(int bigNode) {
			this.bigNode = bigNode;
			this.pixel = (int)GlobalManager.BIG_NODESIZE*bigNode;
		}
		
		public int getBigNode() {
			return bigNode;
		}
		
		public int getPixel() {
			return pixel;
		}

	}
	
	public enum RessourceType {
		OIL, 
		URANIUM
	}
	
	//TODO AMA temporaire
	public enum BuildingQgUpgradeType {
		BUILDING,
		GOLD,
		NUMBER,
		RESIST,
		DMG,
		RANGE,
		SPEEDEFFECT,
		HITSPEED,
		HITDURATION,
		LIFE
	}

	
	public AnimatedTextureType getSpriteBuildingCalculated(int direction, int level) {
		AnimatedTextureType tx = getSpriteBuildingUpgrade(direction, level);
		if(tx!=null) return tx;
		
		switch (direction) {
			case 2:
				return spriteBuilding2;
			case 3:
				return spriteBuilding3;
			case 4:
				return spriteBuilding4;
			default:
				return spriteBuilding1;
		}
	}
	
	private AnimatedTextureType getSpriteBuildingUpgrade(int direction, int level) {
		UpgradePattern um = getUpgradesBuildingPattern().get(level);
		if(um!=null){
			switch (direction) {
			case 2:
				return um.getSpriteBuilding2();
			case 3:
				return um.getSpriteBuilding3();
			case 4:
				return um.getSpriteBuilding4();
			default:
				return um.getSpriteBuilding1();
			}
		}
		return null;
	}
	

	
	public void calculateAnimationList(){
		animationList = new int[animationNbFrame*animationSpeed];
		animationListY = new int[animationNbFrame*animationSpeed];
		
		int currentFrameX = 0;
		int currentFrameY = 0;
		for(int i=0; i<animationNbFrame; i++){
			for(int j=0; j<animationSpeed; j++){
				animationList[(i*animationSpeed)+j]=currentFrameX;
				animationListY[(i*animationSpeed)+j]=currentFrameY;
			}
			currentFrameX++;

			if(currentFrameX>=animationNbFramePerLine){
				currentFrameX=0;
				currentFrameY++;
			}
		}
	}

	// ===========================================================
	// Attributes accessor
	// ===========================================================
	
	
	public BuildingQgID getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(BuildingQgID buildingID) {
		this.buildingID = buildingID;
	}

	public BuildingQgSize getBuildingSize() {
		return buildingSize;
	}

	public void setBuildingSize(BuildingQgSize buildingSize) {
		this.buildingSize = buildingSize;
	}

	public String getName() {
		return name;
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

	public boolean isBuidable() {
		return buidable;
	}

	public void setBuidable(boolean buidable) {
		this.buidable = buidable;
	}

	public boolean isSellable() {
		return sellable;
	}

	public void setSellable(boolean sellable) {
		this.sellable = sellable;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCristaux() {
		return cristaux;
	}

	public void setCristaux(int cristaux) {
		this.cristaux = cristaux;
	}

	public TextureType getSprite() {
		return sprite;
	}

	public void setSprite(TextureType sprite) {
		this.sprite = sprite;
	}

	public TextureType getSpriteIcone() {
		return spriteIcone;
	}

	public void setSpriteIcone(TextureType spriteIcone) {
		this.spriteIcone = spriteIcone;
	}

	public Map<BuildingQgUpgradeType, Map<Integer, UpgradePattern>> getUpgradesPattern() {
		return upgradesPattern;
	}

	public void setUpgradesPattern(
			Map<BuildingQgUpgradeType, Map<Integer, UpgradePattern>> upgradesPattern) {
		this.upgradesPattern = upgradesPattern;
	}
	
	public AnimatedTextureType getSpriteBuilding() {
		return spriteBuilding;
	}

	public void setSpriteBuilding(AnimatedTextureType spriteBuilding) {
		this.spriteBuilding = spriteBuilding;
	}

	public int[] getAnimationList() {
		return animationList;
	}

	public void setAnimationList(int[] animationList) {
		this.animationList = animationList;
	}

	public void setAnimationNbFrame(int animationNbFrame) {
		this.animationNbFrame = animationNbFrame;
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

	public List<ConditionPattern> getListCondiction() {
		return listCondiction;
	}

	public void setListCondiction(List<ConditionPattern> listCondiction) {
		this.listCondiction = listCondiction;
	}

	public int getAnimationNbFrame() {
		return animationNbFrame;
	}

	public int getAnimationSpeed() {
		return animationSpeed;
	}

	public int getAnimationPositionX() {
		return animationPositionX;
	}

	public void setAnimationPositionX(int animationPositionX) {
		this.animationPositionX = animationPositionX;
	}

	public int getAnimationPositionY() {
		return animationPositionY;
	}

	public void setAnimationPositionY(int animationPositionY) {
		this.animationPositionY = animationPositionY;
	}


	public Map<Integer, UpgradePattern> getUpgradesBuildingPattern() {
		return upgradesBuildingPattern;
	}

	public void setUpgradesBuildingPattern(
			Map<Integer, UpgradePattern> upgradesBuildingPattern) {
		this.upgradesBuildingPattern = upgradesBuildingPattern;
	}

	public BuildingQgType getBuildingQgType() {
		return buildingQgType;
	}

	public void setBuildingQgType(BuildingQgType buildingQgType) {
		this.buildingQgType = buildingQgType;
	}

	public List<CreepID> getListUnity() {
		return listUnity;
	}

	public void setListUnity(List<CreepID> listUnity) {
		this.listUnity = listUnity;
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

	public int getAnimationNbFramePerLine() {
		return animationNbFramePerLine;
	}

	public void setAnimationNbFramePerLine(int animationNbFramePerLine) {
		this.animationNbFramePerLine = animationNbFramePerLine;
	}

	public int[] getAnimationListY() {
		return animationListY;
	}

	public void setAnimationListY(int[] animationListY) {
		this.animationListY = animationListY;
	}
	
	public int getGraphicID() {
		return graphicID;
	}

	public void setGraphicID(int graphicID) {
		this.graphicID = graphicID;
	}

	public int getQgUIPosition() {
		return qgUIPosition;
	}

	public void setQgUIPosition(int qgUIPosition) {
		this.qgUIPosition = qgUIPosition;
	}

	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("#####################################\nBuildingQgPattern \n---- buildingID=");
		builder.append(buildingID);
		builder.append(" \n---- buildingSize=");
		builder.append(buildingSize);
		builder.append(" \n---- buildingQgType=");
		builder.append(buildingQgType);
		builder.append(" \n---- name=");
		builder.append(name);
		builder.append(" \n---- description=");
		builder.append(description);
		builder.append(" \n---- buidable=");
		builder.append(buidable);
		builder.append(" \n---- sellable=");
		builder.append(sellable);
		builder.append(" \n---- movable=");
		builder.append(movable);
		builder.append(" \n---- animationPositionX=");
		builder.append(animationPositionX);
		builder.append(" \n---- animationPositionY=");
		builder.append(animationPositionY);
		builder.append(" \n---- price=");
		builder.append(price);
		builder.append(" \n---- cristaux=");
		builder.append(cristaux);
		builder.append(" \n---- sprite=");
		builder.append(sprite);
		builder.append(" \n---- spriteIcone=");
		builder.append(spriteIcone);
		builder.append(" \n---- animationNbFrame=");
		builder.append(animationNbFrame);
		builder.append(" \n---- animationSpeed=");
		builder.append(animationSpeed);
		builder.append(" \n---- animationList=");
		builder.append(Arrays.toString(animationList));
		builder.append(" \n---- animationListY=");
		builder.append(Arrays.toString(animationListY));
		builder.append(" \n---- upgradesPattern=");
		builder.append(upgradesPattern.size());
		builder.append(" \n---- upgradesBuildingPattern=");
		builder.append(upgradesBuildingPattern.size());
		builder.append(" \n---- listCondiction=");
		builder.append(listCondiction.size());
		builder.append(" \n---- listUnity=");
		builder.append(listUnity.size());
		builder.append(" \n---- graphicID=");
		builder.append(graphicID);
		builder.append(" \n---- qgUIPosition=");
		builder.append(qgUIPosition);
		builder.append(" ");
		return builder.toString();
	}
}
