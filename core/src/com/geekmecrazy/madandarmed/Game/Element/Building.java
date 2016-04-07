package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Game.Factory.BuildingFactory;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;


public class Building extends Military{

	private BuildingPattern pattern;
		
    // ===========================================================
    // Constructors
    // ===========================================================
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================
	
	public BuildingPattern getPattern() {
		return pattern;
	}
	
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

	@Override
	public void reset() {
		super.reset();
		
		this.militaryRenderer.detachSelf();
		
		pattern=null;

	}

	@Override
	public void notifyDestrution() {
		BuildingFactory.destroy(this);
	}

    // ===========================================================
    // Methods
    // ===========================================================
    
	public void init(float posX, float posY, float diameter, BuildingPattern buildingPattern, Life life, GamePlay_Team myTeam) {
		super.init(posX, posY, diameter, life, myTeam);

		this.pattern=buildingPattern;

	}
	
	/**si plus de vie , alors on enleve le building du jeu
	 * (on fait le recycle Full une fois l anim de mort faite)
	 */
	/** TODO: a isoler dans les classes Fights!! ici classe generic GamePlay **/
	@Override
	public void noMoreLife(){
		super.noMoreLife();

		this.getMyTeam().removeMilitary(this);
		this.getMyTeam().getStateMap().removeBuilding(this);
		
		/** TODO: a isoler dans les classes Fights!! ici classe generic GamePlay **/
		ScreenManager.fightScreen.getOtherTeam((Fight_Team)this.getMyTeam()).addScore(this.getPattern().getPrice());

	}
	

}
