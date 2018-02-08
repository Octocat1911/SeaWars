package com.team5.seawar.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created with love by Team 5
 */

public class Action2DSprite{

    private Sprite sprite;
    private ActionSprite actionSprite;

    public Action2DSprite(Sprite sprite,ActionSprite actionSprite){
        this.sprite = sprite;
        this.actionSprite = actionSprite;
    }

    public void touchAction(){
        actionSprite.touchAction(this.sprite);
    }

    public void clickedAction(){
        actionSprite.clickedAction(this.sprite);
    }

    public void defaultAction(){
        actionSprite.defaultAction(this.sprite);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
