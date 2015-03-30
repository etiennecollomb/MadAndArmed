
package com.geekmecrazy.madandarmed.IA;

import java.util.ArrayList;
import java.util.Random;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Vehicle;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class GroundPathFinding extends PathFinding {


	private Vector2d tempPosition = new Vector2d();
	private Random randomGenerator = new Random();

	//Path
	private ArrayList<Node> path = new ArrayList<Node>() ;

	/***********/
	//Algo de deplacement
	private int currentNode;
	//Pause rapide quand le path est bloque momentanement
	private int shortPauseLength = 5 ; //valeur = 10
	private int shortPause = shortPauseLength;
	private boolean isShortPause = false;
	//Temps de recalcul obligatoire pour reajuster le path
	private int updateAstarCounterLength ;
	private int updateAstarCounter;
	//random generator
    private Random generator;
    //check NPC (on ne le fait qu une fois sur n cycle pour les perfs)
//    private int numberNPCCycle = 10; //every n cycle
//    private int countNPCCycle; //every n cycle
	/***********/


	/**********************************************************/

	public GroundPathFinding() {
		//GlobalAstar.init();
		generator = new Random();
	}

	public void init(Vehicle v, int ZoneACreepWidth, int ZoneACreepHeight, int ZoneBWidth, int ZoneBHeight) {

		isShortPause = false;
		currentNode = 0;

		//on init les compteurs
		//c est la distance minimal pour laquelle on peut sortir de la zone A
		updateAstarCounterLength = 12; //(int) (bigNodeSize * Math.min(astarMap.getZoneACreep().getWidth(), astarMap.getZoneACreep().getHeight()) / vitesse);
		updateAstarCounter = 0;

//        countNPCCycle = generator.nextInt(numberNPCCycle);

	}



	/**********************************************************/
	/****************************/
	// CALCUL LA POSITION
	// position suivante du Vehicule v
	/****************************/
	private float debugX=0, debugY=0, debugTime = 5, debugCounter=debugTime; //debugTime = temps a l arret pdt lequel on considere qu il n est pas a l arret
	//calcul fait pour placer le vehicule qd il est en mouvement (non considere a l arret)
	public void calculate(Vehicle v) {

        //si en mode Go Point
        if(v.isGoPoint()) {
            if (path.size() <= currentNode) v.setIsGoPoint(false); //on remet le target normal Si on est en fin de Path
        }

		//si en mode prioritaire
		if(!v.isNPC()){

			setNewPosition(v);
			if(path.size()<=currentNode) v.setNPC(true); //on remet le NPC car un chemin a ete a nouveau calcule....
		}
		//Si on a un Path, on calcul la nouvelle position
		else{

			//Si on est en fin de Path ou si fin de cycle d'update
			//On calcul un nouveau path
			if((updateAstarCounter<=0) || (path.size()<=currentNode)){
				this.getNewPath(v);
				updateAstarCounter = updateAstarCounterLength;
			}

			if(!(path.size()<=currentNode)){ 
				setNewPosition(v);
				updateAstarCounter--;
			}

//			//Problem des creeps non attack en movement immobile
//			//on les fait aller a un point precis coute que coute pour sortir de se trou noir, avant de reprendre les calculs normaux.
//			if(!v.isAttacking()){
//				if(debugX==v.getPos().getX() && debugY==v.getPos().getY()){
//					debugCounter++;
//
//					if(debugCounter>=debugTime){
//						//mettre un node proche en target et debloquer les CNP jusqua ce qu elle soit atteinte
//						//puis recalculer un pathfinfing
//						//retour a la normal ensuite
//						this.path.clear();
//						//On choisit un point au hasard "assez loin" pour recalculer un path ailleurs
//						int node_X = generator.nextInt(5) - 2; //from 0 to 4 included, minus 2
//						int node_Y = generator.nextInt(5) - 2; //from 0 to 4 included, minus 2
//
//						//on check pour les bords
//						int coordX_ = v.getSmallNodeX()+node_X;	int coordY_ = v.getSmallNodeY()+node_Y;
//						if(coordX_ < 0) coordX_ = 1; if(coordY_ < 0) coordY_ = 1;
//						if(coordX_ >= AstarMap.mapWidthInSmallNodes) coordX_ = AstarMap.mapWidthInSmallNodes-1;
//						if(coordY_ >= AstarMap.mapHeightInSmallNodes) coordY_ = AstarMap.mapHeightInSmallNodes-1;
//
//						Node targetNode = AstarMap.getNode(coordX_, coordY_);
//						this.path.add(targetNode);
//						currentNode=0;
//						v.setNPC(false);
//
//					}
//				}
//				else{debugCounter=0;}
//
//				debugX=v.getPos().getX(); debugY=v.getPos().getY();
//			}

		}


	}




	/**********************************************************/

	/****************************/
	// CALCUL DU NOUVEAU PATH
	/****************************/
	private void getNewPath(Vehicle v){

//		//DEBUG
//		if(v.isDrawAstarMapFlag()){
//			//reset les voisins sinon on garde les anciens par dessus
//			//en vrai pas besoin car on stock les voisin en cours , on prends pas ceux qui auraient existe au tour precedent (sinon il sont ecrase par le calcul en cours
//			AstarMap.resetNeighborhoodNodes();
//		}
//		//FIN DEBUG

		currentNode=0;
		//on prepare la map
		AstarMap.resetMap(v);
		//position du creep

		//on check pour les bords
		int coordX_ = v.getSmallNodeX(); int coordY_ = v.getSmallNodeY();
		if(coordX_ < 0) coordX_ = 1; if(coordY_ < 0) coordY_ = 1;
		if(coordX_ >= AstarMap.mapWidthInSmallNodes) coordX_ = AstarMap.mapWidthInSmallNodes-1;
		if(coordY_ >= AstarMap.mapHeightInSmallNodes) coordY_ = AstarMap.mapHeightInSmallNodes-1;

		Node debutNode = AstarMap.getNode(coordX_, coordY_);

        //si les unit selectionnees ont l ordre d aller a un point
		if(v.isGoPoint()){
            coordX_ = (int)(v.getGoPoint().getX()/GlobalManager.SMALL_NODESIZE);
            coordY_ = (int)(v.getGoPoint().getY()/GlobalManager.SMALL_NODESIZE);
        }else {
        //sinon target
            coordX_ = v.getCurrentTarget().getSmallNodeX();
            coordY_ = v.getCurrentTarget().getSmallNodeY();
        }
        //on check pour les bords
		if(coordX_ < 0) coordX_ = 1; if(coordY_ < 0) coordY_ = 1;
		if(coordX_ >= AstarMap.mapWidthInSmallNodes) coordX_ = AstarMap.mapWidthInSmallNodes-1;
		if(coordY_ >= AstarMap.mapHeightInSmallNodes) coordY_ = AstarMap.mapHeightInSmallNodes-1;

		Node finNode = AstarMap.getNode(coordX_, coordY_);
		
		GlobalAstar.getPath(debutNode, finNode, this.path, v);

//		//DEBUG
//		if(v.isDrawAstarMapFlag()){
//			AstarMap.drawAstarMap(v);
//		}
//		//FIN DEBUG
	}

	/**********************************************************/

	/****************************/
	// CALCULE DE LA NOUVELLE POSITION
	// par rapport au path calcule
	/****************************/
	private void setNewPosition(Vehicle v){

		//on check si on a atteint la node pour passer a la suivante
		//ie : on est sur la tiled/node
		tempPosition.set(this.path.get(currentNode).getX()*(GlobalManager.SMALL_NODESIZE)+(GlobalManager.SMALL_NODESIZE/2f), this.path.get(currentNode).getY()*(GlobalManager.SMALL_NODESIZE)+(GlobalManager.SMALL_NODESIZE/2f));
		tempPosition.sub(v.getPos().getX(), v.getPos().getY());
		float distance2=tempPosition.length();
		//si on est sur la node on passe a la suivante...
		if(distance2<=(GlobalManager.SMALL_NODESIZE)) {
			currentNode++;
		}

		if(this.path.size()>currentNode){

			//on calcul la prochaine position
			tempPosition.set(this.path.get(currentNode).getX()*(GlobalManager.SMALL_NODESIZE)+(GlobalManager.SMALL_NODESIZE/2f), this.path.get(currentNode).getY()*(GlobalManager.SMALL_NODESIZE)+(GlobalManager.SMALL_NODESIZE/2f));
			tempPosition.sub(v.getPos());
			tempPosition.normalize(); //la direction du vehicle
			tempPosition.scale(v.getPattern().getSpeed());
			tempPosition.add(v.getPos());
			v.setPos(tempPosition.getX(),tempPosition.getY());
		}

	}


	/**********************************************************/

	/****************************/
	// CONTRAINTE NON PENTRATION
	/****************************/
	//se fait sur le next position map qui se construit au fur et a mesure
	//(premier arrive premier servit)
	//modifie la positon si on penetre un autre
	//renvoie true si ce st le cas, false sinon

	//TODO : le calcul par par CERCLE mais par CARRE si trop lent?
	//ie: x2-x1 < largueur1+largeur2?
	//idem pour y
	public void applyPenetrationContrainte(Vehicle v){

//        //Is it time to NPC?
//        countNPCCycle++;
//        if(countNPCCycle>=numberNPCCycle) {
//            countNPCCycle = 0;
//
//
//            StateMap stateMap = v.getMyTeam().getStateMap();
//
//            int x_ = v.getSmallNodeX();
//            int y_ = v.getSmallNodeY();
//
//
//            //TODO: ajouter les buildings aussi (calculer a chaque fois pour tous les buidings
//            // car peux de buildings, ca dervait etre ok)
//            //Note : Gestion des bords de la scene : repulsion 100% faite dans Vehicle.onUpdateNextState()
//            int rayon = v.getPattern().getUnitSize().getRayon() + 1; //+1 car on doit prendre en compte les units tres proches des bords d'une smallNode (important sinon les units se poussent pas suffisement....)
//            for (int i = -rayon; i <= rayon; i++)
//                for (int j = -rayon; j <= rayon; j++) {
//
//                    int tempX = x_ + i, tempY = y_ + j;
//
//                    //gestion des bords
//                    if (tempX >= 0 && tempX < stateMap.getMapWidthInSmallNodes() && tempY >= 0 && tempY < stateMap.getMapHeightInSmallNodes()) {
//
//                        //On regarde le CREEP presents dans CurrentCreepZoneAPosition
//                        Vehicle neighborVehicle = stateMap.getCurrentCreepZoneAPositionMap()[tempX][tempY];
//
//                        //neighborVehicle!=null est normalement INUTILE car il ya lui meme ! mais bon.....
//                        if (neighborVehicle != null && neighborVehicle != v) {
//                            tempPosition.set(neighborVehicle.getPos());
//                            tempPosition.sub(v.getPos());
//                            float distance = tempPosition.length();
//                            distance = distance - (v.getWidth() + neighborVehicle.getWidth());
//
//                            if (distance < 0) {
//                                //collision
//                                tempPosition.normalize(); //la direction du vehicle
//                                tempPosition.scale(distance / 40f * 3); //IMPORTANT : influe sur la "tassement" des unit entre elle dans les zones de bourrage
//
//                                tempPosition.add(v.getPos());
//                                v.setPos(tempPosition.getX(), tempPosition.getY());
//
//                                return; //stopper le calcul apres UN voisin trouvé
//                            }
//                        }
//                    }
//
//                }
//        }

	}




	/**********************************************************/

	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
	}


	
	
	




}
