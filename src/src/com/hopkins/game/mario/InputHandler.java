package com.hopkins.game.mario;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.hopkins.game.mario.sprite.Position;

public class InputHandler extends KeyAdapter {
	private boolean m_leftDown;
	private boolean m_rightDown;
	private boolean m_jumpDown;
	private boolean m_fireDown;
	private boolean m_duckDown;
	
	public static final int ForceQuantity = 4;
	
	public Position getForceVector() {
		Position v = new Position();
		if (m_leftDown) {
			v.setX(v.getX() - ForceQuantity);
		}
		if (m_rightDown) {
			v.setX(v.getX() + ForceQuantity);
		}
		if (m_jumpDown) {
			v.setY(v.getY() - 3 * ForceQuantity / 2);
		}
		
		return v;
	}
	
	public boolean isFiring() {
		return m_fireDown;
	}
	public boolean isDucking() {
		return m_duckDown;
	}
	
	public void keyPressed(KeyEvent ev) {
		keyChange(ev.getKeyCode(), true);
	}
	
	public void keyReleased(KeyEvent ev) {
		keyChange(ev.getKeyCode(), false);
	}
	
	public void keyChange(int keyCode, boolean isDown) {
		switch(keyCode) {
			case KeyEvent.VK_LEFT:
				m_leftDown = isDown;
				break;
			case KeyEvent.VK_RIGHT:
				m_rightDown = isDown;
				break;
			case KeyEvent.VK_DOWN:
				m_duckDown = isDown;
				break;
			case KeyEvent.VK_SPACE:
				m_jumpDown = isDown;
				break;
			case KeyEvent.VK_CONTROL:
				m_fireDown = isDown;
				break;
		}
	}
}
