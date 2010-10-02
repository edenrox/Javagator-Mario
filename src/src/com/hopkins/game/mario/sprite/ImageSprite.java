package com.hopkins.game.mario.sprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public abstract class ImageSprite extends Sprite {
	
	public abstract String getSpriteFile();
	
	public Image getImage() {
		return SpriteCache.get().getSprite(getSpriteFile());
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		g.drawImage(getImage(), p.x, p.y, null);
	}
}
