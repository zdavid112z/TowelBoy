package ducky.core.levels;

import ducky.Game;
import ducky.core.DInput;
import ducky.graphics.DRenderer;
import ducky.graphics.Res;

public class MenuMain extends DLevel {

	private class Rect {
		public Rect(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.sx = w + x;
			this.sy = h + y;
		}

		public int x, y, sx, sy;
	}

	private Rect playButton;
	private Rect aboutButton;
	private Rect helpButton;
	private Rect quitButton;

	private boolean inside(Rect r, int x, int y) {
		return r.x <= x && x <= r.sx && r.y <= y && y <= r.sy;
	}

	public MenuMain() {
		playButton = new Rect(16, 80, 56, 32);
		aboutButton = new Rect(16, 117, 62, 32);
		helpButton = new Rect(19, 154, 77, 26);
		quitButton = new Rect(18, 191, 60, 31);
	}

	public void update() {
		if (DInput.mb == 1) {
			int mx = DInput.mx;
			int my = DInput.my;
			if (inside(playButton, mx, my)) {
				Game.mainGame.startGame(new InfoLevel());
			} else if (inside(aboutButton, mx, my)) {
				Game.mainGame.startGame(new MenuAbout(Res.MENU_HELP));
			} else if (inside(helpButton, mx, my)) {
				Game.mainGame.startGame(new MenuAbout(Res.MENU_ABOUT));
			} else if (inside(quitButton, mx, my)) {
				Game.mainGame.closeWindow();
			}
		}
	}

	public void render(DRenderer r) {
		r.render(Res.MENU_MAIN, 0, 0);
	}

}
