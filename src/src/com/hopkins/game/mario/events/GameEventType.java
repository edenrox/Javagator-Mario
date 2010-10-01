package com.hopkins.game.mario.events;

public enum GameEventType {
	NoEvent,PreventCollision,
	SpawnPowerup,BrickBreak,
	Collect,
	Jump,
	TouchBadGuy,StompBadGuy,Bounce,
	LevelComplete,PipeEnter,PipeExit,
	Death,
}
