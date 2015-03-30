package com.geekmecrazy.madandarmed.Tools;

/**
 * Created by ECollomb on 18/03/2015.
 */
public class ResetArray {

    private static int EMPTY_CELL = -1;

    private int currentID;

    private int numberOfCells;

    private int[][] array;

    private int width;

    private int height;

    // ===========================================================
    // Constructors
    // ===========================================================

    public ResetArray(final int pWidth, int pHeight){
        this.array = new int[pWidth][pHeight];
        this.width = pWidth;
        this.height = pHeight;
        this.currentID = 0;
        this.numberOfCells = (pWidth * pHeight);
    }

    public void init(){
        for(int i=0; i<this.width; i++)
            for(int j=0; j<this.height; j++)
                array[i][j]=EMPTY_CELL;
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

    /** cell by cell */
    public void reset(){
        //Modulo : (n % m = n & (m-1))
        this.array[ currentID / this.width ][ currentID & (this.height -1) ] = EMPTY_CELL;

        currentID++;
        if(currentID >= numberOfCells) currentID = 0;
    }

    public boolean isUsed(final int posX, final int posY){
        if(this.array[posX][posY] == currentID)
            return true;
        return false;
    }

    public void setUsed(final int posX, final int posY){
        this.array[posX][posY] = currentID;
    }




}
