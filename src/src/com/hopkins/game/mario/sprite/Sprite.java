package com.hopkins.game.mario.sprite;

import java.awt.Point;
import java.awt.Rectangle;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.graphics.Renderable;

public abstract class Sprite implements Renderable {

	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 16;
	
	private Rectangle m_bounds;
	private Point m_velocity;

	public Sprite() {
		m_bounds = new Rectangle(0,0, TILE_WIDTH, TILE_HEIGHT);
		m_velocity = new Point(0,0);
	}
	public Point getVelocity() {
		return m_velocity;
	}
	public Rectangle getBounds() {
		return m_bounds;
	}
	public void setLocation(int x, int y) {
		m_bounds.x = x;
		m_bounds.y = y;
	}
	public void setSize(int width, int height) {
		m_bounds.width = width;
		m_bounds.height = height;
	}
	public int getX() {
		return m_bounds.x;
	}
	public int getY() {
		return m_bounds.y;
	}
	
	public int getMaxVelocity() {
		return 6;
	}
	
	public GameEventType onCollision(Sprite that, Point collisionVector) {
		return (this.isSolid()) ? GameEventType.PreventCollision : GameEventType.NoEvent;
	}
	public boolean isSolid() {
		return true;
	}
	public boolean isGravityEffected() {
		return true;
	}
	public void applyForce(Point forceVector) {
		m_velocity.x += forceVector.x;
		m_velocity.y += forceVector.y;
	}
	public void applyVelocity() {
		m_bounds.x += m_velocity.x;
		m_bounds.y += m_velocity.y;
	}
}
