package com.team5.seawar.screens.playstates;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.objects.Case;
import com.team5.seawar.objects.Element;
import com.team5.seawar.player.Player;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.ship.Canon;
import com.team5.seawar.utils.Animation;
import com.team5.seawar.utils.Assets;

public class AttackTurn implements State{
    private PlayScreen playScreen;
    private Case caseSelected;
    private Array<Case> accessible;
    private Player player;
    private Player ennemie;
    private Canon canon;

    private static AttackTurn instance = new AttackTurn();

    private AttackTurn(){
    }

    public static AttackTurn getInstance(Case c, Player player, Player ennemie){
        instance.player = player;
        instance.ennemie = ennemie;
        instance.caseSelected = c;
        if (instance.caseSelected.getShip().getMainCanon().canAttack()){
            instance.canon = instance.caseSelected.getShip().getMainCanon();
        } else {
            instance.canon = instance.caseSelected.getShip().getSecondaryCanon();
        }
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
        if ((Inputs.isPressed(Inputs.A) || Inputs.isPressed(Inputs.CLICK))){
            if (accessible.contains(playScreen.getCurrentCase(), true) && ennemie.getShips().contains(playScreen.getCurrentCase().getShip(), true)) {
                attackShip(caseSelected, playScreen.getCurrentCase());
                playScreen.changeState(MoveShip.getInstance(caseSelected, player, ennemie));
            } else {
                playScreen.changeState(ShipSelect.getInstance());
            }
        } else if (Inputs.isPressed(Inputs.START)){
            caseSelected.getShip().finish();
        } else if (Inputs.isPressed(Inputs.X) && caseSelected.getShip().canMove()){
            playScreen.changeState(MoveShip.getInstance(caseSelected, player, ennemie));
        } else if (Inputs.isPressed(Inputs.Y)){
            if (canon.equals(caseSelected.getShip().getMainCanon())){
                if (caseSelected.getShip().getSecondaryCanon().canAttack()) {
                    canon = caseSelected.getShip().getSecondaryCanon();
                    majAccessible();
                }
            } else {
                if (caseSelected.getShip().getMainCanon().canAttack()) {
                    canon = caseSelected.getShip().getMainCanon();
                    majAccessible();
                }
            }
        } else if (Inputs.isPressed(Inputs.B)){
            playScreen.changeState(ShipSelect.getInstance());
        } else if (Inputs.isPressed(Inputs.L1) && !caseSelected.getShip().hasFired() && caseSelected.getShip().getMaxMovements()==caseSelected.getShip().getMovements()){
            caseSelected.getShip().rotateLeft();
        } else if (Inputs.isPressed(Inputs.R1) && !caseSelected.getShip().hasFired() && caseSelected.getShip().getMaxMovements()==caseSelected.getShip().getMovements()){
            caseSelected.getShip().rotateRight();
        }
        if (ennemie.getShips().size == 0){
            playScreen.changeState(EndGame.getInstance(player, EndGame.VICTOIRE_DANS_LES_LARMES_ET_LE_SANG));
        }
    }

    public void draw(){
        for (Case c : accessible){
            playScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/cible.png"), c.getPosition().x, c.getPosition().y);
        }
        playScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/hexSelected.png"), caseSelected.getPosition().x, caseSelected.getPosition().y);
    }

    public void drawUI(){
        playScreen.getBatch().draw(Assets.getInstance().getTexture("UI/AttackTurn.png"), 870, 35, 1807/5, 1382/5);
    }

    public void majAccessible(){
        accessible = new Array<Case>();
        Array<Vector2> portee = caseSelected.getShip().getRangeCanon(canon);
        for (Vector2 vector2 : portee){
            float nouveauX = caseSelected.getPosition().x+vector2.x;
            float nouveauY = caseSelected.getPosition().y+(int)vector2.y;
            if (nouveauX >= 0 && nouveauX < playScreen.getMap().getColonne() && nouveauY >= 0 && nouveauY < playScreen.getMap().getLigne() && playScreen.getMap().getCase((int)nouveauX, (int)nouveauY).getElement().getType() != Element.Type.VOID){
                accessible.add(playScreen.getMap().getCase((int)nouveauX, (int)nouveauY));
            }
        }
    }

    public void attackShip(Case attaquant, Case cible){
        cible.getShip().takeDamages(canon.getDamage());
        if (cible.getShip().getCurrentLifePoints() == 0){
            playScreen.getExplosionMort().startPosition(cible.getPosition().x, cible.getPosition().y);
            ennemie.getShips().removeValue(cible.getShip(), true);
            cible.deleteShip();
            Assets.getInstance().getSound("Sounds/tir_mort.ogg").play(.15f);
        } else {
            playScreen.getExplosionDegat().startPosition(cible.getPosition().x, cible.getPosition().y);
            Assets.getInstance().getSound("Sounds/tir_degat.ogg").play();
        }
        attaquant.getShip().attack(canon);
    }
}
