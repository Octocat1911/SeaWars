package com.team5.seawar.screens.playstates;

import com.team5.seawar.objects.Element;
import com.team5.seawar.player.Player;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.ship.Ship;

public class EndTurn implements State {
    private PlayScreen playScreen;
    private Player player;

    private static EndTurn instance = new EndTurn();

    private EndTurn(){
    }

    public static EndTurn getInstance(Player player){
        instance.player = player;
        return instance;
    }

    public static void init(PlayScreen playScreen){
        instance.playScreen = playScreen;
    }


    public void update(float dt){
        for (Ship ship: player.getShips()){
            ship.setHasFinished(false);
            if (playScreen.getMap().getCase(ship.getPosition().getColonne(),ship.getPosition().getLigne()).getElement().getType() == Element.Type.LIGHTHOUSE){
                playScreen.getMap().getCase(ship.getPosition().getColonne(),ship.getPosition().getLigne()).setProprietaire(player);
            }
        }
        int nbLighthouse = 0;
        for (int i=0; i<playScreen.getMap().getColonne(); i++){
            for (int j=0; j<playScreen.getMap().getLigne(); j++) {
                if (playScreen.getMap().getCase(i, j).getProprietaire() == player){
                    nbLighthouse++;
                }
            }
        }
        if (nbLighthouse == playScreen.getMap().getNbLighthouses()){
            playScreen.changeState(EndGame.getInstance(player, EndGame.VICTOIRE_PACIFIQUE));
        } else {
            ShipSelect.getInstance().newTurn();
            playScreen.changeState(ShipSelect.getInstance());
        }
    }

    public void draw(){

    }
}
