package com.hopkins.game.mario.movement;

import java.awt.Rectangle;
import java.util.Collection;
import java.util.Vector;

import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.sprite.Position;
import com.hopkins.game.mario.sprite.Size;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCallback;

public class MovementManager {
	
	public static final int GRAVITY_FORCE = 5;
	
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
		if (itemA.getTop() < itemB.getBottom()) {
			collisionVector.setY(1);
		} else if (itemA.getBottom() > itemB.getTop()) {
			collisionVector.setY(-1);
		}
		if (itemA.getRight() < itemB.getLeft()) {
			collisionVector.setX(1);
		} else if (itemA.getLeft() > itemB.getRight()) {
			collisionVector.setX(-1);
		}
		return collisionVector;
	}
	
	private void checkCollisions(Sprite item, Map map) {
		Position collisionVector = null;
		Position newpos = new Position();
		newpos.copy(item.getPosition());
		newpos.add(item.getVelocity());
		
		int minX = newpos.getX() - Sprite.TileWidth * 2;
		int maxX = newpos.getX() + item.getSize().getWidth();
		
		Vector<Sprite> sprites = map.getRange(minX, maxX);
		for(Sprite that : sprites) {
			if (that.isSolid()) {
				if (isInCollision(item, newpos, that)) {
					collisionVector = getCollisionVector(item, that);
					
				}
			}
		}
	}
	private void applyGravity(Sprite item) {
		item.applyForce(m_gravityFV);
	}
	
	private void applyDrag(Sprite item) {
		m_dragFV.setX((int) (-0.5 * item.getVelocity().getX()));
		item.applyForce(m_dragFV);
	}
	
	public void applyInputForce(Sprite player, Position inputFV) {
		player.applyForce(inputFV);
		if (inputFV.getX() == 0) {
			applyDrag(player);
		}
	}
	
	public void applyForcesAndVelocities(Collection<Sprite> sprites, Map map) {
		for(Sprite item : sprites) {
			applyGravity(item);
			limitVelocity(item);
			checkCollisions(item, map);
			item.applyVelocity();
		}
	}
}
