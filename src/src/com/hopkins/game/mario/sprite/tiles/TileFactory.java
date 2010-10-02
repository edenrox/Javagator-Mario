package com.hopkins.game.mario.sprite.tiles;

import com.hopkins.game.mario.map.Map;

public class TileFactory {

	public static Tile create(String name) {
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
		if (name.equals("coin")) {
			return new Coin();
		}
		throw new RuntimeException("Error, invalid tile type: " + name);
	}
	public static Tile createQuestionBlock(String contains, int quantity) {
		
		return new QuestionBox(contains, quantity);
	}
	
	public static Tile createSpawn(String type) {
		return new Spawn(type);
	}

}
