package org.sfaci.arkanoidx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.sfaci.arkanoidx.Arkanoidx;

/**
 * Pantalla de inicio
 * Se presenta el menú de game
 * @author Santiago Faci
 * @version Agosto 2014
 */
public class MainMenuScreen implements Screen {
	
	final Arkanoidx game;

    private Stage stage;
	
	public MainMenuScreen(Arkanoidx game) {
		this.game = game;
	}

	@Override
	public void render(float dt) {

        // Limpia la pantalla
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act();
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
        stage.setViewport(width, height);
	}

	@Override
	public void show() {
        stage = new Stage();

        Table table = new Table(game.getSkin());
        table.setFillParent(true);
        table.center();

        Label title = new Label("ARKANOIDX\nMAIN MENU", game.getSkin());
        title.setFontScale(2.5f);

        TextButton playButton = new TextButton("PLAY GAME", game.getSkin());
        playButton.addListener(new ClickListener() {
           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
               dispose();
               game.setScreen(new GameScreen(game));
           }
        });
        TextButton optionsButton = new TextButton("OPTIONS", game.getSkin());
        optionsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                // FIXME ¿Menú de opciones?

            }
        });
        TextButton exitButton = new TextButton("QUIT GAME", game.getSkin());
        exitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                System.exit(0);
            }
        });

        Label aboutLabel = new Label("Arkanoidx v0.1\n(c) Santiago Faci\nhttp://bitbucket.org/sfaci/arkanoidx", game.getSkin());
        aboutLabel.setFontScale(1f);

        table.row().height(275);
        table.add(title).center().pad(35f);
        table.row().height(75);
        table.add(playButton).center().width(500).pad(5f);
        table.row().height(75);
        table.add(optionsButton).center().width(500).pad(5f);
        table.row().height(75);
        table.add(exitButton).center().width(500).pad(5f);
        table.row().height(75);
        table.add(aboutLabel).center().pad(55f);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
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
	}
}