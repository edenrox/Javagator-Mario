package com.hopkins.game.mario.sprite.projectile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;

public class Fireball extends Sprite {
	public static final int FRAMES = 4;
	public static final int SIZE = 8;

	public Fireball() {
		
	}

	
	public String getSpriteFile() {
		return "projectiles/fireball.png";
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		Image fire = SpriteCache.get().getSprite(getSpriteFile());
		
		int frame = (tick % FRAMES);
		int x = frame * SIZE;
		int y = 0;
		
		g.drawImage(fire, p.x, p.y, p.x+SIZE, p.y+SIZE, 
						x, y, x+SIZE, y+SIZE, null);
		
	}
	
	
}
