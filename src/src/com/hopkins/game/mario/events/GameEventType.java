package com.hopkins.game.mario.events;

public enum GameEventType {
	NoEvent,PreventCollision,
	SpawnSprite,RemoveSprite,
	Collect,
	Jump,
	TouchBadGuy,StompBadGuy,Bounce,
	LevelComplete,PipeEnter,PipeExit,
	Injure, Death, Fireball
}
