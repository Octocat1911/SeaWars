package com.team5.seawar.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.inputHandler.XBoxHandler;
import com.team5.seawar.screens.MenuScreen;
import com.team5.seawar.utils.Assets;

public class GameApp extends Game {
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
    public static final String TITLE = "Sea-Wars";

	private SpriteBatch batch;
	
	@Override
	public void create () {
		new XBoxHandler();
		new InputHandler();
		Assets.getInstance().load();
		Assets.getInstance().finishLoading();
		MenuScreen.init(this);
		batch = new SpriteBatch();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        setScreen(MenuScreen.getInstance());
	}

	@Override
	public void render () {
		Inputs.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		Assets.getInstance().dispose();
	}

    public SpriteBatch getBatch() {
        return batch;
    }
}
