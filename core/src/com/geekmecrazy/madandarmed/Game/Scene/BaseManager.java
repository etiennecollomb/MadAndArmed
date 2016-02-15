package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;
import com.geekmecrazy.madandarmed.Game.Element.Barricade;
import com.geekmecrazy.madandarmed.Game.Element.BaseBuilding;
import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Turret;
import com.geekmecrazy.madandarmed.Utils.SpawnOrderComparator;
import com.geekmecrazy.madandarmed.Utils.ZIndexComparator;

/** Gestion de l'etat de la base (sert aussi pour l edition du coup) **/

public class BaseManager {

	private Array<Barricade> listBarricades;
	private Array<Turret> listTurrets;
	private Array<BaseBuilding> listBaseBuildings;
	private Array<SpawnBuilding> listSpawnBuildings;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseManager(){
		
		this.listBarricades = new Array<Barricade>();
		this.listTurrets = new Array<Turret>();
		this.listBaseBuildings = new Array<BaseBuilding>();
		this.listSpawnBuildings = new Array<SpawnBuilding>();
	}

	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Array<Barricade> getListBarricades() {
		return listBarricades;
	}

	public Array<Turret> getListTurrets() {
		return listTurrets;
	}

	public Array<BaseBuilding> getListBaseBuildings() {
		return listBaseBuildings;
	}

	public Array<SpawnBuilding> getListSpawnBuildings() {
		return listSpawnBuildings;
	}

	// ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
	
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	public void addBarricade(Barricade barricade){
		this.listBarricades.add(barricade);
	}
	
	public void addTurret(Turret turret){
		this.listTurrets.add(turret);
	}
	
	public void addBaseBuilding(BaseBuilding basebuilding){
		this.listBaseBuildings.add(basebuilding);
	}
	
	public void addSpawnBuilding(SpawnBuilding spawnbuilding){
		this.listSpawnBuildings.add(spawnbuilding);
	}
	
	
	public void removeBarricade(Barricade barricade){
		this.listBarricades.removeValue(barricade, true);
	}
	
	public void removeTurret(Turret turret){
		this.listTurrets.removeValue(turret, true);
	}
	
	public void removeBaseBuilding(BaseBuilding basebuilding){
		this.listBaseBuildings.removeValue(basebuilding, true);
	}
	
	public void removeSpawnBuilding(SpawnBuilding spawnbuilding){
		this.listSpawnBuildings.removeValue(spawnbuilding, true);
	}
	
	/** Spawn Building Order **/
	public void sortSpawBuilding() {
        this.listSpawnBuildings.sort(SpawnOrderComparator.getInstance());
    }
	
	
	
}
