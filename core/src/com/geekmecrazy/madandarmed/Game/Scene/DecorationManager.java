package com.geekmecrazy.madandarmed.Game.Scene;


import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.DecorationsType;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Sprite.Sprite;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ECollomb on 03/03/2015.
 */
public class DecorationManager {

    public static int NUMBER_OF_DECORATIONS = 40;

    private ArrayList<Sprite> listDecorations;

    public static DecorationsType decorationsType;


    // ===========================================================
    // Constructors
    // ===========================================================

    public DecorationManager(){
        this.listDecorations = new ArrayList<Sprite>();
        this.decorationsType = new DecorationsType();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public void setDecoration(){

        //TODO: appel reset if factory
        listDecorations.clear();

        for(int i=0; i<DecorationManager.NUMBER_OF_DECORATIONS; i++){
            Random ran = new Random();
            int plantNumber = ran.nextInt(this.decorationsType.getPlantsType().size());

            //TODO: factory
            TextureType textureType = this.decorationsType.getPlantsType().get(plantNumber);
            Sprite sprite = new Sprite();
            sprite.init(textureType);

            ran.nextBoolean();
            sprite.setPosition( 100+(GlobalManager.FIGHT_SCENE_WIDTH-200)*ran.nextFloat(), 10+(GlobalManager.FIGHT_SCENE_HEIGHT-100)*ran.nextFloat());

            this.listDecorations.add(sprite);

        }
    }

    public void attachDecoration(Entity pEntity){

        int size = this.listDecorations.size();
        for(int i=0; i<size ; i++){
            pEntity.attachChild( this.listDecorations.get(i) );
        }
    }


}
