package org.sfaci.arkanoidx;

import org.sfaci.arkanoidx.characters.Ball;
import org.sfaci.arkanoidx.characters.Board;
import org.sfaci.arkanoidx.managers.LevelManager;
import org.sfaci.arkanoidx.managers.ResourceManager;
import org.sfaci.arkanoidx.managers.SpriteManager;
import org.sfaci.arkanoidx.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;

/**
 * Pantalla del game, donde el usuario juega la partida
 * @author Santiago Faci
 *
 */
public class GameScreen implements Screen, InputProcessor {

	final Arkanoidx game;
	
	// Indica si el game está en pausa
	boolean paused = false;
	
	LevelManager levelManager;
	SpriteManager spriteManager;
	
	public GameScreen(Arkanoidx game) {
		this.game = game;
			
		ResourceManager.loadAllResources();
		
		spriteManager = new SpriteManager(game);
		levelManager = new LevelManager(spriteManager);
        levelManager.loadCurrentLevel();
        spriteManager.setLevelManager(levelManager);
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void show() {
	}
	
	@Override
	public void render(float dt) {
		
		// Pinta el fondo de la pantalla de azul oscuro (RGB + alpha)
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		// Limpia la pantalla
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Actualiza la cámara
		game.camera.update();
		
		/* Comprueba la entrada del usuario, actualiza
		 * la posición de los elementos del game y
		 * dibuja en pantalla
		 */
		if (!paused) {
			handleInput(dt);
			spriteManager.update(dt);
		}
		
		spriteManager.render();
	}
	
	/*
	 * Comprueba la entrada del usuario (teclado o pantalla si está en el móvil)
	 */
	private void handleInput(float dt) {
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			spriteManager.board.move(Constants.BOARD_SPEED * dt);
			spriteManager.board.state = Board.State.RIGHT;
		}
		else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			spriteManager.board.move(-Constants.BOARD_SPEED * dt);
			spriteManager.board.state = Board.State.LEFT;
		}
		else {
			spriteManager.board.state = Board.State.IDLE;
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            for (Ball ball : spriteManager.balls)
			    ball.setPaused(false);
		}
	}

	/*
	 * Método que se invoca cuando esta pantalla
	 * deja de ser la principal
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void resize(int width, int height) {
		game.camera.viewportWidth = width;
		game.camera.viewportHeight = height;
		game.camera.update();
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		// Pone el game en pausa
		if (keycode == Keys.P)
			paused = !paused;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
