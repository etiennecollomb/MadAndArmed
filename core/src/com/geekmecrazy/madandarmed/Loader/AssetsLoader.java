package com.geekmecrazy.madandarmed.Loader;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.geekmecrazy.madandarmed.Core.GlobalManager;

public class AssetsLoader {

	static String UNIT_DIR = "game/Units";
	static String TURRET_DIR = "game/Turrets";

	/** Should not be static ! **/
	AssetManager assetManager;


	public AssetsLoader(){

		assetManager = new AssetManager();

		this.loadAssetsFromDir(UNIT_DIR);
		this.loadAssetsFromDir(TURRET_DIR);


	}

	
	/** structure Dir : dirName/{color subdir}/assests.{txt || png} **/
	private void loadAssetsFromDir(String dirName){
		
		//TODO : optim pour gagner 5 s de load : mettre en dur les path des assest car lister sur mobile via file handle est tres long !
		
		/** Set Units Dir **/
		FileHandle dirHandle;
		dirHandle = Gdx.files.internal(GlobalManager.convertToDevicePath(dirName));

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


	/** Get all assests loaded inside a specific Dir **/
	public List<TextureAtlas> getTextureAtlasListFromDirName(String dirName){

		dirName = GlobalManager.convertToDevicePath(dirName);
		
		List<TextureAtlas> textureAtlasList = new ArrayList<TextureAtlas>();

		for(String assetName : assetManager.getAssetNames()){			
			if(assetName.startsWith(dirName)){
				
				String fileName = assetName;
				String extension = fileName.substring(fileName.lastIndexOf(".")+1);

				if(extension.equals("txt")){
					System.out.println("List of Assests: " + assetName);
					
					TextureAtlas textureAtlas = assetManager.get(assetName, TextureAtlas.class);
					textureAtlasList.add(textureAtlas);
				}
				
			}
		}

		return textureAtlasList;

	}



}





