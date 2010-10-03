package com.hopkins.game.mario;

import com.hopkins.game.mario.controllers.Controller;
import com.hopkins.game.mario.controllers.ControllerFactory;
import com.hopkins.game.mario.graphics.WindowManager;
import com.hopkins.game.mario.input.KeyboardManager;

public class Mario implements Runnable {
	public static void main(String[] args) {
		Mario app = new Mario();
		app.run();
	}
	
	public Mario() {
	}
	
	public void run() {
		Controller ctl = ControllerFactory.create("title");
		KeyboardManager km = new KeyboardManager();
		WindowManager wm = new WindowManager();
		wm.setKeyListener(km);
		ctl.startup(km, wm);
	}
}
