package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Vehicle;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class GroundMoveBehavior extends MoveBehavior {
	
	public void calculate(Vehicle v){

		if(!v.getAttackBehavior().isAttacking()){
			
			this.pathFinding.calculate(v);
			
		}

		//On sauve la nouvelle position dans le Next Buffer pour le AStar Pathfinding
		v.getMyTeam().getStateMap().addGroundVehicle(v);

	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		GlobalManager.poolManager.getGroundPathFinding().free((GroundPathFinding) pathFinding);
	}
	
}
