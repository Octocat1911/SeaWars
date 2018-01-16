package com.team5.seawar.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.ship.Ship;

public class Player {
    private String nickname;
    private Sprite avatar;
    private Array<Ship> ships;

    public Player(int nbShips){
        ships = new Array<Ship>(3);
    }

    public Array<Ship> getShips(){
        return ships;
    }
}
