package org.sfaci.arkanoidx.managers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Clase que gestiona los recursos del juego
 * @author Santiago Faci
 * @version Agosto 2014
 *
 */
public class ResourceManager {

    /* Atlas de texturas con todos los elementos del juego */
    private static TextureAtlas atlas = new TextureAtlas();
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();

    public static AssetManager assets = new AssetManager();
	
	/**
	 * Carga en memoria todos los recursos del juego (texturas y sonidos)
	 */
	public static void loadAllResources() {
		
		Texture.setEnforcePotImages(false);

        assets.load("pictures/arkanoidx.pack", TextureAtlas.class);
        assets.load("sounds/bump.wav", Sound.class);
	}

    public static TextureAtlas getAtlas(String name) {
        return assets.get(name, TextureAtlas.class);
    }
	
	/**
	 * Obtiene un recurso de sonido de memoria
	 * @param name
	 * @return
	 */
	public static Sound getSound(String name) {
		
		return assets.get(name, Sound.class);
	}

    public static boolean update() {
        return assets.update();
    }
}
