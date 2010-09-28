package com.hopkins.game.mario;

import java.util.Vector;

import com.hopkins.game.mario.map.Level1;
import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;

public class GameState {
	private int m_level;
	private int m_lives;
	private int m_players;
	private int m_coins;
	private Map m_map;
	private Vector<Sprite> m_activeSprites;
	private static GameState s_instance;
	
	private GameState() {
		m_activeSprites = new Vector<Sprite>();
		m_level = 0;
		m_lives = 3;
		m_players = 1;
		m_coins = 0;
		m_map = (new Level1()).getMap();
		m_activeSprites.add(m_map.getPlayer());
	}
	
	public Vector<Sprite> getActiveSprites() {
		return m_activeSprites;
	}
	
	public Player getPlayer() {
		return m_map.getPlayer();
	}
	
	public int getCoins() {
		return m_coins;
	}
	public int getLives() {
		return m_lives;
	}
	
	public void collectOneUp() {
		m_lives++;
	}
	
	public void collectCoin() {
		m_coins++;
		if (m_coins > 100) {
			m_coins -= 100;
			collectOneUp();
		}
	}
	
	public static GameState getCurrent() {
		if (s_instance == null) {
			s_instance = new GameState();
		}
		return s_instance; 
	}
	
	public Map getMap() {
		return m_map;
	}
}
