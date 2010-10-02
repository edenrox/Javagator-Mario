package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics2D;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.sprite.Sprite;

public class PitDeath extends Tile {

	public String getSpriteFile() {
		// we don't need to render anything
		return null;
	}
	
	public boolean isSolid() {
		return false;
	}
	public GameEventType onCollision(Sprite that, Point collisionVector) {
		return GameEventType.Death;
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		// noop
	}

	
}
