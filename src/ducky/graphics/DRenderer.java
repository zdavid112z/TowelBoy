package ducky.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import ducky.core.levels.DLevel;

public class DRenderer {

	public final int width, height;
	public int dwidth, dheight;
	private Graphics g;
	private int offset;

	private BufferedImage image;
	private int[] pixels;

	public DRenderer(int w, int h) {
		width = w;
		height = h;
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public void begin(Graphics g, int w, int h) {
		this.g = g;
		dwidth = w;
		dheight = h;
		offset = 0;
		clear();
	}

	public void end() {
		g.drawImage(image, 0, 0, dwidth, dheight, null);
	}

	public void setXOffset(int x) {
		offset = x;
	}

	public void clear() {
		for (int i = 0; i < width * height; i++)
			pixels[i] = 0xd0d0d0;
	}

	public void render(DLevel l) {
		l.render(this);
	}

	public void render(PImage p, int xd, int yd, int overlay, float alpha) {
		xd -= offset;
		for (int x = 0; x < p.width; x++) {
			if (x + xd < 0)
				continue;
			if (x + xd >= width)
				continue;
			for (int y = 0; y < p.height; y++) {
				if (y + yd < 0)
					continue;
				if (y + yd >= height)
					continue;
				if ((p.pixels[x + y * p.width] & 0xffffff) == 0xff00ff)
					continue;
				Color dest = new Color(p.pixels[x + y * p.width] & 0xffffff);
				int r = (int) (((overlay & 0xff0000) >> 16) * (1.0 - alpha) + dest
						.getRed() * alpha);
				int g = (int) (((overlay & 0xff00) >> 8) * (1.0 - alpha) + dest
						.getGreen() * alpha);
				int b = (int) (((overlay & 0xff)) * (1.0 - alpha) + dest
						.getBlue() * alpha);
				int color = r << 16 | g << 8 | b;
				pixels[(x + xd) + (y + yd) * width] = color;
			}
		}
	}

	public void render(PImage p, int xd, int yd, double alpha) {
		xd -= offset;
		for (int x = 0; x < p.width; x++) {
			if (x + xd < 0)
				continue;
			if (x + xd >= width)
				continue;
			for (int y = 0; y < p.height; y++) {
				if (y + yd < 0)
					continue;
				if (y + yd >= height)
					continue;
				if ((p.pixels[x + y * p.width] & 0xffffff) == 0xff00ff)
					continue;
				setPixel(x + xd, y + yd, p.pixels[x + y * p.width], alpha);
			}
		}
	}

	public void render(PImage p, int xd, int yd) {
		xd -= offset;
		for (int x = 0; x < p.width; x++) {
			if (x + xd < 0)
				continue;
			if (x + xd >= width)
				continue;
			for (int y = 0; y < p.height; y++) {
				if (y + yd < 0)
					continue;
				if (y + yd >= height)
					continue;
				if ((p.pixels[x + y * p.width] & 0xffffff) == 0xff00ff)
					continue;
				pixels[(x + xd) + (y + yd) * width] = p.pixels[x + y * p.width];
			}
		}
	}

	public void renderCV(PImage p, int xd, int yd, int color) {
		xd -= offset;
		for (int x = 0; x < p.width; x++) {
			if (x + xd < 0)
				continue;
			if (x + xd >= width)
				continue;
			for (int y = 0; y < p.height; y++) {
				if (y + yd < 0)
					continue;
				if (y + yd >= height)
					continue;
				if ((p.pixels[x + y * p.width] & 0xffffff) == 0xff00ff)
					continue;
				if ((p.pixels[x + y * p.width] & 0x0020C4) == 0x0020C4)
					pixels[(x + xd) + (y + yd) * width] = color;
				else
					pixels[(x + xd) + (y + yd) * width] = p.pixels[x + y
							* p.width];
			}
		}
	}

	public void renderLine(int sx, int sy, int dx, int dy, int col, double alpha) {
		double mx = dx - sx;
		double my = dy - sy;
		double len = Math.sqrt(mx * mx + my * my);
		mx /= len;
		my /= len;
		double cx = sx - mx, cy = sy - my;
		double px, py;
		while (true) {
			if (mx < 0) {
				if (cx < dx)
					return;
			} else if (cx > dx)
				return;
			if (my < 0) {
				if (cy < dy)
					return;
			} else if (cy > dy)
				return;
			px = cx;
			py = cy;
			cx += mx;
			cy += my;
			if (cx < 0)
				return;
			if (cx >= width)
				return;
			if (cy < 0)
				return;
			if (cy >= height)
				return;
			if ((int) cx == (int) px && (int) cy == (int) py)
				continue;
			setPixel((int) cx, (int) cy, col, alpha);
		}
	}

	public void renderPlayerBar(int yd, double level) {
		if(level < 0)
			level = 0;
		if(level > 1)
			level = 1;
		PImage p = Res.PLAYER_BAR;
		final int beg = 15;
		final int end = 303;
		int maxlevel = (int) ((end - beg) * level + beg);
		int xd = 0;
		for (int x = 0; x < p.width; x++) {
			if (x + xd < 0)
				continue;
			if (x + xd >= width)
				continue;
			for (int y = 0; y < p.height; y++) {
				if (y + yd < 0)
					continue;
				if (y + yd >= height)
					continue;
				if ((p.pixels[x + y * p.width] & 0xffffff) == 0xff00ff)
					continue;
				if(x > maxlevel && x <= end && y >= 2 && y < 30)
					continue;
					pixels[(x + xd) + (y + yd) * width] = p.pixels[x + y
							* p.width];
			}
		}
	}
	
	public void renderRect(int sx, int sy, int dx, int dy, int color, double alpha) {
		for (int x = 0; x < dx - sx; x++) {
			if (x + sx < 0)
				continue;
			if (x + sx >= width)
				continue;
			for (int y = 0; y < dy - sy; y++) {
				if (y + sy < 0)
					continue;
				if (y + sy >= height)
					continue;
				setPixel(x + sx, y + sy, color, alpha);
			}
		}
	}

	protected void setPixel(int lx, int ly, int color, double alpha) {
		int src = pixels[lx + ly * width];
		int r = (int) (((src & 0xff0000) >> 16) * (1.0 - alpha) + ((color & 0xff0000) >> 16)
				* alpha);
		int g = (int) (((src & 0xff00) >> 8) * (1.0 - alpha) + ((color & 0xff00) >> 8)
				* alpha);
		int b = (int) (((src & 0xff)) * (1.0 - alpha) + ((color & 0xff))
				* alpha);
		int col = r << 16 | g << 8 | b;
		pixels[lx + ly * width] = col;
	}
}
