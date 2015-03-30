package com.geekmecrazy.madandarmed.Core;


import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
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
        finalPixmap = new Pixmap(pWidth, pHeight, Pixmap.Format.RGBA8888);

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




}
