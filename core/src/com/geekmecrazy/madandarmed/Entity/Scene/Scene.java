package com.geekmecrazy.madandarmed.Entity.Scene;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Quint;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.ITouchable;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Game.Tween.OrthographicCameraTween;
import com.geekmecrazy.madandarmed.Input.SelectedShapeManager;
import com.geekmecrazy.madandarmed.Input.TouchData;
import com.badlogic.gdx.utils.Array;

public class Scene extends Shape implements ITouchable {

	protected Array<Shape> mRegisteredTouchableShape;

	private Tween cameraVelocityTween;

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
	}

	@Override
	public void onTouch(){

		TouchData.convertToScene();

		int size = this.mRegisteredTouchableShape.size;
		for(int i=0; i<size; i++){
			Shape shape = this.mRegisteredTouchableShape.get(i);
			if(shape.contains(TouchData.screenTouchX, TouchData.screenTouchY)){
				shape.onTouch();
				if(SelectedShapeManager.isTouchLocked) /** si locked on ne teste plus le reste */
					break;
			}
		}

		if(!SelectedShapeManager.isTouchLocked) {
			/** if touch not locked, we play event on shape itself */
			super.onTouch();
		}

	}

	/** pan de la camera sur la scene */
	@Override
	public void onPanEvent(){
		if(cameraVelocityTween != null) cameraVelocityTween.kill();
		/** rien de selectionner dans Screen n'empeche le scroll de la camera... */
		GlobalManager.camera.translate(-TouchData.deltaX * GlobalManager.camera.zoom , TouchData.deltaY * GlobalManager.camera.zoom);
	}


	public void onFlingEvent(){
		float velocityRatio = 0.3f;
		float targetX = GlobalManager.camera.position.x - velocityRatio* TouchData.velocityX *GlobalManager.camera.zoom;
		float targetY = GlobalManager.camera.position.y + velocityRatio* TouchData.velocityY *GlobalManager.camera.zoom;

		if(cameraVelocityTween != null) cameraVelocityTween.kill();
		cameraVelocityTween = Tween.to(GlobalManager.camera, OrthographicCameraTween.TRANSLATE, 1f)
				.target(targetX, targetY)
				.ease(Quint.OUT)
				.start(GlobalManager.getTweenManager());
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
		pShape.setHUD(false);
	}

	public void unregisterTouchableShape(final Shape pShape){
		this.mRegisteredTouchableShape.removeValue(pShape, true);
		pShape.setHUD(false);
	}

}
