package com.hopkins.game.mario.sprite.powerups;

public class Mushroom extends Collectable {
	
	public Mushroom() {
		getVelocity().x = 4;
	}

	public String getSpriteFile() {
		return "powerups/mushroom.png";
	}
}
