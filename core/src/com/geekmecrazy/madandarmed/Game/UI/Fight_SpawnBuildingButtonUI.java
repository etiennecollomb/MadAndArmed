package com.geekmecrazy.madandarmed.Game.UI;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IMoneyListener;
import com.geekmecrazy.madandarmed.Game.Element.Fight_Team;
import com.geekmecrazy.madandarmed.Game.Element.GamePlay_Team;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Scene.GamePlay_BuildingManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.ButtonPattern;
import com.geekmecrazy.madandarmed.Renderer.SpawnBuildingButtonRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Fight_SpawnBuildingButtonUI extends Layout implements IMoneyListener {

	private static int BUTTON_OK_STATE = 0;
	private static int BUTTON_NO_MONEY_STATE = 1;

	private Map<BuildingPattern, Integer> spawnBuildingButtonsState;
	private Map<BuildingPattern, SpawnBuildingButtonRenderer> spawnBuildingButtonsSprites;

	public void init(final float pX, final float pY){
		super.init(pX, pY);

		this.setOrientation(Orientation.HORIZONTAL);

		GlobalManager.fightScreen.getTeamPlayer().addMoneyListener(this);

		List<ButtonPattern> buttonsPattern = PatternLoader.getMenusPattern().get("FIGHT_SPAWN_BUILDING_MENU").getButtonsPattern();
		for (final ButtonPattern buttonPattern: buttonsPattern){

			final BuildingPattern buildingPattern = PatternLoader.getBuildingsPattern().get(buttonPattern.getBuildingName().name());

			//ADD BUTTON

			final SpawnBuildingButtonRenderer spawnBuildingButtonRenderer = new SpawnBuildingButtonRenderer();
			spawnBuildingButtonRenderer.init(TextureType.valueOf(buttonPattern.getIcon().name()), 0, 0, TextureType.BUTTON_UNIT_BACKGROUND.getWidth(), TextureType.BUTTON_UNIT_BACKGROUND.getHeight());

			spawnBuildingButtonRenderer.setScale(1f);
			spawnBuildingButtonsSprites.put(buildingPattern, spawnBuildingButtonRenderer);

			// State
			setButtonOKState(spawnBuildingButtonRenderer);
			spawnBuildingButtonsState.put(buildingPattern, Integer.valueOf(Fight_SpawnBuildingButtonUI.BUTTON_OK_STATE));


			///////////////////

			Button newSpawnBuildingButton = new Button();
			newSpawnBuildingButton.init(0, 0, TextureType.BUTTON_UNIT_BACKGROUND);
			newSpawnBuildingButton.setAction(new IAction(){
				// Pour le moment ça va lancer le fight direct ce bouton au lieu du world
				@Override
				public void execute(){
					//System.out.println("#### TOUCH SPAWN BUILDING BUTTON !!");
					if(spawnBuildingButtonsState.get(buildingPattern).intValue() == Fight_SpawnBuildingButtonUI.BUTTON_OK_STATE) {
						GlobalManager.gamePlay_BuildingManager.askForCreateSpawnBuilding(buttonPattern.getBuildingName(), GlobalManager.fightScreen.getTeamPlayer());
					}
				}
			});

			newSpawnBuildingButton.attachChild(spawnBuildingButtonRenderer, Alignment.CENTER);

			newSpawnBuildingButton.setSize(1.5f, 1.5f);
			this.add(newSpawnBuildingButton);
			GlobalManager.fightScreen.getHUD().registerTouchableShape(newSpawnBuildingButton);


		}

	}

	public Fight_SpawnBuildingButtonUI(){
		//les boutons par type d unite
		spawnBuildingButtonsSprites = new HashMap<BuildingPattern, SpawnBuildingButtonRenderer>();
		spawnBuildingButtonsState = new HashMap<BuildingPattern, Integer>();
	}


	//Change aspet bouton en fonction de la money
	@Override
	public void moneyChange(Fight_Team team) {

		for(BuildingPattern buildingPattern : spawnBuildingButtonsSprites.keySet()){
			Entity buttonLayer = spawnBuildingButtonsSprites.get(buildingPattern);

			if(team.hasEnoughtMoney(buildingPattern.getPrice())){
				setButtonOKState(buttonLayer);
				spawnBuildingButtonsState.put(buildingPattern, Integer.valueOf(Fight_SpawnBuildingButtonUI.BUTTON_OK_STATE));
			}else{
				setButtonNoMoneyState(buttonLayer);
				spawnBuildingButtonsState.put(buildingPattern,  Integer.valueOf(Fight_SpawnBuildingButtonUI.BUTTON_NO_MONEY_STATE));
			}
		}
	}

	//SET BUTTON SPRITE STATE
	private void setButtonNoMoneyState(Entity buttonLayer){

		for(int i=0; i<buttonLayer.getChildCount(); i++){
			((Shape) buttonLayer.getChild(i)).setColor(0.75f, 0.75f, 0.75f, 0.5f);

		}
	}

	private void setButtonOKState(Entity buttonLayer){
		for(int i=0; i<buttonLayer.getChildCount(); i++){
			((Shape) buttonLayer.getChild(i)).setColor(1f, 1f, 1f, 1f);
		}
	}

}
