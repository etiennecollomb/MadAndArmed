package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.GameElement;
import com.geekmecrazy.madandarmed.Game.Element.Vehicle;
import com.geekmecrazy.madandarmed.pool.PoolManager;

public abstract class MoveBehavior extends GameElement {
	protected PathFinding pathFinding;
	//protected Attaque attaque;

	
	public abstract void calculate(Vehicle v);

	
	public PathFinding getPathFinding() {
		return pathFinding;
	}

	public void setPathFinding(PathFinding pathFinding) {
		this.pathFinding = pathFinding;
	}


	@Override
	public void reset() {		
    	if(pathFinding instanceof GroundPathFinding)PoolManager.getManager().getGroundPathFinding().free((GroundPathFinding) pathFinding);
    	else if(pathFinding instanceof AirPathFinding)PoolManager.getManager().getAirPathFindingPool().free((AirPathFinding) pathFinding);
    	pathFinding=null;
	}

}
