package com.hopkins.game.mario.sprite.projectile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;

public class BrickBit extends Sprite {
	public static final int FRAMES = 3;
	public static final int WIDTH = 9;
	public static final int HEIGHT = 8;
	public static final int MAX_VELOCITY = 20;
	public static final int VELOCITY_INCREMENT = 4;

	public BrickBit(int x, int y, int vx, int vy) {
		setLocation(x, y);
		setSize(WIDTH, HEIGHT);
		getVelocity().x = vx;
		getVelocity().y = vy;
	}
	
	public boolean respectsSolids() {
		return false;
	}
	
	public boolean isGravityEffected() {
		return false;
	}
	public boolean isSolid() {
		return false;
	}
	
	public String getSpriteFile() {
		return "projectiles/brickbit.png";
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		Image fire = SpriteCache.get().getSprite(getSpriteFile());
		
		if (getVelocity().y < MAX_VELOCITY) {
			getVelocity().y += VELOCITY_INCREMENT;
		}
		
		int frame = (tick % FRAMES);
		int x = frame * WIDTH;
		int y = 0;
		
		g.drawImage(fire, p.x, p.y, p.x+WIDTH, p.y+HEIGHT, 
						x, y, x+WIDTH, y+HEIGHT, null);
		
	}
}
