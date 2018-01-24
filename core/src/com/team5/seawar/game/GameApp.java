package com.team5.seawar.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team5.seawar.inputHandler.InputHandler;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.inputHandler.XBoxHandler;
import com.team5.seawar.maps.Map;
import com.team5.seawar.maps.Map1;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.MapSelectScreen;
import com.team5.seawar.screens.MenuScreen;
import com.team5.seawar.screens.StartScreen;
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
		MapSelectScreen.init(this, new Map1());
		//MenuScreen.init(this);
		batch = new SpriteBatch();
        Gdx.gl.glClearColor(0, 0, 0, 1);
       	//setScreen(new MapEditorScreen(this,new Map(7,6)));
		//setScreen(MenuScreen.getInstance());
		setScreen(new StartScreen(this));
		//setScreen(MapSelectScreen.getInstance());
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
