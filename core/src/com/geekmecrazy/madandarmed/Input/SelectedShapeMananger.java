package com.geekmecrazy.madandarmed.Input;

import java.util.ArrayList;

import com.geekmecrazy.madandarmed.Entity.Shape;

public class SelectedShapeMananger {

	public static boolean isTouchLocked = false;
	
	/** stocke les shapes qui ont lock�s le touch
	 * et qui ont l exclusivit�� du onTouch()
	 */
	public static ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	
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

	public static void doTouch(){
		int size = selectedShapes.size();
		for(int i=0; i<size; i++)
			selectedShapes.get(i).onTouch();
	}
	
	public static void lockTouch(){
		isTouchLocked = true;
	}
	
	public static void cleanSelectedShape(){
		isTouchLocked = false;
		selectedShapes.clear();
	}
}
