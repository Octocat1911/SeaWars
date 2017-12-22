package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.*;

public class Element {
    public enum Type {WATER, DIRT, LIGHTHOUSE, VOID}
    private boolean navigable;
    private Sprite sprite;

    private Type type;

    public Element(Type type, int colonne, int ligne){
        switch (type){
            case WATER:
                this.navigable = true;
                sprite = new Sprite(Assets.getInstance().getTexture("Maptextures/hexEau.png"));
                break;
            case DIRT:
                this.navigable = false;
                sprite = new Sprite(Assets.getInstance().getTexture("Maptextures/hexTerre.png"));
                break;
            case LIGHTHOUSE:
                this.navigable = false;
                sprite = new Sprite(Assets.getInstance().getTexture("Maptextures/hexPhare.png"));
                break;
            default: //VOID
                this.navigable = false;
                sprite = new Sprite(Assets.getInstance().getTexture("Maptextures/JESAISPASENCORE"));
                break;
        }
        if (colonne%2==0){
            sprite.setPosition(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight);
        } else {
            sprite.setPosition(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight + PlayScreen.hexHeight/2);
        }
        sprite.setSize(PlayScreen.hexWidth, PlayScreen.hexHeight);
    }

    public boolean isNavigable(){
        return navigable;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public Type type(){
        return type;
    }

}
