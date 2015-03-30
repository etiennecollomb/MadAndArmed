package com.geekmecrazy.madandarmed.Utils;

import java.util.Comparator;
import com.geekmecrazy.madandarmed.Entity.Entity;


public class ZIndexComparator implements Comparator<Entity> {

	private static ZIndexComparator INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	/** Singleton **/
	private ZIndexComparator() {}

	public static ZIndexComparator getInstance() {
		if(INSTANCE == null)
			INSTANCE = new ZIndexComparator();
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
	public int compare(final Entity pEntity0, final Entity pEntity1) {
		return (pEntity0.getZIndex() < pEntity1.getZIndex()) ? -1 : //classement par ordre croissant
			(pEntity0.getZIndex() == pEntity1.getZIndex()) ? 0 : 1;
	}

}

