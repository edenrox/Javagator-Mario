package com.hopkins.game.mario.movement;

import java.util.Collection;

import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.sprite.Position;
import com.hopkins.game.mario.sprite.Sprite;

public class MovementManager {
	
	public MovementManager() {

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
	private void checkCollisions(Sprite item, Map map) {
		boolean inCollision = false;
		Position newpos = new Position();
		newpos.copy(item.getPosition());
		newpos.add(item.getVelocity());
		
		
		
		
		
	}
	
	public void applyVelocities(Collection<Sprite> sprites, Map map) {
		for(Sprite item : sprites) {
			limitVelocity(item);
			checkCollisions(item, map);
			item.applyVelocity();
		}
	}
}
