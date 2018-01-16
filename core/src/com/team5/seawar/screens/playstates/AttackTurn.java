package com.team5.seawar.screens.playstates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.objects.Case;
import com.team5.seawar.player.Player;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Assets;

public class AttackTurn implements State{
    private PlayScreen playScreen;
    private Case caseSelected;
    private Array<Case> accessible;
    private Player ennemie;

    private static AttackTurn instance = new AttackTurn();

    private AttackTurn(){
    }

    public static AttackTurn getInstance(Case c, Player ennemie){
        instance.ennemie = ennemie;
        instance.caseSelected = c;
        instance.majAccessible();
        return instance;
    }

    public static void init(PlayScreen playScreen){
        instance.playScreen = playScreen;
    }


    public void update(float dt) {
        if (caseSelected.getShip().hasFinished()){
            playScreen.changeState(ShipSelect.getInstance());
        }
        for (Case c : accessible){
            playScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/hexPortee.png"), c.getPosition().x, c.getPosition().y);
        }
        if ((Inputs.isPressed(Inputs.A) || Inputs.isPressed(Inputs.CLICK))){
            if (accessible.contains(playScreen.getCurrentCase(), true) && ennemie.getShips().contains(playScreen.getCurrentCase().getShip(), true)) {
                attackShip(caseSelected, playScreen.getCurrentCase());
                playScreen.changeState(ShipSelected.getInstance(caseSelected, ennemie));
            } else {
                playScreen.changeState(ShipSelect.getInstance());
            }
        } else if (Inputs.isPressed(Inputs.START)){
            caseSelected.getShip().finish();
        }
    }

    public void majAccessible(){
        accessible = new Array<Case>();
        Array<Vector2> portee = caseSelected.getShip().getRangeMainCanon();
        for (Vector2 vector2 : portee){
            float nouveauX = caseSelected.getPosition().x+vector2.x;
            float nouveauY = caseSelected.getPosition().y+(int)vector2.y;
            if (nouveauX >= 0 && nouveauX < playScreen.getMap().getColonne() && nouveauY >= 0 && nouveauY < playScreen.getMap().getLigne()){
                accessible.add(playScreen.getMap().getCase((int)nouveauX, (int)nouveauY));
            }
        }
    }

    public void attackShip(Case attaquant, Case cible){
        cible.getShip().takeDamages(attaquant.getShip().getMainCanon().getDamage());
        if (cible.getShip().getCurrentLifePoints() == 0){
            ennemie.getShips().removeValue(cible.getShip(), true);
            cible.deleteShip();
        }
        attaquant.getShip().attack();
    }
}
