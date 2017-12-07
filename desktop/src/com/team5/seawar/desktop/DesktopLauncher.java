package com.team5.seawar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.team5.seawar.game.GameApp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameApp.WIDTH;
		config.height = GameApp.HEIGHT;
		config.title = GameApp.TITLE;
		new LwjglApplication(new GameApp(), config);
	}
}
