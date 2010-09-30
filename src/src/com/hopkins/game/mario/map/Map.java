package com.hopkins.game.mario.map;

import java.util.Vector;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCallback;
import com.hopkins.game.mario.sprite.SpriteFactory;
import com.hopkins.game.mario.sprite.player.Player;

public class Map {
	
	private RangeTree m_tree;
	private Player m_player;
	
	public Map() {
		m_tree = new RangeTree();
	}
	
	public void addPlayer(Player item) {
		m_player = item;
		//add(item);
	}
	public Player getPlayer() {
		return m_player;
	}

	public void add(Sprite item) {
		m_tree.add(item);
	}
	public void add(Sprite item, int x, int y) {
		item.getPosition().set(x * Sprite.TileWidth, y * Sprite.TileHeight);
		add(item);
	}
	public void create(String name, int x, int y) {
		add(SpriteFactory.create(name), x, y);
	}
	public void move(int oldX, Sprite item) {
		m_tree.remove(oldX, item);
		m_tree.add(item);
	}
	
	public void remove(Sprite item) {
		m_tree.remove(item);
	}
	
	public void iterateRange(int x1, int x2, SpriteCallback func, Object data) {
		m_tree.iterateRange(x1, x2, func, data);
	}
	public Vector<Sprite> getRange(int x1, int x2) {
		return m_tree.getRange(x1, x2);
	}
	
	public int distanceFromGround(Sprite item) {
		int x1 = (int) (Math.floor((double) item.getLeft() / 16) * 16);
		int x2 = (int) (Math.ceil((double) item.getLeft() / 16) * 16);
		int y = item.getBottom();
		int distance = 10000; 
		Vector<Sprite> sprites = m_tree.get(x1);
		if (x2 != x1) {
			sprites.addAll(m_tree.get(x2));
		}
		for(Sprite compare : sprites) {
			if (compare.isSolid()) {
			int dx = compare.getPosition().getY() - y;
				if (dx >= 0) {
					distance = Math.min(dx, distance);
				}
			}
		}
		return distance;
	}
}
