package com.team5.seawar.screens.playstates;

import com.team5.seawar.player.Player;
import com.team5.seawar.screens.PlayScreen;

public class EndTurn implements State {
    private PlayScreen playScreen;

    private static EndTurn instance = new EndTurn();

    private EndTurn(){
    }

    public static EndTurn getInstance(Player player){
        player.newTurn();
        return instance;
    }

    public static void init(PlayScreen playScreen){
        instance.playScreen = playScreen;
    }


    public void update(float dt){
        ShipSelect.getInstance().newTurn();
        playScreen.changeState(ShipSelect.getInstance());
    }
}
