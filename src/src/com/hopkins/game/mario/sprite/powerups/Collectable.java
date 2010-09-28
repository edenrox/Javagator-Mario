package com.hopkins.game.mario.sprite.powerups;

import com.hopkins.game.mario.GameState;
import com.hopkins.game.mario.sprite.ImageSprite;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;

public abstract class Collectable extends ImageSprite {
	public boolean isSolid() {
		return false;
	}
	
	public boolean onCollision(Sprite that) {
		if (that.getClass() == Player.class) {
			GameState gs = GameState.getCurrent();
			gs.getMap().remove(this);
			onCollect();
		}
		return false;
	}
	
	public abstract void onCollect();
}
