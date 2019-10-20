package ducky.core.entities;

import java.util.Random;

import ducky.core.levels.DLevel;
import ducky.graphics.DRenderer;

public class DEntity {

	public int x, y;
	public boolean deleted;
	protected DLevel level;
	protected static Random random = new Random();
	
	public DEntity(DLevel level) {
		deleted = false;
		this.level = level;
	}
	
	public void update() {

	}

	public void render(DRenderer r) {

	}

}
