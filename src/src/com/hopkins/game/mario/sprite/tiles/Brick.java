package com.hopkins.game.mario.sprite.tiles;

import com.hopkins.game.mario.sprite.ImageSprite;

public class Brick extends ImageSprite {

	public String getSpriteFile() {
		return "tiles/block-brick.png";
	}
	
	public boolean isGravityEffected() {
		return false;
	}

}
