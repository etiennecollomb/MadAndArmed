package com.geekmecrazy.madandarmed.XML;

import java.util.Map;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingType;
import com.geekmecrazy.madandarmed.Pattern.BuildingQgPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingQgPattern.BuildingQgID;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepID;
import com.geekmecrazy.madandarmed.Pattern.MapPattern;
import com.geekmecrazy.madandarmed.Utils.XMLDecoder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;


public class DataManager {
	// ===========================================================
	// Data loaded
	// ===========================================================
	private static Map<CreepID, CreepPattern> creepsPattern;
	private static Map<BuildingType, BuildingPattern> buildingsPatern;
	private static Map<BuildingQgID, BuildingQgPattern> buildingsQgPatern;
	//private static Map<TiledPos, BasePattern> basePattern;
	private static MapPattern mapPattern;
	
	
	/** Disable object's instantiation (private constructor) */
	private DataManager(){} 
	
	

	// ===========================================================
	// Loading Method
	// ===========================================================
	
	public static void loadCreepsPattern() { 
		try {
			
		    FileHandle fileHandle = Gdx.files.internal(GlobalManager.XML_CREEP);
		    CreepPatternHandler dataHandler = new CreepPatternHandler(); 
		    XMLDecoder.getInstance().parseXML(fileHandle, dataHandler);
		    
		    creepsPattern = dataHandler.getCreepPattern();
		    
		} catch(Exception e) { 
		    System.out.println("###### ERROR ! DataManager.java - loadCreepsPattern");
		} 
	}
	

	public static void unLoadCreepsPattern() { 
		creepsPattern = null;
	}
	
	public static void loadBuildingsPattern() { 
		try {
		    
		    FileHandle fileHandle = Gdx.files.internal(GlobalManager.XML_BUILDINGPATTERN);
		    BuildingPatternHandler dataHandler = new BuildingPatternHandler(); 
		    XMLDecoder.getInstance().parseXML(fileHandle, dataHandler);
		    
		    buildingsPatern = dataHandler.getBuildingPattern();
		    
		} catch(Exception e) { 
			System.out.println("###### ERROR ! DataManager.java - loadBuildingsPattern");
		}
	}
	
	public static void unLoadBuildingsPattern() { 
		buildingsPatern = null;
	}
		
	public static void loadMapPattern() { 
		try {
		    
			FileHandle fileHandle = Gdx.files.internal(GlobalManager.XML_LEVEL11);
			MapPatternDataHandler dataHandler = new MapPatternDataHandler(); 
		    XMLDecoder.getInstance().parseXML(fileHandle, dataHandler);
		    
		    mapPattern = dataHandler.getMapPattern();
		    
		} catch(Exception e) { 
			System.out.println("###### ERROR ! DataManager.java - loadmapPattern");
		} 
	}
	
	public static void unLoadMapPattern() { 
		mapPattern = null;
	}


	public static void updateCreepsPatternAnimation(){
		for(CreepPattern creepPattern : creepsPattern.values()){
			creepPattern.calculateAnimationListWalk(); 
			creepPattern.calculateAnimationListFire();
		}
	}
	
	public static void updateBuildingsPatternAnimation(){
		for(BuildingPattern building : buildingsPatern.values()){
			building.calculateAnimationListFire();
		}
	}
	
	public static void updateBuildingsQGPatternAnimation(){
		for(BuildingQgPattern building : buildingsQgPatern.values()){
			building.calculateAnimationList();
		}
	}

	public static Map<CreepID, CreepPattern> getCreepsPattern() {
		return creepsPattern;
	}

	public static Map<BuildingType, BuildingPattern> getBuildingsPatern() {
		return buildingsPatern;
	}

	public static MapPattern getMapPattern() {
		return mapPattern;
	}
	
	
	public static Map<BuildingQgID, BuildingQgPattern> getBuildingsQgPatern() {
		return buildingsQgPatern;
	}


//A MIGRER
//	public static Map<TiledPos, BasePattern> getBasePattern() {
//		return basePattern;
//	}


	// ===========================================================
	// Debug Method
	// ===========================================================

	public static void logCreepsPattern(){
		for(CreepPattern creep : creepsPattern.values()){
			System.out.println(creep);
		}
	}
	
	public static void logBuildingsPattern(){
		for(BuildingPattern b : buildingsPatern.values()){
			System.out.println(b);
		}
	}
	
	public static void logBuildingsQgPattern(){
		for(BuildingQgPattern b : buildingsQgPatern.values()){
			System.out.println(b);
		}
	}
	

}
