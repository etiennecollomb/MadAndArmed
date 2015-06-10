package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.geekmecrazy.madandarmed.CoreConfig.SoundType;

public class SoundManager {

	/** Sounds */
	public static Map<SoundType, Sound> soundsType;
	
	/** Number of simultanous sounds allowed */
	public static final int MAXSOUNDS = 10;
	public static ArrayList<SoundType> runningSoundTypes = new ArrayList<SoundType>();
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
		
		runningSoundTypes = new ArrayList<SoundType>();
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
		
		if(runningSoundTypes.size() < MAXSOUNDS){
			float volume = 0.1f;
	    	Sound sound = SoundManager.soundsType.get(soundType);
	    	sound.play(volume);
	    	runningSoundTypes.add(soundType);
	    	runningSoundsStartTime.add( System.currentTimeMillis() );
		}
		
	}
	
	private static void checkFinishedSound(){
		
		int size = runningSoundTypes.size();
		for(int i=size-1; i>0; i--){
			/** play is finished? */
			if(runningSoundsStartTime.get(i) + (runningSoundTypes.get(i).getSecondsLength()) < System.currentTimeMillis() ){
				runningSoundTypes.remove(i);
				runningSoundsStartTime.remove(i);
			}
		}
	}
	
}
