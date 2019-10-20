package ducky.core.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ducky.core.entities.DEntity;
import ducky.graphics.DRenderer;

public abstract class DLevel {

	protected List<DEntity> list = new ArrayList<DEntity>();
	protected static Random random = new Random();
	
	public DLevel() {

	}

	public void add(DEntity e) {
		list.add(e);
	}

	public void remove(DEntity e) {
		list.remove(e);
	}

	public void update() {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).deleted) {
				list.remove(i);
				i--;
				continue;
			}
			list.get(i).update();
		}
	}

	public void render(DRenderer r) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).render(r);
		}
	}

}
