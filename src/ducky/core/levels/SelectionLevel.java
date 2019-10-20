package ducky.core.levels;

import java.util.Random;

import ducky.Game;
import ducky.core.DInput;
import ducky.core.entities.Dot;
import ducky.graphics.DRenderer;
import ducky.graphics.PImage;
import ducky.graphics.Res;

public class SelectionLevel extends DLevel {

	private PImage back;

	private Random random;
	private int timer = 0;
	private final int minTime = 100;
	private final int maxTime = 200;

	private int nx = 0, ny = 0;
	private int w = 245, h = 225;
	private int lineSpeed = 2;

	public SelectionLevel() {
		back = Res.SELECT_MAP;
		random = new Random();
		timer = random.nextInt(60) + 30;
	}

	public void update() {
		super.update();
		timer--;
		if (timer <= 0) {
			int select = random.nextInt(4);
			if (select == 0)
				list.add(new Dot(this, new IntLevel1(), random.nextInt(220),
						random.nextInt(200)));
			else if(select == 1)
				list.add(new Dot(this, new PlayLevel2(), random.nextInt(220),
						random.nextInt(200)));
			else if(select == 2)
				list.add(new Dot(this, new PlayLevel1(), random.nextInt(220),
						random.nextInt(200)));
			else if(select == 3)
				list.add(new Dot(this, new IntLevel2(), random.nextInt(220),
						random.nextInt(200)));
			timer = random.nextInt(maxTime - minTime) + minTime;
		}
		nextCoords();
		if(DInput.esc)
			Game.mainGame.startGame(new MenuMain());
	}

	public void render(DRenderer r) {
		Dot.taken = false;
		r.render(back, 0, 0);
		super.render(r);
		if(!Dot.taken)
			r.render(Res.SELECT_INFO, 247, 0);
		r.renderLine(122, 112, nx, ny, 0xffffff, 0.5);
	}

	private void nextCoords() {
		if (nx == 0) {
			ny += lineSpeed;
			if (ny >= h - 1)
				nx = lineSpeed;
		} else if (ny <= 0) {
			nx -= lineSpeed;
			if (nx <= 0)
				ny = lineSpeed;
		} else if (nx >= w - 1) {
			ny -= lineSpeed;
			if (ny <= 0)
				nx -= lineSpeed;
		} else {
			nx += lineSpeed;
			if (nx >= w - 1)
				ny -= lineSpeed;
		}
	}

}
