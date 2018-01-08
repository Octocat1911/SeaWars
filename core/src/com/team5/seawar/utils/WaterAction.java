package com.team5.seawar.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class WaterAction implements ActionSprite {

    private static WaterAction instance = new WaterAction();

    private WaterAction(){
    }

    public static WaterAction getInstance(){
        return instance;
    }

    @Override
    public void touchAction(Sprite sprite) {
        //sprite.setTexture(Assets.getInstance().getTexture("Maptextures/hexPointeur.png"));
    }

    @Override
    public void clickedAction(Sprite sprite) {

    }

    @Override
    public void defaultAction(Sprite sprite) {
        sprite.setTexture(Assets.getInstance().getTexture("Maptextures/hexEau.png"));
    }
}
