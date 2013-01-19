package View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Background {
	private static BufferedImage background;

	public Background(Graphics2D g, String string, int x, int y) {
		URL url = this.getClass().getClassLoader().getResource(string);
		try {
			background = ImageIO.read(url);
		} catch (IOException ex) {
			System.out.println("No Background");
		}
		g.drawImage(background, 0, 0, x, y, Color.black, null);
	}
}
