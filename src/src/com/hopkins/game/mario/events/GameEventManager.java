package com.hopkins.game.mario.events;

import java.util.Vector;

import com.hopkins.game.mario.sprite.Sprite;

public class GameEventManager {
	
	private Vector<GameEventListener> m_listeners;
	
	public GameEventManager() {
		m_listeners = new Vector<GameEventListener>();
	}
	
	public void addListener(GameEventListener listener) {
		m_listeners.add(listener);
	}
	
	public void fireEvent(GameEventType ev, Sprite target) {
		for(GameEventListener item : m_listeners) {
			item.onGameEvent(ev, target);
		}
	}
}
