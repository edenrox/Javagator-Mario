package com.hopkins.game.mario.map;

import java.util.*;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCallback;

public class RangeTree {
	
	private Vector<Sprite> m_empty;
	private TreeMap<Integer, Vector<Sprite>> m_tree;
	
	public RangeTree() {
		m_empty = new Vector<Sprite>(0);
		m_tree = new TreeMap<Integer, Vector<Sprite>>();
	}
	
	public void clear() {
		m_tree.clear();
	}

	public void add(Sprite item) {
		Integer key = new Integer(item.getLeft());
		if (m_tree.containsKey(key)) {
			m_tree.get(key).add(item);
		} else {
			Vector<Sprite> v = new Vector<Sprite>();
			v.add(item);
			m_tree.put(key, v);
		}
	}
	
	public void remove(Sprite item) {
		remove(item.getLeft(), item);
	}
	
	public void remove(int x, Sprite item) {
		Integer key = new Integer(x);
		if (m_tree.containsKey(key)) {
			m_tree.get(key).remove(item);
		}
	}
	
	public void iterateRange(int x1, int x2, SpriteCallback func, Object data) {
		SortedMap<Integer, Vector<Sprite>> subtree = m_tree.subMap(new Integer(x1),new Integer(x2));
		for(Vector<Sprite> list_item : subtree.values()) {
			for (Sprite item : list_item) {
				func.execute(item, data);
			}
		}
	}
	public Vector<Sprite> getRange(int x1, int x2) {
		SortedMap<Integer, Vector<Sprite>> subtree = m_tree.subMap(new Integer(x1),new Integer(x2));
		Vector<Sprite> rv = new Vector<Sprite>();
		for(Vector<Sprite> list_item : subtree.values()) {
			for (Sprite item : list_item) {
				rv.add(item);
			}
		}
		return rv;
	}
	
	public Vector<Sprite> get(int x) {
		Integer key = new Integer(x);
		if (m_tree.containsKey(key)) {
			return m_tree.get(key);
		}
		return m_empty;
	}
}
