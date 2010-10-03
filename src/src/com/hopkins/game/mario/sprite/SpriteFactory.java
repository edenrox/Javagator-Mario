package com.hopkins.game.mario.sprite;

import com.hopkins.game.mario.sprite.enemies.Goomba;
import com.hopkins.game.mario.sprite.enemies.Koopa;
import com.hopkins.game.mario.sprite.enemies.KoopaState;
import com.hopkins.game.mario.sprite.powerups.*;
import com.hopkins.game.mario.sprite.projectile.BounceCoin;
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
		if (name.equals("star")) {
			return new Star();
		}
		if (name.equals("coin")) {
			return new Coin();
		}
		if (name.equals("goomba")) {
			return new Goomba();
		}
		if (name.equals("bounce-coin")) {
			return new BounceCoin();
		}
		if (name.startsWith("koopa")) {
			String[] parts = name.split("-");
			Koopa item = new Koopa();
			if (parts.length > 1) {
				if (parts[1] == "shell") {
					item.setState(KoopaState.Still);
				} else if (parts[1] == "flying") {
					item.setState(KoopaState.Fly);
				}
			}
			return item;
		}
		throw new RuntimeException("Error, invalid sprite type: " + name);
	}
}
