package com.hopkins.game.mario.map;

import java.util.Arrays;

import com.hopkins.game.mario.sprite.*;
import com.hopkins.game.mario.sprite.player.Player;
import com.hopkins.game.mario.sprite.player.PlayerState;
import com.hopkins.game.mario.sprite.tiles.*;

public class Level1 {
	private Map m_map;
	
	public Map getMap() {
		return m_map;
	}
	
	public Level1() {
		m_map = new Map();
		
		// place the player
		Player item = new Player(0, PlayerState.Small);
		item.getPosition().set(6 * Sprite.TileWidth, 12 * Sprite.TileHeight);
		m_map.addPlayer(item);
		
		buildGround();
		
		buildBackground();
		
		buildArea1();
		
		buildArea2();
		
		buildArea3();
	}
	
	private void buildBackground() {
		for (int i = 0; i < 5; i++) {
			int x = i * 48;
			m_map.create("cloud", x + 3, 4);
			m_map.create("tree", x + 12, 12);
			m_map.create("tree", x + 13, 12);
			m_map.create("tree", x + 14, 12);
			m_map.create("cloud", x + 20, 3);
			
			m_map.create("tree", x + 24, 12);
			m_map.create("cloud", x + 28, 4);
			m_map.create("cloud", x + 29, 4);
			m_map.create("cloud", x + 30, 4);
			m_map.create("cloud", x + 37, 3);
			m_map.create("cloud", x + 38, 3);
			m_map.create("tree", x + 42, 12);
			m_map.create("tree", x + 43, 12);
		}
	}
	
	private void buildArea1() {
		
		addQuestionBlock(16, 9, "coin");
		m_map.create("brick", 20, 9);
		addQuestionBlock(21, 9, "mushroom");
		addQuestionBlock(22, 5, "coin");
		m_map.create("brick", 22, 9);
		addQuestionBlock(23, 9, "coin");
		m_map.create("brick", 24, 9);
		
		addPipe(28, 11, 2);
		addPipe(38, 10, 3);
		
		
		addPipe(46, 9, 4);
		addPipe(57, 9, 4);
		addQuestionBlock(64, 8, "oneup");
	}
	
	private void buildArea2() {
		m_map.create("brick", 77, 9);
		addQuestionBlock(78, 9, "mushroom");
		m_map.create("brick", 79, 9);
		
		for(int i = 0; i < 8; i++) {
			m_map.add(SpriteFactory.create("brick"), 80 + i, 5);
		}
		m_map.create("brick", 91, 5);
		m_map.create("brick", 92, 5);
		m_map.create("brick", 93, 5);
		addQuestionBlock(94, 5, "coin");
		addQuestionBlock(94, 9, "coin", 12);
		m_map.create("brick", 100, 9);
		addQuestionBlock(101, 9, "star");
		addQuestionBlock(106, 9, "coin");
		addQuestionBlock(109, 9, "coin");
		addQuestionBlock(109, 5, "mushroom");
		addQuestionBlock(112, 9, "coin");
		m_map.create("brick", 118, 9);
		m_map.create("brick", 121, 5);
		m_map.create("brick", 122, 5);
		m_map.create("brick", 123, 5);
		m_map.create("brick", 128, 5);
		addQuestionBlock(129, 5, "coin");
		m_map.create("brick", 129, 9);
		addQuestionBlock(130, 5, "coin");
		m_map.create("brick", 130, 9);
		m_map.create("brick", 131, 5);
	}
	
	private void buildArea3() {
		blockPile(134, 1);
		blockPile(135, 2);
		blockPile(136, 3);
		blockPile(137, 4);
		
		blockPile(140, 4);
		blockPile(141, 3);
		blockPile(142, 2);
		blockPile(143, 1);
		
		blockPile(148, 1);
		blockPile(149, 2);
		blockPile(150, 3);
		blockPile(151, 4);
		blockPile(152, 4);
		
		blockPile(155, 4);
		blockPile(156, 3);
		blockPile(157, 2);
		blockPile(158, 1);
		
		addPipe(163, 11, 2);
		
		m_map.create("brick", 168, 9);
		m_map.create("brick", 169, 9);
		addQuestionBlock(170, 9, "coin");
		m_map.create("brick", 171, 9);
		
		addPipe(179, 11, 2);
		
		for(int i = 0; i < 9; i++) {
			blockPile(181 + i, Math.min(8, i+1));
		}
		
		blockPile(198, 1);
	}
	
	private void blockPile(int x, int height) {
		for (int i = 0; i < height; i++) {
			m_map.create("block", x, 12 - i);
		}
	}
	

	
	private void addPipe(int x, int y, int height) {
		Pipe item = new Pipe(height);
		m_map.add(item, x, y);
	}
	
	private void addQuestionBlock(int x, int y, String type) {
		addQuestionBlock(x,y, type, 1);
	}
	
	private void addQuestionBlock(int x, int y, String type, int qty) {
		QuestionBox item = new QuestionBox(type, qty);
		m_map.add(item, x, y);
	}
	
	
	
	private void buildGround() {
		int[] skip = new int[] {67,68,69,70, 86, 87, 88, 153, 154};
		Block item;
		for(int i = 0; i < 212; i++) {
			if (Arrays.binarySearch(skip, i) < 1) {
				for (int j = 13; j < 15; j++) {
					item = new Block(BlockColor.Wood);
					m_map.add(item, i, j);
				}
			}
			
		}
	}
	
	
}
