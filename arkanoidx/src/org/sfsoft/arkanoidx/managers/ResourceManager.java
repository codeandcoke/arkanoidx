package org.sfsoft.arkanoidx.managers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ResourceManager {

	private static Map<String, Texture> textures = new HashMap<String, Texture>();
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
	
	public static void loadAllResources() {
		
		Texture.setEnforcePotImages(false);
		// Imágenes
		ResourceManager.loadResource("ball", new Texture("player/ball.png"));
		ResourceManager.loadResource("board", new Texture("player/board.png"));
		ResourceManager.loadResource("yellow_brick", new Texture("bricks/yellow_brick.png"));
		ResourceManager.loadResource("green_brick", new Texture("bricks/green_brick.png"));
		ResourceManager.loadResource("gray_brick", new Texture("bricks/gray_brick.png"));
		ResourceManager.loadResource("purple_brick", new Texture("bricks/purple_brick.png"));
		ResourceManager.loadResource("red_brick", new Texture("bricks/red_brick.png"));
		ResourceManager.loadResource("blue_brick", new Texture("bricks/blue_brick.png"));
		ResourceManager.loadResource("black_brick", new Texture("bricks/black_brick.png"));
		ResourceManager.loadResource("white_brick", new Texture("bricks/white_brick.png"));
	}
	
	private static void loadResource(String name, Texture texture) {
		
		textures.put(name, texture);
	}
	
	public static Texture getTexture(String name) {
		
		return textures.get(name);
	}
	
	public static Sound getSound(String name) {
		
		return sounds.get(name);
	}
}
