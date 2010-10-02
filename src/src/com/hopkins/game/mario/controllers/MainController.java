package com.hopkins.game.mario.controllers;

import java.awt.Point;

import com.hopkins.game.mario.events.GameEventListener;
import com.hopkins.game.mario.events.GameEventManager;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.graphics.MainRenderer;
import com.hopkins.game.mario.input.ButtonType;
import com.hopkins.game.mario.input.KeyboardManager;
import com.hopkins.game.mario.map.Level1;
import com.hopkins.game.mario.map.Map;
import com.hopkins.game.mario.movement.MovementManager;
import com.hopkins.game.mario.sound.SoundManager;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;
import com.hopkins.game.mario.sprite.player.PlayerSize;
import com.hopkins.game.mario.sprite.tiles.Coin;
import com.hopkins.game.mario.sprite.tiles.Tile;
import com.hopkins.game.mario.state.PlayerState;

public class MainController extends Controller implements GameEventListener {

	public static final int INPUT_FORCE_MAGNITUDE = 5;
	
	private int m_playerIndex;
	private Player m_player;
	private int m_players;
	private Map m_map;
	private boolean m_isPaused;
	private Point m_inputForceVector;
	private MainRenderer m_rend;
	private MovementManager m_mm;
	private SoundManager m_sm;
	private GameEventManager m_gem;
	private PlayerState[] m_states;
	
	public void init() {
		m_playerIndex = 0;
		m_states = new PlayerState[] {new PlayerState(0), new PlayerState(1) };
		m_player = new Player(m_playerIndex, PlayerSize.Small);
		m_player.setLocation(40, 12 * Sprite.TILE_WIDTH);
		m_isPaused = false;
		m_map = (new Level1()).getMap();
		m_rend = new MainRenderer();
		m_rend.setMap(m_map);
		m_rend.setState(m_states[m_playerIndex]);
		m_rend.getActiveSprites().add(m_player);
		setRenderer(m_rend);
		m_inputForceVector = new Point();
		
		initManagers();
	}
	
	private void initManagers() {
		
		
		m_mm = new MovementManager();
		m_sm = new SoundManager();
		
		m_gem = new GameEventManager();
		m_gem.addListener(m_sm);
		m_gem.addListener(this);
		m_gem.addListener(m_states[m_playerIndex]);
		m_sm.onGameEvent(GameEventType.Collect, new Coin());
	}
	
	public void run() {
		if (m_isPaused) {
			pause();
			m_isPaused = false;
		}
		if (isGameOver()) {
			setDone();
		}
		m_mm.applyInputForce(m_player, getInputForceVector());
		m_mm.applyForcesAndVelocities(m_rend.getActiveSprites(), m_rend.getMap(), m_gem);
		m_rend.setPlayerPosition(m_player.getX(), m_player.getY());
	}
	
	private Point getInputForceVector() {
		KeyboardManager km = getKeyboardManager();
		if (km.isPressed(ButtonType.Left)) {
			m_inputForceVector.x = -1 * INPUT_FORCE_MAGNITUDE;
		} else if (km.isPressed(ButtonType.Right)) {
			m_inputForceVector.x = INPUT_FORCE_MAGNITUDE;
		} else {
			m_inputForceVector.x = 0;
		}
		if (km.isPressed(ButtonType.A)) {
			m_inputForceVector.y = -1 * INPUT_FORCE_MAGNITUDE;
		} else {
			m_inputForceVector.y = 0;
		}
		return m_inputForceVector;
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

	public void onGameEvent(GameEventType type, Sprite target) {
		
		switch (type)
		{
			case Collect:
				onCollect(target);
				break;
			case Death:
				onDeath(target);
				break;
		}
	}
	
	private void onDeath(Sprite target) {
		// do the death animation here
	}
	
	private void onCollect(Sprite target) {
		// remove the item collected from either the map (flowers/coins)
		if (Tile.class.isAssignableFrom(target.getClass())) {
			m_map.remove((Tile) target);
		}
		// or the active sprites (mushrooms, oneups, stars)
		m_rend.getActiveSprites().remove(target);
	}
}