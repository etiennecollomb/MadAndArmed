package com.geekmecrazy.madandarmed.Renderer;

import java.util.List;

import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class MissileRenderer extends WeaponRenderer {

	// ===========================================================
	// Constructors
	// ===========================================================

	public MissileRenderer(){
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void reset() {
		super.reset();
	}

	@Override
	public void setWeaponTravellingEffect(){
		UniqueActionRenderer uniqueActionRenderer = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
		uniqueActionRenderer.init(PoolAnimManager.MISSILE_EXPLOSION_SPRITESHEET);
		this.getWeaponTravellingEffectList().add(uniqueActionRenderer);
	}
	
	@Override
	protected void setWeaponEffect(final List<UniqueActionRenderer> weaponEffectList) {
		UniqueActionRenderer uniqueActionRenderer = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
		uniqueActionRenderer.init(PoolAnimManager.FIRE_BLAST_001_128PX_SPRITESHEET);
		weaponEffectList.add(uniqueActionRenderer);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Weapon weapon, final Military military){
		super.init(weapon);
		
		this.attachWeaponTravellingEffect(this); //atacah to himself the travelling effect
	}




}
