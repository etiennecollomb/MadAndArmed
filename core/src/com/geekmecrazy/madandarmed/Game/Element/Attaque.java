package com.geekmecrazy.madandarmed.Game.Element;

import java.util.List;

import com.badlogic.gdx.audio.Sound;
import com.geekmecrazy.madandarmed.CoreConfig.SoundType;
import com.geekmecrazy.madandarmed.Game.Scene.MissileManager;
import com.geekmecrazy.madandarmed.Game.Scene.SoundManager;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponType;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class Attaque extends GameElement {

	private Vector2d tempDistance = new Vector2d();
	
	private int shootCycleCounter;
	
	//Distance de tolerance, si on "glisse" et qu on s'ecarte de la target pour rester sur la target.
	//En dehors on change de target , sinon on reste dessus.
	private float toleranceDistanceTirCoef = 2f; //N fois la distance

	
    // ===========================================================
    // Constructors
    // ===========================================================
        
    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

	@Override
	public void reset(){
		shootCycleCounter=0;
	}

	@Override
	public void onUpdate(){
		// TODO Auto-generated method stub		
	}
    
    // ===========================================================
    // Methods
    // ===========================================================

	public void initAttacking(Military pMilitary) {
		shootCycleCounter=0; //TODO : A MIGRER, pour retarder le debut de l anim pour etre synchro avec le shoot pMilitary.getWeaponPattern().getReduceStartingFire();
	
	}
	
	public void calculate(Military m) {
		Military target = m.getAttackBehavior().getCurrentTarget();

		//Target Vivante
		if(target.isAlive()){
			
			//Target a portee de tir?
			if(this.isTirDistance(m)){
				
				WeaponPattern weaponPattern = m.getAttackBehavior().getWeaponPattern();
				
				//On a recharge le tir
				if(shootCycleCounter>=weaponPattern.getHitSpeed()){
					shootCycleCounter=0;	

					//On attaque !
					if(WeaponType.MISSILE.equals(weaponPattern.getWeaponType())){
						attackMissile(m, target);
					} else if(WeaponType.GUN.equals(weaponPattern.getWeaponType())){
						 attackGun(m, target);
					} else if(WeaponType.CAC.equals(weaponPattern.getWeaponType())){
						 attackCac(m, target);
					}
					
					/** Sound */
					if(weaponPattern.getSoundsType() != null){
						SoundManager.playSound(weaponPattern);
					}

				}
				shootCycleCounter++;
			}
			
			//Target Hors de portee
			else{
				m.getAttackBehavior().setCurrentTarget(m.getAttackBehavior().getMainTarget());
				m.getAttackBehavior().setAttacking(false);
			}
			
		}
		//Target Morte
		else{
			m.getAttackBehavior().setCurrentTarget(m.getAttackBehavior().getMainTarget());
			m.getAttackBehavior().setAttacking(false);
		}			
		
	}

	/** Attaque Missile */
	private void attackMissile(Military shooter, Military target){
		MissileManager.getManager().fireMissile(shooter, target);
	}
	
	/** Attaque Gun */
	private void attackGun(Military shooter, Military target){
		target.hit(shooter.getAttackBehavior().getWeaponPattern().getDmgEffect(), shooter.getAttackBehavior().getWeaponPattern().getAnimatedTextureType());
	}
	
	/** Attaque CAC */
	private void attackCac(Military shooter, Military target){
		target.hit(shooter.getAttackBehavior().getWeaponPattern().getDmgEffect(), shooter.getAttackBehavior().getWeaponPattern().getAnimatedTextureType());
	}
	
	/** Check de distance de Tir 
	 * avec la target
	 */
	//TODO: toleranceDistanceTirCoef a mettre que si on est deja en mode attack, sinon il ne faut pas
	public boolean isTirDistance(Military m){

		boolean flag=false;
		float distanceTir=m.getAttackBehavior().getWeaponPattern().getHitRange();

		this.tempDistance.set(m.getAttackBehavior().getCurrentTarget().getPos());
		this.tempDistance.sub(m.getPos().getX(), m.getPos().getY());
		float distance=this.tempDistance.length();
		if(distance<=(distanceTir*toleranceDistanceTirCoef)) flag=true;
		return flag;
	}



}
