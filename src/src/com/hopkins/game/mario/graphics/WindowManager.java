package com.hopkins.game.mario.graphics;

import java.awt.Color;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class WindowManager {
	
	public static final String WINDOW_TITLE = "Javagator Mario";
	
	private JFrame m_frame;
	private ContentPanel m_contentPanel;
	
	
	public WindowManager() {
		
		// create a content panel
		m_contentPanel = new ContentPanel();
		
		// create the window
		m_frame = new JFrame(WINDOW_TITLE);
		
		// set the size and background color
		m_frame.setSize(ContentPanel.WIDTH + 10, ContentPanel.HEIGHT + 30);
		m_frame.setBackground(Color.white);
		m_frame.setContentPane(m_contentPanel);
		
		// hook up a close listener
		m_frame.addWindowListener(new CloseWindowListener());
		run();
	}
	
	public void setKeyListener(KeyListener listener) {
		m_frame.addKeyListener(listener);
	}
	
	public void setRenderer(Renderer rend) {
		m_contentPanel.setRenderer(rend);
	}
	
	public void render() {
		m_contentPanel.update();
	}
	
	public void run() {
		m_frame.setVisible(true);
	}
}
