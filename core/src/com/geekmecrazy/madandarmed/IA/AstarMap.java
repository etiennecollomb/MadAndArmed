

package com.geekmecrazy.madandarmed.IA;


import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Scene.FightCreepManager;
import com.geekmecrazy.madandarmed.Tools.ResetArray;

public class AstarMap {

    public static int EMPTY_VALUE = -1000;
    public static int WALL_VALUE = -1001;


	//Taille de la map en SmallNode
	public static int mapWidthInSmallNodes;
	public static int mapHeightInSmallNodes;

	//Grille de nodes
	public static Node[][] nodes;
    public static ResetArray usedNodes;

	//f Max de la map +1 :)
	public static int fMax;

	/**********************************************************/

	// disable object's instanciation (private constructor)
	private AstarMap(){}

	public static void init(int mapWidthInBigNodes_, int mapHeightInBigNodes_) {

		mapWidthInSmallNodes = mapWidthInBigNodes_*3;
		mapHeightInSmallNodes = mapHeightInBigNodes_*3;

		nodes = new Node[mapWidthInSmallNodes][mapHeightInSmallNodes];
        usedNodes = new ResetArray(mapWidthInSmallNodes, mapHeightInSmallNodes);
        usedNodes.init();

		fMax=mapWidthInSmallNodes*mapHeightInSmallNodes*+1;

		//on cree toutes les smallNodes
		for(int i=0; i<mapWidthInSmallNodes; i++)
			for(int j=0; j<mapHeightInSmallNodes; j++) {
                nodes[i][j] = new Node(i, j);
                nodes[i][j].init(fMax, EMPTY_VALUE);

                //init des cost
                for(int k=0; k<=2; k++)
                    for(int l=0; l<=2; l++) {
                        setNeighborCost(k, l, nodes[i][j]);
                    }

            }

	}


	/**********************************************************/

	/****************************/
	//CONSTRUCTION DE LA MAP
	//selon les zones definies
	/****************************/
	public static void resetMap(Military pMilitary){


//		//ie : On Reset la map
//		for(int i=0; i<mapWidthInSmallNodes; i++)
//			for(int j=0; j<mapHeightInSmallNodes; j++){
//				nodes[i][j].init(fMax, 0); //set a vide
//			}

        usedNodes.reset();

	}

	public static Node getNode(int x_, int y_){
		return nodes[x_][y_];
	}

	public static Node[][] getNodes() {
		return nodes;
	}



    /****************************/
    // CALCUL DES COUTS ASTAR
    // d'une node vers sa voisine
    /****************************/
    // (neighborX, neighborY) est une des 8 voisines de (currentNode_X, currentNode_Y)
    // 0 <= currentNode_X <= 2  && 0 <= currentNode_Y <= 2
    // Echelle : cout d'une smallNode vers smallNode sur un des 4 cotes adjacents = 1
    private static void setNeighborCost(int neighborNode_X, int neighborNode_Y,	Node currentNode){

        if(neighborNode_X==1 || neighborNode_Y==1)
            currentNode.setNeighborhoodNodesCost(neighborNode_X, neighborNode_Y, 1f); //On est sur les cotes
        else
            currentNode.setNeighborhoodNodesCost(neighborNode_X, neighborNode_Y, 1.4142135f); //On est sur les angles
    }

}



