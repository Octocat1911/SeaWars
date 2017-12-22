package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.screens.PlayScreen;

public class Case {
    private Element element;
    private Vector2 position;
    private Rectangle bounds;

    public Case(Element.Type type, int colonne, int ligne){
        this.element = new Element(type, colonne, ligne);
        this.position = new Vector2(colonne, ligne);
        this.bounds = new Rectangle();
        if (colonne%2==0){
            bounds.set(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight, PlayScreen.hexWidth, PlayScreen.hexHeight);
        } else {
            bounds.set(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight + PlayScreen.hexHeight/2, PlayScreen.hexWidth, PlayScreen.hexHeight);
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void handleInput(OrthographicCamera cam, Viewport viewport){
        InputHandler.updateCase(this, cam, viewport);
        InputHandler.isTouched(getElement().getAction2DSprite(), cam, viewport);
    }
}
