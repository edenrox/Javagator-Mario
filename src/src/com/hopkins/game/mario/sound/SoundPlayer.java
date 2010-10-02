package com.hopkins.game.mario.sound;

import java.io.File;
import java.util.HashMap;
import javax.sound.sampled.*;

public class SoundPlayer implements Runnable {
	
	public static final String PathToSounds = "C:\\Users\\Ian\\projects\\Icadev\\SideScroller\\sounds\\";
	
	private HashMap<String, Clip> m_clips;
	
	public SoundPlayer() {
		m_clips = new HashMap<String, Clip>();
	}

	public void run() {
		
	}
	
	public void play(String path) {
		if (!m_clips.containsKey(path)) {
			 load(path);
		}
		try {
			
			
			Clip clip = m_clips.get(path);
			if (clip.isRunning()) {
				clip.stop();
			}
			clip.setFramePosition(0);
			clip.start();
			


		} catch (Exception ex) {
			System.err.println("Error, could not play audio: " + path + " " + ex.toString());
		}
	}
	
	public void load(String path) {
		String file_path = PathToSounds + path; 
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(file_path));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			m_clips.put(path, clip);
		} catch (Exception ex) {
			System.err.println("Error, could not read audio: " + path + " " + ex.toString());
		}
	}
}
