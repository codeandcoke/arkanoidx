package org.sfaci.arkanoidx.managers;

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
    LevelManager levelManager;
	
	public SpriteManager(SpriteBatch batch) {
		
		this.batch = batch;
		
		board = new Board(ResourceManager.getAtlas().findRegion("board"), 0, 0, 3);
		ball = new Ball(ResourceManager.getAtlas().findRegion("ball"), Constants.SCREEN_WIDTH / 2, 250, this);
		bricks = new Array<Brick>();
	}

    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
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

        if (levelIsOver())
            levelManager.passToNextLevel();
	}

    private boolean levelIsOver() {
        return (bricks.size == 0);
    }
}
