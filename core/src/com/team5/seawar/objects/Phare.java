package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.Texture;
import com.team5.seawar.utils.Assets;

public class Phare extends Element{

    public Phare(){
        navigable = true;
    }

    public Texture getTexture() {
        return Assets.getInstance().getTexture("hexPhare.png");
    }
}
