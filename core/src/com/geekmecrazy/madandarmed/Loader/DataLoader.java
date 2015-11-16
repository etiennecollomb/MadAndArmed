package com.geekmecrazy.madandarmed.Loader;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.files.FileHandle;

public class DataLoader {

	static String unitDir = "game/Units";

	/** Should not be static ! **/
	AssetManager assetManager = new AssetManager();


	public DataLoader(){


		FileHandle dirHandle;
		if (Gdx.app.getType() == ApplicationType.Android) {
			/** Android Application **/
			dirHandle = Gdx.files.internal(unitDir);
		} else {
			/** ApplicationType.Desktop **/
			dirHandle = Gdx.files.internal("./bin/"+unitDir);
		}
		
		/** Load all Units from assets **/
		for (FileHandle entry: dirHandle.list()) {
			entry.file().getName()
		}

		/** Load Units **/

		assetManager.load("game/Units/Gladiator/red", TextureAtlasLoader.class);

	}


}
