package com.codeandcoke.arkanoidx.managers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.codeandcoke.arkanoidx.characters.Item;
import com.codeandcoke.arkanoidx.characters.Brick;
import com.codeandcoke.arkanoidx.characters.Brick.BrickType;

import static com.codeandcoke.arkanoidx.util.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Clase que gestiona los niveles del juego
 * @author Santiago Faci
 * @version Agosto 2014
 *
 */
public class LevelManager {

	public int currentLevel;
	SpriteManager spriteManager;
	
	public LevelManager(SpriteManager spriteManager) {
		
		this.spriteManager = spriteManager;
		currentLevel = 1;
	}
	
	/**
	 * Carga el nivel actual leyendo el fichero 'level' + currentLevel + '.txt'
	 */
	public void loadCurrentLevel() {
		
		FileHandle file = Gdx.files.internal("levels/level" + currentLevel + ".txt");
		String levelInfo = file.readString();
		
		int x = 0, y = SCREEN_HEIGHT - BRICK_HEIGHT;
		String[] rows = levelInfo.split("\n");
		Brick brick = null;
		for (String row : rows) {
			String[] brickIds = row.split(",");
			for (String brickId : brickIds) {
	
				if (brickId.trim().equals("x")) {
					x += BRICK_WIDTH;
					continue;
				}
	            // FIXME asignar vida y valor a los ladrillos en función del color
				brick = new Brick(getTextureBrick(brickId.trim()), x, y, BrickType.values()[Integer.valueOf(brickId.trim())], 1, 25);
                // Se calcula si este ladrillo llevará asociado un item
                if (MathUtils.random(0, 10) < 4) {
                    Item.ItemType itemType = Item.ItemType.values()[MathUtils.random(0, 4)];
                    switch (itemType) {
                        case E:
                            brick.setItem(new Item(ResourceManager.getAtlas(ATLAS_PATH).findRegion("letra_e"), x, y, Item.ItemType.E));
                            break;
                        case I:
                            brick.setItem(new Item(ResourceManager.getAtlas(ATLAS_PATH).findRegion("letra_i"), x, y, Item.ItemType.I));
                            break;
                        case L:
                            brick.setItem(new Item(ResourceManager.getAtlas(ATLAS_PATH).findRegion("letra_l"), x, y, Item.ItemType.L));
                            break;
                        case O:
                            brick.setItem(new Item(ResourceManager.getAtlas(ATLAS_PATH).findRegion("letra_o"), x, y, Item.ItemType.O));
                            break;
                        case V:
                            brick.setItem(new Item(ResourceManager.getAtlas(ATLAS_PATH).findRegion("letra_v"), x, y, Item.ItemType.V));
                            break;
                        default:
                            break;
                    }
                }

				spriteManager.bricks.add(brick);
				x += BRICK_WIDTH;
			}
			
			x = 0;
			y -= BRICK_HEIGHT;
		}
	}

    /**
     * Reinicia el nivel actual
     */
    public void restartCurrentLevel() {

        spriteManager.init();
        loadCurrentLevel();
    }

    /**
     * Carga el siguiente nivel
     */
    public void passToNextLevel() {
        currentLevel++;
        spriteManager.init();
        loadCurrentLevel();
    }
	
	/**
	 * Obtiene la textura que corresponde con el tipo de ladrillo dado
	 * @param brickId
	 * @return
	 */
	private TextureRegion getTextureBrick(String brickId) {
		
		BrickType type = BrickType.values()[Integer.valueOf(brickId)];
		
		switch (type) {
		case YELLOW:
			return ResourceManager.getAtlas(ATLAS_PATH).findRegion("yellow_brick");
		case BLACK:
			return ResourceManager.getAtlas(ATLAS_PATH).findRegion("black_brick");
		case GREEN:
			return ResourceManager.getAtlas(ATLAS_PATH).findRegion("green_brick");
		case WHITE:
			return ResourceManager.getAtlas(ATLAS_PATH).findRegion("white_brick");
		case PURPLE:
			return ResourceManager.getAtlas(ATLAS_PATH).findRegion("purple_brick");
		case RED:
			return ResourceManager.getAtlas(ATLAS_PATH).findRegion("red_brick");
		case BLUE:
			return ResourceManager.getAtlas(ATLAS_PATH).findRegion("blue_brick");
		case GRAY:
			return ResourceManager.getAtlas(ATLAS_PATH).findRegion("gray_brick");
		default:
			return null;
		}
	}
}
