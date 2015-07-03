package com.geekmecrazy.madandarmed.Input;

import java.util.ArrayList;

import com.geekmecrazy.madandarmed.Entity.Shape;

public class SelectedShapeManager {

	/** stocke les shapes qui ont lockés le touch
	 * et qui ont l exclusivitéé du onTouch()
	 * Les shapes doivent gerer eux meme si elles lockent ou pas
	 * Si locked, on appel le doTouch qui s apliquera qu aux selected Shapes
	 */
	public static ArrayList<Shape> selectedHUDShapes = new ArrayList<Shape>();
	public static ArrayList<Shape> selectedSceneShapes = new ArrayList<Shape>();

	public static boolean isTouchLocked = false;
	
	private static boolean isRemoveMe = false;
	
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

	/** isTouchLocked can be set in onTouch() from one shape */
	public static void doTouch(){
		
		int size;
		
		/** HUD */
		TouchData.convertToHud();
		doTouch(selectedHUDShapes);
		
		/** Scene */
		TouchData.convertToScene();
		doTouch(selectedSceneShapes);
		
		if(selectedHUDShapes.size() == 0 && selectedSceneShapes.size() == 0)
			isTouchLocked = false; //no more shape lock touch
			
	}
	
	private static void doTouch(ArrayList<Shape> selectedShapes){
		int size = selectedShapes.size();
		for(int i=size-1; i>=0; i--){
			
			isRemoveMe = false;
			selectedShapes.get(i).onTouch();

			/** si le removeShape() a ete appele dans le onTouch */
			if(isRemoveMe){
				selectedShapes.remove(i);
				System.out.println("###__ REMOOOVE");
			}
		}
	}
	
	public static void addMe(Shape shape){
		if(shape.isHUD())
			selectedHUDShapes.add(shape);
		else
			selectedSceneShapes.add(shape);
	}
	
	/** a shape request delete in doTouch() */
	public static void removeMe(){
		isRemoveMe = true;
	}
	
	public static void lockTouch(){
		isTouchLocked = true;
	}
	
	public static void unlockTouch(){
		isTouchLocked = false;
	}
}
