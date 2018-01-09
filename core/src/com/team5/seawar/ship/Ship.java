package com.team5.seawar.ship;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.team5.seawar.objects.Element;
import com.team5.seawar.objects.Layer;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Assets;

public class Ship{
    private int lifepoints;
    private Canon mainCanon;
    private Canon secondaryCanon;
    private int currentMovements;
    private int maxMovements;
    private ShipPosition shipPosition;
    private Sprite sprite;

    public Ship(int lifepoints, Canon mainCanon, Canon secondaryCanon, int currentMovements, int maxMovements, int colonne, int ligne, ShipPosition.Orientation orientation){
        this.lifepoints = lifepoints;
        this.mainCanon = mainCanon;
        this.secondaryCanon = secondaryCanon;
        this.currentMovements = currentMovements;
        this.maxMovements = maxMovements;
        this.shipPosition = new ShipPosition(colonne,ligne,orientation);
        this.sprite = new Sprite(Assets.getInstance().getTexture("Shiptextures/ShipH.png"));
        if (colonne%2==0){
            sprite.setPosition(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight);
        } else {
            sprite.setPosition(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight + PlayScreen.hexHeight/2);
        }
        sprite.setSize(PlayScreen.hexWidth, PlayScreen.hexHeight);
    }

    public Sprite getSprite() {
        switch (shipPosition.getOrientation()){
            case TOP:
                sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/ShipH.png"));
                break;
            case TOP_RIGHT:
                sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/ShipHD.png"));
                break;
            case BOTTOM_RIGHT:
                sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/ShipBD.png"));
                break;
            case BOTTOM:
                sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/ShipB.png"));
                break;
            case BOTTOM_LEFT:
                sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/ShipBG.png"));
                break;
            case TOP_LEFT:
                sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/ShipHG.png"));
                break;
        }
        return sprite;
    }

    public void move(Vector2 arrive){
        float deltaX = arrive.x - shipPosition.getColonne();
        float deltaY = arrive.y - shipPosition.getLigne();
        if (arrive.x%2==0){
            sprite.setPosition(arrive.x * PlayScreen.hexWidth *3/4, arrive.y * PlayScreen.hexHeight);
            if (deltaX == 0 && deltaY == 1)
                shipPosition.setOrientation(ShipPosition.Orientation.TOP);
            if (deltaX == 1 && deltaY == 1)
                shipPosition.setOrientation(ShipPosition.Orientation.TOP_RIGHT);
            if (deltaX == 1 && deltaY == 0)
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM_RIGHT);
            if (deltaX == 0 && deltaY == -1)
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM);
            if (deltaX == -1 && deltaY == 0)
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM_LEFT);
            if (deltaX == -1 && deltaY == 1)
                shipPosition.setOrientation(ShipPosition.Orientation.TOP_LEFT);
        } else {
            sprite.setPosition(arrive.x * PlayScreen.hexWidth *3/4, arrive.y * PlayScreen.hexHeight + PlayScreen.hexHeight/2);
            if (deltaX == 0 && deltaY == 1)
                shipPosition.setOrientation(ShipPosition.Orientation.TOP);
            if (deltaX == 1 && deltaY == 0)
                shipPosition.setOrientation(ShipPosition.Orientation.TOP_RIGHT);
            if (deltaX == 1 && deltaY == -1)
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM_RIGHT);
            if (deltaX == 0 && deltaY == -1)
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM);
            if (deltaX == -1 && deltaY == -1)
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM_LEFT);
            if (deltaX == -1 && deltaY == 0)
                shipPosition.setOrientation(ShipPosition.Orientation.TOP_LEFT);
        }
        shipPosition.setPosiiton(arrive);
    }

    public void setLifepoints(int lifepoints) {
        this.lifepoints = lifepoints;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getLifepoints() {
        return lifepoints;
    }

    public Canon getMainCanon() {
        return mainCanon;
    }

    public void setMainCanon(Canon mainCanon) {
        this.mainCanon = mainCanon;
    }

    public Canon getSecondaryCanon() {
        return secondaryCanon;
    }

    public void setSecondaryCanon(Canon secondaryCanon) {
        this.secondaryCanon = secondaryCanon;
    }

    public int getCurrentMovements() {
        return currentMovements;
    }

    public void setCurrentMovements(int currentMovements) {
        this.currentMovements = currentMovements;
    }

    public int getMaxMovements() {
        return maxMovements;
    }

    public boolean isNavigable(){
        return false;
    }

    public ShipPosition getPosition() {
        return shipPosition;
    }
}
