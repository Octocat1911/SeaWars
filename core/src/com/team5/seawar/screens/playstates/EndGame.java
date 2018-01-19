package com.team5.seawar.screens.playstates;

import com.team5.seawar.player.Player;
import com.team5.seawar.screens.PlayScreen;

public class EndGame implements State {
    private PlayScreen playScreen;
    private Player winner;

    private static EndGame instance = new EndGame();
    public static final int VICTOIRE_PACIFIQUE = 1;
    public static final int VICTOIRE_DANS_LES_LARMES_ET_LE_SANG = 2;
    private int type_victoire;

    private EndGame(){
    }

    public static EndGame getInstance(Player player, int type_victoire){
        instance.winner = player;
        instance.type_victoire = type_victoire;
        System.out.println("fini");
        return instance;
    }

    public static void init(PlayScreen playScreen){
        instance.playScreen = playScreen;
    }


    public void update(float dt){
    }

    public void draw(){

    }
}
