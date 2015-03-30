package com.geekmecrazy.madandarmed.Pattern;

import java.util.List;

import com.geekmecrazy.madandarmed.Utils.Vector2d;


public class MapPattern {
	// ===========================================================
	// Attributes
	// ===========================================================
	private List<LevelBuildingPattern> listBuildingPlayer;
	private List<LevelBuildingPattern> listBuildingIA;
	

	private Vector2d spawnPointPlayer;
	private Vector2d spawnPointIa;
	
	// ===========================================================
	// Attributes accessor
	// ===========================================================
	
	public List<LevelBuildingPattern> getListBuildingPlayer() {
		return listBuildingPlayer;
	}
	
	public void setListBuildingPlayer(List<LevelBuildingPattern> listBuildingPlayer) {
		this.listBuildingPlayer = listBuildingPlayer;
	}
	
	public List<LevelBuildingPattern> getListBuildingIA() {
		return listBuildingIA;
	}
	
	public void setListBuildingIA(List<LevelBuildingPattern> listBuildingIA) {
		this.listBuildingIA = listBuildingIA;
	}

	public Vector2d getSpawnPointPlayer() {
		return spawnPointPlayer;
	}

	public void setSpawnPointPlayer(Vector2d spawnPointPlayer) {
		this.spawnPointPlayer = spawnPointPlayer;
	}

	public Vector2d getSpawnPointIa() {
		return spawnPointIa;
	}

	public void setSpawnPointIa(Vector2d spawnPointIa) {
		this.spawnPointIa = spawnPointIa;
	}

}
