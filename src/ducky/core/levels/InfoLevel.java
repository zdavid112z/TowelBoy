package ducky.core.levels;

import ducky.Game;
import ducky.core.DInput;
import ducky.graphics.DRenderer;
import ducky.graphics.Res;

public class InfoLevel extends DLevel {
	
	public InfoLevel() {
		
	}
	
	public void update() {
		if(DInput.space)
			Game.mainGame.startGame(new SelectionLevel());
		if(DInput.esc)
			Game.mainGame.startGame(new MenuMain());
	}
	
	public void render(DRenderer r) {
		r.render(Res.MENU_HELP_START, 0, 0);
	}

}
