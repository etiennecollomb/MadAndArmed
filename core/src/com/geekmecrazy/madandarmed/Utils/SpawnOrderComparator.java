package com.geekmecrazy.madandarmed.Utils;

import java.util.Comparator;

import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Game.Element.SpawnBuilding;


public class SpawnOrderComparator implements Comparator<SpawnBuilding> {

	private static SpawnOrderComparator INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	/** Singleton **/
	private SpawnOrderComparator() {}

	public static SpawnOrderComparator getInstance() {
		if(INSTANCE == null)
			INSTANCE = new SpawnOrderComparator();
		return INSTANCE;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	@Override
	public int compare(final SpawnBuilding pEntity0, final SpawnBuilding pEntity1) {
		return (pEntity0.getSpawnOrder() < pEntity1.getSpawnOrder()) ? -1 : //classement par ordre croissant
			(pEntity0.getSpawnOrder() == pEntity1.getSpawnOrder()) ? 0 : 1;
	}

}

