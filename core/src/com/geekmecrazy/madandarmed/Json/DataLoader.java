package com.geekmecrazy.madandarmed.Json;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingType;
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

	@SuppressWarnings("unchecked")
	public static void loadBuildingsPattern() { 

//		BuildingPattern bp = new BuildingPattern();
//		bp.setAimAnimationRow(new ArrayList<Integer>());
//		bp.getAimAnimationRow().add(new Integer(2));
//		bp.getAimAnimationRow().add(new Integer(5));
//		bp.getAimAnimationRow().add(new Integer(6));
//		bp.setPrice(300);
//
//		Json json = new Json();
//		json.setElementType(BuildingPattern.class, "aimAnimationRow", Integer.class);
//		System.out.println(json.prettyPrint(bp));
//		String text = json.toJson(bp, BuildingPattern.class);
//		BuildingPattern bp3 = json.fromJson(BuildingPattern.class, text);
		
		Json json = new Json();
		FileHandle text = Gdx.files.internal(GlobalManager.JSON_BUILDINGSPATTERN);
		buildingsPattern = json.fromJson(ObjectMap.class, text);
		System.out.println(buildingsPattern.get(BuildingType.CASTLE.name()));

	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

}
