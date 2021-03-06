package com.hopkins.game.mario.sprite.enemies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.movement.CollisionResponse;
import com.hopkins.game.mario.movement.MovementManager;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;
import com.hopkins.game.mario.sprite.player.Player;

public class Goomba extends Enemy {
	public static final int FRAMES = 2;
	
	public Goomba() {
		super();
		getVelocity().x = -1;
	}
	public int getRenderIndex() {
		return 2;
	}
	public boolean isGravityEffected() {
		return true;
	}
	public String getSpriteFile() {
		return "enemies/goomba.png";
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		
		Image fire = SpriteCache.get().getSprite(getSpriteFile());
		int frame = (tick / 2 % FRAMES);
		int x = frame * TILE_WIDTH;
		int y = 0;
		int height = TILE_HEIGHT;
		
		g.drawImage(fire, p.x, p.y+1, p.x+TILE_WIDTH, p.y+height, 
						x, y, x+TILE_WIDTH, y+TILE_HEIGHT, null);
		
	}
	
	public CollisionResponse onCollision(Sprite that, Point collisionVector) {
		if (checkStomp(that, collisionVector)) {
			GameEventManager.get().fireEvent(GameEventType.StompBadGuy, this);
			GameEventManager.get().fireEvent(GameEventType.RemoveSprite, this);
		} else if (that.getClass() == Player.class) {
			GameEventManager.get().fireEvent(GameEventType.Injure, this);
		}
		return CollisionResponse.Overlap;
	}
}
