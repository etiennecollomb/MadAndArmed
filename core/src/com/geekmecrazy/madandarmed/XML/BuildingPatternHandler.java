package com.geekmecrazy.madandarmed.XML;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingType;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingSize;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponType;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;


public class BuildingPatternHandler extends DefaultHandler {

	private static final String MODEL 							= "Model";
	private static final String WEAPON							= "Weapon";

	/** Building */
	private static final String BUILDING_BUILDING_ID			= "buildingID";
	private static final String BUILDING_SIZE 					= "buildingSize";
	private static final String BUILDING_PRICE 					= "price";
	private static final String BUILDING_LIFE 					= "life";
	private static final String BUILDING_SPRITE			 		= "spriteBuilding";

	/** Weapon */
	private static final String WEAPON_HIT_SPEED 				= "hitSpeed";
	private static final String WEAPON_WEAPON_TYPE 				= "weaponType";
	private static final String WEAPON_HIT_RANGE 				= "hitRange";
	private static final String WEAPON_DMG_EFFECT 				= "dmgEffect";
	private static final String WEAPON_MISSILE_SPEED 			= "missileSpeed";
	private static final String WEAPON_SPRITE_EFFECT 			= "spriteWeaponEffect";
	private static final String AIM_ANIMATION_ROW 				= "aimAnimationRow";

	/** Internal */
	private Map<BuildingType, BuildingPattern> buildingPattern;
	private BuildingPattern currentPatern;
	private String currentElement;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void startDocument() throws SAXException {
		buildingPattern = new HashMap<BuildingType, BuildingPattern>();
	}

	@Override
	public void startElement(String namespaceURI, String name, String qName, Attributes atts) throws SAXException{

		currentElement=qName;
		if(MODEL.equalsIgnoreCase(currentElement)) {
			currentPatern = new BuildingPattern();
			currentPatern.setBuildingID(BuildingType.valueOf(atts.getValue(BUILDING_BUILDING_ID)));
			buildingPattern.put(currentPatern.getBuildingType(), currentPatern);
		}else if(WEAPON.equalsIgnoreCase(currentElement)) {
			//currentPatern.setWeaponPattern(new WeaponPattern());
		}
	}

	@Override
	public void characters(char ch[], int start, int length) {

		String value = new String( ch , start , length );
		if(!value.trim().equals("")) {
			/** Building */
			if(BUILDING_SPRITE.equalsIgnoreCase(currentElement)) {
				currentPatern.setAnimatedTextureType(AnimatedTextureType.valueOf(value));
			}else if(BUILDING_LIFE.equalsIgnoreCase(currentElement)) {
				currentPatern.setLife(Integer.valueOf(value));
			}else if(BUILDING_PRICE.equalsIgnoreCase(currentElement)) {
				currentPatern.setPrice(Integer.valueOf(value));
			}else if(BUILDING_SIZE.equalsIgnoreCase(currentElement)) {
				currentPatern.setBuildingSize(BuildingSize.valueOf(value));
			}else if(AIM_ANIMATION_ROW.equalsIgnoreCase(currentElement)) {
				//currentPatern.setAimAnimationRow(strArray2IntArray(value.split(",")));
			}

			/** Weapon */
//			else if(WEAPON_WEAPON_TYPE.equalsIgnoreCase(currentElement)) {
//				currentPatern.getWeaponPattern().setWeaponType(WeaponType.valueOf(value));
//			}else if(WEAPON_HIT_RANGE.equalsIgnoreCase(currentElement)) {
//				currentPatern.getWeaponPattern().setHitRange(Integer.valueOf(value));
//			} else if(WEAPON_DMG_EFFECT.equalsIgnoreCase(currentElement)) {
//				currentPatern.getWeaponPattern().setDmgEffect(Integer.valueOf(value));
//			} else if(WEAPON_HIT_SPEED.equalsIgnoreCase(currentElement)) {
//				currentPatern.getWeaponPattern().setHitSpeed(Integer.valueOf(value));
//			} else if(WEAPON_SPRITE_EFFECT.equalsIgnoreCase(currentElement)) {
//				currentPatern.getWeaponPattern().setAnimatedTextureType(AnimatedTextureType.valueOf(value));
//			} else if(WEAPON_MISSILE_SPEED.equalsIgnoreCase(currentElement)) {
//				currentPatern.getWeaponPattern().setMissileSpeed(Float.valueOf(value));
//			}
		}
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	private static int[] strArray2IntArray (String[] array) {
		int[] result = new int[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Integer.parseInt (array[i]);
		}
		return result;
	}

	public Map<BuildingType, BuildingPattern> getBuildingPattern() {
		return buildingPattern;
	}

}
