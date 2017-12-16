package com.team5.seawar.cam;

import com.team5.seawar.screens.PlayScreen;

public class ZoomCam extends PlayScreenCam{

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
        playScreen.getCam().zoom += (zoom - playScreen.getCam().zoom) * 0.02f;
        playScreen.getCam().position.add((playScreen.hexWidth/2 + playScreen.getPosition().x * playScreen.hexWidth*.75f - playScreen.getPosition().x)*0.03f,
                (playScreen.hexHeight/2 + playScreen.getPosition().y * playScreen.hexHeight - playScreen.getCam().position.y)*0.03f,
                0);
    }
}
