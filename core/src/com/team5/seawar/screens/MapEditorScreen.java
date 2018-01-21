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
    private Map map;
    public static final float SCALE = 12;
    public static final float hexWidth = Assets.getInstance().getTexture("Maptextures/hexEau.png").getWidth()/SCALE;
    public static final float hexHeight = Assets.getInstance().getTexture("Maptextures/hexEau.png").getHeight()/SCALE;

    public MapEditorScreen(GameApp gameApp, Map map){
        super(gameApp,map);
        this.map = map;
        this.gameApp = gameApp;
        this.map.init();
        getCam().position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        setCamState(GlobalCam.getInstance());
        GlobalCam.getInstance().setCanGoToZoomCam(false);
    }


    public void update(float dt){
        handleInput(dt);
        getCamState().update(dt);
    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.UP)){
            this.map.setLigne(this.map.getLigne() + 1);
            this.map.init();
            PlayScreen.position.y++;
        }
        if(Inputs.isPressed(Inputs.RIGHT)){
            this.map.setColonne(this.map.getColonne() + 1);
            this.map.init();
            PlayScreen.position.x++;
        }
        if(Inputs.isPressed(Inputs.DOWN)){
            this.map.setLigne(this.map.getLigne() - 1);
            this.map.init();
            if(!(this.map.getLigne()<6)){
                PlayScreen.position.y--;
            }

        }
        if(Inputs.isPressed(Inputs.LEFT)){
            this.map.setColonne(this.map.getColonne() - 1);
            this.map.init();
            if(!(this.map.getColonne()<6)){
                PlayScreen.position.x--;
            }

        }
        map.handleInput(getCam(),getViewport());
    }

    public void render(float dt) {
        update(dt);
        gameApp.getBatch().setProjectionMatrix(getCam().combined);
        gameApp.getBatch().begin();
        map.draw(gameApp.getBatch());
        gameApp.getBatch().end();
    }

}
