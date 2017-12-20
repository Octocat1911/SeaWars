package com.team5.seawar.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.*;

import java.util.List;

public class Element {
    public enum Type {WATER, DIRT, LIGHTHOUSE, VOID};
    private boolean navigable;
    private Action2DSprite action2DSprite;

    private Type type;

    public Element(Type type, int colonne, int ligne){
        this.type = type;
        ActionSprite actionSprite;
        Sprite sprite;
        switch (type){
            case WATER:
                this.navigable = true;
                sprite = new Sprite(Assets.getInstance().getTexture("Maptextures/hexEau.png"));
                actionSprite = WaterAction.getInstance();
                break;
            case DIRT:
                this.navigable = false;
                sprite = new Sprite(Assets.getInstance().getTexture("Maptextures/hexTerre.png"));
                actionSprite = DirtAction.getInstance();
                break;
            case LIGHTHOUSE:
                this.navigable = false;
                sprite = new Sprite(Assets.getInstance().getTexture("Maptextures/hexPhare.png"));
                actionSprite = new ActionSprite() {
                    @Override
                    public void touchAction(Sprite sprite) {

                    }

                    @Override
                    public void clickedAction(Sprite sprite) {

                    }

                    @Override
                    public void defaultAction(Sprite sprite) {

                    }
                };
                break;
            default: //VOID
                this.navigable = false;
                sprite = new Sprite(Assets.getInstance().getTexture("Maptextures/JESAISPASENCORE"));
                actionSprite = new ActionSprite() {
                    @Override
                    public void touchAction(Sprite sprite) {

                    }

                    @Override
                    public void clickedAction(Sprite sprite) {

                    }

                    @Override
                    public void defaultAction(Sprite sprite) {

                    }
                };
                break;
        }
        this.action2DSprite = new Action2DSprite(sprite, actionSprite);
        if (colonne%2==0){
            this.action2DSprite.getSprite().setPosition(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight);
        } else {
            this.action2DSprite.getSprite().setPosition(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight + PlayScreen.hexHeight/2);
        }
        sprite.setSize(PlayScreen.hexWidth, PlayScreen.hexHeight);
    }

    public boolean isNavigable(){
        return navigable;
    }

    public Type type(){
        return type;
    }

    public Action2DSprite getAction2DSprite() {
        return action2DSprite;
    }
}
