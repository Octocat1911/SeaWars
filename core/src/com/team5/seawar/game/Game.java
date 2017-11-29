package com.team5.seawar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team5.seawar.screens.MenuScreen;

public class Game extends com.badlogic.gdx.Game {
	private SpriteBatch batch;

	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;
	public static final String TITLE = "Sea-Wars";

	@Override
	public void create () {
		MenuScreen.init(this);
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		setScreen(MenuScreen.getInstance());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch(){
		return batch;
	}
}
