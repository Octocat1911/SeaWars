package com.team5.seawar.cam;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.team5.seawar.screens.PlayScreen;

/**
 * Created with love by Team 5
 */

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
        this.cam = playScreen.getCam();
        this.nbColonne = playScreen.getMap().getColonne();
        this.nbLigne = playScreen.getMap().getLigne();
        this.hexHeight = PlayScreen.hexHeight;
        this.hexWidth = PlayScreen.hexWidth;
        this.position = playScreen.getPosition();
    }

    public abstract void update(float dt);

}
