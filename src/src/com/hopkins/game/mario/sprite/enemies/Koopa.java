package com.hopkins.game.mario.sprite.enemies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.sprite.SpriteCache;

public class Koopa extends Enemy {
	public static final int HEIGHT = 27;
	private KoopaState m_state;
	
	public Koopa() {
		super();
		setState(KoopaState.Walk);
		setSize(TILE_WIDTH, HEIGHT);
	}
	
	public void setState(KoopaState state) {
		m_state = state;
		if (m_state == KoopaState.Walk) {
			getVelocity().x = -1;
		} else {
			if (m_state == KoopaState.Still) {
				getVelocity().x = 0;
			}
			setSize(TILE_WIDTH, TILE_HEIGHT);
		}  
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
				if (getVelocity().x < 0) {
					return 0;
				} else {
					return 2;
				}
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
		int frame = getFrameOrigin() + (tick / 2 % getNumFrames());
		int x = frame * TILE_WIDTH;
		int y = getBounds().height - HEIGHT;
		
		g.drawImage(fire, p.x, p.y + y, p.x + TILE_WIDTH, p.y + y +HEIGHT, 
						x, y, x + TILE_WIDTH, HEIGHT, null);
	}

}
