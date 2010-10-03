package com.hopkins.game.mario.events;

import java.util.Vector;

import com.hopkins.game.mario.sprite.Sprite;

public class GameEventManager {
	private static GameEventManager s_instance;
	public static GameEventManager get() {
		if (s_instance == null) {
			s_instance = new GameEventManager();
		}
		return s_instance;
	}
	
	private Vector<GameEventListener> m_listeners;
	
	private GameEventManager() {
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
	
	public void remove(Sprite target) {
		fireEvent(GameEventType.RemoveSprite, target);
	}
	public void spawn(Sprite target) {
		fireEvent(GameEventType.SpawnSprite, target);
	}
}