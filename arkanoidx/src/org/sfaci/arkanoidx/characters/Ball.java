package org.sfaci.arkanoidx.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.sfaci.arkanoidx.managers.SpriteManager;

import static org.sfaci.arkanoidx.util.Constants.*;

/**
 * Clase que representa la bola
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Ball extends Character {

	// Al comenzar la partida la bola puede aparecer pausada
	private boolean paused;
	public float speedX;
	public float speedY;
    public boolean unbeatable;
	
	public Ball(TextureRegion texture, float x, float y) {
		super(texture, x, y);
		speedX = BALL_SPEED;
		speedY = BALL_SPEED;
		paused = true;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

    public void setUnbeatable(boolean unbeatable) {
        this.unbeatable = unbeatable;
    }

    public boolean isUnbeatable() {
        return unbeatable;
    }
	
	@Override
	public void update(float dt) {

        // Si el juego está en pause la pelota no hace nada (se quedará parada)
		if (paused)
			return;
		
		// Actualiza posición de la bola
		position.x += speedX * dt;
		position.y += speedY * dt;

        super.update(dt);
	}
}
