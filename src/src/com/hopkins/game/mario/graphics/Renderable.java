package com.hopkins.game.mario.graphics;

import java.awt.Graphics2D;
import java.awt.Point;

public interface Renderable {
	public void render(Graphics2D g, Point position, int tick);
}
