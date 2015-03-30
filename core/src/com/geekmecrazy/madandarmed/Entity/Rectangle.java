package com.geekmecrazy.madandarmed.Entity;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Input.MyGestureListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rectangle extends Sprite {

	/** taille max du rectangle precharge
	 * on Scale ensuite, ca evite d avoir a recharger tout le temps
	 */
	public static int TEXTURE_WIDTH = 100;
	public static int TEXTURE_HEIGHT = 100;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Rectangle() {
		super();

		Pixmap pixmap = new Pixmap((int)Rectangle.TEXTURE_WIDTH, (int)Rectangle.TEXTURE_HEIGHT, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fillRectangle(0, 0, (int)Rectangle.TEXTURE_WIDTH, (int)Rectangle.TEXTURE_HEIGHT);
		
		Texture texture = new Texture(pixmap, false);

        //It's the textures responsibility now... get rid of the pixmap
        pixmap.dispose();

		this.setTextureRegion(new TextureRegion(texture));
		
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public void setWidth(final float pWidth){
		super.setWidth(pWidth);
		this.scaleWidth(pWidth);
	}

	@Override
	public void setHeight(final float pHeight){
		super.setHeight(pHeight);
		this.scaleHeight(pHeight);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final float pX, final float pY, final float pWidth, final float pHeight){
		super.init(pX, pY, pWidth, pHeight);
	}

	private void scaleWidth(final float pScaleWidth){
		this.getTextureRegion().setRegionWidth((int) (pScaleWidth)); //Scale (not stretched ! :)
	}
	
	private void scaleHeight(final float pScaleHeight){
		this.getTextureRegion().setRegionHeight((int) (pScaleHeight)); //Scale (not stretched ! :)
	}


}




