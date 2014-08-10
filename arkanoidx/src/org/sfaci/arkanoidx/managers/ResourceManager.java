package org.sfaci.arkanoidx.managers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Clase que gestiona los recursos del juego
 * @author Santiago Faci
 *
 */
public class ResourceManager {

    /* Atlas de texturas con todos los elementos del juego */
    private static TextureAtlas atlas = new TextureAtlas();
	private static Map<String, Sound> sounds = new HashMap<String, Sound>();
	
	/**
	 * Carga en memoria todos los recursos del juego (texturas y sonidos)
	 */
	public static void loadAllResources() {
		
		Texture.setEnforcePotImages(false);

        atlas = new TextureAtlas(Gdx.files.internal("pictures/arkanoidx.pack"));
	}

    public static TextureAtlas getAtlas() {
        return atlas;
    }
	
	/**
	 * Obtiene un recurso de sonido de memoria
	 * @param name
	 * @return
	 */
	public static Sound getSound(String name) {
		
		return sounds.get(name);
	}
}
