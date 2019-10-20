package ducky.core.entities;

import ducky.core.levels.DLevel;
import ducky.graphics.DRenderer;

public class Mob extends DEntity {

	public static final int STATE_STAND = 0;
	public static final int STATE_WALK = 1;
	public static final int STATE_RUN = 2;
	public static final int STATE_JUMP = 7;
	public static final int STATE_DUCK = 8;
	public static final int STATE_ATTACK = 3;
	public static final int STATE_ATTACK1 = 4;
	public static final int STATE_ATTACK2 = 5;
	public static final int STATE_ATTACK3 = 6;

	public static final int DIRECTION_LEFT = 10;
	public static final int DIRECTION_RIGHT = 11;

	public final int sx, sy;
	protected int state;
	protected int direction;
	protected int speed;
	protected int health;
	public boolean dead;

	public Mob(DLevel level, int x, int y, int sx, int sy) {
		super(level);
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
		health = 100;
		dead = false;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(DRenderer renderer) {

	}

	public void die() {
		dead = true;
	}

	public boolean damage(int damage) {
		health -= damage;
		if (health <= 0) {
			die();
			return true;
		}
		return false;
	}

}
