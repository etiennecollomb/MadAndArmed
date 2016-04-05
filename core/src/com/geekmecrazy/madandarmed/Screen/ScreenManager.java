package com.geekmecrazy.madandarmed.Screen;

import com.geekmecrazy.madandarmed.Core.GlobalManager;

import java.util.HashMap;

public class ScreenManager {
	
	private static Screen currentScreen;
		
	// ===========================================================
	// Constructors
	// ===========================================================
	
	/**  Disable object's instantiation (private constructor) */
	private ScreenManager(){}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public static Screen getCurrentScreen() {
		return currentScreen;
	}

	public static void setCurrentScreen(final Screen pCurrentScreen) {
		currentScreen = pCurrentScreen;
        GlobalManager.initCameras();
        currentScreen.show();
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static void init(){
		currentScreen = null;
	}


	
}
