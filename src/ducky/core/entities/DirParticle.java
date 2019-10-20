package ducky.core.entities;

import java.util.ArrayList;
import java.util.List;

import ducky.core.levels.DLevel;
import ducky.graphics.DRenderer;
import ducky.graphics.PImage;

public class DirParticle extends Particle {

	protected double dir;
	protected double motx, moty;
	protected double px, py;
	protected PImage image;

	public DirParticle(DLevel level, int x, int y, int life, double dir,
			double maxdev, PImage image, double alpha) {
		super(level, x, y, life, alpha);
		this.dir = dir + maxdev * ((double) random.nextFloat() * 2.0 - 1.0);
		this.image = image;
		motx = Math.cos(dir);
		moty = Math.sin(dir);
		px = x;
		py = y;
	}

	public static List<DirParticle> spawn(DLevel level, int num, PImage image,
			int x, int y, int tmin, int tmax, double dir, double dev,
			double minAlpha, double maxAlpha) {
		List<DirParticle> list = new ArrayList<DirParticle>();
		for (int i = 0; i < num; i++)
			list.add(new DirParticle(level, x, y, random.nextInt(tmax - tmin)
					+ tmin, dir, dev, image, random.nextDouble()
					* (maxAlpha - minAlpha) + minAlpha));
		return list;
	}

	public static List<DirParticle> spawn(DLevel level, int num,
			PImage[] image, int x, int y, int tmin, int tmax, double dir,
			double dev, double minAlpha, double maxAlpha) {
		List<DirParticle> list = new ArrayList<DirParticle>();
		for (int i = 0; i < num; i++)
			list.add(new DirParticle(level, x, y, random.nextInt(tmax - tmin)
					+ tmin, dir, dev, image[random.nextInt(image.length)],
					random.nextDouble() * (maxAlpha - minAlpha) + minAlpha));
		return list;
	}

	public void update() {
		if (life <= 0)
			return;
		life--;
		px += motx;
		py += moty;
		x = (int) px;
		y = (int) py;
	}

	public void render(DRenderer r) {
		if (life <= 0)
			return;
		r.render(image, (int) px, (int) py, alpha);
	}

}
