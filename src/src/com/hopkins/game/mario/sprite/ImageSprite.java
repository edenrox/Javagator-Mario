package com.hopkins.game.mario.sprite;

import java.awt.Image;


public abstract class ImageSprite extends Sprite {
	
	public abstract String getSpriteFile();
	
	public ImageSprite() {
		getSize().set(Sprite.TileWidth, Sprite.TileHeight);
	}
	
	public Image getImage() {
		return SpriteCache.get().getSprite(getSpriteFile());
	}
}
