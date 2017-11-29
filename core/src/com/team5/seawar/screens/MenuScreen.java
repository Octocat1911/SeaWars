package com.team5.seawar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.game.Game;

public class MenuScreen extends ScreenAdapter {

    private static MenuScreen instance;

    private Game game;
    private OrthographicCamera cam;
    private Viewport viewport;

    private Texture background;
    private Texture playbutton;
    private Texture exitbutton;

    private MenuScreen(Game game){
        this.game = game;
        this.cam = new OrthographicCamera();

        background = new Texture("background.png");
        playbutton = new Texture("play.png");
        exitbutton = new Texture("exit.png");

        viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, cam);
    }

    public static void init(Game game){
        if(instance ==null){
            instance = new MenuScreen(game);
        }
    }

    public static MenuScreen getInstance(){
        return instance;
    }

    public void  update(float dt){

    }

    public void render(float dt){
        update(dt);
        game.getBatch().begin();
        game.getBatch().draw(background,0,0);
        game.getBatch().draw(playbutton, Game.WIDTH/3 - playbutton.getWidth()/2, 2*Game.HEIGHT/3 - playbutton.getHeight()/10, playbutton.getWidth()/5, playbutton.getHeight()/5);
        game.getBatch().draw(exitbutton, Game.WIDTH/3 - exitbutton.getWidth()/2, 2*Game.HEIGHT/3 - exitbutton.getHeight()/3, exitbutton.getWidth()/5, exitbutton.getHeight()/5);
        game.getBatch().end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }


}
