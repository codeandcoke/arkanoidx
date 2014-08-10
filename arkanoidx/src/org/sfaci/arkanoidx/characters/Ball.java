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
	boolean paused;
	float speedX;
	float speedY;
	
	private SpriteManager spriteManager;
	
	public Ball(TextureRegion texture, float x, float y, SpriteManager spriteManager) {
		super(texture, x, y);
		speedX = BALL_SPEED;
		speedY = BALL_SPEED;
		paused = true;
		
		this.spriteManager = spriteManager;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
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
		
		// Comprueba los límites de la pantalla (
		// Rebote en parte izquierda
		if (position.x <= 0) {
			position.x = 0;
			speedX = -speedX;
		}
			
		// Rebote en parte derecha
		if ((position.x + BALL_WIDTH) >= SCREEN_WIDTH) {
			position.x = SCREEN_WIDTH - BALL_WIDTH;
			speedX = -speedX;
		}
		
		// Rebote en el techo
		if ((position.y + BALL_WIDTH) >= SCREEN_HEIGHT) {
			position.y = SCREEN_HEIGHT - BALL_WIDTH;
			speedY = -speedY;
		}

		// Rebote con la tabla
		Board board = spriteManager.board; 
		if (board.rect.overlaps(rect)) {
			
			// Si la tabla está en movimiento puede alterar la dirección X de la bola
			if (board.state == Board.State.LEFT) {
				speedX = -BALL_SPEED;
			}
			if (board.state == Board.State.RIGHT) {
				speedX = BALL_SPEED;
			}
			
			position.y = spriteManager.board.position.y + BOARD_HEIGHT;
			speedY = -speedY;
		}
		
		// Rebote con ladrillos
		// FIXME Falta comprobar cómo hacer que rebote de lado en un ladrillo
		for (Brick brick : spriteManager.bricks) {
			if (brick.rect.overlaps(rect)) {
				
				// La bola pega desde abajo
				if ((rect.y + BALL_WIDTH) <= (brick.rect.y)) {
					speedY = -speedY;
					position.y = rect.y = brick.position.y - BALL_WIDTH;
					
				}
				// La bola pega desde arriba
				else if ((rect.y > (brick.rect.y + BRICK_HEIGHT))) {
					position.y = rect.y = brick.position.y + BRICK_HEIGHT;
					speedY = -speedY;	
				}
                // La bola pega de lado
                else {

                }

				brick.lives--;
				if (brick.lives == 0)
					spriteManager.bricks.removeValue(brick, true);
			}
		}
	}
}
