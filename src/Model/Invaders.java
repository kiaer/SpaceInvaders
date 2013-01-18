package Model;

import java.awt.Color;
import java.awt.Point;

import View.View;

import spaceinvaders.Constants;

public class Invaders extends GameObjects {

	private View view;

	public Invaders(View view, String ref, int x, int y) {
		super(ref, x, y);

		this.view = view;

		dx = Constants.ALIEN_MOVE;

	}

	
	public void move(long delta) {

		if ((dx < 0) && (x < 10)) {
			
			view.changeDirection();
			
		}
		if ((dx > 0) && (x > 750)) {

			view.changeDirection();
		}
		
		// proceed with normal move
		

	//	super.changeDirection();
	}
	


	@Override
	public void collide(GameObjects other) {
		
	}

}
