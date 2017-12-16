package com.team5.seawar.cam;

import com.team5.seawar.screens.PlayScreen;

public abstract class PlayScreenCam {

    protected PlayScreen playScreen;
    protected float zoom;

    public void init(PlayScreen playScreen){
        this.playScreen = playScreen;
    }

    public abstract void update(float dt);
}
