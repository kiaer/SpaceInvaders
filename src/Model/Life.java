package Model;

import View.View;

public class Life extends GameObjects {

	public Life(View view, String ref, int x, int y, int health) {
		super(ref, x, y, health);
	}


	public void collide(GameObjects other) {

		if (other instanceof Invaders) {
		}

	}
}
