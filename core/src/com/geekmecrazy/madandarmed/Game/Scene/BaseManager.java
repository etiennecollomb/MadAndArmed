package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import com.geekmecrazy.madandarmed.Game.Element.Barricade;
import com.geekmecrazy.madandarmed.Game.Element.BaseBuilding;
import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;
import com.geekmecrazy.madandarmed.Game.Element.Turret;

/** Gestion de l'etat de la base (sert aussi pour l edition du coup) **/

public class BaseManager {

	private ArrayList<Barricade> listBarricades;
	private ArrayList<Turret> listTurrets;
	private ArrayList<BaseBuilding> listBaseBuildings;
	private ArrayList<SpawnBuilding> listSpawnBuildings;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseManager(){
		
		this.listBarricades = new ArrayList<Barricade>();
		this.listTurrets = new ArrayList<Turret>();
		this.listBaseBuildings = new ArrayList<BaseBuilding>();
		this.listSpawnBuildings = new ArrayList<SpawnBuilding>();
	}

	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
    
	public ArrayList<Barricade> getListBarricades() {
		return listBarricades;
	}

	public ArrayList<Turret> getListTurrets() {
		return listTurrets;
	}

	public ArrayList<BaseBuilding> getListBaseBuildings() {
		return listBaseBuildings;
	}

	public ArrayList<SpawnBuilding> getListSpawnBuildings() {
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
		this.listBarricades.remove(barricade);
	}
	
	public void removeTurret(Turret turret){
		this.listTurrets.remove(turret);
	}
	
	public void removeBaseBuilding(BaseBuilding basebuilding){
		this.listBaseBuildings.remove(basebuilding);
	}
	
	public void removeSpawnBuilding(SpawnBuilding spawnbuilding){
		this.listSpawnBuildings.remove(spawnbuilding);
	}
	
	
	
	
}
