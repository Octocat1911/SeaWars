package com.team5.seawar.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.objects.Case;
import com.team5.seawar.objects.Element;
import com.team5.seawar.player.Player;
import com.team5.seawar.ship.Ship;

public class Map {
    protected Player player1;
    protected Player player2;

    protected int colonne;
    protected int ligne;
    protected Case tab[][];
    protected int nbLighthouses;

    public Map(int colonne, int ligne) {
        if(!(colonne<7) && !(ligne<6) && colonne<= 41 && ligne<= 40){
            this.ligne = ligne;
            this.colonne = colonne;
        }
        else{
            System.err.println("Map size Error !");
            Gdx.app.exit();
        }

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

    public void setColonne(int colonne) {
        if(!(colonne<7) && colonne <=41){
            this.colonne = colonne;
        }
    }

    public void setLigne(int ligne) {
        if(!(ligne<6) && ligne <=40){
            this.ligne = ligne;
        }
    }

    public void draw(SpriteBatch sb){
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++) {
                getCase(i, j).drawElements(sb);
            }
        }
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++) {
                getCase(i, j).drawShips(sb);
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

    public void update(float dt){
        for (Ship ship: player1.getShips()){
            ship.update(dt);
        }
        for (Ship ship: player2.getShips()){
            ship.update(dt);
        }
    }

    public void init(){
        tab = new Case[colonne][ligne];
        for(int i = 0;i< colonne;i++){
            for(int j = 0; j < ligne;j++){
                tab[i][j] = new Case(Element.Type.WATER,i,j);
            }
        }
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public int getNbLighthouses(){
        return nbLighthouses;
    }

    protected void majNbLighthouses(){
        int nb = 0;
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++) {
                if (getCase(i,j).getElement().getType() == Element.Type.LIGHTHOUSE)
                    nb++;
            }
        }
        if (nb > 0) {
            nbLighthouses = nb;
        } else {
            nbLighthouses = -1;
        }
    }
}