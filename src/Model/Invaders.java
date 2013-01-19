package Model;

import View.View;

import spaceinvaders.Constants;

public class Invaders extends GameObjects {

	private View view;

	public Invaders(View view, String ref, int x, int y, int health) {
		super(ref, x, y, health);

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

	}

	public void collide(GameObjects other) {
		
	}

}
