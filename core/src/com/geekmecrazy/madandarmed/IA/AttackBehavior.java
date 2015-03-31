package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.GameElement;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class AttackBehavior extends GameElement {

	protected Attaque attaque;

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


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() {
		PoolManager.getManager().getAttaquePool().free(attaque);
		attaque=null;
	}


	// ===========================================================
	// Methods
	// ===========================================================

	public void calculate(Military m){

		// On attaque la target deja reperee
		if(m.isAttacking()){
			this.attaque.calculate(m);

		} else {

			// On est a niveau de la target poursuivis, on tire dessus
			// Si on est a distance de tir on passe en mode attaque
			if(m.getCurrentTarget().isAlive() && this.attaque.isTirDistance(m)){
				attaque.initAttacking(m);
				m.setAttacking(true);
			}

			// On est trop loin de la target ou on en a pas
			// On se deplace vers la target ENEMY la plus proche
			else{

				Military currentTarget = m.getEnnemyTeam().getNearestMilitary(m.getSmallNodeX(), m.getSmallNodeY());

				//aucune target en vue, on focalise sur la MainTarget
				if(currentTarget==null){
					m.setCurrentTarget(m.getMainTarget());
				}else{
					m.setCurrentTarget(currentTarget);
				}

			}
		}
	}


}
