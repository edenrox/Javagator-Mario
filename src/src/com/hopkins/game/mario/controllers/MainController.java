package com.hopkins.game.mario.controllers;

import com.hopkins.game.mario.graphics.MainRenderer;
import com.hopkins.game.mario.input.ButtonType;
import com.hopkins.game.mario.map.Level1;
import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.movement.MovementManager;
import com.hopkins.game.mario.sprite.Position;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;
import com.hopkins.game.mario.sprite.player.PlayerState;

public class MainController extends Controller {

	private Player m_player;
	private int m_players;
	private Map m_map;
	private boolean m_isPaused;
	private MainRenderer m_rend;
	private MovementManager m_mm;
	
	public void init() {
		m_player = new Player(0, PlayerState.Small);
		m_player.getPosition().set(40, 12 * Sprite.TileWidth);
		m_player.applyInputForce(new Position(5, 0));
		m_isPaused = false;
		m_map = (new Level1()).getMap();
		m_rend = new MainRenderer();
		m_rend.setMap(m_map);
		m_rend.getActiveSprites().add(m_player);
		setRenderer(m_rend);
		
		m_mm = new MovementManager();
		
	}
	public void run() {
		if (m_isPaused) {
			pause();
			m_isPaused = false;
		}
		if (isGameOver()) {
			setDone();
		}
		m_mm.applyVelocities(m_rend.getActiveSprites(), m_rend.getMap());
		m_rend.setPlayerPosition(m_player.getPosition());
	}
	
	public void setParams(int players) {
		m_players = players;
	}
	
	public void onKeyRelease(ButtonType button) {
		if (button == ButtonType.Start) {
			m_isPaused = true;
		}
	}
	
	private boolean isGameOver() {
		return false;
	}
	
	private void pause() {
		startController("paused");
	}
}
