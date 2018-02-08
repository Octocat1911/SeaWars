package com.team5.seawar.screens;


import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.maps.Map;
import com.team5.seawar.screens.editorstates.SizeEditionState;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Assets;

/**
 * Created with love by Team 5
 */

public class MapEditorScreen extends PlayScreen {

    private GameApp gameApp;
    private Map map;
    public static final float SCALE = 12;
    public static final float hexWidth = Assets.getInstance().getTexture("Maptextures/hexEau.png").getWidth()/SCALE;
    public static final float hexHeight = Assets.getInstance().getTexture("Maptextures/hexEau.png").getHeight()/SCALE;
    private State state;

    public MapEditorScreen(GameApp gameApp, Map map){
        super(gameApp,map);
        this.map = map;
        this.gameApp = gameApp;
        this.map.init();
        this.state = new SizeEditionState(this);
        getCam().position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        setCamState(GlobalCam.getInstance());
        music.stop();
    }


    public void update(float dt){
        state.update(dt);
    }

    @Override
    public void changeState(State state){
        this.state = state;
    }

    public void render(float dt) {
        update(dt);
        gameApp.getBatch().setProjectionMatrix(getCam().combined);
        gameApp.getBatch().begin();
        state.draw();
        gameApp.getBatch().setProjectionMatrix(getCamUI().combined);
        state.drawUI();
        gameApp.getBatch().end();
    }

}
