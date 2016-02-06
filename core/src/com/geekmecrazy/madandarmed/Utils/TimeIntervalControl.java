package com.geekmecrazy.madandarmed.Utils;

import com.badlogic.gdx.utils.TimeUtils;


public class TimeIntervalControl {

	public static int ONE_MILLISECOND_IN_NANOSECONDE = 1000000;

	private int mIntervalofTime; /** in ms */

	private long mEndTime;

	// ===========================================================
	// Constructors
	// ===========================================================

	/** mIntervalofTime in Milli Seconde **/
	public TimeIntervalControl(int mIntervalofTime) {
		this.mIntervalofTime = mIntervalofTime * ONE_MILLISECOND_IN_NANOSECONDE;
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
		return ( TimeUtils.nanoTime() < this.mEndTime );
	}
	
	public void resetTimer() {
		this.mEndTime = TimeUtils.nanoTime() + this.mIntervalofTime;
	}

}

