package com.team5.seawar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.cam.CamState;
import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.cam.ZoomCam;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.maps.Map;
import com.team5.seawar.screens.playstates.State;


public class MapEditorScreen extends ScreenAdapter {

    private GameApp gameApp;
    private Map map;
    private CamState camState;
    public static Vector2 position;
    private OrthographicCamera cam;
    private Viewport viewport;
    private TextArea textArea;

    public MapEditorScreen(GameApp gameApp){
        this.gameApp = gameApp;

        viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        camState = ZoomCam.getInstance();
        this.textArea = new TextArea("Map size",new Skin(Gdx.files.external("Menutextures/play.png")));

    }


    public void update(float dt){

    }

    public void render(float dt) {
        update(dt);
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin(); //
        textArea.draw(gameApp.getBatch(),dt);
        gameApp.getBatch().end(); //
    }

}
