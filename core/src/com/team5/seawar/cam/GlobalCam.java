package com.team5.seawar.cam;

import com.team5.seawar.game.GameApp;
import com.team5.seawar.screens.PlayScreen;

public class GlobalCam extends PlayScreenCam{

    private static GlobalCam instance = new GlobalCam();

    private GlobalCam(){
    }

    public static GlobalCam getInstance() {
        return instance;
    }

    public void init(PlayScreen playScreen) {
        super.init(playScreen);
        zoom = Math.max(playScreen.hexWidth * (1+(playScreen.getMap().getColonne()-1) *.75f) / GameApp.WIDTH, playScreen.hexHeight * (playScreen.getMap().getLigne()+.5f) / GameApp.HEIGHT);
    }


    public void update(float dt) {
        playScreen.getCam().zoom += (globalZoom - cam.zoom) * 0.02f;
        cam.position.add((( (1 + (map.getColonne()-1)*.75f) * hexWidth) /2 - cam.position.x) *0.03f,
                ((map.getLigne()+.5f) * hexHeight / 2- cam.position.y) *0.03f,
                0);
    }
}
