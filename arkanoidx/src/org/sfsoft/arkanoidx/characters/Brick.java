package org.sfsoft.arkanoidx.characters;

import com.badlogic.gdx.graphics.Texture;

public class Brick extends Character {

	public enum BrickType {
		YELLOW, BLACK, GREEN, WHITE, PURPLE, RED, BLUE, GRAY
	}
	
	BrickType type;
	int lives;
	int value;
	
	public Brick(Texture texture, float x, float y, BrickType type, int lives, int value) {
		
		super(texture, x, y);
		this.type = type;
		this.lives = lives;
		this.value = value;
	}
	
	@Override
	public void update(float dt) {
		
	}
}
