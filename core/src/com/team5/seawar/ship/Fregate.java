package com.team5.seawar.ship;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Assets;

public class Fregate extends Ship {

    public Fregate(int colonne, int ligne, ShipPosition.Orientation orientation){
        super(50, new Canon(), new Canon(),6, colonne, ligne, orientation);
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

    public void draw(SpriteBatch sb) {
        switch (getPosition().getOrientation()){
            case TOP:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateH_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateH_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case TOP_RIGHT:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateHD_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateHD_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case BOTTOM_RIGHT:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateBD_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateBD_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case BOTTOM:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateB_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateB_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case BOTTOM_LEFT:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateBG_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateBG_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
            case TOP_LEFT:
                if (!hasFinished()) {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateHG_J"+joueur+".png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                } else {
                    sb.draw(Assets.getInstance().getTexture("Shiptextures/FregateHG_end.png"), posX, posY, PlayScreen.hexWidth, PlayScreen.hexHeight);
                }
                break;
        }
    }

}
