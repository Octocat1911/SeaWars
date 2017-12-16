package com.team5.seawar.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface ActionSprite  {

    public void touchAction(Sprite sprite);
    public void clickedAction(Sprite sprite);
    public void defaultAction(Sprite sprite);

}
