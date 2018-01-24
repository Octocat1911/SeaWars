package com.team5.seawar.screens.uiState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.screens.playstates.State;

public class UIState implements State{
    private PlayScreen playScreen;
    private BitmapFont font;

    public UIState(PlayScreen playScreen){
        this.playScreen = playScreen;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/vik.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 23;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1.5f;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    public void update(float dt) {

    }

    public void draw() {

    }

    public void drawUI() {
        if (playScreen.getCurrentCase().getShip() != null) {
            font.draw(playScreen.getBatch(), "pv : " + playScreen.getCurrentCase().getShip().getCurrentLifePoints() + " / " + playScreen.getCurrentCase().getShip().getMaxLifePoints(), 60, 175);
            font.draw(playScreen.getBatch(), "déplacement : " + playScreen.getCurrentCase().getShip().getMovements() + " / " + playScreen.getCurrentCase().getShip().getMaxMovements(),60, 140);
            font.draw(playScreen.getBatch(),"dégats canon principal : " + playScreen.getCurrentCase().getShip().getMainCanon().getDamage(), 60, 105);
            font.draw(playScreen.getBatch(),"dégats canon secondaire : " + playScreen.getCurrentCase().getShip().getSecondaryCanon().getDamage(), 60, 70);
        }
    }
}
