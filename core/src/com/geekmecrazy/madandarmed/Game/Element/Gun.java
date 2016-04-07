package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.GunRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;

public class Gun extends Weapon {

		
		public Gun() {
		}
		
		public void init(float posX, float posY, Military shooter, Military target){
			
			super.init(posX, posY, shooter, target);
			
			this.setWeaponRenderer( GlobalManager.poolAnimManager.getGunRendererPool().obtain() );
			((GunRenderer) this.getWeaponRenderer()).init(this, shooter);

			ScreenManager.fightScreen.getScene().attachChild(this.getWeaponRenderer());
			
			}

		
		@Override
		public void onUpdate(){

			/** effet immediat **/
			this.getTarget().hit(this);
			GlobalManager.fight_WeaponManager.destroyWeapon(this);

		}

}
