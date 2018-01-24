package com.team5.seawar.screens;

import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.maps.Map;
import com.team5.seawar.maps.Map2;
import com.team5.seawar.utils.Assets;

import java.util.ArrayList;

public class MapSelectScreen extends PlayScreen{

    private static MapSelectScreen instance;
    private GameApp gameApp;
    private int currentMap = 0;
    private ArrayList<Map> maps;
    private Map map;
    public static final float SCALE = 12;
    public static final float hexWidth = Assets.getInstance().getTexture("Maptextures/hexEau.png").getWidth()/SCALE;
    public static final float hexHeight = Assets.getInstance().getTexture("Maptextures/hexEau.png").getHeight()/SCALE;

    private MapSelectScreen(GameApp gameApp,Map map){
        super(gameApp,map);
        this.maps = new ArrayList<Map>();
        maps.add(map);
        maps.add(new Map2());
        this.map = map;
        this.gameApp = gameApp;
        getCam().position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        setCamState(GlobalCam.getInstance());
        music.stop();
        GlobalCam.getInstance().setCanGoToZoomCam(false);
    }

    public static void init(GameApp gameApp,Map map){
        if(instance ==null){
            instance = new MapSelectScreen(gameApp,map);
        }
    }

    public static MapSelectScreen getInstance(){
        return instance;
    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.RIGHT)){
            if(currentMap < maps.size() - 1){
                currentMap =+ 1;
            }else{
                currentMap = 0;
            }
            setMap(maps.get(currentMap));
        }
    }

    public void update(float dt){
        handleInput(dt);
        getCamState().update(dt);
    }

    public void render(float dt) {
        update(dt);
        gameApp.getBatch().setProjectionMatrix(getCam().combined);
        gameApp.getBatch().begin();
        getMap().draw(gameApp.getBatch());
        gameApp.getBatch().end();
    }
}
