package com.hopkins.game.mario.movement;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;
import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.sprite.*;
import com.hopkins.game.mario.sprite.player.Player;
import com.hopkins.game.mario.sprite.projectile.Fireball;
import com.hopkins.game.mario.sprite.tiles.Tile;

public class MovementManager {
	
	public static final int GRAVITY_FORCE = 1;
	
	private static MovementManager s_instance;
	private Point m_gravityFV;
	private Point m_dragFV;
	private Vector<Sprite> m_items;
	private Sprite[] m_sprites;
	private Vector<Sprite> m_active;
	private Map m_map;
	
	private MovementManager() {
		m_gravityFV = new Point(0, GRAVITY_FORCE);
		m_dragFV = new Point(0, 0);
		m_items = new Vector<Sprite>(10);
		m_sprites = new Sprite[10];
	}
	
	public static MovementManager get() {
		if (s_instance == null) {
			s_instance = new MovementManager();
		}
		return s_instance;
	}
	
	public void spawn(Sprite item) {
		m_active.add(item);
	}
	
	public void remove(Sprite item) {
		if (Tile.class.isAssignableFrom(item.getClass())) {
			m_map.remove((Tile) item);
		} else {
			// or the active sprites (mushrooms, oneups, stars)
			m_active.remove(item);
		}
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
		if ((itemB == null) || (itemA == itemB)) {
			return false;
		}
		Rectangle rectA = new Rectangle(newPosA.x, newPosA.y, itemA.getBounds().width, itemA.getBounds().height);
		Rectangle rectB = new Rectangle(itemB.getX(), itemB.getY(), itemB.getBounds().width, itemB.getBounds().height);
		
		return rectA.intersects(rectB);
	}
	
	
	private void checkCollisions(Sprite item, GameEventManager gem) {
		Point collisionVector = new Point();
		Point newpos = new Point(item.getX(), item.getY());
		Rectangle itemRect = new Rectangle(item.getBounds());
		Rectangle overlap = null;
		int magnitude = 0;
		int temp = 0;
		
		// we need to detect and correct collisions one dimension at a time
		for (int i = 0; i < 2; i++) {
		
			if (i == 0) {
				// x direction
				magnitude = item.getVelocity().x;
				collisionVector.x = magnitude;
				newpos.x += magnitude;
				itemRect.x = newpos.x;
			} else {
				// y direction
				magnitude = item.getVelocity().y;
				collisionVector.x = 0;
				collisionVector.y = magnitude;
				newpos.y += magnitude;
				itemRect.y = newpos.y;
			}
			if (magnitude != 0) {
			
				// define the search params
				int minX = newpos.x - Sprite.TILE_WIDTH * 2;
				int maxX = newpos.x + item.getBounds().width;
				m_items.clear();
				for(Tile tile : m_map.getRange(minX, maxX)) {
					m_items.add(tile);
				}
				for(Sprite sprite : m_active) {
					m_items.add(sprite);
				}
				
				// loop through the tiles found and test for collisions
				for(Sprite that : m_items) {
					if (isInCollision(item, newpos, that)) {
						if (item.getClass() == Fireball.class) {
							temp++;
						}
						GameEventType ev = that.onCollision(item, collisionVector);
						if (ev == GameEventType.PreventCollision) {
							item.onPreventCollision(that, collisionVector);
							overlap = itemRect.intersection(that.getBounds());
							if (i == 0) {
								if (magnitude > 1) {
									newpos.x -= overlap.width;
								} else {
									newpos.x += overlap.width;
								}
							} else {
								if (magnitude > 1) {
									newpos.y -= overlap.height;
								} else {
									newpos.y += overlap.height;
								}
							}
						} else if (ev != GameEventType.NoEvent) {
							gem.fireEvent(ev, that);
						}
					}
				}
			}
		}
		
		
		// move the sprite to the corrected position
		item.setLocation(newpos.x, newpos.y);
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
	
	public void applyForcesAndVelocities(GameEventManager gem) {
		m_sprites = m_active.toArray(m_sprites);
		for(Sprite item : m_sprites) {
			if (item != null) {
				// limit velocities, check for collisions, then apply velocities
				applyGravity(item);
				limitVelocity(item);
				checkCollisions(item, gem);
			}
		}
	}

	public void setActiveSprites(Vector<Sprite> active) {
		m_active = active;
	}
	public void setMap(Map map) {
		m_map = map;
	}
}
