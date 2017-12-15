package com.team5.seawar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.maps.Map1;
import com.team5.seawar.utils.Assets;

public class MenuScreen extends ScreenAdapter {

    private static MenuScreen instance;

    private GameApp gameApp;
    private OrthographicCamera cam;
    private Viewport viewport;

    private Texture background;
    private Texture playbutton;
    private Texture exitbutton;

    private MenuScreen(GameApp gameApp){
        this.gameApp = gameApp;
        this.cam = new OrthographicCamera();
        this.cam.setToOrtho(false);

        background = Assets.getInstance().getTexture("background.png");
        playbutton = Assets.getInstance().getTexture("play.png");
        exitbutton = Assets.getInstance().getTexture("exit.png");

        viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
    }

    public static void init(GameApp gameApp){
        if(instance ==null){
            instance = new MenuScreen(gameApp);
        }
    }

    public static MenuScreen getInstance(){
        return instance;
    }

    public void handleInput(float dt){
        if (InputHandler.getInstance().isJustPressed(InputHandler.getInstance().getA()) ||
            InputHandler.getInstance().isJustPressed(InputHandler.getInstance().getStart()) ||
            InputHandler.getInstance().isMousePressed(InputHandler.getInstance().getClicGauche())){

            gameApp.setScreen(new PlayScreen(gameApp, new Map1()));
        }
    }

    public void render(float dt){
        handleInput(dt);
        gameApp.getBatch().begin();
        gameApp.getBatch().draw(background,0,0);
        gameApp.getBatch().draw(playbutton, GameApp.WIDTH/3 - playbutton.getWidth()/2, 2*GameApp.HEIGHT/3 - playbutton.getHeight()/10, playbutton.getWidth()/5, playbutton.getHeight()/5);
        gameApp.getBatch().draw(exitbutton, GameApp.WIDTH/3 - exitbutton.getWidth()/2, 2*GameApp.HEIGHT/3 - exitbutton.getHeight()/3, exitbutton.getWidth()/5, exitbutton.getHeight()/5);
        gameApp.getBatch().end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }


}
