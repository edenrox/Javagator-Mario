package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class Pipe extends Tile {
	
	private int m_height;

	public Pipe(int height) {
		super();
		m_height = height;
		this.setSize(TILE_WIDTH * 2, TILE_HEIGHT * (m_height + 1));
	}
	
	public String getSpriteFile() {
		return "tiles/pipe.png";
	}
	
	public boolean isGravityEffected() {
		return false;
	}
	
	public boolean isSolid() {
		return true;
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		Image pipe = getImage();
		
		// draw the top of the pipe
		g.drawImage(pipe, p.x, p.y, p.x + TILE_WIDTH * 2, p.y + TILE_HEIGHT,
					0, 0, TILE_WIDTH * 2, TILE_HEIGHT, null);
		
		// draw the bottom of the pipe
		if (m_height > 1) {
			g.drawImage(pipe, p.x, p.y + TILE_HEIGHT, p.x + TILE_WIDTH * 2, p.y + TILE_HEIGHT * (m_height + 1),
					0, TILE_HEIGHT, TILE_WIDTH * 2, TILE_HEIGHT * 2, null);
		}
	}
}
