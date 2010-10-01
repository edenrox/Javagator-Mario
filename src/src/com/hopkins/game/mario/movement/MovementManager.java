package com.hopkins.game.mario.movement;

import java.awt.Rectangle;
import java.util.Collection;
import java.util.Vector;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.sprite.*;
import com.hopkins.game.mario.sprite.powerups.Coin;
import com.hopkins.game.mario.sprite.tiles.Pipe;

public class MovementManager {
	
	public static final int GRAVITY_FORCE = 1;
	
	private Position m_gravityFV;
	private Position m_dragFV;
	
	public MovementManager() {
		m_gravityFV = new Position(0, GRAVITY_FORCE);
		m_dragFV = new Position(0, GRAVITY_FORCE);
	}
	
	private void limitVelocity(Sprite item) {
		int maxV = item.getMaxVelocity();
		int nMaxV = -1 * maxV;
		Position v = item.getVelocity();
		if (v.getX() > maxV) {
			v.setX(maxV);
		} else if (v.getX() < nMaxV) {
			v.setX(nMaxV);
		}
		if (v.getY() > maxV) {
			v.setY(maxV);
		} else if (v.getY() < nMaxV) {
			v.setY(nMaxV);
		}
	}
	
	private boolean isInCollision(Sprite itemA, Position newPosA, Sprite itemB) {
		Rectangle rectA = new Rectangle(newPosA.getX(), newPosA.getY(), itemA.getSize().getWidth(), itemA.getSize().getHeight());
		Rectangle rectB = new Rectangle(itemB.getPosition().getX(), itemB.getPosition().getY(), itemB.getSize().getWidth(), itemB.getSize().getHeight());
		
		return rectA.intersects(rectB);
	}
	
	private Position getCollisionVector(Sprite itemA, Sprite itemB) {
		Position collisionVector = new Position();
		
		// we are below, but our top is above this items bottom
		if ((itemA.getTop() <= itemB.getBottom()) && (itemA.getBottom() >= itemB.getBottom())) {
			collisionVector.setY(itemB.getBottom() - itemA.getTop());
		} else if ((itemA.getBottom() >= itemB.getTop()) && (itemA.getTop() <= itemB.getTop())) {
			collisionVector.setY(itemB.getTop() - itemA.getBottom());
		}
		if (itemA.getRight() < itemB.getLeft()) {
			collisionVector.setX(itemB.getLeft() - itemA.getRight());
		} else if (itemA.getLeft() > itemB.getRight()) {
			collisionVector.setX(itemB.getRight() - itemA.getLeft());
		}
		return collisionVector;
	}
	
	private boolean checkCollisions(Sprite item, Map map, GameEventManager gem) {
		boolean rv = false;
		Position collisionVector = null;
		Position newpos = new Position();
		newpos.copy(item.getPosition());
		newpos.add(item.getVelocity());
		
		int minX = newpos.getX() - Sprite.TileWidth * 2;
		int maxX = newpos.getX() + item.getSize().getWidth();
		
		Vector<Sprite> sprites = map.getRange(minX, maxX);
		for(Sprite that : sprites) {
			if (isInCollision(item, newpos, that)) {
				collisionVector = getCollisionVector(item, that);
				if (that.getClass() == Coin.class) {
					System.err.println(String.format("CV: {x: %d, y: %d}", collisionVector.getX(), collisionVector.getY()));
				}
				GameEventType ev = that.onCollision(item, collisionVector);
				if (ev == GameEventType.PreventCollision) {
					if (Math.abs(collisionVector.getX()) > Math.abs(collisionVector.getY())) {
						item.getVelocity().setX(0);
					} else {
						item.getVelocity().setY(0);
					}
				} else if (ev != GameEventType.NoEvent) {
					gem.fireEvent(ev, that);
				}
				
			}
			
		}
		return rv;
	}
	private void applyGravity(Sprite item) {
		item.applyForce(m_gravityFV);
	}
	
	private void applyDrag(Sprite item) {
		m_dragFV.setX((int) Math.round(-0.5 * item.getVelocity().getX()));
		item.applyForce(m_dragFV);
	}
	
	public void applyInputForce(Sprite player, Position inputFV) {
		player.applyForce(inputFV);
		if (inputFV.getX() == 0) {
			applyDrag(player);
		}
	}
	
	public void applyForcesAndVelocities(Collection<Sprite> sprites, Map map, GameEventManager gem) {
		for(Sprite item : sprites) {
			// limit velocities, check for collisions, then apply velocities
			applyGravity(item);
			limitVelocity(item);
			checkCollisions(item, map, gem);
			item.applyVelocity();
		}
	}
}
