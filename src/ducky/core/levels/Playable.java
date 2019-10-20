package ducky.core.levels;

import ducky.core.entities.DEntity;
import ducky.graphics.PImage;


public interface Playable{
	
	public class DamageInfo {
		public int damaged, killed;
	}
	
	abstract DamageInfo damageZone(int x, int y, int sx, int sy, int damage); 
	abstract int genRandomPos() ;
	abstract boolean outOfTime();
	abstract void attract(int x, int speed);
	abstract PImage getInfo();
	abstract void add(DEntity e);
}
