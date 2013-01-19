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

		if (other instanceof Invaders) {
			this.health=0;

		}if (other instanceof Invader_Shot) {
			this.health=health-1;
			
		}
	}
}
