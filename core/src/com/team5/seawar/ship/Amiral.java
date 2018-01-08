package com.team5.seawar.ship;

import com.badlogic.gdx.math.Vector2;

public class Amiral extends Ship {

    public Amiral(){
        super(100, new Canon(), new Canon(),3,3);
        Vector2 lprincipal = new Vector2();
        lprincipal.add(new Vector2(0,1));
        lprincipal.add(new Vector2(0,2));
        lprincipal.add(new Vector2(0,3));
        lprincipal.add(new Vector2(0,4));
        Canon principal = new Canon(lprincipal, 50,3);
        this.setMainCanon(principal);

        Vector2 lsecondaire = new Vector2();
        lsecondaire.add(new Vector2(0,1));
        lsecondaire.add(new Vector2(0,2));
        lsecondaire.add(new Vector2(1,0));
        lsecondaire.add(new Vector2(-1,0));
        lsecondaire.add(new Vector2(1,1));
        lsecondaire.add(new Vector2(-1,1));
        Canon secondaire = new Canon(lsecondaire, 30,1);
        this.setSecondaryCanon(secondaire);

    }

}
