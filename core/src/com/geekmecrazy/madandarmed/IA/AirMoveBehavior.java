package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.Vehicle;


public class AirMoveBehavior extends MoveBehavior{
	
	public void calculate(Vehicle v){

//		//si on attaque pas, on va juste sur la target
//		if(!v.isAttacking()){
//			this.pathFinding.calculate(v);
//
//			this.pathFinding.applyPenetrationContrainte(v);
//		}
//		//Si on attaque (donc pseudo immobile), on applique que C. de N.P.
//		else{
//				this.pathFinding.applyPenetrationContrainte(v);
//		}
//
//		//On sauve la nouvelle position dans le Next Buffer
//		v.getMyTeam().getStateMap().addAirVehicle(v);

	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}
	
}
