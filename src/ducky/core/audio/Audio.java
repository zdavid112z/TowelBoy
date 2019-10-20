package ducky.core.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

	private Clip clip;
	
	public Audio(String path) {
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(path));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	    } catch(Exception ex) {
	        System.out.println("Could not find audio file " + path);
	        ex.printStackTrace();
	    }
	}
	
	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void pause() {
		clip.stop();
	}
	
	public void stop() {
		clip.stop();
		clip.setFramePosition(0);
	}
	
	public boolean isPlaying() {
		return clip.isRunning();
	}

}
