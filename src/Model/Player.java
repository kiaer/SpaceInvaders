package Model;

import View.View;

public class Player extends GameObjects {

	public Player(View view, String ref, int x, int y, int health) {
		super(ref, x, y, health);
	}

	public void move(long delta) {
		super.move(delta);
	}

	public void collide(GameObjects other) {
	}
}
