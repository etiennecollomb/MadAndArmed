package com.geekmecrazy.madandarmed.Game.Element;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.IMoneyListener;
import com.geekmecrazy.madandarmed.Entity.IScoreListener;
import com.geekmecrazy.madandarmed.Game.Scene.GamePlayScreen;
import com.geekmecrazy.madandarmed.IA.StateMap;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern.BuildingName;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern.CreepType;

/**
 * Joueur (thune+score...) :)
 */
public class Fight_Team extends GamePlay_Team {

	private int money;
	private int moneyByTurn;
	private int moneyMax;
	private int score;
	
	private int thoriumMax; //depends des upgrade de la base
	
	private Building castle;
	
	/** Listeners **/
	private List<IMoneyListener> moneyListeners;
	private List<IScoreListener> scoreListeners;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public Fight_Team(int startingMoney, int moneyByTurn, int moneyMax, TeamID teamID_, int thoriumMax) {
		super(teamID_, GlobalManager.MAP_FIGHT_STARMAP_WIDTH, GlobalManager.MAP_FIGHT_STARMAP_HEIGHT);
		
		this.moneyListeners = new ArrayList<IMoneyListener>();
		this.scoreListeners = new ArrayList<IScoreListener>();
		this.money = startingMoney;
		this.moneyByTurn = moneyByTurn;
		this.moneyMax = moneyMax;
		this.thoriumMax = thoriumMax;
	}


	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getMoney() {
		return money;
	}
	
	public int getMoneyMax() {
		return moneyMax;
	}
	
	public void setMoneyMax(int moneyMax) {
		this.moneyMax = moneyMax;
	}

	public int getThoriumMax() {
		return thoriumMax;
	}

	public void setThoriumMax(int thoriumMax) {
		this.thoriumMax = thoriumMax;
	}
	

	// ===========================================================
	// Methods
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
	
	/** Add score */
	public void addScore(int points){
		this.score=this.score+points;
		//this.notifyScoreListener();
	}
	
	// Set du Castle !important
	public void registerCastle(Building castle) {
		this.castle = castle;
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

	public void addMoneyListener(IMoneyListener moneyListener){
		this.moneyListeners.add(moneyListener);
	}

	public void addScoreListener(IScoreListener scoreListener){
		this.scoreListeners.add(scoreListener);
	}

	/** Listeners **/
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


	
}
