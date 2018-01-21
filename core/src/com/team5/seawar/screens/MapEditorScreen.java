package com.team5.seawar.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.cam.CamState;
import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.cam.ZoomCam;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.maps.Map;
import com.team5.seawar.screens.editorstates.MapEditionState;
import com.team5.seawar.screens.editorstates.SizeEditionState;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Action2DSprite;
import com.team5.seawar.utils.ActionSprite;
import com.team5.seawar.utils.Assets;


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
        MapEditionState.init(this);
        SizeEditionState.init(this);
        this.state = SizeEditionState.getInstance();
        getCam().position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        setCamState(GlobalCam.getInstance());
        GlobalCam.getInstance().setCanGoToZoomCam(false);
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
        gameApp.getBatch().end();
    }

}
