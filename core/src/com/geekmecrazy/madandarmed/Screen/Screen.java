package com.geekmecrazy.madandarmed.Screen;

import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.HUD.HUD;
import com.geekmecrazy.madandarmed.Entity.IDrawable;
import com.geekmecrazy.madandarmed.Entity.ITouchable;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Input.MyGestureListener;
import com.geekmecrazy.madandarmed.Renderer.MyTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

public abstract class Screen implements Poolable, IUpdatable, ITouchable {

    private Array<IUpdatable> mRegisteredUpdatable;

    private MyTiledMapRenderer mTiledGround;

	private Scene mScene;

    private HUD mHUD;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Screen(){
		this.setRegisteredUpdatable( new Array<IUpdatable>() );
        this.setHUD( new HUD() );
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

    public Array<IUpdatable> getRegisteredUpdatable() {
        return mRegisteredUpdatable;
    }

    public void setRegisteredUpdatable(Array<IUpdatable> pRegisteredUpdatable) {
        this.mRegisteredUpdatable = pRegisteredUpdatable;
    }

    public MyTiledMapRenderer getTiledGround() {
        return mTiledGround;
    }

    public void setTiledGround(MyTiledMapRenderer pTiledGround) {
        this.mTiledGround = pTiledGround;
    }

	public Scene getScene() {
		return mScene;
	}

	public void setScene(final Scene pScene) {
		this.mScene = pScene;
	}

    public HUD getHUD() { return mHUD; }

    public void setHUD(final HUD pHUD) { this.mHUD = pHUD; }

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

    @Override
    public void onTouch(){
        this.getScene().onTouch();
        this.getHUD().onTouch();
    }

    @Override
    public boolean contains(final float pX, final float pY){
        return true;
    }

	@Override
	public void onUpdate() {

		int size = this.mRegisteredUpdatable.size;
		for(int i=0; i<size; i++)
			this.mRegisteredUpdatable.get(i).onUpdate();

        if(this.mTiledGround != null) this.mTiledGround.onUpdate();
        this.getScene().onUpdate();
        this.getHUD().onUpdate();
	}

    @Override
    public void reset() {
        this.getRegisteredUpdatable().clear();
        this.mTiledGround = null;
        this.mScene = null;
        this.getHUD().reset();
    }

	// ===========================================================
	// Methods
	// ===========================================================

    public void init(final Scene pScene){
        this.mTiledGround = null;
        this.setScene(pScene);
        this.getHUD().init();
    }

	public void registerUpdatable(final IUpdatable pUpdatable){
		this.mRegisteredUpdatable.add(pUpdatable);
	}

	public void unregisterUpdatable(final IUpdatable pUpdatable){
		this.mRegisteredUpdatable.removeValue(pUpdatable, true);
	}

    public abstract void show();


}
