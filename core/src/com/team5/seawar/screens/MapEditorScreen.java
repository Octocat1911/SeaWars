package com.team5.seawar.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.cam.CamState;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.maps.Map;
import com.team5.seawar.screens.playstates.State;


public class MapEditorScreen extends ScreenAdapter {

    private GameApp gameApp;
    private Map map;
    private State state;
    private CamState camState;
    public static Vector2 position;
    private OrthographicCamera cam;
    private Viewport viewport;
    private TextField textField;

    public MapEditorScreen(GameApp gameApp){

    }


    public void update(float dt){

    }

    public void render(float dt) {
        update(dt);
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin(); //
        textField.draw(gameApp.getBatch(),dt);
        gameApp.getBatch().end(); //
    }

}
