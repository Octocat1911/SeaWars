package com.team5.seawar.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.team5.seawar.ship.Ship;

import java.util.List;

public class Player {
    private String nickname;
    private Sprite avatar;
    private List<Ship> ships;

    public Player(String nickname,Sprite avatar, List<Ship> ships){
        this.nickname = nickname;
        this.avatar = avatar;
        this.ships = ships;
    }

    public Ship getShip(int i){
        return ships.get(i);
    }

    public int getShipsSize(){
        return ships.size();
    }

    public String getNickname() {
        return nickname;
    }

    public Sprite getAvatar() {
        return avatar;
    }

}
