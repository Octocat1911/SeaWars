package com.team5.seawar.ship;

import com.badlogic.gdx.math.Vector2;

public class Fregate extends Ship {

    public Fregate(){
        super(50, new Canon(), new Canon(),7,7);
        Vector2 lprincipal = new Vector2();
        lprincipal.add(new Vector2(0,1));
        lprincipal.add(new Vector2(0,2));
        lprincipal.add(new Vector2(1,0));
        lprincipal.add(new Vector2(-1,0));
        lprincipal.add(new Vector2(1,-1));
        lprincipal.add(new Vector2(-1,-1));
        Canon principal = new Canon(lprincipal, 30,1);
        this.setMainCanon(principal);

        Vector2 lsecondaire = new Vector2();
        lsecondaire.add(new Vector2(0,1));
        lsecondaire.add(new Vector2(1,0));
        lsecondaire.add(new Vector2(-1,0));
        lsecondaire.add(new Vector2(1,-1));
        lsecondaire.add(new Vector2(-1,-1));
        lsecondaire.add(new Vector2(0,-1));
        Canon secondaire = new Canon(lsecondaire, 10,0);
        this.setSecondaryCanon(secondaire);

    }
}
