package com.geekmecrazy.madandarmed.Input;

import com.badlogic.gdx.math.Vector3;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Utils.VirtualViewport;

public class TouchData {

	public static MyGestureDetector.GestureType gestureType;

	/** Position sur l'ecran physique */
	//ONLY USE FOR SAVE DATA
	public static float touchX;
	public static float touchY;

	public static float deltaX;
	public static float deltaY;

	public static float velocityX;
	public static float velocityY;

	/** Position en fonction de la camera */
	//ONLY USE FOR GET DATA
	public static float screenTouchX;
	public static float screenTouchY;

	public static float screenDeltaX;
	public static float screenDeltaY;

	/** private stuff */
    private static Vector3 VERTICES_TOUCH_TO_SCENE_TMP = new Vector3();
    private static Vector3 VERTICES_TOUCH_TO_HUD_TMP=new Vector3();


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
    
	public static void convertToScene(){

        //System.out.println("_________________________________________________");
        //System.out.println("### TOUCH SCREEN X: "+pX+" Y:"+pY);
        touchToSceneCoord(TouchData.touchX, TouchData.touchY);
        TouchData.screenTouchX = VERTICES_TOUCH_TO_SCENE_TMP.x;
        TouchData.screenTouchY = VERTICES_TOUCH_TO_SCENE_TMP.y;
        //System.out.println("### TOUCH SCENE X: "+X+" Y:"+Y);
        
		screenDeltaX = deltaX;
		screenDeltaY = deltaY;
        
	}

	public static void convertToHud(){
		
        //System.out.println("_________________________________________________");
        //System.out.println("### TOUCH SCREEN X: "+pX+" Y:"+pY);
        touchToHUDCoord(TouchData.touchX, TouchData.touchY);
        TouchData.screenTouchX = VERTICES_TOUCH_TO_HUD_TMP.x;
        TouchData.screenTouchY = VERTICES_TOUCH_TO_HUD_TMP.y;
//        System.out.println("### TOUCH HUD X: "+X+" Y:"+Y);
        
        screenDeltaX = VirtualViewport.convertUIWidthToUnit(deltaX * VirtualViewport.device_to_virtual_width);
		screenDeltaY = VirtualViewport.convertUIHeightToUnit(deltaY * VirtualViewport.device_to_virtual_height); 

	}

	/** Convert touch coord to scene coord **/
	private static void touchToSceneCoord(final float pX, final float pY){
		VERTICES_TOUCH_TO_SCENE_TMP.x = pX;
		VERTICES_TOUCH_TO_SCENE_TMP.y = pY;
		VERTICES_TOUCH_TO_SCENE_TMP.z = 0;
		GlobalManager.camera.unproject(VERTICES_TOUCH_TO_SCENE_TMP);
	}

    /** Convert touch coord to hud coord **/
    private static void touchToHUDCoord(final float pX, final float pY){
        VERTICES_TOUCH_TO_HUD_TMP.x = pX;
        VERTICES_TOUCH_TO_HUD_TMP.y = pY;
        VERTICES_TOUCH_TO_HUD_TMP.z = 0;
        GlobalManager.hudCamera.unproject(VERTICES_TOUCH_TO_HUD_TMP);
    }


}
