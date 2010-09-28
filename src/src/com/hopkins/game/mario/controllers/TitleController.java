package com.hopkins.game.mario.controllers;

import com.hopkins.game.mario.graphics.TitleRenderer;
import com.hopkins.game.mario.input.ButtonType;

public class TitleController extends Controller {

	private boolean m_selected;
	private TitleRenderer m_rend;
	
	public void init() {
		m_selected = false;
		m_rend = new TitleRenderer();
		setRenderer(m_rend);
	}
	
	public void run() {
		m_rend.tick(); 
		
		if (m_selected) {
			startController("main");
			reset();
		}
	}
	
	private void reset() {
		m_rend.reset();
		m_selected = false;
	}
	
	public void onKeyRelease(ButtonType button) {
		if ((button == ButtonType.Start) && (m_rend.isIntroDone())) {
			m_selected = true;
		} else if (!m_rend.isIntroDone()) {
			m_rend.skipIntro();
		} else if ((button == ButtonType.Up) || (button == ButtonType.Down)) {
			m_rend.toggle();
		}
	}
}
