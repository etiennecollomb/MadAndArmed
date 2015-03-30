package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.GameElement;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class AttackBehavior extends GameElement {
	protected Attaque attaque;

	public void calculate(Military m){

		// On attaque la target deja reperee
		if(m.isAttacking()){

			this.attaque.calculate(m);

		} else {

			// On est a niveau de la target poursuivis, on tir desssu
			if(m.getCurrentTarget().isAlive() && this.attaque.isTirDistance(m)){
				// Si on est a distance de tir on passe en mode attaque
				attaque.initAttacking(m);
				m.setAttacking(true);

			}else{

				// On se deplace vers la target ENEMY la plus proche (soit une nouvelle soit celle en cours)
				Military currentTarget = m.getEnnemyTeam().getNearestMilitary(m.getSmallNodeX(), m.getSmallNodeY());
				if(currentTarget==null){
					m.setCurrentTarget(m.getMainTarget());
				}else{
					m.setCurrentTarget(currentTarget);
				}

			}
		}
	}


	public Attaque getAttaque() {
		return attaque;
	}

	public void setAttaque(Attaque attaque) {
		this.attaque = attaque;
	}


	@Override
	public void reset() {
    	PoolManager.getManager().getAttaquePool().free(attaque);
    	attaque=null;
	}


	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

}
