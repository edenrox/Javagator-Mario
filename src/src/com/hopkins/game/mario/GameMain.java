package com.hopkins.game.mario;

import java.awt.Color;
import javax.swing.JFrame;
import com.hopkins.game.mario.graphics.RenderManager;
import com.hopkins.game.mario.sprite.Position;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.player.Player;

public class GameMain {

	
	private RenderManager m_mw;
	private InputHandler m_input;
	private Position m_gravity;
	private Position m_drag;
	
	/**
	 * @param args
	 */
	public static void mainold(String[] args) {
		GameMain instance = new GameMain();
		instance.run();
	}
	
	public GameMain() {
		m_mw = new RenderManager(320, 240);
		m_input = new InputHandler();
		m_gravity = new Position(0,  InputHandler.ForceQuantity);
		m_drag = new Position();
		
		
	}

	private Position getGravityFV() {
		return m_gravity;
	}
	private Position getDragFV(Player p) {
		int dx = -1 * p.getVelocity().getX();
		if (dx < -1) {
			dx = -1;
		}
		if (dx > 1) {
			dx = 1;
		}
		m_drag.setX(dx);
		return m_drag;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				GameState gs = GameState.getCurrent();
				Player p = gs.getPlayer();
				
				// apply drag (player only)
				p.applyForce(getDragFV(p));
				
				// Move the player based on the current force vector
				
				
				for(Sprite item : gs.getActiveSprites()) {
					// apply gravity (all active sprites)
					if (item.isGravityEffected()) {
						int distance = gs.getMap().distanceFromGround(item);
						if (distance > 0) {
							// the sprite is off the ground
							item.applyForce(getGravityFV());
							int vy = item.getVelocity().getY();
							if (vy > distance) {
								item.getVelocity().setY(distance);
							}
						} else if (item.getVelocity().getY() > 0) {
							// the sprite is on the ground, stop it from falling
							item.getVelocity().setY(0);
						}
					}
					if (item == p) {
						if (m_input.isDucking()) {
							p.setDucking(true);
						} else {
							p.setDucking(false);
							Position fv = m_input.getForceVector();
							p.applyInputForce(fv);
						}
					}
					item.applyVelocity();
					System.out.println(String.format("{x: %d, y: %d}", item.getLeft(), item.getTop()));
				}
				
				m_mw.repaint();
			} catch (Exception ex) {
				
			}
		}
	}

	
	

}
