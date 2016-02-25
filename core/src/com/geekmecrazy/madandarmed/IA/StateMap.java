package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.Team.TeamID;
import com.geekmecrazy.madandarmed.Game.Element.Vehicle;
import com.geekmecrazy.madandarmed.Game.Scene.Fight_BuildingManager;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepSize;
import com.geekmecrazy.madandarmed.Tools.ResetArray;

import java.util.List;


public class StateMap {


	//Taille de la map en BigNode
	public int mapWidthInBigNodes;
	public int mapHeightInBigNodes;

	//Taille de la map en BigNode
	public int mapWidthInSmallNodes;
	public int mapHeightInSmallNodes;


	/**************************/
	// SMAP ARRAY
	/**************************/
	//Les arrays sont de la meme taille que celle contenant les smallNodes de la map
	//Contient l'information de la repartition des smallNodes occupees 
	//	sur le jeu par les geometries (batiments, creeps..etc)
	//Systeme de buffer : on swap current****PositionMap et next****PositionMap a chaque cycle
	//	On ne le fait pas pour les batiments car ils ne bougent pas d un cycle a un autre
	//currentCreepZoneAPosition : Contient 1 creep (l'ordre d'ecrasement 5X5, 3x3, 1x1 pour une meme place occupee) par smallnode
	//	Permet d'eviter que des creeps se superposent en les ecartant d'un des creeps present
	//	(un seul type par smallNode suffit, ca s ecarte au fur et a mesure)
	//	On test si c'est un BIG, MEDIUM, ou SMALL et on ecrase par le nouveau si il est plus grand


	/**** SOL ***/
    //Movable units
	//Contient le nombre de geometries "fixes" par case
	private ResetArray currentZoneAPositionMap;
	private ResetArray nextZoneAPositionMap;
	//On ecrase du plus grand au plus petit : 5x5, 3x3,1x1
	private Vehicle[][] currentCreepZoneAPositionMap;
	private Vehicle[][] nextCreepZoneAPositionMap;

	//pour les buildings ou autre objets STATIC (en coord BigNodes)
	private int[][] zoneBPositionMap;

	/**** AIR ***/
	//On ecrase du plus grand au plus petit : 5x5, 3x3,1x1
	private Vehicle[][] currentCreepAirPositionMap;
	private Vehicle[][] nextCreepAirPositionMap;


	/**********************************************************/

	//Size en bigNode
	public void init(int mapWidth, int mapHeight) {

		mapWidthInBigNodes = mapWidth;
		mapHeightInBigNodes = mapHeight;

		mapWidthInSmallNodes = mapWidth*3;
		mapHeightInSmallNodes = mapHeight*3;

		//SOL
		currentZoneAPositionMap = new ResetArray(mapWidthInSmallNodes, mapHeightInSmallNodes);
        currentZoneAPositionMap.init();
		nextZoneAPositionMap = new ResetArray(mapWidthInSmallNodes, mapHeightInSmallNodes);
        nextZoneAPositionMap.init();
		currentCreepZoneAPositionMap = new Vehicle[mapWidthInSmallNodes][mapHeightInSmallNodes];
		nextCreepZoneAPositionMap = new Vehicle[mapWidthInSmallNodes][mapHeightInSmallNodes];
		zoneBPositionMap = new int[mapWidthInSmallNodes][mapHeightInSmallNodes];

		//AIR
		currentCreepAirPositionMap = new Vehicle[mapWidthInSmallNodes][mapHeightInSmallNodes];
		nextCreepAirPositionMap = new Vehicle[mapWidthInSmallNodes][mapHeightInSmallNodes];

	}

	public void initGame(){
		//permet de faire un reset sur les 2 positionMaps
		swap();
		swap();
	}


	/**********************************************************/

	/****************************/
	// SWAP
	//appele a chaque cycle
	/****************************/

	public void swap(){

		/*** SOL ***/
		ResetArray tempPositionMap;
		Vehicle[][] tempVehiclePostionMap;

		tempPositionMap = currentZoneAPositionMap;
		currentZoneAPositionMap = nextZoneAPositionMap;
		nextZoneAPositionMap = tempPositionMap;

		tempVehiclePostionMap = currentCreepZoneAPositionMap;
		currentCreepZoneAPositionMap = nextCreepZoneAPositionMap;
		nextCreepZoneAPositionMap = tempVehiclePostionMap;

		//Reinitialise nextPositionMap
		resetNextZoneAPositionMap();


		/*** AIR ***/
		tempVehiclePostionMap = currentCreepAirPositionMap;
		currentCreepAirPositionMap = nextCreepAirPositionMap;
		nextCreepAirPositionMap = tempVehiclePostionMap;

		//Reinitialise nextPositionMap
		resetNextAirPositionMap();

	}

	/**********************************************************/
	//SOL

	private void resetNextZoneAPositionMap(){
        nextZoneAPositionMap.reset();
	}

	public void setZoneBPositionMap(TeamID teamID){

		List<Building> list_buildings_ = Fight_BuildingManager.getManager().getListBuildings();
		for(Building building_ : list_buildings_){
			//on ne recupere que les building de notre equipe
			if(building_.getMyTeam().getTeamID() == teamID)
				addBuilding(building_);
		}

	}

	public void addBuilding(Building building_){

		int xmin_, ymin_,xmax_, ymax_;
		xmin_ = building_.getSmallNodeX()-building_.getSmallNodeDiameter()/2;
		ymin_ = building_.getSmallNodeY()-building_.getSmallNodeDiameter()/2;
		xmax_ = xmin_ + building_.getSmallNodeDiameter();
		ymax_ = ymin_ + building_.getSmallNodeDiameter();

		//TODO: les buildings ne doivent jamais etre en dehors de la map
		for(int i=xmin_; i<xmax_; i++)
			for(int j=ymin_; j<ymax_; j++){
				zoneBPositionMap[i][j]=zoneBPositionMap[i][j]+1;
			}

	}

	public void removeBuilding(Building building_){

		int xmin_, ymin_,xmax_, ymax_;
		xmin_ = building_.getBigNodeX()-building_.getSmallNodeDiameter()/2;
		ymin_ = building_.getBigNodeY()-building_.getSmallNodeDiameter()/2;
		xmax_ = xmin_ + building_.getSmallNodeDiameter();
		ymax_ = ymin_ + building_.getSmallNodeDiameter();

        //TODO: les buildings ne doivent jamais etre en dehors de la map
		for(int i=xmin_; i<xmax_; i++)
			for(int j=ymin_; j<ymax_; j++){
				zoneBPositionMap[i][j]=zoneBPositionMap[i][j]-1;
			}
	}

	public void addGroundVehicle(Vehicle vehicle){
		int x_=vehicle.getSmallNodeX();
		int y_=vehicle.getSmallNodeY();

		//faire pour TOUS! 3X3 5X5...
		//Gere les 3 size : on ecrase par dessus dans nextCreepZoneAPosition sans checker si size est plus grosse
		//Cela sert pour les contrainte de penetration : on priviligie d abord les grosses units, et les petits qui s en ecratent, plutot que l inverse
		//ATTENTION AUX BORDS!
		if(vehicle.getPattern().getCreepSize() == CreepSize.BIG){
			//on check rien : prioritaire ds tous les cas, on ecrase
			int rayon = vehicle.getPattern().getCreepSize().getRayon();
			for(int i=-rayon; i<=rayon; i++){
				for(int j=-rayon; j<=rayon; j++){

					int x__ = x_+i , y__ = y_+j;
					if(x__ >= 0 && x__ < mapWidthInSmallNodes && y__ >= 0 && y__ < mapHeightInSmallNodes){ //Check Bords
						nextCreepZoneAPositionMap[x__][y__]=vehicle;
						nextZoneAPositionMap.setUsed(x__, y__);
					}
				}
			}

		}
		else if(vehicle.getPattern().getCreepSize() == CreepSize.MEDIUM){
			//on check si c est pas Big sinon ok
			//ie: si vide ou si pas big c est OK
			int rayon = vehicle.getPattern().getCreepSize().getRayon();
			for(int i=-rayon; i<=rayon; i++){
				for(int j=-rayon; j<=rayon; j++){

					int x__ = x_+i , y__ = y_+j;
					if(x__ >= 0 && x__ < mapWidthInSmallNodes && y__ >= 0 && y__ < mapHeightInSmallNodes){ //Check Bords


						if(!nextZoneAPositionMap.isUsed(x__, y__) || nextCreepZoneAPositionMap[x__][y__].getPattern().getCreepSize() != CreepSize.BIG){
							nextCreepZoneAPositionMap[x__][y__]=vehicle;
							nextZoneAPositionMap.setUsed(x__, y__);
						}
					}
				}
			}

		}
		else if(vehicle.getPattern().getCreepSize() == CreepSize.SMALL){
			
			if(x_ >= 0 && x_ < mapWidthInSmallNodes && y_ >= 0 && y_ < mapHeightInSmallNodes){ //Check Bords
				
				//on remplit que si il a y rien
				if(!nextZoneAPositionMap.isUsed(x_, y_)){
					nextCreepZoneAPositionMap[x_][y_]=vehicle;
					nextZoneAPositionMap.setUsed(x_, y_);
				}
			}

		}		


	}

	/**********************************************************/
	//AIR


	private void resetNextAirPositionMap(){

        //TODO : utiliser les ResetArray
//		for(int i=0; i<mapWidthInSmallNodes; i++)
//			for(int j=0; j<mapHeightInSmallNodes; j++)
//				nextCreepAirPositionMap[i][j]=null;
	}

	public void addAirVehicle(Vehicle vehicle){
        //TODO : utiliser les ResetArray
//		int x_=vehicle.getSmallNodeX();
//		int y_=vehicle.getSmallNodeY();
//
//		//ATTENTION AUX BORDS!
//
//		//faire pour TOUS! 3X3 5X5...
//		//Gere les 3 size : on ecrase par dessus dans nextCreepZoneAPosition sans checker si size est plus grosse
//		if(vehicle.getPattern().getUnitSize() == UnitSize.BIG){
//			//on check rien : prioritaire ds tous les cas
//		}
//		else if(vehicle.getPattern().getUnitSize() == UnitSize.MEDIUM){
//			//on check si c est pas Big sinon ok
//			//if(! (nextCreepZoneAPosition[x_][y_]!=null && nextCreepZoneAPosition[x_][y_].getUnitSize()!=UnitSize.BIG) )
//		}
//		else if(vehicle.getPattern().getUnitSize() == UnitSize.SMALL){
//			//on remplit que si il a y rien
//			if(nextCreepAirPositionMap[x_][y_]==null){
//				nextCreepAirPositionMap[x_][y_]=vehicle;
//			}
//
//		}

	}

	/**********************************************************/

	public ResetArray getCurrentZoneAPositionMap() {
		return currentZoneAPositionMap;
	}

	public int[][] getZoneBPositionMap() {
		return zoneBPositionMap;
	}

	public Vehicle[][] getCurrentCreepZoneAPositionMap() {
		return currentCreepZoneAPositionMap;
	}

	public Vehicle[][] getNextCreepZoneAPositionMap() {
		return nextCreepZoneAPositionMap;
	}

	public Vehicle[][] getCurrentCreepAirPositionMap() {
		return currentCreepAirPositionMap;
	}

	public Vehicle[][] getNextCreepAirPositionMap() {
		return nextCreepAirPositionMap;
	}

	public int getMapWidthInSmallNodes() {
		return mapWidthInSmallNodes;
	}

	public int getMapHeightInSmallNodes() {
		return mapHeightInSmallNodes;
	}


}


