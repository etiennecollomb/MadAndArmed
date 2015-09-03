package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
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
		uniqueActionRenderer.init(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.MISSILE_EXPLOSION));
		this.getWeaponTravellingEffectList().add(uniqueActionRenderer);
	}
	
	@Override
	protected void setWeaponEffect() {}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Weapon weapon){
		super.init(weapon);
		
		this.attachWeaponTravellingEffect(this); //atacah to himself the travelling effect
	}




}
