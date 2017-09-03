package com.geekmecrazy.madandarmed.CoreConfig;

import java.util.HashMap;

/**
 * Created by ECollomb on 03/03/2015.
 */
public class DecorationsType {

    private HashMap<Integer, TextureType> plantsType;

    public DecorationsType(){

        //PLANTS
        plantsType = new HashMap<Integer, TextureType>();

//        plantsType.put(0, TextureType.PLANT_00);
//        plantsType.put(1, TextureType.PLANT_01);
//        plantsType.put(2, TextureType.PLANT_02);
//        plantsType.put(3, TextureType.PLANT_03);
//
//        plantsType.put(4, TextureType.PLANT_00_MEDIUM);
//        plantsType.put(5, TextureType.PLANT_01_MEDIUM);
//        plantsType.put(6, TextureType.PLANT_02_MEDIUM);
//        plantsType.put(7, TextureType.PLANT_03_MEDIUM);

        plantsType.put(0, TextureType.PLANT_04);

    }

    public HashMap<Integer, TextureType> getPlantsType() {
        return plantsType;
    }


}
