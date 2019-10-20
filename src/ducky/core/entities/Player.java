package ducky.core.entities;

import ducky.core.DInput;
import ducky.core.audio.AudioManager;
import ducky.core.levels.PlayLevel;
import ducky.core.levels.Playable;
import ducky.graphics.DRenderer;
import ducky.graphics.PImage;
import ducky.graphics.Res;
import ducky.core.levels.DLevel;

public class Player extends Mob {

	public int playerXP = 0;
	public final int playerXPLimit = 1000;

	public static final int attack1Cost = 75;
	public static final int attack2Cost = 250;

	public PImage[] standr;
	public PImage[] standl;
	public PImage[] walkr;
	public PImage[] walkl;
	public PImage attackr;
	public PImage attackl;
	public PImage[] attack1;
	public PImage attack2r;
	public PImage attack2l;

	public boolean endScene = false;
	public int ending = 0;

	private int timer;
	private final int frameTimer = 10;
	private final int damageRange = 7;

	private Playable level;
	private int gx = 0;

	public Player(Playable level, int x, int y) {
		super((DLevel) level, x, y, 20, 28);
		this.level = level;
		standr = Res.PLAYER_STANDR;
		standl = Res.PLAYER_STANDL;
		walkr = Res.PLAYER_RUNR;
		walkl = Res.PLAYER_RUNL;
		attackr = Res.PLAYER_ATTACKR;
		attackl = Res.PLAYER_ATTACKL;
		attack1 = Res.PLAYER_ATTACK1;
		attack2r = Res.PLAYER_ATTACK2R;
		attack2l = Res.PLAYER_ATTACK2L;
		setState(STATE_STAND);
		direction = DIRECTION_LEFT;
		speed = 3;
		endScene = false;
	}

	@Override
	public void update() {
		timer++;
		if (ending != 0) {
			if (direction == DIRECTION_RIGHT)
				gx += speed;
			else
				gx -= speed;
			ending++;
			if (ending >= 200)
				endScene = true;
			return;
		}
		if (timer == frameTimer * 3 && state == STATE_ATTACK1)
			doDamage1();
		else if (timer == frameTimer * 6 && state == STATE_ATTACK1)
			doDamage1();
		if (state == STATE_ATTACK2) {
			level.attract(x, timer % 2 == 0 ? 1 : 2);
			level.add(new RandParticle((DLevel) level,
					Res.PARTICLE_SMOKE[random
							.nextInt(Res.PARTICLE_SMOKE.length)], x + sx / 2, y
							+ sy / 2, 60, 100, 0.3));
		}
		if (state == STATE_WALK) {
			if(timer % frameTimer == 0)
				AudioManager.STEP.play();
			if (DInput.right) {
				x += speed;
				direction = DIRECTION_RIGHT;
			} else if (DInput.left) {
				x -= speed;
				direction = DIRECTION_LEFT;
			} else
				setState(STATE_STAND);
			if (DInput.attack) {
				setState(STATE_ATTACK);
				doNormalDamage();
			}
			if (DInput.attack1) {
				setState(STATE_ATTACK1);
				doDamage1();
			}
			if (DInput.attack2) {
				setState(STATE_ATTACK2);
			}
			if (DInput.attack3) {
				//setState(STATE_ATTACK3);
			}
		}
		if (state == STATE_STAND) {
			if (DInput.right) {
				x += speed;
				direction = DIRECTION_RIGHT;
				setState(STATE_WALK);
			}
			if (DInput.left) {
				x -= speed;
				direction = DIRECTION_LEFT;
				setState(STATE_WALK);
			}
			if (DInput.attack) {
				setState(STATE_ATTACK);
				doNormalDamage();
			}
			if (DInput.attack1) {
				setState(STATE_ATTACK1);
				doDamage1();
			}
			if (DInput.attack2) {
				setState(STATE_ATTACK2);
			}
			if (DInput.attack3) {
				//setState(STATE_ATTACK3);
			}
		}
		if (state == STATE_ATTACK) {
			if (timer >= frameTimer)
				setState(STATE_STAND);
		}
		if (state == STATE_ATTACK1) {
			if (timer >= frameTimer * 7.5)
				setState(STATE_STAND);
		}
		if (state == STATE_ATTACK2) {
			if (timer >= frameTimer * 10)
				setState(STATE_STAND);
		}
		if (state == STATE_ATTACK3) {

		}
	}

	@Override
	public void render(DRenderer r) {
		if(ending == 0)
			r.render(getFrame(timer / frameTimer), x, y);
		else r.render(getFrame(timer / frameTimer), gx, y);
		r.renderPlayerBar(12, (double) playerXP / (double) playerXPLimit);
	}

	public PImage getFrame(int f) {
		switch (state) {
		case STATE_STAND:
			switch (direction) {
			case DIRECTION_LEFT:
				return standl[f % 2];
			case DIRECTION_RIGHT:
				return standr[f % 2];
			}
		case STATE_WALK:
			switch (direction) {
			case DIRECTION_LEFT:
				return walkl[f % 2];
			case DIRECTION_RIGHT:
				return walkr[f % 2];
			}
		case STATE_ATTACK:
			switch (direction) {
			case DIRECTION_LEFT:
				return attackl;
			case DIRECTION_RIGHT:
				return attackr;
			}
			break;
		case STATE_ATTACK1:
			switch (direction) {
			case DIRECTION_LEFT:
				return attack1[6 - f % 7];
			case DIRECTION_RIGHT:
				return attack1[f % 7];
			}
			break;
		case STATE_ATTACK2:
			switch (direction) {
			case DIRECTION_LEFT:
				return attack2l;
			case DIRECTION_RIGHT:
				return attack2r;
			}
			break;
		}
		return null;
	}

	public void setState(int s) {
		if (playerXP < attack1Cost && s == STATE_ATTACK1)
			return;
		if (playerXP < attack2Cost && s == STATE_ATTACK2)
			return;
		if(s == STATE_ATTACK2)
			AudioManager.ATTACK2.play();
		state = s;
		timer = 0;
		if (s == STATE_ATTACK1)
			playerXP -= attack1Cost;
		if (s == STATE_ATTACK2)
			playerXP -= attack2Cost;
	}

	public void doDamage(boolean right, int damage) {
		if (state != STATE_ATTACK && state != STATE_ATTACK1)
			return;
		PlayLevel.DamageInfo info = level.damageZone(right ? (x + sx / 2) : (x
				+ sx / 2 - damageRange), y, damageRange, sy, damage);
		playerXP += info.killed * 30;
		playerXP += info.damaged * 8;
		AudioManager.ATTACK.play();
	}

	public void doNormalDamage() {
		doDamage(direction == DIRECTION_RIGHT, 20);
	}

	public void doDamage1() {
		doDamage(true, 45);
		doDamage(false, 45);
	}

	public void endScene() {
		if(ending != 0)
			return;
		setState(STATE_WALK);
		AudioManager.stopMainMusic();
		AudioManager.WIN.play();
		gx = x;
		direction = random.nextInt(2) == 0 ? DIRECTION_RIGHT : DIRECTION_LEFT;
		ending = 1;
	}

	/*
	 * public void doDamage() { level.damageZone(direction == DIRECTION_RIGHT ?
	 * (x + sx / 2) : (x + sx / 2 - damageRange), y, damageRange, sy,
	 * damageValue); }
	 */

}
