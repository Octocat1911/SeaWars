package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.Texture;

public abstract class Element {
    protected boolean navigable;

    public boolean isNavigable(){
        return navigable;
    }

    public abstract Texture getTexture();
}
