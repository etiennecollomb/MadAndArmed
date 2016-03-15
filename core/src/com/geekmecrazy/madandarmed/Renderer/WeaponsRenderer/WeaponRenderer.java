package com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.Renderer.UniqueActionRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public abstract class WeaponRenderer extends Entity {

	Weapon weapon;

	private List<UniqueActionRenderer> weaponTravellingEffectList;

	// ===========================================================
	// Constructors
	// ===========================================================

	public WeaponRenderer(){
		weaponTravellingEffectList = new ArrayList<UniqueActionRenderer>();
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

	public void setWeaponTravellingEffectList(final 
			List<UniqueActionRenderer> weaponTravellingEffectList) {
		this.weaponTravellingEffectList = weaponTravellingEffectList;
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
		
		/** We delete finished weapon effect sprites **/
		int size = weaponTravellingEffectList.size();
		for(int i=0; i<size; i++){
			if(this.weaponTravellingEffectList.get(i).isFinished()){
				GlobalManager.poolAnimManager.getUniqueActionRendererPool().free(this.weaponTravellingEffectList.get(i));
				this.weaponTravellingEffectList.remove(i);
				size = size-1; i = i-1;
			}	
		}
	}
	
	@Override
	public void reset(){
		
		weapon = null;
		
		int size = weaponTravellingEffectList.size();
		for(int i=0; i<size; i++)
			GlobalManager.poolAnimManager.getUniqueActionRendererPool().free(this.weaponTravellingEffectList.get(i));
		weaponTravellingEffectList.clear();
		
		super.reset();
		
	}
		
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Weapon weapon){
		super.init(0, 0);

		this.setWeapon(weapon);
		
		this.setWeaponTravellingEffect(); //set the animations of the weapon running to the target
	}
	

	/** Pattern of the weapon travelling to the unit **/
	public abstract void setWeaponTravellingEffect();
	
	/** Pattern of the weapon to the unit
	 * We add to the weaponEffectList **/
	public abstract void setWeaponEffect(final List<UniqueActionRenderer> weaponEffectList);

	/** attach all UniqueActionRenderer to entity **/
	public void attachWeaponTravellingEffect(final Entity entity){
		int size = weaponTravellingEffectList.size();
		for(int i=0; i<size; i++)
			entity.attachChild(this.weaponTravellingEffectList.get(i), Entity.Alignment.CENTER);
	}
		

}
