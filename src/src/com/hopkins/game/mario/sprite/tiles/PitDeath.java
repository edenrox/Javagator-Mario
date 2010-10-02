package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics2D;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventType;
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
	public GameEventType onCollision(Sprite that, Point collisionVector) {
		if (that.getClass() == Player.class) {
			return GameEventType.Death;
		} else {
			return GameEventType.NoEvent;
		}
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		// noop
	}

	
}
