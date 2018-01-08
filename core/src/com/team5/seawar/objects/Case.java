package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.screens.PlayScreen;

public class Case {
    private Element element;
    private Vector2 position;
    private Circle bounds;

    public Case(Element.Type type, int colonne, int ligne){
        this.element = new Element(type, colonne, ligne);
        this.position = new Vector2(colonne, ligne);
        this.bounds = new Circle();
        if (colonne%2==0){
            bounds.set(colonne * PlayScreen.hexWidth *3/4 + PlayScreen.hexWidth/2, ligne * PlayScreen.hexHeight + PlayScreen.hexHeight/2, PlayScreen.hexWidth-55);
        } else {
            bounds.set(colonne * PlayScreen.hexWidth *3/4 + PlayScreen.hexWidth/2, ligne * PlayScreen.hexHeight + PlayScreen.hexHeight, PlayScreen.hexWidth-55);
        }
    }

    public Element getElement() {
        return element;
    }

    public Vector2 getPosition(){
        return position;
    }

    public boolean isNavigable(){
        return element.isNavigable();
    }

    public Circle getBounds() {
        return bounds;
    }

    public void handleInput(OrthographicCamera cam, Viewport viewport){
        Inputs.updateCase(this, cam, viewport);
    }
}
