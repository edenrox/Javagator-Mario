package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;

public abstract class Tile extends Sprite {
	
	public boolean isGravityEffected() {
		return false;
	}
	public boolean isSolid() {
		return true;
	}
	public abstract String getSpriteFile();

	public Image getImage() {
		return SpriteCache.get().getSprite(getSpriteFile());
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		g.drawImage(getImage(), p.x, p.y, null);
	}
}
