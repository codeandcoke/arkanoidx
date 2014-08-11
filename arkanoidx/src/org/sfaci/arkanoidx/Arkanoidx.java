package org.sfaci.arkanoidx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.sfaci.arkanoidx.util.Constants;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase principal del proyecto principal del game
 * 
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Arkanoidx extends Game {

	public OrthographicCamera camera;
	public SpriteBatch spriteBatch;
	public BitmapFont font;
    Skin skin;
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		
		// Crea la cámara y define la zona de visión del game (toda la pantalla)
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		camera.update();
		
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
		font.dispose();
	}

    public Skin getSkin() {
        if (skin == null)
            skin = new Skin(Gdx.files.internal("uiskin.json"));

        return skin;
    }
}
