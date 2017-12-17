package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.Texture;
import com.team5.seawar.utils.Assets;

public class Water extends Element{

    private static Water instance = null;

    protected Water(){
        navigable = true;
    }

    public static Water getInstance() {
        if(instance == null) {
            instance = new Water();
        }
        return instance;
    }

    public Texture getTexture() {
        return Assets.getInstance().getTexture("hexEau.png");
    }
}
