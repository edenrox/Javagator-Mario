package com.hopkins.game.mario.controllers;

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
import com.hopkins.game.mario.sprite.Position;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;
import com.hopkins.game.mario.sprite.player.PlayerState;
import com.hopkins.game.mario.sprite.powerups.Coin;

public class MainController extends Controller implements GameEventListener {

	public static final int INPUT_FORCE_MAGNITUDE = 5;
	
	private Player m_player;
	private int m_players;
	private Map m_map;
	private boolean m_isPaused;
	private Position m_inputForceVector;
	private MainRenderer m_rend;
	private MovementManager m_mm;
	private SoundManager m_sm;
	private GameEventManager m_gem;
	
	public void init() {
		m_player = new Player(0, PlayerState.Small);
		m_player.getPosition().set(40, 12 * Sprite.TileWidth);
		m_isPaused = false;
		m_map = (new Level1()).getMap();
		m_rend = new MainRenderer();
		m_rend.setMap(m_map);
		m_rend.getActiveSprites().add(m_player);
		setRenderer(m_rend);
		m_inputForceVector = new Position();
		
		initManagers();
	}
	
	private void initManagers() {
		m_gem = new GameEventManager();
		
		m_mm = new MovementManager();
		m_sm = new SoundManager();
		m_gem.addListener(m_sm);
		m_gem.addListener(this);
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
		m_rend.setPlayerPosition(m_player.getPosition());
	}
	
	private Position getInputForceVector() {
		KeyboardManager km = getKeyboardManager();
		if (km.isPressed(ButtonType.Left)) {
			m_inputForceVector.setX(-1 * INPUT_FORCE_MAGNITUDE);
		} else if (km.isPressed(ButtonType.Right)) {
			m_inputForceVector.setX(INPUT_FORCE_MAGNITUDE);
		} else {
			m_inputForceVector.setX(0);
		}
		if (km.isPressed(ButtonType.A)) {
			m_inputForceVector.setY(-1 * INPUT_FORCE_MAGNITUDE);
		} else {
			m_inputForceVector.setY(0);
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
		if (type == GameEventType.Collect) {
			m_map.remove(target);
		}
	}
}
