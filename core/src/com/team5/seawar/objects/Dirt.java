package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.Texture;
import com.team5.seawar.utils.Assets;

public class Dirt extends Element{

    private static Dirt instance = null;

    protected Dirt(){
        navigable = true;
    }

    public static Dirt getInstance() {
        if(instance == null) {
            instance = new Dirt();
        }
        return instance;
    }

    public Texture getTexture() {
        return Assets.getInstance().getTexture("hexTerre.png");
    }

}
