package ducky.core.entities;

import ducky.core.levels.DLevel;

public class Particle extends DEntity {

	protected int life;
	protected boolean dead;
	protected double alpha;

	public Particle(DLevel level, int x, int y, int tmin, int tmax, double alpha) {
		super(level);
		this.alpha = alpha;
		this.x = x;
		this.y = y;
		life = random.nextInt(tmax - tmin) + tmin;
		dead = false;
	}

	public Particle(DLevel level, int x, int y, int life, double alpha) {
		super(level);
		this.alpha = alpha;
		this.x = x;
		this.y = y;
		this.life = life;
		dead = false;
	}

	public void update() {
		super.update();
		life--;
		if (life <= 0)
			deleted = true;
	}

}
