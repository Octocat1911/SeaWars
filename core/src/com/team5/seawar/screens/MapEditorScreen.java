package com.team5.seawar.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.cam.CamState;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.maps.Map;
import com.team5.seawar.utils.Action2DSprite;
import com.team5.seawar.utils.ActionSprite;
import com.team5.seawar.utils.Assets;


public class MapEditorScreen extends ScreenAdapter {

    private GameApp gameApp;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Map map;

    public MapEditorScreen(GameApp gameApp){
        this.gameApp = gameApp;
        this.cam = new OrthographicCamera();
        this.cam.position.set(GameApp.WIDTH/2f, GameApp.HEIGHT/2f, 0);
        this.viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        this.map = new Map(1,1);
        this.map.init();
    }


    public void update(float dt){

    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.UP)){
            this.map.setLigne(this.map.getLigne() + 1);
            this.map.init();
        }
        if(Inputs.isPressed(Inputs.RIGHT)){
            this.map.setColonne(this.map.getColonne() + 1);
            this.map.init();
        }
        if(Inputs.isPressed(Inputs.DOWN)){
            this.map.setLigne(this.map.getLigne() - 1);
            this.map.init();
        }
        if(Inputs.isPressed(Inputs.LEFT)){
            this.map.setColonne(this.map.getColonne() - 1);
            this.map.init();
        }
    }

    public void render(float dt) {
        handleInput(dt);
        update(dt);
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin();
        map.draw(gameApp.getBatch());
        gameApp.getBatch().end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
