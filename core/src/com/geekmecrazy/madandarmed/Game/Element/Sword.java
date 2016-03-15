package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Game.Scene.Fight_WeaponManager;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.GunRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.MissileRenderer;
import com.geekmecrazy.madandarmed.Renderer.WeaponsRenderer.SwordRenderer;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;

public class Sword extends Weapon {

		
		public Sword() {
		}
		
		public void init(float posX, float posY, Military shooter, Military target){
			
			super.init(posX, posY, shooter, target);
			
			this.setWeaponRenderer( PoolAnimManager.getManager().getSwordRendererPool().obtain() );
			((SwordRenderer) this.getWeaponRenderer()).init(this, shooter);

			GlobalManager.fightScreen.getScene().attachChild(this.getWeaponRenderer());
			
			}

		
		@Override
		public void onUpdate(){

			/** effet immediat **/
			this.getTarget().hit(this);
			GlobalManager.fight_WeaponManager.destroyWeapon(this);

		}

}
