package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.GameElement;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class AttackBehavior extends GameElement {

	protected Attaque attaque;
	
	private WeaponPattern weaponPattern;

	/** Target */
	private Military mainTarget;
	private Military currentTarget;
	
	/** Status */
	protected boolean attacking;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Attaque getAttaque() {
		return attaque;
	}

	public void setAttaque(Attaque attaque) {
		this.attaque = attaque;
	}

	public WeaponPattern getWeaponPattern() {
		return weaponPattern;
	}

	public Military getMainTarget() {
		return mainTarget;
	}

	public void setMainTarget(Military mainTarget) {
		this.mainTarget = mainTarget;
	}

	public Military getCurrentTarget() {
		return currentTarget;
	}

	public void setCurrentTarget(Military currentTarget) {
		this.currentTarget = currentTarget;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() {
		GlobalManager.poolManager.getAttaquePool().free(attaque);
		attaque=null;

		weaponPattern=null;
		mainTarget=null;
		currentTarget=null;

	}


	// ===========================================================
	// Methods
	// ===========================================================

	public void init(WeaponPattern weaponPattern){
		this.weaponPattern=weaponPattern;
		attacking=false;
	}
	
	public void calculate(Military m){

		// On attaque la target deja reperee
		if(this.isAttacking()){
			this.attaque.calculate(m);

		} else {

			// On est a niveau de la target poursuivis, on tire dessus
			// Si on est a distance de tir on passe en mode attaque
			if(m.getAttackBehavior().getCurrentTarget().isAlive() && this.attaque.isTirDistance(m)){
				attaque.initAttacking(m);
				this.setAttacking(true);
			}

			// On est trop loin de la target ou on en a pas
			// On se deplace vers la target ENEMY la plus proche
			else{

				Military currentTarget = m.getEnnemyTeam().getNearestMilitary(m.getSmallNodeX(), m.getSmallNodeY());

				//aucune target en vue, on focalise sur la MainTarget
				if(currentTarget==null){
					m.getAttackBehavior().setCurrentTarget(m.getAttackBehavior().getMainTarget());
				}else{
					m.getAttackBehavior().setCurrentTarget(currentTarget);
				}

			}
		}
	}

}
