package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.IA.AttackBehavior;
import com.geekmecrazy.madandarmed.Renderer.MilitaryRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;


/**
 * Gestion des batiments et unite destrutible
 */
public class Military extends Geometrie {

	/** renderer graphic du military */
    protected MilitaryRenderer militaryRenderer;

	private Life life;
	
	/** Team */
	private GamePlay_Team myTeam;
	private GamePlay_Team ennemyTeam;
		
	protected AttackBehavior attackBehavior;
	
	/** Radius Visibility of the military to other unit 
	 * in Small Node
	*/
	protected int visibilityRadiusRange;

	/** Status */
	private boolean isAlive;

    
    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

	public Life getLife() {
		return life;
	}

	public GamePlay_Team getMyTeam() {
		return myTeam;
	}

	public GamePlay_Team getEnnemyTeam() {
		return ennemyTeam;
	}

	public AttackBehavior getAttackBehavior() {
		return attackBehavior;
	}

	public void setAttackBehavior(AttackBehavior attackBehavior) {
		this.attackBehavior = attackBehavior;
	}

	public boolean isAlive() {
		return isAlive;
	}

    public MilitaryRenderer getMilitaryRenderer() {
        return militaryRenderer;
    }

    public void setMilitaryRenderer(MilitaryRenderer militaryRenderer) {
        this.militaryRenderer = militaryRenderer;
    }

	public int getVisibilityRadiusRange() {
		return visibilityRadiusRange;
	}


	public void setVisibilityRadiusRange(int visibilityRadiusRange) {
		this.visibilityRadiusRange = visibilityRadiusRange;
	}
	
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    
	@Override
	public void reset() {
		super.reset();
		
		if(life != null){
			life.reset();
			life=null;
		}

		if(attackBehavior!=null){
			GlobalManager.poolManager.getAttackBehaviorPool().free(attackBehavior);
			attackBehavior=null;
		}

		isAlive = false;
	}
	
    
    // ===========================================================
    // Methods
    // ===========================================================
	
	public void init(float posX, float posY, float diameter, Life life, GamePlay_Team myTeam) {
		super.init(posX, posY, diameter);
		
		this.life=life;
		this.myTeam=myTeam;
		
		/** set enemyTeam if we are in fight Screen only **/
		if(ScreenManager.getCurrentScreen() instanceof FightScreen)
			this.ennemyTeam = ScreenManager.fightScreen.getOtherTeam(myTeam);
		else
			this.ennemyTeam = null;

		this.isAlive = true;
		this.militaryRenderer = null;
		
		this.visibilityRadiusRange = 50; //TODO : a parametrer dynamiquement
        
	}

	public void onUpdateNextState() {

		/** dead */
		if(!this.isAlive()){
			if(this.getAttackBehavior() != null)
				this.getAttackBehavior().setAttacking(false);
			this.militaryRenderer.setVisible(false);
		}
		
		/** dead and death rendering finished too */
		if(!this.isAlive() && this.militaryRenderer.isDeadRenderingFinish()){
			notifyDestrution(); //destroy the military
		}
		/** alive 
		 * We attack what we can
		 * */
		else{
			if(attackBehavior!=null) attackBehavior.calculate(this);
		}
		
		
		
	}

	public void hit(final Weapon weapon){

		life.hit(weapon.getDmgEffect());
		
		militaryRenderer.addHitActionRenderer(weapon);

		if(life.isDead()){
			this.noMoreLife();
		}

	}

	/** death settings */
	public void noMoreLife(){
		this.isAlive = false;
		this.militaryRenderer.setDeadPattern(); //for death rendering
	}
	
	public void notifyDestrution() {}


}