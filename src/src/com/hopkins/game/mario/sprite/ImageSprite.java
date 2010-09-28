package com.hopkins.game.mario.sprite;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;


public abstract class ImageSprite extends Sprite {
	
	private String m_file;
	private Image m_image;
	
	public abstract String getSpriteFile();
	
	public Image getImage() {
		return SpriteCache.get().getSprite(getSpriteFile());
	}
}
