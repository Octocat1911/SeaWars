package com.team5.seawar.ship;


import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ship {
    private Sprite skin;
    private int lifepoints;
    private Canon mainCanon;
    private Canon secondaryCanon;
    private int currentMovements;
    private int maxMovements;
    private ShipPosition shipPosition;

    public Ship(int lifepoints, Canon mainCanon, Canon secondaryCanon, int currentMovements, int maxMovements){
        this.lifepoints = lifepoints;
        this.mainCanon = mainCanon;
        this.secondaryCanon = secondaryCanon;
        this.currentMovements = currentMovements;
        this.maxMovements = maxMovements;
        this.skin = null;
        this.shipPosition = new ShipPosition(0,0, ShipPosition.Orientation.TOP_RIGHT);
    }

    public Sprite getSkin() {
        return skin;
    }

    public void setLifepoints(int lifepoints) {
        this.lifepoints = lifepoints;
    }

    public void setSkin(Sprite skin) {
        this.skin = skin;
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

    public void setMaxMovements(int maxMovements) {
        this.maxMovements = maxMovements;
    }

}
