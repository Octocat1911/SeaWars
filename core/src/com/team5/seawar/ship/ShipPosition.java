package com.team5.seawar.ship;

import com.badlogic.gdx.math.Vector2;

public class ShipPosition {
    private int colonne;
    private int ligne;
    public enum Orientation {TOP, BOTTOM, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT};
    private Orientation orientation;

    public ShipPosition(){}

    public ShipPosition(int x, int y, Orientation orientation){
        this.colonne = x;
        this.ligne = y;
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getColonne(){
        return colonne;
    }

    public int getLigne(){
        return ligne;
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
    }

    public void setPosiiton(Vector2 arrive){
        colonne = (int)arrive.x;
        ligne = (int)arrive.y;
    }
}

