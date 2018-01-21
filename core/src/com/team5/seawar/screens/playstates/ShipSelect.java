package com.team5.seawar.screens.playstates;

import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.player.Player;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Assets;

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
        instance.playScreen.getBannièreNouveauTour().setTextures(Assets.getInstance().getTexture("UI/Joueur1.png"), Assets.getInstance().getTexture("UI/Tour1.png"));
        instance.playScreen.getBannièreNouveauTour().start();
    }

    public void update(float dt){
        if (player.hasFinished()){
            playScreen.changeState(EndTurn.getInstance(player));
        }
        if ((Inputs.isPressed(Inputs.A) || Inputs.isPressed(Inputs.CLICK)) && player.getShips().contains(playScreen.getCurrentCase().getShip(), true) && !playScreen.getCurrentCase().getShip().hasFinished()){
            playScreen.changeState(MoveShip.getInstance(playScreen.getCurrentCase(),player, ennemie));
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

    public void draw(){
    }


    public void newTurn() {
        if (player.equals(playScreen.getMap().getPlayer1())) {
            player = playScreen.getMap().getPlayer2();
            ennemie = playScreen.getMap().getPlayer1();
            playScreen.getBannièreNouveauTour().setTextures(Assets.getInstance().getTexture("UI/Joueur2.png"), Assets.getInstance().getTexture("UI/Tour2.png"));
        } else {
            player = playScreen.getMap().getPlayer1();
            ennemie = playScreen.getMap().getPlayer2();
            playScreen.getBannièreNouveauTour().setTextures(Assets.getInstance().getTexture("UI/Joueur1.png"), Assets.getInstance().getTexture("UI/Tour1.png"));
        }
        player.newTurn();
        playScreen.getPosition().set(player.nextShip());
        playScreen.getBannièreNouveauTour().start();
    }
}
