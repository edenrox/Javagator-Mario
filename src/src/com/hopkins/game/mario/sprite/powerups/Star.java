package com.hopkins.game.mario.sprite.powerups;

import java.awt.Point;
import com.hopkins.game.mario.sprite.Sprite;

public class Star extends Collectable {

	public Star() {
		getVelocity().x = 4;
		getVelocity().y = 4;
	}
	
	public String getSpriteFile() {
		return "powerups/star.png";
	}
	
	public void onPreventCollision(Sprite that, Point collisionVector) {
		if (collisionVector.y != 0) {
			getVelocity().y = -1 * getVelocity().y;
		} else if (collisionVector.x != 0) {
			getVelocity().x = -1 * getVelocity().x;
		}
	}

}
