package com.geekmecrazy.madandarmed.Json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;

public class DataLoader {

	private static ObjectMap<String, BuildingPattern> buildingsPattern;
	private static ObjectMap<String, WeaponPattern> weaponsPattern;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

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
	
}
