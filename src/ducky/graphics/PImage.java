package ducky.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class PImage {

	public int width, height;
	public int[] pixels;

	public PImage(String path) {
		try {
			BufferedImage image = ImageIO.read(this.getClass().getResource(path));
			if(image == null)
				throw new Exception();
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (Exception e) {
			System.err.println("Could not load image " + path);
			//e.printStackTrace();
		}
	}

}
