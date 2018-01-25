package com.team5.seawar.screens.menustates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.maps.Map;
import com.team5.seawar.maps.Map1;
import com.team5.seawar.screens.*;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Action2DSprite;
import com.team5.seawar.utils.ActionSprite;
import com.team5.seawar.utils.Assets;

public class MenuState implements State {

    private static MenuState instance;
    private MenuScreen menuScreen;
    private Action2DSprite playButton;
    private Action2DSprite creditButton;
    private Action2DSprite exitButton;
    private Action2DSprite mapEditorButton;
    private enum Etat {PLAY, CREDIT,MEDIT, EXIT}
    private Etat etat;


    private MenuState(final MenuScreen menuScreen){
        this.menuScreen = menuScreen;
        this.playButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("Menutextures/play.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/playpush.png"));
                etat = MenuState.Etat.PLAY;
            }

            @Override
            public void clickedAction(Sprite sprite) {
                MapSelectScreen.init(menuScreen.getGameApp());
                menuScreen.setState(NewGameMenuState.getInstance());
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
                etat = MenuState.Etat.CREDIT;
            }

            @Override
            public void clickedAction(Sprite sprite) {
                menuScreen.getMenu_music().stop();
                menuScreen.getGameApp().setScreen(new StartScreen(menuScreen.getGameApp()));
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
                etat = MenuState.Etat.EXIT;
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
        this.mapEditorButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("Menutextures/MapEditor.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/MapEditorpush.png"));
                etat = Etat.MEDIT;
            }

            @Override
            public void clickedAction(Sprite sprite) {
                menuScreen.getGameApp().setScreen(new MapEditorScreen(menuScreen.getGameApp(),new Map(7,6)));
            }

            @Override
            public void defaultAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/MapEditor.png"));
            }
        });
        this.mapEditorButton.getSprite().setPosition(GameApp.WIDTH/3 - mapEditorButton.getSprite().getWidth()/9.8f, 2*GameApp.HEIGHT/3 - mapEditorButton.getSprite().getHeight()/5);
        this.mapEditorButton.getSprite().setSize(mapEditorButton.getSprite().getWidth()/10,mapEditorButton.getSprite().getHeight()/10);
        this.playButton.getSprite().setPosition(GameApp.WIDTH/3 - playButton.getSprite().getWidth()/5, 2*GameApp.HEIGHT/3 - playButton.getSprite().getHeight()/20);
        this.playButton.getSprite().setSize(playButton.getSprite().getWidth()/10,playButton.getSprite().getHeight()/10);
        this.creditButton.getSprite().setPosition(GameApp.WIDTH/3 - creditButton.getSprite().getWidth()/5, 2*GameApp.HEIGHT/3 - creditButton.getSprite().getHeight()/2.7f);
        this.creditButton.getSprite().setSize(creditButton.getSprite().getWidth()/10, creditButton.getSprite().getHeight()/10);
        this.exitButton.getSprite().setPosition(GameApp.WIDTH/3 - exitButton.getSprite().getWidth()/4, 2*GameApp.HEIGHT/3 - exitButton.getSprite().getHeight()/2.2f);
        this.exitButton.getSprite().setSize(exitButton.getSprite().getWidth()/10,exitButton.getSprite().getHeight()/10);
    }

    public static void init(MenuScreen menuScreen){
        if(instance ==null){
            instance = new MenuState(menuScreen);
        }
    }



    public static MenuState getInstance(){
        instance.etat = Etat.PLAY;
        return instance;
    }

    public void handleInput(float dt){
        Inputs.isTouched(playButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isJustClicked(playButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isTouched(creditButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isJustClicked(creditButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isTouched(exitButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isJustClicked(exitButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isTouched(mapEditorButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isJustClicked(mapEditorButton,menuScreen.getCam(),menuScreen.getViewport());
        if (Inputs.isPressed(Inputs.UP)){
            switch (etat){
                case PLAY:
                    etat = MenuState.Etat.EXIT;
                    break;
                case CREDIT:
                    etat = MenuState.Etat.MEDIT;
                    break;
                case MEDIT:
                    etat = MenuState.Etat.PLAY;
                    break;
                case EXIT:
                    etat = MenuState.Etat.CREDIT;
                    break;
            }
        }
        if (Inputs.isPressed(Inputs.DOWN)){
            switch (etat){
                case PLAY:
                    etat = MenuState.Etat.MEDIT;
                    break;
                case CREDIT:
                    etat = MenuState.Etat.EXIT;
                    break;
                case MEDIT:
                    etat = MenuState.Etat.CREDIT;
                    break;
                case EXIT:
                    etat = MenuState.Etat.PLAY;
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
                case MEDIT:
                    mapEditorButton.clickedAction();
                    break;
                case EXIT:
                    exitButton.clickedAction();
                    break;
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        if (Inputs.mode == Inputs.Mode.INPUT_MODE){
            switch (etat){
                case PLAY:
                    playButton.touchAction();
                    break;
                case CREDIT:
                    creditButton.touchAction();
                    break;
                case MEDIT:
                    mapEditorButton.touchAction();
                    break;
                case EXIT:
                    exitButton.touchAction();
            }
        }
        playButton.getSprite().draw(menuScreen.getGameApp().getBatch());
        creditButton.getSprite().draw(menuScreen.getGameApp().getBatch());
        exitButton.getSprite().draw(menuScreen.getGameApp().getBatch());
        mapEditorButton.getSprite().draw(menuScreen.getGameApp().getBatch());
    }

    @Override
    public void draw() {

    }

    @Override
    public void drawUI() {

    }
}
