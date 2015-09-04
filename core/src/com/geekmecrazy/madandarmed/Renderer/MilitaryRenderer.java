package com.geekmecrazy.madandarmed.Renderer;

import java.util.ArrayList;
import java.util.List;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.Sprite.SpriteSheet;
import com.geekmecrazy.madandarmed.Game.Element.Military;
import com.geekmecrazy.madandarmed.Game.Element.Weapon;
import com.geekmecrazy.madandarmed.pool.PoolAnimManager;
import com.badlogic.gdx.utils.Array;


public class MilitaryRenderer extends MultiActionRenderer {

	/** Military associe au renderer */
	private Military mMilitary;

	/** Taille reel de l'image affichee */
	private float mMilitaryRealSize;

	/** List des anims de Hit*/
	private Array<UniqueActionRenderer> mHitActionRendererList = new Array<UniqueActionRenderer>();

	/** List des anims de Dead*/
	private Array<UniqueActionRenderer> mDeadActionRendererList = new Array<UniqueActionRenderer>();

	private boolean mIsDeadRenderingFinish;

	// ===========================================================
	// Constructors
	// ===========================================================

	public MilitaryRenderer(){
		super();

		this.setHitActionRendererList(new Array<UniqueActionRenderer>());
		this.setDeadActionRendererList(new Array<UniqueActionRenderer>());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Military getMilitary() {
		return mMilitary;
	}

	public void setMilitary(final Military mMilitary) {
		this.mMilitary = mMilitary;
	}

	public float getMilitaryRealSize() {
		return mMilitaryRealSize;
	}

	public void setMilitaryRealSize(final float mMilitaryRealSize) {
		this.mMilitaryRealSize = mMilitaryRealSize;
	}

	public Array<UniqueActionRenderer> getHitActionRendererList() {
		return mHitActionRendererList;
	}

	public void setHitActionRendererList(final Array<UniqueActionRenderer> mHitActionRendererList) {
		this.mHitActionRendererList = mHitActionRendererList;
	}

	public Array<UniqueActionRenderer> getDeadActionRendererList() {
		return mDeadActionRendererList;
	}

	public void setDeadActionRendererList(final Array<UniqueActionRenderer> mDeadActionRendererList) {
		this.mDeadActionRendererList = mDeadActionRendererList;
	}

	public boolean isDeadRenderingFinish() {
		return mIsDeadRenderingFinish;
	}

	public void setIsDeadRenderingFinish(final boolean mIsDeadRenderingFinish) {
		this.mIsDeadRenderingFinish = mIsDeadRenderingFinish;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onDraw(){

		if(this.isVisible()) {
			super.onDraw();
		}

		if(!this.getMilitary().isAlive()) {
			//Cas particuliers : On affiche les child qui sont pas affichés si le millitary est dead (visible = false)
			this.onDrawHit(); //on affiche qd meme le hit meme si le military est morte
			this.onDrawDead();

			//on a fini de dessiner dead? alors on peut supprimer le millitary DEFINITIVEMENT
			if(this.getDeadActionRendererList().size == 0 && this.getHitActionRendererList().size == 0){
				this.setIsDeadRenderingFinish(true);
			}
		}


	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void init(final SpriteSheet pSpriteSheet, final Military pMilitary){
		super.init(pSpriteSheet, 0, 0);

		this.getHitActionRendererList().clear();
		this.getDeadActionRendererList().clear();

		this.setMilitaryRealSize(pSpriteSheet.getAnimatedTextureTypeRoot().getRealSizeRenderer());
		this.setIsDeadRenderingFinish(false);
		this.setMilitary(pMilitary);
	}

	/** Method pour ajouter des anims de mort quand isAlive = false */
	public void setDeadPattern(){
	}

	/** C est ici que l on met les animations a afficher, elles se supperposent
	 * on peut en supperposer pls pour un hit pour faire une grose explosion
	 */
	public void addHitActionRenderer(final Weapon weapon){

		List<UniqueActionRenderer> weaponEffectList = new ArrayList<UniqueActionRenderer>();;
		weapon.getWeaponRenderer().setWeaponEffect(weaponEffectList);


		UniqueActionRenderer hitActionRenderer;

		int size = weaponEffectList.size();
		for(int i=0; i<size; i++){
			hitActionRenderer = weaponEffectList.get(i);

			//Position du hit
			float hitPosX, hitPosY;
			switch(hitActionRenderer.getSpriteSheet().getAnimatedTextureTypeRoot()){

			case IMPACT_BULLET :
				hitPosX = ((float)Math.random()*this.getMilitaryRealSize() - this.getMilitaryRealSize()/2) /2; // /2 pour centrer un peu sur le military
				hitPosY = ((float)Math.random()*this.getMilitaryRealSize() - this.getMilitaryRealSize()/2) /2; // /2 pour centrer un peu sur le military
				break;

			default:
				hitPosX = 0;
				hitPosY = 0;
				break;
			}

			hitActionRenderer.setPosition(hitPosX, hitPosY);

			/** on add l'impact renderer du military */
			this.attachChild(hitActionRenderer, Entity.Alignment.CENTER);

			this.getHitActionRendererList().add(hitActionRenderer);
		}	


	}

	/** C est ici que l on met les animations a afficher, elles se supperposent
	 * on peut en supperposer pls pour un dead pour faire pls explosions simultanees
	 */
	public void addDeadActionRenderer(final AnimatedTextureType pDeadActionTypeTexture, final float pPosX, final float pPosY, final int pStartDelay){

		UniqueActionRenderer deadActionRenderer = PoolAnimManager.getManager().getUniqueActionRendererPool().obtain();
		deadActionRenderer.init(PoolAnimManager.getManager().getSpriteSheets().get(pDeadActionTypeTexture));
		deadActionRenderer.setStartDelay(pStartDelay);
		deadActionRenderer.setPosition(pPosX, pPosY);

		/** on add le dead renderer sur le render du military */
		this.attachChild(deadActionRenderer, Entity.Alignment.CENTER);

		this.getDeadActionRendererList().add(deadActionRenderer);
	}

	/** On dessine tous les hits */
	private void onDrawHit() {

		for(int i=0; i<this.getHitActionRendererList().size; i++){
			UniqueActionRenderer hitActionRenderer = this.getHitActionRendererList().get(i);
			if(hitActionRenderer.isFinished()) {
				PoolAnimManager.getManager().getUniqueActionRendererPool().free(hitActionRenderer);
				this.getHitActionRendererList().removeIndex(i);
			}
			else{
				hitActionRenderer.onDraw();		    
			}
		}
	}

	/** On dessine tous les deads */
	private void onDrawDead() {
		
		for(int i=0; i<this.getDeadActionRendererList().size; i++){
			UniqueActionRenderer deadActionRenderer = this.getDeadActionRendererList().get(i);
			if(deadActionRenderer.isFinished()) {
				PoolAnimManager.getManager().getUniqueActionRendererPool().free(deadActionRenderer);
				this.getDeadActionRendererList().removeIndex(i);
			}
			else{
				deadActionRenderer.onDraw();		    
			}
		}
	}	

	public void reset() {
		super.reset();	

		this.resetHits();
		this.resetDeads();

		this.setMilitaryRealSize(0);
		this.setIsDeadRenderingFinish(false);
		this.setMilitary(null);
	}

	private void resetHits(){
		for(int i=0; i<this.getHitActionRendererList().size; i++){
			UniqueActionRenderer hitActionRenderer = this.getHitActionRendererList().get(i);
			PoolAnimManager.getManager().getUniqueActionRendererPool().free(hitActionRenderer);
		}		    
		this.getHitActionRendererList().clear();
	}

	private void resetDeads(){
		for(int i=0; i<this.getDeadActionRendererList().size; i++){
			UniqueActionRenderer deadActionRenderer = this.getDeadActionRendererList().get(i);
			PoolAnimManager.getManager().getUniqueActionRendererPool().free(deadActionRenderer);
		}		    
		this.getDeadActionRendererList().clear();
	}


}
