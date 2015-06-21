package com.geekmecrazy.madandarmed.Utils;

import com.badlogic.gdx.Gdx;

public class VirtualViewport {

	public static int device_screen_width = Gdx.graphics.getWidth();
	public static int device_screen_height = Gdx.graphics.getHeight();
	
	public static float ui_virtual_width = 800;
	public static float ui_virtual_height = 480;
	
	public static float world_x = 15;
	public static float world_y = 10;
	
	public static float unit_scale_x;
	public static float unit_scale_y;
	
	public static float unit_ui_scale_x;
	public static float unit_ui_scale_y;
	
	public static float device_to_virtual_width = ui_virtual_width / device_screen_width;
	public static float device_to_virtual_height = ui_virtual_height / device_screen_height;
	
	public VirtualViewport(float screenWidth,float screenHeight){
		unit_scale_x = screenWidth/world_x;
		world_y = screenHeight/unit_scale_x;
		unit_scale_y = screenHeight/world_y;
		
		unit_ui_scale_x = ui_virtual_width/world_x;
		ui_virtual_height = (ui_virtual_width*(screenHeight/screenWidth));
		unit_ui_scale_y = unit_ui_scale_x;
    }
	
	//pixel to Scene
	public static float convertWorldWidthToUnit(float pixelW){
		return pixelW/unit_scale_x;
	}
	
	public static float convertWorldHeightToUnit(float pixelH){
		return pixelH/unit_scale_y;
	}
	
	//Scene to pixel
	public static float convertWorldWidthToPixel(float unitW){
		return unitW*unit_scale_x;
	}
	
	public static float convertWorldHeightToPixel(float unitH){
		return unitH*unit_scale_y;
	}
	
	//pixel to HUD
	public static float convertUIWidthToUnit(float pixelW){
		return pixelW/unit_ui_scale_x;
	}
	
	public static float convertUIHeightToUnit(float pixelH){
		return pixelH/unit_ui_scale_y;
	}
	
	//HUD to pixel
	public static float convertUIWidthToPixel(float unitW){
		return unitW*unit_ui_scale_x;
	}
	
	public static float convertUIHeightToPixel(float unitH){
		return unitH*unit_ui_scale_y;
	}
	
}
