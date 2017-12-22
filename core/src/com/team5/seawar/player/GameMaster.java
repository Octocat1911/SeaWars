package com.team5.seawar.player;

import com.team5.seawar.maps.Map;
import com.team5.seawar.ship.Ship;

public class GameMaster {
    private Map map;
    private GameMaster uniqueInstance = new GameMaster();

    public void init(Map map) {
        this.map = map;
    }

    private GameMaster(){
    }

    public GameMaster getInstance() {
        return uniqueInstance;
    }

    public void attackShip(Ship ship, int i, int j){
        map.getElement(i,j);
    }
}

