package com.team5.seawar.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.maps.Map1;
import com.team5.seawar.utils.Action2DSprite;
import com.team5.seawar.utils.ActionSprite;
import com.team5.seawar.utils.Assets;

import java.awt.event.ActionListener;

public class MenuScreen extends ScreenAdapter {

    private static MenuScreen instance;
    private GameApp gameApp;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Sprite background;
    /*private Sprite playButton;
    private Sprite exitButton;*/
    private Action2DSprite playButton;
    private Action2DSprite exitButton;
    private Image image;

    private MenuScreen(final GameApp gameApp){
        this.gameApp = gameApp;
        this.cam = new OrthographicCamera();
        this.cam.position.set(GameApp.WIDTH/2f, GameApp.HEIGHT/2f, 0);
        this.background = new Sprite(Assets.getInstance().getTexture("background.png"));
        //this.playButton = new Sprite(Assets.getInstance().getTexture("play.png"));
        //this.exitButton = new Sprite(Assets.getInstance().getTexture("exit.png"));
        this.playButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("play.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("play2.png"));
            }

            @Override
            public void clickedAction(Sprite sprite) {
                gameApp.setScreen(new PlayScreen(gameApp, new Map1()));
            }

            @Override
            public void defaultAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("play.png"));
            }
        });
        this.exitButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("exit.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {

            }

            @Override
            public void clickedAction(Sprite sprite) {

            }

            @Override
            public void defaultAction(Sprite sprite) {

            }
        });
        this.viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        this.playButton.getSprite().setPosition(GameApp.WIDTH/3 - playButton.getSprite().getWidth()/2, 2*GameApp.HEIGHT/3 - playButton.getSprite().getHeight()/10);
        this.playButton.getSprite().setSize(playButton.getSprite().getWidth()/5,playButton.getSprite().getHeight()/5);
        this.exitButton.getSprite().setPosition(GameApp.WIDTH/3 - exitButton.getSprite().getWidth()/2, 2*GameApp.HEIGHT/3 - exitButton.getSprite().getHeight()/3);
        this.exitButton.getSprite().setSize(exitButton.getSprite().getWidth()/5, exitButton.getSprite().getHeight()/5);
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
       /* if (InputHandler.getInstance().isJustClicked(playButton,cam,viewport)){
            gameApp.setScreen(new PlayScreen(gameApp, new Map1()));
        }if(InputHandler.getInstance().isTouched(playButton,cam,viewport)){
            playButton.setTexture(Assets.getInstance().getTexture("play2.png"));
        }else {
            playButton.setTexture(Assets.getInstance().getTexture("play.png"));
        }*/
       InputHandler.getInstance().isTouched(playButton,cam,viewport);
       InputHandler.getInstance().isJustClicked(playButton,cam,viewport);
    }

    public void render(float dt){
        handleInput(dt);
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin();
        gameApp.getBatch().draw(background,0,0);
        playButton.getSprite().draw(gameApp.getBatch());
        exitButton.getSprite().draw(gameApp.getBatch());
        gameApp.getBatch().end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }


}
