package ducky.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import ducky.Game;

public class DInput implements KeyListener, MouseListener, MouseMotionListener {

	static private List<Integer> keys = new ArrayList<Integer>();
	static private int mxo, myo;
	static public int mx, my, mb;
	static public boolean up, right, down, left, use, duck, esc, attack, attack1, attack2, attack3, space;
	
	public void update(int w, int h) {
		mx = (int)((float)mxo / (float)w * (float)Game.WIDTH);
		my = (int)((float)myo / (float)h * (float)Game.HEIGHT);
//		up = keys.contains(KeyEvent.VK_UP) || keys.contains(KeyEvent.VK_W);
//		left = keys.contains(KeyEvent.VK_LEFT) || keys.contains(KeyEvent.VK_A);
//		down = keys.contains(KeyEvent.VK_DOWN) || keys.contains(KeyEvent.VK_S);
//		right = keys.contains(KeyEvent.VK_RIGHT) || keys.contains(KeyEvent.VK_D);
		up = keys.contains(KeyEvent.VK_UP);
		left = keys.contains(KeyEvent.VK_LEFT);
		down = keys.contains(KeyEvent.VK_DOWN);
		right = keys.contains(KeyEvent.VK_RIGHT);
		space = keys.contains(KeyEvent.VK_SPACE);
		use = keys.contains(KeyEvent.VK_SPACE);
		duck = keys.contains(KeyEvent.VK_SHIFT);
		esc = keys.contains(KeyEvent.VK_ESCAPE);
		attack = keys.contains(KeyEvent.VK_Q);
		attack1 = keys.contains(KeyEvent.VK_W);
		attack2 = keys.contains(KeyEvent.VK_E);
		attack3 = keys.contains(KeyEvent.VK_R);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mxo = e.getX();
		myo = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mxo = e.getX();
		myo = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mb = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mb = 0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys.add(new Integer(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		while(keys.contains(e.getKeyCode()))
			keys.remove(new Integer(e.getKeyCode()));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
