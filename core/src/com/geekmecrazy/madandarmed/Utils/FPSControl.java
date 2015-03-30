package com.geekmecrazy.madandarmed.Utils;

import com.badlogic.gdx.utils.TimeUtils;


public class FPSControl {

	public static int ONE_SECOND_IN_MS = 1000000000;

	private int mNumberOfFPS;

	/** in ms */
	private int mIntervalofTime;

	private long mStartTime;

	// ===========================================================
	// Constructors
	// ===========================================================

	public FPSControl(int pNumberOfFPS) {
		this.mNumberOfFPS = pNumberOfFPS;
		this.mIntervalofTime = FPSControl.ONE_SECOND_IN_MS / mNumberOfFPS;
		this.mStartTime = TimeUtils.nanoTime();
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

	/** Logs the current frames per second to the console. */
	public void check () {
		//System.out.println("*** Real FPS : " + ONE_SECOND_IN_MS/(TimeUtils.nanoTime() - this.mStartTime));
		while (TimeUtils.nanoTime() - this.mStartTime < this.mIntervalofTime){}
		this.mStartTime = TimeUtils.nanoTime();
	}

}

