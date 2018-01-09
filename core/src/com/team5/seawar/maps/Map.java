package com.team5.seawar.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.objects.Case;
import com.team5.seawar.ship.Ship;

public abstract class Map {
    protected Array<Ship> shipJ1;
    protected Array<Ship> shipJ2;

    protected int colonne;
    protected int ligne;
    protected Case tab[][];


    public Map(int colonne, int ligne) {
        this.ligne = ligne;
        this.colonne = colonne;
        tab = new Case[colonne][ligne];
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public Case getCase(int j, int i){
        return tab[j][i];
    }

    public void draw(SpriteBatch sb){
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++) {
                getCase(i, j).draw(sb);
            }
        }
    }

    public void handleInput(OrthographicCamera cam, Viewport viewport){
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++) {
                getCase(i,j).handleInput(cam, viewport);
            }
        }
    }

    public Array<Ship> getShipJ1(){
        return shipJ1;
    }

    public Array<Ship> getShipJ2(){
        return shipJ2;
    }
}