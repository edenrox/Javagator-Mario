package com.hopkins.game.mario.sound;

import java.io.File;
import java.util.HashMap;
import javax.sound.sampled.*;

public class SoundPlayer implements Runnable {
	
	public static final String PathToSounds = "C:\\Users\\Ian\\projects\\Icadev\\SideScroller\\sounds\\";
	
	private TargetDataLine m_line;
	private HashMap<String, AudioInputStream> m_ais;
	
	public SoundPlayer() {
		m_ais = new HashMap<String, AudioInputStream>();
		
		Port.Info info = Port.Info.SPEAKER;
		if (AudioSystem.isLineSupported(info)) {
			try {
				m_line = (TargetDataLine) AudioSystem.getLine(info);
				m_line.open();
			} catch (Exception ex) {
				System.err.println("Error openning audio system: " + ex.toString());
			}
		}
	}

	public void run() {
		
		
	}
	
	public void play(String path) {
		if (!m_ais.containsKey(path)) {
			 load(path);
		}
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(m_ais.get(path));
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
			m_ais.put(path, ais);
		} catch (Exception ex) {
			System.err.println("Error, could not read audio: " + path + " " + ex.toString());
		}
	}
}