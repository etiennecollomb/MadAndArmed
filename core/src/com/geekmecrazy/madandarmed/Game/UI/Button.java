package com.geekmecrazy.madandarmed.Game.UI;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Tween.ButtonTween;
import com.geekmecrazy.madandarmed.Input.SelectedShapeManager;
import com.geekmecrazy.madandarmed.Input.TouchData;

import aurelienribon.tweenengine.Tween;

public class Button extends Shape{

    public static enum ButtonState {
        PRESSED,
        RELEASED
    }

    private ButtonState buttonState;

    private IAction action = null;

    // ===========================================================
    // Constructors
    // ===========================================================

    public Button() {
        super();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public boolean isPressed(){
        return this.buttonState == ButtonState.PRESSED;
    }

    public void setAction(IAction action){
        this.action = action;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    //TODO: c'est du specifique sur juste une couche d enfant
    @Override
    public void setSize(float w,float h){
        super.setSize(w,h);

        int size = this.getChildren().size;
        for(int i=0; i<size; i++)
            this.getChildren().get(i).setSize(w,h);
    }

    @Override
    public void onTouchDownEvent(){
    	SelectedShapeManager.lockTouch(); /** a button lock the touch */
    	SelectedShapeManager.addMe(this);
    	
    	if(!this.isPressed()) {
            this.onPress();
        }
    }
    
    @Override
    public void onTouchUpEvent(){
    	if(this.isPressed())
            this.onRelease(true);
    	SelectedShapeManager.removeMe(); /** no more need to lock the touch */
    	this.tweenRegress();
    }
    
    @Override
    public void onPanEvent(){
    	/** outside the button */
    	if(!this.contains(TouchData.screenTouchX, TouchData.screenTouchY)){
    		if(this.isPressed())
                this.onRelease(false);
        	SelectedShapeManager.removeMe(); /** no more need to lock the touch */
        	this.tweenRegress();
        	this.tweenRegress(); //TODO : ?? 2 fois car sinon le button reste figé parfois en mode "expand"
    	}
    		
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void init(final float pX, final float pY){
        super.init(pX, pY);

        this.buttonState = ButtonState.RELEASED;
        this.setScalable(true);
    }

    public void init(final float pX, final float pY, final Shape pShape){

        this.init(pX, pY);
        this.attachChild(pShape, Entity.Alignment.CENTER);
    }

    public void init(final float pX, final float pY, final TextureType pTextureType){
        this.init(pX, pY);

        Sprite sprite = new Sprite();
        sprite.init(pTextureType);
        this.attachChild(sprite, Entity.Alignment.CENTER);
    }
    
    private void onPress(){
        this.buttonState = ButtonState.PRESSED;
        this.tweenExtend();
    }

    private void onRelease(final boolean doAction){
        if (doAction) {
            if (action != null) {
                action.execute();
            }
        }
        this.buttonState = ButtonState.RELEASED;
    }
    
    private void tweenExtend(){
    	Tween.to(this, ButtonTween.SCALE, 0.11f).target(1.12f, 1.12f).start(GlobalManager.getTweenManager());
    }
    
    private void tweenRegress(){
    	Tween.to(this, ButtonTween.SCALE, 0.11f).target(1f, 1f).start(GlobalManager.getTweenManager());
    }

    

}
