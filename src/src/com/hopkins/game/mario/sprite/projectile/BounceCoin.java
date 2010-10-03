package com.hopkins.game.mario.sprite.projectile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.movement.CollisionResponse;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;

public class BounceCoin extends Sprite {
	public static final int FRAMES = 6;
	public static final int DURATION = 6;
	
	private int m_start = -1;
	
	public BounceCoin() {
		getVelocity().y = -4;
	}
	
	public CollisionResponse onCollision(Sprite that, Point collisionVector) {
		return CollisionResponse.Overlap;
	}
	public boolean isGravityEffected() {
		return false;
	}
	public boolean isSolid() {
		return false;
	}
	public String getSpriteFile() {
		return "tiles/coin.png";
	}
	public void render(Graphics2D g, Point p, int tick) {
		if (m_start == -1) {
			m_start = tick;
		} else if (tick > m_start + DURATION) {
			GameEventManager.get().remove(this);
		}
		
		Image img = SpriteCache.get().getSprite(getSpriteFile());
		
		int frame = ((tick + m_start) % FRAMES);
		int x = frame * TILE_WIDTH;
		int y = 0;
		
		g.drawImage(img, p.x, p.y, p.x+TILE_WIDTH, p.y+TILE_HEIGHT, 
						x, y, x+TILE_WIDTH, y+TILE_HEIGHT, null);
		
	}

}
