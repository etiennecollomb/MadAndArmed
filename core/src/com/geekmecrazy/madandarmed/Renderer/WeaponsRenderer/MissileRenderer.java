package com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer;

import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.Renderer.UniqueActionRenderer;
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
		UniqueActionRenderer uniqueActionRenderer = GlobalManager.poolAnimManager.getUniqueActionRendererPool().obtain();
		uniqueActionRenderer.init(GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.MISSILE_EXPLOSION));
		this.getWeaponTravellingEffectList().add(uniqueActionRenderer);
	}
	
	@Override
	public void setWeaponEffect(final List<UniqueActionRenderer> weaponEffectList) {
		UniqueActionRenderer uniqueActionRenderer = GlobalManager.poolAnimManager.getUniqueActionRendererPool().obtain();
		uniqueActionRenderer.init(GlobalManager.poolAnimManager.getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_001_128PX));
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
