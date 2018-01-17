package com.team5.seawar.screens.playstates;

import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.player.Player;
import com.team5.seawar.screens.PlayScreen;

public class ShipSelect implements State {
    private PlayScreen playScreen;
    private Player player;
    private Player ennemie;

    private static ShipSelect instance = new ShipSelect();

    private ShipSelect(){
    }

    public static ShipSelect getInstance(){
        return instance;
    }

    public static void init(PlayScreen playScreen){
        instance.playScreen = playScreen;
        instance.player = playScreen.getMap().getPlayer1();
        instance.ennemie = playScreen.getMap().getPlayer2();
    }

    public void update(float dt){
        if (player.hasFinished()){
            playScreen.changeState(EndTurn.getInstance(player));
        }
        if ((Inputs.isPressed(Inputs.A) || Inputs.isPressed(Inputs.CLICK)) && player.getShips().contains(playScreen.getCurrentCase().getShip(), true) && !playScreen.getCurrentCase().getShip().hasFinished()){
            playScreen.changeState(MoveShip.getInstance(playScreen.getCurrentCase(),ennemie));
        } else if (Inputs.isPressed(Inputs.R1)){
            if (player.getShips().contains(playScreen.getCurrentCase().getShip(), true)){
                playScreen.getPosition().set(player.nextShip(playScreen.getCurrentCase().getShip()));
            } else {
                playScreen.getPosition().set(player.nextShip());
            }
        } else if (Inputs.isPressed(Inputs.START)){
            playScreen.changeState(EndTurn.getInstance(player));
        }
    }

    public void newTurn() {
        if (player.equals(playScreen.getMap().getPlayer1())) {
            player = playScreen.getMap().getPlayer2();
            ennemie = playScreen.getMap().getPlayer1();
        } else {
            player = playScreen.getMap().getPlayer1();
            ennemie = playScreen.getMap().getPlayer2();
        }
        player.newTurn();
        playScreen.getPosition().set(player.nextShip());
    }
}
