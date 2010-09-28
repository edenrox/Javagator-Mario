package com.hopkins.game.mario.sprite;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteCache {
	
	public static final String PathToImages = "C:\\Users\\Ian\\projects\\Icadev\\SideScroller\\images\\";
	private static SpriteCache s_instance;
	
	public static SpriteCache get() {
		if (s_instance == null) {
			s_instance = new SpriteCache();
		}
		return s_instance;
	}
	
	private HashMap<String, Image> m_sprites;
	
	private SpriteCache() {
		m_sprites = new HashMap<String, Image>();
	}

	public void saveSprite(String path, Image img) {
		m_sprites.put(path, img);
	}
	
	public Image getSprite(String path) {
		if (!m_sprites.containsKey(path)) {
			try {
				Image img = ImageIO.read(new File(PathToImages + path));
				saveSprite(path, img);
			} catch (IOException ex) {
				System.err.println("Error loading sprite: " + path + "\n");
				System.err.println(ex.toString());
			}
		}
		return m_sprites.get(path);
	}
}
