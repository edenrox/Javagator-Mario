package com.hopkins.game.mario.sprite.powerups;

import com.hopkins.game.mario.GameState;
import com.hopkins.game.mario.sprite.Sprite;

public class OneUp extends Collectable {

	public String getSpriteFile() {
		return "powerups/oneup";
	}
	
	public void onCollect() {
		GameState.getCurrent().collectOneUp();
	}
	
	public boolean onCollision(Sprite that) {
		boolean rv = super.onCollision(that);
		if (rv) {
			// reverse vector
		}
		return rv;
	}
}
