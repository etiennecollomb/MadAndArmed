package com.geekmecrazy.madandarmed.Renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Creep;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.Tools.GraphicalTools;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;


public class MeshMultiExplosionRenderer extends WeaponRenderer {

	// ===========================================================
	// Constructors
	// ===========================================================

	public MeshMultiExplosionRenderer(){
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

		Random random = new Random();

		SpriteSheet sp = PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.HALO_BLUE_192PX);
		SpriteSheet sp2 = PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_004_128PX);

		List<SpriteSheet> explosionsList = new ArrayList<SpriteSheet>();
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_001_128PX));
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_002_128PX));
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_003_128PX));
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_004_128PX));
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_005_128PX));

		/** fire_angle is (2*Math.PI/16f)*graphicOrientation **/
		float fire_angle = (float) GraphicalTools.STEP_ANGLE_RADIAN * GraphicalTools.getGraphicDirection(this.getWeapon().getShooter().getNormalizedDir()) ;
		fire_angle = fire_angle + GraphicalTools.START_ANGLE_RADIAN;

		/** Position center of parent **/
		float positionX = 0;
		float positionY = 0;
		
		/** direction of the shooter **/
		float dirX = this.getWeapon().getShooter().getNormalizedDir().getX();
		float dirY = this.getWeapon().getShooter().getNormalizedDir().getY()/GlobalManager.ISO_CIRCLE_RATIO;
		

		float deltaAngleWeapon = (float) (+Math.PI/5);
		float posX_ = (float)Math.cos(fire_angle + deltaAngleWeapon) * 80 ;
		float posY_ = (float)Math.sin(fire_angle + deltaAngleWeapon) * 80;
		posY_ = posY_/1.5f - 20f;
		fireThrowerMesh(positionX+posX_, positionY+posY_, dirX, dirY, fire_angle, 0);

		deltaAngleWeapon = (float) (-Math.PI/5);
		posX_ = (float)Math.cos(fire_angle + deltaAngleWeapon) * 80 ;
		posY_ = (float)Math.sin(fire_angle + deltaAngleWeapon) * 80;
		posY_ = posY_/1.5f - 20f;
		fireThrowerMesh(positionX+posX_, positionY+posY_, dirX, dirY, fire_angle, 1);
		

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
	
	
	public void fireThrowerMesh(final float posX, final float posY, final float dirX, final float dirY, final float angle, final float weaponID){

		Random random = new Random();

		SpriteSheet sp = PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.HALO_BLUE_192PX);
		SpriteSheet sp2 = PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_004_128PX);

		List<SpriteSheet> explosionsList = new ArrayList<SpriteSheet>();
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_001_128PX));
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_002_128PX));
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_003_128PX));
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_004_128PX));
		explosionsList.add(PoolAnimManager.getManager().getSpriteSheets().get(AnimatedTextureType.FIRE_BLAST_005_128PX));


		float positionX = posX;
		float positionY = posY;

		/** Jet Explosion **/
		float distanceBetweenSprite = (float) (Math.sqrt(2.0f*5.0f*5.0f) * (float)sp.getFrameWidth(0, 0)/64.0f); // 5.0f for 64px looks good

		int delai = 0;
		int delaiIncrement = 1;
		float numberOfBalls = 12;
		float numberOfEndingExplosions = 10;
		float animationSpeedStart = 4.0f;

		float increment, currentValue, scale, animationSpeed;
		for(int i=0; i<=numberOfBalls; i++){

			/** We print only if not hide by the mesh - specific case for mesh **/
			if(!(
				((weaponID==0)&&(angle>=1* Math.PI*2/16-Math.PI/16)&&(angle<=1* Math.PI*2/16+Math.PI/16)&&(i<1))
				||((weaponID==0)&&(angle>=2* Math.PI*2/16-Math.PI/16)&&(angle<=2* Math.PI*2/16+Math.PI/16)&&(i<2))
				||((weaponID==0)&&(angle>=3* Math.PI*2/16-Math.PI/16)&&(angle<=3* Math.PI*2/16+Math.PI/16)&&(i<3))
				||((weaponID==1)&&(angle>=5* Math.PI*2/16-Math.PI/16)&&(angle<=5* Math.PI*2/16+Math.PI/16)&&(i<3))
				||((weaponID==1)&&(angle>=6* Math.PI*2/16-Math.PI/16)&&(angle<=6* Math.PI*2/16+Math.PI/16)&&(i<2))
				||((weaponID==1)&&(angle>=7* Math.PI*2/16-Math.PI/16)&&(angle<=7* Math.PI*2/16+Math.PI/16)&&(i<1))
				)) {
				
				//Exponential value : Exp(-2) = env. 0 to Exp(0) = 1
				increment = -2.0f + (2.0f/numberOfBalls)*(i);
				currentValue = (float) Math.exp(increment);

				scale = currentValue;
				animationSpeed = (1.0f-currentValue)*animationSpeedStart;

				/** Under explosion effect **/
				UniqueActionRenderer uar = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
				uar.init(sp);
				uar.setScalable(true);
				uar.setScale(scale);
				uar.setStartDelay(delai);
				uar.setAnimationSpeed(animationSpeed/3f);
				uar.setPosition(positionX, positionY);
				this.getWeaponTravellingEffectList().add(uar);

				/** Explosion effect **/
				UniqueActionRenderer uar2 = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
				uar2.init(sp2);
				uar2.setScalable(true);
				uar2.setScale(scale);
				uar2.setStartDelay(delai);
				uar2.setAnimationSpeed(animationSpeed);
				uar2.setPosition(positionX, positionY);
				this.getWeaponTravellingEffectList().add(uar2);
			}

			delai = delai + delaiIncrement;

			positionX = positionX + distanceBetweenSprite*dirX;
			positionY = (float) (positionY + distanceBetweenSprite*dirY/GlobalManager.ISO_CIRCLE_RATIO);

		}

		/** MASS explosion **/
		float explosionsWidth = (5f);
		float explosionsWidthIncrement = 150f/5f/numberOfEndingExplosions;

		for(int j=0; j<=4; j++){
			for(int i=0; i<=numberOfEndingExplosions; i++){

				float x_ = (float) (positionX + Math.random()*2f*explosionsWidth-explosionsWidth);
				float y_ = (float) (positionY + Math.random()*2f*explosionsWidth-explosionsWidth);

				SpriteSheet sp_explosion = explosionsList.get(random.nextInt(explosionsList.size()));

				/** Explosion effect **/
				UniqueActionRenderer uar3 = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
				uar3.init(sp_explosion);
				uar3.setScalable(true);
				//uar3.setScale(scale);
				uar3.setStartDelay(delai);
				//uar3.setAnimationSpeed(animationSpeed);
				uar3.setPosition(x_, y_);
				this.getWeaponTravellingEffectList().add(uar3);

				delai = delai + 2;
				explosionsWidth = explosionsWidth + explosionsWidthIncrement;
			}
		}


	}
	




}
