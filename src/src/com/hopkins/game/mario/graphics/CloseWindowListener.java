package com.hopkins.game.mario.graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CloseWindowListener extends WindowAdapter {

	
	public void windowClosing(WindowEvent ev) {
		System.exit(0);
	}
}
