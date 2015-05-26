package com.geekmecrazy.madandarmed.Game.UI;

import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IMoneyListener;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.IAction;
import com.geekmecrazy.madandarmed.Game.Scene.CreepManager;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Input.MyGestureDetector;
import com.geekmecrazy.madandarmed.Json.DataLoader;
import com.geekmecrazy.madandarmed.Pattern.ButtonPattern;
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Renderer.UnitButtonRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class UnitButtonUI extends Layout implements IMoneyListener {

	private static int BUTTON_OK_STATE = 0;
	private static int BUTTON_NO_MONEY_STATE = 1;

	private Map<CreepPattern, Integer> unitsButtonsState;
	private Map<CreepPattern, UnitButtonRenderer> unitsButtonsSprites;

	public void init(final float pX, final float pY){
		super.init(pX, pY);

		this.setOrientation(Orientation.HORIZONTAL);

		FightScreen.getManager().getTeamPlayer().addMoneyListener(this);

		List<ButtonPattern> buttonsPattern = DataLoader.getMenusPattern().get("MENU_1").getButtonsPattern();
		for (final ButtonPattern buttonPattern: buttonsPattern){

			final CreepPattern creepPattern = DataLoader.getCreepsPattern().get(buttonPattern.getCreepType().name());

			//ADD BUTTON

			final UnitButtonRenderer unitButtonRenderer = new UnitButtonRenderer();
			unitButtonRenderer.init(TextureType.valueOf(buttonPattern.getIcon().name()), 0, 0, TextureType.BUTTON_UNIT_BACKGROUND.getWidth(), TextureType.BUTTON_UNIT_BACKGROUND.getHeight());

			unitButtonRenderer.setScale(1f);
			unitsButtonsSprites.put(creepPattern, unitButtonRenderer);

			// State
			setButtonOKState(unitButtonRenderer);
			unitsButtonsState.put(creepPattern, Integer.valueOf(UnitButtonUI.BUTTON_OK_STATE));


			///////////////////

			Button newUnitButton = new Button();
			newUnitButton.init(0, 0, TextureType.BUTTON_UNIT_BACKGROUND);
			newUnitButton.setAction(new IAction(){
				// Pour le moment ça va lancer le fight direct ce bouton au lieu du world
				@Override
				public void execute(){
					//System.out.println("#### TOUCH UNIT BUTTON !!");
					if(unitsButtonsState.get(creepPattern).intValue() == UnitButtonUI.BUTTON_OK_STATE) {
						CreepManager.getManager().askForCreateCreep(buttonPattern.getCreepType(), FightScreen.getManager().getTeamPlayer());
					}
				}
			});

			newUnitButton.attachChild(unitButtonRenderer, Alignment.CENTER);

			newUnitButton.setSize(1.5f, 1.5f);
			this.add(newUnitButton);
			FightScreen.getManager().getHUD().registerTouchableShape(newUnitButton);


		}

	}

	public UnitButtonUI(){
		//les boutons par type d unite
		unitsButtonsSprites = new HashMap<CreepPattern, UnitButtonRenderer>();
		unitsButtonsState = new HashMap<CreepPattern, Integer>();
	}


	//Change aspet bouton en fonction de la money
	@Override
	public void moneyChange(Team team) {

		for(CreepPattern creepPattern : unitsButtonsSprites.keySet()){
			Entity buttonLayer = unitsButtonsSprites.get(creepPattern);

			if(team.hasEnoughtMoney(creepPattern.getPrice())){
				setButtonOKState(buttonLayer);
				unitsButtonsState.put(creepPattern, Integer.valueOf(UnitButtonUI.BUTTON_OK_STATE));
			}else{
				setButtonNoMoneyState(buttonLayer);
				unitsButtonsState.put(creepPattern,  Integer.valueOf(UnitButtonUI.BUTTON_NO_MONEY_STATE));
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
