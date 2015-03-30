package com.geekmecrazy.madandarmed.Game.UI;

import com.geekmecrazy.madandarmed.Assets.Assets;
import com.geekmecrazy.madandarmed.Core.GlobalManager;
import com.geekmecrazy.madandarmed.CoreConfig.TextureType;
import com.geekmecrazy.madandarmed.Entity.Entity;
import com.geekmecrazy.madandarmed.Entity.IMoneyListener;
import com.geekmecrazy.madandarmed.Entity.IScoreListener;
import com.geekmecrazy.madandarmed.Entity.Rectangle;
import com.geekmecrazy.madandarmed.Entity.Shape;
import com.geekmecrazy.madandarmed.Game.Element.Team;
import com.geekmecrazy.madandarmed.Game.Scene.FightScreen;
import com.geekmecrazy.madandarmed.Renderer.FontRenderer;
import com.geekmecrazy.madandarmed.Renderer.ScoreBarRenderer;
import com.geekmecrazy.madandarmed.Screen.ScreenManager;
import com.geekmecrazy.madandarmed.Utils.VirtualViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import javax.xml.bind.annotation.XmlElementDecl;


public class ScoreBarUI extends Layout implements IMoneyListener, IScoreListener {

    FontRenderer fontRenderer_0; //Score
    FontRenderer fontRenderer_1; //Money
    FontRenderer fontRenderer_2; //Money by Turn

    ScoreBarRenderer scoreBarRenderer_0;
    ScoreBarRenderer scoreBarRenderer_1;

    // ===========================================================
    // Constructors
    // ===========================================================

    public ScoreBarUI(){
        scoreBarRenderer_0 = new ScoreBarRenderer();
        scoreBarRenderer_1 = new ScoreBarRenderer();

        fontRenderer_0 = new FontRenderer( new BitmapFont(Gdx.files.internal("font/trivial_14.fnt"), false) );
        fontRenderer_1 = new FontRenderer( new BitmapFont(Gdx.files.internal("font/trivial_14.fnt"), false) );
        fontRenderer_2 = new FontRenderer( new BitmapFont(Gdx.files.internal("font/trivial_14.fnt"), false) );
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    //Change aspet bouton en fonction de la money
    @Override
    public void moneyChange(Team team) {
        fontRenderer_0.setText(this.getMoneyText());
        float size_ = ((float)team.getMoney() / (float)team.getMoneyMax()) * (float)(this.scoreBarRenderer_0.getScore_bar_maxX() - this.scoreBarRenderer_0.getScore_bar_minX());
        this.scoreBarRenderer_0.setBarSize((int) size_);

        fontRenderer_2.setText(this.getMoneyTurnText());
    }

    @Override
    public void scoreChange(Team team) {
        fontRenderer_1.setText(this.getScoreText());
        float size_ = ((float)this.getScore() / (float)team.getThoriumMax()) * (float)(this.scoreBarRenderer_1.getScore_bar_maxX() - this.scoreBarRenderer_1.getScore_bar_minX());
        this.scoreBarRenderer_1.setBarSize((int)size_);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void init(final float pX, final float pY){
        super.init(pX, pY);

        this.setOrientation(Orientation.VERTICAL);

        FightScreen.getManager().getTeamPlayer().addMoneyListener(this);
        FightScreen.getManager().getTeamPlayer().addScoreListener(this);

        float score_bar_min = 49;
        float score_bar_max = 207;
        float score_bar_min_size = 2;

        float posX_0 = 0f;
        float posY_0 = 0f; //VirtualViewport.convertWorldHeightToUnit(-60f);
        this.scoreBarRenderer_0.init(TextureType.SCORE_BAR_BLUE, posX_0, posY_0, score_bar_min, score_bar_max, score_bar_min_size);
        this.add(scoreBarRenderer_0);
//        this.scoreBarRenderer_0.setColor(1f, 1f, 1f, 0.5f);

        float posX_2 = VirtualViewport.convertWorldWidthToUnit(-49f);
        float posY_2 = 0f;
        fontRenderer_0.init("", posX_2, posY_2);
        this.add(fontRenderer_0);
        fontRenderer_0.setAlignment(Alignment.RIGHT);

        float posX_1 = 0f;
        float posY_1 = 0f;
        this.scoreBarRenderer_1.init(TextureType.SCORE_BAR_YELLOW, posX_1, posY_1, score_bar_min, score_bar_max, score_bar_min_size);
        this.add(scoreBarRenderer_1);
//        this.scoreBarRenderer_1.setColor(1f, 1f, 1f, 0.5f);

        float posX_3 = VirtualViewport.convertWorldWidthToUnit(-49f);
        float posY_3 = 0f;
        fontRenderer_1.init("", posX_3, posY_3);
        this.add(fontRenderer_1);
        fontRenderer_1.setAlignment(Alignment.RIGHT);

        float posX_4 = VirtualViewport.convertWorldWidthToUnit(-49f);
        float posY_4 = 0f;
        fontRenderer_2.init("", posX_4, posY_4);
        this.add(fontRenderer_2);
        fontRenderer_2.setAlignment(Alignment.RIGHT);

    }

    private int getScore(){

        int score = FightScreen.getManager().getTeamPlayer().getScore();

        if(score > FightScreen.getManager().getTeamPlayer().getThoriumMax())
            score = FightScreen.getManager().getTeamPlayer().getThoriumMax();

        return score;
    }

    private String getMoneyText(){
        return FightScreen.getManager().getTeamPlayer().getMoney() + " / " + FightScreen.getManager().getTeamPlayer().getMoneyMax();
    }

    private String getScoreText(){
        return getScore() + " / " + FightScreen.getManager().getTeamPlayer().getThoriumMax();
    }

    private String getMoneyTurnText(){
        return "" + FightScreen.getManager().getTeamPlayer().getMoneyByTurn();
    }


}
