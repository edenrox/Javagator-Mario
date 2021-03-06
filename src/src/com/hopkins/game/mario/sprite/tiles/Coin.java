package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.movement.CollisionResponse;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;
import com.hopkins.game.mario.sprite.player.Player;

public class Coin extends Tile {
	public static final int FRAMES = 6;

	public CollisionResponse onCollision(Sprite that, Point collisionVector) {
		if (that.getClass() == Player.class) {
			GameEventManager.get().fireEvent(GameEventType.Collect, this);
		}
		return CollisionResponse.Overlap;
	}

	public String getSpriteFile() {
		return "tiles/coin.png";
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
