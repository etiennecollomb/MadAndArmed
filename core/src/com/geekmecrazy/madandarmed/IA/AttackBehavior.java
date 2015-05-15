package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.Attaque;
import com.geekmecrazy.madandarmed.Game.Element.GameElement;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.pool.PoolManager;


public class AttackBehavior extends GameElement {

	/** coord du carre definissant l espace dans lequel
	 * un creep peut encore poursuivre un autre
	 */
	private static final int PURSUIT_DISTANCE_X = 3*3;
	private static final int PURSUIT_DISTANCE_Y = 3*3;
	private static final int PURSUIT_DISTANCE_X_2 = 3*3+1;
	private static final int PURSUIT_DISTANCE_Y_2 = 3*3+1;
	private int[] mMatrixPursuitRange;
	
	protected Attaque attaque;
	
	private WeaponPattern weaponPattern;

	/** Target */
	private Military mainTarget;
	private Military currentTarget;
	

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public AttackBehavior(){
		this.mMatrixPursuitRange = new int[4];
	}
	
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

	public int[] getMatrixPursuitRange() {
		return mMatrixPursuitRange;
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

		weaponPattern=null;
		mainTarget=null;
		currentTarget=null;

		mMatrixPursuitRange[0]=0;
		mMatrixPursuitRange[1]=0;
		mMatrixPursuitRange[2]=0;
		mMatrixPursuitRange[3]=0;
	}


	// ===========================================================
	// Methods
	// ===========================================================

	public void init(WeaponPattern weaponPattern){
		this.weaponPattern=weaponPattern;
		//this.calculateMilitarySpace();
	}
	
	public void calculate(Military m){

		// On attaque la target deja reperee
		if(m.isAttacking()){
			this.attaque.calculate(m);

		} else {

			// On est a niveau de la target poursuivis, on tire dessus
			// Si on est a distance de tir on passe en mode attaque
			if(m.getAttackBehavior().getCurrentTarget().isAlive() && this.attaque.isTirDistance(m)){
				attaque.initAttacking(m);
				m.setAttacking(true);
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
	

	/** Calcul distance de poursuite des Military
	 * Coord (x_,y_) en smallNode
	 */
	public void calculateMilitarySpace(Military m){
		mMatrixPursuitRange[0]=m.getSmallNodeX()-PURSUIT_DISTANCE_X;
		mMatrixPursuitRange[1]=m.getSmallNodeY()-PURSUIT_DISTANCE_Y;
		mMatrixPursuitRange[2]=m.getSmallNodeX()+PURSUIT_DISTANCE_X_2;
		mMatrixPursuitRange[3]=m.getSmallNodeY()+PURSUIT_DISTANCE_Y_2;
	}


}
