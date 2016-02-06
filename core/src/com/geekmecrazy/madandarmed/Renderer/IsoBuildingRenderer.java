package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Scene.IsoGrid;
import com.geekmecrazy.madandarmed.Game.Tween.ShapeTween;
import com.geekmecrazy.madandarmed.Game.Tween.SpriteTween;
import com.geekmecrazy.madandarmed.Input.SelectedShapeManager;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;


public class IsoBuildingRenderer extends BuildingRenderer {

	private boolean isDark;

	// ===========================================================
	// Constructors
	// ===========================================================

    public IsoBuildingRenderer(){
        super();
    }
    
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    
    @Override
    public void setFocusState(){
        if(this.getState() == ShapeState.FOCUSED){
            unfocus();
            unshine();
        }
        else{
        	focus();
            shine(0.1f, 0.1f, 0.1f);
            isDark = true;
        }
    }
    
	@Override
	public void onLongPressEvent(){
		this.setFocusState();
        Tween
                .to(this, ShapeTween.SCALE, 0.1f)
                .target(1.15f,1.15f)
                .setCallbackTriggers(TweenCallback.END)
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        Tween.to(IsoBuildingRenderer.this,ShapeTween.SCALE,0.1f).target(1.0f,1.0f).start(GlobalManager.getTweenManager());
                    }
                })
                .start(GlobalManager.getTweenManager());
	};
	
	@Override
	public void onTapEvent(){
		this.setFocusState();
        Tween
                .to(this, ShapeTween.SCALE, 0.1f)
                .target(1.15f,1.15f)
                .setCallbackTriggers(TweenCallback.END)
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        Tween.to(IsoBuildingRenderer.this,ShapeTween.SCALE,0.1f).target(1.0f,1.0f).start(GlobalManager.getTweenManager());
                    }
                })
                .start(GlobalManager.getTweenManager());
	};

    @Override
    public void reset(){
    }
    
	@Override
	public void onUpdate(){
		
		Building thisBuilding = ((Building)this.getMilitary());
		thisBuilding.setPos(this.getSceneX(), this.getSceneY()); //Update the position of the model (it can be moved)
        this.setZIndex(GlobalManager.ZINDEXMAXVALUE - (int)this.getY());
        
		super.onUpdate();
	}
    
	// ===========================================================
	// Methods
	// ===========================================================
    
    public void init(final SpriteSheet pSpriteSheet, final Building pBuilding, final IsoGrid isoGrid){
    	this.init(pSpriteSheet, pBuilding);

    	this.setIsoShape(true);
    	this.setIsoGrid(isoGrid);
    	
        isDark = false;
    }

    public void unfocus(){
    	SelectedShapeManager.removeMe(); /** no more need to lock the touch */
    	
        this.setState(ShapeState.UNFOCUSED);
    }
    
    public void unshine(){
        this.setColor(1f,1f,1f,1f);
        GlobalManager.getTweenManager().killAll();
    }
    
    public void focus(){
    	SelectedShapeManager.lockTouch(); /** a button lock the touch */
    	SelectedShapeManager.addMe(this);
    	
        this.setState(ShapeState.FOCUSED);
    }

    public void shine(float r,float g,float b){
        Tween
                .to(this, SpriteTween.COLOR, 0.42f)
                .target(r, g, b)
                .setCallbackTriggers(TweenCallback.END)
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        if (isDark()) {
                            isDark = false;
                            shine(1f, 1f, 1f);
                        } else {
                            isDark = true;
                            shine(0.1f, 0.1f, 0.1f);
                        }
                    }
                })
                .start(GlobalManager.getTweenManager());
    }

    public boolean isDark(){
        return isDark;
    }
    
    /** Release all control done by Touch Event ...etc */
    public void releaseControls(){
    	unfocus();
        unshine();
    }

}
