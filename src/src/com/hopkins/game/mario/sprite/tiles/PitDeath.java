package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics2D;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.movement.CollisionResponse;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;

public class PitDeath extends Tile {

	public String getSpriteFile() {
		// we don't need to render anything
		return null;
	}
	
	public boolean isSolid() {
		return false;
	}
	public CollisionResponse onCollision(Sprite that, Point collisionVector) {
		if (that.getClass() == Player.class) {
			GameEventManager.get().fireEvent(GameEventType.Death, that);
		}
		return CollisionResponse.Overlap;
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		// noop
	}

	
}
