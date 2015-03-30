package com.geekmecrazy.madandarmed.Entity.HUD;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.ITouchable;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Game.UI.Button;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.MyGestureListener;
import com.geekmecrazy.madandarmed.MadAndArmed;
import com.geekmecrazy.madandarmed.Utils.VirtualViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class HUD extends Shape implements ITouchable {

    private static Vector3 VERTICES_TOUCH_TO_HUD_TMP=new Vector3();

	private Array<Shape> mRegisteredTouchableShape;

	// ===========================================================
	// Constructors
	// ===========================================================

	public HUD(){
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
    public void onTouch(final MyGestureDetector.GestureType pGestureType, final float pX, final float pY){

        this.touchToHUDCoord(pX, pY);

        float X = VERTICES_TOUCH_TO_HUD_TMP.x;
        float Y = VERTICES_TOUCH_TO_HUD_TMP.y;

//        System.out.println("### TOUCH HUD X: "+X+" Y:"+Y);

        int size = this.mRegisteredTouchableShape.size;
        for(int i=0; i<size; i++){
            Shape shape = this.mRegisteredTouchableShape.get(i);
            if(shape.contains(X, Y)){
                shape.onTouch(pGestureType, X, Y);
            }
            else if(shape instanceof Button){
                if(pGestureType == MyGestureDetector.GestureType.PAN && (((Button) shape).isPressed())){
                    ((Button)shape).onRelease(false);
                }
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

	public void init(){
		super.init(GlobalManager.getVvp().world_x/2f, GlobalManager.getVvp().world_y/2f, GlobalManager.getVvp().world_x, GlobalManager.getVvp().world_y);
        this.setAlignment(Alignment.NONE);
	}

	public void registerTouchableShape(final Shape pShape){
		this.mRegisteredTouchableShape.add(pShape);
	}

	public void unregisterTouchableShape(final Shape pShape){
		this.mRegisteredTouchableShape.removeValue(pShape, true);
	}

    /** Convert touch coord to scene coord **/
    private void touchToHUDCoord(final float pX, final float pY){
        VERTICES_TOUCH_TO_HUD_TMP.x = pX;
        VERTICES_TOUCH_TO_HUD_TMP.y = pY;
        VERTICES_TOUCH_TO_HUD_TMP.z = 0;
        GlobalManager.hudCamera.unproject(VERTICES_TOUCH_TO_HUD_TMP);
    }
}
