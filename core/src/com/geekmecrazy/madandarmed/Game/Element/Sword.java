package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.WeaponManager;
import com.geekmecrazy.madandarmed.Renderer.GunRenderer;
import com.geekmecrazy.madandarmed.Renderer.MissileRenderer;
import com.geekmecrazy.madandarmed.Renderer.SwordRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class Sword extends Weapon {

		
		public Sword() {
		}
		
		public void init(float posX, float posY, Military shooter, Military target){
			
			super.init(posX, posY, shooter, target);
			
			this.setWeaponRenderer( PoolAnimManager.getManager().getSwordRendererPool().obtain() );
			((SwordRenderer) this.getWeaponRenderer()).init(this, shooter);

			FightScreen.getManager().getScene().attachChild(this.getWeaponRenderer());
			
			}

		
		@Override
		public void onUpdate(){

			/** effet immediat **/
			this.getTarget().hit(this);
			WeaponManager.getManager().destroyWeapon(this);

		}

}
