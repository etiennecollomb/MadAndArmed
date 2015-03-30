package com.geekmecrazy.madandarmed.XML;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.geekmecrazy.madandarmed.Pattern.LevelBuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.MapPattern;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingID;



public class MapPatternDataHandler extends DefaultHandler {
	// ===========================================================
	// Constants
	// ===========================================================
	
	// COMMON
	private static final String PLAYER 						= "Player";
	private static final String IA							= "Ia";
	private static final String BUILDING					= "Building";
	
	// TEAM
	private static final String TEAM_SPAWNPOINT_X			= "spawnPointX";
	private static final String TEAM_SPAWNPOINT_Y			= "spawnPointY";
	
	
	// BUILDING
	private static final String BUILDING_MODELID			= "modelID";
	private static final String BUILDING_LIFE				= "life";
	private static final String BUILDING_POSNODEX			= "posNodeX";
	private static final String BUILDING_POSNODEY			= "posNodeY";
	
	
	// ===========================================================
	// Data Loaded
	// ===========================================================
	private MapPattern mapPattern;
	private List<LevelBuildingPattern> listBuildingPlayer;
	private List<LevelBuildingPattern> listBuildingIA;
	
	private String currentElement;
	private List<LevelBuildingPattern> currentList;
	private LevelBuildingPattern buildingCurrent;

	
	
	@Override
	public void startDocument() throws SAXException {
		mapPattern = new MapPattern();
		listBuildingPlayer = new ArrayList<LevelBuildingPattern>();
		mapPattern.setListBuildingPlayer(listBuildingPlayer);
		listBuildingIA = new ArrayList<LevelBuildingPattern>();
		mapPattern.setListBuildingIA(listBuildingIA);
	}

	@Override
	public void startElement(String namespaceURI, String name, String qName, Attributes atts) throws SAXException{
		currentElement=qName;
		if(PLAYER.equalsIgnoreCase(currentElement)) {
			currentList = listBuildingPlayer;
			mapPattern.setSpawnPointPlayer(new Vector2d());
			mapPattern.getSpawnPointPlayer().set(new Integer(atts.getValue(TEAM_SPAWNPOINT_X)).intValue(),new Integer(atts.getValue(TEAM_SPAWNPOINT_Y)).intValue());
			
		}else if(IA.equalsIgnoreCase(currentElement)) {
			currentList = listBuildingIA;
			mapPattern.setSpawnPointIa(new Vector2d());
			mapPattern.getSpawnPointIa().set(new Integer(atts.getValue(TEAM_SPAWNPOINT_X)).intValue(),new Integer(atts.getValue(TEAM_SPAWNPOINT_Y)).intValue());
		}else if(BUILDING.equalsIgnoreCase(currentElement)) {
			buildingCurrent = new LevelBuildingPattern();
			currentList.add(buildingCurrent);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) {
		String value = new String( ch , start , length );
		if(!value.trim().equals("")) {
			if(BUILDING_MODELID.equalsIgnoreCase(currentElement)) {
				buildingCurrent.setModelID(BuildingID.valueOf(value));
			} else if(BUILDING_LIFE.equalsIgnoreCase(currentElement)) {
				buildingCurrent.setLife((new Integer(value)).intValue());
			} else if(BUILDING_POSNODEX.equalsIgnoreCase(currentElement)) {
				buildingCurrent.setPosNodeX((new Integer(value)).intValue());
			} else if(BUILDING_POSNODEY.equalsIgnoreCase(currentElement)) {
				buildingCurrent.setPosNodeY((new Integer(value)).intValue());
			} 
		}
	}

	public MapPattern getMapPattern() {
		return mapPattern;
	}


}
