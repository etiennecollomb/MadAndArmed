package com.geekmecrazy.madandarmed.Game.UI;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Tween.ButtonTween;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.SelectedShapeManager;
import com.geekmecrazy.madandarmed.Input.TouchData;

import aurelienribon.tweenengine.Tween;

public class Button extends Shape{

    public static enum ButtonState {
        PRESSED,
        RELEASED
    }

    private boolean isDisabled;

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

    public void disable(){
        this.isDisabled = true;
    }

    public void enable(){
        this.isDisabled = false;
    }

    public boolean isPressed(){
        return this.buttonState == ButtonState.PRESSED;
    }

    public boolean isDisabled(){
        return this.isDisabled;
    }

    public void setAction(IAction action){
        this.action = action;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void setSize(float w,float h){
        super.setSize(w,h);

        int size = this.getChildren().size;
        for(int i=0; i<size; i++)
            this.getChildren().get(i).setSize(w,h);
    }

    @Override
    public void onTouchDownEvent(){
    	System.out.println("####TOUCHDOWN !!!");
    	SelectedShapeManager.lockTouch(); /** a button lock the touch */
    	SelectedShapeManager.addMe(this);
    	
    	if(!this.isPressed() && !this.isDisabled()) {
            this.disable();
            this.onPress();
        }
    }
    
    @Override
    public void onTouchUpEvent(){
    	if(this.isPressed())
            this.onRelease(true);
    	SelectedShapeManager.removeMe(); /** no more need to lock the touch */
    }
    
    @Override
    public void onPanEvent(){
    	System.out.println("####PAN !!!");
    	/** outside the button */
    	if(!this.contains(TouchData.screenTouchX, TouchData.screenTouchY)){
    		if(this.isPressed())
                this.onRelease(false);
        	SelectedShapeManager.removeMe(); /** no more need to lock the touch */
    	}
    		
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void init(final float pX, final float pY){
        super.init(pX, pY);

        this.buttonState = ButtonState.RELEASED;
        this.isDisabled = false;
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

    public void onRelease(final boolean doAction){
        Button.this.enable();
        if (doAction) {
            if (action != null) {
                action.execute();
            }
        }
        this.buttonState = ButtonState.RELEASED;
        Tween.to(this, ButtonTween.SCALE, 0.11f).target(1f, 1f)
        .start(GlobalManager.getTweenManager());
    }

    public void onPress(){
        this.buttonState = ButtonState.PRESSED;
        Tween.to(this, ButtonTween.SCALE, 0.11f).target(1.12f, 1.12f).start(GlobalManager.getTweenManager());
    }


}
