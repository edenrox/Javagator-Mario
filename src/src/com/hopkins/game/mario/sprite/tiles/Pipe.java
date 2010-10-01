package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;

import com.hopkins.game.mario.sprite.ImageSprite;
import com.hopkins.game.mario.sprite.Sprite;

public class Pipe extends ImageSprite {
	
	private int m_height;
	private Image m_image;

	public Pipe(int height) {
		super();
		m_height = height;
		this.getSize().set(TileWidth * 2, TileHeight * (m_height + 1));
	}
	
	public String getSpriteFile() {
		return "tiles/pipe.png";
	}
	
	public boolean isGravityEffected() {
		return false;
	}
	
	public boolean isSolid() {
		return true;
	}

	public Image getImage() {
		if (m_image == null) { 
			generateImage();
		}
		return m_image;
	}
	
	private void generateImage() {
		Image img = super.getImage();
		BufferedImage bi = new BufferedImage(2 * Sprite.TileWidth, m_height * Sprite.TileHeight, IndexColorModel.TRANSLUCENT);
		Graphics g = bi.createGraphics();
		
		// top of the pipe
		g.drawImage(img, 0, 0, 2 * Sprite.TileWidth, 1 * Sprite.TileHeight, 0, 0, 2 * Sprite.TileWidth, 1 * Sprite.TileHeight, null);
	
		// bottom of the pipe
		for (int i = 0; i < m_height - 1; i++) {
			g.drawImage(img, 0, (i +1) * Sprite.TileHeight, 2 * Sprite.TileWidth, (i +2) * Sprite.TileHeight, 0, Sprite.TileHeight, 2 * Sprite.TileWidth, 2 * Sprite.TileHeight, null);  
		}
		
		m_image = bi;
	}
}
