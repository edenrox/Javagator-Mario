package com.hopkins.game.mario.sprite.powerups;

import java.awt.Point;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.sprite.ImageSprite;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;

public abstract class Collectable extends ImageSprite {
	public boolean isSolid() {
		return false;
	}
	
	public GameEventType onCollision(Sprite that, Point collisionVector) {
		if (that.getClass() == Player.class) {
			return GameEventType.Collect;
		}
		return GameEventType.NoEvent;
	}
}
