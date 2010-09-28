package com.hopkins.game.mario.graphics;

import java.awt.*;

import com.hopkins.game.mario.sprite.SpriteCache;

public class TitleRenderer implements Renderer {
	
	public static final int ANIMATION_STEPS = 20;
	public static final int MAX_PLAYERS = 2;
	public static final String TEXT_ONE_PLAYER = "1 Player";
	public static final String TEXT_TWO_PLAYER = "2 Players";
	
	private int m_step;
	private int m_players;
	private Image m_splash;
	
	public TitleRenderer() {
		m_splash = SpriteCache.get().getSprite("splash.png");
	}
	
	public void skipIntro() {
		m_step = Math.max(ANIMATION_STEPS, m_step);
	}
	public boolean isIntroDone() {
		return (m_step >= ANIMATION_STEPS); 
	}
	public void toggle() {
		m_players = (m_players + 1) % MAX_PLAYERS;
	}
	public void reset() {
		m_players = 1;
		m_step = 0;
	}
	public void tick() {
		m_step++;
	}

	public void render(Graphics2D g) {
		int x, y, dy, travel;
		
		// figure out how big the screen is
		Rectangle rect = g.getClipBounds();

		// fill the screen with black
		g.setColor(Color.black);
		g.fill(rect);
		
		// calculate where the title image should be
		x = (rect.width - m_splash.getWidth(null)) / 2;
		travel = rect.height * 5 / 6;
		dy = (int) (travel * Math.min(1, (double) m_step / ANIMATION_STEPS));
		y = (rect.height - dy) / 2;
		
		// draw the title image
		g.drawImage(m_splash, x, y, null);
		
		// the player select
		if (m_step >= ANIMATION_STEPS) {
			
			x = rect.width / 6;
			y = rect.height * 5 / 6;
			
			g.setColor(Color.gray);
			g.drawString(TEXT_ONE_PLAYER, x, y);
			if (m_players == 0) {
				renderHighlight(g, x - 10, y);
			}
			
			y += 20;
			
			g.setColor(Color.gray);
			g.drawString(TEXT_TWO_PLAYER, x, y);
			if (m_players == 1) {
				renderHighlight(g, x - 10, y);
			}
		}
	}
	
	private void renderHighlight(Graphics2D g, int x, int y) {
		g.setColor(Color.white);
		g.fillOval(x, y - 8, 8, 8);
	}
	
}
