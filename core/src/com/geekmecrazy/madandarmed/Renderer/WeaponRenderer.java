package com.geekmecrazy.madandarmed.Renderer;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;

public abstract class WeaponRenderer extends Entity {

	Weapon weapon;

	private List<UniqueActionRenderer> weaponTravellingEffect;
	private List<UniqueActionRenderer> weaponEffect;

	// ===========================================================
	// Constructors
	// ===========================================================

	public WeaponRenderer(){
		weaponTravellingEffect = new ArrayList<UniqueActionRenderer>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public List<UniqueActionRenderer> getUniqueActionRendererList() {
		return weaponTravellingEffect;
	}

	public void setUniqueActionRendererList(
			List<UniqueActionRenderer> uniqueActionRendererList) {
		this.weaponTravellingEffect = uniqueActionRendererList;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(){
		
		this.setPosition(this.getWeapon().getPos().getX(), this.getWeapon().getPos().getY());
		this.setZIndex(GlobalManager.ZINDEXMAXVALUE - (int)this.getWeapon().getPos().getY());

		//after because Position has been modified
		super.onUpdate();
	}
		
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Weapon weapon){
		super.init(0, 0);

		this.setWeapon(weapon);
		
		this.setWeaponTravellingEffect(); //set the animations of the weapon running to the target
		this.setWeaponEffect(); //set the animations of the weapon on the target
	}
	

	/** Pattern of the weapon travelling to the unit **/
	protected abstract void setWeaponTravellingEffect();
	
	/** Pattern of the weapon to the unit **/
	protected abstract void setWeaponEffect();

}
