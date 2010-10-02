package com.hopkins.game.mario.map;

import java.util.Collection;
import java.util.TreeMap;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.tiles.Tile;
import com.hopkins.game.mario.sprite.tiles.TileFactory;

public class Map {
	
	private TreeMap<Integer, Tile> m_tree;
	
	public Map() {
		m_tree = new TreeMap<Integer, Tile>();
	}
	
	private Integer getKey(int x, int y) {
		return new Integer(x * 1000 + y);
	}
	private Integer getKey(Tile item) {
		return getKey(item.getX(), item.getY());
	}

	
	public void add(Tile item) {
		m_tree.put(getKey(item), item);
	}
	public void add(Tile item, int x, int y) {
		item.setLocation(x * Sprite.TILE_WIDTH, y * Sprite.TILE_HEIGHT);
		add(item);
	}
	public void create(String name, int x, int y) {
		add(TileFactory.create(name), x, y);
	}
	
	public void remove(Tile item) {
		m_tree.remove(getKey(item));
	}
	
	public Collection<Tile> getRange(int x1, int x2) {
		return m_tree.subMap(getKey(x1, 0), getKey(x2, 0)).values();
	}
}
