package com.hopkins.game.mario.sprite;

import com.hopkins.game.mario.sprite.powerups.*;
import com.hopkins.game.mario.sprite.tiles.Block;
import com.hopkins.game.mario.sprite.tiles.BlockColor;
import com.hopkins.game.mario.sprite.tiles.Brick;
import com.hopkins.game.mario.sprite.tiles.Cloud;
import com.hopkins.game.mario.sprite.tiles.Tree;

public class SpriteFactory {

	public static Sprite create(String name) {
		if (name.equals("coin")) {
			return new Coin();
		}
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
		if (name.equals("brick")) {
			return new Brick();
		}
		if (name.equals("tree")) {
			return new Tree();
		}
		if (name.equals("cloud")) {
			return new Cloud();
		}
		if (name.equals("block")) {
			return new Block(BlockColor.Stone);
		}
		throw new RuntimeException("Error, invalid sprite type: " + name);
	}
}
