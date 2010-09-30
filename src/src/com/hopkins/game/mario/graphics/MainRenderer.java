package com.hopkins.game.mario.graphics;

import java.awt.*;
import java.util.Vector;

import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.sprite.Position;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCallback;

public class MainRenderer implements Renderer {

	public static final double ACTIVE_PERCENT = 0.70;
	
	private Map m_map;
	private Vector<Sprite> m_active;
	private Rectangle m_visible;
	
	public MainRenderer() {
		m_visible = new Rectangle(0, 0, ContentPanel.WIDTH, ContentPanel.HEIGHT);
		m_active = new Vector<Sprite>();
	}
	
	public Vector<Sprite> getActiveSprites() {
		return m_active;
	}

	public Map getMap() {
		return m_map;
	}
	
	public void setMap(Map map) {
		m_map = map;
	}
	
	public void setPlayerPosition(Position pos) {
		int tX = (int) (m_visible.width * (1 - ACTIVE_PERCENT) / 2);
		int minX = m_visible.x + tX;
		int maxX = m_visible.x + m_visible.width - tX;
		
		if (pos.getX() < minX) {
			m_visible.x += pos.getX() - minX;
		} else if (pos.getX() > maxX) {
			m_visible.x += pos.getX() - maxX;
		}
		
		int tY = (int) (m_visible.height * (1 - ACTIVE_PERCENT) / 2);
		int minY = m_visible.y + tY;
		int maxY = m_visible.y + m_visible.height - tY;
		
		if (pos.getY() < minY) {
			m_visible.y += pos.getY() - minY;
		} else if (pos.getY() > maxY) {
			m_visible.y += pos.getY() - maxY;
		}
		
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.decode("0x6B88FF"));
		g.fill(g.getClipBounds());
		renderMap(g);
		renderSprites(g);
	}
	
	private void renderMap(final Graphics2D g) {
		if (m_map == null) {
			return;
		}
		
		final int ox = m_visible.x;
		final int oy = m_visible.y;
		
		SpriteCallback func = new SpriteCallback() {
			public void execute(Sprite item, Object data) {
				Position pos = item.getPosition();
				g.drawImage(item.getImage(), pos.getX() - ox, pos.getY() - oy, null);
			}
		};
		
		m_map.iterateRange(m_visible.x - Sprite.TileWidth, m_visible.x + m_visible.width, func, null);
	}
	
	private void renderSprites(Graphics2D g) {
		if (m_active == null) {
			return;
		}
		
		int ox = m_visible.x;
		int oy = m_visible.y;
		
		for(Sprite item : m_active) {
			Position pos = item.getPosition();
			g.drawImage(item.getImage(), pos.getX() - ox, pos.getY() - oy, null);
		}
	}

}
