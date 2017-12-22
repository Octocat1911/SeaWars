package com.team5.seawar.cam;


import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.screens.PlayScreen;

public class ZoomCam extends CamState {

    private static ZoomCam instance = new ZoomCam();

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
        if (InputHandler.isJustPressed(InputHandler.SELECT)){
            playScreen.setCamState(GlobalCam.getInstance());
        }
        cam.zoom += (zoom - cam.zoom) * 0.02f;
        cam.position.add((hexWidth/2 + position.x * hexWidth*.75f - cam.position.x)*0.03f,
                (hexHeight/2 + position.y * hexHeight - cam.position.y)*0.03f,
                0);
        cam.update();
    }
}
