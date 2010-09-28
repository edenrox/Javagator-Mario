package com.hopkins.game.mario.sprite;

import java.awt.Image;


public abstract class Sprite {
	public static final int TileWidth = 16;
	public static final int TileHeight = 16;
	
	private Position m_position;
	private Position m_velocity;
	private Size m_size;
	
	
	
	public int getLeft() { return m_position.getX(); }
	public int getRight() { return m_position.getX() + m_size.getWidth(); }
	public int getTop() { return m_position.getY(); }
	public int getBottom() { return m_position.getY() + m_size.getHeight(); }
	
	public Position getPosition() { return m_position; }
	public Position getVelocity() { return m_velocity; }
	public Size getSize() { return m_size; }
	
	public Sprite() {
		m_position = new Position();
		m_velocity = new Position();
		m_size = new Size();
	}
	
	public int getMaxVelocity() {
		return 6;
	}
	
	public boolean onCollision(Sprite that) {
		return (that.isSolid());
	}
	public boolean isSolid() {
		return true;
	}
	public boolean isGravityEffected() {
		return true;
	}
	
	public boolean isActive() {
		return false;
	}
	
	public Image getImage() {
		return null;
	}
	public void applyForce(Position forceVector) {
		getVelocity().add(forceVector);
	}
	public void applyVelocity() {
		getPosition().add(getVelocity());
	}
}
