package com.hopkins.game.mario.sprite.projectile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;

public class Hammer extends Sprite {
	public static final int FRAMES = 8;

	public String getSpriteFile() {
		return "projectiles/hammer.png";
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		Image fire = SpriteCache.get().getSprite(getSpriteFile());
		
		int frame = (tick % FRAMES);
		int x = frame * TILE_WIDTH;
		int y = 0;
		
		g.drawImage(fire, p.x, p.y, p.x+TILE_WIDTH, p.y+TILE_HEIGHT, 
						x, y, x+TILE_WIDTH, y+TILE_HEIGHT, null);
		
	}
}
