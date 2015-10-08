package com.geekmecrazy.madandarmed.Renderer;

import java.util.List;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class GunRenderer extends WeaponRenderer {

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
	public void setWeaponTravellingEffect(){}
	
	@Override
	protected void setWeaponEffect(final List<UniqueActionRenderer> weaponEffectList) {
		UniqueActionRenderer uniqueActionRenderer = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
		uniqueActionRenderer.init(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.IMPACT_BULLET));
		weaponEffectList.add(uniqueActionRenderer);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Weapon weapon, final Military military){
		super.init(weapon);
	}


	
}
