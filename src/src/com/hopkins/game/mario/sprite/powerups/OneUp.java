package com.hopkins.game.mario.sprite.powerups;

import com.hopkins.game.mario.GameState;
import com.hopkins.game.mario.sprite.Position;
import com.hopkins.game.mario.sprite.Sprite;

public class OneUp extends Collectable {

	public String getSpriteFile() {
		return "powerups/oneup";
	}
	
	public void onCollect() {
		GameState.getCurrent().collectOneUp();
	}
	
	public boolean onCollision(Sprite that, Position collisionVector) {
		boolean rv = super.onCollision(that, collisionVector);
		if (rv) {
			// reverse vector
		}
		return rv;
	}
}
