package com.hopkins.game.mario.sprite.powerups;

import java.awt.Point;

import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.sprite.Sprite;

public class Star extends Collectable {

	public Star() {
		getVelocity().x = 4;
		getVelocity().y = 4;
	}
	
	public String getSpriteFile() {
		return "powerups/star.png";
	}
	
	public GameEventType onCollision(Sprite that, Point collisionVector) {
		if ((that.isSolid()) && (collisionVector.y > 0)) {
			getVelocity().y = 4;
		}
		return super.onCollision(that, collisionVector);
	}

}
