package com.team5.seawar.screens.playstates;

import com.badlogic.gdx.utils.Array;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.ship.Ship;

public class ShipSelect implements State {
    private PlayScreen playScreen;
    private Array<Ship> player;

    private static ShipSelect instance = new ShipSelect();

    private ShipSelect(){
    }

    public static ShipSelect getInstance(){
        return instance;
    }

    public static void init(PlayScreen playScreen){
        instance.playScreen = playScreen;
        instance.player = playScreen.getMap().getShipJ1();
    }

    public void update(float dt){
        if ((Inputs.isPressed(Inputs.A) || Inputs.isPressed(Inputs.CLICK)) && player.contains(playScreen.getCurrentCase().getShip(), true)){
            playScreen.changeState(ShipSelected.getInstance(playScreen.getCurrentCase()));
        }
    }

    public void newTurn() {
        if (player.equals(playScreen.getMap().getShipJ1())) {
            player = playScreen.getMap().getShipJ2();
        } else {
            player = playScreen.getMap().getShipJ1();
        }
    }
}
