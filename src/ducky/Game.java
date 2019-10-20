package ducky;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import ducky.core.DInput;
import ducky.core.audio.AudioManager;
import ducky.core.levels.DLevel;
import ducky.core.levels.InfoLevel;
import ducky.core.levels.MenuAbout;
import ducky.core.levels.MenuMain;
import ducky.core.levels.SelectionLevel;
import ducky.graphics.DRenderer;
import ducky.graphics.Res;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public final static int WIDTH = 400;
	public final static int HEIGHT = WIDTH / 16 * 9;
	public final static float SCALE = 2.5f;
	public final static String TITLE = "Towel Boy";
	public final static int UPS = 60;

	public static Game mainGame;

	private Thread thread;
	private JFrame frame;
	private DRenderer renderer;
	private DLevel level;
	private DInput input;
	private boolean running;

	public static void main(String[] args) {
		mainGame = new Game();
		mainGame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGame.frame.add(mainGame);
		mainGame.frame.pack();
		mainGame.frame.setTitle(TITLE);
		mainGame.frame.setLocationRelativeTo(null);
		mainGame.frame.setVisible(true);
		mainGame.frame.setEnabled(true);
		mainGame.start();
	}

	public Game() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension((int) ((float) WIDTH * SCALE),
				(int) ((float) HEIGHT * SCALE)));
		input = new DInput();
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		renderer = new DRenderer(WIDTH, HEIGHT);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void closeWindow() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

	public void init() {
		Res.load();
		AudioManager.load();
		level = new MenuMain();
		startGame(level);
	}

	public void quit() {

	}

	public void startGame(DLevel level) {
		this.level = level;
		if (level instanceof MenuMain || level instanceof MenuAbout || level instanceof InfoLevel) {
			AudioManager.SELECT_MUSIC.stop();
			if(!AudioManager.isPlayingMainMusic())
				AudioManager.playMainMusic();
		} else if (level instanceof SelectionLevel) {
			while (AudioManager.isPlayingMainMusic())
				AudioManager.stopMainMusic();
			AudioManager.SELECT_MUSIC.loop();
		} else {
			while (AudioManager.SELECT_MUSIC.isPlaying())
				AudioManager.SELECT_MUSIC.stop();
			AudioManager.playMainMusic();
		}
	}

	public void endGame() {
		level = new SelectionLevel();
	}

	public void update() {
		input.update(getWidth(), getHeight());
		level.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		renderer.begin(g, getWidth(), getHeight());
		renderer.render(level);
		renderer.end();
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		init();
		final long interval = 1000 / UPS;
		int f = 0, u = 0;
		long time = System.currentTimeMillis();
		while (running) {
			render();
			f++;
			if (System.currentTimeMillis() - time >= interval) {
				time += interval;
				update();
				u++;
				if (u == UPS) {
					System.out.println("FPS : " + f + " , UPS : " + u);
					f = 0;
					u = 0;
				}
			}
		}
		quit();
	}

}
