package ducky.core.levels;

import ducky.Game;
import ducky.core.audio.AudioManager;
import ducky.core.entities.Mob;
import ducky.core.entities.Player;
import ducky.graphics.DRenderer;
import ducky.graphics.PImage;
import ducky.graphics.Res;

public class PlayLevel extends DLevel implements Playable {

	public final PImage back, info, eotlr, eotll;
	public final int height, dist, roff, loff, tiles;

	public final static int distance = 100;

	protected int offset = 0;
	protected int curTime = 0;
	protected int timeinTicks = 0;
	protected int dx, dy;

	private int timerOut = 0;
	private int timeMax = 60 * 4;

	protected Player player;

	public PlayLevel(PImage back, int height, PImage info, PImage eotlr,
			PImage eotll, int distBothWays, int roff, int loff, int timeInSecs) {
		this.back = back;
		this.info = info;
		this.height = height;
		this.eotll = eotll;
		this.eotlr = eotlr;
		this.roff = roff;
		this.loff = loff;
		tiles = distBothWays;
		dist = distBothWays * back.width;
		timeinTicks = timeInSecs * Game.UPS;
		player = new Player(this, 0, Game.HEIGHT - height - 28);
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
			int d = player.x - offset;
			if (d < distance)
				offset = player.x - distance;
			if (d > Game.WIDTH - distance)
				offset = player.x - Game.WIDTH + distance;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof Mob) {
					Mob m = (Mob) list.get(i);
					if (m.x > dist + loff - m.sx - 12)
						m.x = dist + loff - m.sx - 12;
					if (m.x < -dist + eotlr.width - roff)
						m.x = -dist + eotlr.width - roff;
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
		for (int i = -tiles + 1; i < tiles; i++) {
			r.render(back, i * back.width - offset, 0);
		}
		r.render(eotll, -dist - offset, 0);
		r.render(eotlr, dist - offset, 0);
		r.render(Res.CLOCK, 330, 10);
		r.renderLine(362, 42, dx, dy, 0, 1);
		r.setXOffset(offset);
		super.render(r);
		r.setXOffset(0);
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
		return random.nextInt(2 * (dist - 100)) - (dist - 100);
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
