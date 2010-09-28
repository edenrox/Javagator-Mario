package com.hopkins.game.mario.utils;

public class DelayHelper {
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			
		}
	}
}
