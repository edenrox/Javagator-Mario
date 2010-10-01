package com.hopkins.game.mario.sprite.tiles;

import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.sprite.*;

public class Brick extends ImageSprite {

	public String getSpriteFile() {
		return "tiles/block-brick.png";
	}
	
	public boolean isGravityEffected() {
		return false;
	}
	
	public GameEventType onCollision(Sprite that, Position collisionVector) {
		if (collisionVector.getY() > 0) {
			// break the brick
			return GameEventType.BrickBreak;
		}
		return super.onCollision(that, collisionVector);
	}

}
