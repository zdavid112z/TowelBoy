package ducky.core.audio;

public class AudioManager {

	private static Thread player;
	private static boolean playing;
	
	public static void playMainMusic() {
		player = new Thread() {
			public void run() {
				playing = true;
				MAIN_MUSIC_INTRO.play();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while (MAIN_MUSIC_INTRO.isPlaying())
					;
				if(!playing)
					return;
				MAIN_MUSIC_LOOP.loop();
			}
		};
		player.start();
	}

	public static void stopMainMusic() {
		MAIN_MUSIC_INTRO.stop();
		MAIN_MUSIC_LOOP.stop();
		playing = false;
	}

	public static boolean isPlayingMainMusic() {
		return MAIN_MUSIC_INTRO.isPlaying() || MAIN_MUSIC_LOOP.isPlaying();
	}

	public static Audio MAIN_MUSIC_INTRO;
	public static Audio MAIN_MUSIC_LOOP;
	public static Audio SELECT_MUSIC;
	public static Audio DEAD;
	public static Audio WIN;
	public static Audio STEP;
	public static Audio ATTACK;
	public static Audio ATTACK2;
	public static Audio FLY;

	public static void load() {
		MAIN_MUSIC_LOOP = new Audio("/music/loop.wav");
		MAIN_MUSIC_INTRO = new Audio("/music/intro.wav");
		ATTACK = new Audio("/music/attack.wav");
		ATTACK2 = new Audio("/music/attack2.wav");
		WIN = new Audio("/music/win.wav");
		DEAD = new Audio("/music/dead.wav");
		STEP = new Audio("/music/step.wav");
		FLY = new Audio("/music/fly.wav");
		SELECT_MUSIC = new Audio("/music/select.wav");
	}

}
