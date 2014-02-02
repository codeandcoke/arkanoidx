package org.sfsoft.arkanoidx.characters;

import com.badlogic.gdx.graphics.Texture;

public class Ball extends Character {

	boolean paused;
	
	public Ball(Texture texture, float x, float y) {
		super(texture, x, y);
		paused = true;
	}
	
	@Override
	public void update(float dt) {
		
		if (paused)
			return;
		
		
	}
}
