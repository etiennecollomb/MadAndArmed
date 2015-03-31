package com.geekmecrazy.madandarmed.Game.Scene;

import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Scene.Scene;
import com.geekmecrazy.madandarmed.Game.Element.HQBuilding;

public class Grid extends Entity{
    public float cellSizeX;
    public float cellSizeY;
    public float cols;
    public float rows;
    public Rectangle[][] rects;

    public Grid(int cols,int rows){
        this.cols = (float)cols;
        this.rows = (float)rows;
        rects = new Rectangle[rows][cols];
    }

    public void init(float pX,float pY){
        super.init(pX,pY);
    }

    public void sync(Scene scene){
        this.cellSizeX = scene.getWidth()/cols;
        this.cellSizeY = scene.getHeight()/rows;

        for(int i = 0 ; i < rects.length ; i++){
            for(int j = 0 ; j < rects[i].length ; j++){
                rects[i][j] = new Rectangle();
                rects[i][j].setAlignment(Alignment.NONE);
                rects[i][j].init(j*this.cellSizeX+(this.cellSizeX/2f),i*this.cellSizeY+(this.cellSizeY/2f),this.cellSizeX,this.cellSizeY);
                //Color 
                float r = (float)Math.random();
                rects[i][j].setColor(r,r,r,0.5f);
                this.attachChild(rects[i][j]);
            }
        }
    }

    public void snap(Entity e,float row,float col){
        e.setPosition(row*cellSizeX + cellSizeX/2f - e.getWidth()/2f,col*cellSizeY + cellSizeY/2f - e.getHeight()/2f);
    }

    public float widthFor(int val){
        return val*cellSizeX;
    }

    public float heightFor(int val){
        return val*cellSizeY;
    }
}
