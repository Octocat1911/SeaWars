package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.Texture;
import com.team5.seawar.utils.Assets;

public class Eau extends Element{

    private static Eau instance = null;

    protected Eau(){
        navigable = true;
    }

    public static Eau getInstance() {
        if(instance == null) {
            instance = new Eau();
        }
        return instance;
    }

    public Texture getTexture() {
        return Assets.getInstance().getTexture("hexEau.png");
    }
}
