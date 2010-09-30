package com.hopkins.game.mario.graphics;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import com.hopkins.game.mario.GameState;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCallback;

public class RenderManager extends JPanel implements SpriteCallback {
	
	private static final long serialVersionUID = 1L;
	
	private Rectangle m_renderWindow;
	private Image m_buffer;
	
	public RenderManager(int width, int height) {
		super(false);
		setLayout(null);
		m_buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		m_renderWindow = new Rectangle(0,0, width, height);
		RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
	}
	
	public void moveRight() {
		m_renderWindow.x += 2;
	}
	
	public void execute(Sprite item, Object data) {
		if (!item.isActive()) {
			renderSprite(item, (Graphics2D) data);
		}
	}
	
	private void renderSprite(Sprite item, Graphics2D g) {
		int x = item.getPosition().getX() - m_renderWindow.x;
		int y = item.getPosition().getY() - m_renderWindow.y;
		g.drawImage(item.getImage(), x, y, null);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	
	
	public void paintComponent(Graphics gPanel) {
		Graphics2D g = (Graphics2D) m_buffer.getGraphics();
		int w = m_buffer.getWidth(null);
		int h = m_buffer.getHeight(null);
		g.setColor(Color.decode("0x6B88FF"));
		g.fillRect(0, 0, w, 13 * Sprite.TileHeight);
		g.setColor(Color.black);
		g.fillRect(0, 13 * Sprite.TileHeight, w, h);
		
		// render the map
		GameState.getCurrent().getMap().iterateRange(m_renderWindow.x - Sprite.TileWidth, m_renderWindow.x + m_renderWindow.width, this, g);
		
		// render the active sprites
		for(Sprite item : GameState.getCurrent().getActiveSprites()) {
			renderSprite(item, g);
		}
		
		m_buffer.flush();
		
		gPanel.drawImage(m_buffer, 
				0, 0, m_buffer.getWidth(null), m_buffer.getHeight(null),
				0 , 0, w, h, this);
	}
	
}
