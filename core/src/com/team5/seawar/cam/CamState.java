package com.team5.seawar.cam;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.team5.seawar.screens.PlayScreen;

public abstract class CamState {

    protected PlayScreen playScreen;

    protected float zoom;
    protected OrthographicCamera cam;
    protected int nbLigne;
    protected int nbColonne;
    protected float hexHeight;
    protected float hexWidth;
    protected Vector2 position;

    public void init(PlayScreen playScreen){
        this.playScreen = playScreen;
        cam = playScreen.getCam();
        nbColonne = playScreen.getMap().getColonne();
        nbLigne = playScreen.getMap().getLigne();
        hexHeight = playScreen.hexHeight;
        hexWidth = playScreen.hexWidth;
        position = playScreen.getPosition();
    }

    public abstract void update(float dt);

}
