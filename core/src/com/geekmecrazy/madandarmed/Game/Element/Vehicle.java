package com.geekmecrazy.madandarmed.Game.Element;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.IA.AirMoveBehavior;
import com.geekmecrazy.madandarmed.IA.GroundMoveBehavior;
import com.geekmecrazy.madandarmed.IA.MoveBehavior;
import com.geekmecrazy.madandarmed.IA.StateMap;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Utils.Vector2d;
import com.geekmecrazy.madandarmed.pool.PoolManager;

import java.util.Random;


/** Class defining vehicles */
public abstract class Vehicle extends Military {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Attributes
	// ===========================================================
	private CreepPattern pattern;

	private Vector2d previousPos = new Vector2d();
	private Vector2d moveVector = new Vector2d();
	private MoveBehavior behavior;

    /** tricks for performance
     * We compute Path only every N cycles
     */
    private Vector2d lastMoveVector = new Vector2d();
    private Random random = new Random();
    private int maxUpdateCounter = 8; // > 8 = pathfinding wierd behavior
    private int updateCounter;
    private Vector2d tempPosition = new Vector2d();


	public Vehicle(){
	}


	// ===========================================================
	// Init
	// ===========================================================
	public void init(CreepPattern pattern, float posX, float posY, float diameter, Life life, Team myTeam, Team ennemyTeam) {
		super.init(posX, posY, diameter, diameter, life, myTeam, ennemyTeam, pattern.getWeaponPattern());
		this.pattern=pattern;

		updateCounter = random.nextInt(maxUpdateCounter);
        lastMoveVector.set(0, 0);
	}



	// ===========================================================
	// Methods
	// ===========================================================



	// ===========================================================
	// Attributes accessor
	// ===========================================================

	public MoveBehavior getBehavior() {
		return behavior;
	}

	public void setBehavior(MoveBehavior behavior) {
		this.behavior = behavior;
	}

	public CreepPattern getPattern() {
		return pattern;
	}



	// ===========================================================
	// Run item
	// ===========================================================
	public void onUpdateNextState() {
		super.onUpdateNextState();

		updateCounter++;
        if(updateCounter>=maxUpdateCounter) {
        	updateCounter=0;

            //on calcul la distance de deplacement
            previousPos.set(-this.getPos().getX(), -this.getPos().getY());
            this.behavior.calculate(this);
            moveVector.set(previousPos);
            moveVector.add(this.getPos());

            lastMoveVector.set(moveVector);

            //On set la direction du vehicle en fonction de si il attaque ou pas
            //si on attaque pas
            //la direction est celle du mouvement de deplacement
            if (!this.isAttacking()) {
                //on update localXY que si on bouge
                if (moveVector.getX() != 0.0f && moveVector.getY() != 0.0f) {

                    //moveVector.normalize();
                    setNormalizedDir(moveVector);
                    this.setMoving(true);

                } else {
                    this.setMoving(false);
                }

            } else { //si on attaque

                //>= 0.1f  normalement pour que qd on tir on bouge pas tout le temps pour les petits deplacement
                //si y a que petit changement les mec font que bouger pour se replacer
                if (moveVector.getX() >= 0.1f || moveVector.getY() >= 0.1f) {
                    this.setMoving(true);
                } else {
                    this.setMoving(false);
                }

                //En mode attaque, on focalise sur la target et non le sens de la direction
                moveVector.set(previousPos);
                moveVector.add(this.getCurrentTarget().getPos());
                //moveVector.normalize();
                setNormalizedDir(moveVector);
            }

        }else{
            this.getPos().add(lastMoveVector);
        }


        this.applyNonPenetrationConstraint();

		//Contrainte de la scene elle meme (NON penetration)
		Vector2d pos = this.getPos();
		if (pos.getX() > GlobalManager.MAP_FIGHT_WIDTH) pos.setX(GlobalManager.MAP_FIGHT_WIDTH);
		if (pos.getX() < 0.0) pos.setX(0.0f);
		if (pos.getY() > GlobalManager.MAP_FIGHT_HEIGHT) pos.setY(GlobalManager.MAP_FIGHT_HEIGHT);
		if (pos.getY() < 0.0) pos.setY(0.0f);

		this.setPos(pos.getX(), pos.getY());
	}


	// ===========================================================
	// Recycle
	// ===========================================================
	@Override
	public void reset() {
		super.reset();
		if(behavior instanceof GroundMoveBehavior)PoolManager.getManager().getGroundBehaviorPool().free((GroundMoveBehavior) behavior);
		else if(behavior instanceof AirMoveBehavior)PoolManager.getManager().getAirBehaviorPool().free((AirMoveBehavior) behavior);
		behavior=null;
	}



    public void applyNonPenetrationConstraint(){

            StateMap stateMap = this.getMyTeam().getStateMap();

            int x_ = this.getSmallNodeX();
            int y_ = this.getSmallNodeY();


            //TODO: ajouter les buildings aussi (calculer a chaque fois pour tous les buidings
            // car peu de buildings, ca dervait etre ok)
            int rayon = this.getPattern().getUnitSize().getRayon() + 1; //+1 car on doit prendre en compte les units tres proches des bords d'une smallNode (important sinon les units se poussent pas suffisement....)
            for (int i = -rayon; i <= rayon; i++)
                for (int j = -rayon; j <= rayon; j++) {

                    int tempX = x_ + i, tempY = y_ + j;

                    //gestion des bords
                    if (tempX >= 0 && tempX < stateMap.getMapWidthInSmallNodes() && tempY >= 0 && tempY < stateMap.getMapHeightInSmallNodes()) {

                        if(stateMap.getCurrentZoneAPositionMap().isUsed(tempX, tempY)) {
                            //On regarde le CREEP presents dans CurrentCreepZoneAPosition
                            Vehicle neighborVehicle = stateMap.getCurrentCreepZoneAPositionMap()[tempX][tempY];

                            //neighborVehicle!=null est normalement INUTILE car il ya lui meme ! mais bon.....
                            if (neighborVehicle != null || neighborVehicle != this) {

                                tempPosition.set(neighborVehicle.getPos());
                                tempPosition.sub(this.getPos());
                                float distance = tempPosition.length();
                                distance = distance - (this.getWidth() + neighborVehicle.getWidth());

                                if (distance < 0) {
                                    //collision
                                    tempPosition.normalize(); //la direction avec le vehicle voisin
                                    tempPosition.scale(distance / 40f * 6); //IMPORTANT : influe sur la "tassement" des unit entre elle dans les zones de bourrage

                                    tempPosition.add(this.getPos());
                                    this.setPos(tempPosition.getX(), tempPosition.getY());
                                    //return; //stopper le calcul apres UN voisin trouvé
                                }
                            }
                        }
                    }

                }

    }



}
