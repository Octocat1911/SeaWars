package com.team5.seawar.ship;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.utils.Assets;

public class Amiral extends Ship {

    public Amiral(int colonne, int ligne, ShipPosition.Orientation orientation){
        super(100, new Canon(), new Canon(),3, colonne, ligne, orientation);
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
    }

    public Sprite getSprite() {
        switch (getPosition().getOrientation()){
            case TOP:
                if (!hasFinished()) {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralH_J"+joueur+".png"));
                } else {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralH_end.png"));
                }
                break;
            case TOP_RIGHT:
                if (!hasFinished()) {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralHD_J"+joueur+".png"));
                } else {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralHD_end.png"));
                }
                break;
            case BOTTOM_RIGHT:
                if (!hasFinished()) {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralBD_J"+joueur+".png"));
                } else {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralBD_end.png"));
                }
                break;
            case BOTTOM:
                if (!hasFinished()) {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralB_J"+joueur+".png"));
                } else {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralB_end.png"));
                }
                break;
            case BOTTOM_LEFT:
                if (!hasFinished()) {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralBG_J"+joueur+".png"));
                } else {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralBG_end.png"));
                }
                break;
            case TOP_LEFT:
                if (!hasFinished()) {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralHG_J"+joueur+".png"));
                } else {
                    sprite.setTexture(Assets.getInstance().getTexture("Shiptextures/AmiralHG_end.png"));
                }
                break;
        }
        return sprite;
    }

}
