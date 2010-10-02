package com.hopkins.game.mario.graphics;

import java.awt.*;
import java.util.Collection;
import java.util.Vector;

import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.tiles.Tile;
import com.hopkins.game.mario.state.PlayerState;

public class MainRenderer implements Renderer {

	public static final double ACTIVE_PERCENT = 0.70;
	public static final int TICK_MODULUS = 210;
	
	private int m_tick;
	private Map m_map;
	private Vector<Sprite> m_active;
	private Rectangle m_visible;
	private TopBarRenderer m_tbr;
	
	public MainRenderer() {
		m_tick = 0;
		m_visible = new Rectangle(0, 0, ContentPanel.WIDTH, ContentPanel.HEIGHT);
		m_active = new Vector<Sprite>();
		m_tbr = new TopBarRenderer();
	}
	
	public void setState(PlayerState ps) {
		m_tbr.setPlayerState(ps);
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
	
	public void setPlayerPosition(int x, int y) {
		int tX = (int) (m_visible.width * (1 - ACTIVE_PERCENT) / 2);
		int minX = m_visible.x + tX;
		int maxX = m_visible.x + m_visible.width - tX;
		
		if (x < minX) {
			m_visible.x += x - minX;
		} else if (x > maxX) {
			m_visible.x += x - maxX;
		}
		
		int tY = (int) (m_visible.height * (1 - ACTIVE_PERCENT) / 2);
		int minY = m_visible.y + tY;
		int maxY = m_visible.y + m_visible.height - tY;
		
		if (y < minY) {
			m_visible.y += y - minY;
		} else if (y > maxY) {
			m_visible.y += y - maxY;
		}
		
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.decode("0x6B88FF"));
		g.fill(g.getClipBounds());
		renderMap(g);
		renderSprites(g);
		m_tbr.render(g);
		m_tick = (m_tick + 1) % TICK_MODULUS; 
	}
	
	private void renderMap(final Graphics2D g) {
		if (m_map == null) {
			return;
		}
		
		// get all the Tiles we need to render
		int minX = m_visible.x - Sprite.TILE_WIDTH;
		int maxX = m_visible.x + m_visible.width;
		Collection<Tile> tiles = m_map.getRange(minX, maxX);

		
		int ox = m_visible.x;
		int oy = m_visible.y;
		
		Point pos = new Point();
		
		for(Sprite item : tiles) {
			pos.setLocation(item.getX() - ox, item.getY() - oy);
			item.render(g, pos, m_tick);
		}
		
	}
	
	private void renderSprites(Graphics2D g) {
		if (m_active == null) {
			return;
		}
		Point pos = new Point();
		int ox = m_visible.x;
		int oy = m_visible.y;
		
		for(Sprite item : m_active) {
			pos.setLocation(item.getX() - ox, item.getY() - oy);
			item.render(g, pos, m_tick);
		}
	}

}
