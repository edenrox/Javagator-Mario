package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics2D;
import java.awt.Point;

public class Spawn extends Tile {
	
	private String m_type;
	
	public Spawn(String type) {
		m_type = type;
	}

	public String getSpriteFile() {
		return null;
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		// no rendering
		
		// here we will spawn the bad guy
	}
	
}
