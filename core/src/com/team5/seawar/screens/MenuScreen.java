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
import com.team5.seawar.utils.Action2DSprite;
import com.team5.seawar.utils.ActionSprite;
import com.team5.seawar.utils.Assets;


public class MenuScreen extends ScreenAdapter {

    private static MenuScreen instance;
    private GameApp gameApp;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Sprite background;
    private Action2DSprite playButton;
    private Action2DSprite creditButton;
    private Action2DSprite exitButton;
    private Music menu_music;
    private enum Etat {PLAY, CREDIT, EXIT}
    private Etat etat;

    private MenuScreen(final GameApp gameApp){
        this.gameApp = gameApp;
        this.cam = new OrthographicCamera();
        this.cam.position.set(GameApp.WIDTH/2f, GameApp.HEIGHT/2f, 0);
        this.background = new Sprite(Assets.getInstance().getTexture("Menutextures/background.png"));
        this.playButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("Menutextures/play.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/playpush.png"));
                etat = Etat.PLAY;
            }

            @Override
            public void clickedAction(Sprite sprite) {
                menu_music.stop();
                gameApp.setScreen(new PlayScreen(gameApp, new Map1()));
            }

            @Override
            public void defaultAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/play.png"));
            }
        });
        this.creditButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("Menutextures/credit.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/creditpush.png"));
                etat = Etat.CREDIT;
            }

            @Override
            public void clickedAction(Sprite sprite) {
                menu_music.stop();
                gameApp.setScreen(new StartScreen(gameApp));
            }

            @Override
            public void defaultAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/credit.png"));
            }
        });
        this.exitButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("Menutextures/exit.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/exitpush.png"));
                etat = Etat.EXIT;
            }

            @Override
            public void clickedAction(Sprite sprite) {
                Gdx.app.exit();
            }

            @Override
            public void defaultAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/exit.png"));
            }
        });
        this.viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        this.playButton.getSprite().setPosition(GameApp.WIDTH/3 - playButton.getSprite().getWidth()/5, 2*GameApp.HEIGHT/3 - playButton.getSprite().getHeight()/20);
        this.playButton.getSprite().setSize(playButton.getSprite().getWidth()/10,playButton.getSprite().getHeight()/10);
        this.creditButton.getSprite().setPosition(GameApp.WIDTH/3 - creditButton.getSprite().getWidth()/5, 2*GameApp.HEIGHT/3 - creditButton.getSprite().getHeight()/5);
        this.creditButton.getSprite().setSize(creditButton.getSprite().getWidth()/10, creditButton.getSprite().getHeight()/10);
        this.exitButton.getSprite().setPosition(GameApp.WIDTH/3 - exitButton.getSprite().getWidth()/4, 2*GameApp.HEIGHT/3 - exitButton.getSprite().getHeight()/3.4f);
        this.exitButton.getSprite().setSize(exitButton.getSprite().getWidth()/10,exitButton.getSprite().getHeight()/10);

        menu_music = Assets.getInstance().getMusic("Sounds/menu_music.mp3");
        menu_music.setLooping(true);
        menu_music.setVolume(.3f);
    }

    public static void init(GameApp gameApp){
        if(instance ==null){
            instance = new MenuScreen(gameApp);
        }
    }

    public static MenuScreen getInstance(){
        instance.etat = Etat.PLAY;
        instance.menu_music.play();
        return instance;
    }

    public void handleInput(float dt){
        Inputs.isTouched(playButton,cam,viewport);
        Inputs.isJustClicked(playButton,cam,viewport);
        Inputs.isTouched(creditButton,cam,viewport);
        Inputs.isJustClicked(creditButton,cam,viewport);
        Inputs.isTouched(exitButton,cam,viewport);
        Inputs.isJustClicked(exitButton,cam,viewport);
        if (Inputs.isPressed(Inputs.UP)){
            switch (etat){
                case PLAY:
                    etat = Etat.EXIT;
                    break;
                case CREDIT:
                    etat = Etat.PLAY;
                    break;
                case EXIT:
                    etat = Etat.CREDIT;
                    break;
            }
        }
        if (Inputs.isPressed(Inputs.DOWN)){
            switch (etat){
                case PLAY:
                    etat = Etat.CREDIT;
                    break;
                case CREDIT:
                    etat = Etat.EXIT;
                    break;
                case EXIT:
                    etat = Etat.PLAY;
                    break;
            }
        }
        if (Inputs.isPressed(Inputs.A) || Inputs.isPressed(Inputs.START)){
            switch (etat){
                case PLAY:
                    playButton.clickedAction();
                    break;
                case CREDIT:
                    creditButton.clickedAction();
                    break;
                case EXIT:
                    exitButton.clickedAction();
                    break;
            }
        }
    }

    public void render(float dt){
        handleInput(dt);
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin();
        gameApp.getBatch().draw(background,0,0);
        if (Inputs.mode == Inputs.Mode.INPUT_MODE){
            switch (etat){
                case PLAY:
                    playButton.touchAction();
                    break;
                case CREDIT:
                    creditButton.touchAction();
                    break;
                case EXIT:
                    exitButton.touchAction();
            }
        }
        playButton.getSprite().draw(gameApp.getBatch());
        creditButton.getSprite().draw(gameApp.getBatch());
        exitButton.getSprite().draw(gameApp.getBatch());
        gameApp.getBatch().end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
