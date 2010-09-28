package com.hopkins.game.mario.sprite;

public class Position {
	private int m_x;
	private int m_y;
	private int m_z;
	
	public int getX() {
		return m_x;
	}
	public void setX(int value) {
		m_x = value;
	}
	
	public int getY() {
		return m_y;
	}
	public void setY(int value) {
		m_y = value;
	}
	
	public int getZ() {
		return m_z;
	}
	public void setZ(int value) {
		m_z = value;
	}
	
	public Position() {
		m_x = 0;
		m_y = 0;
		m_z = 0;
	}
	public Position(int x, int y) {
		m_x = x;
		m_y = y;
		m_z = 0;
	}
	
	public void copy(Position that) {
		m_x = that.getX();
		m_y = that.getY();
		m_z = that.getZ();
	}
	public void set(int x, int y) {
		m_x = x;
		m_y = y;
	}
	public void set(int x, int y, int z) {
		m_x = x;
		m_y = y;
		m_z = z;
	}
	
	public void add(Position that) {
		m_x += that.getX();
		m_y += that.getY();
		m_z += that.getZ();
	}
	public void add(int x, int y) {
		m_x += x;
		m_y += y;
	}
}
