package com.codeandcoke.arkanoidx.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.codeandcoke.arkanoidx.managers.ResourceManager;

import java.lang.*;

import static com.codeandcoke.arkanoidx.util.Constants.*;

/**
 * Clase que representa la tabla del jugador
 * @author Santiago Faci
 * @version Agosto 2014
 *
 */
public class Board extends Character {

	public enum State {
		RIGHT, LEFT, IDLE;
	}
	public State state;
    public int width;
	
	public Board(TextureRegion texture, float x, float y) {
		super(texture, x, y);
		state = State.IDLE;

        width = BOARD_WIDTH;
	}

    /**
     * Cambia el tamaño de la tabla
     * Actualmente se trabaja con 2 tamaños
     */
    public void changeSize(int newWidth) {

        if (newWidth == BOARD_WIDTH)
            texture = ResourceManager.getAtlas(ATLAS_PATH).findRegion("board");
        else
            texture = ResourceManager.getAtlas(ATLAS_PATH).findRegion("board2");

        rect = new Rectangle(position.x, position.y, texture.getRegionWidth(), texture.getRegionHeight());
        width = newWidth;
    }
	
	// Desplaza la tabla en el eje x
	public void move(float x) {
		
		position.x += x;
	}
	
	@Override
	public void update(float dt) {
		
		super.update(dt);
		
		// Comprueba los límites de la pantalla
		if (position.x <= 0)
			position.x = 0;
		
		if ((position.x + width) >= SCREEN_WIDTH)
			position.x = SCREEN_WIDTH - width;
	}
}
