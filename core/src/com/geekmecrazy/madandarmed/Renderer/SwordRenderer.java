package com.geekmecrazy.madandarmed.Renderer;

import java.util.List;

import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class SwordRenderer extends WeaponRenderer {

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
	public void setWeaponTravellingEffect(){
	}
	
	@Override
	protected void setWeaponEffect(final List<UniqueActionRenderer> weaponEffectList) {
		UniqueActionRenderer uniqueActionRenderer = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
		uniqueActionRenderer.init(PoolAnimManager.SWORD_001_256PX_SPRITESHEET);
		weaponEffectList.add(uniqueActionRenderer);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Weapon weapon){
		super.init(weapon);
	}


	
}
