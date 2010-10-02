package com.hopkins.game.mario.movement;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.sprite.*;
import com.hopkins.game.mario.sprite.tiles.Coin;
import com.hopkins.game.mario.sprite.tiles.Tile;

public class MovementManager {
	
	public static final int GRAVITY_FORCE = 1;
	
	private Point m_gravityFV;
	private Point m_dragFV;
	private Tile[] m_tiles;
	
	public MovementManager() {
		m_gravityFV = new Point(0, GRAVITY_FORCE);
		m_dragFV = new Point(0, 0);
		m_tiles = new Tile[10];
	}
	
	private void limitVelocity(Sprite item) {
		int maxV = item.getMaxVelocity();
		int nMaxV = -1 * maxV;
		Point v = item.getVelocity();
		if (v.getX() > maxV) {
			v.x = maxV;
		} else if (v.getX() < nMaxV) {
			v.x = nMaxV;
		}
		if (v.getY() > maxV) {
			v.y = maxV;
		} else if (v.getY() < nMaxV) {
			v.y = nMaxV;
		}
	}
	
	private boolean isInCollision(Sprite itemA, Point newPosA, Sprite itemB) {
		if (itemB == null) {
			return false;
		}
		Rectangle rectA = new Rectangle(newPosA.x, newPosA.y, itemA.getBounds().width, itemA.getBounds().height);
		Rectangle rectB = new Rectangle(itemB.getX(), itemB.getY(), itemB.getBounds().width, itemB.getBounds().height);
		
		return rectA.intersects(rectB);
	}
	
	
	private Point getCollisionVector(Rectangle rectA, Rectangle rectB) {
		Point collisionVector = new Point();
		
		// we are below, but our top is above this items bottom
		if ((rectA.y <= rectB.getMaxY()) && (rectA.getMaxY() >= rectB.getMaxY())) {
			collisionVector.y = (int) (rectB.getMaxY() - rectA.y);
		} else if ((rectA.getMaxY() >= rectB.getY()) && (rectA.y <= rectB.y)) {
			collisionVector.y = (int) (rectB.getY() - rectA.getMaxY());
		}
		if (rectA.getMaxX() < rectB.x) {
			collisionVector.x = (int) (rectB.x - rectA.getMaxX());
		} else if (rectA.x > rectB.getMaxX()) {
			collisionVector.x = (int) (rectB.getMaxX() - rectA.x);
		}
		return collisionVector;
	}
	
	private Point getNewPoint(Sprite item) {
		Point newpos = new Point();
		newpos.x = item.getX();
		newpos.y = item.getY();
		newpos.x += item.getVelocity().x;
		newpos.y += item.getVelocity().y;
		return newpos;
	}
	
	private boolean checkCollisions(Sprite item, Map map, GameEventManager gem) {
		boolean rv = false;
		Point collisionVector = null;
		Point newpos = getNewPoint(item);
		
		int minX = newpos.x - Sprite.TILE_WIDTH * 2;
		int maxX = newpos.x + item.getBounds().width;
		
		m_tiles = map.getRange(minX, maxX).toArray(m_tiles);
		for(Tile that : m_tiles) {
			if (isInCollision(item, newpos, that)) {
				collisionVector = getCollisionVector(item.getBounds(), that.getBounds());
				if (that.getClass() == Coin.class) {
					System.err.println(String.format("CV: {x: %d, y: %d}", collisionVector.x, collisionVector.y));
				}
				GameEventType ev = that.onCollision(item, collisionVector);
				if (ev == GameEventType.PreventCollision) {
					if (Math.abs(collisionVector.x) > Math.abs(collisionVector.y)) {
						item.getVelocity().x = 0;
					} else {
						item.getVelocity().y = 0;
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
		m_dragFV.x = (int) Math.round(-0.6 * item.getVelocity().x);
		item.applyForce(m_dragFV);
	}
	
	public void applyInputForce(Sprite player, Point inputFV) {
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
