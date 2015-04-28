package com.geekmecrazy.madandarmed.Entity.Scene;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.ITouchable;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Game.Scene.CreepManager;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.MyGestureListener;
import com.geekmecrazy.madandarmed.Renderer.HQBuildingRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Scene extends Shape implements ITouchable {

    private static Vector3 VERTICES_TOUCH_TO_SCENE_TMP = new Vector3();

    protected float touchX;
    protected float touchY;

	protected Array<Shape> mRegisteredTouchableShape;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Scene(){
		super();
		this.mRegisteredTouchableShape = new Array<Shape>();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

    @Override
    public void init(final float pWidth, final float pHeight){
        super.init(pWidth/2f, pHeight/2f, pWidth, pHeight);
        this.setAlignment(Alignment.NONE);

        //DEBUG
//        this.setColor(0.5f, 0.5f, 0.5f, 0.5f);
    }

    @Override
    public void onTouch(final MyGestureDetector.GestureType pGestureType, final float pX, final float pY){

        //System.out.println("_________________________________________________");
        //System.out.println("### TOUCH SCREEN X: "+pX+" Y:"+pY);

        this.touchToSceneCoord(pX, pY);

        touchX = VERTICES_TOUCH_TO_SCENE_TMP.x;
        touchY = VERTICES_TOUCH_TO_SCENE_TMP.y;

        //System.out.println("### TOUCH SCENE X: "+X+" Y:"+Y);

        int size = this.mRegisteredTouchableShape.size;
        for(int i=0; i<size; i++){
            Shape shape = this.mRegisteredTouchableShape.get(i);
            if(shape.contains(touchX, touchY)){
                shape.onTouch(pGestureType, touchX, touchY);
            }
        }
    }

    @Override
    public boolean contains(final float pX, final float pY){
        return true;
    }

    // ===========================================================
	// Methods
	// ===========================================================

	public void registerTouchableShape(final Shape pShape){
		this.mRegisteredTouchableShape.add(pShape);
	}

	public void unregisterTouchableShape(final Shape pShape){
		this.mRegisteredTouchableShape.removeValue(pShape, true);
	}

    /** Convert touch coord to scene coord **/
    private void touchToSceneCoord(final float pX, final float pY){
        VERTICES_TOUCH_TO_SCENE_TMP.x = pX;
        VERTICES_TOUCH_TO_SCENE_TMP.y = pY;
        VERTICES_TOUCH_TO_SCENE_TMP.z = 0;
        GlobalManager.camera.unproject(VERTICES_TOUCH_TO_SCENE_TMP);
    }



}
