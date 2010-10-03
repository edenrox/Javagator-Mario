package com.hopkins.game.mario.controllers;

import java.awt.Point;
import java.util.Vector;

import com.hopkins.game.mario.events.*;
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
import com.hopkins.game.mario.sprite.powerups.Flower;
import com.hopkins.game.mario.sprite.powerups.Mushroom;
import com.hopkins.game.mario.sprite.projectile.Fireball;
import com.hopkins.game.mario.sprite.tiles.Tile;
import com.hopkins.game.mario.state.PlayerState;

public class MainController extends Controller implements GameEventListener {

	public static final int INPUT_FORCE_MAGNITUDE = 5;
	
	private int m_playerIndex;
	private Player m_player;
	private int m_players;
	private Map m_map;
	private boolean m_isPaused;
	private boolean m_fire;
	private Point m_inputForceVector;
	private MainRenderer m_rend;
	private MovementManager m_mm;
	private SoundManager m_sm;
	private GameEventManager m_gem;
	private PlayerState[] m_states;
	private Vector<Sprite> m_active;
	private Vector<Sprite> m_toremove;
	
	public void init() {
		m_playerIndex = 0;
		m_states = new PlayerState[] {new PlayerState(0), new PlayerState(1) };
		m_player = new Player(m_playerIndex, PlayerSize.Fire);
		m_player.setLocation(40, 10 * Sprite.TILE_WIDTH);
		m_isPaused = false;
		m_fire = false;
		m_map = (new Level1()).getMap();
		m_inputForceVector = new Point();
		
		
		// setup the active sprites
		m_active = new Vector<Sprite>();
		m_active.add(m_player);
		
		// setup the remove list
		m_toremove = new Vector<Sprite>();
		
		// setup the renderer
		m_rend = new MainRenderer();
		m_rend.setMap(m_map);
		m_rend.setState(m_states[m_playerIndex]);
		m_rend.setActiveSprites(m_active);
		setRenderer(m_rend);
		
		// setup the managers
		initManagers();
	}
	
	private void initManagers() {
		
		// setup the movement manager
		m_mm = new MovementManager();
		
		// setup the sound manager
		m_sm = new SoundManager();
		
		// setup the game event manager and its listeners
		m_gem = GameEventManager.get();
		m_gem.addListener(m_sm);
		m_gem.addListener(this);
		m_gem.addListener(m_states[m_playerIndex]);
	}
	
	public void run() {
		if (m_isPaused) {
			pause();
			m_isPaused = false;
		}
		if (isGameOver()) {
			setDone();
		}
		if (m_fire) {
			if (m_player.getPlayerSize() == PlayerSize.Fire) {
				Fireball fb = new Fireball(m_player.isLookingLeft());
				fb.setLocation(m_player.getX(), m_player.getY() + 6);
				onSpawn(fb);
				m_gem.fireEvent(GameEventType.Fireball, fb);
			}
			m_fire = false;
		}
		m_mm.applyInputForce(m_player, getInputForceVector());
		m_mm.applyForcesAndVelocities(m_map, m_active);
		m_rend.setPlayerPosition(m_player.getX(), m_player.getY());
		removeSprites();
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
		if (button == ButtonType.B) {
			m_fire = true;
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
			case SpawnSprite:
				onSpawn(target);
				break;
			case RemoveSprite:
				onRemove(target);
				break;
		}
	}
	
	private void onDeath(Sprite target) {
		// do the death animation here
	}
	
	private void onCollect(Sprite target) {
		// remove the item collected from either the map (flowers/coins)
		onRemove(target);
		
		if (target.getClass() == Mushroom.class) {
			m_player.setPlayerSize(PlayerSize.Big);
		} else if (target.getClass() == Flower.class) {
			m_player.setPlayerSize(PlayerSize.Fire);
		}
	}
	
	public void onSpawn(Sprite target) {
		m_active.add(target);
	}
	
	public void onRemove(Sprite item) {
		m_toremove.add(item);
	}
	
	private void removeSprites() {
		for(Sprite item : m_toremove) {
			if (Tile.class.isAssignableFrom(item.getClass())) {
				m_map.remove((Tile) item);
			} else {
				// or the active sprites (mushrooms, oneups, stars)
				m_active.remove(item);
			}
		}
		m_toremove.clear();
	}
}