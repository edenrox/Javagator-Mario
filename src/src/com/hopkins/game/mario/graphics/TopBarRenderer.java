package com.hopkins.game.mario.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;
import com.hopkins.game.mario.state.PlayerState;

public class TopBarRenderer implements Renderer {
	
	private PlayerState m_state;
	
	public void setPlayerState(PlayerState state) {
		m_state = state;
	}

	public void render(Graphics2D g) {
		int xinc = g.getClipBounds().width / 4;
		int yinc = 15;
		
		g.setColor(Color.white);
		// Player name
		g.drawString(m_state.getPlayerName(), 0, yinc);
		
		// Points
		g.drawString(String.format("%06d", m_state.getPoints()), 0, yinc * 2);
		
		// Coins
		Image coin = SpriteCache.get().getSprite("tiles/coin.png");
		g.drawImage(coin, xinc - Sprite.TILE_WIDTH, yinc + 3, xinc, yinc + Sprite.TILE_HEIGHT + 3,
						0, 0, Sprite.TILE_WIDTH, Sprite.TILE_HEIGHT, null);
		g.drawString(String.format(" x %02d", m_state.getCoins()), xinc, yinc * 2);
		
		// World
		g.drawString("World", xinc * 2, yinc);
		g.drawString(m_state.getLevel(), xinc * 2, yinc * 2);
		
		// Time
		g.drawString("Time", xinc * 3, yinc);
		g.drawString(String.format("%03d", m_state.getTimeLeft()), xinc * 3, yinc*2);
	}

}
