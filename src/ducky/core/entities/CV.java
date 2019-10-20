package ducky.core.entities;

import ducky.core.audio.AudioManager;
import ducky.core.levels.DLevel;
import ducky.graphics.DRenderer;
import ducky.graphics.PImage;
import ducky.graphics.Res;

public class CV extends Mob {

	protected PImage[] standr;
	protected PImage[] standl;
	protected PImage[] walkr;
	protected PImage[] walkl;
	protected PImage flyr;
	protected PImage flyl;

	private int ticks;
	private int gravt = 0;
	private int timer = 0;
	private final int frameTime = 10;
	private final int iy;
	private int moty = 0;

	private int smokes = 15;
	private int interval = 10;
	private int color;

	public CV(DLevel level, int x, int y, int res) {
		super(level, x, y, 32, 28);
		iy = y;
		speed = 1;
		this.standr = Res.CV_STANDR[res];
		this.standl = Res.CV_STANDL[res];
		this.walkr = Res.CV_WALKR[res];
		this.walkl = Res.CV_WALKL[res];
		this.flyr = Res.CV_FLYR[res];
		this.flyl = Res.CV_FLYL[res];
		state = STATE_STAND;
		direction = DIRECTION_RIGHT;
		color = random.nextInt(0xffffff);
		timer = random.nextInt(frameTime);
	}

	public void update() {
		gravt++;
		if (dead) {
			x += direction == DIRECTION_RIGHT ? 1 : -1;
			y -= 2;
			if (gravt % interval == 0 && smokes >= 0) {
				smokes--;
				level.add(new DirParticle(level, x, y, 60, Math.toRadians(90),
						Math.toRadians(25), Res.PARTICLE_SMOKE[random
								.nextInt(Res.PARTICLE_SMOKE.length)], 0.5));
			}
			if(smokes < 0)
				deleted = true;
			return;
		}
		timer++;
		y -= moty;
		if (gravt % 5 == 0)
			moty -= 1;
		if (y > iy)
			y = iy;
		if (ticks == 0) {
			ticks = random.nextInt(200) + 100;
			int action = random.nextInt(3);
			switch (action) {
			case 0:
				state = STATE_STAND;
				break;
			case 1:
				state = STATE_WALK;
				direction = DIRECTION_RIGHT;
				break;
			case 2:
				state = STATE_WALK;
				direction = DIRECTION_LEFT;
				break;
			}
		}
		ticks--;
		if (state == STATE_WALK) {
			if (direction == DIRECTION_RIGHT) {
				x += speed;
			} else {
				x -= speed;
			}
		}
	}

	public void render(DRenderer r) {
		if (dead) {
			r.renderCV(direction == DIRECTION_RIGHT ? flyr : flyl, x, y - 20,
					color);
		} else
			r.renderCV(getFrame(timer / frameTime), x, y, color);
	}

	public PImage getFrame(int t) {
		switch (state) {
		case STATE_STAND:
			switch (direction) {
			case DIRECTION_RIGHT:
				return standr[t % 2];
			case DIRECTION_LEFT:
				return standl[t % 2];
			}
		case STATE_WALK:
			switch (direction) {
			case DIRECTION_RIGHT:
				return walkr[t % 2];
			case DIRECTION_LEFT:
				return walkl[t % 2];
			}
		}
		return null;
	}

	public void die() {
		if (dead)
			return;
		super.die();
		AudioManager.FLY.play();
	}

	public boolean damage(int damage) {
		moty = 5;
		return super.damage(damage);
	}

}
