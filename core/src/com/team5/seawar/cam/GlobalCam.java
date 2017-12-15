package com.team5.seawar.cam;

import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.screens.PlayScreen;

public class GlobalCam extends CamState {

    private static GlobalCam instance = new GlobalCam();

    private GlobalCam(){
    }

    public static GlobalCam getInstance() {
        return instance;
    }

    public void init(PlayScreen playScreen) {
        super.init(playScreen);
        zoom = Math.max(hexWidth * (1+(nbColonne-1) *.75f) / GameApp.WIDTH, hexHeight * (nbLigne+.5f) / GameApp.HEIGHT);
    }


    public void update(float dt) {
        if (InputHandler.getInstance().isJustPressed(InputHandler.getInstance().getSelect())){
            playScreen.setCamState(ZoomCam.getInstance());
        }
        cam.zoom += (zoom - cam.zoom) * 0.02f;
        cam.position.add((( (1 + (nbColonne-1)*.75f) * hexWidth) /2 - cam.position.x) *0.03f,
                ((nbLigne+.5f) * hexHeight / 2- cam.position.y) *0.03f,
                0);
        cam.update();
    }
}
