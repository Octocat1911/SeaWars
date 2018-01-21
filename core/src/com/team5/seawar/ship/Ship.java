package com.team5.seawar.ship;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Assets;
import com.team5.seawar.utils.CanonCalcul;

public abstract class Ship{
    private int maxLifePoints;
    private int currentLifePoints;

    private int maxMovements;
    private int movements;

    private Canon mainCanon;
    private Canon secondaryCanon;

    private ShipPosition shipPosition;

    protected Sprite sprite;

    private boolean hasFired; //utilisé seulement pour rotationer un bateau
    private boolean canFire;
    private boolean hasFinished;

    private Vector2 destination;
    private float speedAnimation = 0.05f;

    protected int joueur;

    public Ship(int maxLifePoints, Canon mainCanon, Canon secondaryCanon, int maxMovements, int colonne, int ligne, ShipPosition.Orientation orientation){
        this.destination = new Vector2();
        this.maxLifePoints = maxLifePoints;
        this.currentLifePoints = maxLifePoints;
        this.mainCanon = mainCanon;
        this.secondaryCanon = secondaryCanon;
        this.maxMovements = maxMovements;
        this.movements = maxMovements;
        this.shipPosition = new ShipPosition(colonne,ligne,orientation);
        this.sprite = new Sprite(Assets.getInstance().getTexture("Maptextures/void.png"));
        if (colonne%2==0){
            sprite.setPosition(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight);
        } else {
            sprite.setPosition(colonne * PlayScreen.hexWidth *3/4, ligne * PlayScreen.hexHeight + PlayScreen.hexHeight/2);
        }
        destination.set(sprite.getX(), sprite.getY());
        sprite.setSize(PlayScreen.hexWidth, PlayScreen.hexHeight);
        this.hasFinished = false;
        this.canFire = true;
        this.hasFired = false;
    }

    public abstract Sprite getSprite();

    public void move(Vector2 arrive){
        float deltaX = arrive.x - shipPosition.getColonne();
        float deltaY = arrive.y - shipPosition.getLigne();
        if (arrive.x%2==0){
            destination.set(arrive.x * PlayScreen.hexWidth *3/4, arrive.y * PlayScreen.hexHeight);
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
            destination.set(arrive.x * PlayScreen.hexWidth *3/4, arrive.y * PlayScreen.hexHeight + PlayScreen.hexHeight/2);
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
        movements--;
        if (!canMove() && !canFire){
            hasFinished = true;
        }
        Assets.getInstance().getSound("Sounds/deplacement_bateau.ogg").play(.2f);
    }

    public void setMainCanon(Canon mainCanon) {
        this.mainCanon = mainCanon;
    }

    public void setSecondaryCanon(Canon secondaryCanon) {
        this.secondaryCanon = secondaryCanon;
    }

    public ShipPosition getPosition() {
        return shipPosition;
    }

    public int getMaxLifePoints(){
        return maxLifePoints;
    }

    public int getCurrentLifePoints(){
        return currentLifePoints;
    }

    public int getMaxMovements(){
        return maxMovements;
    }

    public int getMovements(){
        return movements;
    }

    public ShipPosition getShipPosition() {
        return shipPosition;
    }

    public void takeDamages(int damages){
        currentLifePoints = currentLifePoints - damages;
        if (currentLifePoints<0)
            currentLifePoints = 0;
    }

    public boolean canMove(){
        return movements > 0;
    }

    public void newTurn(){
        hasFinished = false;
        movements = maxMovements;
        mainCanon.newTurn();
        secondaryCanon.newTurn();
        if (mainCanon.canAttack() || secondaryCanon.canAttack()){
            canFire = true;
        } else {
            canFire = false;
        }
        hasFired = false;
    }

    public boolean canFire(){
        return canFire;
    }

    public void attack(Canon canon){
        canFire = false;
        hasFired = true;
        canon.attack();
        if (!canMove()){
            hasFinished = true;
        }
    }

    public boolean hasFinished(){
        return hasFinished;
    }

    public void finish(){
        hasFinished = true;
    }

    public Array<Vector2> getRangeCanon(Canon canon){
        Array<Vector2> range = new Array<Vector2>();
        for (Vector2 vector2 : canon.getRange()){
            if (shipPosition.getColonne()%2 == 0){
                range.add(CanonCalcul.rotation(vector2, shipPosition.getOrientation(), false));
            } else {
                range.add(CanonCalcul.rotation(CanonCalcul.even2odd(vector2), shipPosition.getOrientation(), true));
            }
        }
        return range;
    }

    public Canon getMainCanon() {
        return mainCanon;
    }

    public Canon getSecondaryCanon() {
        return secondaryCanon;
    }

    public void update(float dt){
        sprite.setPosition(sprite.getX() + (destination.x - sprite.getX()) * speedAnimation, sprite.getY() + (destination.y - sprite.getY()) * speedAnimation);
    }

    public void setHasFinished(boolean bool){
        hasFinished = bool;
    }

    public boolean hasFired(){
        return hasFired;
    }

    public void rotateRight(){
        switch (shipPosition.getOrientation()){
            case TOP:
                shipPosition.setOrientation(ShipPosition.Orientation.TOP_RIGHT);
                break;
            case TOP_RIGHT:
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM_RIGHT);
                break;
            case BOTTOM_RIGHT:
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM);
                break;
            case BOTTOM:
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM_LEFT);
                break;
            case BOTTOM_LEFT:
                shipPosition.setOrientation(ShipPosition.Orientation.TOP_LEFT);
                break;
            case TOP_LEFT:
                shipPosition.setOrientation(ShipPosition.Orientation.TOP);
                break;
        }
        hasFinished = true;
    }

    public void rotateLeft(){
        switch (shipPosition.getOrientation()){
            case TOP:
                shipPosition.setOrientation(ShipPosition.Orientation.TOP_LEFT);
                break;
            case TOP_RIGHT:
                shipPosition.setOrientation(ShipPosition.Orientation.TOP);
                break;
            case BOTTOM_RIGHT:
                shipPosition.setOrientation(ShipPosition.Orientation.TOP_RIGHT);
                break;
            case BOTTOM:
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM_RIGHT);
                break;
            case BOTTOM_LEFT:
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM);
                break;
            case TOP_LEFT:
                shipPosition.setOrientation(ShipPosition.Orientation.BOTTOM_LEFT);
                break;
        }
        hasFinished = true;
    }

    public void setJoueur(int joueur){
        this.joueur = joueur;
    }
}
