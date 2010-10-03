package com.hopkins.game.mario.sound;

import com.hopkins.game.mario.events.GameEventListener;
import com.hopkins.game.mario.events.GameEventType;
import com.hopkins.game.mario.sprite.Sprite;
import com.hopkins.game.mario.sprite.powerups.Collectable;
import com.hopkins.game.mario.sprite.powerups.Flower;
import com.hopkins.game.mario.sprite.powerups.Mushroom;
import com.hopkins.game.mario.sprite.powerups.OneUp;
import com.hopkins.game.mario.sprite.tiles.Brick;
import com.hopkins.game.mario.sprite.tiles.Coin;

public class SoundManager implements GameEventListener {
	
	private SoundPlayer m_sp;
	
	public SoundManager() {
		m_sp = new SoundPlayer();
	}

	public void onGameEvent(GameEventType ev, Sprite target) {
		String sound = getSoundForEvent(ev, target);
		if (sound != null) {
			m_sp.play(sound);
		}
	}
	
	private String getSoundForEvent(GameEventType ev, Sprite target) {
		switch(ev) {
			case RemoveSprite:
				if (target.getClass() == Brick.class) {
					return "brickbreak.wav";
				}
				break;
			case Fireball:
				return "fireball.wav";
			case Death:
				return "death.wav";
			case Jump:
				return "jump.wav";
			case Collect:
				if (target.getClass() == Coin.class) {
					return "coin.wav";
				}
				if ((target.getClass() == Flower.class) || (target.getClass() == Mushroom.class)) {
					return "powerup.wav";
				}
				if (target.getClass() == OneUp.class) {
					return "oneup.wav";
				}
				break;
			case PipeEnter:
			case PipeExit:
				return "pipe.wav";
			case StompBadGuy:
				return "stomp.wav";
			case SpawnSprite:
				if (Collectable.class.isAssignableFrom(target.getClass())) {
					return "powerup_appears.wav";
				}
		}
		return null;
	}
}
