//TODO : setSpawnPosition : appeler setCoord bignode..etc... sinon c est a zero au depart


package com.geekmecrazy.madandarmed.Game.Element;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.IMoneyListener;
import com.geekmecrazy.madandarmed.Entity.IScoreListener;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.IA.StateMap;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

/**
 * Joueur (thune+score...) :)
 */
public class Team extends GameElement {
	// ===========================================================
	// Attributs
	// ===========================================================
	
	public static enum TeamID{
		TEAM1,
		TEAM2
	}
	
	private TeamID teamID;
	
	private int money;
	private int moneyByTurn;
	private int moneyMax;
	private int score;
	
	private int thoriumMax; //depends des upgrade de la base

	private Vector2d spawnPoint;
	
	private Building castle;
	
	private List<CreepType> listAskForCreateCreep;
	
	private List<Military> listMilitary; // liste des Military de la team (building + creep)
	private int currentNbMilitary; // Nombre de Military courante
	
	private StateMap stateMap = new StateMap();



	// ===========================================================
	// Listeners
	// ===========================================================
	private List<IMoneyListener> moneyListeners;
	private List<IScoreListener> scoreListeners;
	
	public Team(int startingMoney, int moneyByTurn, int moneyMax, Vector2d spawnPoint, TeamID teamID_, int thoriumMax) {
		this.listMilitary = new ArrayList<Military>(FightScreen.MAX_UNITS);
		this.listAskForCreateCreep=new ArrayList<CreepType>();
		this.moneyListeners = new ArrayList<IMoneyListener>();
		this.scoreListeners = new ArrayList<IScoreListener>();
		this.money = startingMoney;
		this.moneyByTurn = moneyByTurn;
		this.moneyMax = moneyMax;
		this.spawnPoint = spawnPoint;
		this.teamID = teamID_;
		this.thoriumMax = thoriumMax;
		
		this.stateMap.init(GlobalManager.STARMAP_WIDTH, GlobalManager.STARMAP_HEIGHT);
	}


	// ===========================================================
	// Money
	// ===========================================================

	/** Add money */
	public void addMoney(int _money){
		this.money=this.money+_money;
		//notifyMoneyListener();
	}
	
	/** Add money at new turn */
	public void addMoneyNewTurn(){
		if(this.money+moneyByTurn>moneyMax)this.money=moneyMax;
		else this.money=this.money+moneyByTurn;
		//notifyMoneyListener();
	}
	
	/** Sub money */
	public void subMoney(int _money){
		this.money=this.money-_money;
		//notifyMoneyListener();
	}
	
	/** Enought Money ? */
	public boolean hasEnoughtMoney(int _money){
		return this.money-_money>=0;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getMoneyMax() {
		return moneyMax;
	}
	
	public void setMoneyMax(int moneyMax) {
		this.moneyMax = moneyMax;
	}
	
	public StateMap getStateMap() {
		return stateMap;
	}

	public int getThoriumMax() {
		return thoriumMax;
	}

	public void setThoriumMax(int thoriumMax) {
		this.thoriumMax = thoriumMax;
	}
	
	// ===========================================================
	// Score
	// ===========================================================


	/** Add score */
	public void addScore(int points){
		this.score=this.score+points;
		//this.notifyScoreListener();
	}
	
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	// Set du Castle !important
	public void registerCastle(Building castle) {
		this.castle = castle;
	}

	// Add Military
	public void addMilitary(Military military){
		listMilitary.add(military);
		currentNbMilitary++;
	}
	
	// Remove Military
	public void removeMilitary(Military military){
		//listMilitaryRecycle[counterMilitaryRecycle]=military;
		//counterMilitaryRecycle++;
		
		listMilitary.remove(military);
		currentNbMilitary--;
	}
	
//	public void calculateMilitarySpace(){
//		for(int i=0; i<currentNbMilitary; i++)listMilitary.get(i).getAttackBehavior().calculateMilitarySpace(listMilitary.get(i));
//	}

	// Check si (x_,y_) a un voisin dans cette team - Coord (x_,y_) en smallNode
	public Military getNearestMilitary(int x_, int y_){
		int min_distance = 99999999;
		Military nearestMilitary = null;
		for(int i=0; i<currentNbMilitary; i++){
			if(listMilitary.get(i).getAttackBehavior() != null){
				int[] matrixPursuitRange = listMilitary.get(i).getAttackBehavior().getMatrixPursuitRange();
				if(x_ >= matrixPursuitRange[0])
					if(y_ >= matrixPursuitRange[1])
						if(x_ <= matrixPursuitRange[2])
							if(y_ <= matrixPursuitRange[3]){
								//target encore plus proche?
								int currentDistance = Math.abs(x_-listMilitary.get(i).getSmallNodeX())+Math.abs(y_-listMilitary.get(i).getSmallNodeY());
								if(currentDistance<min_distance){
									min_distance=currentDistance;
									nearestMilitary=listMilitary.get(i);
								}
							}
			}
		}
		return nearestMilitary;
	}

	
	//DEBUG METHOD
	public void instantKillAllUnit(){
		for(Military m : listMilitary){
			if(m instanceof Creep) m.hit(100000, AnimatedTextureType.MISSILE_EXPLOSION);
		}
	}
	
	


	public int getMoneyByTurn() {
		return moneyByTurn;
	}


	public int getScore() {
		return score;
	}	
	
	public Building getCastle() {
		return castle;
	}

	public Vector2d getSpawnPoint() {
		return spawnPoint;
	}

	public List<CreepType> getListAskForCreateCreep() {
		return listAskForCreateCreep;
	}

	public void addMoneyListener(IMoneyListener moneyListener){
		this.moneyListeners.add(moneyListener);
	}

	public void addScoreListener(IScoreListener scoreListener){
		this.scoreListeners.add(scoreListener);
	}

	public TeamID getTeamID() {
		return teamID;
	}

	
	
	///////////////
	// LISTENERS //
	///////////////

	//On traite tout cycle par cycle, une fois tout boucles
	public void  notifyListeners(){
		notifyMoneyListener();
		notifyScoreListener();
	}
	
	private void notifyMoneyListener(){
		for(IMoneyListener moneyListener : moneyListeners)
			moneyListener.moneyChange(this);
	}

	private void notifyScoreListener(){
		for(IScoreListener scoreListener : scoreListeners)
			scoreListener.scoreChange(this);
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
