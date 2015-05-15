package com.geekmecrazy.madandarmed.Game.Factory;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.Game.Scene.BuildingManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.IA.AttackBehavior;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingID;
import com.geekmecrazy.madandarmed.Renderer.BuildingRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;
import com.geekmecrazy.madandarmed.pool.PoolManager;



public class BuildingFactory{

	// disable object's instanciation (private constructor)
	private BuildingFactory(){} 

	public static void create (float posX, float posY, BuildingPattern buildingPattern, Team team) {
		
		// LIFE
		Life life = null;
		if(buildingPattern.getLife()>0){
			life = PoolManager.getManager().getLifePool().obtain();
			life.init(buildingPattern.getLife());
		}

		// ANIM
		BuildingRenderer buildingRenderer = PoolAnimManager.getManager().getBuildingRendererPool().obtain();
		
		// BUILDING
		Turret turret = PoolManager.getManager().getTurretPool().obtain();
		turret.init(posX, posY, buildingPattern.getBuildingSize().getBigNodeSize()*GlobalManager.BIG_NODESIZE,  buildingPattern.getBuildingSize().getBigNodeSize()*GlobalManager.BIG_NODESIZE, buildingPattern, life, team, FightScreen.getManager().getOtherTeam(team), buildingRenderer);

        if(buildingPattern.getBuildingID()==BuildingID.CASTLE_TEAM1
                || buildingPattern.getBuildingID()==BuildingID.CASTLE_TEAM2)
            team.registerCastle(turret);
		
		// ATTACK
		if(buildingPattern.getWeaponPattern()!=null){
			AttackBehavior attackBehavior = PoolManager.getManager().getAttackBehaviorPool().obtain();
			attackBehavior.init(buildingPattern.getWeaponPattern());
			attackBehavior.setAttacking(true);
			turret.setAttackBehavior(attackBehavior);
			Attaque attaque = PoolManager.getManager().getAttaquePool().obtain();
			attackBehavior.setAttaque(attaque);
		}
		
		//building.addDestructibleObs(this);
		BuildingManager.getManager().addBuilding(turret);
	}

	public static void destroy(Building building) {
		BuildingManager.getManager().removeBuilding(building);
		//building.recycle();
		if(building.equals(FightScreen.getManager().getTeamIA().getCastle())){
			FightScreen.getManager().getUiFinishGame().showUI(true);
		}else if(building.equals(FightScreen.getManager().getTeamPlayer().getCastle())){
			FightScreen.getManager().getUiFinishGame().showUI(false);
		}

	}


}
