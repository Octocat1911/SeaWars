package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.Texture;
import com.team5.seawar.utils.Assets;

public class Terre extends Element{

    private static Terre instance = null;

    protected Terre(){
        navigable = true;
    }

    public static Terre getInstance() {
        if(instance == null) {
            instance = new Terre();
        }
        return instance;
    }

    public Texture getTexture() {
        return Assets.getInstance().getTexture("hexTerre.png");
    }

}
