package com.team5.seawar.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.ship.Ship;
import com.team5.seawar.utils.Assets;

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

    public Vector2 nextShip(Ship currentShip){
        int i = ships.indexOf(currentShip, true);
        int j =  (i + 1)% ships.size;
        while (ships.get(j).hasFinished()){
            j = (j+1)%ships.size;
        }
        Assets.getInstance().getSound("Sounds/transition.ogg").play(.3f);
        return new Vector2(ships.get(j).getPosition().getColonne(), ships.get(j).getPosition().getLigne());
    }

    public Vector2 nextShip(){
        int j = 0;
        while (ships.get(j).hasFinished()){
            j = (j+1)%ships.size;
        }
        Assets.getInstance().getSound("Sounds/transition.ogg").play(.3f);
        return new Vector2(ships.get(j).getPosition().getColonne(), ships.get(j).getPosition().getLigne());
    }
}
