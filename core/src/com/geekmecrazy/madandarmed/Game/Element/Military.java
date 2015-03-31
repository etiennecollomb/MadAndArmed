package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;
import com.geekmecrazy.madandarmed.IA.AttackBehavior;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Renderer.MilitaryRenderer;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.pool.PoolManager;


/**
 * Gestion des batiments et unite destrutible
 */
public class Military extends Geometrie {

	/** coord du carre definissant l espace dans lequel
	 * un creep peut encore poursuivre un autre
	 */
	private static final int PURSUIT_DISTANCE_X = 3*3;
	private static final int PURSUIT_DISTANCE_Y = 3*3;
	private static final int PURSUIT_DISTANCE_X_2 = 3*3+1;
	private static final int PURSUIT_DISTANCE_Y_2 = 3*3+1;
	private int[] mMatrixPursuitRange;
	
	/** nombre d orientations graphiques possible des unités */
	private static final int NB_ORIENTATION = 16;
	
	/** angle de depart du spritesheet */
	private static final float START_ANGLE = 90f;

	/** renderer graphic du military */
    protected MilitaryRenderer militaryRenderer;

	private Life life;
	
	private WeaponPattern weaponPattern;
	
	/** Team */
	private Team myTeam;
	private Team ennemyTeam;
	
	/** Target */
	private Military mainTarget;
	private Military currentTarget;
	
	protected AttackBehavior attackBehavior;
    
	/** Status */
	private boolean isAlive;
	protected boolean attacking;
	protected boolean moving;
	
	protected static float[][][] graphicOrientation; //liste des valeurs cos et sin pour la direction graphique

    /** the position to go if it is a selected unit */
    protected boolean isGoPoint;
    protected Vector2d goPoint;

    
    // ===========================================================
    // Constructors
    // ===========================================================
    
	public Military() {
		this.mMatrixPursuitRange = new int[4];
		if(graphicOrientation==null)setGraphicDirection(NB_ORIENTATION, START_ANGLE);
	}

	
    // ===========================================================
    // Getter & Setter
    // ===========================================================

	public Life getLife() {
		return life;
	}

	public Team getMyTeam() {
		return myTeam;
	}

	public Team getEnnemyTeam() {
		return ennemyTeam;
	}

	public WeaponPattern getWeaponPattern() {
		return weaponPattern;
	}

	public Military getMainTarget() {
		return mainTarget;
	}

	public void setMainTarget(Military mainTarget) {
		this.mainTarget = mainTarget;
	}

	public Military getCurrentTarget() {
		return currentTarget;
	}

	public void setCurrentTarget(Military currentTarget) {
		this.currentTarget = currentTarget;
	}

	public int[] getMatrixPursuitRange() {
		return mMatrixPursuitRange;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public AttackBehavior getAttackBehavior() {
		return attackBehavior;
	}

	public void setAttackBehavior(AttackBehavior attackBehavior) {
		this.attackBehavior = attackBehavior;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isAlive() {
		return isAlive;
	}

    public boolean isGoPoint() {
        return isGoPoint;
    }

    public void setIsGoPoint(boolean isGoPoint) {
        this.isGoPoint = isGoPoint;
    }

    public Vector2d getGoPoint() {
        return goPoint;
    }

    public void setGoPoint(Vector2d goPoint) {
        this.goPoint = goPoint;
    }

    public MilitaryRenderer getMilitaryRenderer() {
        return militaryRenderer;
    }

    public void setMilitaryRenderer(MilitaryRenderer militaryRenderer) {
        this.militaryRenderer = militaryRenderer;
    }

	
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    
	@Override
	public void reset() {
		super.reset();
		
		if(life != null){
			life.reset();
			life=null;
		}

		if(attackBehavior!=null){
			PoolManager.getManager().getAttackBehaviorPool().free(attackBehavior);
			attackBehavior=null;
		}

		isAlive = false;
		attacking=false;
		moving=false;
		weaponPattern=null;
		mainTarget=null;
		currentTarget=null;

		mMatrixPursuitRange[0]=0;
		mMatrixPursuitRange[1]=0;
		mMatrixPursuitRange[2]=0;
		mMatrixPursuitRange[3]=0;
	}
	
    
    // ===========================================================
    // Methods
    // ===========================================================
	
	public void init(float posX, float posY, float width, float height, Life life, Team myTeam, Team ennemyTeam, WeaponPattern weaponPattern) {
		super.init(posX, posY, width, height);
		
		this.life=life;
		this.myTeam=myTeam;
		this.ennemyTeam=ennemyTeam;
		this.weaponPattern=weaponPattern;
		this.calculateMilitarySpace();
		this.isAlive = true;
        this.isGoPoint = false;
        this.goPoint = new Vector2d();
        
	}

	public void onUpdateNextState() {

		/** dead */
		if(!this.isAlive()){
			this.setAttacking(false);
			this.militaryRenderer.setVisible(false);
		}
		
		/** dead and death rendering finished too */
		if(!this.isAlive() && this.militaryRenderer.isDeadRenderingFinish()){
			notifyDestrution(); //destroy the military
		}
		/** alive 
		 * We attack what we can
		 * */
		else{
			if(attackBehavior!=null) attackBehavior.calculate(this);
		}
		
	}

	public void hit(float getDmgEffect, AnimatedTextureType hitAnimatedType){

		life.hit(getDmgEffect);
		
		militaryRenderer.addHitActionRenderer(hitAnimatedType);

		if(life.isDead()){
			this.noMoreLife();
		}

	}

	/** death settings */
	public void noMoreLife(){
		this.isAlive = false;
		this.militaryRenderer.setDeadPattern(); //for death rendering
	}

	/** Calcul distance de poursuite des Military
	 * Coord (x_,y_) en smallNode
	 */
	public void calculateMilitarySpace(){
		mMatrixPursuitRange[0]=this.getSmallNodeX()-PURSUIT_DISTANCE_X;
		mMatrixPursuitRange[1]=this.getSmallNodeY()-PURSUIT_DISTANCE_Y;
		mMatrixPursuitRange[2]=this.getSmallNodeX()+PURSUIT_DISTANCE_X_2;
		mMatrixPursuitRange[3]=this.getSmallNodeY()+PURSUIT_DISTANCE_Y_2;
	}

	/**
	 * startAngle is the direction 0
	 * @param nbDirection
	 * @param startAngle
	 */
	public static void setGraphicDirection(int nbDirection, float startAngle){
		
		nbDirection = nbDirection/4*4; //nbDirection must be divisible by 4 !
		graphicOrientation = new float [nbDirection/2+1][2][3];
		float pas = (float)360/(float)nbDirection;
		int direction=0;

		graphicOrientation[0][0][0]=1f;
		graphicOrientation[0][0][1]=0f;
		graphicOrientation[0][0][2]=direction;

		int i=1;
		for(float angle=pas/2; angle<180f; angle=angle+pas){
			direction=direction+1;
			float radian= (float)(angle*Math.PI/180.0f);
			float x = (float) Math.cos(radian);     
			float y = (float) Math.sin(radian);
			graphicOrientation[i][0][0]=x;
			graphicOrientation[i][0][1]=y;
			graphicOrientation[i][0][2]=direction;
			i++;
		}

		for(i=nbDirection/2; i>=0; i--){			
			graphicOrientation[i][1][0]=graphicOrientation[i][0][0];
			graphicOrientation[i][1][1]=-graphicOrientation[i][0][1];
			graphicOrientation[i][1][2]=direction;
			direction=direction+1;
			if(direction>=nbDirection) direction=0;
		}

		//we "shift" the directions regarding startAngle
		int startdirection = (int)(startAngle/pas);
		for(i=0; i<graphicOrientation.length; i++){
			graphicOrientation[i][0][2]=graphicOrientation[i][0][2]-startdirection;
			if(graphicOrientation[i][0][2]<0)
				graphicOrientation[i][0][2]=graphicOrientation[i][0][2]+nbDirection;
			graphicOrientation[i][1][2]=graphicOrientation[i][1][2]-startdirection;
			if(graphicOrientation[i][1][2]<0)
				graphicOrientation[i][1][2]=graphicOrientation[i][1][2]+nbDirection;
		}

		//PRINT
		/*
		for(i=0; i<nbDirection/2+1; i++){
			System.out.println(graphicOrientation[i][0][0]+"|"+graphicOrientation[i][0][1]+"|"+graphicOrientation[i][0][2]);
		}
		for(i=nbDirection/2; i>=0; i--){
			System.out.println(graphicOrientation[i][1][0]+"|"+graphicOrientation[i][1][1]+"|"+graphicOrientation[i][1][2]);
		}
		 */
	}


	/** give the graphic direction from a vector */
	public int getGraphicDirection(){

		//this.getNormalizedDir() doit toujours etre normalise	!!!!!!
		float x = this.getNormalizedDir().getX();
		float y = this.getNormalizedDir().getY();
		int size= graphicOrientation.length-1;
		int halfsize= size/2;

		int i;
		if(x>=0){ for(i=0; i<halfsize; i++) if(graphicOrientation[i+1][0][0]<x) break;}
		else{ for(i=halfsize; i<size; i++) if(graphicOrientation[i+1][0][0]<x) break;}

		//trigonometric (y>0)
		//if (y>0) return (int)graphicOrientation[i][0][2];
		//return (int)graphicOrientation[i][1][2];

        //non-trigonometric ie Y-axis inversion (y<0)
		if (y<0) return (int)graphicOrientation[i][0][2];
		return (int)graphicOrientation[i][1][2];
	}

	
	public void notifyDestrution() {}


}