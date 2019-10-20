package ducky.core.entities;

import java.util.ArrayList;
import java.util.List;

import ducky.core.levels.DLevel;
import ducky.graphics.DRenderer;
import ducky.graphics.PImage;

public class RandParticle extends Particle {

	protected int timer = 0;
	protected double px, py;
	protected double motx, moty;
	protected PImage image;

	public RandParticle(DLevel level, PImage image, int x, int y, int tmin,
			int tmax, double alpha) {
		super(level, x, y, tmin, tmax, alpha);
		this.image = image;
		motx = random.nextGaussian();
		moty = random.nextGaussian();
		px = x;
		py = y;
	}

	public static List<RandParticle> spawn(DLevel level, int num, PImage image, int x, int y,
			int tmin, int tmax, double minAlpha, double maxAlpha) {
		List<RandParticle> list = new ArrayList<RandParticle>();
		for (int i = 0; i < num; i++)
			list.add(new RandParticle(level, image, x, y, tmin, tmax, random.nextDouble() * (maxAlpha - minAlpha) + minAlpha));
		return list;
	}

	public static List<RandParticle> spawn(DLevel level, int num, PImage[] image, int x,
			int y, int tmin, int tmax, double minAlpha, double maxAlpha) {
		List<RandParticle> list = new ArrayList<RandParticle>();
		for (int i = 0; i < num; i++)
			list.add(new RandParticle(level, image[random.nextInt(image.length)], x,
					y, tmin, tmax, random.nextDouble() * (maxAlpha - minAlpha) + minAlpha));
		return list;
	}

	public void update() {
		if (dead)
			return;
		super.update();
		timer++;
		px += motx;
		py += moty;
	}

	public void render(DRenderer r) {
		if (dead)
			return;
		r.render(image, (int)px, (int)py, alpha);
	}

}
