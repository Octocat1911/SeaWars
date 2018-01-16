package com.team5.seawar.screens.playstates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.objects.Case;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.ship.Ship;
import com.team5.seawar.utils.Assets;

public class ShipSelected implements State{
    private PlayScreen playScreen;
    private Case caseSelected;
    private Array<Vector2> portee;
    private Array<Case> accessible;

    private static ShipSelected instance = new ShipSelected();

    private ShipSelected(){
        portee = new Array<Vector2>(3);
        portee.setSize(3);
    }

    public static ShipSelected getInstance(Case c){
        instance.caseSelected = c;
        instance.majPortee();
        return instance;
    }

    public static void init(PlayScreen playScreen){
        instance.playScreen = playScreen;
    }

    public void majPortee(){
        if (caseSelected.getPosition().x%2==0){
            switch (caseSelected.getShip().getPosition().getOrientation()){
                case TOP:
                    portee.set(0,new Vector2(-1,0));
                    portee.set(1,new Vector2(0,1));
                    portee.set(2,new Vector2(1,0));
                    break;
                case TOP_RIGHT:
                    portee.set(0,new Vector2(0,1));
                    portee.set(1,new Vector2(1,0));
                    portee.set(2,new Vector2(1,-1));
                    break;
                case BOTTOM_RIGHT:
                    portee.set(0,new Vector2(1,0));
                    portee.set(1,new Vector2(1,-1));
                    portee.set(2,new Vector2(0,-1));
                    break;
                case BOTTOM:
                    portee.set(0,new Vector2(1,-1));
                    portee.set(1,new Vector2(0,-1));
                    portee.set(2,new Vector2(-1,-1));
                    break;
                case BOTTOM_LEFT:
                    portee.set(0,new Vector2(0,-1));
                    portee.set(1,new Vector2(-1,-1));
                    portee.set(2,new Vector2(-1,0));
                    break;
                case TOP_LEFT:
                    portee.set(0,new Vector2(-1,-1));
                    portee.set(1,new Vector2(-1,0));
                    portee.set(2,new Vector2(0,1));
                    break;
            }
        } else {
            switch (caseSelected.getShip().getPosition().getOrientation()){
                case TOP:
                    portee.set(0,new Vector2(-1,1));
                    portee.set(1,new Vector2(0,1));
                    portee.set(2,new Vector2(1,1));
                    break;
                case TOP_RIGHT:
                    portee.set(0,new Vector2(0,1));
                    portee.set(1,new Vector2(1,1));
                    portee.set(2,new Vector2(1,0));
                    break;
                case BOTTOM_RIGHT:
                    portee.set(0,new Vector2(1,1));
                    portee.set(1,new Vector2(1,0));
                    portee.set(2,new Vector2(0,-1));
                    break;
                case BOTTOM:
                    portee.set(0,new Vector2(1,0));
                    portee.set(1,new Vector2(0,-1));
                    portee.set(2,new Vector2(-1,0));
                    break;
                case BOTTOM_LEFT:
                    portee.set(0,new Vector2(0,-1));
                    portee.set(1,new Vector2(-1,-0));
                    portee.set(2,new Vector2(-1,1));
                    break;
                case TOP_LEFT:
                    portee.set(0,new Vector2(-1,-0));
                    portee.set(1,new Vector2(-1,1));
                    portee.set(2,new Vector2(0,1));
                    break;
            }
        }
        accessible = new Array<Case>();
        for (Vector2 vector2 : portee){
            float nouveauX = caseSelected.getPosition().x+vector2.x;
            float nouveauY = caseSelected.getPosition().y+(int)vector2.y;
            if (nouveauX >= 0 && nouveauX < playScreen.getMap().getColonne() && nouveauY >= 0 && nouveauY < playScreen.getMap().getLigne() && playScreen.getMap().getCase((int)nouveauX, (int)nouveauY).isNavigable()){
                accessible.add(playScreen.getMap().getCase((int)nouveauX, (int)nouveauY));
            }
        }

    }

    public void update(float dt){
        for (Case c : accessible){
                playScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/hexPortee.png"), c.getPosition().x, c.getPosition().y);
        }
        if ((Inputs.isPressed(Inputs.A) || Inputs.isPressed(Inputs.CLICK))){
            if (accessible.contains(playScreen.getCurrentCase(), true)){
                moveShip(caseSelected, playScreen.getCurrentCase());
                caseSelected = playScreen.getCurrentCase();
                majPortee();
                if (!caseSelected.getShip().canMove()){
                    playScreen.changeState(ShipSelect.getInstance());
                }
            } else {
                playScreen.changeState(ShipSelect.getInstance());
            }
        }
    }

    public void moveShip(Case depart, Case arrive){
        caseSelected.getShip().move(arrive.getPosition());
        playScreen.getCurrentCase().addShip(caseSelected.getShip());
        caseSelected.deleteShip();
    }
}
