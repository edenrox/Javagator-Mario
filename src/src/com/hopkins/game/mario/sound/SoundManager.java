package com.hopkins.game.mario.sound;

import com.hopkins.game.mario.events.GameEventListener;
import com.hopkins.game.mario.events.GameEventType;

public class SoundManager implements GameEventListener {
	
	private SoundPlayer m_sp;
	
	public SoundManager() {
		m_sp = new SoundPlayer();
	}

	public void onGameEvent(GameEventType ev) {
		String sound = getSoundForEvent(ev);
		if (sound != null) {
			m_sp.play(sound);
		}
	}
	
	private String getSoundForEvent(GameEventType ev) {
		switch(ev) {
			case BrickBreak:
				return "brickbreak.wav";
			case Jump:
				return "jump.wav";
			case CollectCoin:
				return "coin.wav";
			case CollectFlower:
			case CollectMushroom:
				return "powerup.wav";
			case CollectOneUp:
				return "oneup.wav";
			case PipeEnter:
			case PipeExit:
				return "pipe.wav";
			case StompBadGuy:
				return "stomp.wav";
			case SpawnPowerup:
				return "powerup_appears.wav";
			default:
				return null;
		}
	}
}
