package com.hopkins.game.mario.controllers;

public class ControllerFactory {
	
	public static Controller create(String name) {
		if (name.equals("paused")) {
			return new PausedController();
		}
		if (name.equals("main")) {
			return new MainController();
		}
		if (name.equals("title")) {
			return new TitleController();
		}
		
		throw new RuntimeException("Unknown controller: " + name);
	}
}
