package com.team5.seawar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team5.seawar.maps.Map1;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Assets;

public class Game extends com.badlogic.gdx.Game {
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
    public static final String TITLE = "Sea-Wars";

	private SpriteBatch batch;
	
	@Override
	public void create () {
		Assets.getInstance().load();
		Assets.getInstance().finishLoading();
		batch = new SpriteBatch();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        setScreen(new PlayScreen(this, new Map1()));
	}

	@Override
	public void render () {
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
