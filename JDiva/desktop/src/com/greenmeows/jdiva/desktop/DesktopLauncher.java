package com.greenmeows.jdiva.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.greenmeows.jdiva.Game;
import com.greenmeows.jdiva.constants.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;
		config.resizable = false;
		config.title = Constants.TITLE;
		new LwjglApplication(new Game(), config);
	}
}
