package com.hopkins.game.mario.controllers;

import com.hopkins.game.mario.graphics.Renderer;
import com.hopkins.game.mario.graphics.WindowManager;
import com.hopkins.game.mario.input.ButtonType;
import com.hopkins.game.mario.input.KeyReleaseHandler;
import com.hopkins.game.mario.input.KeyboardManager;
import com.hopkins.game.mario.utils.DelayHelper;

public abstract class Controller implements Runnable, KeyReleaseHandler {
	
	private KeyboardManager m_km;
	private WindowManager m_wm;
	private Renderer m_rend;
	private boolean m_done;
	
	public KeyboardManager getKeyboardManager() {
		return m_km;
	}
	
	public WindowManager getWindowManager() {
		return m_wm;
	}
	
	public void setDone() {
		m_done = true;
	}
	
	public void startController(String name) {
		// startup the new controller
		Controller item = ControllerFactory.create(name);
		item.startup(m_km, m_wm);
		
		// restore the current controller (renderer and keyboard handler)
		setRenderer(m_rend);
		m_km.setReleasedHandler(this);
	}
	
	public final void startup(KeyboardManager km, WindowManager wm) {
		m_km = km;
		m_wm = wm;
		m_km.setReleasedHandler(this);

		// run the controller
		execute();
	}
	
	public void execute() {
		init();
		
		while (!m_done) {
			run();
			render();
			DelayHelper.sleep(50);
		}
	}
	
	public void init() {
		
	}
	
	public abstract void run();
	
	public void onKeyRelease(ButtonType button) {
		// noop by default
	}
	
	public void setRenderer(Renderer rend) {
		m_rend = rend;
		m_wm.setRenderer(rend);
	}
	
	public void render() {
		m_wm.render();
	}
}
