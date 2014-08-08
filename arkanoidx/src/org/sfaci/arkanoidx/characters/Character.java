package org.sfaci.arkanoidx.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Clase base para todos los caracteres del juego
 * @author Santiago Faci
 * @version 1.0
 *
 */
public abstract class Character {

	TextureRegion texture;
	float x;
	float y;
	Rectangle rect;
	
	public Character(TextureRegion texture, float x, float y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		rect = new Rectangle(x, y, texture.getRegionWidth(), texture.getRegionHeight());
	}
	
	public void render(SpriteBatch batch) {
		
		batch.draw(texture, x, y);
	}
	
	public void update(float dt) {
		
		// Actualiza la posición del rectángulo
		rect.x = x;
		rect.y = y;
	};
}
