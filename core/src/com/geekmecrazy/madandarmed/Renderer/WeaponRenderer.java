package com.geekmecrazy.madandarmed.Renderer;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;

public abstract class WeaponRenderer extends Entity {

	Weapon weapon;

	private List<UniqueActionRenderer> weaponTravellingEffectList;
	private List<UniqueActionRenderer> weaponEffectList;

	// ===========================================================
	// Constructors
	// ===========================================================

	public WeaponRenderer(){
		weaponTravellingEffectList = new ArrayList<UniqueActionRenderer>();
		weaponEffectList = new ArrayList<UniqueActionRenderer>();
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
	
	public List<UniqueActionRenderer> getWeaponTravellingEffectList() {
		return weaponTravellingEffectList;
	}

	public void setWeaponTravellingEffectList(
			List<UniqueActionRenderer> weaponTravellingEffectList) {
		this.weaponTravellingEffectList = weaponTravellingEffectList;
	}

	public List<UniqueActionRenderer> getWeaponEffectList() {
		return weaponEffectList;
	}

	public void setWeaponEffectList(List<UniqueActionRenderer> weaponEffectList) {
		this.weaponEffectList = weaponEffectList;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(){
		
		this.setPosition(this.getWeapon().getPos().getX(), this.getWeapon().getPos().getY());
		this.setZIndex(GlobalManager.ZINDEXMAXVALUE);// - (int)this.getWeapon().getPos().getY()); //todo : zindex max, doit etre au dessus de tout le monde

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

	/** attach all UniqueActionRenderer to entity **/
	public void attachWeaponTravellingEffect(final Entity entity){
		int size = weaponTravellingEffectList.size();
		for(int i=0; i<size; i++)
			entity.attachChild(this.weaponTravellingEffectList.get(i), Entity.Alignment.CENTER);
	}
	
	/** attach all UniqueActionRenderer to entity **/
	public void attachWeaponEffect(final Entity entity){
		int size = weaponEffectList.size();
		for(int i=0; i<size; i++)
			entity.attachChild(this.weaponEffectList.get(i), Entity.Alignment.CENTER);
	}
	

}
