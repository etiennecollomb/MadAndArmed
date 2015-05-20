package com.geekmecrazy.madandarmed.Json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern2;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern2.BuildingType;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponType;

public class DataLoader {

	/**TEST*/
	static ObjectMap<String, BuildingPattern2> buildingPatternMap = new ObjectMap<String, BuildingPattern2>();
	/**FIN TEST*/



	private static ObjectMap<BuildingType, BuildingPattern2> buildingsPattern;
	private static ObjectMap<WeaponType, WeaponPattern> weaponsPattern;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	@SuppressWarnings("unchecked")
	public static void loadBuildingsPattern() { 

			/** TEST */
//			Json json = new Json();
//			FileHandle text = Gdx.files.internal(GlobalManager.JSON_BUILDINGSPATTERN);
//			buildingPatternMap = json.fromJson(ObjectMap.class, text);
//			System.out.println(buildingPatternMap.get("CASTEL"));
			
			Json json = new Json();
			
			/*
			BuildingPattern2 bp2 = new BuildingPattern2();
			bp2.name = "t1";
			bp2.setBuildingType(BuildingType.CASTLE);
			System.out.println(json.prettyPrint(bp2));
			String text = json.toJson(bp2, Object.class);
			BuildingPattern2 bp3 = json.fromJson(BuildingPattern2.class, text);
			*/
			
			FileHandle text = Gdx.files.internal(GlobalManager.JSON_BUILDINGSPATTERN);
			buildingPatternMap = json.fromJson(ObjectMap.class, text);
			System.out.println(buildingPatternMap.get("first"));
			/**FIN TEST*/

	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

}
