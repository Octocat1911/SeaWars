package com.team5.seawar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.maps.Map1;
import com.team5.seawar.screens.menustates.MenuState;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Action2DSprite;
import com.team5.seawar.utils.ActionSprite;
import com.team5.seawar.utils.Assets;


public class MenuScreen extends ScreenAdapter {

    private static MenuScreen instance;
    private GameApp gameApp;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Sprite background;
    private Music menu_music;
    private State state;

    private MenuScreen(final GameApp gameApp){
        this.gameApp = gameApp;
        this.cam = new OrthographicCamera();
        this.cam.position.set(GameApp.WIDTH/2f, GameApp.HEIGHT/2f, 0);
        this.background = new Sprite(Assets.getInstance().getTexture("Menutextures/background.png"));
        this.viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        MenuState.init(this);
        state = MenuState.getInstance();
        menu_music = Assets.getInstance().getMusic("Sounds/menu_music.mp3");
        menu_music.setLooping(true);
        menu_music.setVolume(.3f);
    }

    public static void init(GameApp gameApp){
        if(instance ==null){
            instance = new MenuScreen(gameApp);
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public static MenuScreen getInstance(){
        instance.menu_music.play();
        return instance;
    }

    public GameApp getGameApp() {
        return gameApp;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Music getMenu_music() {
        return menu_music;
    }

    public void render(float dt){
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin();
        gameApp.getBatch().draw(background,0,0);
        state.update(dt);
        gameApp.getBatch().end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
