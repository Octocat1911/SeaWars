package com.team5.seawar.screens;

import com.badlogic.gdx.utils.Array;
import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.maps.Map;
import com.team5.seawar.maps.Map2;
import com.team5.seawar.player.Player;
import com.team5.seawar.utils.Assets;

import java.util.ArrayList;

import static com.team5.seawar.game.GameApp.MAPS;

public class MapSelectScreen extends PlayScreen{

    private static MapSelectScreen instance;
    private GameApp gameApp;
    private int currentMap = 0;
    private Array<Map> maps;
    public static final float SCALE = 12;
    public static final float hexWidth = Assets.getInstance().getTexture("Maptextures/hexEau.png").getWidth()/SCALE;
    public static final float hexHeight = Assets.getInstance().getTexture("Maptextures/hexEau.png").getHeight()/SCALE;

    private MapSelectScreen(GameApp gameApp){
        super(gameApp, MAPS.get(0));
        this.maps = GameApp.MAPS;
        setMap(MAPS.get(0));
        this.gameApp = gameApp;
        getCam().position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        setCamState(GlobalCam.getInstance());
        music.stop();
        GlobalCam.getInstance().setCanGoToZoomCam(false);
    }

    public static void init(GameApp gameApp){
            instance = new MapSelectScreen(gameApp);
    }

    public static MapSelectScreen getInstance(){
        return instance;
    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.RIGHT)){
            if (currentMap < maps.size - 1){
                currentMap++;
            } else {
                currentMap = 0;
            }
            setMap(maps.get(currentMap));
        }
        if(Inputs.isPressed(Inputs.START)){
            gameApp.setScreen(new PlayScreen(gameApp, getMap()));
        }
    }

    public void update(float dt){
        handleInput(dt);
        GlobalCam.getInstance().update(dt);
    }

    public void render(float dt) {
        update(dt);
        gameApp.getBatch().setProjectionMatrix(getCam().combined);
        gameApp.getBatch().begin();
        getMap().draw(gameApp.getBatch());
        gameApp.getBatch().setProjectionMatrix(getCamUI().combined);
        gameApp.getBatch().draw(Assets.getInstance().getTexture("UI/validerCarte.png"),300,0, 1280/1.4f, 720/1.4f);
        gameApp.getBatch().end();
    }
}
