package ducky.core.levels;

import ducky.Game;
import ducky.core.audio.AudioManager;
import ducky.core.entities.Mob;
import ducky.core.entities.Player;
import ducky.graphics.DRenderer;
import ducky.graphics.PImage;
import ducky.graphics.Res;

public class InteractiveLevel extends DLevel implements Playable {

	public PImage back;
	public PImage info;

	protected int curTime = 0;
	protected int timeinTicks = 0;
	protected int height;

	protected int timerOut = 0;
	protected int timeMax = 60 * 4;

	protected int dx, dy;
	protected Player player;

	public InteractiveLevel(PImage back, PImage info, int timeInSeconds,
			int height) {
		timeinTicks = timeInSeconds * 60;
		this.info = info;
		this.back = back;
		this.height = height;
		player = new Player(this, 180, Game.HEIGHT - height - 28);
		add(player);
	}

	public void update() {
		if (outOfTime()) {
			if (timerOut == 0) {
				AudioManager.stopMainMusic();
				AudioManager.DEAD.play();
			}
			timerOut++;
			if (timerOut >= timeMax)
				Game.mainGame.startGame(new SelectionLevel());
		} else {
			super.update();
			curTime++;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof Mob) {
					Mob m = (Mob) list.get(i);
					if (m.x > 400 - m.sx - 5)
						m.x = 400 - m.sx - 5;
					if (m.x < -6)
						m.x = -6;
				}
			}
			double angle = (double) curTime / (double) timeinTicks;
			angle *= 2 * Math.PI;
			angle -= Math.PI / 2.0;
			double mx, my;
			mx = Math.cos(angle);
			my = Math.sin(angle);
			double len = 24;
			dx = (int) (362 + mx * len);
			dy = (int) (42 + my * len);
		}
	}

	public void render(DRenderer r) {
		r.render(back, 0, 0);
		r.render(Res.CLOCK, 330, 10);
		r.renderLine(362, 42, dx, dy, 0, 1);
		super.render(r);
		if (outOfTime()) {
			r.renderRect(0, 0, Game.WIDTH, Game.HEIGHT, 0,
					timerOut < 60 ? (double) timerOut / 180.0 : 0.34);
			r.render(Res.OUT_OF_TIME, 100, 90,
					timerOut < 90 ? (double) timerOut / 90.0 : 1);
		}
	}

	public DamageInfo damageZone(int x, int y, int sx, int sy, int damage) {
		DamageInfo info = new DamageInfo();
		info.damaged = 0;
		info.killed = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Mob && !(list.get(i) instanceof Player)) {
				Mob m = (Mob) list.get(i);
				if (Math.abs(x - m.x) <= (sx + m.sx) / 2) {
					if (Math.abs(y - m.y) <= (sy + m.sy) / 2) {
						info.damaged++;
						if (m.damage(damage))
							info.killed++;
					}
				}
			}
		}
		return info;
	}

	public int genRandomPos() {
		return random.nextInt(400);
	}

	public boolean outOfTime() {
		return curTime >= timeinTicks;
	}

	public void attract(int x, int speed) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Mob && !(list.get(i) instanceof Player)) {
				Mob m = (Mob) list.get(i);
				if (x > m.x)
					m.x += speed;
				else
					m.x -= speed;
			}
		}
	}

	@Override
	public PImage getInfo() {
		return info;
	}
}
