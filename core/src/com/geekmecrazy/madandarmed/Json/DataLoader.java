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

	/**TEST*/
	static ObjectMap<String, PhoneNumber> buildingPatternMap = new ObjectMap<String, PhoneNumber>();
	/**FIN TEST*/



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
			
			/** TEST */
//			Json json = new Json();
//			FileHandle text = Gdx.files.internal(GlobalManager.JSON_BUILDINGSPATTERN);
//			buildingPatternMap = json.fromJson(ObjectMap.class, text);
//			System.out.println(buildingPatternMap.get("CASTEL"));
			
			Json json = new Json();
			FileHandle text = Gdx.files.internal(GlobalManager.JSON_BUILDINGSPATTERN);
			buildingPatternMap = json.fromJson(ObjectMap.class, text);
			System.out.println(buildingPatternMap.get("first"));
			/**FIN TEST*/

		} catch(Exception e) { 
			System.out.println("### Error DataLoader.java - loadBuildingsPattern()");
			System.out.println(e.getMessage());
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

}
