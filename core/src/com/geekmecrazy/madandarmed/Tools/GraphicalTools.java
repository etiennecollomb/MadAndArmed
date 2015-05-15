package com.geekmecrazy.madandarmed.Tools;

import com.badlogic.gdx.graphics.Color;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class GraphicalTools {


	/** nombre d orientations graphiques possible des unités */
	private static final int NB_ORIENTATION = 16;

	/** angle de depart du spritesheet */
	private static final float START_ANGLE = 90f;
	
	protected static float[][][] graphicOrientation; //liste des valeurs cos et sin pour la direction graphique

	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	public static void init(){
		if(graphicOrientation==null)
			setGraphicDirection(NB_ORIENTATION, START_ANGLE);
	}
	
	/**
	 * startAngle is the direction 0
	 * @param nbDirection
	 * @param startAngle
	 */
	public static void setGraphicDirection(int nbDirection, float startAngle){

		nbDirection = nbDirection/4*4; //nbDirection must be divisible by 4 !
		graphicOrientation = new float [nbDirection/2+1][2][3];
		float pas = (float)360/(float)nbDirection;
		int direction=0;

		graphicOrientation[0][0][0]=1f;
		graphicOrientation[0][0][1]=0f;
		graphicOrientation[0][0][2]=direction;

		int i=1;
		for(float angle=pas/2; angle<180f; angle=angle+pas){
			direction=direction+1;
			float radian= (float)(angle*Math.PI/180.0f);
			float x = (float) Math.cos(radian);     
			float y = (float) Math.sin(radian);
			graphicOrientation[i][0][0]=x;
			graphicOrientation[i][0][1]=y;
			graphicOrientation[i][0][2]=direction;
			i++;
		}

		for(i=nbDirection/2; i>=0; i--){			
			graphicOrientation[i][1][0]=graphicOrientation[i][0][0];
			graphicOrientation[i][1][1]=-graphicOrientation[i][0][1];
			graphicOrientation[i][1][2]=direction;
			direction=direction+1;
			if(direction>=nbDirection) direction=0;
		}

		//we "shift" the directions regarding startAngle
		int startdirection = (int)(startAngle/pas);
		for(i=0; i<graphicOrientation.length; i++){
			graphicOrientation[i][0][2]=graphicOrientation[i][0][2]-startdirection;
			if(graphicOrientation[i][0][2]<0)
				graphicOrientation[i][0][2]=graphicOrientation[i][0][2]+nbDirection;
			graphicOrientation[i][1][2]=graphicOrientation[i][1][2]-startdirection;
			if(graphicOrientation[i][1][2]<0)
				graphicOrientation[i][1][2]=graphicOrientation[i][1][2]+nbDirection;
		}

		//PRINT
		/*
		for(i=0; i<nbDirection/2+1; i++){
			System.out.println(graphicOrientation[i][0][0]+"|"+graphicOrientation[i][0][1]+"|"+graphicOrientation[i][0][2]);
		}
		for(i=nbDirection/2; i>=0; i--){
			System.out.println(graphicOrientation[i][1][0]+"|"+graphicOrientation[i][1][1]+"|"+graphicOrientation[i][1][2]);
		}
		 */
	}


	/** give the graphic direction from a vector */
	public static int getGraphicDirection(Vector2d normalizedVector){

		//this.getNormalizedDir() doit toujours etre normalise	!!!!!!
		float x = normalizedVector.getX();
		float y = normalizedVector.getY();
		int size= graphicOrientation.length-1;
		int halfsize= size/2;

		int i;
		if(x>=0){ for(i=0; i<halfsize; i++) if(graphicOrientation[i+1][0][0]<x) break;}
		else{ for(i=halfsize; i<size; i++) if(graphicOrientation[i+1][0][0]<x) break;}

		//trigonometric (y>0)
		//if (y>0) return (int)graphicOrientation[i][0][2];
		//return (int)graphicOrientation[i][1][2];

		//non-trigonometric ie Y-axis inversion (y<0)
		if (y<0) return (int)graphicOrientation[i][0][2];
		return (int)graphicOrientation[i][1][2];
	}

}
