package com.team5.seawar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.maps.Map;
import com.team5.seawar.utils.Assets;

public class PlayScreen extends ScreenAdapter{
    private GameApp gameApp;
    private Map map;

    private Vector2 position;
    private OrthographicCamera cam;
    private Viewport viewport;

    private enum CamState{zoomCam, globalCam}
    private CamState camState;
    private float zoom = 1;
    private float globalZoom;

    public static final float SCALE = 12;
    public static final float hexWidth = Assets.getInstance().getTexture("hexEau.png").getWidth()/SCALE;
    public static final float hexHeight = Assets.getInstance().getTexture("hexEau.png").getHeight()/SCALE;

    public PlayScreen(GameApp gameApp, Map map){
        this.gameApp = gameApp;
        this.map = map;
        position = new Vector2(map.getColonne()/2, map.getLigne()/2);
        cam = new OrthographicCamera();
        cam.position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        camState = CamState.zoomCam;
        viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        globalZoom = Math.max(hexWidth * (1+(map.getColonne()-1) *.75f) / GameApp.WIDTH, hexHeight * (map.getLigne()+.5f) / GameApp.HEIGHT);
    }

    public void update(float dt){
        if (InputHandler.getInstance().isJustPressed(InputHandler.getInstance().getGauche()) && position.x>0){
            position.x--;
        }
        if (InputHandler.getInstance().isJustPressed(InputHandler.getInstance().getDroite()) && position.x<map.getColonne()-1){
            position.x++;
        }
        if (InputHandler.getInstance().isJustPressed(InputHandler.getInstance().getHaut()) && position.y<map.getLigne()-1){
            position.y++;
        }
        if (InputHandler.getInstance().isJustPressed(InputHandler.getInstance().getBas()) && position.y>0){
            position.y--;
        }
        if (InputHandler.getInstance().isJustPressed(InputHandler.getInstance().getSelect())){
            switch (camState){
                case zoomCam:
                    camState = CamState.globalCam;
                    break;
                case globalCam:
                    camState = CamState.zoomCam;
                    break;
            }
        }
    }

    public void camUpdate(float dt){
        switch (camState){
            case zoomCam:
                cam.zoom += (zoom - cam.zoom) * 0.02f;
                cam.position.add((hexWidth/2 + position.x * hexWidth*.75f - cam.position.x)*0.03f,
                        (hexHeight/2 + position.y * hexHeight - cam.position.y)*0.03f,
                        0);
                break;
            case globalCam:
                cam.zoom += (globalZoom - cam.zoom) * 0.02f;
                cam.position.add((( (1 + (map.getColonne()-1)*.75f) * hexWidth) /2 - cam.position.x) *0.03f,
                        ((map.getLigne()+.5f) * hexHeight / 2- cam.position.y) *0.03f,
                        0);
                break;
        }
        cam.update();
    }

    //A deléguer plus tard...
    public void render(float dt) {
        update(dt);
        camUpdate(dt);
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin();
        for (int i=0; i<map.getColonne(); i++){
            for (int j=0; j<map.getLigne(); j++) {
                renderTexture(map.getElement(i,j).getTexture(), i, j);
            }
        }
        renderTexture(Assets.getInstance().getTexture("hexPointeur.png"), position.x, position.y);
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

    public void dispose() {

    }
}
