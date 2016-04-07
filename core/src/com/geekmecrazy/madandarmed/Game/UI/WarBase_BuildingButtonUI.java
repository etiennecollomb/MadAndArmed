package com.geekmecrazy.madandarmed.Game.UI;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Loader.PatternLoader;
import com.geekmecrazy.madandarmed.Pattern.BuildingPattern;
import com.geekmecrazy.madandarmed.Pattern.ButtonPattern;
import com.geekmecrazy.madandarmed.Renderer.WarBaseBuildingButtonRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class WarBase_BuildingButtonUI extends Layout {


	private Map<BuildingPattern, WarBaseBuildingButtonRenderer> warBaseBuildingButtonsSprites;


	public WarBase_BuildingButtonUI(){
		//les boutons par type d unite
		warBaseBuildingButtonsSprites = new HashMap<BuildingPattern, WarBaseBuildingButtonRenderer>();
	}
	
	public void init(final float pX, final float pY){
		super.init(pX, pY);

		this.setOrientation(Orientation.HORIZONTAL);


		List<ButtonPattern> buttonsPattern = PatternLoader.getMenusPattern().get("WARBASE_BUILDING_MENU").getButtonsPattern();
		for (final ButtonPattern buttonPattern: buttonsPattern){

			final BuildingPattern buildingPattern = PatternLoader.getBuildingsPattern().get(buttonPattern.getBuildingName().name());

			/** Add Button **/

			final WarBaseBuildingButtonRenderer warBaseBuildingButtonRenderer = new WarBaseBuildingButtonRenderer();
			warBaseBuildingButtonRenderer.init(TextureType.valueOf(buttonPattern.getIcon().name()), 0, 0, TextureType.BUTTON_UNIT_BACKGROUND.getWidth(), TextureType.BUTTON_UNIT_BACKGROUND.getHeight());

			warBaseBuildingButtonRenderer.setScale(1f);
			warBaseBuildingButtonsSprites.put(buildingPattern, warBaseBuildingButtonRenderer);

			///////////////////

			Button newWarBaseBuildingButton = new Button();
			newWarBaseBuildingButton.init(0, 0, TextureType.BUTTON_UNIT_BACKGROUND);
			newWarBaseBuildingButton.setAction(new IAction(){
				// Pour le moment ça va lancer le fight direct ce bouton au lieu du world
				@Override
				public void execute(){
					//System.out.println("#### TOUCH WAR BASE BUILDING BUTTON !!");
					GlobalManager.gamePlay_BuildingManager.askForCreateSpawnBuilding(buttonPattern.getBuildingName(), ScreenManager.warBaseScreen.getTeamPlayer());

				}
			});

			newWarBaseBuildingButton.attachChild(warBaseBuildingButtonRenderer, Alignment.CENTER);

			newWarBaseBuildingButton.setSize(1.5f, 1.5f);
			this.add(newWarBaseBuildingButton);
			ScreenManager.warBaseScreen.getHUD().registerTouchableShape(newWarBaseBuildingButton);

		}

	}


}
