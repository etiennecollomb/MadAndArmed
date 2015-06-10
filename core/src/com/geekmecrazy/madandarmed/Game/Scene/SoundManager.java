package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.geekmecrazy.madandarmed.CoreConfig.SoundType;
import com.geekmecrazy.madandarmed.Json.DataLoader;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponName;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponType;


public class SoundManager {
	
	
	/** A group of sound from a weaponType that can be played together with max number for overlapping */
	private class SoundPack{

		/** Number of simultanous sounds allowed */
		private int maxOverlappingSounds;
		
		private ArrayList<SoundType> runningSoundTypes = new ArrayList<SoundType>();
		private ArrayList<Long> runningSoundsStartTime = new ArrayList<Long>();
		
		public SoundPack(final int pMaxSounds){
			this.maxOverlappingSounds = pMaxSounds;
			this.runningSoundTypes = new ArrayList<SoundType>();
			this.runningSoundsStartTime = new ArrayList<Long>();
		}
		
		// Getter & Setter
		public int getMaxOverlappingSounds() {
			return maxOverlappingSounds;
		}
		public void setMaxOverlappingSounds(int maxOverlappingSounds) {
			this.maxOverlappingSounds = maxOverlappingSounds;
		}
		public ArrayList<SoundType> getRunningSoundTypes() {
			return runningSoundTypes;
		}
		public void setRunningSoundTypes(ArrayList<SoundType> runningSoundTypes) {
			this.runningSoundTypes = runningSoundTypes;
		}
		public ArrayList<Long> getRunningSoundsStartTime() {
			return runningSoundsStartTime;
		}
		public void setRunningSoundsStartTime(ArrayList<Long> runningSoundsStartTime) {
			this.runningSoundsStartTime = runningSoundsStartTime;
		}
		
	}


	/** Sounds */
	public static Map<SoundType, Sound> soundsType;

	/** SoundsPacks */
	public static Map<WeaponName, SoundPack> soundsPacks;


	// ===========================================================
	// Constructors
	// ===========================================================

	public SoundManager(){
		
		System.out.println("Create SoundManager...");
		
		SoundManager.soundsType = new HashMap<SoundType, Sound>();
		SoundManager.soundsPacks = new HashMap<WeaponName, SoundPack>();
				
		for (SoundType soundType : SoundType.values()) {
			Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundType.getPath()));
			soundsType.put(soundType, sound);
		}
		
		for (WeaponName weaponName : WeaponName.values()) {
			
			WeaponPattern weaponPattern = DataLoader.getWeaponsPattern().get(weaponName.name());
			
			/** sound is setted ? */
			if(weaponPattern.getSoundsType() != null){
				int maxOverlappingSound = weaponPattern.getMaxOverlappingSound();
				SoundPack soundPack = new SoundPack(maxOverlappingSound);
				soundsPacks.put(weaponPattern.getWeaponName(), soundPack);
			}
			
		}

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
	
	public static void playSound(final WeaponPattern weaponPattern){

		SoundPack soundPack = SoundManager.soundsPacks.get( weaponPattern.getWeaponName() );
		
		SoundManager.checkFinishedSound(soundPack);
		
		/** Max sound Overlapping? */
		int size = soundPack.getRunningSoundTypes().size();
		if(size < soundPack.getMaxOverlappingSounds()){
			/** Min delay between Sounds? */
			if(size == 0 || soundPack.getRunningSoundsStartTime().get(size-1) + weaponPattern.getMinDelayBetweenSound() < System.currentTimeMillis()){
				
				float volume = 0.1f;
				SoundType soundType = weaponPattern.getRandometSoundsType();
		    	Sound sound = SoundManager.soundsType.get(soundType); // Random sound from list of sounds
		    	sound.play(volume);
		    	soundPack.getRunningSoundTypes().add(soundType);
		    	soundPack.getRunningSoundsStartTime().add( System.currentTimeMillis() );
		    	
			}
	    	
		}
		
	}
	
	/** Delete Finished played Sounds */
	private static void checkFinishedSound(final SoundPack soundPack){
		
		int size = soundPack.getRunningSoundTypes().size();
		for(int i=size-1; i>0; i--){
			/** play duration is finished? */
			if(soundPack.getRunningSoundsStartTime().get(i) + (soundPack.getRunningSoundTypes().get(i).getMsLength()) < System.currentTimeMillis() ){
				soundPack.getRunningSoundTypes().remove(i);
				soundPack.getRunningSoundsStartTime().remove(i);
			}
		}
	}
	
}
