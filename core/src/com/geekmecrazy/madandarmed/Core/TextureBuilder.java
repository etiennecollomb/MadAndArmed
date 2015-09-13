package com.geekmecrazy.madandarmed.Core;


import java.util.ArrayList;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Entity.Alignment;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Etienne on 22/02/2015.
 */
public class TextureBuilder {

    private Pixmap finalPixmap;
    private Texture finalTexture;

    //Global size that must be generated
    private int textureWidth;
    private int textureHeight;

    // ===========================================================
    // Constructors
    // ===========================================================

    public TextureBuilder() {
    }

    public void init(final int pWidth, final int pHeight){

        this.setTextureWidth(pWidth);
        this.setTextureHeight(pHeight);
        finalPixmap = new Pixmap(pWidth, pHeight, Pixmap.Format.RGBA4444);

        //DEBUG
//        Pixmap pixmapBrick = new Pixmap(textureWidth, textureHeight, Pixmap.Format.RGBA8888);
//        pixmapBrick.setColor(0.5f, 0.5f, 0.5f, 0.5f);
//        pixmapBrick.fillRectangle(0, 0, textureWidth, textureHeight);
//        finalPixmap.drawPixmap(pixmapBrick, 0, 0);
//        pixmapBrick.dispose();
        //DEBUG
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public Texture getFinalTexture() {
        return finalTexture;
    }

    public Pixmap getFinalPixmap() {
        return finalPixmap;
    }

    public void setFinalTexture(Texture finalTexture) {
        this.finalTexture = finalTexture;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(int textureWidth) {
        this.textureWidth = textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public void setTextureHeight(int textureHeight) {
        this.textureHeight = textureHeight;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public void drawNewTexture(TextureType pTextureType, final int posX, final int posY){
        Texture texture = new Texture(Gdx.files.internal(pTextureType.getPath()));
        Pixmap pixmapBrick = getPixmapFromTexture(texture);
        this.finalPixmap.drawPixmap(pixmapBrick, posX, posY);
        pixmapBrick.dispose();
    }

    public Pixmap getPixmapFromTexture(Texture pTexture){
        TextureData textureData = pTexture.getTextureData();
        textureData.prepare();
        return textureData.consumePixmap();
    }

    public Texture createTexture(){

        Texture finalTexture = new Texture(finalPixmap, false);
        finalPixmap.dispose();

        return finalTexture;
    }
    
    /** split the generated texture in smaller one
     * based on max texture size allowed by the device
     */
    public ArrayList<Sprite> splitInSprites(){

		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
				
		Pixmap megaPixmap = this.getFinalPixmap();

        //max sub texture size allowed (depend about device capacity and <= of scene size
        int maxTextureWidth = GlobalManager.MAX_TEXTURE_WIDTH;
        int maxTextureHeight = GlobalManager.MAX_TEXTURE_HEIGHT;
        
        //Split texture in smaller one
        int numberColumns = (megaPixmap.getWidth() < maxTextureWidth)? 1 : megaPixmap.getWidth() / maxTextureWidth;
        int numberRows = (megaPixmap.getHeight() < maxTextureHeight)? 1 : megaPixmap.getHeight() / maxTextureHeight;
        
        int posX = 0;
        int posY = megaPixmap.getHeight() - maxTextureHeight;

        for(int i=0; i < numberColumns; i++) {
            for (int j=0; j < numberRows; j++) {

                //Create Texture
                //TODO : calculer la taille restante en puissance de 2 (limite espace memoire)
                this.init(maxTextureWidth, maxTextureHeight);
                this.getFinalPixmap().drawPixmap(megaPixmap, -posX, -posY);
                Texture spriteTexture = this.createTexture();

                //Create Sprite
                Sprite newSprite = new Sprite();
                newSprite.init(i*maxTextureWidth, j*maxTextureHeight, spriteTexture.getWidth(), spriteTexture.getHeight());
                newSprite.setTextureRegion(new TextureRegion(spriteTexture));
                newSprite.setAlignment(Alignment.NONE);
                
                //Debug
                //newSprite.setPosition(newSprite.getX()+1100, newSprite.getY()+1100); //global
                //newSprite.setPosition(newSprite.getX()+i*100, newSprite.getY()+j*100); //local

                sprites.add(newSprite);

                posY = posY - maxTextureHeight;
            }
            posX = posX + maxTextureWidth;
            posY = megaPixmap.getHeight() - maxTextureHeight;
        }

        megaPixmap.dispose();
        
        return sprites;

    }
    
    
    public static void attachSprites(Entity father, ArrayList<Sprite> sprites){

        int size = sprites.size();
        for(int i = 0; i< size; i++) {
            Sprite sprite = sprites.get(i);
            father.attachChild(sprite);
        }
    }


}
