package com.hopkins.game.mario.controllers;

import com.hopkins.game.mario.graphics.PausedRenderer;
import com.hopkins.game.mario.input.ButtonType;

public class PausedController extends Controller {
	
	public void init() {
		// create and set the renderer
		setRenderer(new PausedRenderer());
	}
	
	public void run() {
		// noop
	}
	
	public void onKeyRelease(ButtonType button) {
		switch (button) {
			case Start:
				setDone();
		}
	}
}
