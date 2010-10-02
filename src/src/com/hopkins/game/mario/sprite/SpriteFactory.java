package com.hopkins.game.mario.sprite;

import com.hopkins.game.mario.sprite.powerups.*;
import com.hopkins.game.mario.sprite.tiles.Coin;

public class SpriteFactory {

	public static Sprite create(String name) {
		if (name.equals("flower")) {
			return new Flower();
		}
		if (name.equals("mushroom")) {
			return new Mushroom();
		}
		if (name.equals("oneup")) {
			return new OneUp();
		}
		if (name.equals("coin")) {
			return new Coin();
		}
		throw new RuntimeException("Error, invalid sprite type: " + name);
	}
}
