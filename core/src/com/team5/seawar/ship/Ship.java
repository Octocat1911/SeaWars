package com.team5.seawar.ship;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Assets;
import com.team5.seawar.utils.CanonCalcul;

public class Ship{
    private int maxLifePoints;
    private int currentLifePoints;

    private int maxMovements;
    private int movements;

    private Canon mainCanon;
    private Canon secondaryCanon;

    private ShipPosition shipPosition;

    private boolean hasFired; //utilisé seulement pour rotationer un bateau
    private boolean canFire;
    private boolean hasFinished;

    private Vector2 destination;
    private float speedAnimation = 0.05f;

    protected int joueur;

    protected float posX;
    protected float posY;

    public enum Type {AMIRAL, FREGATE}
    private Type type;

    public Ship(int colonne, int ligne, ShipPosition.Orientation orientation, Type type){
        this.destination = new Vector2();
        this.shipPosition = new ShipPosition(colonne,ligne,orientation);
        this.posX = colonne * PlayScreen.hexWidth *3/4;
        if (colonne%2==0){
            this.posY = ligne * PlayScreen.hexHeight;
        } else {
            this.posY = ligne * PlayScreen.hexHeight + PlayScreen.hexHeight/2;
        }
        destination.x = posX;
        destination.y = posY;
        this.hasFinished = false;
        this.canFire = true;
        this.hasFired = false;
        this.type = type;

        if (type == Type.AMIRAL){
            this.maxLifePoints = 100;
            this.maxMovements = 3;
            Array<Vector2> lprincipal = new Array<Vector2>();
            lprincipal.add(new Vector2(0,1));
            lprincipal.add(new Vector2(0,2));
            lprincipal.add(new Vector2(0,3));
            lprincipal.add(new Vector2(0,4));
            Canon principal = new Canon(lprincipal, 50,3);
            this.setMainCanon(principal);

            Array<Vector2> lsecondaire = new Array<Vector2>();
            lsecondaire.add(new Vector2(0,1));
            lsecondaire.add(new Vector2(0,2));
            lsecondaire.add(new Vector2(1,0));
            lsecondaire.add(new Vector2(-1,0));
            lsecondaire.add(new Vector2(1,1));
            lsecondaire.add(new Vector2(-1,1));
            Canon secondaire = new Canon(lsecondaire, 30,1);
            this.setSecondaryCanon(secondaire);
        } else {
            this.maxLifePoints = 50;
            this.maxMovements = 6;
            Array<Vector2> lprincipal = new Array<Vector2>();
            lprincipal.add(new Vector2(0,1));
            lprincipal.add(new Vector2(0,2));
            lprincipal.add(new Vector2(1,0));
            lprincipal.add(new Vector2(-1,0));
            lprincipal.add(new Vector2(1,-1));
            lprincipal.add(new Vector2(-1,-1));
            Canon principal = new Canon(lprincipal, 30,1);
            this.setMainCanon(principal);

            Array<Vector2> lsecondaire = new Array<Vector2>();
            lsecondaire.add(new Vector2(0,1));
            lsecondaire.add(new Vector2(1,0));
            lsecondaire.add(new Vector2(-1,0));
            lsecondaire.add(new Vector2(1,-1));
            lsecondaire.add(new Vector2(-1,-1));
            lsecondaire.add(new Vector2(0,-1));
            Canon secondaire = new Canon(lsecondaire, 10,0);
            this.setSecondaryCanon(secondaire);
        }
        this.currentLifePoints = maxLifePoints;
        this.movements = maxMovements;
    }

    public  void draw(SpriteBatch sb){
        String navire = "";
        switch (type){
            case AMIRAL:
                navire = "Amiral";
                break;
            case FREGATE:
                navire = "Fregate";
                break;
        }
        switch (getPosition().getOrientation()){
            case TOP:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"H_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"H_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case TOP_RIGHT:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"HD_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"HD_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case BOTTOM_RIGHT:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"BD_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"BD_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case BOTTOM:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"B_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"B_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case BOTTOM_LEFT:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"BG_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"BG_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case TOP_LEFT:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"HG_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/"+navire+"HG_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
        }

    }

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
        posX += (destination.x - posX) * speedAnimation;
        posY += (destination.y - posY) * speedAnimation;
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
