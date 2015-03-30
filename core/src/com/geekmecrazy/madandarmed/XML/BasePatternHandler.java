package com.geekmecrazy.madandarmed.XML;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.geekmecrazy.madandarmed.Pattern.BasePattern;
import com.geekmecrazy.madandarmed.Pattern.BasePattern.TiledSkin;
import com.geekmecrazy.madandarmed.Pattern.TiledPos;


public class BasePatternHandler  extends DefaultHandler {
	// ===========================================================
	// Constants
	// ===========================================================
	
	// COMMON
	private static final String BASE 							= "base";
	
	
	// BASE
	private static final String BASE_POSX						= "posX";
	private static final String BASE_POSY						= "posY";
	private static final String BASE_IA							= "ia";
	private static final String BASE_SKIN						= "skin";
	
	// ===========================================================
	// Data Loaded
	// ===========================================================
	private Map<TiledPos, BasePattern> basePattern;
	private BasePattern currentPatern;
	//private String currentElement;
	
	@Override
	public void startDocument() throws SAXException {
		basePattern = new HashMap<TiledPos, BasePattern>();
	}
	
	@Override
	public void startElement(String namespaceURI, String name, String qName, Attributes atts) throws SAXException{
		//currentElement=name;
		if(BASE.equalsIgnoreCase(name)) {
			currentPatern = new BasePattern();
			TiledPos pos = new TiledPos(new Integer(atts.getValue(BASE_POSX)).intValue(), new Integer(atts.getValue(BASE_POSY)).intValue());
			currentPatern.setSkin(TiledSkin.valueOf(atts.getValue(BASE_SKIN)));
			currentPatern.setPos(pos);
			currentPatern.setIa(new Integer(atts.getValue(BASE_IA)).intValue());
			basePattern.put(pos, currentPatern);
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
		String value = new String( ch , start , length );
		if(!value.trim().equals("")) {
		}
	}

	public Map<TiledPos, BasePattern> getBasePattern() {
		return basePattern;
	}

	
}
