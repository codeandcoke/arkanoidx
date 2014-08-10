package org.sfaci.arkanoidx.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Clase base para todos los caracteres del juego
 * @author Santiago Faci
 * @version 1.0
 *
 */
public abstract class Character {

	TextureRegion texture;
    public Vector2 position;
	Rectangle rect;
	
	public Character(TextureRegion texture, float x, float y) {
		this.texture = texture;
        position = new Vector2(x, y);
		rect = new Rectangle(x, y, texture.getRegionWidth(), texture.getRegionHeight());
	}
	
	public void render(SpriteBatch batch) {
		
		batch.draw(texture, position.x, position.y);
	}
	
	public void update(float dt) {
		
		// Actualiza la posición del rectángulo
		rect.x = position.x;
		rect.y = position.y;
	};
}
