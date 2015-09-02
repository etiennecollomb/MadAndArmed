package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.Entity.Entity;
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
		//Put pSpriteSheet in the uniqueAnmitedSprite list
		UniqueActionRenderer deadActionRenderer = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
		deadActionRenderer.init(PoolAnimManager.getManager().getSpriteSheets().get(pDeadActionTypeTexture));
		deadActionRenderer.setStartDelay(pStartDelay);
		deadActionRenderer.setPosition(pPosX, pPosY);

		/** on add le dead renderer sur le render du military */
		this.attachChild(deadActionRenderer, Entity.Alignment.CENTER);

		this.getDeadActionRendererList().add(deadActionRenderer);
	}

	@Override
	protected void setWeaponEffect() {
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Weapon weapon){
		super.init(weapon);
	}




}
