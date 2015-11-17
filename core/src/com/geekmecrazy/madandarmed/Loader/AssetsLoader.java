package com.geekmecrazy.madandarmed.Loader;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.geekmecrazy.madandarmed.Entity.IUpdatable;

public class AssetsLoader {

	static String unitDir = "game/Units";

	/** Should not be static ! **/
	AssetManager assetManager;


	public AssetsLoader(){
		
		assetManager = new AssetManager();
		
		
		/** Set Units Dir **/
		FileHandle dirHandle;
		if (Gdx.app.getType() == ApplicationType.Android)
			dirHandle = Gdx.files.internal(unitDir); /** Android Application **/
		else
			dirHandle = Gdx.files.internal("./bin/"+unitDir); /** ApplicationType.Desktop **/

		
		/** List all Units **/
		for (FileHandle unitType: dirHandle.list()) {

			/** list all Colors **/
			if(unitType.isDirectory()){
				for (FileHandle colorType: unitType.list()) {
					
					/** list All assets **/
					for (FileHandle asset: colorType.list()) {
						
						String fileName = asset.file().getName();
						String extension = fileName.substring(fileName.lastIndexOf(".")+1);

						if(extension.equals("txt")){
							
							System.out.println("asset: " + asset.path());
							/** Load assests **/
							assetManager.load(asset.path(), TextureAtlas.class);
						}
						
					}
				}
				
			}

		}

	}
	

	public AssetManager getAssetManager() {
		return assetManager;
	}


}
