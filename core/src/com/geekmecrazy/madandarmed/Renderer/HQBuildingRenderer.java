package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Assets.Assets;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.OrthoShape;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Tween.ShapeTween;
import com.geekmecrazy.madandarmed.Game.Tween.SpriteTween;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;


public class HQBuildingRenderer extends OrthoShape/* implements IMoveable*/{

    private Sprite icon;
    private Sprite leftArrow;
    private Sprite rightArrow;
    private Sprite bottomArrow;
    private Sprite topArrow;
    public Rectangle groundSquare;

    private boolean isDark;

	// ===========================================================
	// Constructors
	// ===========================================================

    
    public HQBuildingRenderer(){
        super();
        
        icon = new Sprite();
        topArrow = new Sprite();
        leftArrow = new Sprite();
        bottomArrow = new Sprite();
        rightArrow = new Sprite();
        groundSquare = new Rectangle();
        isDark = false;
    }


	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

    @Override
    public void setFocusState(){
        if(this.getState() == OrthoShapeState.FOCUSED){
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
	public void onLongPressEvent(final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		this.setFocusState();
        Tween
                .to(this, ShapeTween.SCALE, 0.1f)
                .target(1.15f,1.15f)
                .setCallbackTriggers(TweenCallback.END)
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        Tween.to(HQBuildingRenderer.this,ShapeTween.SCALE,0.1f).target(1.0f,1.0f).start(GlobalManager.getTweenManager());
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
                        Tween.to(HQBuildingRenderer.this,ShapeTween.SCALE,0.1f).target(1.0f,1.0f).start(GlobalManager.getTweenManager());
                    }
                })
                .start(GlobalManager.getTweenManager());
	};

    @Override
    public void reset(){
        icon.reset();
    }
    
	// ===========================================================
	// Methods
	// ===========================================================
    
    public void init(TextureType pTextureType){
        super.init(0, 0, pTextureType.getWidth(), pTextureType.getHeight());

        float arrowPercentSize = 0.25f;

        icon.init(pTextureType);
        icon.setAlignment(Alignment.CENTER);

        float topOrBottomHeight = arrowPercentSize*icon.getHeight();
        float leftOrRightWidth = arrowPercentSize*icon.getWidth();

        topArrow.init(Assets.greenArrowTop);

        float topOrBottomWidth = topArrow.getWidth()/(topArrow.getHeight()/topOrBottomHeight);

        topArrow.setSize(topOrBottomWidth, topOrBottomHeight);
        topArrow.setVisible(false);
        topArrow.setAlignment(Alignment.CENTER_TOP);

        leftArrow.init(Assets.greenArrowLeft);

        float leftOrRightHeight = leftArrow.getHeight()/(leftArrow.getWidth()/leftOrRightWidth);

        leftArrow.setSize(leftOrRightWidth, leftOrRightHeight);
        leftArrow.setVisible(false);
        leftArrow.setAlignment(Alignment.CENTER_LEFT);

        bottomArrow.init(Assets.greenArrowBottom);
        bottomArrow.setSize(topOrBottomWidth, topOrBottomHeight);
        bottomArrow.setVisible(false);
        bottomArrow.setAlignment(Alignment.CENTER_BOTTOM);

        rightArrow.init(Assets.greenArrowRight);
        rightArrow.setSize(leftOrRightWidth, leftOrRightHeight);
        rightArrow.setVisible(false);
        rightArrow.setAlignment(Alignment.CENTER_RIGHT);

        groundSquare.init(0,0,icon.getWidth(),icon.getHeight());
        groundSquare.setColor(0, 1, 0, 0.5f);
        groundSquare.setVisible(false);
        groundSquare.setAlignment(Alignment.CENTER);

        this.attachChild(groundSquare);
        this.attachChild(icon);
        this.attachChild(leftArrow);
        this.attachChild(topArrow);
        this.attachChild(rightArrow);
        this.attachChild(bottomArrow);
    }

    public void unfocus(){
        GlobalManager.moveable = true;
        this.setState(OrthoShapeState.UNFOCUSED);
        leftArrow.setVisible(false);
        rightArrow.setVisible(false);
        topArrow.setVisible(false);
        bottomArrow.setVisible(false);
        groundSquare.setVisible(false);
        
        this.getGrid().getGridRenderer().setVisible(false);
    }
    
    public void unshine(){
        this.icon.setColor(1f,1f,1f,1f);
        GlobalManager.getTweenManager().killAll();
    }
    
    public void focus(){
    	GlobalManager.moveable = false;
        this.setState(OrthoShapeState.FOCUSED);
        leftArrow.setVisible(true);
        rightArrow.setVisible(true);
        topArrow.setVisible(true);
        bottomArrow.setVisible(true);
        groundSquare.setVisible(true);
        
        this.getGrid().getGridRenderer().setVisible(true);
    }

    public void shine(float r,float g,float b){
        Tween
                .to(this.icon, SpriteTween.COLOR, 0.42f)
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
