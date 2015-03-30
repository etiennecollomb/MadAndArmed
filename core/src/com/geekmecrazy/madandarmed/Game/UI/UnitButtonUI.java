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
import com.geekmecrazy.madandarmed.Pattern.CreepPattern;
import com.geekmecrazy.madandarmed.Renderer.UnitButtonRenderer;
import com.geekmecrazy.madandarmed.XML.DataManager;

import java.util.HashMap;
import java.util.Map;



public class UnitButtonUI extends Layout implements IMoneyListener {

    private static float SPACE_BETWEEN_BUTTON = 0.12f;
    private static float UI_POSX = 0.8f;
    private static float UI_POSY = 1f;

    private static int BUTTON_OK_STATE = 0;
    private static int BUTTON_NO_MONEY_STATE = 1;

    private Map<CreepPattern, Integer> unitsButtonsState;
    private Map<CreepPattern, UnitButtonRenderer> unitsButtonsSprites;

    public void init(final float pX, final float pY){
        super.init(pX, pY);

        this.setOrientation(Orientation.HORIZONTAL);

        FightScreen.getManager().getTeamPlayer().addMoneyListener(this);

        java.util.Map<CreepPattern.CreepID, CreepPattern> creepsPattern = DataManager.getCreepsPattern();
        for (final CreepPattern.CreepID creepID : creepsPattern.keySet()){

            final CreepPattern creepPattern = creepsPattern.get(creepID);

            //ADD SPRITE
            //on ne veut afficher que les boutons des unit dispo de la team du joueur
            if(creepPattern.getTeam() == CreepPattern.UnitTeam.TEAM1){

//                final UnitButtonRenderer unitButtonRenderer = new UnitButtonRenderer() {
//                    @Override
//                    public void onTouch(final MyGestureDetector.GestureType pGestureType, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
//                        //System.out.println("#### TOUCH UNIT BUTTON !!");
//                        if(unitsButtonsState.get(creepPattern).intValue() == UnitButtonUI.BUTTON_OK_STATE) {
//                            CreepManager.getManager().askForCreateCreep(creepID, FightScreen.getManager().getTeamPlayer());
//                        }
//                    };
//                };

                final UnitButtonRenderer unitButtonRenderer = new UnitButtonRenderer();
                unitButtonRenderer.init(creepPattern.getSpriteButton(), 0, 0, TextureType.BUTTON_UNIT_BACKGROUND.getWidth(), TextureType.BUTTON_UNIT_BACKGROUND.getHeight());

                unitButtonRenderer.setScale(1f);
                unitsButtonsSprites.put(creepPattern, unitButtonRenderer);

//                this.add(unitButtonRenderer);
//                FightScreen.getManager().getHUD().registerTouchableShape(unitButtonRenderer);

                /** State */
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
                            CreepManager.getManager().askForCreateCreep(creepID, FightScreen.getManager().getTeamPlayer());
                        }
                    }
                });

                newUnitButton.attachChild(unitButtonRenderer, Alignment.CENTER);

                newUnitButton.setSize(1.5f, 1.5f);
                this.add(newUnitButton);
                FightScreen.getManager().getHUD().registerTouchableShape(newUnitButton);


            }
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
//            buttonLayer.getChild(i).setScale(UnitButtonUI.BUTTON_SCALE);
//            buttonLayer.getChild(i).setColor(0.75f, 0.75f, 0.75f); //sombre (different de gris)
            ((Shape) buttonLayer.getChild(i)).setColor(0.75f, 0.75f, 0.75f, 0.5f);

        }
    }

    private void setButtonOKState(Entity buttonLayer){
        for(int i=0; i<buttonLayer.getChildCount(); i++){
//            buttonLayer.getChild(i).setScale(UnitButtonUI.BUTTON_SCALE);
            ((Shape) buttonLayer.getChild(i)).setColor(1f, 1f, 1f, 1f);
        }
    }

    private void setButtonDownState(Entity buttonLayer){
        for(int i=0; i<buttonLayer.getChildCount(); i++){
//            buttonLayer.getChild(i).setScale(UICreateCreep.BUTTON_SCALE * UnitButtonUI.BUTTON_ZOOM_ON_PUSH);
//            buttonLayer.getChild(i).setColor(1.8f, 1.8f, 1.8f);
        }
    }

}
