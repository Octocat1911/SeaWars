package com.team5.seawar.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class LighthouseAction implements ActionSprite {

    private static LighthouseAction instance = new LighthouseAction();

    private LighthouseAction(){
    }

    public static LighthouseAction getInstance(){
        return instance;
    }

    @Override
    public void touchAction(Sprite sprite) {
        sprite.setTexture(Assets.getInstance().getTexture("Maptextures/hexPointeur.png"));
    }

    @Override
    public void clickedAction(Sprite sprite) {

    }

    @Override
    public void defaultAction(Sprite sprite) {
        sprite.setTexture(Assets.getInstance().getTexture("Maptextures/hexPhare.png"));
    }
}
