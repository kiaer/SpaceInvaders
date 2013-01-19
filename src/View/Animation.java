package View;

import java.awt.Graphics;
import java.awt.Image;

public class Animation {
	
	
	private Image image;

	public Animation(Image image, Image image1) {
		long delta = System.currentTimeMillis();
		if(delta%2==0){
		this.image = image1;
		}else{
			this.image=image;
		}
	}


	public int getWidth() {
		return image.getWidth(null);
	}

	public int getHeight() {
		return image.getHeight(null);
	}

	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}
	
		

	}


	
