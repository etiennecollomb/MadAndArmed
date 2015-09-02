package com.geekmecrazy.madandarmed.Game.Element;

public class Weapon extends Geometrie {

	private Military shooter;
	private Military target;
	
	/** Weapon
	 * OnUpdate : trajectory of the weapon before reaching the target and until that time, manage by WeaponManager
	 * ***.hit() : spread weapon effect on the target (list of UniqueActionRenedere with delay), weapon is destroy at this time
	 */

	// ===========================================================
	// Constructors
	// ===========================================================

	public Weapon() {}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public Military getShooter() {
		return shooter;
	}

	public void setShooter(Military shooter) {
		this.shooter = shooter;
	}
	
	public Military getTarget() {
		return target;
	}

	public void setTarget(Military target) {
		this.target = target;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate() {
		
	}
	
	@Override
	public void reset() {
		this.target = null;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	/** Pos : position of the weapon effect (can be little bit shift from the shooter itself, ie: flame thrower) **/
	public void init(final float posX, final float poxY, Military shooter, Military target){
		super.init(posX, poxY, 0);
		
		this.setTarget(target);
		this.setTarget(shooter);
	}


}
