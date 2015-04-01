package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Assets.Assets;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Scene.Grid;
import com.geekmecrazy.madandarmed.Game.Tween.ShapeTween;
import com.geekmecrazy.madandarmed.Game.Tween.SpriteTween;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.badlogic.gdx.graphics.Color;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;


public class HQBuilding extends Shape/* implements IMoveable*/{

    private Sprite icon;
    private Sprite leftArrow;
    private Sprite rightArrow;
    private Sprite bottomArrow;
    private Sprite topArrow;
    public Rectangle groundSquare;

    private Color mIconColor;

    private float diffX;
    private float diffY;

    private Grid grid;

    public static enum HQBuildingState {
        FOCUSED,
        UNFOCUSED
    }

    private HQBuildingState state;
    private boolean isDark;

    public HQBuilding(){
        super();
        icon = new Sprite();
        topArrow = new Sprite();
        leftArrow = new Sprite();
        bottomArrow = new Sprite();
        rightArrow = new Sprite();
        groundSquare = new Rectangle();

        state = HQBuildingState.UNFOCUSED;
        isDark = false;
    }

    public void init(TextureType pTextureType){
        super.init(0, 0, pTextureType.getWidth(), pTextureType.getHeight());

        float arrowPercentSize = 0.25f;

        icon.init(pTextureType);
        icon.setAlignment(Alignment.CENTER);

        mIconColor = icon.getColor().cpy();

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

    @Override
    public void setSize(float w,float h){
        super.setSize(w, h);

        float arrowPercentSize = 1f;

        icon.setSize(w,h);
        topArrow.setSize(topArrow.getWidth() * arrowPercentSize, topArrow.getHeight() * arrowPercentSize);
        leftArrow.setSize(leftArrow.getWidth() * arrowPercentSize, leftArrow.getHeight() * arrowPercentSize);
        bottomArrow.setSize(bottomArrow.getWidth() * arrowPercentSize, bottomArrow.getHeight() * arrowPercentSize);
        rightArrow.setSize(rightArrow.getWidth() * arrowPercentSize, rightArrow.getHeight() * arrowPercentSize);
        groundSquare.setSize(icon.getWidth(), icon.getHeight());
    }

    @Override
    public void reset(){
        icon.reset();
    }

    @Override
    public void onTouch(final MyGestureDetector.GestureType pGestureType, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
        switch(pGestureType){
            case TOUCHDOWN:
                //System.out.println("viewportWidth vaut "+GlobalManager.camera.viewportWidth+" et viewportHeight vaut "+GlobalManager.camera.viewportHeight);
                diffX = pTouchAreaLocalX - this.getX();
                diffY = pTouchAreaLocalY - this.getY();
                break;
            case TOUCHUP:
                break;
            case LONGPRESS:
                this.setFocusState();
                Tween
                        .to(this, ShapeTween.SCALE, 0.1f)
                        .target(1.15f,1.15f)
                        .setCallbackTriggers(TweenCallback.END)
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int type, BaseTween<?> source) {
                                Tween.to(HQBuilding.this,ShapeTween.SCALE,0.1f).target(1.0f,1.0f).start(GlobalManager.getTweenManager());
                            }
                        })
                        .start(GlobalManager.getTweenManager());
                break;
            case TAP:
                this.setFocusState();
                Tween
                        .to(this, ShapeTween.SCALE, 0.1f)
                        .target(1.15f,1.15f)
                        .setCallbackTriggers(TweenCallback.END)
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int type, BaseTween<?> source) {
                                Tween.to(HQBuilding.this,ShapeTween.SCALE,0.1f).target(1.0f,1.0f).start(GlobalManager.getTweenManager());
                            }
                        })
                        .start(GlobalManager.getTweenManager());
                break;
            case PAN:
                if(isFocused()){
                    float effectiveViewportWidth = GlobalManager.camera.viewportWidth * GlobalManager.camera.zoom;
                    float effectiveViewportHeight = GlobalManager.camera.viewportHeight * GlobalManager.camera.zoom;

                    this.setPosition(Math.round((pTouchAreaLocalX-diffX)/grid.getCellSizeX())*grid.getCellSizeX(), Math.round((pTouchAreaLocalY-diffY)/grid.getCellSizeY())*grid.getCellSizeY());
                    
                    if(this.getX() < 0){
                        this.setPosition(0,this.getY());
                    }
                    else if(this.getX() + this.getWidth() > GlobalManager.HQ_SCENE_WIDTH){
                        this.setPosition(GlobalManager.HQ_SCENE_WIDTH - this.getWidth(),this.getY());
                    }
                    else if(this.getX() < GlobalManager.camera.position.x - effectiveViewportWidth/2f){
                        GlobalManager.camera.position.x = this.getX() + effectiveViewportWidth/2f;
                    }
                    else if(this.getX() + this.getWidth() > GlobalManager.camera.position.x + effectiveViewportWidth/2f){
                        GlobalManager.camera.position.x = this.getX() + this.getWidth() - effectiveViewportWidth/2f;
                    }

                    if(this.getY() < 0){
                        this.setPosition(this.getX(),0);
                    }
                    else if(this.getY() + this.getHeight() > GlobalManager.HQ_SCENE_HEIGHT){
                        this.setPosition(this.getX(),GlobalManager.HQ_SCENE_HEIGHT - this.getHeight());
                    }
                    else if(this.getY() < (GlobalManager.camera.position.y - effectiveViewportHeight/2f)){
                        GlobalManager.camera.position.y = this.getY() + effectiveViewportHeight/2f;
                    }
                    else if(this.getY() + this.getHeight() > GlobalManager.camera.position.y + effectiveViewportHeight/2f){
                        GlobalManager.camera.position.y = this.getY() + this.getHeight() - effectiveViewportHeight/2f;
                    }
                }
                break;
            default:
                break;
        }
    }


    public void setFocusState(){
        if(state == HQBuildingState.FOCUSED){
            unfocus();
            unshine();
        }
        else{
        	focus();
            shine(0.1f, 0.1f, 0.1f);
            isDark = true;
        }
    }

    public void unfocus(){
        GlobalManager.moveable = true;
        state = HQBuildingState.UNFOCUSED;
        leftArrow.setVisible(false);
        rightArrow.setVisible(false);
        topArrow.setVisible(false);
        bottomArrow.setVisible(false);
        groundSquare.setVisible(false);
        
        this.grid.getGridRenderer().setVisible(false);
    }
    
    public void unshine(){
        this.icon.setColor(1f,1f,1f,1f);
        GlobalManager.getTweenManager().killAll();
    }
    
    public void focus(){
    	GlobalManager.moveable = false;
        state = HQBuildingState.FOCUSED;
        leftArrow.setVisible(true);
        rightArrow.setVisible(true);
        topArrow.setVisible(true);
        bottomArrow.setVisible(true);
        groundSquare.setVisible(true);
        
        this.grid.getGridRenderer().setVisible(true);
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

    public boolean isFocused(){
        return state == HQBuildingState.FOCUSED;
    }

    public boolean isDark(){
        return isDark;
    }

    public void setGrid(Grid g){
        this.grid = g;
    }

}
