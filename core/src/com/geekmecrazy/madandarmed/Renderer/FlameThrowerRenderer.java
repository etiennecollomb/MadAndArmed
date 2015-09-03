package com.geekmecrazy.madandarmed.Renderer;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
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
	public void reset() {
		super.reset();
	}

	@Override
	public void setWeaponTravellingEffect(){

		SpriteSheet sp = new SpriteSheet(AnimatedTextureType.FIRE_BLAST_001_64PX, true); //MISSILE_EXPLOSION FIRE_BLAST_001_64PX ok
		
    	float positionX = this.getWeapon().getPos().getX();
    	float positionY = this.getWeapon().getPos().getY();
    	
    	float distanceBetweenSprite = (float) (Math.sqrt(2.0f*5.0f*5.0f) * (float)sp.getFrameSize(0, 0)/64.0f); // 5.0f for 64px looks good

    	int delai = 0;
    	int delaiIncrement = 1;
    	float numberOfBalls = 7;
    	float animationSpeedStart = 4.0f;
    	
    	float increment, currentValue, scale, animationSpeed;
    	for(int i=0; i<=numberOfBalls; i++){
    			        
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
    		float dirY = this.getWeapon().getShooter().getNormalizedDir().getY();
	        positionX = positionX + distanceBetweenSprite*dirX;
	        positionY = positionY + distanceBetweenSprite*dirY;
	        
    	}
    	
	}
	
	@Override
	protected void setWeaponEffect() {}
	
	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final Weapon weapon){
		super.init(weapon);
		
		this.attachWeaponTravellingEffect(this); //attach to himself the travelling effect
	}




}
