package com.geekmecrazy.madandarmed.Game.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.geekmecrazy.madandarmed.CoreConfig.SoundType;
import com.geekmecrazy.madandarmed.Json.DataLoader;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern;
import com.geekmecrazy.madandarmed.Pattern.WeaponPattern.WeaponName;


public class SoundManager {


	/** A group of sound from a weaponType that can be played together with max number for overlapping */
	private class SoundPack{

		/** Number of simultanous sounds allowed */
		private int maxOverlappingSounds;

		private ArrayList<SoundType> runningSoundTypes = new ArrayList<SoundType>();
		private ArrayList<Long> runningSoundID = new ArrayList<Long>();
		private ArrayList<Long> runningSoundsStartTime = new ArrayList<Long>();

		public SoundPack(final int pMaxSounds){
			this.maxOverlappingSounds = pMaxSounds;
			this.runningSoundTypes = new ArrayList<SoundType>();
			this.runningSoundID = new ArrayList<Long>();
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
		public ArrayList<Long> getRunningSoundID() {
			return runningSoundID;
		}
		public void setRunningSoundID(ArrayList<Long> runningSoundID) {
			this.runningSoundID = runningSoundID;
		}
		public ArrayList<Long> getRunningSoundsStartTime() {
			return runningSoundsStartTime;
		}
		public void setRunningSoundsStartTime(ArrayList<Long> runningSoundsStartTime) {
			this.runningSoundsStartTime = runningSoundsStartTime;
		}

	}

	public static boolean isSoundON;

	/** Music background */
	public static Music backgroundMusic;

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

		SoundManager.isSoundON = true;
		SoundManager.swicthSoundONOFF(); /** Turn Off */

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

	public static void swicthSoundONOFF(){

		/** Set ON */
		if(!SoundManager.isSoundON){
			SoundManager.isSoundON = true;

			if(SoundManager.backgroundMusic != null)
				SoundManager.backgroundMusic.play();

		}
		/** Set OFF */
		else{
			SoundManager.isSoundON = false;

			if(SoundManager.backgroundMusic != null)
				SoundManager.backgroundMusic.pause();

			/** clean all played sounds */
			for (SoundPack soundPack : SoundManager.soundsPacks.values()) {
				int size = soundPack.getRunningSoundTypes().size();
				for(int i=size-1; i>0; i--){
					SoundManager.soundsType.get(soundPack.getRunningSoundTypes().get(i)).stop(soundPack.getRunningSoundID().get(i)); //Stop Sound
					soundPack.getRunningSoundTypes().remove(i);
					soundPack.getRunningSoundID().remove(i);
					soundPack.getRunningSoundsStartTime().remove(i);
				}
			}

		}
	}

	public static void playMusicBackground(){
		/** Start Sound Background */
		SoundManager.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/Background_Music_Sci-Fi.mp3"));
		SoundManager.backgroundMusic.setVolume(0.3f);
		SoundManager.backgroundMusic.setLooping(true);
		if(SoundManager.isSoundON)
			SoundManager.backgroundMusic.play();
	}

	public static void playSound(final WeaponPattern weaponPattern){

		if(SoundManager.isSoundON){
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
					Long id = sound.play(volume);
					soundPack.getRunningSoundTypes().add(soundType);
					soundPack.getRunningSoundID().add(id);
					soundPack.getRunningSoundsStartTime().add( System.currentTimeMillis() );

				}
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
				soundPack.getRunningSoundID().remove(i);
				soundPack.getRunningSoundsStartTime().remove(i);
			}
		}
	}


}
