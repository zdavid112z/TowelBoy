package ducky.core.levels;

import ducky.Game;
import ducky.core.entities.CV;
import ducky.graphics.DRenderer;
import ducky.graphics.Res;

public class IntLevel1 extends InteractiveLevel {

	private int numAdv = 2;
	private int wave = 0;
	private int cvs = 1;

	public IntLevel1() {
		super(Res.LEVEL3_BACKGROUND, Res.LEVEL3_INFO, 10, 39);
		player.playerXP = player.playerXPLimit / 2;
	}

	public void update() {
		super.update();
		int cvsleft = 0;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) instanceof CV)
				if (!((CV) (list.get(i))).dead)
					cvsleft++;
		if (cvsleft == 0) {
			curTime = 0;
			for (int i = 0; i < cvs; i++) {
				add(new CV(this, genRandomPos(), Game.HEIGHT - height - 28, 2));
			}
			wave++;
			if (wave % numAdv == 0)
				cvs++;
		}
		if (wave == 12) {
			player.endScene();
			curTime--;
			if (player.endScene)
				Game.mainGame.startGame(new SelectionLevel());
		}

	}

	public void render(DRenderer r) {
		super.render(r);
		r.render(Res.HELP_SURVIVE, 5, 45);
	}

}
