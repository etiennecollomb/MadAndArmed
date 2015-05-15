package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.Vehicle;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class AirPathFinding extends PathFinding {

	private Vector2d tempPosition = new Vector2d();
	private float vitesse=1f;

	//private float diametrePenetrationContrainte=20f;

	/**********************************************************/

	/****************************/
	// CALCUL LA POSITION
	// position suivante du Vehicule v
	/****************************/
	public void calculate(Vehicle v) {

		tempPosition.set(v.getAttackBehavior().getCurrentTarget().getPos());
		tempPosition.sub(v.getPos());
		tempPosition.normalize();
		tempPosition.scale(vitesse);
		tempPosition.add(v.getPos());
		v.setPos(tempPosition.getX(),tempPosition.getY());

	}

	/**********************************************************/

	/****************************/
	// CONTRAINTE NON PENTRATION
	/****************************/
	//TEST : DOIT REMPLACER ISMOVEOK !
	//se fait sur le next position map qui se construit au fur et a mesure
	//(premier arrive premier servit)
	//modifie la positon si on penetre un autre
	//renvoie true si ce st le cas, false sinon

	public void applyPenetrationContrainte(Vehicle v){

		int x_=v.getSmallNodeX();
		int y_=v.getSmallNodeY();


		//21 case autour pour TEST ! (pourquoi?)
		for(int i=-2; i<=2; i++)
			for(int j=-2; j<=2; j++){

				//TODO : gestion des bords!
				int tempX = x_+i, tempY = y_+j;

				//On regarde les CREEPS presents dans les smallNodes
				//for(int k=0; k<StateMap.getNextCreepAirPosition()[tempX][tempY]; k++){

				Vehicle neighborVehicle = v.getMyTeam().getStateMap().getNextCreepAirPositionMap()[tempX][tempY];

				//on s ecarte des vehicle qui sont dans la meme action uniquement
				if(neighborVehicle!=null && (neighborVehicle.getAttackBehavior().isAttacking()==v.getAttackBehavior().isAttacking())){
					tempPosition.set(neighborVehicle.getPos());
					tempPosition.sub(v.getPos());
					float distance = tempPosition.length();
					distance = distance - (v.getWidth()+neighborVehicle.getWidth());

					if(distance < 0){
						//collision
						tempPosition.normalize(); //la direction du vehicle
						tempPosition.scale(distance/100f);

						tempPosition.add(v.getPos());
						v.setPos(tempPosition.getX(),tempPosition.getY());
					}
				}
				//pour en utiliser QU UN !! A REFAIRE
				//break;
				//}
			}


	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}


}
