package ducky.core.levels;

import ducky.Game;
import ducky.core.entities.CV;
import ducky.graphics.DRenderer;
import ducky.graphics.Res;

public class PlayLevel2 extends PlayLevel {

	public PlayLevel2() {
		super(Res.LEVEL2_BACKGROUND, 82, Res.LEVEL2_INFO, Res.LEVEL2_EOTLR,
				Res.LEVEL2_EOTLL, 4, 0, 0, 60);
		int limit = random.nextInt(30) + 50;
		for (int i = 0; i < limit; i++) {
			int skin = random.nextInt(2);
			add(new CV(this, genRandomPos(), Game.HEIGHT - height - 28, skin));
		}
	}
	
	public void update() {
		super.update();
		if (player.playerXP >= player.playerXPLimit) {
			player.endScene();
			curTime--;
			if(player.endScene)
				Game.mainGame.startGame(new SelectionLevel());
		}
	}
	
	public void render(DRenderer r) {
		super.render(r);
		r.render(Res.HELP_FILL, 5, 45);
	}

}
