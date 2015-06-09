package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.geekmecrazy.madandarmed.CoreConfig.SoundType;

public class SoundManager {

	/** Sounds */
	public static Map<SoundType, Sound> soundsType;
	
	/** Number of simultanous sounds allowed */
	public static final int MAXSOUNDS = 10;
	public static ArrayList<Long> runningSoundsID = new ArrayList<Long>();
	public static ArrayList<Long> runningSoundsStartTime = new ArrayList<Long>();
		
	// ===========================================================
	// Constructors
	// ===========================================================

	public SoundManager(){
		
		SoundManager.soundsType = new HashMap<SoundType, Sound>();
				
		for (SoundType soundType : SoundType.values()) {
			Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundType.getPath()));
			soundsType.put(soundType, sound);
		}
		
		runningSoundsID = new ArrayList<Long>();
		runningSoundsStartTime = new ArrayList<Long>();
		
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
	
	public static void playSound(SoundType soundType){

		SoundManager.checkFinishedSound();
		
		if(runningSoundsID.size() < MAXSOUNDS){
		
	    	Sound sound = SoundManager.soundsType.get(SoundType.SHOTGUN_BLAST_01);
	    	runningSoundsID.add(sound.play(1.0f));
	    	runningSoundsStartTime.add( System.currentTimeMillis() );
		}
		
	}
	
	private static void checkFinishedSound(){
		
		int size = runningSoundsID.size();
		for(int i=size-1; i>0; i--){
			/** play is finished? */
			if(runningSoundsStartTime.get(i) + (SoundType.SHOTGUN_BLAST_01.getSecondsLength()*1000) < System.currentTimeMillis() ){
				runningSoundsID.remove(i);
				runningSoundsStartTime.remove(i);
			}
		}
	}
	
}
