package com.geekmecrazy.madandarmed.Utils;

/**
 * Created by ECollomb on 03/03/2015.
 */
public class MathTools {

    /** power of 2 >= value
     */
    public static int getNearestPowerOfTwo(int value){
        int powerValue = 1;
        while(value > powerValue){
            powerValue = powerValue+powerValue;
        }
        return powerValue;
    }
}
