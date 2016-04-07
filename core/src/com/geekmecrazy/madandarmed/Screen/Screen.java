package com.geekmecrazy.madandarmed.Screen;

import com.geekmecrazy.madandarmed.Entity.HUD.HUD;
import com.geekmecrazy.madandarmed.Entity.ITouchable;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Input.SelectedShapeManager;
import com.geekmecrazy.madandarmed.Renderer.MyTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

public abstract class Screen implements IUpdatable, ITouchable {

	private Array<IUpdatable> mRegisteredUpdatable;

	private MyTiledMapRenderer mTiledGround;

	private Scene mScene;

	private HUD mHUD;
	
	private boolean isLoadFirstTime;

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

	public boolean isLoadFirstTime() {
		return isLoadFirstTime;
	}

	public void setLoadFirstTime(boolean isLoadFirstTime) {
		this.isLoadFirstTime = isLoadFirstTime;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onTouch(){

		if(!SelectedShapeManager.isTouchLocked){
			/** HUD prioritaire */
			this.getHUD().onTouch();
			this.getScene().onTouch();
		}
		/** if touch locked */
		else{
			SelectedShapeManager.doTouch();
		}
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

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Scene pScene){
		this.mTiledGround = null;
		this.setScene(pScene);
		this.getHUD().init();
		this.setLoadFirstTime(true);
	}

	public void registerUpdatable(final IUpdatable pUpdatable){
		this.mRegisteredUpdatable.add(pUpdatable);
	}

	public void unregisterUpdatable(final IUpdatable pUpdatable){
		this.mRegisteredUpdatable.removeValue(pUpdatable, true);
	}

	public abstract void show();
	
    /** Load Screen First Time **/
    protected void loadScreenFirstTime(){}
    
    /** Load Screen **/
    public void loadScreen(){
    	if(this.isLoadFirstTime())
    		this.loadScreenFirstTime();
    }
    
    /** UnLoad Screen **/
    public void unLoadScreen(){}
    
    /** UnLoad Screen Last Time **/
    public void unLoadScreenLastTime(){}


}
