package com.hopkins.game.mario.events;

import com.hopkins.game.mario.sprite.Sprite;

public interface GameEventListener {
	public void onGameEvent(GameEventType type, Sprite target);
}
