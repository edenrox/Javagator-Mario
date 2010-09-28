package com.hopkins.game.mario.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;

	private BufferedImage m_buffer;
	private Graphics2D m_bufferGraphics;
	private Renderer m_renderer;

	public ContentPanel() {
		// setup the buffer
		m_buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		m_bufferGraphics = (Graphics2D) m_buffer.createGraphics();
		m_bufferGraphics.setClip(0, 0, WIDTH, HEIGHT);
	}
	public Renderer getRenderer() {
		return m_renderer;
	}
	public void setRenderer(Renderer value) {
		m_renderer = value;
	}
	public void update() {
		repaint();
	}
	public void paintComponent(Graphics gPanel) {
		// render to the back buffer
		if (m_renderer != null) {
			m_renderer.render(m_bufferGraphics);
		}
		
		// copy the back buffer onto the visible buffer
		gPanel.drawImage(m_buffer, 0, 0, null);
	}
}
