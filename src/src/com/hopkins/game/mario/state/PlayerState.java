package com.hopkins.game.mario.state;

import java.util.Date;

import com.hopkins.game.mario.events.GameEventListener;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.powerups.OneUp;
import com.hopkins.game.mario.sprite.tiles.Coin;

public class PlayerState implements GameEventListener {
	public static final int TIME_LIMIT = 400;
	public static final String[] PLAYER_NAMES = new String[] {"Mario", "Luigi"}; 
	
	private int m_lives;
	private int m_playerIndex;
	private int m_points;
	private int m_coins;
	private int m_world;
	private int m_level;
	private Date m_startTime;
	
	public PlayerState(int playerIndex) {
		m_playerIndex = 0;
		m_points = 0;
		m_coins = 0;
		m_world = 1;
		m_level = 1;
		m_lives = 3;
		m_startTime = new Date();
	}
	public void start() {
		m_startTime = new Date();
	}
	public int getTimeLeft() {
		Date now = new Date();
		int timeElapsed = (int) (now.getTime() - m_startTime.getTime()) / 1000;
		
		return TIME_LIMIT - timeElapsed;
	}
	public boolean isTimeUp() {
		return getTimeLeft() <= 0;
	}
	public int getPoints() {
		return m_points;
	}
	public String getPlayerName() {
		return PLAYER_NAMES[m_playerIndex];
	}
	public int getCoins() {
		return m_coins;
	}
	public String getLevel() {
		return String.format("%d-%d", m_world, m_level);
	}

	public void onGameEvent(GameEventType type, Sprite target) {
		switch(type) {
			case BrickBreak:
				m_points += 100;
			case Collect:
				if (target.getClass() == Coin.class) {
					m_coins += 1;
					if (m_coins == 100) {
						// throw new game event here
						m_coins = 0;
					}
				}
				if (target.getClass() == OneUp.class) {
					m_lives += 1;
				}
				m_points += 50;
			case StompBadGuy:
				m_points += 100;
		}
	}
}
