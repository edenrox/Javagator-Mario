package com.hopkins.game.mario.sprite.tiles;

public class Castle extends Tile {
	private boolean m_isLarge;
	
	public Castle(boolean isLarge) {
		super();
		m_isLarge = isLarge;
		setSize(80, 80);
	}
	public boolean isSolid() {
		return false;
	}
	public String getSpriteFile() {
		return String.format("tiles/castle-%s.png", (m_isLarge) ? "large" : "small");
	}

}

