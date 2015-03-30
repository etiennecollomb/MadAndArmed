package com.geekmecrazy.madandarmed.Game.UI;

import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Game.Element.GameElement;


public class UIFinishGame extends GameElement {

	private Entity mainLayer;
	
//A MIGRER
//	//TEXT
//	private ChangeableText finishText, finishTextShadow;
//	private FontType finishTextFont = FontType.TRIVIAL_HEAVY_35;
//	private FontType finishTextFontShadow = FontType.TRIVIAL_HEAVY_35_SHADOW;
//	private float finishTextX = 400;
//	private float finishTextY = 150;
//	
//	private ChangeableText scoreTotalText, scoreTotalTextShadow;
//	private FontType scoreTotalTextFont = FontType.TRIVIAL_HEAVY_25;
//	private FontType scoreTotalTextFontShadow = FontType.TRIVIAL_HEAVY_25_SHADOW;
//	private float scoreTotalTextX = 400;
//	private float scoreTotalTextY = 250;
//	
//	//BUTTON
//	public Sprite okButton;
//	private float okButtonX = 400;
//	private float okButtonY = 300;


	public UIFinishGame(){
//A MIGRER
//		mainLayer = new Entity(0, 0);
//		GameManager.getManager().getScene().attachHUDChild(mainLayer);
//
//		//OK BUTTON
//		okButton = new Sprite(okButtonX-SpriteManager.getTexture(TextureType.OK).getWidth()/2, okButtonY, SpriteManager.getTexture(TextureType.OK)) {
//			@Override
//			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
//				if (pSceneTouchEvent.isActionUp()) {
//					
//					GameManager.getManager().getScene().hideHUD(); //hide HUD
//
//					SceneManager.unloadScene(Game.class);
//					SceneManager.registerScene(new Game());
//					SceneManager.loadScene(HomeMenuScene.class);
//					SceneManager.runScene(HomeMenuScene.class);
//				}
//				return true;
//			}
//		};
//		//okButton.setColor(0.6f, 0.6f, 0.6f);
//		okButton.setScale(0.66f);
//		mainLayer.attachChild(okButton);
//		GameManager.getManager().getScene().getHUD().registerTouchArea(okButton);
//		
//		finishText = new ChangeableText(finishTextX, finishTextY, FontManager.getFont(finishTextFont) , "", HorizontalAlign.CENTER, 50);
//		finishTextShadow = new ChangeableText(finishTextX, finishTextY, FontManager.getFont(finishTextFontShadow) , "", HorizontalAlign.CENTER, 50);
//		mainLayer.attachChild(finishTextShadow);
//		mainLayer.attachChild(finishText);
//		
//		scoreTotalText = new ChangeableText(scoreTotalTextX, scoreTotalTextY, FontManager.getFont(scoreTotalTextFont) , "", HorizontalAlign.CENTER, 50);
//		scoreTotalTextShadow = new ChangeableText(scoreTotalTextX, scoreTotalTextY, FontManager.getFont(scoreTotalTextFontShadow) , "", HorizontalAlign.CENTER, 50);
//		mainLayer.attachChild(scoreTotalTextShadow);
//		mainLayer.attachChild(scoreTotalText);
		
	}

	public void initUI(){
		hideUI();
	}

	public void showUI(boolean win){	
//A MIGRER
//		if(win){
//			finishText.setText("YOUR ARMY \n IS VICTORIOUS !");
//		}else{
//			finishText.setText("YOUR ARMY \n HAS BEEN DEFEATED !");
//		}
//		finishText.setPosition(finishTextX-finishText.getWidth()/2, finishText.getY());
//		FontManager.setShadowText(finishText, finishTextShadow, FontManager.Shadow.SHADOW_4);
//		
//		scoreTotalText.setText(""+GameManager.getManager().getTeamPlayer().getScore());
//		scoreTotalText.setPosition(scoreTotalTextX-scoreTotalText.getWidth()/2, scoreTotalText.getY());
//		FontManager.setShadowText(scoreTotalText, scoreTotalTextShadow, FontManager.Shadow.SHADOW_4);
//		
//		mainLayer.setPosition(0,0);
		
	}

	public void hideUI(){
//A MIGRER
//		mainLayer.setPosition(XSceneManager.OUT_OF_SCENE, XSceneManager.OUT_OF_SCENE);
	}

	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}
}
