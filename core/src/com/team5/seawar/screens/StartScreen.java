package com.team5.seawar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.video.*;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.sun.media.jfxmedia.control.VideoRenderControl;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.utils.Action2DSprite;
import javafx.scene.media.VideoTrack;
import jdk.internal.util.xml.impl.Input;

import java.io.FileNotFoundException;


public class StartScreen extends ScreenAdapter {

    private GameApp gameApp;
    private OrthographicCamera cam;
    private Viewport viewport;
    private VideoPlayer videoPlayer;

    public StartScreen(final GameApp gameApp){
        this.gameApp = gameApp;
        this.cam = new OrthographicCamera();
        this.cam.position.set(GameApp.WIDTH/2f, GameApp.HEIGHT/2f, 0);
        this.viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        this.videoPlayer = VideoPlayerCreator.createVideoPlayer();
        try {
            this.videoPlayer.play(Gdx.files.internal("Menutextures/credit.webm"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle file) {
                MenuScreen.init(gameApp);
                gameApp.setScreen(MenuScreen.getInstance());
            }
        });
        this.videoPlayer.resize(1280,720);
    }

    public void render(float dt){
        handleInput(dt);
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin();
        if(videoPlayer.isPlaying()){
            videoPlayer.render();
        }
        else {
            videoPlayer.stop();
        }
        gameApp.getBatch().end();
    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.SELECT) || Inputs.isPressed(Inputs.CLICK )|| Inputs.isPressed(Inputs.START)){
            videoPlayer.stop();
            MenuScreen.init(gameApp);
            gameApp.setScreen(MenuScreen.getInstance());
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
