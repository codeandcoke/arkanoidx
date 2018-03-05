package com.codeandcoke.arkanoidx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.codeandcoke.arkanoidx.Arkanoidx;
import com.codeandcoke.arkanoidx.util.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Arkanoidx";

		config.width = Constants.SCREEN_WIDTH;
		config.height = Constants.SCREEN_HEIGHT;

		new LwjglApplication(new Arkanoidx(), config);
	}
}
