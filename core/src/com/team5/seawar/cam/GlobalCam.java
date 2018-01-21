package com.team5.seawar.cam;

import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Assets;

public class GlobalCam extends CamState {

    private static GlobalCam instance = new GlobalCam();
    private boolean canGoToZoomCam;

    private GlobalCam(){
        canGoToZoomCam = true;
    }

    public static GlobalCam getInstance() {
        return instance;
    }

    public void init(PlayScreen playScreen) {
        super.init(playScreen);
        zoom = Math.max(hexWidth * (1+(nbColonne-1) *.75f) / GameApp.WIDTH, hexHeight * (nbLigne+.5f) / GameApp.HEIGHT);
    }

    public void update(float dt) {
        nbColonne = playScreen.getMap().getColonne();
        nbLigne = playScreen.getMap().getLigne();
        zoom = Math.max(hexWidth * (1+(nbColonne-1) *.75f) / GameApp.WIDTH, hexHeight * (nbLigne+.5f) / GameApp.HEIGHT);

        if (Inputs.isPressed(Inputs.SELECT) && canGoToZoomCam){
            Assets.getInstance().getSound("Sounds/zoom.ogg").play(.2f);
            playScreen.setCamState(ZoomCam.getInstance());
        }
        cam.zoom += (zoom - cam.zoom) * 0.02f;
        cam.position.add((( (1 + (nbColonne-1)*.75f) * hexWidth) /2 - cam.position.x) *0.03f,
                ((nbLigne+.5f) * hexHeight / 2- cam.position.y) *0.03f,
                0);
        cam.update();
    }

    public void setCanGoToZoomCam(boolean canGoToZoomCam) {
        this.canGoToZoomCam = canGoToZoomCam;
    }
}

