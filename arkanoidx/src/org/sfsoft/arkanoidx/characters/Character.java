package org.sfsoft.arkanoidx.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Character {

	Texture texture;
	float x;
	float y;
	
	public Character(Texture texture, float x, float y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
	}
	
	public void render(SpriteBatch batch) {
		
		batch.draw(texture, x, y);
	}
	
	public abstract void update(float dt);
}
