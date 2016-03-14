package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.Fight_WeaponManager;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.MeshMultiExplosionRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class MeshMultiExplosion extends Weapon {


	MeshMultiExplosionRenderer meshMultiExplosionRenderer;

	public MeshMultiExplosion() {}


	public void init(float posX, float posY, Military shooter, Military target){

		super.init(posX, posY, shooter, target);

		this.meshMultiExplosionRenderer = PoolAnimManager.getManager().getMeshMultiExplosionRendererPool().obtain();
		this.meshMultiExplosionRenderer.init(this);

		GlobalManager.fightScreen.getScene().attachChild(this.meshMultiExplosionRenderer);

	}


	@Override
	public void onUpdate(){

		/** anim is finished we destroy it **/
		if(this.meshMultiExplosionRenderer.getWeaponTravellingEffectList().size() == 0)
			Fight_WeaponManager.getManager().destroyWeapon(this);

	}


	@Override
	public void reset() {
		super.reset();
	}

}
