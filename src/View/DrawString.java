package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DrawString {
	public DrawString(Graphics g, String text, int x, int y, int size) {
		Font f = new Font("Super Mario 256", Font.PLAIN, size);

		g.setColor(Color.RED);
		g.setFont(f);

		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

}
