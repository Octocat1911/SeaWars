package com.team5.seawar.objects;

import com.badlogic.gdx.math.Vector2;

public class Case {
    private Element element;
    private Vector2 position;

    public Case(Element.Type type, int i, int j){
        this.element = new Element(type, i, j);
        this.position = new Vector2(i, j);
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
}
