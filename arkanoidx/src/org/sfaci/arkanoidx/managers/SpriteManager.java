package org.sfaci.arkanoidx.managers;

import org.sfaci.arkanoidx.Arkanoidx;
import org.sfaci.arkanoidx.GameOverScreen;
import org.sfaci.arkanoidx.characters.Ball;
import org.sfaci.arkanoidx.characters.Board;
import org.sfaci.arkanoidx.characters.Brick;
import org.sfaci.arkanoidx.util.Constants;

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
	public Ball ball;
	public Array<Brick> bricks;
	
	SpriteBatch batch;
    Arkanoidx game;
    LevelManager levelManager;
	
	public SpriteManager(Arkanoidx game) {
		
		batch = game.spriteBatch;
        this.game = game;
		
		init();
	}

    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    public void init() {
        board = new Board(ResourceManager.getAtlas().findRegion("board"), 0, 0, 3);
        ball = new Ball(ResourceManager.getAtlas().findRegion("ball"), Constants.SCREEN_WIDTH / 2, 250, this);
        bricks = new Array<Brick>();
    }
	
	public void render() {
		
		batch.begin();
			board.render(batch);
			ball.render(batch);
			for (Brick brick : bricks)
				brick.render(batch);
		batch.end();
	}
	
	public void update(float dt) {
		
		board.update(dt);
		ball.update(dt);
		for (Brick brick : bricks)
			brick.update(dt);

        /*
         La pelota llega al suelo.
         Si quedan vidas se reinicia el nivel
         Si no quedan vidas se termina la partida
         */
        if (ball.position.y < 0) {
            if (board.lives > 0) {
                board.lives--;
                levelManager.restartCurrentLevel();
            }
            else {
                game.setScreen(new GameOverScreen(game));
            }
        }

        if (levelIsOver())
            levelManager.passToNextLevel();
	}

    private boolean levelIsOver() {
        return (bricks.size == 0);
    }
}
