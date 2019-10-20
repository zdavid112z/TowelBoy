package ducky.core.levels;

import ducky.Game;
import ducky.core.entities.CV;
import ducky.graphics.DRenderer;
import ducky.graphics.Res;

public class PlayLevel1 extends PlayLevel {

	public PlayLevel1() {
		super(Res.LEVEL1_BACKGROUND, 22, Res.LEVEL1_INFO, Res.LEVEL1_EOTLR,
				Res.LEVEL1_EOTLL, 3, 73, 25, 60);
		int limit = random.nextInt(20) + 30;
		for (int i = 0; i < limit; i++) {
			add(new CV(this, genRandomPos(), Game.HEIGHT - height - 28, 2));
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
