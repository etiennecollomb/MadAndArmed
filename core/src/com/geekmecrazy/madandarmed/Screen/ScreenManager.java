package com.geekmecrazy.madandarmed.Screen;

import com.geekmecrazy.madandarmed.Core.GlobalManager;

import java.util.HashMap;

public class ScreenManager {
	
	private static Screen mCurrentScreen;
		
	// ===========================================================
	// Constructors
	// ===========================================================
	
	/**  Disable object's instantiation (private constructor) */
	private ScreenManager(){}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public static Screen getCurrentScreen() {
		return mCurrentScreen;
	}

	public static void setCurrentScreen(final Screen pCurrentScreen) {
		mCurrentScreen = pCurrentScreen;
        GlobalManager.initCameras();
        mCurrentScreen.show();
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static void init(){
		mCurrentScreen = null;
	}


	
}
