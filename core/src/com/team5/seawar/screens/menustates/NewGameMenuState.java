package com.team5.seawar.screens.menustates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.maps.Map;
import com.team5.seawar.maps.Map1;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.MapSelectScreen;
import com.team5.seawar.screens.MenuScreen;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Action2DSprite;
import com.team5.seawar.utils.ActionSprite;
import com.team5.seawar.utils.Assets;
import com.team5.seawar.utils.Save;

public class NewGameMenuState implements State {

    private static NewGameMenuState instance;
    private MenuScreen menuScreen;
    private Action2DSprite newGameButton;
    private Action2DSprite backButton;
 
    private Action2DSprite loadButton;
    private enum Etat {NEWGAME, BACK, LOAD}
    private Etat etat;


    private NewGameMenuState(final MenuScreen menuScreen){
        this.menuScreen = menuScreen;
        this.newGameButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("Menutextures/NewGame.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/NewGamepush.png"));
                etat = NewGameMenuState.Etat.NEWGAME;
            }

            @Override
            public void clickedAction(Sprite sprite) {
                menuScreen.getMenu_music().stop();
                MapSelectScreen.init(menuScreen.getGameApp());
                menuScreen.getGameApp().setScreen(MapSelectScreen.getInstance());
            }

            @Override
            public void defaultAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/NewGame.png"));
            }
        });
        this.backButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("Menutextures/Back.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/Backpush.png"));
                etat = NewGameMenuState.Etat.BACK;
            }

            @Override
            public void clickedAction(Sprite sprite) {
                menuScreen.setState(MenuState.getInstance());
            }

            @Override
            public void defaultAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/Back.png"));
            }
        });
       
        this.loadButton = new Action2DSprite(new Sprite(Assets.getInstance().getTexture("Menutextures/Load.png")), new ActionSprite() {
            @Override
            public void touchAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/Loadpush.png"));
                etat = Etat.LOAD;
            }

            @Override
            public void clickedAction(Sprite sprite) {
                try {
                    Json json = new Json();
                    Save save = json.fromJson(Save.class, Gdx.files.local("save.txt"));
                    menuScreen.getGameApp().setScreen(new PlayScreen(menuScreen.getGameApp()));
                    menuScreen.getMenu_music().stop();
                } catch (SerializationException e){
                    System.err.println("No Save Found");
                }

            }

            @Override
            public void defaultAction(Sprite sprite) {
                sprite.setTexture(Assets.getInstance().getTexture("Menutextures/Load.png"));
            }
        });
        this.loadButton.getSprite().setPosition(GameApp.WIDTH/3 - loadButton.getSprite().getWidth()/3.5f, 2*GameApp.HEIGHT/3 - loadButton.getSprite().getHeight()/5);
        this.loadButton.getSprite().setSize(loadButton.getSprite().getWidth()/10, loadButton.getSprite().getHeight()/10);
        this.newGameButton.getSprite().setPosition(GameApp.WIDTH/3 - newGameButton.getSprite().getWidth()/7, 2*GameApp.HEIGHT/3 - newGameButton.getSprite().getHeight()/20);
        this.newGameButton.getSprite().setSize(newGameButton.getSprite().getWidth()/10, newGameButton.getSprite().getHeight()/10);
        this.backButton.getSprite().setPosition(GameApp.WIDTH/3 - backButton.getSprite().getWidth()/4, 2*GameApp.HEIGHT/3 - backButton.getSprite().getHeight()/2.7f);
        this.backButton.getSprite().setSize(backButton.getSprite().getWidth()/10, backButton.getSprite().getHeight()/10);

    }

    public static void init(MenuScreen menuScreen){
        if(instance ==null){
            instance = new NewGameMenuState(menuScreen);
        }
    }



    public static NewGameMenuState getInstance(){
        instance.etat = Etat.NEWGAME;
        return instance;
    }

    public void handleInput(float dt){
        Inputs.isTouched(newGameButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isJustClicked(newGameButton,menuScreen.getCam(),menuScreen.getViewport());

        Inputs.isTouched(backButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isJustClicked(backButton,menuScreen.getCam(),menuScreen.getViewport());

        Inputs.isTouched(loadButton,menuScreen.getCam(),menuScreen.getViewport());
        Inputs.isJustClicked(loadButton,menuScreen.getCam(),menuScreen.getViewport());
        if (Inputs.isPressed(Inputs.UP)){
            switch (etat){
                case NEWGAME:
                    etat = NewGameMenuState.Etat.BACK;
                    break;
                case BACK:
                    etat = NewGameMenuState.Etat.LOAD;
                    break;
                case LOAD:
                    etat = NewGameMenuState.Etat.NEWGAME;
                    break;
            }
        }
        if (Inputs.isPressed(Inputs.DOWN)){
            switch (etat){
                case NEWGAME:
                    etat = NewGameMenuState.Etat.LOAD;
                    break;
                case LOAD:
                    etat = NewGameMenuState.Etat.BACK;
                    break;
                case BACK:
                    etat = NewGameMenuState.Etat.NEWGAME;
                    break;
            }
        }
        if (Inputs.isPressed(Inputs.A) || Inputs.isPressed(Inputs.START)){
            switch (etat){
                case NEWGAME:
                    newGameButton.clickedAction();
                    break;
                case LOAD:
                    loadButton.clickedAction();
                    break;
                case BACK:
                    backButton.clickedAction();
                    break;
                
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        if (Inputs.mode == Inputs.Mode.INPUT_MODE){
            switch (etat){
                case NEWGAME:
                    newGameButton.touchAction();
                    break;
                case LOAD:
                    loadButton.touchAction();
                    break;
                case BACK:
                    backButton.touchAction();
                    break;
            }
        }
        newGameButton.getSprite().draw(menuScreen.getGameApp().getBatch());
        backButton.getSprite().draw(menuScreen.getGameApp().getBatch());
        loadButton.getSprite().draw(menuScreen.getGameApp().getBatch());
    }

    @Override
    public void draw() {

    }

    @Override
    public void drawUI() {

    }
}
