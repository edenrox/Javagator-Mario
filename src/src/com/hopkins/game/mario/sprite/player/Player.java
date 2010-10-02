package com.hopkins.game.mario.sprite.player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;

import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.SpriteCache;

public class Player extends Sprite {
	public static final int BIG_HEIGHT = 28;
	private BufferedImage m_buffer;
	private BufferedImage m_clear;
	private Graphics m_bg;
	private int m_playerIndex;
	private int m_animationIndex;
	private PlayerSize m_ps;
	private boolean m_hasStar;
	private boolean m_direction;
	private boolean m_inJump;
	private boolean m_isDucking;
	private boolean m_onGround;
	private Point m_forceVector;
	
	public boolean isOnGround() {
		return m_onGround;
	}
	
	public int getPlayerIndex() { 
		return m_playerIndex;
	}
	public void setPlayerIndex(int value) { 
		m_playerIndex = value; 
	}
	public int getRenderIndex() {
		return m_playerIndex; 
	}
	public String getPlayerName() {
		return ((m_playerIndex < 1) ? "Mario" : "Luigi");
	}
	
	public boolean isDucking() {
		return m_isDucking;
	}
	public void setDucking(boolean isDucking) {
		m_isDucking = isDucking;
	}
	public boolean isSolid() {
		return false;
	}
	public PlayerSize getPlayerSize() {
		return m_ps;
	}
	public void setPlayerSize(PlayerSize ps) {
		if ((m_ps == PlayerSize.Small) && (ps != PlayerSize.Small)) {
			setSize(TILE_WIDTH, BIG_HEIGHT);
			setLocation(getX(), getY() - (BIG_HEIGHT - TILE_HEIGHT));
		} else if ((m_ps != PlayerSize.Small) && (ps == PlayerSize.Small)) {
			setSize(TILE_WIDTH, TILE_HEIGHT);
			setLocation(getX(), getY() + (BIG_HEIGHT - TILE_HEIGHT));
		}
		m_ps = ps;
		
	}
	
	public Player(int index, PlayerSize playerState) {
		m_clear = new BufferedImage(16, 32, IndexColorModel.TRANSLUCENT);
		m_buffer = new BufferedImage(16, 32, IndexColorModel.TRANSLUCENT);
		m_bg = m_buffer.getGraphics();
		m_ps = playerState;
		m_direction = false;
		m_hasStar = false;
		m_inJump = false;
		m_isDucking = false;
		m_animationIndex = 0;
		m_forceVector = new Point();
		if (m_ps == PlayerSize.Small) {
			setSize(16, 16);
		} else {
			setSize(16, BIG_HEIGHT);
		}
		
		// load sprites
		loadSprites();
	}
	
	public void applyInputForce(Point forceVector) {
		m_forceVector.x = forceVector.x;
		super.applyForce(forceVector);
	}
	
	
	private void loadSprites() {
		SpriteCache sm = SpriteCache.get();
		for(String playerName : new String[] {"Mario"}) {
			for (PlayerSize ps : PlayerSize.values()) {
				String srcPath = String.format("player/%s-%s.png", 
						playerName.toLowerCase(), ps.toString().toLowerCase());
				String rightPath = getSpritePath(playerName, ps, false); 
				String leftPath = getSpritePath(playerName, ps, true);
				
				// load the "right" facing sprite
				Image src = sm.getSprite(srcPath);
				// save the "right" facing sprite
				sm.saveSprite(rightPath, src);
				
				// build the "left" facing sprite
				int w, h;
				w = src.getWidth(null);
				h = src.getHeight(null);
				BufferedImage flipped = new BufferedImage(w, h, IndexColorModel.TRANSLUCENT);
				// loop through each sub sprite
				for (int i = 0; i < w / Sprite.TILE_WIDTH; i++) {
					int left = 0 + i * Sprite.TILE_WIDTH;
					int right = left + Sprite.TILE_WIDTH;
					// copy the sub sprite and flip it
					flipped.getGraphics().drawImage(src, 
							left, 0, right, h, 
							right, 0, left, h, null);
				}
				flipped.flush();
				// save the "left" facing sprite 
				sm.saveSprite(leftPath, flipped);
			}
		}
	}
	
	private String getSpritePath() {
		return getSpritePath(getPlayerName(), m_ps, m_direction);
	}
	
	private String getSpritePath(String playerName, PlayerSize state, boolean direction) {
		return String.format("player/%s-%s-%d.png", 
				playerName.toLowerCase(), state.toString().toLowerCase(), (direction ? 1 : 0));
	}
	
	private boolean signDifferent(int a, int b) {
		return (((a < 0) && (b > 0)) || ((a > 0) && (b < 0)));
	}
	
	public Image getImage() {
		Image src = SpriteCache.get().getSprite(getSpritePath());
		int frameIndex = 0;
		int vx = getVelocity().x;
		int vy = getVelocity().y;
		int fx = m_forceVector.x;
		if (vx != 0) {
			m_direction = (vx < 0);
		}
		if (vy < 0) {
			m_inJump = true;
		} else if (vy == 0) {
			m_inJump = false;
		}
		if (m_inJump) {
			if (m_ps == PlayerSize.Small) {
				frameIndex = 3;
			} else {
				frameIndex = 4;
			}
		} else if ((m_isDucking) && (m_ps != PlayerSize.Small)) {
			frameIndex = 5;
		} else if (signDifferent(vx, fx)) {
			if (m_ps == PlayerSize.Small) {
				frameIndex = 2;
			} else {
				frameIndex = 3;
			}
		} else if (getVelocity().getX() != 0) {
			if (m_ps == PlayerSize.Small) {
				frameIndex = (m_animationIndex % 2);
			} else {
				frameIndex = (m_animationIndex % 3);
			}
			m_animationIndex++;
		}
		
		int left = frameIndex * Sprite.TILE_WIDTH;
		int right = left + Sprite.TILE_WIDTH;
		m_buffer.setData(m_clear.getData());
		m_bg.drawImage(src,
					0, 0, getBounds().width, getBounds().height, 
					left, 0, right, getBounds().height, null);
		m_buffer.flush();
		return m_buffer;
	}
	public boolean isActive() {
		return true;
	}
	public boolean isLookingLeft() {
		return m_direction;
	}
	
	public void render(Graphics2D g, Point p, int tick) {
		g.drawImage(getImage(), p.x, p.y, null);
	}
}
