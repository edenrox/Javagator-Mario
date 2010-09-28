package com.hopkins.game.mario.sprite.powerups;

import com.hopkins.game.mario.GameState;

public class Coin extends Collectable {

	public String getSpriteFile() {
		return "powerups/coin.png";
	}
	
	public boolean isGravityEffected() {
		return false;
	}
	
	public void onCollect() {
		GameState.getCurrent().collectCoin();
	}

}
