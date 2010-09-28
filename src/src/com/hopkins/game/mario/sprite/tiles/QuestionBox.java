package com.hopkins.game.mario.sprite.tiles;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteFactory;

public class QuestionBox extends Block {
	private int m_quantity;
	private String m_contains;
	private Sprite m_creating;

	public QuestionBox(String contains, int quantity) {
		super(BlockColor.Brown);
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
	
	public boolean onCollision(Sprite that) {
		if (m_quantity < 1) {
			return super.onCollision(that);
		}
		
		// create the new item
		m_creating = SpriteFactory.create(m_contains);
		m_creating.getPosition().copy(this.getPosition());
		
		return true;
	}
	
	public boolean isGravityEffected() {
		return false;
	}

}
