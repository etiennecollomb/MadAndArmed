package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Game.Scene.MissileManager;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponType;
import com.geekmecrazy.madandarmed.Utils.Vector2d;

public class Attaque extends GameElement {
	// ===========================================================
	// Attributes
	// ===========================================================
	private Vector2d tempDistance = new Vector2d();
	private int shootCycleCounter;
	//distance de tolerance si on "glisse" et qu on s ecarte de la target pour rester sur la target.
	//En dehors on change de target , sino on reste dessus.
	private float toleranceDistanceTirCoef = 2f; //N fois la distance

	
	// ===========================================================
	// Methods
	// ===========================================================

	public void initAttacking(Military pMilitary) {
		shootCycleCounter=0; //A MIGRER, pour retarder le debut de l anim pour etre synchro avec le shoot pMilitary.getWeaponPattern().getReduceStartingFire();
	}
	
	public void calculate(Military m) {
		Military target = m.getCurrentTarget();

		//Target Vivante
		if(target.isAlive()){
			
			//Target a portee de tir?
			if(this.isTirDistance(m)){
				
				//On a recharge le tir
				if(shootCycleCounter>=m.getWeaponPattern().getHitSpeed()){
					shootCycleCounter=0;	

					//on attaque !
					if(WeaponType.MISSILE.equals(m.getWeaponPattern().getWeaponType())){
						attackMissile(m, target);
					} else if(WeaponType.GUN.equals(m.getWeaponPattern().getWeaponType())){
						 attackGun(m, target);
					} else if(WeaponType.CAC.equals(m.getWeaponPattern().getWeaponType())){
						 attackCac(m, target);
					}

				}
				shootCycleCounter++;
			}
			//Target Hors de portee
			else{
				m.setCurrentTarget(m.getMainTarget());
				m.setAttacking(false);
			}
			
		}
		//Target Morte
		else{
			m.setCurrentTarget(m.getMainTarget());
			m.setAttacking(false);
		}			
		
	}
	
	/**********************************************************/
	
	// Attaque Missile
	private void attackMissile(Military shooter, Military target){
		MissileManager.getManager().fireMissile(shooter, target);
	}
	
	// Attaque Gun
	private void attackGun(Military shooter, Military target){
		target.hit(shooter.getWeaponPattern().getDmgEffect(), shooter.getWeaponPattern().getAnimatedTextureType(), target.getPos().getX(), target.getPos().getY());
	}
	
	// Attaque CAC
	private void attackCac(Military shooter, Military target){
		target.hit(shooter.getWeaponPattern().getDmgEffect(), shooter.getWeaponPattern().getAnimatedTextureType(), target.getPos().getX(), target.getPos().getY());
	}
	
	
	/**********************************************************/

	/****************************/
	// CHECK DE DISTANCE DE TIR
	// avec la target
	/****************************/
	public boolean isTirDistance(Military m){

		boolean flag=false;
		float distanceTir=m.getWeaponPattern().getHitRange();

		this.tempDistance.set(m.getCurrentTarget().getPos());
		this.tempDistance.sub(m.getPos().getX(), m.getPos().getY());
		float distance=this.tempDistance.length();
		if(distance<=(distanceTir*toleranceDistanceTirCoef)) flag=true;
		return flag;
	}


	
	// ===========================================================
	// Recycle
	// ===========================================================

	@Override
	public void reset(){
		shootCycleCounter=0;
	}

	@Override
	public void onUpdate(){
		// TODO Auto-generated method stub
		
	}


}
