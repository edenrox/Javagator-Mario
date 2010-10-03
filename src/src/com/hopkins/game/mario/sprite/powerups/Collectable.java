package com.hopkins.game.mario.sprite.powerups;

import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.movement.CollisionResponse;
import com.hopkins.game.mario.sprite.ImageSprite;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;

public abstract class Collectable extends ImageSprite {
	public boolean isSolid() {
		return false;
	}
	
	public void onPreventCollision(Sprite that, Point collisionVector) {
		if (collisionVector.x != 0) {
			getVelocity().x = -1 * getVelocity().x;
		}
	}
	
	public CollisionResponse onCollision(Sprite that, Point collisionVector) {
		if (that.getClass() == Player.class) {
			GameEventManager.get().fireEvent(GameEventType.Collect, this);
		}
		return CollisionResponse.Overlap;
	}
}
