package com.geekmecrazy.madandarmed.Json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingType;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponType;

public class DataLoader {

	private static ObjectMap<BuildingType, BuildingPattern> buildingsPattern;
	private static ObjectMap<WeaponType, WeaponPattern> weaponsPattern;
	

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	@SuppressWarnings("unchecked")
	public static void loadBuildingsPattern() { 
		try {
		    FileHandle fileHandle = Gdx.files.internal(GlobalManager.JSON_BUILDINGSPATTERN);
		    Json json = new Json();
		    buildingsPattern = json.fromJson(ObjectMap.class, fileHandle);
		    		    
		} catch(Exception e) { 
			System.out.println("### Error DataLoader.java - loadBuildingsPattern()");
		}
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
}
