package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.Fight_WeaponManager;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.FlameThrowerRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class FlameThrower extends Weapon {


	FlameThrowerRenderer flameThrowerRenderer;

	public FlameThrower() {}


	public void init(float posX, float posY, Military shooter, Military target){

		super.init(posX, posY, shooter, target);

		this.flameThrowerRenderer = PoolAnimManager.getManager().getFlameThrowerRendererPool().obtain(); //new FlameThrowerRenderer();
		this.flameThrowerRenderer.init(this);

		GlobalManager.fightScreen.getScene().attachChild(this.flameThrowerRenderer);

	}


	@Override
	public void onUpdate(){

		/** anim is finished we destroy it **/
		if(this.flameThrowerRenderer.getWeaponTravellingEffectList().size() == 0)
			GlobalManager.fight_WeaponManager.destroyWeapon(this);

	}


	@Override
	public void reset() {
		super.reset();
	}

}
