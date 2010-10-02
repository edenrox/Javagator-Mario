package com.hopkins.game.mario.sprite.enemies;

import java.awt.Point;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;

public abstract class Enemy extends Sprite {

	public void onPreventCollision(Sprite that, Point collisionVector) {
		if (collisionVector.x != 0) {
			getVelocity().x = -1 * getVelocity().x;
		}
	}
	
	public boolean checkStomp(Sprite that, Point collisionVector) {
		if (that.getClass() == Player.class) {
			if (collisionVector.y > 0) {
				return true;
			}
		}
		return false;
	}
}
