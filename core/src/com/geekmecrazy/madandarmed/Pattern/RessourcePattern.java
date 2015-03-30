package com.geekmecrazy.madandarmed.Pattern;

import com.geekmecrazy.madandarmed.CoreConfig.TextureType;


public class RessourcePattern {
	
	private RessourceID ressourceID;					// ressourceID (ex : OIL, URANIUM ...)
	
	private String name;								// Ressource Name
	private String description;							// Ressource Description
	private TextureType spriteIcone;					// Icone
	
	public enum RessourceID {
		OIL, 
		URANIUM,
		TRINIUM,
		CARBONITE
	}

	public RessourceID getRessourceID() {
		return ressourceID;
	}

	public void setRessourceID(RessourceID ressourceID) {
		this.ressourceID = ressourceID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TextureType getSpriteIcone() {
		return spriteIcone;
	}

	public void setSpriteIcone(TextureType spriteIcone) {
		this.spriteIcone = spriteIcone;
	} 
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("#####################################\nRessourcePattern \n---- ressourceID=");
		builder.append(ressourceID);
		builder.append(" \n---- name=");
		builder.append(name);
		builder.append(" \n---- description=");
		builder.append(description);
		builder.append(" ");
		return builder.toString();
	}
}
