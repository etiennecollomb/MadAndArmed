package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.Vehicle;


public class GroundMoveBehavior extends MoveBehavior {
	
	public void calculate(Vehicle v){

		if(!v.isAttacking()){
			
			this.pathFinding.calculate(v);
//			if(v.isNPC())
//				this.pathFinding.applyPenetrationContrainte(v); //ne doivent jamais se supperposer
			
		}
		else{
			
//			if(v.isNPC())
//				this.pathFinding.applyPenetrationContrainte(v); //ne doivent jamais se supperposer
			
		}

		//On sauve la nouvelle position dans le Next Buffer pour le AStar Pathfinding
		v.getMyTeam().getStateMap().addGroundVehicle(v);

	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}
	
}
