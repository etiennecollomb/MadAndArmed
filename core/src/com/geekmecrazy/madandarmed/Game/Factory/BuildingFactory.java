package com.geekmecrazy.madandarmed.Game.Factory;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.Barricade;
import com.geekmecrazy.madandarmed.Game.Element.Building;
import com.geekmecrazy.madandarmed.Game.Element.CampBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Life;
import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.Game.Scene.BuildingManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.IA.AttackBehavior;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingType;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class BuildingFactory{

	// ===========================================================
	// Constructors
	// ===========================================================

	// disable object's instanciation (private constructor)
	private BuildingFactory(){} 

	public static Building create (float posX, float posY, BuildingPattern buildingPattern, Team team) {

		Building building = null;

		if(buildingPattern.getBuildingType() == BuildingType.TURRET)
			building = createTurret(posX, posY, buildingPattern, team);
		else if(buildingPattern.getBuildingType() == BuildingType.CASTLE)
			building = createTurret(posX, posY, buildingPattern, team);
		else if(buildingPattern.getBuildingType() == BuildingType.BARRICADE)
			building = createBarricade(posX, posY, buildingPattern, team);
		else if(buildingPattern.getBuildingType() == BuildingType.CAMP_BUILDING)
			building = createCampBuilding(posX, posY, buildingPattern, team);

		return building;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static void destroy(Building building) {
		BuildingManager.getManager().removeBuilding(building);
		//building.recycle();
		if(building.equals(FightScreen.getManager().getTeamIA().getCastle())){
			FightScreen.getManager().getUiFinishGame().showUI(true);
		}else if(building.equals(FightScreen.getManager().getTeamPlayer().getCastle())){
			FightScreen.getManager().getUiFinishGame().showUI(false);
		}

	}

	private static Building createTurret(float posX, float posY, BuildingPattern buildingPattern, Team team){

		/** Life */
		Life life = null;
		if(buildingPattern.getLife()>0){
			life = PoolManager.getManager().getLifePool().obtain();
			life.init(buildingPattern.getLife());
		}

		/** Building */
		Turret turret = PoolManager.getManager().getTurretPool().obtain();
		float diameter = buildingPattern.getBuildingSize().getBigNodeSize()*GlobalManager.BIG_NODESIZE;
		turret.init(posX, posY, diameter, buildingPattern, life, team, FightScreen.getManager().getOtherTeam(team));

		/** AttackBehavior */
		if(buildingPattern.getWeaponName()!=null){

			AttackBehavior attackBehavior = PoolManager.getManager().getAttackBehaviorPool().obtain();
			attackBehavior.init(PatternLoader.getWeaponsPattern().get(buildingPattern.getWeaponName().name()));
			attackBehavior.setAttacking(true);
			turret.setAttackBehavior(attackBehavior);
			Attaque attaque = PoolManager.getManager().getAttaquePool().obtain();
			attackBehavior.setAttaque(attaque);
		}

		/** Castle Special Case */
		if(buildingPattern.getBuildingType()==BuildingType.CASTLE)
			team.registerCastle(turret);

		return turret;
	}

	private static Building createBarricade(float posX, float posY, BuildingPattern buildingPattern, Team team){

		/** Life */
		Life life = null;
		if(buildingPattern.getLife()>0){
			life = PoolManager.getManager().getLifePool().obtain();
			life.init(buildingPattern.getLife());
		}

		/** Building */
		Barricade barricade = PoolManager.getManager().getBarricadePool().obtain();
		float diameter = buildingPattern.getBuildingSize().getBigNodeSize()*GlobalManager.BIG_NODESIZE;
		barricade.init(posX, posY, diameter, buildingPattern, life, team, FightScreen.getManager().getOtherTeam(team));

		return barricade;
	}

	private static Building createCampBuilding(float posX, float posY, BuildingPattern buildingPattern, Team team){

		/** Life */
		Life life = null;
		if(buildingPattern.getLife()>0){
			life = PoolManager.getManager().getLifePool().obtain();
			life.init(buildingPattern.getLife());
		}

		/** Building */
		CampBuilding campBuilding = PoolManager.getManager().getCampBuildingPool().obtain();
		float diameter = buildingPattern.getBuildingSize().getBigNodeSize()*GlobalManager.BIG_NODESIZE;
		campBuilding.init(posX, posY, diameter, buildingPattern, life, team, FightScreen.getManager().getOtherTeam(team));

		return campBuilding;
	}

	public static SpawnBuilding createSpawnBuilding(float posX, float posY, BuildingPattern buildingPattern, Team team){

		/** Life */
		Life life = null;
		if(buildingPattern.getLife()>0){
			life = PoolManager.getManager().getLifePool().obtain();
			life.init(buildingPattern.getLife());
		}

		/** Building */
		SpawnBuilding spawnBuilding = PoolManager.getManager().getSpawnBuildingPool().obtain();
		float diameter = buildingPattern.getBuildingSize().getBigNodeSize()*GlobalManager.BIG_NODESIZE;
		spawnBuilding.init(posX, posY, diameter, buildingPattern, life, team, FightScreen.getManager().getOtherTeam(team));

		return spawnBuilding;
	}



}
