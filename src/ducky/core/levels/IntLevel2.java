package ducky.core.levels;

import ducky.Game;
import ducky.core.entities.CV;
import ducky.graphics.DRenderer;
import ducky.graphics.Res;

public class IntLevel2 extends InteractiveLevel implements Playable {

	public IntLevel2() {
		super(Res.LEVEL4_BACKGROUND, Res.LEVEL4_INFO, 40, 32);
		int limit = random.nextInt(10) + 10;
		for (int i = 0; i < limit; i++) {
			int skin = random.nextInt(2);
			add(new CV(this, genRandomPos(), Game.HEIGHT - height - 28, skin));
		}
	}

	public void update() {
		super.update();
//		if (player.playerXP >= player.playerXPLimit) {
//			player.endScene();
//			curTime--;
//			if(player.endScene)
//				Game.mainGame.startGame(new SelectionLevel());
//		}
		int cvsleft = 0;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) instanceof CV)
				cvsleft++;
		if (cvsleft == 0) {
			Game.mainGame.startGame(new SelectionLevel());
		}
	}
	
	public void render(DRenderer r) {
		super.render(r);
		r.render(Res.HELP_BREAK, 5, 45);
	}
}
