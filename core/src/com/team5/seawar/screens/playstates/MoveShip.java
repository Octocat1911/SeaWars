package com.team5.seawar.screens.playstates;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.objects.Case;
import com.team5.seawar.player.Player;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Animation;
import com.team5.seawar.utils.Assets;
import com.team5.seawar.utils.CanonCalcul;

public class MoveShip implements State{
    private PlayScreen playScreen;
    private Case caseSelected;
    private Array<Vector2> portee;
    private Array<Vector2> porteeMax;
    private Array<Case> accessible;
    private Array<Case> accessibleMax;
    private Player player;
    private Player ennemie;

    private static MoveShip instance = new MoveShip();

    private MoveShip(){
        portee = new Array<Vector2>(3);
        portee.setSize(3);
    }

    public static MoveShip getInstance(Case c, Player player, Player ennemie){
        instance.player = player;
        instance.ennemie = ennemie;
        instance.caseSelected = c;
        instance.majPortee();
        instance.majPorteeMax();
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

    public void majPorteeMax(){
        instance.porteeMax = CanonCalcul.reachablePosition(caseSelected.getShip(), playScreen.getMap());
        accessibleMax = new Array<Case>();
        for (Vector2 vector2 : porteeMax){
            //if (vector2.x >= 0 && vector2.x < playScreen.getMap().getColonne() && vector2.y >= 0 && vector2.y < playScreen.getMap().getLigne() && playScreen.getMap().getCase((int)vector2.x, (int)vector2.y).isNavigable()){
                accessibleMax.add(playScreen.getMap().getCase((int)vector2.x, (int)vector2.y));
            //}
        }
    }

    public void update(float dt){
        if (caseSelected.getShip().hasFinished()){
            playScreen.changeState(ShipSelect.getInstance());
        } else if (!caseSelected.getShip().canMove()){
            playScreen.changeState(AttackTurn.getInstance(caseSelected, player, ennemie));
        }
        if ((Inputs.isPressed(Inputs.A) || Inputs.isPressed(Inputs.CLICK))){
            if (accessible.contains(playScreen.getCurrentCase(), true)){
                moveShip(caseSelected, playScreen.getCurrentCase());
                caseSelected = playScreen.getCurrentCase();
                majPortee();
                majPorteeMax();
                if (!caseSelected.getShip().canMove()){
                    if (caseSelected.getShip().canFire()) {
                        playScreen.changeState(AttackTurn.getInstance(caseSelected, player, ennemie));
                    } else {
                        playScreen.changeState(ShipSelect.getInstance());
                    }
                }
            } else {
                playScreen.changeState(ShipSelect.getInstance());
            }
        } else if (Inputs.isPressed(Inputs.X) && caseSelected.getShip().canFire()){
            playScreen.changeState(AttackTurn.getInstance(caseSelected, player, ennemie));
        } else if (Inputs.isPressed(Inputs.START)){
            caseSelected.getShip().finish();
        } else if (Inputs.isPressed(Inputs.B)){
            playScreen.changeState(ShipSelect.getInstance());
        } else if (Inputs.isPressed(Inputs.L1) && !caseSelected.getShip().hasFired() && caseSelected.getShip().getMaxMovements()==caseSelected.getShip().getMovements()){
            caseSelected.getShip().rotateLeft();
        } else if (Inputs.isPressed(Inputs.R1) && !caseSelected.getShip().hasFired() && caseSelected.getShip().getMaxMovements()==caseSelected.getShip().getMovements()){
            caseSelected.getShip().rotateRight();
        }
    }

    public void draw(){
        if (caseSelected.getShip().canMove()) {
            for (Case c : accessible) {
                playScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/hexPortee.png"), c.getPosition().x, c.getPosition().y);
            }
            for (Case c : accessibleMax) {
                playScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/hexPorteeMax.png"), c.getPosition().x, c.getPosition().y);
            }
        }
        playScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/hexSelected.png"), caseSelected.getPosition().x, caseSelected.getPosition().y);
    }

    public void drawUI(){
        playScreen.getBatch().draw(Assets.getInstance().getTexture("UI/MoveShip.png"), 1280-428/1.5f-125, 35, 1675/5, 1006/5);
    }

    public void moveShip(Case depart, Case arrive){
        caseSelected.getShip().move(arrive.getPosition());
        playScreen.getCurrentCase().addShip(caseSelected.getShip());
        caseSelected.deleteShip();
    }
}
