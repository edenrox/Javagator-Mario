package com.hopkins.game.mario.sprite.powerups;

public class OneUp extends Collectable {

	public OneUp() {
		getVelocity().x = 4;
	}
	
	public String getSpriteFile() {
		return "powerups/oneup.png";
	}
}
