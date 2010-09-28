package com.hopkins.game.mario.sprite.projectile;

import com.hopkins.game.mario.sprite.Sprite;

public class Hammer extends Sprite {

	public boolean onCollision() {
		return false;
	}
	public boolean isSolid() {
		return false;
	}
}
