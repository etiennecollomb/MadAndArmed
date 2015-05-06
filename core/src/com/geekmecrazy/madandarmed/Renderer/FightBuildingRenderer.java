package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.IsoShape;
import com.geekmecrazy.madandarmed.Entity.Sprite.AnimatedSprite;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Scene.IsoGrid;
import com.geekmecrazy.madandarmed.Game.Tween.ShapeTween;
import com.geekmecrazy.madandarmed.Game.Tween.SpriteTween;
import com.geekmecrazy.madandarmed.IA.IsoMapState.Type;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;


public class FightBuildingRenderer extends IsoShape/* implements IMoveable*/{

    private AnimatedSprite buildingSprite;
    
    private boolean isDark;

	// ===========================================================
	// Constructors
	// ===========================================================

    
    public FightBuildingRenderer(){
        super();
        
        buildingSprite = new AnimatedSprite();
    }


	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    
    @Override
    public void setFocusState(){
        if(this.getState() == IsoShapeState.FOCUSED){
            unfocus();
            unshine();
            /**Put on gridMapState */
            this.getGrid().getIsoMapState().add(Type.BARRICADE, this.getGridPosX(), this.getGridPosY());
        }
        else{
        	focus();
            shine(0.1f, 0.1f, 0.1f);
            isDark = true;
            /**OFF of gridMapState */
            this.getGrid().getIsoMapState().remove(this.getGridPosX(), this.getGridPosY());
        }
    }

	@Override
	public void onLongPressEvent(final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		this.setFocusState();
        Tween
                .to(this, ShapeTween.SCALE, 0.1f)
                .target(1.15f,1.15f)
                .setCallbackTriggers(TweenCallback.END)
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        Tween.to(FightBuildingRenderer.this,ShapeTween.SCALE,0.1f).target(1.0f,1.0f).start(GlobalManager.getTweenManager());
                    }
                })
                .start(GlobalManager.getTweenManager());
	};
	
	@Override
	public void onTapEvent(final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		this.setFocusState();
        Tween
                .to(this, ShapeTween.SCALE, 0.1f)
                .target(1.15f,1.15f)
                .setCallbackTriggers(TweenCallback.END)
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        Tween.to(FightBuildingRenderer.this,ShapeTween.SCALE,0.1f).target(1.0f,1.0f).start(GlobalManager.getTweenManager());
                    }
                })
                .start(GlobalManager.getTweenManager());
	};


    @Override
    public void reset(){
    	buildingSprite.reset();
    }
    
	// ===========================================================
	// Methods
	// ===========================================================
    
    public void init(final SpriteSheet pSpriteSheet, final IsoGrid grid){
        super.init(0, 0, pSpriteSheet.getFrameSize(0, 0), pSpriteSheet.getFrameSize(0, 0), grid);

        buildingSprite.init(pSpriteSheet, 0, 0);
        buildingSprite.setAlignment(Alignment.CENTER);
        this.attachChild(buildingSprite);
        
        isDark = false;
    }

    public void unfocus(){
        GlobalManager.moveable = true;
        this.setState(IsoShapeState.UNFOCUSED);
        
        //this.getGrid().getIsoGridRenderer().setVisible(false);
    }
    
    public void unshine(){
        this.buildingSprite.setColor(1f,1f,1f,1f);
        GlobalManager.getTweenManager().killAll();
    }
    
    public void focus(){
    	GlobalManager.moveable = false;
        this.setState(IsoShapeState.FOCUSED);
        
        //this.getGrid().getIsoGridRenderer().setVisible(true);
    }

    public void shine(float r,float g,float b){
        Tween
                .to(this.buildingSprite, SpriteTween.COLOR, 0.42f)
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

}
