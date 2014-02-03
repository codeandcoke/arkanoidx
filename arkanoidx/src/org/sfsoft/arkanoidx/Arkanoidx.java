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
	
	/*
	 * Método invocado en el momento de crearse la aplicación
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		fuente = new BitmapFont();
		
		// Crea la c�mara y define la zona de visi�n del juego (toda la pantalla)
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		camera.update();
		
		setScreen(new MainMenuScreen(this));
	}

	/*
	 * M�todo que se invoca cada vez que hay que renderizar
	 * Es el m�todo donde se actualiza tambi�n la l�gica del juego
	 * @see com.badlogic.gdx.ApplicationListener#pause()
	 */
	@Override
	public void render() {
		super.render();
	}
	
	/*
	 * M�todo invocado cuando se destruye la aplicaci�n
	 * Siempre va precedido de una llamada a 'pause()'
	 * @see com.badlogic.gdx.ApplicationListener#dispose()
	 */
	@Override
	public void dispose() {
		spriteBatch.dispose();
		fuente.dispose();
	}
	
	
}
