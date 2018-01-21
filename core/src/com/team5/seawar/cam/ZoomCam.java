package com.team5.seawar.cam;

import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.screens.PlayScreen;

public class ZoomCam extends CamState {

    private static ZoomCam instance = new ZoomCam();
    private float speed;

    private ZoomCam(){
    }

    public static ZoomCam getInstance() {
        return instance;
    }

    public void init(PlayScreen playScreen) {
        super.init(playScreen);
        zoom = 1;
    }

    public void update(float dt) {
        nbColonne = playScreen.getMap().getColonne();
        nbLigne = playScreen.getMap().getLigne();

        if (Inputs.isPressed(Inputs.SELECT)){
            playScreen.setCamState(GlobalCam.getInstance());
        }
        cam.zoom += (zoom - cam.zoom) * 0.02f;
        switch (Inputs.mode){
            case MOUSE_MODE:
                speed = 0.0135f;
                break;
            case INPUT_MODE:
                speed = 0.03f;
                break;
        }
        cam.position.add((hexWidth / 2 + position.x * hexWidth * .75f - cam.position.x) * speed,
                (hexHeight / 2 + position.y * hexHeight - cam.position.y) * speed,
                0);

        cam.update();
    }
}
