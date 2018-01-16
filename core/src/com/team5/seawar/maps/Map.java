package com.team5.seawar.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.objects.Case;
import com.team5.seawar.player.Player;

public abstract class Map {
    protected Player player1;
    protected Player player2;

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

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }
}