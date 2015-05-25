package com.geekmecrazy.madandarmed.Json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Pattern.BuildingMapPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Pattern.MapPattern;
import com.geekmecrazy.madandarmed.Pattern.TeamMapPattern;
import com.geekmecrazy.madandarmed.Pattern.TexturePattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class DataLoader {

	private static ObjectMap<String, BuildingPattern> buildingsPattern;
	private static ObjectMap<String, WeaponPattern> weaponsPattern;
	private static ObjectMap<String, TexturePattern> texturesPattern;
	private static ObjectMap<String, MapPattern> mapsPattern;
	private static ObjectMap<String, CreepPattern> creepsPattern;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public static ObjectMap<String, MapPattern> getMapsPattern() {
		return mapsPattern;
	}

	public static void setMapsPattern(ObjectMap<String, MapPattern> mapsPattern) {
		DataLoader.mapsPattern = mapsPattern;
	}

	public static ObjectMap<String, TexturePattern> getTexturesPattern() {
		return texturesPattern;
	}

	public static void setTexturesPattern(
			ObjectMap<String, TexturePattern> texturesPattern) {
		DataLoader.texturesPattern = texturesPattern;
	}

	public static ObjectMap<String, BuildingPattern> getBuildingsPattern() {
		return buildingsPattern;
	}

	public static void setBuildingsPattern(
			ObjectMap<String, BuildingPattern> buildingsPattern) {
		DataLoader.buildingsPattern = buildingsPattern;
	}

	public static ObjectMap<String, WeaponPattern> getWeaponsPattern() {
		return weaponsPattern;
	}

	public static void setWeaponsPattern(
			ObjectMap<String, WeaponPattern> weaponsPattern) {
		DataLoader.weaponsPattern = weaponsPattern;
	}

	public static ObjectMap<String, CreepPattern> getCreepsPattern() {
		return creepsPattern;
	}

	public static void setCreepsPattern(
			ObjectMap<String, CreepPattern> creepsPattern) {
		DataLoader.creepsPattern = creepsPattern;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	@SuppressWarnings("unchecked")
	public static void loadBuildingsPattern() {
		
		Json json = new Json();
		FileHandle text = Gdx.files.internal(GlobalManager.JSON_BUILDINGSPATTERN);
		buildingsPattern = json.fromJson(ObjectMap.class, text);
	}

	@SuppressWarnings("unchecked")
	public static void loadWeaponsPattern() {
		
		Json json = new Json();
		FileHandle text = Gdx.files.internal(GlobalManager.JSON_WEAPONSPATTERN);
		weaponsPattern = json.fromJson(ObjectMap.class, text);
	}
	
	@SuppressWarnings("unchecked")
	public static void loadTexturesPattern() {

		Json json = new Json();
		FileHandle text = Gdx.files.internal(GlobalManager.JSON_TEXTURESPATTERN);
		texturesPattern = json.fromJson(ObjectMap.class, text);
	}
	
	@SuppressWarnings("unchecked")
	public static void loadMapsPattern() {

		Json json = new Json();
		FileHandle text = Gdx.files.internal(GlobalManager.JSON_MAPSPATTERN);
		mapsPattern = json.fromJson(ObjectMap.class, text);
	}
	
	@SuppressWarnings("unchecked")
	public static void loadCreepsPattern() {

		Json json = new Json();
		FileHandle text = Gdx.files.internal(GlobalManager.JSON_CREEPSPATTERN);
		creepsPattern = json.fromJson(ObjectMap.class, text);
	}
	
	
	
}

