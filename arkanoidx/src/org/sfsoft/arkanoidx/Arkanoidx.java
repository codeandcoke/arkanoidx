package org.sfsoft.arkanoidx;

import org.sfsoft.arkanoidx.util.Constants;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase principal del proyecto principal del juego
 * 
 * @author Santiago Faci
 *
 */
public class Arkanoidx extends Game {

	public OrthographicCamera camera;
	SpriteBatch spriteBatch;
	BitmapFont fuente;
	int gotasRecogidas;
	
	/*
	 * Método invocado en el momento de crearse la aplicación
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		fuente = new BitmapFont();
		
		// Crea la cámara y define la zona de visión del juego (toda la pantalla)
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		camera.update();
		
		setScreen(new MainMenuScreen(this));
	}

	/*
	 * Método que se invoca cada vez que hay que renderizar
	 * Es el método donde se actualiza también la lógica del juego
	 * @see com.badlogic.gdx.ApplicationListener#pause()
	 */
	@Override
	public void render() {
		super.render();
	}
	
	/*
	 * Método invocado cuando se destruye la aplicación
	 * Siempre va precedido de una llamada a 'pause()'
	 * @see com.badlogic.gdx.ApplicationListener#dispose()
	 */
	@Override
	public void dispose() {
		spriteBatch.dispose();
		fuente.dispose();
	}
	
	
}
