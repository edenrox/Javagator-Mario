package com.hopkins.game.mario.sprite.enemies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;

public class Koopa extends Sprite {

	private KoopaState m_state;
	
	public Koopa() {
		super();
		m_state = KoopaState.Walk;
	}
	
	public void setState(KoopaState state) {
		m_state = state;
	}
	
	private int getNumFrames() {
		switch(m_state) {
			case Walk:
				return 2;
			case Still:
				return 1;
			case Slide:
				return 4;
		}
		return 1;
	}
	private int getFrameOrigin() {
		switch(m_state) {
			case Walk:
				//if (going left)
				return 1;
				// else 
				// return 2;
			case Still:
				return 4;
			case Slide:
				return 4;
		}
		return 0;
	}
	

	public String getSpriteFile() {
		return "enemies/koopa.png";
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		
		Image fire = SpriteCache.get().getSprite(getSpriteFile());
		int frame = getFrameOrigin() + (tick % getNumFrames());
		int x = frame * TILE_WIDTH;
		int y = 0;
		
		g.drawImage(fire, p.x, p.y, p.x+TILE_WIDTH, p.y+TILE_HEIGHT, 
						x, y, x+TILE_WIDTH, y+TILE_HEIGHT, null);
	}

}
