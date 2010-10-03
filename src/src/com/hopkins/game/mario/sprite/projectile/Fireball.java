package com.hopkins.game.mario.sprite.projectile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.movement.CollisionResponse;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;
import com.hopkins.game.mario.sprite.enemies.Enemy;

public class Fireball extends Sprite {
	public static final int FRAMES = 4;
	public static final int SIZE = 8;
	

	public Fireball(boolean direction) {
		super();
		getVelocity().x = (direction) ? -4 : 4;
		getVelocity().y = 4;
		setSize(SIZE, SIZE);
	}

	
	public String getSpriteFile() {
		return "projectiles/fireball.png";
	}
	
	public void onPreventCollision(Sprite that, Point collisionVector) {
		if (collisionVector.y != 0) {
			getVelocity().y = -1 * getVelocity().y;
		} else if (collisionVector.x != 0) {
			GameEventManager.get().remove(this);
		}
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public CollisionResponse onCollision(Sprite item, Point collisionVector) {
		if (Enemy.class.isAssignableFrom(item.getClass())) {
			GameEventManager.get().remove(item);
			GameEventManager.get().remove(this);
			return CollisionResponse.Block;
		}
		return super.onCollision(item, collisionVector);
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
