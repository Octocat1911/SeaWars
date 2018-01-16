package com.team5.seawar.player;

import com.badlogic.gdx.utils.Array;
import com.team5.seawar.ship.Ship;

public class Player {
    private Array<Ship> ships;

    public Player(){
        ships = new Array<Ship>();
    }

    public Array<Ship> getShips(){
        return ships;
    }

    public void newTurn(){
        for (Ship ship: ships){
            ship.newTurn();
        }
    }

    public boolean hasFinished(){
        for (Ship ship: ships){
            if (!ship.hasFinished()){
                return false;
            }
        }
        return true;
    }
}
