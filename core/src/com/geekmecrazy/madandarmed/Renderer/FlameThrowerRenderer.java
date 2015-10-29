package com.geekmecrazy.madandarmed.Renderer;

import java.util.List;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.Tools.GraphicalTools;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class FlameThrowerRenderer extends WeaponRenderer {

	// ===========================================================
	// Constructors
	// ===========================================================

	public FlameThrowerRenderer(){
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void setWeaponTravellingEffect(){

		SpriteSheet sp = PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_001_128PX); //MISSILE_EXPLOSION FIRE_BLAST_001_64PX ok


		/** TODO: switch unit type **/

		/** start_angle is (2*Math.PI/16f)*graphicOrientation **/
		float fire_angle = (float) GraphicalTools.STEP_ANGLE_RADIAN * GraphicalTools.getGraphicDirection(this.getWeapon().getShooter().getNormalizedDir()) ;
		fire_angle = fire_angle + GraphicalTools.START_ANGLE_RADIAN;

		/** Position center of parent **/
		float positionX = 0;
		float positionY = 0;

		if(this.getWeapon().getShooter() instanceof Creep){

			Creep shooterCreep = (Creep)this.getWeapon().getShooter();
			switch(shooterCreep.getPattern().getCreepType()){
			
			case FLAMETHROWER:
				float deltaAngleWeapon = (float) (-Math.PI/24);
				positionX = (float)Math.cos(fire_angle + deltaAngleWeapon) * 80 ;
				positionY = (float)Math.sin(fire_angle + deltaAngleWeapon) * 80;
				positionY = positionY/GlobalManager.ISO_CIRCLE_RATIO + 2f;
				break;
				
			default:
				positionX = 0;
				positionY = 0;
				break;
			}

		}


		float distanceBetweenSprite = (float) (Math.sqrt(2.0f*5.0f*5.0f) * (float)sp.getFrameWidth(0, 0)/64.0f); // 5.0f for 64px looks good

		int delai = 0;
		int delaiIncrement = 1;
		float numberOfBalls = 12;
		float animationSpeedStart = 4.0f;

		float increment, currentValue, scale, animationSpeed;
		for(int i=0; i<=numberOfBalls-1; i++){

			//Exponential value : Exp(-2) = env. 0 to Exp(0) = 1
			increment = -2.0f + (2.0f/numberOfBalls)*(i);
			currentValue = (float) Math.exp(increment);

			scale = currentValue;
			animationSpeed = (1.0f-currentValue)*animationSpeedStart;

			UniqueActionRenderer uar = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
			uar.init(sp);
			uar.setScalable(true);
			uar.setScale(scale);
			uar.setStartDelay(delai);
			uar.setAnimationSpeed(animationSpeed);
			uar.setPosition(positionX, positionY);
			this.getWeaponTravellingEffectList().add(uar);

			delai = delai + delaiIncrement;

			/** direction of the shooter **/
			float dirX = this.getWeapon().getShooter().getNormalizedDir().getX();
			float dirY = this.getWeapon().getShooter().getNormalizedDir().getY()/GlobalManager.ISO_CIRCLE_RATIO;
			positionX = positionX + distanceBetweenSprite*dirX;
			positionY = positionY + distanceBetweenSprite*dirY;

		}

	}

	@Override
	protected void setWeaponEffect(final List<UniqueActionRenderer> weaponEffectList) {}

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Weapon weapon){
		super.init(weapon);

		this.attachWeaponTravellingEffect(this); //attach to himself the travelling effect
	}




}
