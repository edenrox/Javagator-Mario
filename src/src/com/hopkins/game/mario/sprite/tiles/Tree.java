package com.hopkins.game.mario.sprite.tiles;

import com.hopkins.game.mario.sprite.ImageSprite;

public class Tree extends ImageSprite {


	public String getSpriteFile() {
		return "tiles/tree.png";
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public boolean isGravityEffected() {
		return false;
	}
}
