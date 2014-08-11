package org.sfaci.arkanoidx.managers;

import org.sfaci.arkanoidx.Arkanoidx;
import org.sfaci.arkanoidx.GameOverScreen;
import org.sfaci.arkanoidx.characters.Ball;
import org.sfaci.arkanoidx.characters.Board;
import org.sfaci.arkanoidx.characters.Brick;
import org.sfaci.arkanoidx.characters.Item;
import static org.sfaci.arkanoidx.util.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Clase que gestiona los sprites del juego
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class SpriteManager {

	public Board board;
	public Array<Ball> balls;
	public Array<Brick> bricks;
    public Array<Item> items;
    private int lives;
    private int score;
	
	SpriteBatch batch;
    Arkanoidx game;
    LevelManager levelManager;
	
	public SpriteManager(Arkanoidx game) {
		
		batch = game.spriteBatch;
        this.game = game;

        lives = LIVES;
        score = 0;

		init();
	}

    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public void init() {
        board = new Board(ResourceManager.getAtlas().findRegion("board"), 0, GROUND_LEVEL);
        bricks = new Array<Brick>();
        items = new Array<Item>();
        balls = new Array<Ball>();
        Ball ball = new Ball(ResourceManager.getAtlas().findRegion("ball"), SCREEN_WIDTH / 2, 250);
        balls.add(ball);
    }
	
	public void render() {
		
		batch.begin();
			board.render(batch);
            for (Ball ball : balls)
			    ball.render(batch);
			for (Brick brick : bricks)
				brick.render(batch);
            for (Item item : items)
                item.render(batch);
            drawHud();
		batch.end();
	}
	
	public void update(float dt) {
		
		board.update(dt);
		for (Brick brick : bricks)
			brick.update(dt);

        updateBalls(dt);
        updateItems(dt);

        /*
         Si se han eliminado todas las bola se pierde una vida
         Si quedan vidas se reinicia el nivel
         Si no quedan vidas se termina la partida
         */
        if (balls.size == 0) {
            if (lives > 1) {
                lives--;
                levelManager.restartCurrentLevel();
            }
            else {
                game.setScreen(new GameOverScreen(game));
            }
        }

        if (levelIsOver())
            levelManager.passToNextLevel();
	}

    private void drawHud() {
        // FIXME pintar el marcador de puntos, vidas, pantalla, . . .
        game.font.draw(game.spriteBatch, " " + "LEVEL  " + levelManager.currentLevel, 0, 20);
        game.font.draw(game.spriteBatch, " " + "LIVES  " + lives, 100, 20);
        game.font.draw(game.spriteBatch, "SCORE  " + score, SCREEN_WIDTH - 120, 20);
    }

    /**
     * Actualiza la lógica de la bola con el resto de elementos
     * de la pantalla
     * @param dt
     */
    public void updateBalls(float dt) {

        for (Ball ball : balls) {
            ball.update(dt);
            // Comprueba los límites de la pantalla (
            // Rebote en parte izquierda
            if (ball.position.x <= 0) {
                ball.position.x = 0;
                ball.speedX = -ball.speedX;
            }

            // Rebote en parte derecha
            if ((ball.position.x + BALL_WIDTH) >= SCREEN_WIDTH) {
                ball.position.x = SCREEN_WIDTH - BALL_WIDTH;
                ball.speedX = -ball.speedX;
            }

            // Rebote en el techo
            if ((ball.position.y + BALL_WIDTH) >= SCREEN_HEIGHT) {
                ball.position.y = SCREEN_HEIGHT - BALL_WIDTH;
                ball.speedY = -ball.speedY;
            }

            // Rebote con la tabla
            if (board.rect.overlaps(ball.rect)) {

                // Si la bola pega en el centro de la tabla se reduce el ángulo de rebote
                if ((ball.position.x > (board.position.x + board.width / 4)) && ((ball.position.x + BALL_WIDTH) < (board.position.x + board.width * 3/4))) {
                    // Si la bola pega centrada rebota hacia el sentido de donde venía (así lo hace Arkanoid original)
                    ball.speedX = BALL_SPEED / 3;

                    // La pelota cambia de sentido en x en función del lado donde da (para dirigir la bola)
                    if ((ball.speedX < 0) && (ball.position.x - BALL_WIDTH / 2 > board.position.x + board.width / 2)) {
                        ball.speedX = -ball.speedX;
                    }
                    if ((ball.speedX > 0) && (ball.position.x + BALL_WIDTH / 2 < board.position.x + board.width / 2)) {
                        ball.speedX = -ball.speedX;
                    }
                }
                // Si pega en algún borde la tabla la bola amplia el ángulo de rebote
                else {
                    if (ball.speedX > 0)
                        ball.speedX = BALL_SPEED;
                    else
                        ball.speedX = -BALL_SPEED;
                }

                // Si la tabla está en movimiento puede alterar la dirección X de la bola
                if (board.state == Board.State.LEFT) {
                    ball.speedX = -BALL_SPEED;
                }
                if (board.state == Board.State.RIGHT) {
                    ball.speedX = BALL_SPEED;
                }

                ball.position.y = board.position.y + BOARD_HEIGHT;
                ball.speedY = -ball.speedY;
            }

            // Rebote con ladrillos
            // FIXME Falta comprobar cómo hacer que rebote de lado en un ladrillo
            for (Brick brick : bricks) {
                if (brick.rect.overlaps(ball.rect)) {

                    // La bola pega desde abajo
                    if ((ball.rect.y) <= (brick.rect.y)) {
                        if (!ball.isUnbeatable()) {
                            ball.position.y = ball.rect.y = brick.rect.y - BALL_HEIGHT;
                            ball.speedY = -ball.speedY;
                        }

                    }
                    // La bola pega desde arriba
                    else if ((ball.rect.y) > (brick.rect.y)) {
                        if (!ball.isUnbeatable()) {
                            ball.speedY = -ball.speedY;
                            ball.position.y = ball.rect.y = brick.rect.y + BRICK_HEIGHT;
                        }
                    }
                    else {
                        // FIXME
                    /*// La bola pega por el lado derecho
                    if ((rect.x + BALL_WIDTH) >  (brick.rect.x + BRICK_WIDTH)) {
                        speedX = -speedX;
                        position.x = rect.x = brick.rect.x + BRICK_WIDTH;
                    }
                    // La bola pega por el lado izquierdo
                    else if (rect.x < brick.rect.x) {
                        speedX = -speedX;
                        position.x = rect.x = brick.rect.x;
                    }*/
                    }

                    brick.lives--;
                    if (brick.lives == 0) {
                        removeBrick(brick);
                        score += brick.value;
                    }
                }
            }

            // Si una bola llega al suelo se elimina
            if (ball.position.y < GROUND_LEVEL)
                balls.removeValue(ball, true);
        }
    }

    /**
     * Comprueba los impactos de los items
     * @param dt
     */
    public void updateItems(float dt) {

        for (Item item : items) {
            item.update(dt);

            if (item.rect.overlaps(board.rect)) {
                switch (item.type) {
                    case E:
                        board.changeSize(BOARD2_WIDTH);
                        break;
                    case I:
                        for (Ball ball : balls)
                            ball.setUnbeatable(true);
                        break;
                    case L:
                        board.changeSize(BOARD_WIDTH);
                        break;
                    case O:
                        Ball ball = new Ball(ResourceManager.getAtlas().findRegion("ball"), SCREEN_WIDTH / 2, 250);
                        balls.add(ball);
                        break;
                    case V:
                        // FIXME este item produce que la tabla del jugador se duplique durante un tiempo determinado
                        break;
                    default:
                        break;
                }

                items.removeValue(item, true);
            }

            if (item.position.y + ITEM_HEIGHT < GROUND_LEVEL)
                items.removeValue(item, true);
        }
    }

    public void removeBrick(Brick brick) {

        if (brick.getItem() != null) {
            items.add(brick.getItem());
        }

        bricks.removeValue(brick, true);
    }

    private boolean levelIsOver() {
        return (bricks.size == 0);
    }
}
