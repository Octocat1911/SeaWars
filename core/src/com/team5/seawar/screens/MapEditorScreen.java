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
import com.team5.seawar.utils.Action2DSprite;
import com.team5.seawar.utils.ActionSprite;
import com.team5.seawar.utils.Assets;


public class MapEditorScreen extends PlayScreen {

    private GameApp gameApp;
    private OrthographicCamera cam;
    private CamState camState;
    private Viewport viewport;
    private Map map;
    public static Vector2 position;
    public static final float SCALE = 12;
    public static final float hexWidth = Assets.getInstance().getTexture("Maptextures/hexEau.png").getWidth()/SCALE;
    public static final float hexHeight = Assets.getInstance().getTexture("Maptextures/hexEau.png").getHeight()/SCALE;

    public MapEditorScreen(GameApp gameApp, Map map){
        super(gameApp,map);
        this.map = map;
        this.gameApp = gameApp;
        this.cam = new OrthographicCamera();
        this.cam.position.set(GameApp.WIDTH/2f, GameApp.HEIGHT/2f, 0);
        this.viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        this.map.init();
        position = new Vector2(map.getColonne()/2, map.getLigne()/2);
        cam.position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        camState = GlobalCam.getInstance();
    }


    public void update(float dt){
        handleInput(dt);
        camState.update(dt);
    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.UP)){
            this.map.setLigne(this.map.getLigne() + 1);
            this.map.init();
            position.y++;
        }
        if(Inputs.isPressed(Inputs.RIGHT)){
            this.map.setColonne(this.map.getColonne() + 1);
            this.map.init();
            position.x++;
        }
        if(Inputs.isPressed(Inputs.DOWN)){
            this.map.setLigne(this.map.getLigne() - 1);
            this.map.init();
            if(!(this.map.getLigne()<1)){
                position.y--;
            }

        }
        if(Inputs.isPressed(Inputs.LEFT)){
            this.map.setColonne(this.map.getColonne() - 1);
            this.map.init();
            if(!(this.map.getColonne()<1)){
                position.x--;
            }

        }
        map.handleInput(cam,viewport);
    }

    public void render(float dt) {
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
