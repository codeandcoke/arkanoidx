package org.sfaci.arkanoidx.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Representa los items que se desprenden aleatoriamente de los ladrillos
 * que rompe el jugador
 *
 * @author Santiago Faci
 * @version 1.0
 */
public class Item extends Character {

    /**
     * E: tabla más grande
     * I: bola invencible
     * L: tabla más pequeña
     * O: Añade una bola más
     * V: tabla doble
     */
    public enum ItemType {
        E, I, L, O, V
    }

    // Velocidad del item al caer
    private float speed = 200f;
    public ItemType type;


    public Item(TextureRegion texture, float x, float y, ItemType type) {
        super(texture, x, y);

        this.type = type;
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        position.y -= speed * dt;
    }
}
