package com.team5.seawar.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.cam.CamState;
import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.cam.ZoomCam;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.maps.Map;
import com.team5.seawar.utils.Assets;

public class PlayScreen extends ScreenAdapter{
    private GameApp gameApp;
    private Map map;

    private CamState camState;

    private Vector2 position;
    private OrthographicCamera cam;
    private Viewport viewport;

    public static final float SCALE = 12;
    public static final float hexWidth = Assets.getInstance().getTexture("Maptextures/hexEau.png").getWidth()/SCALE;
    public static final float hexHeight = Assets.getInstance().getTexture("Maptextures/hexEau.png").getHeight()/SCALE;

    public PlayScreen(GameApp gameApp, Map map){
        this.gameApp = gameApp;
        this.map = map;
        position = new Vector2(map.getColonne()/2, map.getLigne()/2);
        cam = new OrthographicCamera();
        cam.position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        ZoomCam.getInstance().init(this);
        GlobalCam.getInstance().init(this);
        camState = ZoomCam.getInstance();
    }

    public void handleInput(){
        if (InputHandler.isJustPressed(InputHandler.LEFT) && position.x>0){
            position.x--;
        }
        if (InputHandler.isJustPressed(InputHandler.RIGHT) && position.x<map.getColonne()-1){
            position.x++;
        }
        if (InputHandler.isJustPressed(InputHandler.UP) && position.y<map.getLigne()-1){
            position.y++;
        }
        if (InputHandler.isJustPressed(InputHandler.DOWN) && position.y>0){
            position.y--;
        }
        map.handleInput(cam, viewport);
    }

    public void render(float dt) {
        handleInput();
        camState.update(dt);
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin();
        map.draw(gameApp.getBatch());
        renderTexture(Assets.getInstance().getTexture("Maptextures/hexPointeur.png"), position.x, position.y);
        gameApp.getBatch().end();
    }

    public void renderTexture(Texture texture, float colonne, float ligne){
        if (colonne%2==0){
            gameApp.getBatch().draw(texture, colonne * hexWidth *3/4, ligne * hexHeight, hexWidth, hexHeight);
        } else {
            gameApp.getBatch().draw(texture, colonne * hexWidth *3/4, ligne * hexHeight + hexHeight/2, hexWidth, hexHeight);
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public Map getMap() {
        return map;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setCamState(CamState camState) {
        this.camState = camState;
    }

    public void dispose() {

    }
}

