package com.hopkins.game.mario.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardManager extends KeyAdapter {
	
	private boolean[] m_keyState;
	private KeyReleaseHandler m_releaseHandler;
	
	public KeyboardManager() {
		m_keyState = new boolean[ButtonType.values().length];
		m_releaseHandler = null;
	}
	
	public boolean isPressed(ButtonType button) {
		return m_keyState[button.ordinal()];
	}
	public void setReleasedHandler(KeyReleaseHandler handler) {
		m_releaseHandler = handler;
	}
	
	private void setPressed(ButtonType button, boolean isPressed) {
		m_keyState[button.ordinal()] = isPressed;
	}
	
	private ButtonType getButtonType(int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_UP:
				return ButtonType.Up;
			case KeyEvent.VK_DOWN:
				return ButtonType.Down;
			case KeyEvent.VK_LEFT:
				return ButtonType.Left;
			case KeyEvent.VK_RIGHT:
				return ButtonType.Right;
			case KeyEvent.VK_SPACE:
				return ButtonType.A;
			case KeyEvent.VK_CONTROL:
				return ButtonType.B;
			case KeyEvent.VK_ENTER:
			case KeyEvent.VK_ESCAPE:
				return ButtonType.Start;
		}
		return null;
	}
	
	public void keyPressed(KeyEvent ev) {
		ButtonType button = getButtonType(ev.getKeyCode());
		if (button != null) {
			setPressed(button, true);
		}
	}
	
	public void keyReleased(KeyEvent ev) {
		ButtonType button = getButtonType(ev.getKeyCode());
		if (button != null) {
			setPressed(button, false);
			if (m_releaseHandler != null) {
				m_releaseHandler.onKeyRelease(button);
			}
		}
		
	}
	
	
}
