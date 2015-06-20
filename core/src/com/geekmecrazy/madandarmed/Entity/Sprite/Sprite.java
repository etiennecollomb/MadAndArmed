package com.geekmecrazy.madandarmed.Entity.Sprite;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Game.UI.Layout;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Utils.VirtualViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Sprite extends Shape {

	private TextureRegion mTextureRegion;

	/** Draw Stuff */
	private float draw_x;
	private float draw_y;
	private float draw_originX;
	private float draw_originY;
	private float draw_width;
	private float draw_height;
	private float draw_scaleX;
	private float draw_scaleY;
	private float draw_rotation;
	private int draw_srcX;
	private int draw_srcY;
	private int draw_srcWidth;
	private int draw_srcHeight;
	private boolean draw_flipX;
	private boolean draw_flipY;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Sprite() {
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public TextureRegion getTextureRegion() {
		return mTextureRegion;
	}

	public void setTextureRegion(final TextureRegion pTextureRegion) {
		this.mTextureRegion = pTextureRegion;
	}

	/** Draw Stuff */

	public float getDraw_x() {
		return draw_x;
	}

	public void setDraw_x(float draw_x) {
		this.draw_x = draw_x;
	}

	public float getDraw_y() {
		return draw_y;
	}

	public void setDraw_y(float draw_y) {
		this.draw_y = draw_y;
	}

	public float getDraw_originX() {
		return draw_originX;
	}

	public void setDraw_originX(float draw_originX) {
		this.draw_originX = draw_originX;
	}

	public float getDraw_originY() {
		return draw_originY;
	}

	public void setDraw_originY(float draw_originY) {
		this.draw_originY = draw_originY;
	}

	public float getDraw_width() {
		return draw_width;
	}

	public void setDraw_width(float draw_width) {
		this.draw_width = draw_width;
	}

	public float getDraw_height() {
		return draw_height;
	}

	public void setDraw_height(float draw_height) {
		this.draw_height = draw_height;
	}

	public float getDraw_scaleX() {
		return draw_scaleX;
	}

	public void setDraw_scaleX(float draw_scaleX) {
		this.draw_scaleX = draw_scaleX;
	}

	public float getDraw_scaleY() {
		return draw_scaleY;
	}

	public void setDraw_scaleY(float draw_scaleY) {
		this.draw_scaleY = draw_scaleY;
	}

	public float getDraw_rotation() {
		return draw_rotation;
	}

	public void setDraw_rotation(float draw_rotation) {
		this.draw_rotation = draw_rotation;
	}

	public int getDraw_srcX() {
		return draw_srcX;
	}

	public void setDraw_srcX(int draw_srcX) {
		this.draw_srcX = draw_srcX;
	}

	public int getDraw_srcY() {
		return draw_srcY;
	}

	public void setDraw_srcY(int draw_srcY) {
		this.draw_srcY = draw_srcY;
	}

	public int getDraw_srcWidth() {
		return draw_srcWidth;
	}

	public void setDraw_srcWidth(int draw_srcWidth) {
		this.draw_srcWidth = draw_srcWidth;
	}

	public int getDraw_srcHeight() {
		return draw_srcHeight;
	}

	public void setDraw_srcHeight(int draw_srcHeight) {
		this.draw_srcHeight = draw_srcHeight;
	}

	public boolean isDraw_flipX() {
		return draw_flipX;
	}

	public void setDraw_flipX(boolean draw_flipX) {
		this.draw_flipX = draw_flipX;
	}

	public boolean isDraw_flipY() {
		return draw_flipY;
	}

	public void setDraw_flipY(boolean draw_flipY) {
		this.draw_flipY = draw_flipY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onDraw(){
		if(this.isVisible()){
			GlobalManager.spriteBatchDraw2(this);
			this.onDrawChildren();
		}
	}

	@Override
	public void reset() {
		super.reset();

		this.setTextureRegion(null);

		/** Draw Stuff */
		this.setDraw_x(0);
		this.setDraw_y(0);

		this.setDraw_originX(0);
		this.setDraw_originY(0);

		this.setDraw_width(0);
		this.setDraw_height(0);

		this.setDraw_scaleX(0);
		this.setDraw_scaleY(0);

		this.setDraw_rotation(0);

		this.setDraw_srcX(0);
		this.setDraw_srcY(0);

		this.setDraw_srcWidth(0);
		this.setDraw_srcHeight(0);

		this.setDraw_flipX(false);
		this.setDraw_flipY(false);

	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		/** update Draw Parameter for onDraw() */
		this.setDraw_x(this.getSceneX());
		this.setDraw_y(this.getSceneY());

		this.setDraw_originX(this.getCenterX());
		this.setDraw_originY(this.getCenterY());

		this.setDraw_width(this.getWidth());
		this.setDraw_height(this.getHeight());

		this.setDraw_scaleX(this.getSceneScaleX());
		this.setDraw_scaleY(this.getSceneScaleY());

		this.setDraw_srcX(this.getTextureRegion().getRegionX());
		this.setDraw_srcY(this.getTextureRegion().getRegionY());

		this.setDraw_srcWidth(this.getTextureRegion().getRegionWidth());
		this.setDraw_srcHeight(this.getTextureRegion().getRegionHeight());

		/** si dans un Layout
		 * on gere les bord coupés
		 */
		if(this.getParent() != null && this.getParent() instanceof Layout){

			this.setVisible(true);

			/** outside the layout */
			if(this.getX()+this.getWidth() < 0 || this.getX() > this.getParent().getWidth()){
				this.setVisible(false);
			}
			/** left border */
			if(this.getX()<0){
				
				float rapport = -this.getX() / this.getWidth();
				this.setDraw_x(this.getSceneX() -this.getX() );
				this.setDraw_srcX((int)(rapport*this.getTextureRegion().getRegionWidth()));
			}
			/** right border */
			else if(this.getX()+this.getWidth() > this.getParent().getWidth()){
				
				float rapport = (this.getParent().getWidth()-this.getX()) / this.getWidth();
				this.setDraw_srcWidth((int)(rapport*this.getTextureRegion().getRegionWidth()));
				this.setDraw_width(rapport*this.getWidth());
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final TextureType pTextureType){
		super.init(0, 0, pTextureType.getWidth(), pTextureType.getHeight());

		Texture spriteTexture = new Texture(Gdx.files.internal(pTextureType.getPath()));
		this.setTextureRegion(new TextureRegion(spriteTexture));

		this.initDraw();

	}

	public void init(final TextureRegion region){
		super.init(0, 0, region.getRegionWidth(), region.getRegionHeight());

		this.setTextureRegion(region);

		this.initDraw();
	}


	/** Draw Stuff */
	private void initDraw(){

		this.setDraw_x(0);
		this.setDraw_y(0);

		this.setDraw_originX(0);
		this.setDraw_originY(0);

		this.setDraw_width(0);
		this.setDraw_height(0);

		this.setDraw_scaleX(0);
		this.setDraw_scaleY(0);

		this.setDraw_rotation(0);

		this.setDraw_srcX(0);
		this.setDraw_srcY(0);

		this.setDraw_srcWidth(this.getTextureRegion().getTexture().getWidth());
		this.setDraw_srcHeight(this.getTextureRegion().getTexture().getHeight());

		this.setDraw_flipX(false);
		this.setDraw_flipY(false);
	}
}




