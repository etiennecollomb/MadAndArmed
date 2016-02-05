package com.geekmecrazy.madandarmed.Utils;

import com.badlogic.gdx.utils.TimeUtils;


public class TimeIntervalControl {

	/** in ms */
	private int mIntervalofTime;

	private long mStartTime;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TimeIntervalControl(int mIntervalofTime) {
		this.mIntervalofTime = mIntervalofTime;
		this.resetTimer();
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

	/** true = still within the interval, false = time consumed */
	public boolean isInsideInterval () {
		return ( TimeUtils.nanoTime() - this.mStartTime < this.mIntervalofTime );
	}
	
	public void resetTimer() {
		this.mStartTime = TimeUtils.nanoTime();
	}

}

