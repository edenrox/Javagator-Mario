package com.hopkins.game.mario.sprite.enemies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.movement.CollisionResponse;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;
import com.hopkins.game.mario.sprite.player.Player;

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
	
	public CollisionResponse onCollision(Sprite that, Point collisionVector) {
		if (checkStomp(that, collisionVector)) {
			if (m_state == KoopaState.Walk) {
				GameEventManager.get().fireEvent(GameEventType.StompBadGuy, this);
				setState(KoopaState.Still);
			} else if (m_state == KoopaState.Slide) {
				setState(KoopaState.Still);
			} else if (m_state == KoopaState.Still) {
				if (that.getX() != this.getX()) {
					if (this.getX() < that.getX()) {
						getVelocity().x = -4;
					} else {
						getVelocity().x = 4;
					}
					setState(KoopaState.Slide);	
				}
			}
			return CollisionResponse.Bounce;
		} else if (that.getClass() == Player.class) {
			GameEventManager.get().fireEvent(GameEventType.Injure, this);
			return CollisionResponse.Overlap;
		}
		return CollisionResponse.Block;
	}
}