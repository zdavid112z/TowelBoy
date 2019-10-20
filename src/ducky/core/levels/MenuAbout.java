package ducky.core.levels;

import ducky.Game;
import ducky.core.DInput;
import ducky.graphics.DRenderer;
import ducky.graphics.PImage;

public class MenuAbout extends DLevel {

	private PImage image;
	
	public MenuAbout(PImage image) {
		this.image = image;
	}
	
	public void update() {
		if(DInput.esc)
			Game.mainGame.startGame(new MenuMain());
	}
	
	public void render(DRenderer r) {
		r.render(image, 0, 0);
	}
	
}
