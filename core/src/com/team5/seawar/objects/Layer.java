package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created with love by Team 5
 */

public abstract class Layer {

    public abstract Sprite getSprite();
    public abstract boolean isNavigable();

}
