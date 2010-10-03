package com.hopkins.game.mario.sprite.tiles;

import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.movement.CollisionResponse;
import com.hopkins.game.mario.sprite.*;
import com.hopkins.game.mario.sprite.player.Player;
import com.hopkins.game.mario.sprite.player.PlayerSize;
import com.hopkins.game.mario.sprite.projectile.BrickBit;

public class Brick extends Tile {
	

	public String getSpriteFile() {
		return "tiles/block-brick.png";
	}
	
	public void spawnBits() {
		GameEventManager gem = GameEventManager.get();
		gem.spawn(new BrickBit(getX(), getY(), -3, -12));
		gem.spawn(new BrickBit(getX() + 8, getY(), 3, -12));
		
		gem.spawn(new BrickBit(getX(), getY() + 8, -3, -8));
		gem.spawn(new BrickBit(getX() + 8, getY() + 8, 3, -8));
	}
	
	public CollisionResponse onCollision(Sprite that, Point collisionVector) {
		if (that.getClass() == Player.class) {
			Player p = (Player) that;
			if ((p.getPlayerSize() != PlayerSize.Small) && (collisionVector.getY() < 0)) {
				// break the brick
				spawnBits();
				GameEventManager.get().remove(this);
			}
		}
		return super.onCollision(that, collisionVector);
	}

}
