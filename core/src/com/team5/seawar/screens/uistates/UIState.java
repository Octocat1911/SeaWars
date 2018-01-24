package com.team5.seawar.screens.uistates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.screens.playstates.State;

public class UIState implements State{
    private PlayScreen playScreen;
    private BitmapFont font;
    private String joueur;

    private static UIState instance = new UIState();

    public static void init(PlayScreen playScreen){
        instance.playScreen = playScreen;
    }

    private UIState(){
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/vik.ttf"));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/PiecesOfEight.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //parameter.size = 23;
        parameter.size = 35;
        parameter.borderWidth = 1.3f;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    public static UIState getInstance(){
        return instance;
    }

    public void update(float dt) {

    }

    public void draw() {

    }

    public void drawUI() {
        if (playScreen.getCurrentCase().getShip() != null) {
            if (playScreen.getMap().getPlayer1().getShips().contains(playScreen.getCurrentCase().getShip(), true)){
                font.setColor(new Color(.204f,.506f,.859f,1));
            } else {
                font.setColor(new Color(.87f, .20f, .20f, 1));
            }
            font.draw(playScreen.getBatch(), "PV : " + playScreen.getCurrentCase().getShip().getCurrentLifePoints() + " / " + playScreen.getCurrentCase().getShip().getMaxLifePoints(), 60, 185);
            font.draw(playScreen.getBatch(), "Déplacement : " + playScreen.getCurrentCase().getShip().getMovements() + " / " + playScreen.getCurrentCase().getShip().getMaxMovements(),60, 150);
            font.draw(playScreen.getBatch(),"Dégats Canon Principal : " + playScreen.getCurrentCase().getShip().getMainCanon().getDamage() + " ( " + playScreen.getCurrentCase().getShip().getMainCanon().getCurrentCooldown() + " / " + playScreen.getCurrentCase().getShip().getMainCanon().getCooldown() + " )", 60, 115);
            font.draw(playScreen.getBatch(),"Dégats Canon Secondaire : " + playScreen.getCurrentCase().getShip().getSecondaryCanon().getDamage() + " ( " + playScreen.getCurrentCase().getShip().getSecondaryCanon().getCurrentCooldown() + " / " + playScreen.getCurrentCase().getShip().getSecondaryCanon().getCooldown() + " )", 60, 80);
        }
        if (playScreen.getPlayer() == playScreen.getMap().getPlayer1()){
            font.setColor(new Color(.204f,.506f,.859f,1));
            joueur = "Tour Joueur 1";
        } else {
            font.setColor(new Color(.87f, .20f, .20f, 1));
            joueur = "Tour Joueur 2";
        }
        font.draw(playScreen.getBatch(), joueur, 1050, 680);
    }

    public void dispose(){
        font.dispose();
    }
}
