package org.sfsoft.arkanoidx.characters;

import org.sfsoft.arkanoidx.util.Constants;

import com.badlogic.gdx.graphics.Texture;

public class Board extends Character {

	int lives;
	
	public Board(Texture texture, float x, float y, int lives) {
		super(texture, x, y);
		this.lives = lives;
	}
	
	public void move(float x) {
		
		this.x += x;
	}
	
	@Override
	public void update(float dt) {
		
		if (x <= 0)
			x = 0;
		
		if ((x + Constants.BOARD_WIDTH) >= Constants.SCREEN_WIDTH)
			x = Constants.SCREEN_WIDTH - Constants.BOARD_WIDTH;
	}
}
