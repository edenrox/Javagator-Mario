package com.hopkins.game.mario.sprite.powerups;

import com.hopkins.game.mario.GameState;
import com.hopkins.game.mario.sprite.player.PlayerState;


public class Flower extends Collectable {

	public String getSpriteFile() {
		return "powerups/flower.png";
	}
	
	public void onCollect() {
		GameState.getCurrent().getPlayer().setPlayerState(PlayerState.Fire);
	}



}
