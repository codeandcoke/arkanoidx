package org.sfaci.arkanoidx;

import org.sfaci.arkanoidx.util.Constants;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Clase principal de la versi�n de escritorio (PC) del game
 * @author Santiago Faci
 *
 */
public class DesktopArkanoidx {

	public static void main(String[] args) {
		LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
		configuration.title = "Arkanoidx";

		configuration.width = Constants.SCREEN_WIDTH;
		configuration.height = Constants.SCREEN_HEIGHT;
				
		new LwjglApplication(new Arkanoidx(), configuration);
	}
}
