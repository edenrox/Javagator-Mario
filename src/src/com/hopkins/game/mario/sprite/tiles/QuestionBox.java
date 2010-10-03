package com.hopkins.game.mario.sprite.tiles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.movement.CollisionResponse;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;
import com.hopkins.game.mario.sprite.SpriteFactory;
import com.hopkins.game.mario.sprite.player.Player;
import com.hopkins.game.mario.sprite.player.PlayerSize;

public class QuestionBox extends Block {
	public static final int FRAMES = 4;
	
	private int m_quantity;
	private String m_contains;
	private Sprite m_creating;

	public QuestionBox(String contains, int quantity) {
		super(BlockColor.Brown);
		if (contains == "coin") {
			contains = "bounce-coin";
		}
		m_quantity = quantity;
		m_contains = contains;
		m_creating = null;
	}
	
	public String getSpriteFile() {
		if (m_quantity < 1) {
			return super.getSpriteFile();
		}
		return "tiles/block-question.png";
	}
	
	public CollisionResponse onCollision(Sprite that, Point collisionVector) {
  		if (m_quantity < 1) {
			return super.onCollision(that, collisionVector);
		}
  		if (that.getClass() == Player.class) {
			if (collisionVector.y < 0) {
				if (m_contains == "mushroom") {
					Player p = (Player) that;
					if (p.getPlayerSize() != PlayerSize.Small)
					m_contains = "flower";
				}
				// create the new item
				m_creating = SpriteFactory.create(m_contains);
				m_creating.setLocation(this.getX(), this.getY() - TILE_HEIGHT);
				GameEventManager.get().spawn(m_creating);
				m_quantity--;
			}
  		}
		
		return CollisionResponse.Block;
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		if (m_quantity < 1) {
			super.render(g,p,tick);
			return;
		}
		
		Image fire = SpriteCache.get().getSprite(getSpriteFile());
		int frame = (tick % FRAMES);
		int x = frame * TILE_WIDTH;
		int y = 0;
		
		g.drawImage(fire, p.x, p.y, p.x+TILE_WIDTH, p.y+TILE_HEIGHT, 
						x, y, x+TILE_WIDTH, y+TILE_HEIGHT, null);
		
	}

}
