package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.ship.Ship;

public class Case {
    private Vector2 position;
    private Circle bounds;
    private Element element;
    private Ship ship;

    public Case(Element.Type type, int colonne, int ligne){
        element = new Element(type, colonne, ligne);
        ship = null;
        this.position = new Vector2(colonne, ligne);
        this.bounds = new Circle();
        if (colonne%2==0){
            bounds.set(colonne * PlayScreen.hexWidth *3/4 + PlayScreen.hexWidth/2, ligne * PlayScreen.hexHeight + PlayScreen.hexHeight/2, PlayScreen.hexWidth-55);
        } else {
            bounds.set(colonne * PlayScreen.hexWidth *3/4 + PlayScreen.hexWidth/2, ligne * PlayScreen.hexHeight + PlayScreen.hexHeight, PlayScreen.hexWidth-55);
        }

    }

    public Vector2 getPosition(){
        return position;
    }

    public boolean isNavigable(){
        if (ship != null) {
            return false;
        } else {
            return element.isNavigable();
        }
    }

    public Circle getBounds() {
        return bounds;
    }

    public void handleInput(OrthographicCamera cam, Viewport viewport){
        Inputs.updateCase(this, cam, viewport);
    }

    public void draw(SpriteBatch sb){
        element.getSprite().draw(sb);
        if (ship != null){
            ship.getSprite().draw(sb);
        }
    }

    public void addShip(Ship ship){
        if (this.ship == null){
            this.ship = ship;
        }
    }

    public void deleteShip(){
        ship = null;
    }

    public Ship getShip() {
        return ship;
    }
}
