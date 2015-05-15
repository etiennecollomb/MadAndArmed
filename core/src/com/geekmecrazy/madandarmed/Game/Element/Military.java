package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.IA.AttackBehavior;
import com.geekmecrazy.madandarmed.Renderer.MilitaryRenderer;
import com.geekmecrazy.madandarmed.pool.PoolManager;


/**
 * Gestion des batiments et unite destrutible
 */
public class Military extends Geometrie {

	/** renderer graphic du military */
    protected MilitaryRenderer militaryRenderer;

	private Life life;
	
	/** Team */
	private Team myTeam;
	private Team ennemyTeam;
		
	protected AttackBehavior attackBehavior;
    
	/** Status */
	private boolean isAlive;

    
    // ===========================================================
    // Constructors
    // ===========================================================
    
	public Military() {
	}

	
    // ===========================================================
    // Getter & Setter
    // ===========================================================

	public Life getLife() {
		return life;
	}

	public Team getMyTeam() {
		return myTeam;
	}

	public Team getEnnemyTeam() {
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
			PoolManager.getManager().getAttackBehaviorPool().free(attackBehavior);
			attackBehavior=null;
		}

		isAlive = false;
	}
	
    
    // ===========================================================
    // Methods
    // ===========================================================
	
	public void init(float posX, float posY, float width, float height, Life life, Team myTeam, Team ennemyTeam) {
		super.init(posX, posY, width, height);
		
		this.life=life;
		this.myTeam=myTeam;
		this.ennemyTeam=ennemyTeam;
		this.isAlive = true;
        
	}

	public void onUpdateNextState() {

		/** dead */
		if(!this.isAlive()){
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

	public void hit(float getDmgEffect, AnimatedTextureType hitAnimatedType){

		life.hit(getDmgEffect);
		
		militaryRenderer.addHitActionRenderer(hitAnimatedType);

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