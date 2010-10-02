package com.hopkins.game.mario.sprite.tiles;

import java.awt.Point;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.sprite.*;

public class Brick extends Tile {

	public String getSpriteFile() {
		return "tiles/block-brick.png";
	}
	
	public GameEventType onCollision(Sprite that, Point collisionVector) {
		if (collisionVector.getY() < 0) {
			// break the brick
			return GameEventType.BrickBreak;
		}
		return super.onCollision(that, collisionVector);
	}

}
