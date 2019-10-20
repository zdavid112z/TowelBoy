package ducky.graphics;


public class Res {

	public static PImage[] PLAYER_STANDR;
	public static PImage[] PLAYER_STANDL;
	public static PImage[] PLAYER_RUNR;
	public static PImage[] PLAYER_RUNL;
	public static PImage   PLAYER_ATTACKR;
	public static PImage   PLAYER_ATTACKL;
	public static PImage[] PLAYER_ATTACK1;
	public static PImage   PLAYER_ATTACK2R;
	public static PImage   PLAYER_ATTACK2L;
	
	public static PImage   PLAYER_BAR;
	
	public static PImage[][] CV_STANDR;
	public static PImage[][] CV_STANDL;
	public static PImage[][] CV_WALKR;
	public static PImage[][] CV_WALKL;
	public static PImage[]   CV_FLYR;
	public static PImage[]   CV_FLYL;
	
	public static PImage[] PARTICLE_SMOKE;
	
	public static PImage   SELECT_MAP;
	public static PImage   SELECT_DOT;
	public static PImage   CLOCK;
	public static PImage   OUT_OF_TIME;
	
	public static PImage   LEVEL1_BACKGROUND;
	public static PImage   LEVEL1_EOTLR;
	public static PImage   LEVEL1_EOTLL;
	public static PImage   LEVEL1_INFO;
	
	public static PImage   LEVEL2_BACKGROUND;
	public static PImage   LEVEL2_EOTLR;
	public static PImage   LEVEL2_EOTLL;
	public static PImage   LEVEL2_INFO;
	
	public static PImage   LEVEL3_BACKGROUND;
	public static PImage   LEVEL3_INFO;
	
	public static PImage   LEVEL4_BACKGROUND;
	public static PImage   LEVEL4_INFO;
	
	public static PImage   SELECT_INFO;
	
	public static PImage   MENU_MAIN;
	public static PImage   MENU_ABOUT;
	public static PImage   MENU_HELP;
	public static PImage   MENU_HELP_START;
	
	public static PImage   HELP_FILL;
	public static PImage   HELP_SURVIVE;
	public static PImage   HELP_BREAK;;
	
	public static void load() {
		PLAYER_STANDR = new PImage[2];
		PLAYER_STANDR[0] = new PImage("/player/s1r.png");
		PLAYER_STANDR[1] = new PImage("/player/s2r.png");
		PLAYER_STANDL = new PImage[2];
		PLAYER_STANDL[0] = new PImage("/player/s1l.png");
		PLAYER_STANDL[1] = new PImage("/player/s2l.png");
		PLAYER_RUNR = new PImage[2];
		PLAYER_RUNR[0] = new PImage("/player/w1r.png");
		PLAYER_RUNR[1] = new PImage("/player/w2r.png");
		PLAYER_RUNL = new PImage[2];
		PLAYER_RUNL[0] = new PImage("/player/w1l.png");
		PLAYER_RUNL[1] = new PImage("/player/w2l.png");
		PLAYER_ATTACKR = new PImage("/player/ar.png");
		PLAYER_ATTACKL = new PImage("/player/al.png");
		PLAYER_ATTACK1 = new PImage[7];
		PLAYER_ATTACK1[0] = new PImage("/player/a11.png");
		PLAYER_ATTACK1[1] = new PImage("/player/a12.png");
		PLAYER_ATTACK1[2] = new PImage("/player/a13.png");
		PLAYER_ATTACK1[3] = new PImage("/player/a14.png");
		PLAYER_ATTACK1[4] = new PImage("/player/a15.png");
		PLAYER_ATTACK1[5] = new PImage("/player/a16.png");
		PLAYER_ATTACK1[6] = new PImage("/player/a17.png");
		PLAYER_ATTACK2R = new PImage("/player/a2r.png");
		PLAYER_ATTACK2L = new PImage("/player/a2l.png");
		
		PLAYER_BAR = new PImage("/bar.png");
		CLOCK = new PImage("/clock.png");
		OUT_OF_TIME = new PImage("/outoftime.png");
		
		CV_STANDR = new PImage[3][];
		CV_STANDL = new PImage[3][];
		CV_WALKR = new PImage[3][];
		CV_WALKL = new PImage[3][];
		CV_FLYR = new PImage[3];
		CV_FLYL = new PImage[3];
		
		CV_STANDR[0] = new PImage[2];
		CV_STANDR[0][0] = new PImage("/cv1/s1r.png");
		CV_STANDR[0][1] = new PImage("/cv1/s2r.png");
		CV_STANDL[0] = new PImage[2];
		CV_STANDL[0][0] = new PImage("/cv1/s1l.png");
		CV_STANDL[0][1] = new PImage("/cv1/s2l.png");
		CV_WALKR[0] = new PImage[2];
		CV_WALKR[0][0] = new PImage("/cv1/w1r.png");
		CV_WALKR[0][1] = new PImage("/cv1/w2r.png");
		CV_WALKL[0] = new PImage[2];
		CV_WALKL[0][0] = new PImage("/cv1/w1l.png");
		CV_WALKL[0][1] = new PImage("/cv1/w2l.png");
		CV_FLYR[0] = new PImage("/cv1/fr.png");
		CV_FLYL[0] = new PImage("/cv1/fl.png");
		
		CV_STANDR[1] = new PImage[2];
		CV_STANDR[1][0] = new PImage("/cv2/s1r.png");
		CV_STANDR[1][1] = new PImage("/cv2/s2r.png");
		CV_STANDL[1] = new PImage[2];
		CV_STANDL[1][0] = new PImage("/cv2/s1l.png");
		CV_STANDL[1][1] = new PImage("/cv2/s2l.png");
		CV_WALKR[1] = new PImage[2];
		CV_WALKR[1][0] = new PImage("/cv2/w1r.png");
		CV_WALKR[1][1] = new PImage("/cv2/w2r.png");
		CV_WALKL[1] = new PImage[2];
		CV_WALKL[1][0] = new PImage("/cv2/w1l.png");
		CV_WALKL[1][1] = new PImage("/cv2/w2l.png");
		CV_FLYR[1] = new PImage("/cv2/fr.png");
		CV_FLYL[1] = new PImage("/cv2/fl.png");
		
		CV_STANDR[2] = new PImage[2];
		CV_STANDR[2][0] = new PImage("/cv3/s1r.png");
		CV_STANDR[2][1] = new PImage("/cv3/s2r.png");
		CV_STANDL[2] = new PImage[2];
		CV_STANDL[2][0] = new PImage("/cv3/s1l.png");
		CV_STANDL[2][1] = new PImage("/cv3/s2l.png");
		CV_WALKR[2] = new PImage[2];
		CV_WALKR[2][0] = new PImage("/cv3/w1r.png");
		CV_WALKR[2][1] = new PImage("/cv3/w2r.png");
		CV_WALKL[2] = new PImage[2];
		CV_WALKL[2][0] = new PImage("/cv3/w1l.png");
		CV_WALKL[2][1] = new PImage("/cv3/w2l.png");
		CV_FLYR[2] = new PImage("/cv3/fr.png");
		CV_FLYL[2] = new PImage("/cv3/fl.png");
		
		PARTICLE_SMOKE = new PImage[7];
		PARTICLE_SMOKE[0] = new PImage("/particles/smoke1.png");
		PARTICLE_SMOKE[1] = new PImage("/particles/smoke2.png");
		PARTICLE_SMOKE[2] = new PImage("/particles/smoke3.png");
		PARTICLE_SMOKE[3] = new PImage("/particles/smoke4.png");
		PARTICLE_SMOKE[4] = new PImage("/particles/smoke5.png");
		PARTICLE_SMOKE[5] = new PImage("/particles/smoke6.png");
		PARTICLE_SMOKE[6] = new PImage("/particles/smoke7.png");
		
		SELECT_MAP = new PImage("/map.png");
		SELECT_DOT = new PImage("/dot.png");
		
		LEVEL1_BACKGROUND = new PImage("/lvl1back.png");
		LEVEL1_EOTLR = new PImage("/lvl1eotlr.png");
		LEVEL1_EOTLL = new PImage("/lvl1eotll.png");
		LEVEL1_INFO = new PImage("/lvl1info.png");

		LEVEL2_BACKGROUND = new PImage("/lvl2back.png");
		LEVEL2_EOTLR = new PImage("/lvl2eotlr.png");
		LEVEL2_EOTLL = new PImage("/lvl2eotll.png");
		LEVEL2_INFO = new PImage("/lvl2info.png");
		
		LEVEL3_BACKGROUND = new PImage("/lvl3back.png");
		LEVEL3_INFO = new PImage("/lvl3info.png");
		
		LEVEL4_BACKGROUND = new PImage("/lvl4back.png");
		LEVEL4_INFO = new PImage("/lvl4info.png");
		
		SELECT_INFO = new PImage("/select_info.png");
		
		MENU_MAIN = new PImage("/menu_main.png");
		MENU_ABOUT = new PImage("/menu_about.png");
		MENU_HELP = new PImage("/menu_help.png");
		MENU_HELP_START = new PImage("/help_start.png");
		
		HELP_FILL = new PImage("/filler.png");
		HELP_SURVIVE = new PImage("/survive.png");
		HELP_BREAK = new PImage("/break.png");
	}
	
}
