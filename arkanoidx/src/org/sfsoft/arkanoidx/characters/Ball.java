package org.sfsoft.arkanoidx.characters;

import com.badlogic.gdx.graphics.Texture;

/**
 * Clase que representa la bola
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Ball extends Character {

	// Al comenzar la partida la bola puede aparecer pausada
	boolean paused;
	
	public Ball(Texture texture, float x, float y) {
		super(texture, x, y);
		paused = true;
	}
	
	@Override
	public void update(float dt) {
		
		if (paused)
			return;
	}
}
