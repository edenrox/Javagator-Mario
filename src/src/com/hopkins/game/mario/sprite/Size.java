package com.hopkins.game.mario.sprite;

public class Size {
	private int m_width;
	private int m_height;
	
	public int getWidth() { return m_width; }
	public void setWidth(int value) { m_width = value; }
	public int getHeight() { return m_height; }
	public void setHeight(int value) { m_height = value; }
	
	public void set(int width, int height) {
		m_width = width;
		m_height = height;
	}
}
