package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.FightWeaponManager;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.GunRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.MissileRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class Gun extends Weapon {

		
		public Gun() {
		}
		
		public void init(float posX, float posY, Military shooter, Military target){
			
			super.init(posX, posY, shooter, target);
			
			this.setWeaponRenderer( PoolAnimManager.getManager().getGunRendererPool().obtain() );
			((GunRenderer) this.getWeaponRenderer()).init(this, shooter);

			FightScreen.getManager().getScene().attachChild(this.getWeaponRenderer());
			
			}

		
		@Override
		public void onUpdate(){

			/** effet immediat **/
			this.getTarget().hit(this);
			FightWeaponManager.getManager().destroyWeapon(this);

		}

}
