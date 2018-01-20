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
import com.team5.seawar.game.GameApp;
import com.team5.seawar.utils.Action2DSprite;

import java.io.FileNotFoundException;


public class StartScreen extends ScreenAdapter {

    private static StartScreen instance;
    private GameApp gameApp;
    private OrthographicCamera cam;
    private Viewport viewport;
    VideoPlayer videoPlayer;

    private StartScreen(final GameApp gameApp){
        this.gameApp = gameApp;
        this.cam = new OrthographicCamera();
        this.cam.position.set(GameApp.WIDTH/2f, GameApp.HEIGHT/2f, 0);
        this.viewport = new FitViewport(GameApp.WIDTH, GameApp.HEIGHT, cam);
        this.videoPlayer = VideoPlayerCreator.createVideoPlayer();
        try {
            this.videoPlayer.play(Gdx.files.internal("Menutextures/test.webm"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle file) {
                gameApp.setScreen(MenuScreen.getInstance());
            }
        });
        this.videoPlayer.resize(1280,720);
    }

    public static void init(GameApp gameApp){
        if(instance ==null){
            instance = new StartScreen(gameApp);
        }
    }

    public static StartScreen getInstance(){
        return instance;
    }

    public void render(float dt){
        gameApp.getBatch().setProjectionMatrix(cam.combined);
        gameApp.getBatch().begin();
        videoPlayer.render();
        gameApp.getBatch().end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
