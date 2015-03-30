package com.geekmecrazy.madandarmed.XML;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepID;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponType;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.UnitType;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.UnitTeam;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.UnitSize;


public class CreepPatternHandler extends DefaultHandler {

	private static final String MODEL 							= "Model";

	/** Creep */
	private static final String CREEP_UNIT_ID 					= "unitID";
	private static final String CREEP_TEAM 					    = "team";
	private static final String CREEP_UNIT_TYPE 				= "unitType";
	private static final String CREEP_UNIT_SIZE 				= "unitSize";
	private static final String CREEP_PRICE 					= "price";
	private static final String CREEP_LIFE	 					= "lifePts";
	private static final String CREEP_SPEED 					= "walkSpeed";
	private static final String SPRITE_UNIT				 		= "spriteUnit";
	private static final String CREEP_SPRITE_BUTTON 			= "spriteButton";
	private static final String MENU_POSITION				 	= "menuPosition";
	private static final String ANIMATION_WALK_PIXEL_LENGTH 	= "animationWalkPixelLength";
	private static final String WALK_ANIMATION_ROW 				= "animationRowWalk";
	private static final String AIM_ANIMATION_ROW 				= "animationRowAim";
	private static final String FIRE_ANIMATION_ROW 				= "animationRowFire";

	/** Weapon */
	private static final String CREEP_WEAPON_EFFECT 			= "spriteWeaponEffect";
	private static final String CREEP_WEAPON_TYPE 				= "weaponType";
	private static final String CREEP_HIT_RANGE 				= "hitRange";
	private static final String CREEP_DMG_EFFECT 				= "dmgEffect";
	private static final String CREEP_HIT_SPEED 				= "hitSpeed";

	private Map<CreepID, CreepPattern> creepPattern;
	private CreepPattern currentPatern;
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
		creepPattern = new HashMap<CreepID, CreepPattern>();
	}


	@Override
	public void startElement(String namespaceURI, String name, String qName, Attributes atts) throws SAXException{
		currentElement=qName;
		if(MODEL.equalsIgnoreCase(currentElement)) {
			currentPatern = new CreepPattern();
			currentPatern.setWeaponPattern(new WeaponPattern());
			currentPatern.setUnitID(CreepID.valueOf(atts.getValue(CREEP_UNIT_ID)));
			creepPattern.put(currentPatern.getUnitID(), currentPatern);
		}
	}


	@Override
	public void characters(char ch[], int start, int length) {
		String value = new String( ch , start , length );
		if(!value.trim().equals("")) {

			/** Creep */
			if(CREEP_TEAM.equalsIgnoreCase(currentElement)) {
				currentPatern.setTeam(UnitTeam.valueOf(value));
			} else if(CREEP_UNIT_SIZE.equalsIgnoreCase(currentElement)) {
				currentPatern.setUnitSize(UnitSize.valueOf(value));
			} else if(CREEP_UNIT_TYPE.equalsIgnoreCase(currentElement)) {
				currentPatern.setUnitType(UnitType.valueOf(value));
			} else if(CREEP_WEAPON_TYPE.equalsIgnoreCase(currentElement)) {
				currentPatern.getWeaponPattern().setWeaponType(WeaponType.valueOf(value));
			} else if(CREEP_PRICE.equalsIgnoreCase(currentElement)) {
				currentPatern.setPrice(Integer.valueOf(value));
			} else if(CREEP_LIFE.equalsIgnoreCase(currentElement)) {
				currentPatern.setLifePts(Integer.valueOf(value));
			} else if(CREEP_SPEED.equalsIgnoreCase(currentElement)) {
				currentPatern.setSpeed(Float.valueOf(value));
			} else if(SPRITE_UNIT.equalsIgnoreCase(currentElement)) {
				currentPatern.setAnimatedTextureType(AnimatedTextureType.valueOf(value));
			} else if(CREEP_SPRITE_BUTTON.equalsIgnoreCase(currentElement)) {
				currentPatern.setSpriteButton(TextureType.valueOf(value));
			} else if(MENU_POSITION.equalsIgnoreCase(currentElement)) {
				currentPatern.setMenuPosition(Integer.valueOf(value));
			} else if(AIM_ANIMATION_ROW.equalsIgnoreCase(currentElement)) {
				currentPatern.setAimAnimationRow(Integer.valueOf(value));
			} else if(FIRE_ANIMATION_ROW.equalsIgnoreCase(currentElement)) {
				currentPatern.setFireAnimationRow(strArray2IntArray(value.split(",")));
			} else if(WALK_ANIMATION_ROW.equalsIgnoreCase(currentElement)) {
				currentPatern.setWalkAnimationRow(strArray2IntArray(value.split(",")));
			} else if(ANIMATION_WALK_PIXEL_LENGTH.equalsIgnoreCase(currentElement)) {
				currentPatern.setAnimationWalkPixelLength(Integer.valueOf(value));
			}

			/** Weapon */
			else if(CREEP_HIT_RANGE.equalsIgnoreCase(currentElement)) {
				currentPatern.getWeaponPattern().setHitRange(Integer.valueOf(value));
			} else if(CREEP_DMG_EFFECT.equalsIgnoreCase(currentElement)) {
				currentPatern.getWeaponPattern().setDmgEffect(Integer.valueOf(value));
			} else if(CREEP_HIT_SPEED.equalsIgnoreCase(currentElement)) {
				currentPatern.getWeaponPattern().setHitSpeed(Integer.valueOf(value));
			} else if(CREEP_WEAPON_EFFECT.equalsIgnoreCase(currentElement)) {
				currentPatern.getWeaponPattern().setAnimatedTextureType(AnimatedTextureType.valueOf(value));
			}
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

	public Map<CreepID, CreepPattern> getCreepPattern() {
		return creepPattern;
	}

}

