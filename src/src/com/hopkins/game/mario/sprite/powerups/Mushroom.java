package com.hopkins.game.mario.sprite.powerups;

import com.hopkins.game.mario.GameState;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.PlayerState;

public class Mushroom extends Collectable {

	public String getSpriteFile() {
		return "powerups/mushroom.png";
	}
	
	public void onCollect() {
		GameState gs = GameState.getCurrent();
		if (gs.getPlayer().getPlayerState() == PlayerState.Small) {
			gs.getPlayer().setPlayerState(PlayerState.Big);
		}
	}
	
	public boolean onCollision(Sprite that) {
		boolean rv = super.onCollision(that);
		if (rv) {
			// reverse vector
		}
		return rv;
	}
}
