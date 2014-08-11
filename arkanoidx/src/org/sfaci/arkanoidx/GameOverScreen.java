package org.sfaci.arkanoidx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Pantalla de fin de partida
 * @author Santiago Faci
 * @since 1.0
 *
 */
public class GameOverScreen implements Screen {
	
	final Arkanoidx game;
	OrthographicCamera camera;
    Stage stage;
	
	public GameOverScreen(Arkanoidx game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
	}

	@Override
	public void show() {
        stage = new Stage();

        Table table = new Table(game.getSkin());
        table.setFillParent(true);
        table.center();

        Label title = new Label("GAME OVER!!", game.getSkin());
        title.setFontScale(2.5f);

        TextButton playButton = new TextButton("RETURN TO MAIN MENU", game.getSkin());
        playButton.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });
        TextButton exitButton = new TextButton("QUIT GAME", game.getSkin());
        exitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                System.exit(0);
            }
        });

        table.add(title).center().pad(25f);
        table.row().height(75);
        table.add(playButton).center().width(500).pad(5f);
        table.row().height(75);
        table.add(exitButton).center().width(500).pad(5f);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
	}

    @Override
    public void render(float dt) {

       /*
       No se limpia la pantalla, por lo que se verá de fondo como ha
       quedado la partida del jugador justo antes de finalizar
        */

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		game.dispose();
	}
}
