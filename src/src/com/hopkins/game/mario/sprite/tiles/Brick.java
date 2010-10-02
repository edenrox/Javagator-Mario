package com.hopkins.game.mario.sprite.tiles;

import java.awt.Point;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.movement.MovementManager;
import com.hopkins.game.mario.sprite.*;
import com.hopkins.game.mario.sprite.player.Player;
import com.hopkins.game.mario.sprite.player.PlayerSize;

public class Brick extends Tile {

	public String getSpriteFile() {
		return "tiles/block-brick.png";
	}
	
	public GameEventType onCollision(Sprite that, Point collisionVector) {
		if (that.getClass() == Player.class) {
			Player p = (Player) that;
			if ((p.getPlayerSize() != PlayerSize.Small) && (collisionVector.getY() < 0)) {
				// break the brick
				MovementManager.get().remove(this);
				return GameEventType.BrickBreak;
			}
		}
		return super.onCollision(that, collisionVector);
	}

}
