package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics2D;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteFactory;

public class Spawn extends Tile {
	
	private String m_type;
	private boolean m_done;
	
	public Spawn(String type) {
		m_type = type;
		m_done = false;
	}

	public String getSpriteFile() {
		return null;
	}
	
	public boolean isSolid() {
		return false;
	}
	public void render(Graphics2D g, Point p, int tick) {
		// no rendering
		
		// here we will spawn the bad guy
		if (!m_done) {
			Sprite item = SpriteFactory.create(m_type);
			item.setLocation(this.getX(), this.getY());
			GameEventManager.get().spawn(item);
			m_done = true;
		}
	}
	
}
