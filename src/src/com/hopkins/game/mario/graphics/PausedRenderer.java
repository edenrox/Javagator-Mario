package com.hopkins.game.mario.graphics;

import java.awt.*;

public class PausedRenderer implements Renderer {
	
	public static final String PAUSED_TEXT = "Paused";
	
	private Font m_font;
	private FontMetrics m_fm;
	
	public PausedRenderer() {
		m_font = new Font("Arial" ,Font.BOLD, 16);
		m_fm = null;
	}
	
	public void render(Graphics2D g) {
		// figure out how big the screen is
		Rectangle rect = g.getClipBounds();
		
		// fill the screen with black
		g.setColor(Color.black);
		g.fill(rect);
		
		// Setup the font and measure gather the metrics text
		g.setFont(m_font);
		if (m_fm == null) {
			m_fm = g.getFontMetrics();
		}

		// Calcuate the center for the text
		int x = (int) (rect.getCenterX() - m_fm.stringWidth(PAUSED_TEXT) / 2);
		int y = (int) (rect.getCenterY() - m_fm.getHeight() / 2);
		
		// Render the text in white
		g.setColor(Color.white);
		g.drawString(PAUSED_TEXT, x, y);
	}
}
