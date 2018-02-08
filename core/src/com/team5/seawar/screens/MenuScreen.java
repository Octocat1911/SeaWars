package com.team5.seawar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.maps.Map;
import com.team5.seawar.maps.Map1;
import com.team5.seawar.maps.Map2;
import com.team5.seawar.screens.menustates.MenuState;
import com.team5.seawar.screens.menustates.NewGameMenuState;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Action2DSprite;
import com.team5.seawar.utils.ActionSprite;
import com.team5.seawar.utils.Assets;
import com.team5.seawar.utils.Save;

/**
 * Created with love by Team 5
 */

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
        NewGameMenuState.init(this);
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
        GameApp.MAPS = new Array<Map>();
        GameApp.MAPS.add(new Map1());
        GameApp.MAPS.add(new Map2());
        GameApp.NBMAP = 2;
        for (int i= 3; i <= 5; i++){
            boolean error = false;
            Json json = new Json();
            Save save = new Save();
            try {
                save = json.fromJson(Save.class, Gdx.files.local("map" +i+".txt"));
                GameApp.NBMAP++;
            } catch (SerializationException e){
                error = true;
            }
            if (!error) {
                GameApp.MAPS.add(save.getMap());
            }
        }
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
