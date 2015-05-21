package com.geekmecrazy.madandarmed.Pattern;

import com.badlogic.gdx.utils.ObjectMap;
import com.geekmecrazy.madandarmed.CoreConfig.AnimatedTextureType;


public class TexturePattern{
	
	/** Building, Creep names... and AnimatedTextureType associated */
	private ObjectMap<String, AnimatedTextureType> textures;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ObjectMap<String, AnimatedTextureType> getTextures() {
		return textures;
	}

	public void setTextures(ObjectMap<String, AnimatedTextureType> textures) {
		this.textures = textures;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

}


