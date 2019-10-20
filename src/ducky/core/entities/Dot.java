package ducky.core.entities;

import ducky.Game;
import ducky.core.DInput;
import ducky.core.levels.DLevel;
import ducky.core.levels.Playable;
import ducky.core.levels.SelectionLevel;
import ducky.graphics.DRenderer;
import ducky.graphics.PImage;
import ducky.graphics.Res;

public class Dot extends DEntity {

	public static boolean taken = false;

	private PImage image = Res.SELECT_DOT;
	private Playable other;
	private float alpha = 0;
	private boolean rendInfo;
	private int life = 600;

	public Dot(SelectionLevel select, Playable level, int x, int y) {
		super(select);
		this.x = x;
		this.y = y;
		other = level;
	}

	public void update() {
		life--;
		if (life <= 0)
			deleted = true;
		alpha += 0.01;
		rendInfo = false;
		if (DInput.mx >= x && DInput.mx <= x + image.width) {
			if (DInput.my >= y && DInput.my <= y + image.height) {
				rendInfo = true;
				if (DInput.mb == 1) {
					Game.mainGame.startGame((DLevel) other);
				}
			}
		}
	}

	public void render(DRenderer r) {
		if (alpha < 1)
			r.render(image, x, y, alpha);
		else
			r.render(image, x, y);
		if (rendInfo && !taken) {
			r.render(other.getInfo(), 247, 0);
			taken = true;
		}
	}

}
