package com.codeandcoke.arkanoidx.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.codeandcoke.arkanoidx.Arkanoidx;
import com.codeandcoke.arkanoidx.util.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Arkanoidx");
		config.setWindowSizeLimits(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

		new Lwjgl3Application(new Arkanoidx(), config);
	}
}
