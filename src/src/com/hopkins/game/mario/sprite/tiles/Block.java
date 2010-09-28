package com.hopkins.game.mario.sprite.tiles;

import com.hopkins.game.mario.sprite.ImageSprite;

public class Block extends ImageSprite {
	
	private BlockColor m_color;

	public Block(BlockColor color) {
		m_color = color;
	}
	
	public String getSpriteFile() {
		return String.format("tiles/block-%s.png", m_color.toString().toLowerCase());
	}
	
	public boolean isGravityEffected() {
		return false;
	}

}
