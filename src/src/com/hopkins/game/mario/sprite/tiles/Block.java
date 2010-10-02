package com.hopkins.game.mario.sprite.tiles;

public class Block extends Tile {
	
	private BlockColor m_color;

	public Block(BlockColor color) {
		m_color = color;
	}
	
	public String getSpriteFile() {
		return String.format("tiles/block-%s.png", m_color.toString().toLowerCase());
	}

}
