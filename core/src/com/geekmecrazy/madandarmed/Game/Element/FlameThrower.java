package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.FlameThrowerRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;


public class FlameThrower extends Weapon {


	FlameThrowerRenderer flameThrowerRenderer;

	public FlameThrower() {}


	public void init(float posX, float posY, Military shooter, Military target){

		super.init(posX, posY, shooter, target);

		this.flameThrowerRenderer = GlobalManager.poolAnimManager.getFlameThrowerRendererPool().obtain(); //new FlameThrowerRenderer();
		this.flameThrowerRenderer.init(this);

		ScreenManager.fightScreen.getScene().attachChild(this.flameThrowerRenderer);

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
