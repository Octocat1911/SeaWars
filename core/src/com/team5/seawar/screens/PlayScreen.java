package com.team5.seawar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.cam.CamState;
import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.cam.ZoomCam;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.maps.Map;
import com.team5.seawar.objects.Case;
import com.team5.seawar.player.Player;
import com.team5.seawar.screens.playstates.*;
import com.team5.seawar.screens.uistates.UIState;
import com.team5.seawar.utils.Animation;
import com.team5.seawar.utils.Assets;
import com.team5.seawar.utils.NewTurnBanner;
import com.team5.seawar.utils.Save;

import java.util.Random;

/**
 * Created with love by Team 5
 */

public class PlayScreen extends ScreenAdapter{

    private GameApp gameApp;
    private Map map;
    private State state;
    private CamState camState;
    public static Vector2 position;
    private OrthographicCamera cam;
    private OrthographicCamera camUI;
    private Viewport viewport;
    private Viewport viewportUI;
    public static final float SCALE = 12;
    public static final float hexWidth = Assets.getInstance().getTexture("Maptextures/hexEau.png").getWidth()/SCALE;
    public static final float hexHeight = Assets.getInstance().getTexture("Maptextures/hexEau.png").getHeight()/SCALE;
    private Animation explosionDegat;
    private Animation explosionMort;
    private NewTurnBanner newTurnBanner;
    protected Music music;
    private boolean debutJeu = true;
    private Player player;
    private Player ennemie;

    public PlayScreen(final GameApp gameApp){
        this.gameApp = gameApp;

        Json json = new Json();
        Save save = json.fromJson(Save.class, Gdx.files.local("save.txt"));
        this.map = save.getMap();

        map.load();

        newTurnBanner = new NewTurnBanner();

        if (map.getTour_joueur() == 1){
            player = map.getPlayer1();
            ennemie = map.getPlayer2();
            newTurnBanner.setTextures(Assets.getInstance().getTexture("UI/Joueur1.png"), Assets.getInstance().getTexture("UI/Tour1.png"));
        } else {
            player = map.getPlayer2();
            ennemie = map.getPlayer1();
            newTurnBanner.setTextures(Assets.getInstance().getTexture("UI/Joueur2.png"), Assets.getInstance().getTexture("UI/Tour2.png"));
        }

        position = new Vector2(map.getColonne()/2, map.getLigne()/2);

        ShipSelect.init(this);
        MoveShip.init(this);
        AttackTurn.init(this);
        EndTurn.init(this);
        EndGame.init(this);

        UIState.init(this);

        state = ShipSelect.getInstance();

        cam = new OrthographicCamera();
        cam.position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);

        camUI = new OrthographicCamera();
        camUI.position.set(GameApp.WIDTH/2f, GameApp.HEIGHT/2f, 0);
        viewportUI = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, camUI);

        ZoomCam.getInstance().init(this);
        GlobalCam.getInstance().init(this);

        camState = ZoomCam.getInstance();

        explosionDegat  = new Animation(new TextureRegion(Assets.getInstance().getTexture("Effects/explosionDegat.png")), 43, .4f, hexWidth, hexHeight, 10, 5);
        explosionMort  = new Animation(new TextureRegion(Assets.getInstance().getTexture("Effects/explosionMort.png")), 50, .9f, hexWidth, hexHeight, -8, 5);

        Random random = new Random();
        int randomNumber = random.nextInt(3);
        if(randomNumber == 0){
            music = Assets.getInstance().getMusic("Sounds/playscreen_music.mp3");
            music.setVolume(.3f);
        }if(randomNumber == 1){
            music = Assets.getInstance().getMusic("Sounds/playscreen_music2.mp3");
            music.setVolume(.3f);
        }if(randomNumber == 2){
            music = Assets.getInstance().getMusic("Sounds/playscreen_music3.mp3");
            music.setVolume(.2f);
        }
        music.play();
        music.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music1) {
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber== 0){
                    music = Assets.getInstance().getMusic("Sounds/playscreen_music.mp3");
                    music.setVolume(.3f);
                    music.play();
                }if(randomNumber == 1){
                    music = Assets.getInstance().getMusic("Sounds/playscreen_music2.mp3");
                    music.setVolume(.3f);
                    music.play();
                }if(randomNumber == 2){
                    music = Assets.getInstance().getMusic("Sounds/playscreen_music3.mp3");
                    music.setVolume(.2f);
                    music.play();
                }
            }
        });
    }

    public PlayScreen(final GameApp gameApp, Map map){
        this.gameApp = gameApp;
        this.map = map;


        try {
            map.load();
            map.majNbLighthouses();
        } catch (java.lang.NullPointerException e){

        }

        player = map.getPlayer1();
        ennemie = map.getPlayer2();

        position = new Vector2(map.getColonne()/2, map.getLigne()/2);

        newTurnBanner = new NewTurnBanner();
        newTurnBanner.setTextures(Assets.getInstance().getTexture("UI/Joueur1.png"), Assets.getInstance().getTexture("UI/Tour1.png"));

        ShipSelect.init(this);
        MoveShip.init(this);
        AttackTurn.init(this);
        EndTurn.init(this);
        EndGame.init(this);

        UIState.init(this);

        state = ShipSelect.getInstance();

        cam = new OrthographicCamera();
        cam.position.set(hexWidth/2 + position.x * hexWidth*.75f, hexHeight/2 + position.y * hexHeight, 0);
        viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);

        camUI = new OrthographicCamera();
        camUI.position.set(GameApp.WIDTH/2f, GameApp.HEIGHT/2f, 0);
        viewportUI = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, camUI);

        ZoomCam.getInstance().init(this);
        GlobalCam.getInstance().init(this);

        camState = ZoomCam.getInstance();

        explosionDegat  = new Animation(new TextureRegion(Assets.getInstance().getTexture("Effects/explosionDegat.png")), 43, .4f, hexWidth, hexHeight, 10, 5);
        explosionMort  = new Animation(new TextureRegion(Assets.getInstance().getTexture("Effects/explosionMort.png")), 50, .9f, hexWidth, hexHeight, -8, 5);

        Random random = new Random();
        int randomNumber = random.nextInt(3);
        if(randomNumber == 0){
            music = Assets.getInstance().getMusic("Sounds/playscreen_music.mp3");
            music.setVolume(.3f);
        }if(randomNumber == 1){
            music = Assets.getInstance().getMusic("Sounds/playscreen_music2.mp3");
            music.setVolume(.3f);
        }if(randomNumber == 2){
            music = Assets.getInstance().getMusic("Sounds/playscreen_music3.mp3");
            music.setVolume(.2f);
        }
        music.play();
        music.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music1) {
                Random random = new Random();
                int randomNumber = random.nextInt(3);
                if(randomNumber== 0){
                    music = Assets.getInstance().getMusic("Sounds/playscreen_music.mp3");
                    music.setVolume(.3f);
                    music.play();
                }if(randomNumber == 1){
                    music = Assets.getInstance().getMusic("Sounds/playscreen_music2.mp3");
                    music.setVolume(.3f);
                    music.play();
                }if(randomNumber == 2){
                    music = Assets.getInstance().getMusic("Sounds/playscreen_music3.mp3");
                    music.setVolume(.2f);
                    music.play();
                }
            }
        });

    }

    public void handleInput(){
        if (Inputs.isPressed(Inputs.LEFT) && position.x>0){
            position.x--;
        }
        if (Inputs.isPressed(Inputs.RIGHT) && position.x<map.getColonne()-1){
            position.x++;
        }
        if (Inputs.isPressed(Inputs.UP) && position.y<map.getLigne()-1){
            position.y++;
        }
        if (Inputs.isPressed(Inputs.DOWN) && position.y>0){
            position.y--;
        }
        map.handleInput(cam, viewport);
    }

    public void update(float dt){
        if (debutJeu){
            Assets.getInstance().getSound("Sounds/new_player.ogg").play(.5f);
            debutJeu = false;
        }
        handleInput();
        map.update(dt);
        camState.update(dt);
        state.update(dt);
        explosionDegat.update(dt);
        explosionMort.update(dt);
        newTurnBanner.update(dt);
    }

    public void render(float dt) {
        update(dt);
        //MAP
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin(); //
        map.draw(gameApp.getBatch());
        state.draw();
        renderTexture(Assets.getInstance().getTexture("Maptextures/hexPointeur.png"), position.x, position.y);
        explosionDegat.draw(gameApp.getBatch());
        explosionMort.draw(gameApp.getBatch());

        //UI
        gameApp.getBatch().setProjectionMatrix(camUI.combined);
        state.drawUI();
        UIState.getInstance().drawUI();
        newTurnBanner.draw(gameApp.getBatch());
        gameApp.getBatch().end(); //
    }

    public void renderTexture(Texture texture, float colonne, float ligne){
        if (colonne%2==0){
            gameApp.getBatch().draw(texture, colonne * hexWidth *3/4, ligne * hexHeight, hexWidth, hexHeight);
        } else {
            gameApp.getBatch().draw(texture, colonne * hexWidth *3/4, ligne * hexHeight + hexHeight/2, hexWidth, hexHeight);
        }
    }

    public Case getCurrentCase() {
        return map.getCase((int)position.x, (int)position.y);
    }

    public void changeState(State state){
        this.state = state;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        viewportUI.update(width, height);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public OrthographicCamera getCamUI() {
        return camUI;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setCamState(CamState camState) {
        this.camState = camState;
    }

    public void dispose() {

    }

    public SpriteBatch getBatch(){
        return gameApp.getBatch();
    }

    public Animation getExplosionDegat() {
        return explosionDegat;
    }

    public Animation getExplosionMort() {
        return explosionMort;
    }

    public NewTurnBanner getNewTurnBanner() {
        return newTurnBanner;
    }

    public CamState getCamState() {
        return camState;
    }

    public GameApp getGameApp() {
        return gameApp;
    }

    public Music getMusic() {
        return music;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getEnnemie() {
        return ennemie;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setEnnemie(Player ennemie){
        this.ennemie = ennemie;
    }
}