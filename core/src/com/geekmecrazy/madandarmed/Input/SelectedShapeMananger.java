package com.geekmecrazy.madandarmed.Input;

import java.util.ArrayList;

import com.geekmecrazy.madandarmed.Entity.Shape;

public class SelectedShapeMananger {

	public static boolean isTouchLocked = false;
	
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
