package org.sfaci.arkanoidx.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.sfaci.arkanoidx.util.Constants;

/**
 * Clase que representa la tabla del jugador
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Board extends Character {

	public int lives;
	public enum State {
		RIGHT, LEFT, IDLE;
	}
	public State state;
	
	public Board(TextureRegion texture, float x, float y, int lives) {
		super(texture, x, y);
		this.lives = lives;
		state = State.IDLE;
	}
	
	// Desplaza la tabla en el eje x
	public void move(float x) {
		
		position.x += x;
	}
	
	@Override
	public void update(float dt) {
		
		super.update(dt);
		
		// Comprueba los l�mites de la pantalla
		if (position.x <= 0)
			position.x = 0;
		
		if ((position.x + Constants.BOARD_WIDTH) >= Constants.SCREEN_WIDTH)
			position.x = Constants.SCREEN_WIDTH - Constants.BOARD_WIDTH;
	}
}
