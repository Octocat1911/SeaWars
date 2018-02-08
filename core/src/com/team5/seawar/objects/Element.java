package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.*;

/**
 * Created with love by Team 5
 */

public class Element {
    public enum Type {WATER, DIRT, LIGHTHOUSE, VOID}
    private boolean navigable;
    private Type type;
    private float posX;
    private float posY;
    private int proprietaire;

    public Element(){}

    public Element(Type type, float colonne, float ligne){
        this.type = type;
        proprietaire = 0;
        posX = colonne * PlayScreen.hexWidth *3/4;
        if (colonne%2==0){
            posY = ligne * PlayScreen.hexHeight;
        } else {
            posY = ligne * PlayScreen.hexHeight + PlayScreen.hexHeight/2;
        }
        switch (type){
            case WATER:
                this.navigable = true;
                break;
            case DIRT:
                this.navigable = false;
                break;
            case LIGHTHOUSE:
                this.navigable = true;
                break;
            default: //VOID
                this.navigable = false;
                break;
        }
    }

    public boolean isNavigable(){
        return navigable;
    }

    public Type getType(){
        return type;
    }

    public void draw(SpriteBatch sb){
        switch (type){
            case WATER:
                sb.draw(Assets.getInstance().getTexture("Maptextures/hexEau.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                break;
            case DIRT:
                sb.draw(Assets.getInstance().getTexture("Maptextures/hexTerre.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                break;
            case LIGHTHOUSE:
                switch (proprietaire){
                    case 1:
                        sb.draw(Assets.getInstance().getTexture("Maptextures/hexPhareJ1.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                        break;
                    case 2:
                        sb.draw(Assets.getInstance().getTexture("Maptextures/hexPhareJ2.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                        break;
                    default:
                        sb.draw(Assets.getInstance().getTexture("Maptextures/hexPhare.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                        break;
                }
                break;
            default: //VOID
                sb.draw(Assets.getInstance().getTexture("Maptextures/void.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                break;
        }
    }

    public void setProprietaire(int proprietaire){
        this.proprietaire = proprietaire;
    }

    public int getProprietaire(){
        return proprietaire;
    }

}
