//TODO : tester si gain de temps en settant les voisins reciproques pour pas les recalculers sur la node voisine
//Setter donc un voisin et son inverse en une fois! et checker si voisin est a null pour le calculer ou pas
//
//ERREUR IDENTIFIEE : Getpath nullpointeur : fils=fils.getSon();
// -> cas arrive quand on a debutnode == finnode
//en particulier qd maintarget detruire donc (0,0) et spawn des creeps eux meme en (0,0)

package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.Vehicle;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.UnitSize;

import java.util.ArrayList;
import java.util.HashSet;


public class GlobalAstar {

	private static ArrayList<Node> ouverte= new ArrayList<Node>();
	private static HashSet<Node> ferme = new HashSet<Node>();

	//variables globales de calcul (reset a chaque getPath())
	private static Node debut=null;
	private static Node fin=null;
	private static Vehicle vehicle=null;

	//smallNodes
	private static int[][][] corner_smallNodes;
	private static int[][][] side_smallNodes;

	//Node speciale "mur"
	private static Node wallNode = new Node(-1, -1);

	/******************************/
	/**********/
	//TYPE UNIT
	/**********/

	//1x1 UNIT
	private static int[][][] corner_smallNodes_1_1;
	private static int[][][] side_smallNodes_1_1;

	//3x3 UNIT
	private static int[][][] corner_smallNodes_3_3;
	private static int[][][] side_smallNodes_3_3;

	//5x5 UNIT
	private static int[][][] corner_smallNodes_5_5;
	private static int[][][] side_smallNodes_5_5;


	/**********************************************************/

	// disable object's instanciation (private constructor)
	private GlobalAstar(){}

	public static void init() {

		debut=null; fin=null; vehicle=null;
		wallNode.setValue(AstarMap.WALL_VALUE);

		//1x1 UNIT
		corner_smallNodes_1_1=getCornerArray(1);
		side_smallNodes_1_1=getSideArray(1);

		//3x3 UNIT
		corner_smallNodes_3_3=getCornerArray(3);
		side_smallNodes_3_3=getSideArray(3);

		//5x5 UNIT
		corner_smallNodes_5_5=getCornerArray(5);
		side_smallNodes_5_5=getSideArray(5);

	}


	/**********************************************************/

	private static boolean setPath(){

		ouverte.clear();
		ferme.clear();	

		Node courante=debut;
		Node memoire=null;

		boolean isToEnd = true;

        //time limit of computation for astar
		long computingTime = System.currentTimeMillis();
		long maxComputingTime = 2; //en ms

		while (courante != fin){			
			if(!ferme.contains(courante)){	

				ferme.add(courante);

				//Les voisines
				setNeighbors(courante);
				for(int m=0; m<=2; m++)
					for(int n=0; n<=2; n++){
						if(m==1 && n==1) continue; //Pas lui meme
						Node neighbor = courante.getNeighborhoodNodes(m, n);
						//On ajoute le voisin si c'est pas un mur
						//Note: neighbor!=null est-il de trop? a checker car pas de bug sans ca...
						if(neighbor!=null && neighbor!=wallNode){
							ajoutOuverte(courante, neighbor, m, n);
						}
					}

				memoire=courante;
			}

			ouverte.remove(courante);

			//Pas de chemin possible
			//Ou temps de calcul depass�....
			if (ouverte.isEmpty() || System.currentTimeMillis()-computingTime >= maxComputingTime ){
				memoire=getMinH(ferme); //on recupere le chemin le plus court quand meme
				isToEnd = false;
				break;
			}
			else courante=getMinF(ouverte);
		}

		fin.setFather(memoire);

		return isToEnd;

	}


	/**********************************************************/

	private static void ajoutOuverte(Node courante, Node adjacente, int neighborX, int neighborY){

		if(!ferme.contains(adjacente)){
			float g=courante.getG() + courante.getNeighborhoodNodesCost(neighborX,neighborY);
			float h=(Math.abs(adjacente.getX()-fin.getX())+Math.abs(adjacente.getY()-fin.getY()));
			float f=g+h;

			if(f > adjacente.getF()){
				ouverte.remove(courante);
				return;
			}
			else{
				adjacente.setG(g);
				adjacente.setH(h);
				adjacente.setF(f);
				adjacente.setFather(courante);
				ouverte.add(adjacente);
			}
		}
	}


	/**********************************************************/

	private static Node getMinF(ArrayList<Node> list){
		Node min=list.get(0);
		for(Node node_ : list)
			min=(min.getF()<node_.getF())?min:node_;
		return min;
	}


	/**********************************************************/

	private static Node getMinH(HashSet<Node> list){
		Node min=null;
		for(Node node_ : list)
			if(min==null) min=node_;
			else min=(min.getH()<node_.getH())?min:node_;
		return min;
	}


	/**********************************************************/

	/****************************/
	//DEBUT DE TOUTE CHOSE
	/****************************/
	public static void getPath(Node debutNode, Node finNode, ArrayList<Node> path_, Vehicle v){

		boolean isToEnd = true;

		path_.clear();

		//on init les variables globales de calcul
		debut = debutNode;
		fin = finNode;
		vehicle = v;
		//BIG
		if(vehicle.getPattern().getUnitSize() == UnitSize.BIG){
			corner_smallNodes=corner_smallNodes_5_5;
			side_smallNodes=side_smallNodes_5_5;
		}
		//MEDIUM
		else if(vehicle.getPattern().getUnitSize() == UnitSize.MEDIUM){
			corner_smallNodes=corner_smallNodes_3_3;
			side_smallNodes=side_smallNodes_3_3;
		}
		//SMALL
		else{
			corner_smallNodes=corner_smallNodes_1_1;
			side_smallNodes=side_smallNodes_1_1;
		}

		//calcul du path general
		debut.setH(Math.abs(debut.getX()-fin.getX())+Math.abs(debut.getY()-fin.getY()));
		isToEnd = setPath();

		Node fils = fin;
		while(fils != debut){
			Node parent=fils.getFather();
			parent.setSon(fils);
			fils=parent;
		}
		
		fils = debut.getSon(); //On s en branle du debut on est dessus!
		while(fils != null && fils != fin){ //fils != null : si y a pas de chemin trouve
			path_.add(fils);
			fils=fils.getSon();
		}

		//On add la fin QUE si le chemin est alle a la fin
		if(isToEnd){
			path_.add(fin);
		}

	}


	/**********************************************************/

	/****************************/
	//CALCUL DES VOISINS
	//Set le voisinage selon Astar d'une node donnee
	/****************************/
	//REPERE CARTESIEN (0,0)=bas-gauche
	private static void setNeighbors(Node mainNode){
			setNeighborSmallNodeMove(mainNode, mainNode.getX(), mainNode.getY(), corner_smallNodes);
			setNeighborSmallNodeMove(mainNode, mainNode.getX(), mainNode.getY(), side_smallNodes);
	}


	/**********************************************************/

	/****************************/
	// CALCUL DE LA POSSIBILITE DE DEPLACEMENT
	// d'une direction donee
	/****************************/
	//le array sidesNeighborNodes nous donne les coordonnees a checker des futures nodes voisines
	//qui seront occupees pour un deplacement dans une direction donnee (dir_X, dir_Y)
	//on itere sur chacune de ces coordonees pour savoir si c'est OK ou pas
	//si OK, on set le pointeur vers le voisin, sinon vers un wallNode

	//smallNode vers smallNodes voisines
	private static void setNeighborSmallNodeMove(Node currentNode,
			int currentNode_X, int currentNode_Y,
            int[][][] sidesNeighborNodes){


		int decallage_X, decallage_Y; //coordonnees relative des neighborNodes checkees pour un cote
		int dir_X, dir_Y; //direction d'un des cotes qui est checkee
		Node neighborNode; //un des 8 voisins immediat calcule dans une direction donnee
		boolean isValid; //flag pour checker les sidesNeighborNodes
		int sides_nodes_size = sidesNeighborNodes[0].length; //nombre de neighborNodes a tester pour chaque cote

		for(int i=0; i<4; i++){ ////4 cotes a checker uniquement, que ce soit type bord ou corner
			isValid = true;
			//on itere sur toutes les futures nodes occupees pour un cote
			for(int j=1; j<sides_nodes_size; j++){ //j=1 : le premier etant pris pour la direction
				decallage_X = sidesNeighborNodes[i][j][0];
				decallage_Y = sidesNeighborNodes[i][j][1];
				if(!isValidSmallNode(decallage_X, decallage_Y, currentNode_X, currentNode_Y)){
					isValid = false;
					break;
				}
			}

			//on set la node voisine si le deplacement est ok sinon on set en wallNode
			dir_X = sidesNeighborNodes[i][0][0];
			dir_Y = sidesNeighborNodes[i][0][1];
			if(isValid){
				//on pointe sur la node voisine
				neighborNode = AstarMap.nodes[currentNode_X+dir_X][currentNode_Y+dir_Y];  //get Neighbor
			}
			else{
				neighborNode = wallNode;
			}
			currentNode.setNeighborhoodNodes(dir_X+1, dir_Y+1, neighborNode);
		}
	}


	/**********************************************************/

	/****************************/
	// VALIDITE D'UNE SMALLNODE
	// not wall && not border
	/****************************/
	//une des cases voisines definit pas (decallage_X, decallage_Y)
	//par rapport a (smallNode_X, smallNode_Y)
	private static boolean isValidSmallNode(int decallage_X, int  decallage_Y, int smallNode_X, int smallNode_Y){

		int neihborSmallNode_X= smallNode_X+decallage_X;
		int neihborSmallNode_Y= smallNode_Y+decallage_Y;

		if(!isSmallBoderWall(neihborSmallNode_X, neihborSmallNode_Y)
                &&
                ( ! vehicle.getMyTeam().getStateMap().getCurrentZoneAPositionMap().isUsed(neihborSmallNode_X, neihborSmallNode_Y)
                    && vehicle.getMyTeam().getStateMap().getZoneBPositionMap()[neihborSmallNode_X][neihborSmallNode_Y]<=0 )
                )

			return true;
		return false;
	}


	/**********************************************************/

	/****************************/
	// TEST DE LIMITE DE MAP
	// pour smallNode
	/****************************/
	private static boolean isSmallBoderWall(int smallNode_X, int smallNode_Y){
		if(smallNode_X<0 || smallNode_Y<0 || smallNode_X>= AstarMap.mapWidthInSmallNodes || smallNode_Y>= AstarMap.mapHeightInSmallNodes)
			return true;
		return false;
	}


	/**********************************************************/

	/****************************/
	// GENERE les array pour le calcul
	// des 8 neighbors libres ou pas
	// selon une direction
	/****************************/
	//Liste les coordonnees des bords (+1 dans une direction donn�e)
	//en fonction de la largeur en node (bigNode ou smallNode c'est pareil) 
	//resultat : array[a][b][c]
	//[a] = un des quatres coins
	//[b] = un des N points du bord concern�
	//[c] = les coordonnees X,Y du point

	private static int[][][] getCornerArray(int diameter_){

		diameter_ = diameter_/2 + 1; //toujours impaire
		int[][][] cornerTempArray = new int[4][diameter_+diameter_+1+1][2];

		//On met en premier la direction ou on va
		//bas gauche
		cornerTempArray[0][0][0]=-1;
		cornerTempArray[0][0][1]=-1;
		//bas droit
		cornerTempArray[1][0][0]=1;
		cornerTempArray[1][0][1]=-1;
		//haut gauche
		cornerTempArray[2][0][0]=-1;
		cornerTempArray[2][0][1]=1;
		//haut droit
		cornerTempArray[3][0][0]=1;
		cornerTempArray[3][0][1]=1;

		//Le cas des coins
		//bas gauche
		cornerTempArray[0][diameter_+diameter_+1][0]=-diameter_;
		cornerTempArray[0][diameter_+diameter_+1][1]=-diameter_;
		//bas droit
		cornerTempArray[1][diameter_+diameter_+1][0]=diameter_;
		cornerTempArray[1][diameter_+diameter_+1][1]=-diameter_;
		//haut gauche
		cornerTempArray[2][diameter_+diameter_+1][0]=-diameter_;
		cornerTempArray[2][diameter_+diameter_+1][1]=diameter_;
		//haut droit
		cornerTempArray[3][diameter_+diameter_+1][0]=diameter_;
		cornerTempArray[3][diameter_+diameter_+1][1]=diameter_;

		//Le reste des points
		int counter_=1;
		for(int i=1; i<=diameter_ ; i++){

			//bas gauche
			cornerTempArray[0][counter_][0]=i-diameter_;
			cornerTempArray[0][counter_][1]=-diameter_;
			cornerTempArray[0][counter_+diameter_][0]=-diameter_;
			cornerTempArray[0][counter_+diameter_][1]=i-diameter_;
			//bas droit
			cornerTempArray[1][counter_][0]=i-diameter_;
			cornerTempArray[1][counter_][1]=-diameter_;
			cornerTempArray[1][counter_+diameter_][0]=diameter_;
			cornerTempArray[1][counter_+diameter_][1]=i-diameter_;
			//haut gauche
			cornerTempArray[2][counter_][0]=i-diameter_;
			cornerTempArray[2][counter_][1]=diameter_;
			cornerTempArray[2][counter_+diameter_][0]=-diameter_;
			cornerTempArray[2][counter_+diameter_][1]=i-diameter_;
			//haut droit
			cornerTempArray[3][counter_][0]=i-diameter_;
			cornerTempArray[3][counter_][1]=diameter_;
			cornerTempArray[3][counter_+diameter_][0]=diameter_;
			cornerTempArray[3][counter_+diameter_][1]=i-diameter_;

			counter_++;
		}

		//TESTED!
		/*
		System.out.println("#######CORNER######## " + diameter_);
		for(int i=0; i<cornerTempArray.length ; i++){
			System.out.println("_______");
			for(int j=0; j<cornerTempArray[i].length ; j++){
				System.out.println(cornerTempArray[i][j][0]+" "+cornerTempArray[i][j][1]);
			}
		}
		 */

		return cornerTempArray;
	}


	private static int[][][] getSideArray(int diameter_){

		diameter_ = diameter_/2 + 1; //toujours impaire
		int[][][] sideTempArray = new int[4][diameter_+1][2];

		//On met en premier la direction ou on va
		//bas
		sideTempArray[0][0][0]=0;
		sideTempArray[0][0][1]=-1;
		//gauche
		sideTempArray[1][0][0]=-1;
		sideTempArray[1][0][1]=0;
		//droite
		sideTempArray[2][0][0]=1;
		sideTempArray[2][0][1]=0;
		//haut
		sideTempArray[3][0][0]=0;
		sideTempArray[3][0][1]=1;

		//Les points
		for(int i=1; i<=diameter_ ; i++){
			//bas
			sideTempArray[0][i][0]=i-diameter_;
			sideTempArray[0][i][1]=-diameter_;
			//gauche
			sideTempArray[1][i][0]=-diameter_;
			sideTempArray[1][i][1]=i-diameter_;
			//droite
			sideTempArray[2][i][0]=diameter_;
			sideTempArray[2][i][1]=i-diameter_;
			//haut
			sideTempArray[3][i][0]=i-diameter_;
			sideTempArray[3][i][1]=diameter_;
		}

		//TESTED!
		/*
		System.out.println("#######SIDE######## " + diameter_);
		for(int i=0; i<sideTempArray.length ; i++){
			System.out.println("_______");
			for(int j=0; j<sideTempArray[i].length ; j++){
				System.out.println(sideTempArray[i][j][0]+" "+sideTempArray[i][j][1]);
			}
		}
		 */

		return sideTempArray;
	}

}
