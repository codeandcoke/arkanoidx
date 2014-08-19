package org.sfaci.arkanoidx.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Clase que representa a cada uno de los ladrillos del juego
 * @author Santiago Faci
 * @version Agosto 2014
 *
 */
public class Brick extends Character {

	// Tipo de ladrillo
	public enum BrickType {
		YELLOW, BLACK, GREEN, WHITE, PURPLE, RED, BLUE, GRAY
	}
	
	public BrickType type;
	public int lives;
	public int value;
    private Item item;      // Item que puede llevar (o no) asociado este ladrillo)
	
	/**
	 * Constructor
	 * @param texture Textura del ladrillo
	 * @param x Posición x inicial
	 * @param y Posición y inicial
	 * @param type Tipo de ladrillo
	 * @param lives Duración del ladrillo (golpes)
	 * @param value Puntuación que da romper el ladrillo
	 */
	public Brick(TextureRegion texture, float x, float y, BrickType type, int lives, int value) {
		
		super(texture, x, y);
		this.type = type;
		this.lives = lives;
		this.value = value;
	}
	
	@Override
	public void update(float dt) {
		
		super.update(dt);
	}

    /**
     * Asocia un item a este ladrillo
     * @param newItem El nuevo Item
     */
    public void setItem(Item newItem) {
        item = newItem;
    }

    /**
     * Devuelve el item asociado (si lo tiene)
     * @return El item asociado o null si no tiene item
     */
    public Item getItem() {
        return item;
    }
}
