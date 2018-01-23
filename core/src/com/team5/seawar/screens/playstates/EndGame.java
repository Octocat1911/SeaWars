package com.team5.seawar.screens.playstates;

import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.player.Player;
import com.team5.seawar.screens.MenuScreen;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Assets;

public class EndGame implements State {
    private PlayScreen playScreen;
    private int winner;
    private int type_victoire;

    private static EndGame instance = new EndGame();
    public static final int VICTOIRE_PACIFIQUE = 1;
    public static final int VICTOIRE_DANS_LES_LARMES_ET_LE_SANG = 2;


    private EndGame(){
    }

    public static EndGame getInstance(Player player, int type_victoire){
        if (player.equals(instance.playScreen.getMap().getPlayer1())) {
            instance.winner = 1;
        } else {
            instance.winner = 2;
        }
        instance.type_victoire = type_victoire;
        GlobalCam.getInstance().setCanGoToZoomCam(false);
        instance.playScreen.setCamState(GlobalCam.getInstance());
        return instance;
    }

    public static void init(PlayScreen playScreen){
        instance.playScreen = playScreen;
    }


    public void update(float dt){
        if (Inputs.isPressed(Inputs.START) || Inputs.isPressed(Inputs.CLICK)){
            playScreen.getMusic().stop();
            playScreen.getGameApp().setScreen(MenuScreen.getInstance());
        }
    }

    public void draw(){
    }

    public void drawUI(){
        playScreen.getBatch().draw(Assets.getInstance().getTexture("UI/Victoire_"+winner+"_"+type_victoire+".png"), 0, 0);
    }
}
